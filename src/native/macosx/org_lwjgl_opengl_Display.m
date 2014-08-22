/* 
 * Copyright (c) 2002-2008 LWJGL Project
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are 
 * met:
 * 
 * * Redistributions of source code must retain the above copyright 
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'LWJGL' nor the names of 
 *   its contributors may be used to endorse or promote products derived 
 *   from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * $Id$
 *
 * Mac OS X specific display functions.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @author mojang
 * @author kappaOne <one.kappa@gmail.com>
 * @version $Revision$
 */

#import <AppKit/NSApplication.h>
#import <Cocoa/Cocoa.h>
#import <Carbon/Carbon.h>
#import <jni.h>
#import <unistd.h>
#import "common_tools.h"
#import "org_lwjgl_opengl_MacOSXDisplay.h"
#import "org_lwjgl_MacOSXSysImplementation.h"
#import "context.h"

static NSOpenGLPixelFormat *default_format = nil;

static NSAutoreleasePool *pool;

static MacOSXPeerInfo *peer_info;

static bool leftMouseDown = false;
static bool rightMouseDown = false;

static NSUInteger lastModifierFlags = 0;

@implementation MacOSXKeyableWindow

+ (void) createWindow {
	MacOSXWindowInfo *window_info = peer_info->window_info;
	
	int width = window_info->display_rect.size.width;
	int height = window_info->display_rect.size.height;
	
	NSRect view_rect = NSMakeRect(0.0, 0.0, width, height);
	window_info->view = [[MacOSXOpenGLView alloc] initWithFrame:view_rect pixelFormat:peer_info->pixel_format];
	[window_info->view setAutoresizingMask:NSViewWidthSizable | NSViewHeightSizable];

    // Inform the view of its parent window info;
	[window_info->view setParent:window_info];
	
	if (window_info->enableHighDPI) {
		// call method using runtime selector as its a 10.7+ api and allows compiling on older SDK's
		[window_info->view performSelector:NSSelectorFromString(@"setWantsBestResolutionOpenGLSurface:") withObject:YES];
	}
	
	// set nsapp delegate for catching app quit events
	[NSApp setDelegate:window_info->view];
	
	if (window_info->context != nil) {
		[window_info->view setOpenGLContext:window_info->context];
	}
	
	if (!window_info->fullscreen) {
		
		if (window_info->parented) {
			window_info->window = [peer_info->parent window];
			[peer_info->parent addSubview:window_info->view];
		}
		else {
			
			int default_window_mask = NSBorderlessWindowMask; // undecorated
			
			if (!window_info->undecorated) {
				default_window_mask = NSTitledWindowMask | NSClosableWindowMask | NSMiniaturizableWindowMask;
			}
			
			if (window_info->resizable) {
				default_window_mask |= NSResizableWindowMask;
			}
			
			window_info->window = [[MacOSXKeyableWindow alloc] initWithContentRect:window_info->display_rect styleMask:default_window_mask backing:NSBackingStoreBuffered defer:NO];
			
			[window_info->window setContentView:window_info->view];
			[window_info->window setContentView:window_info->view]; // call twice to fix issue
			
			// set NSView as delegate of NSWindow to get windowShouldClose events
			[window_info->window setDelegate:window_info->view];
		}
		
		// disable any fixed backbuffer size to allow resizing
		CGLContextObj cgcontext = (CGLContextObj)[[window_info->view openGLContext] CGLContextObj];
		CGLDisable(cgcontext, kCGLCESurfaceBackingSize);
	}
	else {
		// set a fixed backbuffer size for fullscreen
		CGLContextObj cgcontext = (CGLContextObj)[window_info->context CGLContextObj];
		GLint dim[2] = {width, height};
		CGLSetParameter(cgcontext, kCGLCPSurfaceBackingSize, dim);
		CGLEnable(cgcontext, kCGLCESurfaceBackingSize);
		
		// enter fullscreen mode
		[window_info->view enterFullScreenMode: [NSScreen mainScreen] withOptions: nil ];
		window_info->window = [window_info->view window];
        
		// adjust the NSView bounds to correct mouse coordinates in fullscreen
		NSSize windowSize = [window_info->window frame].size;
		NSSize newBounds = NSMakeSize(windowSize.width/width*windowSize.width, windowSize.height/height*windowSize.height);
		[window_info->view setBoundsSize:newBounds];
	}
	
	if (window_info->enableFullscreenModeAPI && window_info->resizable) {
		// manually create OS X 10.7+ mask to allow compilation on previous OS X versions
		NSUInteger NSWindowCollectionBehaviorFullScreenPrimary = 1 << 7;
		[window_info->window setCollectionBehavior:NSWindowCollectionBehaviorFullScreenPrimary];
	}
	
	[window_info->window makeFirstResponder:window_info->view];
	[window_info->window setInitialFirstResponder:window_info->view];
	[window_info->window makeKeyAndOrderFront:[NSApplication sharedApplication]];
}

