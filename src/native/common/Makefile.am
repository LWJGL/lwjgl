noinst_LTLIBRARIES = libcommon.la

SUBDIRS = arb ati ext nv

libcommon_la_SOURCES = $(COMMON)
libcommon_la_LIBADD = $(DEP_LIBS)
libcommon_la_DEPENDENCIES = $(DEP_LIBS)
DEP_LIBS=arb/libarb.la ati/libati.la ext/libext.la nv/libnv.la

COMMON = \
  extal.cpp \
  extal.h \
  extgl.cpp \
  extgl.h \
  common_tools.cpp \
  common_tools.h \
  org_lwjgl_Display.h \
  org_lwjgl_Sys.h \
  org_lwjgl_input_GamePad.h \
  org_lwjgl_input_Joystick.h \
  org_lwjgl_input_Keyboard.h \
  org_lwjgl_input_Mouse.h \
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
  org_lwjgl_opengl_GL11.cpp \
  org_lwjgl_opengl_GL11.h \
  org_lwjgl_opengl_GL12.cpp \
  org_lwjgl_opengl_GL12.h \
  org_lwjgl_opengl_GL13.cpp \
  org_lwjgl_opengl_GL13.h \
  org_lwjgl_opengl_GL14.cpp \
  org_lwjgl_opengl_GLU.cpp \
  org_lwjgl_opengl_GLU.h
  
