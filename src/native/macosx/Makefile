CC=gcc
LINKER=gcc
STRIP=strip
# Replace SDK_ROOT and ARCHS with the following to enable universal binary build
#SDK_ROOT=/Developer/SDKs/MacOSX10.4u.sdk
#ARCHS=-arch i386 -arch ppc
SDK_ROOT=/
ARCHS=
CFLAGS_LINK=-Wl,-syslibroot,$(SDK_ROOT) $(ARCHS) -dynamiclib -Wall
FRAMEWORKS=-framework Foundation -framework AppKit -framework JavaVM -framework Carbon
CFLAGS_O=-isysroot $(SDK_ROOT) $(ARCHS) -fPIC -O2 -D_MACOSX -Wall -c -I${AL}/include -I../common -I$(SDK_ROOT)/System/Library/Frameworks/JavaVM.framework/Versions/A/Headers
SRC=$(wildcard *.m) $(wildcard *.c) $(wildcard ../common/*.c) $(wildcard ../generated/*.c)
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
