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

#define WAIT_DELAY 100

static NSOpenGLPixelFormat *default_format = nil;

static NSAutoreleasePool *pool;

@implementation MacOSXKeyableWindow
- (BOOL)canBecomeKeyWindow;
{
    return YES;
}
@end

@implementation MacOSXOpenGLView

+ (NSOpenGLPixelFormat*)defaultPixelFormat
{
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

- (void) windowWillClose:(NSNotification *)notification
{
    MacOSXKeyableWindow *closingWindow = [notification object];
    
    if (_parent != nil && closingWindow == _parent->window) {
        JNIEnv *env = attachCurrentThread();
        jclass display_class = (*env)->GetObjectClass(env, _parent->jdisplay);
        jmethodID close_callback = (*env)->GetMethodID(env, display_class, "doHandleQuit", "()V");
        (*env)->CallVoidMethod(env, _parent->jdisplay, close_callback);
    }
}

- (id)initWithFrame:(NSRect)frameRect pixelFormat:(NSOpenGLPixelFormat*)format
{
    self = [super initWithFrame:frameRect];
    _lastModifierFlags = 0;
    _modifierFlags = 0;
    if (self != nil) {
        _pixelFormat = [format retain];
        [[NSNotificationCenter defaultCenter] addObserver:self
                                                 selector:@selector(_surfaceNeedsUpdate:)
                                                     name:NSViewGlobalFrameDidChangeNotification
                                                   object:self];
    }
    return self;
}

- (void) _surfaceNeedsUpdate:(NSNotification*)notification
{
    [self update];
}

- (void)setOpenGLContext:(NSOpenGLContext*)context
{
    _openGLContext = context;
}

- (NSOpenGLContext*)openGLContext
{
    return _openGLContext;
}

- (void)clearGLContext
{
    [_openGLContext release];
    _openGLContext = nil;
}

- (void)prepareOpenGL
{
    
}

- (void)update
{
    [_openGLContext update];
}

- (void)lockFocus
{
    NSOpenGLContext* context = [self openGLContext];
    
    [super lockFocus];
    if ([context view] != self) {
        [context setView:self];
    }
    
    [context makeCurrentContext];
}

- (void)setPixelFormat:(NSOpenGLPixelFormat*)pixelFormat
{
    _pixelFormat = [pixelFormat retain];
}

- (NSOpenGLPixelFormat*)pixelFormat
{
    return _pixelFormat;
}

- (BOOL)acceptsFirstResponder {
    return YES;
}

- (void)setParent:(MacOSXWindowInfo*)parent {
    // Un-register for native window close events if we have a parent window already
    if (_parent != nil) {
        [[NSNotificationCenter defaultCenter] removeObserver:self
                                                 name:NSWindowWillCloseNotification
                                                   object:_parent->window];
    }
    _parent = parent;
    // Register for native window close events if we now have a parent window
    if (_parent != nil) {
        [[NSNotificationCenter defaultCenter] addObserver:self
                                                 selector:@selector(windowWillClose:) name:NSWindowWillCloseNotification
                                                   object:_parent->window];
    }
}

- (void)keyDown:(NSEvent *)event {
    JNIEnv *env = attachCurrentThread();
    if (env == nil || event == nil || _parent == nil || _parent->jkeyboard == nil) {
        return;
    }
    long time = [event timestamp] * 1000000000;
    jclass keyboard_class = (*env)->GetObjectClass(env, _parent->jkeyboard);
    jmethodID keydown = (*env)->GetMethodID(env, keyboard_class, "keyPressed", "(IIJ)V");
    const char* charbuf = [[event characters] cStringUsingEncoding:NSASCIIStringEncoding];
    int charcode = (charbuf == nil) ? 0 : charbuf[0];
    (*env)->CallVoidMethod(env, _parent->jkeyboard, keydown, [event keyCode], charcode, time);
}

- (void)keyUp:(NSEvent *)event {
    JNIEnv *env = attachCurrentThread();
    if (env == nil || event == nil || _parent == nil || _parent->jkeyboard == nil) {
        return;
    }
    long time = [event timestamp] * 1000000000;
    jclass keyboard_class = (*env)->GetObjectClass(env, _parent->jkeyboard);
    jmethodID keyup = (*env)->GetMethodID(env, keyboard_class, "keyReleased", "(IIJ)V");
    const char* charbuf = [[event characters] cStringUsingEncoding:NSASCIIStringEncoding];
    int charcode = (charbuf == nil) ? 0 : charbuf[0];
    (*env)->CallVoidMethod(env, _parent->jkeyboard, keyup, [event keyCode], charcode, time);
}

- (void)flagsChanged:(NSEvent *)event {
    JNIEnv *env = attachCurrentThread();
    if (env == nil || event == nil || _parent == nil || _parent->jkeyboard == nil) {
        return;
    }
    long time = [event timestamp] * 1000000000;
    jclass keyboard_class = (*env)->GetObjectClass(env, _parent->jkeyboard);
    jmethodID keydown = (*env)->GetMethodID(env, keyboard_class, "keyPressed", "(IIJ)V");
    jmethodID keyup = (*env)->GetMethodID(env, keyboard_class, "keyReleased", "(IIJ)V");
    _lastModifierFlags = _modifierFlags;
    _modifierFlags = [event modifierFlags];
    NSUInteger flagDown = ~_lastModifierFlags & _modifierFlags;
    NSUInteger flagUp = _lastModifierFlags & ~_modifierFlags;
    if (flagDown & NSAlphaShiftKeyMask) {
        (*env)->CallVoidMethod(env, _parent->jkeyboard, keydown, 0xf0, 0, time);
    }
    if (flagUp & NSAlphaShiftKeyMask) {
        (*env)->CallVoidMethod(env, _parent->jkeyboard, keyup, 0xf0, 0, time);
    }
    if (flagDown & NSShiftKeyMask) {
        (*env)->CallVoidMethod(env, _parent->jkeyboard, keydown, 0xf1, 0, time);
    }
    if (flagUp & NSShiftKeyMask) {
        (*env)->CallVoidMethod(env, _parent->jkeyboard, keyup, 0xf1, 0, time);
    }
    if (flagDown & NSControlKeyMask) {
        (*env)->CallVoidMethod(env, _parent->jkeyboard, keydown, 0xf2, 0, time);
    }
    if (flagUp & NSControlKeyMask) {
        (*env)->CallVoidMethod(env, _parent->jkeyboard, keyup, 0xf2, 0, time);
    }
    if (flagDown & NSAlternateKeyMask) {
        (*env)->CallVoidMethod(env, _parent->jkeyboard, keydown, 0xf3, 0, time);
    }
    if (flagUp & NSAlternateKeyMask) {
        (*env)->CallVoidMethod(env, _parent->jkeyboard, keyup, 0xf3, 0, time);
    }
    if (flagDown & NSCommandKeyMask) {
        (*env)->CallVoidMethod(env, _parent->jkeyboard, keydown, 0xf4, 0, time);
    }
    if (flagUp & NSCommandKeyMask) {
        (*env)->CallVoidMethod(env, _parent->jkeyboard, keyup, 0xf4, 0, time);
    }
    if (flagDown & NSNumericPadKeyMask) {
        (*env)->CallVoidMethod(env, _parent->jkeyboard, keydown, 0xf5, 0, time);
    }
    if (flagUp & NSNumericPadKeyMask) {
        (*env)->CallVoidMethod(env, _parent->jkeyboard, keyup, 0xf5, 0, time);
    }
    //const char* charbuf = [[event characters] cStringUsingEncoding:NSASCIIStringEncoding];
    //(*env)->CallVoidMethod(env, _parent->jkeyboard, keymod, (jint)[event keyCode], (jint)charbuf[0], time);
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
    [self mouseButtonState:event :0 :1];
}

- (void)rightMouseDown:(NSEvent *)event {
    [self mouseButtonState:event :1 :1];
}

- (void)otherMouseDown:(NSEvent *)event {
    [self mouseButtonState:event :2 :1];
}

- (void)mouseUp:(NSEvent *)event {
    [self mouseButtonState:event :0 :0];
}

- (void)rightMouseUp:(NSEvent *)event {
    [self mouseButtonState:event :1 :0];
}

- (void)otherMouseUp:(NSEvent *)event {
    [self mouseButtonState:event :2 :0];
}

- (void)mouseDragged:(NSEvent *)event {
    JNIEnv *env = attachCurrentThread();
    if (env == nil || event == nil || _parent == nil) {
        return;
    }
    long time = [event timestamp] * 1000000000;
    jclass mouse_class = (*env)->GetObjectClass(env, _parent->jmouse);
    jmethodID mousemove = (*env)->GetMethodID(env, mouse_class, "mouseMoved", "(FFFFFJ)V");
    NSPoint loc = [self convertPoint:[event locationInWindow] toView:self];
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
    NSPoint loc = [self convertPoint:[event locationInWindow] toView:self];
    (*env)->CallVoidMethod(env, _parent->jmouse, mousemove, loc.x, loc.y, [event deltaX], [event deltaY], 0.0f, time);
}

- (void)scrollWheel:(NSEvent *)event {
    JNIEnv *env = attachCurrentThread();
    if (env == nil || event == nil || _parent == nil) {
        return;
    }
    long time = [event timestamp] * 1000000000;
    //float dz = [event scrollingDeltaY]; // An OS X 10.7+ API
    //if (![event hasPreciseScrollingDeltas]) { // Also an OS X 10.7 API
	//    dz *= 12; // or so
    //}
    //jclass mouse_class = (*env)->GetObjectClass(env, _parent->jmouse);
    //jmethodID mousemove = (*env)->GetMethodID(env, mouse_class, "mouseMoved", "(FFFFFJ)V");
    //NSPoint loc = [self convertPoint:[event locationInWindow] toView:self];
    //(*env)->CallVoidMethod(env, _parent->jmouse, mousemove, loc.x, loc.y, [event deltaX], [event deltaY], dz, time);
}

- (void)viewDidMoveToWindow
{
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(windowResized:)
                                                 name:NSWindowDidResizeNotification
                                               object:[self window]];
}

