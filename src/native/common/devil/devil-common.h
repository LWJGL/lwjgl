#ifndef __DEVIL_COMMON_H__
#define __DEVIL_COMMON_H__

#include <jni.h>
#include <stdio.h>
#include <string.h>
#include "org_lwjgl_devil_IL.h"
#include "org_lwjgl_devil_ILU.h"
#include "org_lwjgl_devil_ILUT.h"
#include "common_tools.h"

/*-----------------------------------------*/
typedef unsigned int GLuint;

#ifdef _UNICODE
	#ifndef _WIN32_WCE
		#include <wchar.h>
	#endif
	typedef wchar_t* ILstring;
#else
	typedef char* ILstring;
#endif//_UNICODE

#if (_MSC_VER >= 800) || defined(_STDCALL_SUPPORTED) || defined(__BORLANDC__) || defined(__LCC__)
	#define ILAPIENTRY __stdcall 
	#define IL_PACKSTRUCT
//#elif defined(linux) || defined(MACOSX) || defined(__CYGWIN__) //fix bug 840364
#elif defined( __GNUC__ )
  // this should work for any of the above commented platforms 
  // plus any platform using GCC
	#define ILAPIENTRY
	#define IL_PACKSTRUCT __attribute__ ((packed))
#else
	#define ILAPIENTRY
	#define IL_PACKSTRUCT
#endif

// This is from Win32's <wingdi.h> and <winnt.h>
#if defined(__LCC__)
	#define ILAPI __stdcall
#elif defined(_WIN32) //changed 20031221 to fix bug 840421
	#ifdef IL_STATIC_LIB
		#define ILAPI
	#else
		#ifdef _IL_BUILD_LIBRARY
			#define ILAPI __declspec(dllexport)
		#else
			#define ILAPI __declspec(dllimport)
		#endif
	#endif
#elif __APPLE__
	#define ILAPI extern
#else
	#define ILAPI
#endif

typedef void* ILHANDLE;
typedef unsigned int	ILenum;
typedef unsigned char	ILboolean;
typedef unsigned int	ILbitfield;
typedef char			ILbyte;
typedef short			ILshort;
typedef int				ILint;
typedef int				ILsizei;
typedef unsigned char	ILubyte;
typedef unsigned short	ILushort;
typedef unsigned int	ILuint;
typedef float			ILfloat;
typedef float			ILclampf;
typedef double			ILdouble;
typedef double			ILclampd;
typedef void			ILvoid;

typedef struct ILinfo
{
	ILuint	Id;					// the image's id
	ILubyte	*Data;				// the image's data
	ILuint	Width;				// the image's width
	ILuint	Height;				// the image's height
	ILuint	Depth;				// the image's depth
	ILubyte	Bpp;				// bytes per pixel (not bits) of the image
	ILuint	SizeOfData;			// the total size of the data (in bytes)
	ILenum	Format;				// image format (in IL enum style)
	ILenum	Type;				// image type (in IL enum style)
	ILenum	Origin;				// origin of the image
	ILubyte	*Palette;			// the image's palette
	ILenum	PalType;			// palette type
	ILuint	PalSize;			// palette size
	ILenum	CubeFlags;			// flags for what cube map sides are present
	ILuint	NumNext;			// number of images following
	ILuint	NumMips;			// number of mipmaps
	ILuint	NumLayers;			// number of layers
} ILinfo;


typedef struct ILpointf
{
	ILfloat x, y;
} ILpointf;

typedef struct ILpointi
{
	ILint x, y;
} ILpointi;

#define IL_IMAGE_WIDTH						0x0DE4
#define IL_IMAGE_HEIGHT						0x0DE5
#define IL_IMAGE_BYTES_PER_PIXEL			0x0DE8

// Registered format procedures
typedef ILenum		(ILAPIENTRY *IL_LOADPROC)(const ILstring);
typedef ILenum		(ILAPIENTRY *IL_SAVEPROC)(const ILstring);
/*-----------------------------------------*/

#if defined(_WIN32) && !defined(APIENTRY)
#define WIN32_LEAN_AND_MEAN 1
#include <windows.h>
#endif

#ifndef APIENTRY
#define APIENTRY
#endif

#endif /* __EXTIL_H__ */