+ (void) destroyWindow {
	MacOSXWindowInfo *window_info = peer_info->window_info;
	
	if (window_info->fullscreen) {
		[window_info->view exitFullScreenModeWithOptions: nil];
		window_info->window = nil;
	}
	else {
		if (peer_info->isCALayer) {
			peer_info->isCALayer = false;
            [peer_info->glLayer removeLayer];
            [peer_info->glLayer release];
            peer_info->glLayer = nil;
            return;
		}
		
		if (window_info->window != nil) {
			// if the nsview has no parent then close window
			if ([window_info->window contentView] == window_info->view) {
				// release the nsview and remove it from any parent nsview
				[window_info->view removeFromSuperviewWithoutNeedingDisplay];
				[window_info->window close];
				window_info->window = nil;
			}
			else {
				// release the nsview and remove it from any parent nsview
				[window_info->view removeFromSuperviewWithoutNeedingDisplay];
			}
		}
	}
}

- (BOOL)canBecomeKeyWindow;
{
    return YES;
}
@end

@implementation MacOSXOpenGLView

+ (NSOpenGLPixelFormat*)defaultPixelFormat {
	NSOpenGLPixelFormatAttribute defaultAttribs[] = {
		NSOpenGLPFADoubleBuffer,
		NSOpenGLPFADepthSize, 16,
		NSOpenGLPFAColorSize, 32,
		0
	};
	if (default_format == nil) {
		default_format = [[NSOpenGLPixelFormat alloc] initWithAttributes:defaultAttribs];
	}
	return default_format;
}

- (BOOL)windowShouldClose:(id)sender {
	if (_parent != nil) {
		JNIEnv *env = attachCurrentThread();
		jclass display_class = (*env)->GetObjectClass(env, _parent->jdisplay);
		jmethodID close_callback = (*env)->GetMethodID(env, display_class, "doHandleQuit", "()V");
		(*env)->CallVoidMethod(env, _parent->jdisplay, close_callback);
	}
	return NO;
}

- (NSApplicationTerminateReply)applicationShouldTerminate:(NSApplication *)sender {
	[self windowShouldClose:nil];
	return NSTerminateCancel;
}

- (id)initWithFrame:(NSRect)frameRect pixelFormat:(NSOpenGLPixelFormat*)format {
	self = [super initWithFrame:frameRect];
	if (self != nil) {
		_pixelFormat = [format retain];
		[[NSNotificationCenter defaultCenter] addObserver:self
												selector:@selector(_surfaceNeedsUpdate:)
												name:NSViewGlobalFrameDidChangeNotification
												object:self];
	}
	return self;
}

- (void) _surfaceNeedsUpdate:(NSNotification*)notification {
	[self update];
}

- (void)setOpenGLContext:(NSOpenGLContext*)context {
	_openGLContext = context;
}

- (NSOpenGLContext*)openGLContext {
	return _openGLContext;
}

- (void)clearGLContext {
	[_openGLContext release];
	_openGLContext = nil;
}

- (void)prepareOpenGL {

}

- (void)update {
	[_openGLContext update];
}

- (void)lockFocus {
	[super lockFocus];
	
	NSOpenGLContext* context = [self openGLContext];
	
	if (context == nil) return;
	
	if ([context view] != self) {
		[context setView:self];
	}
	
	[context makeCurrentContext];
}

- (void)setPixelFormat:(NSOpenGLPixelFormat*)pixelFormat {
	_pixelFormat = [pixelFormat retain];
}

- (NSOpenGLPixelFormat*)pixelFormat {
	return _pixelFormat;
}

- (BOOL)acceptsFirstResponder {
	return YES;
}

- (void)setParent:(MacOSXWindowInfo*)parent {
	_parent = parent;
}

- (void)keyDown:(NSEvent *)event {
	JNIEnv *env = attachCurrentThread();
	if (env == nil || event == nil || _parent == nil || _parent->jkeyboard == nil) {
		return;
	}
	long time = [event timestamp] * 1000000000;
	jclass keyboard_class = (*env)->GetObjectClass(env, _parent->jkeyboard);
	jmethodID keydown = (*env)->GetMethodID(env, keyboard_class, "keyPressed", "(ILjava/lang/String;J)V");
	
	// convert key characters from NSString to jstring
	const char *unichars = [[event characters] UTF8String];
	jstring characters = (*env)->NewStringUTF(env, unichars);
	
	(*env)->CallVoidMethod(env, _parent->jkeyboard, keydown, [event keyCode], characters, time);
}