- (void)dealloc
{
    [[NSNotificationCenter defaultCenter] removeObserver:self];
    [super dealloc];
}

- (void)windowResized:(NSNotification *)notification;
{
    if (_parent != nil) {
        _parent->display_rect = [[self window] frame];
        _parent->resized = JNI_TRUE;
    }
}
@end

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_nIsMiniaturized(JNIEnv *env, jobject this, jobject window_handle) {
	MacOSXWindowInfo *window_info = (MacOSXWindowInfo *)(*env)->GetDirectBufferAddress(env, window_handle);
    return (jboolean)[window_info->window isMiniaturized];
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_nIsFocused(JNIEnv *env, jobject this, jobject window_handle) {
    return JNI_TRUE;
	//MacOSXWindowInfo *window_info = (MacOSXWindowInfo *)(*env)->GetDirectBufferAddress(env, window_handle);
    //return (jboolean)([window_info->window isKeyWindow] || [window_info->window isMainWindow]);
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

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_nSetResizable(JNIEnv *env, jobject this, jobject window_handle, jboolean resizable) {
	MacOSXWindowInfo *window_info = (MacOSXWindowInfo *)(*env)->GetDirectBufferAddress(env, window_handle);
    NSUInteger style_mask = [window_info->window styleMask];
    if (resizable == true) {
        style_mask |= NSResizableWindowMask;
    } else {
        style_mask &= ~NSResizableWindowMask;
    }
    [window_info->window setStyleMask:style_mask];
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_nSetTitle(JNIEnv *env, jobject this, jobject window_handle, jobject title_buffer) {
	MacOSXWindowInfo *window_info = (MacOSXWindowInfo *)(*env)->GetDirectBufferAddress(env, window_handle);
	const char *title_cstr = (const char *)(*env)->GetDirectBufferAddress(env, title_buffer);
    NSString *title = [[NSString alloc] initWithUTF8String:title_cstr];
    [window_info->window setTitle:title];
}

JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_nCreateWindow(JNIEnv *env, jobject this, jint x, jint y, jint width, jint height, jboolean fullscreen, jboolean undecorated, jobject peer_info_handle, jobject window_handle) {
    
    if (window_handle == NULL) {
        window_handle = newJavaManagedByteBuffer(env, sizeof(MacOSXWindowInfo));
        if (window_handle == NULL) {
            throwException(env, "Could not create handle buffer");
            return NULL;
        }
    }
	
	pool = [[NSAutoreleasePool alloc] init];

	MacOSXWindowInfo *window_info = (MacOSXWindowInfo *)(*env)->GetDirectBufferAddress(env, window_handle);
    MacOSXPeerInfo *peer_info = (MacOSXPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
    
    // Cache the necessary info for window-close callbacks into the JVM
    if (window_info->jdisplay == NULL) {
        window_info->jdisplay = (*env)->NewGlobalRef(env, this);
    }
    
    window_info->display_rect = NSMakeRect(x, y, width, height);
    int default_window_mask = NSBorderlessWindowMask;
    if (!undecorated && !fullscreen) {
        printf("Resizeable\n"); fflush(stdout);
        default_window_mask = NSTitledWindowMask | NSClosableWindowMask | NSMiniaturizableWindowMask | NSResizableWindowMask;
    }
    window_info->window = (MacOSXKeyableWindow*)[[NSApplication sharedApplication] mainWindow];
    if (window_info->window == nil) {
        window_info->window = [[MacOSXKeyableWindow alloc] initWithContentRect:window_info->display_rect styleMask:default_window_mask backing:NSBackingStoreBuffered defer:YES];
    }
    
    NSRect view_rect = NSMakeRect(0.0, 0.0, width, height);
    window_info->view = [[MacOSXOpenGLView alloc] initWithFrame:view_rect pixelFormat:peer_info->pixel_format];
    if (window_info->context != nil) {
        printf("Setting context\n"); fflush(stdout);
        [window_info->view setOpenGLContext:window_info->context];
    }
    
    // Inform the view of its parent window info; used to register for window-close callbacks
    [window_info->view setParent:window_info];
    
    [window_info->window setContentView:window_info->view];
    [window_info->window makeKeyAndOrderFront:[NSApplication sharedApplication]];
    [window_info->window makeFirstResponder:window_info->view];
    [window_info->window setReleasedWhenClosed:YES];
    [window_info->window setInitialFirstResponder:window_info->view];

    if (window_info->window_options != NSApplicationPresentationDefault) {
        printf("Non-default\n"); fflush(stdout);
        [[NSApplication sharedApplication] setPresentationOptions:window_info->window_options];
    }
    
    peer_info->window_info = window_info;

    return window_handle;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_nDestroyWindow(JNIEnv *env, jobject this, jobject window_handle) {
	MacOSXWindowInfo *window_info = (MacOSXWindowInfo *)(*env)->GetDirectBufferAddress(env, window_handle);
    
    if (window_info->window != nil) {
        [window_info->window close];
    }
    window_info->window = nil;
    
    if (window_info->view != nil) {
        [window_info->view release];
    }
    window_info->view = nil;
    //[window_info->window release];
	[pool drain];
}

JNIEXPORT jint JNICALL Java_org_lwjgl_DefaultSysImplementation_getJNIVersion
  (JNIEnv *env, jobject ignored) {
	return org_lwjgl_MacOSXSysImplementation_JNI_VERSION;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_restoreGamma(JNIEnv *env, jobject this) {
	CGDisplayRestoreColorSyncSettings();
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_setGammaRamp(JNIEnv *env, jobject this, jobject gamma_buffer) {
	const CGGammaValue *values = (*env)->GetDirectBufferAddress(env, gamma_buffer);
	CGTableCount table_size = (*env)->GetDirectBufferCapacity(env, gamma_buffer);
	CGDisplayErr err = CGSetDisplayTransferByTable(kCGDirectMainDisplay, table_size, values, values, values);
	if (err != CGDisplayNoErr) {
		throwException(env, "Could not set display gamma");
	}
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_nHideUI(JNIEnv *env, jobject this, jobject window_handle, jboolean hide) {
    if (window_handle == NULL) {
        printf("Window handle is null\n");
        return;
    }
    
	MacOSXWindowInfo *window_info = (MacOSXWindowInfo *)(*env)->GetDirectBufferAddress(env, window_handle);
    //if(floor(NSAppKitVersionNumber) > NSAppKitVersionNumber10_5) {
        /*NSApplicationPresentationOptions options = NSApplicationPresentationDefault;
        if (hide == JNI_TRUE) {
            options = NSApplicationPresentationFullScreen; // this requires OS X 10.7+ to compile
            options |= NSApplicationPresentationHideDock;
            options |= NSApplicationPresentationHideMenuBar;
        }
        printf("Setting options\n");
        window_info->window_options = options;
        if (window_info->window != nil) {
            [[NSApplication sharedApplication] setPresentationOptions:options];
        }*/
    //} else {
        if (hide == JNI_TRUE) {
            SetSystemUIMode(kUIModeContentSuppressed, 0);
        } else {
            SetSystemUIMode(kUIModeNormal, 0);
        }
    //}
}
