Basic Sudoku Generator.

Experiment:

As the number of randomly removed cells increases, how does this affect the probability of generating a unique sudoku?

Progress:
  - Completed random, iterative and recursive cell removal methods for user chosen number of cells.
      - Unit Tests to show the right number of cells were deleted and the right cells where deleted.
  - Function to check whether the puzzle is unique
      - Tests for unique / multiple solution puzzles
  - Function to run 1000 trials for each method and save to a csv file, counting time, whether 1 or multiple solutions, trial number and number of cells     removed.
  - Process results into a graph and table of mean runtime against number of missing cells
  - For iterative and random methods:  graph of percentage chance of success / chance of generating a valid sudoku against number of missing cells
  - For iterative and random methods: calculates the true total runtime to generate a valid sudoku by the equation
    (single runtime / chance of generating a valid cell)
  - Graph and table to compare the mean true runtime between all three methods.
   
Todo

  - Calculate according to worst time calculations
  - Report detailing my findings.

EBI
  - Improvements to each methods:
      - Such as picking according to lowest entropy (least amount of possible numbers available in a certain cell)