- (void)keyUp:(NSEvent *)event {
	JNIEnv *env = attachCurrentThread();
	if (env == nil || event == nil || _parent == nil || _parent->jkeyboard == nil) {
		return;
	}
	long time = [event timestamp] * 1000000000;
	jclass keyboard_class = (*env)->GetObjectClass(env, _parent->jkeyboard);
	jmethodID keyup = (*env)->GetMethodID(env, keyboard_class, "keyReleased", "(ILjava/lang/String;J)V");
	
	// convert key characters from NSString to jstring
	const char *unichars = [[event characters] UTF8String];
	jstring characters = (*env)->NewStringUTF(env, unichars);
	
	(*env)->CallVoidMethod(env, _parent->jkeyboard, keyup, [event keyCode], characters, time);
}

- (void)flagsChanged:(NSEvent *)event {
	JNIEnv *env = attachCurrentThread();
	if (env == nil || event == nil || _parent == nil || _parent->jkeyboard == nil) {
		return;
	}
	long time = [event timestamp] * 1000000000;

	NSUInteger modifierFlags = [event modifierFlags];
	unsigned short keyCode = [event keyCode];
    
	NSUInteger mask = ~0;
	
	switch(keyCode) {
		case kVK_Control     : mask = NSControlLeftKeyMask; break;
		case kVK_Shift       : mask = NSShiftLeftKeyMask; break;
		case kVK_RightShift  : mask = NSShiftRightKeyMask; break;
		case kVK_Command     : mask = NSCommandLeftKeyMask; break;
		case 0x36            : mask = NSCommandRightKeyMask; break; // Should be: kVK_RightCommand -- missing O.o
		case kVK_Option      : mask = NSAlternateLeftKeyMask; break;
		case kVK_RightOption : mask = NSAlternateRightKeyMask; break;
		case kVK_RightControl: mask = NSControlRightKeyMask; break;
		case kVK_CapsLock    : mask = NSAlphaShiftKeyMask; break;
		case kVK_Function    : mask = NSFunctionKeyMask; break;
		// case 0x??            : mask = NSNumericPadKeyMask; break; // Didn't have the keycode for this one :(
		default:
			// key code not specified when left Command key + Tab moves focus to another Window, therefore manually detect and specify correct key code
			if (((lastModifierFlags & NSCommandLeftKeyMask) == NSCommandLeftKeyMask) && ((modifierFlags & NSCommandLeftKeyMask) != NSCommandLeftKeyMask)) {
				keyCode = kVK_Command; // left command key code
			}
			// key code not specified when right Command key + Tab moves focus to another Window, therefore manually detect and specify correct key code
			else if (((lastModifierFlags & NSCommandLeftKeyMask) == NSCommandLeftKeyMask) && ((modifierFlags & NSCommandLeftKeyMask) != NSCommandLeftKeyMask)) {
				keyCode = 0x36; // right command key code
			}
			else {
				NSLog(@"Unknown modifier with keycode: %d\n", [event keyCode]);
				return;
			}
	}

	// save current modifierFlags for next use
	lastModifierFlags = [event modifierFlags];
    
	jclass keyboard_class = (*env)->GetObjectClass(env, _parent->jkeyboard);

	jmethodID keyMethod;
	if ((modifierFlags & mask) == mask) {
		keyMethod = (*env)->GetMethodID(env, keyboard_class, "keyPressed", "(ILjava/lang/String;J)V");
	} else {
		keyMethod = (*env)->GetMethodID(env, keyboard_class, "keyReleased", "(ILjava/lang/String;J)V");
	}

	(*env)->CallVoidMethod(env, _parent->jkeyboard, keyMethod, keyCode, NULL, time);
}

- (void)mouseButtonState:(NSEvent *)event :(int)button :(int)state {
	JNIEnv *env = attachCurrentThread();
	if (env == nil || event == nil || _parent == nil || _parent->jkeyboard == nil) {
		return;
	}
	long time = [event timestamp] * 1000000000;
	jclass mouse_class = (*env)->GetObjectClass(env, _parent->jmouse);
	jmethodID mousebutton = (*env)->GetMethodID(env, mouse_class, "setButton", "(IIJ)V");
	(*env)->CallVoidMethod(env, _parent->jmouse, mousebutton, button, state, time);
}

