#!/bin/bash

# Set variables
# SRC_DIR="src"
BIN_DIR="bin"
MAIN_CLASS="Main"  # Change this to your actual main class

mkdir -p $BIN_DIR
# clean:
files=$(find . -maxdepth 1 -type f -name "*.class")
for file in $files; do
  rm "$file"
done

echo "Compiling Java files..."
javac -d $BIN_DIR ~/Opt/ninja_noki/src/*.java

# exit_success_check: 
if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo "Running $MAIN_CLASS..."
    java -cp $BIN_DIR $MAIN_CLASS
else
    echo "Compilation failed!"
    exit 1
fi

# package-build:
# javac -d . $(pwd)/*.java
