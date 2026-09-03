#!/usr/bin/env bash
set -e

cd "$(dirname "$0")"

echo "--- Building jar ---"
./mvnw package

JAR="target/sudoku-1.0-SNAPSHOT.jar"

echo "--- Running Experiments ---"
java -cp "$JAR" sudoku.Main

echo "--- Running Iterative analysis ---"
python3 analysis/Iterative/iterative_analyse_sudoku.py

echo "--- Running Random analysis ---"
python3 analysis/Random/random_analyse_sudoku.py

echo "--- Running Recursive analysis ---"
python3 analysis/Recursive/recursive_analyse_sudoku.py

echo "--- Running Comparison analysis ---"
python3 analysis/Comparison/compare_methods.py

echo "--- Success! ---"