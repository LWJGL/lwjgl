#!/bin/bash

./autogen.sh
./configure
make
strip .libs/liblwjgl.so.0.0.0
