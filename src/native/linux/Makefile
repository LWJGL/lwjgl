

CC=gcc
LINKER=gcc
STRIP=strip
CFLAGS_LINK=-Wl,--version-script=lwjgl.map -O2 -shared -Wall
LIBS=-L/usr/X11R6/lib -L/usr/X11/lib -lm -lX11 -lXext -lpthread -L$(JAVA_HOME)/jre/lib/i386 -ljawt -Wl,-static,-lXxf86vm,-lXcursor,-lXrender,-lXrandr,-call_shared
CFLAGS_O=-fPIC -O2 -D_X11 -Wall -std=c99 -pthread -c -I../common -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux -I$(AL_HOME)/include
SRC=$(wildcard *.c) $(wildcard ../common/*.c) $(wildcard ../generated/*c)
OBJECTS=$(subst .c,.o,$(SRC))
LIBRARY=liblwjgl.so

$(LIBRARY): $(OBJECTS)
	$(LINKER) $(CFLAGS_LINK) -o $@ $(OBJECTS) $(LIBS)
	$(STRIP) $@

.c.o:
	$(CC) $(CFLAGS_O) $< -o $@

clean:
	rm -f $(OBJECTS) $(LIBRARY)
