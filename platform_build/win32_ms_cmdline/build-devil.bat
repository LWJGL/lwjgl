@echo off

if "%JAVA_HOME%" == "" goto errorjavahome
if "%PLTSDKHOME%" == "" goto errorpltsdkhome
if "%CHOME%" == "" goto errorchome
if "%DEVILHOME%" == "" goto errordevilhome
set COPTIONS=/I"%PLTSDKHOME%\include" /I"%CHOME%\include" /I"%JAVAHOME%\include" /I"%JAVAHOME%\include\win32" /I"%DEVILHOME%\include" /I..\..\src\native\common /O2 /nologo /c /EHsc
set LINKEROPTS=/link /LIBPATH:"%PLTSDKHOME%\Lib" /LIBPATH:"%CHOME%\Lib" /LIBPATH:"%DEVILHOME%\Lib"
set LIBS=user32.lib Gdi32.lib Advapi32.lib DevIL.lib

for %%x in (..\..\src\native\common\devil\*.c) do cl %COPTIONS% %%x
for %%x in (..\..\src\native\common\*common*.c) do cl %COPTIONS% %%x

cl /LD /Felwjgl-devil.dll *.obj %LINKEROPTS% %LIBS%

del *.obj *.exp *.lib

copy lwjgl-devil.dll ..\..\libs\
copy DevIL.dll ..\..\libs\
