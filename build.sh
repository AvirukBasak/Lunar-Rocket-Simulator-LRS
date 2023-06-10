#!/bin/bash

PROJECT_ROOT="."

SRC="$PROJECT_ROOT/src"
BUILD="$PROJECT_ROOT/build"
MANIFEST="$PROJECT_ROOT/META-INF/MANIFEST.MF"

ENTRY_PT="Main"
JAR_NAME="Lunar-Rocket-Simulator-LRS.jar"

mkdir -p "$BUILD"

if [ "$1" = "run" ] && ( stat "$JAR_NAME" > /dev/null 2> /dev/null ); then
    java -jar "$JAR_NAME"
    exit 0
elif [ "$1" = "clean" ]; then
    rm -rf "$BUILD/"*.class  2> /dev/null
    rm -rf "$BUILD/res"      2> /dev/null
    exit 0
elif [ "$1" = "cleaner" ]; then
    rm -rf "$BUILD/"*.class  2> /dev/null
    rm -rf "$BUILD/res"      2> /dev/null
    rm "$JAR_NAME"           2> /dev/null
    exit 0
elif [ "$1" = "" ]; then
    echo "running javac on $SRC"
    javac -Xlint:deprecation -sourcepath "$SRC" -d "$BUILD" -h "$BUILD" $(find "$SRC/" -name "*.java")
    exitcode=$?
    cp -r "$SRC/res" "$BUILD/"
    echo "entered dir $BUILD"
    cd "$BUILD"
    if (( exitcode == 0 )); then
        echo "running jar..."
        jar -cvmf "../$MANIFEST" "../$JAR_NAME" $(find ./)
    fi
    echo "done"
else
    echo "build.sh: invalid argument"
    exit 1
fi
