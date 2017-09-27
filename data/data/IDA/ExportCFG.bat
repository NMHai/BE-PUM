@ECHO off
ECHO Make sure your bat file must in same folder of Virus folder and IDA
rem get directory of bat file
SET mainpath=%~dp0
rem get IDA execution
rem SET idapath=%mainpath%IDA\idaq.exe
SET idapath=idaq.exe
rem get virus path
SET filepath=%mainpath%%1
rem get script path
SET scriptpath=%mainpath%phongGenerateCFG.py
rem create folder contains CFG
rem IF EXIST %mainpath%\fileCFG GOTO NEXT
rem mkdir %mainpath%\fileCFG
rem :NEXT
rem compile virus, %%f is path of virus
rem FOR /f %%f IN ('dir /b /s "%filepath%"') DO (
rem ECHO %%f
CALL %idapath% -c -A -S%scriptpath% %filepath% 
rem CD %filepath%
rem MOVE *.dot %mainpath%\fileCFG
exit
rem CD %mainpath%