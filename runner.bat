@echo off
setlocal

REM Runs all three Sudoku experiments, then their analysis scripts.
REM Must be run from the project root (where this script lives),
REM since every path (data\, analysis\) is resolved relative to it.

cd /d "%~dp0"

echo --- Building jar ---
call mvnw.cmd -q -DskipTests package
if errorlevel 1 goto :error

set JAR=target\sudoku-1.0-SNAPSHOT.jar

echo --- Running Iterative experiment ---
java -cp "%JAR%" sudoku.IterativeSudokuExperiment
if errorlevel 1 goto :error

echo --- Running Random experiment ---
java -cp "%JAR%" sudoku.RandomSudokuExperiment
if errorlevel 1 goto :error

echo --- Running Recursive experiment ---
java -cp "%JAR%" sudoku.RecursiveSudokuExperiment
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