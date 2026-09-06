# Lightweight Sudoku Generator and Solver

Java-based Sudoku generator, solver, validator and command-line playable game.

---

## Contents

- [Feature List](#feature-list)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
  - [Windows](#windows)
  - [Linux/MacOS](#linuxmacos)
- [Usage](#usage)
  - [Play Sudoku](#play-sudoku)
  - [Solve Sudoku](#solve-sudoku)
- [Tests](#tests)
- [Experiment](#experiment)
- [License](#license)
- [Author](#author)

---
# Feature List

### Sudoku Board
  - Creates empty 9x9 Sudoku grid
  - Values can be placed into individual cells
  - Empty cells represented by 0

### Sudoku Puzzle Generation
  - A solved Sudoku is generated using a recursive backtracking method to ensure solution is valid
  - Greedy/Iterative cell removal method to remove a given number of cells.
  - Uniqueness checks for puzzles using a recursive solution counter.

  > See [experiment](#experiment) for more detail on cell removal methods.
 

### Difficulty Levels
The game has  three difficulty levels: Easy, Medium and Hard

| Difficulty  | Missing Cells |
| ----------- | ------------- |
| Easy        | 40            |
| Medium      | 46            |
| Hard        | 55            |


### Sudoku Validation
- Cells are validated for:
  - Rows
  - Columns
  - 3x3 boxes
- Boards are validated for:
  - Completed Sudoku boards
  - Incomplete Sudoku boards


### Sudoku Solver
  - Allows the user to enter a partially solved Sudoku and outputs the answer.
  - The solver recursively tries to solve the puzzle, backtracking when it reaches a dead end.
  - Checks for:
      - Unsolvable puzzles
      - Already completed puzzles
      - Non-unique puzzles


# Project Structure
```text
Sudoku/
├── .mvn/                                #Maven wrapper
│   └── wrapper/
│       └── maven-wrapper.properties
│
├── src/                                  # Java source code
│   ├── main/                             
│   └── test/                          
│
├── pom.xml                               # Maven config
└── README.md   
```

---

# Prerequisites
- Java 26 or later
- Git


# Installation

### Windows:

Clone this repository.

```bash
git clone https://github.com/tobiayodele/Sudoku.git
cd Sudoku
```
Build the project.
```bash
mvnw.cmd clean package
```

Run the game.
```bash
java -jar target/sudoku-1.0-SNAPSHOT.jar
```

---

### Linux/MacOS:

Clone this repository.

```bash
git clone https://github.com/tobiayodele/Sudoku.git
cd Sudoku
```
Build the project.
```bash
./mvnw clean package

```
Run the game.
```bash
java -jar target/sudoku-1.0-SNAPSHOT.jar
```

---

## Usage
Run the game and the startup menu will display
```text
===== Sudoku =====
1. Play Sudoku
2. Solve Sudoku
3. Exit
Choose an option:
```
### Play Sudoku

Enter 1 to play a new Sudoku game.
 
You will then be prompted to enter a difficulty: Easy/Medium/Hard.

After picking a difficulty select a valid cell and enter a guess.

You will be informed if you have entered a successful cell or if you are wrong; in which case you will lose 1 of 3 lives.

The game will end when you lose all 3 lives or have completed the Sudoku.

### Solve Sudoku

Enter 2 to use the solver.

Enter all the cells in the partially completed board (Enter 0 for unfilled cells).

The solver will then check it is a uniquely solvable puzzle and then print out the answer.

---

## Tests

This project uses JUnit4 for core functionality testing.

To run these tests use the command:

### Windows:
```mvnw.cmd test```
### Linux/MacOS:
```./mvnw test```

---

## Experiment
The ```experiments``` branch contains an experiment comparing three different cell-removal methods in terms of the runtime to generate a unique Sudoku puzzle as the number of missing cells increases.

The Greedy/Iterative method was chosen as the cell-removal method for the Sudoku generator as it performed the best within the 40-60 missing cell range which is the range which is used in the generator. 

To access the experiment and raw experimental data and generated tables and graphs:

```git checkout experiments```

---

## License

[MIT](https://choosealicense.com/licenses/mit/)

## Author
**Tobi Ayodele**
[github.com/tobiayodele](https://github.com/tobiayodele)

