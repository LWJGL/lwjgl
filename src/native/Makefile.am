SUBDIRS = common @native_build_dir@

lib_LTLIBRARIES = liblwjgl.la
liblwjgl_la_SOURCES = dummy.c
liblwjgl_la_LIBADD = $(DEP_LIBS) @native_build_dir@/libnative.la
liblwjgl_la_DEPENDENCIES = $(DEP_LIBS) @native_build_dir@/libnative.la

DEP_LIBS = common/libcommon.la
