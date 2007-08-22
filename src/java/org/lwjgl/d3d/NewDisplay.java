package org.lwjgl.d3d;

import java.util.Arrays;
import java.util.HashSet;

import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class NewDisplay {
    private static final DisplayMode initialMode;
    private static DisplayMode currentMode;
    private static long hwnd;
    private static boolean created;
    private static boolean fullscreen;
    private static boolean modeSet;

    static {
        try {
            currentMode = initialMode = nGetCurrentDisplayMode();
            LWJGLUtil.log("Initial mode: " + initialMode);
        } catch (LWJGLException e) {
            throw new RuntimeException(e);
        }
    }
    
    private NewDisplay(){}
    public static DisplayMode[] getAvailableDisplayModes() throws LWJGLException {
        synchronized (GlobalLock.lock) {
            Object[] unfilteredModes = nGetAvailableDisplayModes();

            if (unfilteredModes == null) {
                return new DisplayMode[0];
            }

            // We'll use a HashSet to filter out the duplicated modes
            HashSet modes = new HashSet(unfilteredModes.length);

            modes.addAll(Arrays.asList(unfilteredModes));
            DisplayMode[] filteredModes = new DisplayMode[modes.size()];
            modes.toArray(filteredModes);

            LWJGLUtil.log("Removed " + (unfilteredModes.length - filteredModes.length) + " duplicate displaymodes");

            return filteredModes;
        }
    }
    
    public static void setDisplayMode(DisplayMode mode) throws LWJGLException {
        synchronized (GlobalLock.lock) {
            if (mode == null)
                throw new NullPointerException("mode must be non-null");
            currentMode = mode;
            if (isCreated()) {
                destroyWindow();
                // If mode is not fullscreen capable, make sure we are in windowed mode
                if (!mode.isFullscreen())
                    resetFullscreen();
                try {
                    if (fullscreen) {
                        switchDisplayMode();
                    }
                    createWindow();
//                    makeCurrentAndSetSwapInterval();
                } catch (LWJGLException e) {
//                    destroyContext();
//                    destroyPeerInfo();
                    resetDisplayMode();
                    throw e;
                }
            }
        }
    }
    
    public static void create() throws LWJGLException {
        //TODO
    }
    public static void destroy() {
        //TODO
    }
    
    public static boolean isCreated() {
        return created;
    }
    private static void destroyWindow() {
        if (created) {
//            if (Mouse.isCreated()) {
//                Mouse.destroy();
//            }
//            if (Keyboard.isCreated()) {
//                Keyboard.destroy();
//            }
            nDestroyWindow();
//            resetCursorClipping();
            created = false;
        }
    }
    private static void resetFullscreen() {
        synchronized (GlobalLock.lock) {
            if (NewDisplay.fullscreen) {
                NewDisplay.fullscreen = false;
                resetDisplayMode();
            }
        }
    }
    private static void switchDisplayMode() throws LWJGLException {
        if (!currentMode.isFullscreen()) {
            LWJGLUtil.log("Switching to " + initialMode);
            setDisplayMode(initialMode);
        }
        switchDisplayMode(initialMode);
    }
    public static void switchDisplayMode(DisplayMode mode) throws LWJGLException {
        nSwitchDisplayMode(mode);
        currentMode = mode;
        modeSet = true;
    }
    private static void createWindow() {
        //TODO
    }
    private static void resetDisplayMode() {
        if (modeSet) {
            modeSet = false;
            nResetDisplayMode();
        }
//        resetCursorClipping();
    }
    private static native void nDestroyWindow();
    private static native DisplayMode[] nGetAvailableDisplayModes();
    private static native void nSetDisplayMode(DisplayMode mode);
    private static native void nSwitchDisplayMode(DisplayMode mode) throws LWJGLException;
    private static native void nResetDisplayMode();
    private static native DisplayMode nGetCurrentDisplayMode() throws LWJGLException;
}
