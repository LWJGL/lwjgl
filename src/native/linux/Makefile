
# Copyright (c) 2002 Light Weight Java Game Library Project
# All rights reserved.
#
# Redistribution and use in source and binary forms, with or without
# modification, are permitted provided that the following conditions are
# met:
#
# * Redistributions of source code must retain the above copyright
#   notice, this list of conditions and the following disclaimer.
#
# * Redistributions in binary form must reproduce the above copyright
#   notice, this list of conditions and the following disclaimer in the
#   documentation and/or other materials provided with the distribution.
#
# * Neither the name of 'Light Weight Java Game Library' nor the names of
#   its contributors may be used to endorse or promote products derived
#   from this software without specific prior written permission.
#
# THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
# "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
# TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
# PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
# CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
# EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
# PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
# PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
# LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
# NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
# SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
#

#
# $Id$
#
# Linux specific Makefile to compile liblwjgl.so.
#
# author elias_naur <elias_naur@users.sourceforge.net>
# version $Revision$
#

GCC = g++
LINK = g++
JAVAHOME=/usr/java/j2sdk1.4.1_01
LIBCSRC=../common/extgl.c
LIBCPPSRC=$(shell find . -name \*.cpp -print|grep -v CVS)
LIBCPPOBJS=$(LIBCPPSRC:.cpp=.o)
LIBCOBJS=$(LIBCSRC:.c=.o)
LIBOBJS=$(LIBCOBJS) $(LIBCPPOBJS)
#DEBUG_FLAGS=-D_DEBUG
LINKOPTS=-L/usr/X11R6/lib -lX11 -lXext -lXxf86vm -lGL -lGLU -lopenal

all: liblwjgl.so


liblwjgl_d.so: $(LIBOBJS)
	$(LINK) -shared -o $@ $^ $(LINKOPTS)
	cp $@ ../../../bin/

liblwjgl.so: $(LIBOBJS)
	$(LINK) -shared -o $@ $^ $(LINKOPTS)
	cp $@ ../../../bin/

.SUFFIXES:      .cpp .so .o .java .h .class

%.o : %.cpp
	$(GCC) $(DEBUG_FLAGS) -Wall -I$(JAVAHOME)/include -I../common -I$(JAVAHOME)/include/linux -c -o $@ $< 

testprog : main.o
	$(LINK) -o $@ $< -L/usr/X11R6/lib -lX11 -lXext -lXxf86vm -lGL -lGLU

.PHONY clean:
	rm -rf *.o *.so
	rm -rf `find ../../ -name \*.class -print`
