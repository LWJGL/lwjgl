@echo off

set JAVAHOME="C:\Program Files\Java\jdk1.5.0"
set ALHOME="c:\Program Files\Creative Labs\OpenAL 1.0 SDK"
set EAXHOME="c:\Program Files\Creative Labs\EAX 2.0 Extensions SDK"
set DXHOME="C:\DXSDK"
set PLTSDKHOME="C:\Program Files\Microsoft SDK"
set COPTIONS=/I%DXHOME%\Include /I%PLTSDKHOME%\include /I%JAVAHOME%\include /I%JAVAHOME%\include\win32 /I%ALHOME%\Include /I%EAXHOME%\Include /I..\..\src\native\common /O2 /nologo /c
set LINKEROPTS=/link /LIBPATH:%ALHOME%\libs /LIBPATH:%EAXHOME%\Libs /LIBPATH:%DXHOME%\Lib /LIBPATH:%PLTSDKHOME%\Lib
set LIBS=dinput.lib dxguid.lib OpenAL32.lib alut.lib eaxguid.lib OpenGL32.Lib Version.lib user32.lib Gdi32.lib Advapi32.lib

for %%x in (..\..\src\native\win32\*.cpp) do cl %COPTIONS% %%x
for %%x in (..\..\src\native\common\*.c) do cl %COPTIONS% %%x
for %%x in (..\..\src\native\common\arb\*.c) do cl %COPTIONS% %%x
for %%x in (..\..\src\native\common\nv\*.c) do cl %COPTIONS% %%x
for %%x in (..\..\src\native\common\ext\*.c) do cl %COPTIONS% %%x
for %%x in (..\..\src\native\common\ati\*.c) do cl %COPTIONS% %%x

cl /LD /Felwjgl.dll *.obj %LINKEROPTS% %LIBS%

del *.obj *.exp *.lib