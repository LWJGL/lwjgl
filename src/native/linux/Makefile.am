noinst_LTLIBRARIES = libnative.la

libnative_la_SOURCES = $(NATIVE)
#libnative_la_LIBADD = -lXrender -lXcursor
#libnative_la_LDFLAGS = -Wl,-static,-lXcursor,-lXrender,-call_shared
INCLUDES = -I../common

NATIVE = \
  org_lwjgl_Sys.cpp \
  org_lwjgl_input_Controller.cpp \
  org_lwjgl_input_Keyboard.cpp \
  org_lwjgl_input_Mouse.cpp \
  org_lwjgl_input_Cursor.cpp \
  org_lwjgl_opengl_Display.cpp \
  org_lwjgl_opengl_Pbuffer.cpp \
  display.cpp \
  extgl_glx.cpp
#  extxcursor.cpp
  