- (void)mouseDown:(NSEvent *)event {
	if ([event modifierFlags] & NSControlKeyMask) {
		rightMouseDown = true;
		[self rightMouseDown:event];
		return;
	}
	
	leftMouseDown = true;
	[self mouseButtonState:event :0 :1];
}

- (void)rightMouseDown:(NSEvent *)event {
	[self mouseButtonState:event :1 :1];
}

- (void)otherMouseDown:(NSEvent *)event {
	[self mouseButtonState:event :2 :1];
}

- (void)mouseUp:(NSEvent *)event {
	if (rightMouseDown) {
		rightMouseDown = false;
		[self rightMouseUp:event];
	}
    
	if (leftMouseDown) {
		leftMouseDown = false;
		[self mouseButtonState:event :0 :0];
	}
}

- (void)rightMouseUp:(NSEvent *)event {
	[self mouseButtonState:event :1 :0];
}

- (void)otherMouseUp:(NSEvent *)event {
	[self mouseButtonState:event :2 :0];
}

- (void)mouseDragged:(NSEvent *)event {
	JNIEnv *env = attachCurrentThread();
	if (env == nil || event == nil || _parent == nil || _parent->jmouse == nil) {
		return;
	}
	long time = [event timestamp] * 1000000000;
	jclass mouse_class = (*env)->GetObjectClass(env, _parent->jmouse);
	jmethodID mousemove = (*env)->GetMethodID(env, mouse_class, "mouseMoved", "(FFFFFJ)V");
	NSPoint loc = [self convertPoint:[event locationInWindow] toView:nil];
	(*env)->CallVoidMethod(env, _parent->jmouse, mousemove, loc.x, loc.y, [event deltaX], [event deltaY], 0.0f, time);
}

- (void)rightMouseDragged:(NSEvent *)event {
	JNIEnv *env = attachCurrentThread();
	if (env == nil || event == nil || _parent == nil || _parent->jmouse == nil) {
		return;
	}
	long time = [event timestamp] * 1000000000;
	jclass mouse_class = (*env)->GetObjectClass(env, _parent->jmouse);
	jmethodID mousemove = (*env)->GetMethodID(env, mouse_class, "mouseMoved", "(FFFFFJ)V");
	NSPoint loc = [self convertPoint:[event locationInWindow] toView:nil];
	(*env)->CallVoidMethod(env, _parent->jmouse, mousemove, loc.x, loc.y, [event deltaX], [event deltaY], 0.0f, time);
}

- (void)otherMouseDragged:(NSEvent *)event {
	JNIEnv *env = attachCurrentThread();
	if (env == nil || event == nil || _parent == nil || _parent->jmouse == nil) {
		return;
	}
	long time = [event timestamp] * 1000000000;
	jclass mouse_class = (*env)->GetObjectClass(env, _parent->jmouse);
	jmethodID mousemove = (*env)->GetMethodID(env, mouse_class, "mouseMoved", "(FFFFFJ)V");
	NSPoint loc = [self convertPoint:[event locationInWindow] toView:nil];
	(*env)->CallVoidMethod(env, _parent->jmouse, mousemove, loc.x, loc.y, [event deltaX], [event deltaY], 0.0f, time);
}

- (void)mouseMoved:(NSEvent *)event {
	JNIEnv *env = attachCurrentThread();
	if (env == nil || event == nil || _parent == nil || _parent->jmouse == nil) {
		return;
	}
	long time = [event timestamp] * 1000000000;
	jclass mouse_class = (*env)->GetObjectClass(env, _parent->jmouse);
	jmethodID mousemove = (*env)->GetMethodID(env, mouse_class, "mouseMoved", "(FFFFFJ)V");
	NSPoint loc = [self convertPoint:[event locationInWindow] toView:nil];
	(*env)->CallVoidMethod(env, _parent->jmouse, mousemove, loc.x, loc.y, [event deltaX], [event deltaY], 0.0f, time);
}

- (void)scrollWheel:(NSEvent *)event {
	JNIEnv *env = attachCurrentThread();
	if (env == nil || event == nil || _parent == nil) {
		return;
	}
	long time = [event timestamp] * 1000000000;
	jclass mouse_class = (*env)->GetObjectClass(env, _parent->jmouse);
	jmethodID mousemove = (*env)->GetMethodID(env, mouse_class, "mouseMoved", "(FFFFFJ)V");
	NSPoint loc = [self convertPoint:[event locationInWindow] toView:nil];
	(*env)->CallVoidMethod(env, _parent->jmouse, mousemove, loc.x, loc.y, [event deltaX], [event deltaY], 1.0f, time);
}

