AL=/Users/oddlabs/cvs/openal



CC=gcc
LINKER=gcc
STRIP=strip
CFLAGS_LINK=-dynamiclib -Wall
FRAMEWORKS=-framework Foundation -framework AppKit -framework JavaVM
CFLAGS_O=-fPIC O2 -D_MACOSX -Wall -c -I${AL}/include -I../common -I/System/Library/Frameworks/JavaVM.framework/Versions/A/Headers
SRC=$(wildcard *.m) $(wildcard *.c) $(wildcard ../common/*.c) $(wildcard ../common/arb/*.c) $(wildcard ../common/ati/*.c) $(wildcard ../common/ext/*.c) $(wildcard ../common/nv/*.c)
OBJECTS=$(subst .m,.o, $(subst .c,.o,$(SRC)))
LIBRARY=liblwjgl.jnilib

$(LIBRARY): $(OBJECTS)
	$(LINKER) $(CFLAGS_LINK) -o $@ $(OBJECTS) $(FRAMEWORKS)
	$(STRIP) -S -X $@

.m.o:
	$(CC) $(CFLAGS_O) $< -o $@

.c.o:
	$(CC) $(CFLAGS_O) $< -o $@

clean:
	rm -f $(OBJECTS) $(LIBRARY)
