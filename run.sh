#!/bin/bash

# Script para executar o Sistema de Gestão de Estudantes

echo "======================================="
echo "Sistema de Gestão de Estudantes - POO"
echo "======================================="
echo ""

# Verificar se o Java está instalado
if ! command -v java &> /dev/null
then
    echo "❌ ERRO: Java não encontrado!"
    echo "Por favor, instale Java 11 ou superior."
    exit 1
fi

# Verificar se o Maven está instalado
if ! command -v mvn &> /dev/null
then
    echo "❌ ERRO: Maven não encontrado!"
    echo "Por favor, instale Maven 3.6 ou superior."
    exit 1
fi

echo "✅ Java encontrado: $(java -version 2>&1 | head -n 1)"
echo "✅ Maven encontrado: $(mvn -version | head -n 1)"
echo ""

# Compilar o projeto
echo "📦 Compilando o projeto..."
mvn clean compile

if [ $? -eq 0 ]; then
    echo ""
    echo "✅ Compilação bem-sucedida!"
    echo ""
    echo "🚀 Iniciando a aplicação..."
    echo ""
    mvn javafx:run
else
    echo ""
    echo "❌ ERRO: Falha na compilação!"
    echo "Verifique as mensagens de erro acima."
    exit 1
fi
