call runcrud
if "%ERRORLEVEL%" == "0" goto runchrome
echo.
echo RUNCRUD has error - breaking script...
goto fail

:runchrome
start chrome http://localhost:8080/crud/v1/task/getTasks
goto end

:fail
echo.
echo Something gone wrong..

:end
echo.
echo All are done.
