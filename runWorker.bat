@echo off
SET fp=%1
SET time=%2
SET max_Time=%3
echo File: %fp%
echo Time_Out: %time%
echo Max_Time_Out: %max_time%

set PROCESSNAME=java.exe
echo java -jar BE-PUM.jar -log -timeout%time% virus\%fp% 
START java -jar BE-PUM.jar -log -timeout%time% virus\%fp% 
set /a count=0
:beforeTimeOut
PING -n 2 127.0.0.1>nul
set /a count+=1
tasklist /FI "IMAGENAME eq %PROCESSNAME%" 2>NUL | FIND /I /N "%PROCESSNAME%">NUL
if "%ERRORLEVEL%"=="1" goto EndOfProcess
echo Program is still running...
if %count% LEQ %max_time% (
	echo You've waited for %count%(s^)
	goto beforeTimeOut
)
:EndOfProcess
echo Kill process, continue with another proccess
taskkill /F /IM %PROCESSNAME% 
exit