- (void)viewDidMoveToWindow {
	[[NSNotificationCenter defaultCenter] addObserver:self
											selector:@selector(windowResized:)
											name:NSWindowDidResizeNotification
											object:[self window]];
}

- (void)dealloc {
	[[NSNotificationCenter defaultCenter] removeObserver:self];
	[super dealloc];
}

- (void)windowResized:(NSNotification *)notification; 
{
	if (_parent != nil) {
		_parent->display_rect = [self frame];
		_parent->resized = JNI_TRUE;
	}
}

- (void)viewDidChangeBackingProperties {
	JNIEnv *env = attachCurrentThread();
	if (env == nil || _parent == nil || _parent->jdisplay == nil) {
		return;
	}
	
	jclass display_class = (*env)->GetObjectClass(env, _parent->jdisplay);
	jmethodID setScaleFactor_callback = (*env)->GetMethodID(env, display_class, "setScaleFactor", "(F)V");
	
	CGFloat scaleFactor;
	
	// call method using runtime selector as its a 10.7+ api and allows compiling on older SDK's
	SEL selector = NSSelectorFromString(@"backingScaleFactor");
	
	// as we are using a runtime selector, we need to use NSInvocations to get a CGFloat value back from it
	NSInvocation *invocation = [NSInvocation invocationWithMethodSignature:[[[self window] class] instanceMethodSignatureForSelector:selector]];
	[invocation setSelector:selector];
	[invocation setTarget:[self window]];
	[invocation invoke];
	[invocation getReturnValue:&scaleFactor];
	
	(*env)->CallVoidMethod(env, _parent->jdisplay, setScaleFactor_callback, scaleFactor);
}

-(void)updateTrackingAreas {
	if(_trackingArea != nil) {
		[self removeTrackingArea:_trackingArea];
		[_trackingArea release];
	}
	
	int options = (NSTrackingMouseEnteredAndExited | NSTrackingActiveAlways);
	_trackingArea = [[NSTrackingArea alloc] initWithRect:[self bounds]
															options:options
															owner:self
															userInfo:nil];
	[self addTrackingArea:_trackingArea];
	
	// since nstrackingarea's don't know if mouse is inside or outside on creation
	// manually detect this and send a fake mouse entered/exited message
	NSPoint mouseLocation = [[self window] mouseLocationOutsideOfEventStream];
    mouseLocation = [self convertPoint:mouseLocation fromView:nil];
	
    if (NSPointInRect(mouseLocation, [self bounds])) {
		[self mouseEntered:nil];
	}
	else {
		[self mouseExited:nil];
	}
}

-(void)mouseEntered:(NSEvent *)event {
	JNIEnv *env = attachCurrentThread();
	if (env == nil || _parent == nil || _parent->jdisplay == nil) {
		return;
	}
	
	jclass display_class = (*env)->GetObjectClass(env, _parent->jdisplay);
	jmethodID mouseInsideWindow_callback = (*env)->GetMethodID(env, display_class, "mouseInsideWindow", "(Z)V");
	(*env)->CallVoidMethod(env, _parent->jdisplay, mouseInsideWindow_callback, JNI_TRUE);
}

-(void)mouseExited:(NSEvent *)event {
	JNIEnv *env = attachCurrentThread();
	if (env == nil || _parent == nil || _parent->jdisplay == nil) {
		return;
	}
	
	jclass display_class = (*env)->GetObjectClass(env, _parent->jdisplay);
	jmethodID mouseInsideWindow_callback = (*env)->GetMethodID(env, display_class, "mouseInsideWindow", "(Z)V");
	(*env)->CallVoidMethod(env, _parent->jdisplay, mouseInsideWindow_callback, JNI_FALSE);
}

