@echo off
REM Script para executar o Sistema de Gestão de Estudantes no Windows

echo =======================================
echo Sistema de Gestão de Estudantes - POO
echo =======================================
echo.

REM Verificar se o Java está instalado
java -version >nul 2>&1
if errorlevel 1 (
    echo ERRO: Java não encontrado!
    echo Por favor, instale Java 11 ou superior.
    pause
    exit /b 1
)

REM Verificar se o Maven está instalado
mvn -version >nul 2>&1
if errorlevel 1 (
    echo ERRO: Maven não encontrado!
    echo Por favor, instale Maven 3.6 ou superior.
    pause
    exit /b 1
)

echo Java e Maven encontrados!
echo.

REM Compilar o projeto
echo Compilando o projeto...
call mvn clean compile

if errorlevel 1 (
    echo.
    echo ERRO: Falha na compilação!
    echo Verifique as mensagens de erro acima.
    pause
    exit /b 1
)

echo.
echo Compilação bem-sucedida!
echo.
echo Iniciando a aplicação...
echo.
call mvn javafx:run

pause
