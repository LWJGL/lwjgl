noinst_LTLIBRARIES = libcommon.la libtools.la

SUBDIRS = arb ati ext nv

libcommon_la_SOURCES = $(COMMON)
libcommon_la_LIBADD = $(DEP_LIBS)
libcommon_la_DEPENDENCIES = $(DEP_LIBS)
libtools_la_SOURCES = $(TOOLS)
DEP_LIBS=libtools.la arb/libarb.la ati/libati.la ext/libext.la nv/libnv.la

TOOLS = \
  common_tools.cpp \
  common_tools.h

COMMON = \
  extal.cpp \
  extal.h \
  extgl.cpp \
  extgl.h \
  org_lwjgl_Display.h \
  org_lwjgl_Sys.h \
  org_lwjgl_input_GamePad.h \
  org_lwjgl_input_Joystick.h \
  org_lwjgl_input_Keyboard.h \
  org_lwjgl_input_Mouse.h \
  org_lwjgl_openal_ALC.cpp \
  org_lwjgl_openal_ALC.h \
  org_lwjgl_openal_AL.cpp \
  org_lwjgl_openal_AL.h \
  org_lwjgl_openal_AL10.cpp \
  org_lwjgl_openal_AL10.h \
  org_lwjgl_openal_eax_EAX.cpp \
  org_lwjgl_openal_eax_EAX.h \
  org_lwjgl_openal_eax_EAX20.cpp \
  org_lwjgl_openal_eax_EAX.h \
  org_lwjgl_openal_eax_EAXBufferProperties.cpp \
  org_lwjgl_openal_eax_EAXBufferProperties.h \
  org_lwjgl_openal_eax_EAXListenerProperties.cpp \
  org_lwjgl_openal_eax_EAXListenerProperties.h \
  org_lwjgl_opengl_GLContext.cpp \
  org_lwjgl_opengl_GL11.cpp \
  org_lwjgl_opengl_GL12.cpp \
  org_lwjgl_opengl_GL13.cpp \
  org_lwjgl_opengl_GL14.cpp \
  org_lwjgl_opengl_GL15.cpp \
  org_lwjgl_opengl_GL20.cpp
