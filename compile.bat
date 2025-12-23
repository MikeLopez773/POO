@echo off
REM Compile script for Collection Management System (Windows)

echo Compiling Collection Management System...
if not exist bin mkdir bin
javac -d bin src\main\java\com\collection\*.java

if %ERRORLEVEL% EQU 0 (
    echo Compilation successful!
    echo To run the application, use: java -cp bin com.collection.Main
) else (
    echo Compilation failed!
    exit /b 1
)
