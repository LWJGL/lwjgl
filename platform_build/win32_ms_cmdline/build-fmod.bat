@echo off

set JAVAHOME=C:\Java\j2sdk1.5.0
set FMODHOME="C:\Program Files\fmodapi372win\api\inc"
set COPTIONS=/I%FMODHOME% /I%JAVAHOME%\include /I%JAVAHOME%\include\win32 /I..\..\src\native\common /O2 /nologo /c
set LINKEROPTS=/link
set LIBS=user32.lib Gdi32.lib Advapi32.lib

for %%x in (..\..\src\native\common\fmod3\*.cpp) do cl %COPTIONS% %%x
for %%x in (..\..\src\native\common\*common*.cpp) do cl %COPTIONS% %%x

cl /LD /Felwjgl-fmod3.dll *.obj %LINKEROPTS% %LIBS%