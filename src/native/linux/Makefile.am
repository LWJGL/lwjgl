noinst_LTLIBRARIES = libnative.la

libnative_la_SOURCES = $(NATIVE)
#libnative_la_LIBADD = -lXrender -lXcursor
#libnative_la_LDFLAGS = -Wl,-static,-lXcursor,-lXrender,-call_shared
INCLUDES = -I../common

NATIVE = \
  org_lwjgl_Sys.c \
  org_lwjgl_input_Controller.c \
  org_lwjgl_input_Keyboard.c \
  org_lwjgl_input_Mouse.c \
  org_lwjgl_input_Cursor.c \
  org_lwjgl_opengl_Display.c \
  org_lwjgl_opengl_Pbuffer.c \
  display.c \
  extgl_glx.c
  
