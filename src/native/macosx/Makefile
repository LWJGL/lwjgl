CC=gcc
LINKER=gcc
STRIP=strip
# Replace SDK_ROOT and ARCHS with the following to enable universal binary build
#SDK_ROOT=/Developer/SDKs/MacOSX10.4u.sdk
#ARCHS=-arch i386 -arch ppc
SDK_ROOT=/Developer/SDKs/MacOSX10.3.9.sdk
ARCHS=
#CFLAGS_LINK=-isysroot $(SDK_ROOT) -Wl,-syslibroot,$(SDK_ROOT) $(ARCHS) -exported_symbols_list lwjgl.symbols -dynamiclib -Wall
SYMBOL_FILE=lwjgl.symbols
CFLAGS_LINK=-exported_symbols_list lwjgl.symbols -dynamiclib -Wall
FRAMEWORKS=-framework Foundation -framework AppKit -framework JavaVM -framework Carbon
#CFLAGS_O=-isysroot $(SDK_ROOT) $(ARCHS) -fPIC -O2 -D_MACOSX -Wall -c -I${AL}/include -I../common -I$(SDK_ROOT)/System/Library/Frameworks/JavaVM.framework/Versions/A/Headers
CFLAGS_O=-fPIC -O2 -D_MACOSX -Wall -c -I${AL}/include -I../common -I$(SDK_ROOT)/System/Library/Frameworks/JavaVM.framework/Versions/A/Headers
SRC=$(wildcard *.m) $(wildcard *.c) $(wildcard ../common/*.c) $(wildcard ../generated/*.c)
OBJECTS=$(subst .m,.o, $(subst .c,.o,$(SRC)))
LIBRARY=liblwjgl.jnilib

$(LIBRARY): $(OBJECTS) $(SYMBOL_FILE)
	$(LINKER) $(CFLAGS_LINK) -o $@ $(OBJECTS) $(FRAMEWORKS)
	$(STRIP) -S -X $@

$(SYMBOL_FILE): $(OBJECTS)
	nm -g $(OBJECTS) | grep "Java_" | cut -d ' ' -f3 | cut -c 1- > $(SYMBOL_FILE)

.m.o:
	$(CC) $(CFLAGS_O) $< -o $@

.c.o:
	$(CC) $(CFLAGS_O) $< -o $@

clean:
	rm -f $(OBJECTS) $(LIBRARY) 
