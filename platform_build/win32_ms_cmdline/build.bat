@echo off

if "%JAVA_HOME%" == "" goto errorjavahome
if "%PLTSDKHOME%" == "" goto errorpltsdkhome
if "%CHOME%" == "" goto errorchome
if "%ALHOME%" == "" goto erroralhome
if "%EAXHOME%" == "" goto erroreaxhome
if "%DXHOME%" == "" goto errordxhome
set COPTIONS=/I"%DXHOME%\include" /I"%CHOME%\include" /I"%PLTSDKHOME%\include" /I"%JAVA_HOME%\include" /I"%JAVA_HOME%\include\win32" /I"%ALHOME%\include" /I"%EAXHOME%\include" /I"..\..\src\native\common" /O2 /nologo /c 
set LINKEROPTS=/link /LIBPATH:"%ALHOME%\libs" /LIBPATH:"%EAXHOME%\Libs" /LIBPATH:"%DXHOME%\Lib" /LIBPATH:"%PLTSDKHOME%\Lib" /LIBPATH:"%CHOME%\Lib"
set LIBS=dinput.lib dxguid.lib OpenAL32.lib alut.lib eaxguid.lib OpenGL32.Lib Version.lib user32.lib Gdi32.lib Advapi32.lib

for %%x in (..\..\src\native\win32\*.cpp) do cl %COPTIONS% %%x
for %%x in (..\..\src\native\common\*.c) do cl %COPTIONS% %%x
for %%x in (..\..\src\native\common\arb\*.c) do cl %COPTIONS% %%x
for %%x in (..\..\src\native\common\nv\*.c) do cl %COPTIONS% %%x
for %%x in (..\..\src\native\common\ext\*.c) do cl %COPTIONS% %%x
for %%x in (..\..\src\native\common\ati\*.c) do cl %COPTIONS% %%x

cl /LD /Felwjgl.dll *.obj %LINKEROPTS% %LIBS%

del *.obj *.exp *.lib

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

:errordevilhome
echo -------------------------
echo --     ** ERROR **     --
echo -------------------------
echo DEVILHOME not set.
echo.
goto error

:erroreaxhome
echo -------------------------
echo --     ** ERROR **     --
echo -------------------------
echo EAXHOME not set.
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
echo DEVILHOME   "   "       "        "  DevIL is installed
echo EAXHOME     "   "       "        "  EAX is installed
echo DXHOME      "   "       "        "  DirectX is installed
echo -------------------------
echo --     ** ERROR **     --
echo -------------------------

:end