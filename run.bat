@echo off
REM Run script for Collection Management System (Windows)

if not exist "bin" (
    echo Project not compiled. Running compilation first...
    call compile.bat
)

echo Starting Collection Management System...
java -cp bin com.collection.Main
