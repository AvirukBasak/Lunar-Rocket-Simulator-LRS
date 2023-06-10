#!/bin/bash

ROOT="."

RES="$ROOT/res"
SRC="$ROOT/src"
BUILD="$ROOT/build"
MANIFEST="$ROOT/META-INF/MANIFEST.MF"
JAR_NAME="$ROOT/Lunar-Rocket-Simulator-LRS.jar"

build() {
    echo "compile sources from $SRC"
    find "$SRC" -name "*.java" > sources.list
    javac -Xlint:deprecation -d build @sources.list
    exitcode=$?
    rm -f sources.list
    if (( exitcode == 0 )); then
        cp -r "$RES" "$BUILD/"
        echo "jar archive $BUILD"
        jar -cmf "$MANIFEST" "$JAR_NAME" -C build .
        echo "done"
    fi
    return $exitcode
}

run() {
    if ! ( stat "$JAR_NAME" > /dev/null 2> /dev/null ); then
        build && java -jar "$JAR_NAME"
    else
        java -jar "$JAR_NAME"
    fi
}

clean() {
    rm -rf "$BUILD/res" 2> /dev/null
    rm -rf "$BUILD/"* 2> /dev/null
}

cleaner() {
    clean
    rm -f "$JAR_NAME" 2> /dev/null
}

mkdir -p "$BUILD"

case "$1" in
    "run") run
        ;;
    "clean") clean
        ;;
    "cleaner") cleaner
        ;;
    "") build
        ;;
    *)
        echo "build.sh: invalid argument"
        exit 1
        ;;
esac
