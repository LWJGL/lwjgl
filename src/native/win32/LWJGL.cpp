/**
 * (C) 2002 Shaven Puppy Ltd
 * 
 * LWJGL.cpp Created on Aug 10, 2002 by foo
 */

#define WIN32_LEAN_AND_MEAN
#include <windows.h>
#include <stdio.h>

/*
 * DLL entry point for Windows. Called when Java loads the .dll
 */
BOOL WINAPI DllMain(
  HINSTANCE hinstDLL,  // handle to DLL module
  DWORD fdwReason,     // reason for calling function
  LPVOID lpvReserved   // reserved
  ) 
{
	return TRUE; // Success
}