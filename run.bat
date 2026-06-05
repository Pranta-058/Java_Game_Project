@echo off
cd /d "%~dp0"
javac -d out src/snake/*.java
java -cp out snake.Main
pause
