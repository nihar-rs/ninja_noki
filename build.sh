#!/bin/bash

# Set variables
SRC_DIR="src"
BIN_DIR="bin"
MAIN_CLASS="src.main.Main" 
mkdir -p $BIN_DIR

echo "Compiling Java files from $SRC_DIR to $BIN_DIR..."
javac -d $BIN_DIR ~/Opt/ninja_noki/src/main/*.java

# exit_success_check: 
if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo "Running $MAIN_CLASS..."
    # java -cp $BIN_DIR $MAIN_CLASS
    java -cp "$BIN_DIR:resource/images/" "$MAIN_CLASS"
else
    echo "Compilation failed!"
    exit 1
fi

# package-build:
# javac -d . $(pwd)/*.java

