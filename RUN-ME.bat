@echo off
cd /d "%~dp0"
echo Starting Baku Guide... first run downloads Maven, please wait a few minutes.
echo When you see "Started BakuGuideApplication", open http://localhost:8080 in your browser.
echo Press Ctrl+C in this window to stop the server.
call mvnw.cmd spring-boot:run
pause
