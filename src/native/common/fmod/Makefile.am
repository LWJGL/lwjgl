lib_LTLIBRARIES = liblwjgl-fmod.la
liblwjgl_fmod_la_SOURCES = $(FMOD_SOURCE)
liblwjgl_fmod_la_LIBADD = $(DEP_LIBS)
liblwjgl_fmod_la_DEPENDENCIES = $(DEP_LIBS)
INCLUDES = -I..
DEP_LIBS = ../libtools.la

FMOD_SOURCE = extfmod.cpp \
              org_lwjgl_fmod_FMOD.cpp \
              org_lwjgl_fmod_FMusic.cpp \
              org_lwjgl_fmod_FSound.cpp
