SUBDIRS = common linux

lib_LTLIBRARIES = liblwjgl.la

#liblwjgl_la_LDFLAGS = \
#        -release $(LT_RELEASE)  \
#        -version-info $(LT_CURRENT):$(LT_REVISION):$(LT_AGE)
liblwjgl_la_SOURCES = \
  dummy.cpp
liblwjgl_la_CPPFLAGS = -D_DEBUG
liblwjgl_la_LIBADD = \
  common/libcommon.la \
  linux/liblinux.la
liblwjgl_la_DEPENDENCIES = \
  common/libcommon.la \
  linux/liblinux.la
