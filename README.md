Basic Sudoku Generator.

What Works:
  - Generates Empty 9x9 Grid
  - Can fill a cell with a value
  - Validity checks for box, column and row duplicates.
  - Validity check for cell duplicates (14/08/26)
  - Completely generates a **unique** completed sudoku (14/08/26)
      - Validity checks for row and column duplicates
      - Validity check for no empty cell

Next changes:
  - Validity checks for box duplicates
  - Removal of a certain number of boxes to give a game one can actually play
      - Should result in a sudoku with only one possible solution

Long Term Aims:
  - Player Interaction
  - Difficulty Levels (Changes the number of starting cells)
  - Tips such as number of remaining numbers, for example if there is one 8 filled in, there are eight 8s to be filled in
  - Sudoku Solver
  - Hints
  - Allowing for x number of incorrect placements
  - GUI interface
