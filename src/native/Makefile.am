SUBDIRS = common @native_build_dir@

lib_LTLIBRARIES = liblwjgl.la
liblwjgl_la_LDFLAGS = -framework Carbon
liblwjgl_la_SOURCES = dummy.cpp
liblwjgl_la_LIBADD = common/libcommon.la @native_build_dir@/libnative.la
liblwjgl_la_DEPENDENCIES = common/libcommon.la @native_build_dir@/libnative.la