- (void) drawRect:(NSRect)rect {
	// set black as the default background color 
	// for the nsview to avoid white flash on fullscreen
	[[NSColor blackColor] setFill];
	NSRectFill(rect);
}
@end

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_nIsMiniaturized(JNIEnv *env, jobject this, jobject window_handle) {
	MacOSXWindowInfo *window_info = (MacOSXWindowInfo *)(*env)->GetDirectBufferAddress(env, window_handle);
	return (jboolean)[window_info->window isMiniaturized];
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_nIsFocused(JNIEnv *env, jobject this, jobject window_handle) {
	MacOSXWindowInfo *window_info = (MacOSXWindowInfo *)(*env)->GetDirectBufferAddress(env, window_handle);
	// Display is focused if nswindow is key window and nsview is first responder in that nswindow
	return (jboolean)([[window_info->view window] isKeyWindow] && [[window_info->view window] firstResponder] == window_info->view);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_nResizeWindow(JNIEnv *env, jobject this, jobject window_handle, jint x, jint y, jint width, jint height) {
	MacOSXWindowInfo *window_info = (MacOSXWindowInfo *)(*env)->GetDirectBufferAddress(env, window_handle);
	window_info->display_rect = NSMakeRect(x, y, width, height);
	[window_info->window setFrame:window_info->display_rect display:false];
	[window_info->view update];
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_nWasResized(JNIEnv *env, jobject this, jobject window_handle) {
	MacOSXWindowInfo *window_info = (MacOSXWindowInfo *)(*env)->GetDirectBufferAddress(env, window_handle);
	jboolean was_resized = window_info->resized;
	window_info->resized = JNI_FALSE;
	return was_resized;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_nGetWidth(JNIEnv *env, jobject this, jobject window_handle) {
	MacOSXWindowInfo *window_info = (MacOSXWindowInfo *)(*env)->GetDirectBufferAddress(env, window_handle);
	jint width = window_info->display_rect.size.width;
	return width;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_nGetHeight(JNIEnv *env, jobject this, jobject window_handle) {
	MacOSXWindowInfo *window_info = (MacOSXWindowInfo *)(*env)->GetDirectBufferAddress(env, window_handle);
	jint height = window_info->display_rect.size.height;
	return height;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_nSetResizable(JNIEnv *env, jobject this, jobject window_handle, jboolean resizable) {
	MacOSXWindowInfo *window_info = (MacOSXWindowInfo *)(*env)->GetDirectBufferAddress(env, window_handle);
	NSUInteger style_mask = [window_info->window styleMask];
	if (resizable == true) {
		style_mask |= NSResizableWindowMask;
	} else {
		style_mask &= ~NSResizableWindowMask;
	}
	[window_info->window setStyleMask:style_mask];

	if (window_info->enableFullscreenModeAPI) {
		if (resizable) {
			// manually create OS X 10.7+ mask to allow compilation on previous OS X versions
			NSUInteger NSWindowCollectionBehaviorFullScreenPrimary = 1 << 7;
			[window_info->window setCollectionBehavior:NSWindowCollectionBehaviorFullScreenPrimary];
		}
		else {
			// manually create OS X 10.7+ mask to allow compilation on previous OS X versions
			NSUInteger NSFullScreenWindowMask = 1 << 14;
			// on disabling resizing exit fullscreen mode exit otherwise will be stuck in it
			if ((style_mask & NSFullScreenWindowMask) == NSFullScreenWindowMask) {
				// call method using runtime selector as its a 10.7+ api and allows compiling on older SDK's
				[window_info->window performSelector:NSSelectorFromString(@"toggleFullScreen:") withObject:nil];
			}
			[window_info->window setCollectionBehavior:NSWindowCollectionBehaviorDefault];
		}
	}
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_nGetX(JNIEnv *env, jobject this, jobject window_handle) {
	MacOSXWindowInfo *window_info = (MacOSXWindowInfo *)(*env)->GetDirectBufferAddress(env, window_handle);
	jint x = [[window_info->view window] frame].origin.x;
	return x;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_nGetY(JNIEnv *env, jobject this, jobject window_handle) {
	MacOSXWindowInfo *window_info = (MacOSXWindowInfo *)(*env)->GetDirectBufferAddress(env, window_handle);
	
	NSRect screenRect = [[window_info->window screen] frame];
	NSRect winRect = [[window_info->view window] frame];
	
	// get top corner of window frame, also flip coords so origin is in top left
	jint y = screenRect.size.height - (winRect.origin.y + winRect.size.height) - 1;
	return y;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_nSetTitle(JNIEnv *env, jobject this, jobject window_handle, jobject title_buffer) {
	MacOSXWindowInfo *window_info = (MacOSXWindowInfo *)(*env)->GetDirectBufferAddress(env, window_handle);
	const char *title_cstr = (const char *)(*env)->GetDirectBufferAddress(env, title_buffer);
	NSString *title = [[NSString alloc] initWithUTF8String:title_cstr];
	[window_info->window performSelectorOnMainThread:@selector(setTitle:) withObject:title waitUntilDone:NO];
}

JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_nCreateWindow(JNIEnv *env, jobject this, jint x, jint y, jint width, jint height, jboolean fullscreen, jboolean undecorated, jboolean resizable, jboolean parented, jboolean enableFullscreenModeAPI, jboolean enableHighDPI, jobject peer_info_handle, jobject window_handle) {
	
	pool = [[NSAutoreleasePool alloc] init];
	
	peer_info = (MacOSXPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	
	if (peer_info->isCALayer && !fullscreen) {
		MacOSXWindowInfo *window_info = (MacOSXWindowInfo *)(*env)->GetDirectBufferAddress(env, window_handle);
		window_info->fullscreen = fullscreen;
		window_info->undecorated = undecorated;
		window_info->parented = parented;
        window_info->enableFullscreenModeAPI = enableFullscreenModeAPI;
		
		return window_handle;
	}
	
	if (window_handle == NULL) {
		window_handle = newJavaManagedByteBuffer(env, sizeof(MacOSXWindowInfo));
		if (window_handle == NULL) {
			throwException(env, "Could not create handle buffer");
			return NULL;
		}
	}
	
	MacOSXWindowInfo *window_info = (MacOSXWindowInfo *)(*env)->GetDirectBufferAddress(env, window_handle);
	
	window_info->fullscreen = fullscreen;
	window_info->undecorated = undecorated;
	window_info->resizable = resizable;
	window_info->parented = parented;
	window_info->enableFullscreenModeAPI = enableFullscreenModeAPI;
	window_info->enableHighDPI = enableHighDPI;
	
	peer_info->window_info = window_info;
	peer_info->isWindowed = true;
	
    window_info->display_rect = NSMakeRect(x, [[NSScreen mainScreen] frame].size.height - y - height, width, height);
	
	// Cache the necessary info for window-close callbacks into the JVM
	if (window_info->jdisplay == NULL) {
		window_info->jdisplay = (*env)->NewGlobalRef(env, this);
	}
	
	// create window on main thread
	[MacOSXKeyableWindow performSelectorOnMainThread:@selector(createWindow) withObject:nil waitUntilDone:YES];
	
	return window_handle;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_nDestroyWindow(JNIEnv *env, jobject this, jobject window_handle) {
	
	// destroy window on main thread
	[MacOSXKeyableWindow performSelectorOnMainThread:@selector(destroyWindow) withObject:nil waitUntilDone:YES];
	
	[pool drain];
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_nDestroyCALayer(JNIEnv *env, jobject this, jobject peer_info_handle) {
	MacOSXPeerInfo *peer_info = (MacOSXPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	if (peer_info->isCALayer) {
		peer_info->isCALayer = false;
		[peer_info->glLayer performSelectorOnMainThread:@selector(removeLayer) withObject:nil waitUntilDone:YES];
		[peer_info->glLayer release];
        peer_info->glLayer = nil;
	}
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_nIsNativeMode(JNIEnv *env, jobject this, jobject peer_info_handle) {
	MacOSXPeerInfo *peer_info = (MacOSXPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	if (peer_info->isCALayer) {
		return JNI_FALSE;
	}
	else {
		return JNI_TRUE;
	}
}

JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_nGetCurrentDisplayMode(JNIEnv *env, jobject this) {
	
	jclass displayClass = (*env)->GetObjectClass(env, this);
	jmethodID createDisplayModeMethod = (*env)->GetMethodID(env, displayClass, "createDisplayMode", "(IIII)Ljava/lang/Object;");

    if (floor(NSAppKitVersionNumber) > NSAppKitVersionNumber10_5) { // if OS X 10.6+ use newer api
    
        CGDisplayModeRef mode = CGDisplayCopyDisplayMode(kCGDirectMainDisplay);
	
        int width = (int) CGDisplayModeGetWidth(mode);
        int height = (int) CGDisplayModeGetHeight(mode);
        int refreshRate = (int)CGDisplayModeGetRefreshRate(mode);
        int bitsPerPixel;
        
        // get bitsPerPixel
        CFStringRef pixelEncoding = CGDisplayModeCopyPixelEncoding(mode);
        
        if(CFStringCompare(pixelEncoding, CFSTR(IO16BitDirectPixels), kCFCompareCaseInsensitive) == kCFCompareEqualTo) {
            bitsPerPixel = 16;
        }
        else {
            bitsPerPixel = 32;
        }
        
        jobject displayMode = (*env)->CallObjectMethod(env, this, createDisplayModeMethod, width, height, bitsPerPixel, refreshRate);
        
        return displayMode;
    
    } else {
        
        CFDictionaryRef mode = CGDisplayCurrentMode(CGMainDisplayID());
        
        long bitsPerPixel = 0;
        long width = 0;
        long height = 0;
        long refreshRate = 0;
        
        CFNumberRef value;
        
        value = CFDictionaryGetValue(mode, kCGDisplayBitsPerPixel);
        CFNumberGetValue(value, kCFNumberLongType, &bitsPerPixel);
        
        value = CFDictionaryGetValue(mode, kCGDisplayWidth);
        CFNumberGetValue(value, kCFNumberLongType, &width);
        
        value = CFDictionaryGetValue(mode, kCGDisplayHeight);
        CFNumberGetValue(value, kCFNumberLongType, &height);
        
        value = CFDictionaryGetValue(mode, kCGDisplayRefreshRate);
        CFNumberGetValue(value, kCFNumberLongType, &refreshRate);
    
        jobject displayMode = (*env)->CallObjectMethod(env, this, createDisplayModeMethod, width, height, bitsPerPixel, refreshRate);
    
        return displayMode;
    }
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_nGetDisplayModes(JNIEnv *env, jobject this, jobject modesList) {
	
	jclass displayClass = (*env)->GetObjectClass(env, this);
	jmethodID addDisplayModeMethod = (*env)->GetMethodID(env, displayClass, "addDisplayMode", "(Ljava/lang/Object;IIII)V");
	   
    if (floor(NSAppKitVersionNumber) > NSAppKitVersionNumber10_5) { // if OS X 10.6+ use newer api
        
        CFArrayRef modes = CGDisplayCopyAllDisplayModes(kCGDirectMainDisplay, NULL);
        
        int i = 0;
	
        for (i = 0; i < CFArrayGetCount(modes); i++) {
            CGDisplayModeRef mode = (CGDisplayModeRef)CFArrayGetValueAtIndex(modes, i);
            
            int width = (int) CGDisplayModeGetWidth(mode);
            int height = (int) CGDisplayModeGetHeight(mode);
            int refreshRate = (int)CGDisplayModeGetRefreshRate(mode);
            int bitsPerPixel;
		
            // get bitsPerPixel
            CFStringRef pixelEncoding = CGDisplayModeCopyPixelEncoding(mode);
            if(CFStringCompare(pixelEncoding, CFSTR(IO32BitDirectPixels), kCFCompareCaseInsensitive) == kCFCompareEqualTo) {
                bitsPerPixel = 32;
            }
            else if(CFStringCompare(pixelEncoding, CFSTR(IO16BitDirectPixels), kCFCompareCaseInsensitive) == kCFCompareEqualTo) {
                bitsPerPixel = 16;
            }
            else {
                continue; // ignore DisplayMode of other bitsPerPixel rates
            }
		
            (*env)->CallVoidMethod(env, this, addDisplayModeMethod, modesList, width, height, bitsPerPixel, refreshRate);
        }

    } else {
        
        CFArrayRef modes = CGDisplayAvailableModes(CGMainDisplayID());
        CFIndex index, count;
        CFDictionaryRef mode;
        
        count = CFArrayGetCount(modes);
    
        for (index = 0; index < count; index++) {
            mode = CFArrayGetValueAtIndex(modes, index);
            
            long bitsPerPixel = 0;
            long width = 0;
            long height = 0;
            long refreshRate = 0;
            
            CFNumberRef value;
            
            value = CFDictionaryGetValue(mode, kCGDisplayBitsPerPixel);
            CFNumberGetValue(value, kCFNumberLongType, &bitsPerPixel);
            
            value = CFDictionaryGetValue(mode, kCGDisplayWidth);
            CFNumberGetValue(value, kCFNumberLongType, &width);
            
            value = CFDictionaryGetValue(mode, kCGDisplayHeight);
            CFNumberGetValue(value, kCFNumberLongType, &height);
            
            value = CFDictionaryGetValue(mode, kCGDisplayRefreshRate);
            CFNumberGetValue(value, kCFNumberLongType, &refreshRate);
            
            (*env)->CallVoidMethod(env, this, addDisplayModeMethod, modesList, width, height, bitsPerPixel, refreshRate);
        }
    }

}

JNIEXPORT jint JNICALL Java_org_lwjgl_DefaultSysImplementation_getJNIVersion(JNIEnv *env, jobject ignored) {
	return org_lwjgl_MacOSXSysImplementation_JNI_VERSION;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_restoreGamma(JNIEnv *env, jobject this) {
	CGDisplayRestoreColorSyncSettings();
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_setGammaRamp(JNIEnv *env, jobject this, jobject gamma_buffer) {
	const CGGammaValue *values = (*env)->GetDirectBufferAddress(env, gamma_buffer);
	uint32_t table_size = (*env)->GetDirectBufferCapacity(env, gamma_buffer);
	CGDisplayErr err = CGSetDisplayTransferByTable(kCGDirectMainDisplay, table_size, values, values, values);
	if (err != CGDisplayNoErr) {
		throwException(env, "Could not set display gamma");
	}
}