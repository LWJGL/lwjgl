@echo off

set JAVAHOME=C:\Java\jdk1.5.0
set FMODHOME="C:\Program Files\fmodapi373win\api\inc"
set PLTSDKHOME="C:\Program Files\Microsoft SDK"
set COPTIONS=-DWIN32 /I%FMODHOME% /I%PLTSDKHOME%\include /I%JAVAHOME%\include /I%JAVAHOME%\include\win32 /I..\..\src\native\common /O2 /nologo /c /EHsc
set LINKEROPTS=/link /LIBPATH:%PLTSDKHOME%\Lib
set LIBS=user32.lib Gdi32.lib Advapi32.lib

for %%x in (..\..\src\native\common\fmod3\*.cpp) do cl %COPTIONS% %%x
for %%x in (..\..\src\native\common\*common*.cpp) do cl %COPTIONS% %%x

cl /LD /Felwjgl-fmod3.dll *.obj %LINKEROPTS% %LIBS%

del *.obj *.exp *.lib

copy lwjgl-fmod3.dll ..\..\libs\
