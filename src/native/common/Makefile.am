noinst_LTLIBRARIES = libcommon.la libtools.la

SUBDIRS = arb ati ext nv

libcommon_la_SOURCES = $(COMMON)
libcommon_la_LIBADD = $(DEP_LIBS)
libcommon_la_DEPENDENCIES = $(DEP_LIBS)
libtools_la_SOURCES = $(TOOLS)
DEP_LIBS=libtools.la arb/libarb.la ati/libati.la ext/libext.la nv/libnv.la

TOOLS = \
  common_tools.c \
  common_tools.h

COMMON = \
  extal.c \
  extgl.c \
  org_lwjgl_openal_ALC.c \
  org_lwjgl_openal_AL.c \
  org_lwjgl_openal_AL10.c \
  org_lwjgl_openal_eax_EAX.c \
  org_lwjgl_openal_eax_EAX20.c \
  org_lwjgl_openal_eax_EAXBufferProperties.c \
  org_lwjgl_openal_eax_EAXListenerProperties.c \
  org_lwjgl_opengl_GLContext.c \
  org_lwjgl_opengl_GL11.c \
  org_lwjgl_opengl_GL12.c \
  org_lwjgl_opengl_GL13.c \
  org_lwjgl_opengl_GL14.c \
  org_lwjgl_opengl_GL15.c \
  org_lwjgl_opengl_GL20.c
