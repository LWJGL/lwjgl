

CC=gcc
LINKER=gcc
STRIP=strip
CFLAGS_LINK=-shared -Wall
LIBS=-L/usr/X11/lib -lX11 -lXext -lXxf86vm -lpthread -L/usr/lib/jvm/java-1.5.0-sun-1.5.0.01/jre/lib/i386/ -ljawt -Wl,-static,-lXcursor,-lXrender,-lXrandr,-call_shared
CFLAGS_O=-fPIC -O2 -D_X11 -Wall -pthread -c -I../common -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux
SRC=$(wildcard *.c) $(wildcard ../common/*.c) $(wildcard ../common/arb/*.c) $(wildcard ../common/ati/*.c) $(wildcard ../common/ext/*.c) $(wildcard ../common/nv/*.c)
OBJECTS=$(subst .c,.o,$(SRC))
LIBRARY=liblwjgl.so

$(LIBRARY): $(OBJECTS)
	$(LINKER) $(CFLAGS_LINK) -o $@ $(OBJECTS) $(LIBS)
	$(STRIP) $@

.c.o:
	$(CC) $(CFLAGS_O) $< -o $@

clean:
	rm -f $(OBJECTS) $(LIBRARY)
