#!/bin/bash

./autogen.sh
./configure
make
strip .libs/liblwjgl.0.0.0
