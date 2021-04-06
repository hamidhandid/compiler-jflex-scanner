#!/bin/bash

app() {
  if type "_${1}" &>/dev/null
  then
    eval "_${1} ${@:2}"
  else
    echo "Method \"${1}\" is not defined."
  fi
}

_run() {
  cd src
  java -jar ../lib/jflex-full-1.8.2.jar -d ./classes/ flex/scanner.flex
  mkdir -p bin
  cd bin
  # Change : to ; in two following lines if you use windows
  javac -cp "..:../../lib/jsoup-1.13.1.jar" -d ./ ../Main.java
  java -cp ".:../../lib/jsoup-1.13.1.jar" Main ../files/ code.txt
  cd ..
  cd ..
}

_delete() {
  cd src
  rm -rf ./Main.class
  rm -rf ./bin/*.class
  rm -rf ./bin/classes/
  rm -rf ./classes/CompilerScanner.java
  rm -rf ./files/highlighter.html
  printf "generated files deleted successfully.\n"
  cd ..
}
