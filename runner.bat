@echo off
setlocal

cd /d "%~dp0"

echo --- Building jar ---
call mvnw.cmd package
if errorlevel 1 goto :error

set JAR=target\sudoku-1.0-SNAPSHOT.jar

echo --- Running Experiments ---
java -cp "%JAR%" sudoku.Main
if errorlevel 1 goto :error

echo --- Running Iterative analysis ---
python analysis\Iterative\iterative_analyse_sudoku.py
if errorlevel 1 goto :error

echo --- Running Random analysis ---
python analysis\Random\random_analyse_sudoku.py
if errorlevel 1 goto :error

echo --- Running Recursive analysis ---
python analysis\Recursive\recursive_analyse_sudoku.py
if errorlevel 1 goto :error

echo --- Running Comparison analysis ---
python analysis\Comparison\compare_methods.py
if errorlevel 1 goto :error

echo --- Success! --
goto :eof

:error
echo Something failed - stopping.
exit /b 1