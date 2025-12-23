#!/bin/bash
# Compile script for Collection Management System

echo "Compiling Collection Management System..."
javac -d bin src/main/java/com/collection/*.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo "To run the application, use: java -cp bin com.collection.Main"
else
    echo "Compilation failed!"
    exit 1
fi
