@ECHO off

SET main_path=%~dp0

SET cfg_path=asm/cfg
SET olly_path=asm/olly
SET data_path=data/data

FOR /f %%f IN ('dir /b /s "%cfg_path%"') DO (
	DEL %%f 		 
)

FOR /f %%f IN ('dir /b /s "%olly_path%"') DO (
	DEL %%f 		 
)

CD %data_path%
IF EXIST *txt ECHO Y | DEL *.txt
CD %main_path%

