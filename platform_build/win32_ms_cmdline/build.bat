@echo off

if "%JAVA_HOME%" == "" goto errorjavahome
if "%PLTSDKHOME%" == "" goto errorpltsdkhome
if "%CHOME%" == "" goto errorchome
if "%ALHOME%" == "" goto erroralhome
if "%DXHOME%" == "" goto errordxhome
set COPTIONS=/Wp64 /I"%CHOME%\include" /I"%PLTSDKHOME%\include" /I"%DXHOME%\include" /I"%JAVA_HOME%\include" /I"%JAVA_HOME%\include\win32" /I"%ALHOME%\include" /I"..\..\src\native\common" /Ox /Ob2 /Oi /Ot /Oy /FD /EHsc /MT /Gy /W2 /nologo /c /D "WIN32" /D "NDEBUG" /D "_WINDOWS" /D "_USRDLL" /D "LWJGL_EXPORTS" /D "_WINDLL"
rem *************************************************
rem ** Build using free compiler requires jawt.dll **
rem ** and awt.dll in current dir. /delayload      **
rem ** fixes this, but is not available in free		 **
rem ** toolkit. Alternatively, remove AWT support  **
rem ** all together (only works on win32)          **
rem *************************************************
set LINKEROPTS=/link /LIBPATH:"%JAVA_HOME%\lib" /LIBPATH:"%ALHOME%\libs" /LIBPATH:"%DXHOME%\Lib\x86" /LIBPATH:"%PLTSDKHOME%\Lib" /LIBPATH:"%CHOME%\Lib" /SUBSYSTEM:WINDOWS /OPT:REF /OPT:ICF /MACHINE:X86 /NOLOGO /DLL
set LIBS=dinput.lib dxguid.lib OpenGL32.Lib Version.lib user32.lib Gdi32.lib Advapi32.lib jawt.lib winmm.lib

rem *************************************************
rem ** Use the following lines below to build      **
rem ** using the commercial toolkit which allows   **
rem ** delayload option. íe. Release build.        **
rem *************************************************
rem set LINKEROPTS=/link /LIBPATH:"%JAVA_HOME%\lib" /LIBPATH:"%ALHOME%\libs" /LIBPATH:"%DXHOME%\Lib" /LIBPATH:"%PLTSDKHOME%\Lib" /LIBPATH:"%CHOME%\Lib" /SUBSYSTEM:WINDOWS /OPT:REF /OPT:ICF /MACHINE:X86 /NOLOGO /DLL /DELAYLOAD:jawt.dll
rem set LIBS=Kernel32.lib dinput.lib dxguid.lib OpenGL32.Lib Version.lib user32.lib Gdi32.lib Advapi32.lib jawt.lib delayimp.lib winmm.lib

for %%x in (..\..\src\native\win32\*.c) do cl %COPTIONS% %%x
for %%x in (..\..\src\native\common\*.c) do cl %COPTIONS% %%x
for %%x in (..\..\src\native\generated\*.c) do cl %COPTIONS% %%x

cl /LD /Felwjgl.dll *.obj %LINKEROPTS% %LIBS%

del *.obj *.exp *.lib

copy lwjgl.dll ..\..\libs\

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

:erroralhome
echo -------------------------
echo --     ** ERROR **     --
echo -------------------------
echo ALHOME not set.
echo.
goto error

:errordxhome
echo -------------------------
echo --     ** ERROR **     --
echo -------------------------
echo DXHOME not set.
echo.
goto error

:error
echo The following environment variables are required to be set.
echo JAVA_HOME  The root directory where Java is installed
echo PLTSDKHOME  "   "       "        "  Platform SDK is installed
echo CHOME       "   "       "        "  Visual C++ toolkit is installed
echo ALHOME      "   "       "        "  OpenAl is installed
echo DXHOME      "   "       "        "  DirectX is installed
echo -------------------------
echo --     ** ERROR **     --
echo -------------------------

:end
