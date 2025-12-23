#!/bin/bash
# Run script for Collection Management System

# Check if compiled
if [ ! -d "bin" ]; then
    echo "Project not compiled. Running compilation first..."
    ./compile.sh
fi

echo "Starting Collection Management System..."
java -cp bin com.collection.Main
