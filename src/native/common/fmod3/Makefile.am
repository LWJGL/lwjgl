lib_LTLIBRARIES = liblwjgl-fmod3.la
liblwjgl_fmod_la_SOURCES = $(FMOD_SOURCE)
liblwjgl_fmod_la_LIBADD = $(DEP_LIBS)
liblwjgl_fmod_la_DEPENDENCIES = $(DEP_LIBS)
INCLUDES = -I..
DEP_LIBS = ../libtools.la

FMOD_SOURCE = extfmod3.cpp \
              org_lwjgl_fmod3_FMOD.cpp \
              org_lwjgl_fmod3_FMusic.cpp \
              org_lwjgl_fmod3_FSound.cpp
