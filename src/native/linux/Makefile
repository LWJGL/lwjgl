GCC = gcc
LINK = gcc
JAVAHOME=/usr/java/j2sdk1.4.1_01
JAVAC=$(JAVAHOME)/bin/javac
JAVAH=$(JAVAHOME)/bin/javah
CP=../../java
JAVAFILES=$(shell find ../../java -name \*.java -print|grep -v CVS)
CLASSFILES=$(JAVAFILES:.java=.class)

all: liblwjgl.so

liblwjgl.so: org_lwjgl_Display.o org_lwjgl_Sys.o org_lwjgl_opengl_BaseGL.o org_lwjgl_opengl_GL.o org_lwjgl_opengl_CoreGL.o org_lwjgl_input_Keyboard.o org_lwjgl_opengl_GLU.o org_lwjgl_input_Mouse.o
	$(LINK) -shared -o $@ $^ -L/usr/X11R6/lib -lX11 -lXext -lXxf86vm -lGL -lGLU
	cp $@ ../../../bin/

.SUFFIXES:      .cpp .so .o .java .h .class

%.class: %.java
	$(JAVAC) -deprecation -O -source 1.4 -classpath $(CP) $<

%.o : %.c
	$(GCC) -Wall -I$(JAVAHOME)/include -I../common -I$(JAVAHOME)/include/linux -c -o $@ $< 

testprog : main.o
	$(LINK) -o $@ $< -L/usr/X11R6/lib -lX11 -lXext -lXxf86vm -lGL -lGLU

.PHONY clean:
	rm -rf *.o *.so
	rm -rf `find ../../ -name \*.class -print`
