@echo off

if "%JAVA_HOME%" == "" goto errorjavahome
if "%PLTSDKHOME%" == "" goto errorpltsdkhome
if "%CHOME%" == "" goto errorchome
if "%FMODHOME%" == "" goto errorfmodhome
set COPTIONS=/I"%FMODHOME%\api\inc" /I"%PLTSDKHOME%\include" /I"%CHOME%\include" /I"%JAVA_HOME%\include" /I"%JAVA_HOME%\include\win32" /I"..\..\src\native\common" /Ox /Ob2 /Oi /Ot /Oy /FD /EHsc /MT /Gy /W0 /nologo /c /D "WIN32" /D "NDEBUG" /D "_WINDOWS" /D "_USRDLL" /D "LWJGL_EXPORTS" /D "_WINDLL"
set LINKEROPTS=/link /LIBPATH:"%PLTSDKHOME%\Lib" /LIBPATH:"%CHOME%\Lib" /SUBSYSTEM:WINDOWS /OPT:REF /OPT:ICF /MACHINE:X86 /NOLOGO /DLL
set LIBS=user32.lib Gdi32.lib Advapi32.lib

for %%x in (..\..\src\native\common\fmod3\*.c) do cl %COPTIONS% %%x
for %%x in (..\..\src\native\common\*common*.c) do cl %COPTIONS% %%x

cl /LD /Felwjgl-fmod3.dll *.obj %LINKEROPTS% %LIBS%

del *.obj *.exp *.lib

copy lwjgl-fmod3.dll ..\..\libs\

goto end

:errorjavahome
echo -------------------------
echo --     ** ERROR **     --
echo -------------------------
echo JAVA_HOME not set.
echo.
goto error

:errorpltsdkhome
echo -------------------------
echo --     ** ERROR **     --
echo -------------------------
echo PLTSDKHOME not set.
echo.
goto error

:errorchome
echo -------------------------
echo --     ** ERROR **     --
echo -------------------------
echo CHOME not set.
echo.
goto error

:errorfmodhome
echo -------------------------
echo --     ** ERROR **     --
echo -------------------------
echo FMODHOME not set.
echo.

:error
echo The following environment variables are required to be set.
echo JAVA_HOME  The root directory where Java is installed
echo PLTSDKHOME  "   "       "        "  Platform SDK is installed
echo CHOME       "   "       "        "  Visual C++ toolkit is installed
echo FMODHOME    "   "       "        "  FMOD API is installed
echo -------------------------
echo --     ** ERROR **     --
echo -------------------------

:end