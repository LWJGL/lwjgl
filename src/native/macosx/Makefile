AL=/Users/oddlabs/cvs/openal



CC=gcc
LINKER=gcc
STRIP=strip
CFLAGS_LINK=-dynamiclib -Wall
#FRAMEWORKS=-framework OpenGL -framework AppKit -framework JavaVM
FRAMEWORKS=-framework Foundation -framework AppKit -framework JavaVM
CFLAGS_O=-O2 -D_MACOSX -Wall -c -I${AL}/include -I../common -I/System/Library/Frameworks/JavaVM.framework/Versions/A/Headers
#SRC:=$(shell find . -name \*.c -print) $(shell find ../common -name \*.c)
SRC=$(wildcard *.m) $(wildcard *.c) $(wildcard ../common/*.c) $(wildcard ../common/arb/*.c) $(wildcard ../common/ati/*.c) $(wildcard ../common/ext/*.c) $(wildcard ../common/nv/*.c)
OBJECTS=$(subst .m,.o, $(subst .c,.o,$(SRC)))
#OBJECTS=org_lwjgl_opengl_Display.o \
#		org_lwjgl_Sys.o \
#		../common/extgl.o \
#		../common/extal.o \
#		../common/org_lwjgl_openal_AL.o \
#		../common/org_lwjgl_openal_ALC.o \
#		../common/org_lwjgl_openal_AL10.o \
#		../common/common_tools.o
LIBRARY=liblwjgl.jnilib

$(LIBRARY): $(OBJECTS)
	$(LINKER) $(CFLAGS_LINK) -o $@ $(OBJECTS) $(FRAMEWORKS)
#	$(STRIP) -S -X $@

.m.o:
	$(CC) $(CFLAGS_O) $< -o $@

.c.o:
	$(CC) $(CFLAGS_O) $< -o $@

clean:
	rm -f ${OBJECTS} ${LIBRARY}
