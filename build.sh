#!/bin/bash

PROJECT_ROOT="."

RES="$PROJECT_ROOT/res"
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
    rm -rf "$BUILD/res"      2> /dev/null
    rm -rf "$BUILD/"*        2> /dev/null
    exit 0
elif [ "$1" = "cleaner" ]; then
    rm -rf "$BUILD/res"      2> /dev/null
    rm -rf "$BUILD/"*        2> /dev/null
    rm "$JAR_NAME"           2> /dev/null
    exit 0
elif [ "$1" = "" ]; then
    echo "compile sources from $SRC"
    find src -name "*.java" > sources.list
    javac -Xlint:deprecation -d build @sources.list
    exitcode=$?
    rm -f sources.list
    if (( exitcode == 0 )); then
        cp -r "$RES" "$BUILD/"
        cd "$BUILD"
        echo "jar archive $BUILD"
        jar -cvmf "../$MANIFEST" "../$JAR_NAME" $(find ./)
    fi
    echo "done"
else
    echo "build.sh: invalid argument"
    exit 1
fi
