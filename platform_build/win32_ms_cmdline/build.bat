@echo off

set JAVAHOME=C:\Java\j2sdk1.5.0
set ALHOME="c:\Program Files\OpenAL 1.0 Software Development Kit"
set EAXHOME="c:\Program Files\Creative Labs\EAX 2.0 Extensions SDK"
set DXHOME=C:\DXSDK
set COPTIONS=/I%DXHOME%\Include /I%JAVAHOME%\include /I%JAVAHOME%\include\win32 /I%ALHOME%\Include /I%EAXHOME%\Include /I..\..\src\native\common /O2 /nologo /c
set LINKEROPTS=/link /LIBPATH:%ALHOME%\libs /LIBPATH:%EAXHOME%\Libs /LIBPATH:%DXHOME%\Lib
set LIBS=dinput.lib dxguid.lib OpenAL32.lib alut.lib eaxguid.lib OpenGL32.Lib Version.lib user32.lib Gdi32.lib Advapi32.lib

for %%x in (..\..\src\native\win32\*.cpp) do cl %COPTIONS% %%x
for %%x in (..\..\src\native\common\*.cpp) do cl %COPTIONS% %%x
for %%x in (..\..\src\native\common\arb\*.cpp) do cl %COPTIONS% %%x
for %%x in (..\..\src\native\common\nv\*.cpp) do cl %COPTIONS% %%x
for %%x in (..\..\src\native\common\ext\*.cpp) do cl %COPTIONS% %%x
for %%x in (..\..\src\native\common\ati\*.cpp) do cl %COPTIONS% %%x

cl /LD /Felwjgl.dll *.obj %LINKEROPTS% %LIBS%
