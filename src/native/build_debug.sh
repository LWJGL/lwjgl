#!/bin/bash

make distclean
./autogen.sh
./configure --enable-debug
make
strip .libs/liblwjgl.0
