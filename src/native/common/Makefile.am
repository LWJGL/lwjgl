SUBDIRS = callbacks

noinst_LTLIBRARIES = libcommon.la

libcommon_la_SOURCES = $(COMMON)
libcommon_la_CPPFLAGS = -D_DEBUG

libcommon_la_LIBADD = \
  callbacks/libcallbacks.la
libcommon_la_DEPENDENCIES = \
  callbacks/libcallbacks.la

COMMON = \
  extal.cpp \
  extal.h \
  extgl.c \
  extgl.h \
  org_lwjgl_Display.h \
  org_lwjgl_Sys.h \
  org_lwjgl_input_GamePad.h \
  org_lwjgl_input_Joystick.h \
  org_lwjgl_input_Keyboard.h \
  org_lwjgl_input_Mouse.h \
  org_lwjgl_Math.cpp \
  org_lwjgl_Math.h \
  org_lwjgl_openal_ALC.cpp \
  org_lwjgl_openal_ALC.h \
  org_lwjgl_openal_BaseAL.cpp \
  org_lwjgl_openal_BaseAL.h \
  org_lwjgl_openal_CoreAL.cpp \
  org_lwjgl_openal_CoreAL.h \
  org_lwjgl_openal_eax_BaseEAX.cpp \
  org_lwjgl_openal_eax_BaseEAX.h \
  org_lwjgl_openal_eax_CoreEAX.cpp \
  org_lwjgl_openal_eax_CoreEAX.h \
  org_lwjgl_openal_eax_EAXBufferProperties.cpp \
  org_lwjgl_openal_eax_EAXBufferProperties.h \
  org_lwjgl_openal_eax_EAXListenerProperties.cpp \
  org_lwjgl_openal_eax_EAXListenerProperties.h \
  org_lwjgl_opengl_CoreGL11.cpp \
  org_lwjgl_opengl_CoreGL11.h \
  org_lwjgl_opengl_CoreGL12.cpp \
  org_lwjgl_opengl_CoreGL12.h \
  org_lwjgl_opengl_CoreGL13.cpp \
  org_lwjgl_opengl_CoreGL13.h \
  org_lwjgl_opengl_CoreGL14.cpp \
  org_lwjgl_opengl_CoreGL15.h \
  org_lwjgl_opengl_GL.cpp \
  org_lwjgl_opengl_GL.h \
  org_lwjgl_opengl_GLU.cpp \
  org_lwjgl_opengl_GLU.h
  
