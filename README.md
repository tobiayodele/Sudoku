# Sudoku Cell Removal Methods Experiment

Java-based Sudoku experiment to compare the efficiency in generating a **unique** Sudoku puzzle.

## Contents

- [Experiment](#experiment)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Usage](#usage)
- [License](#license)
- [Author](#author)

## Experiment
Research Question:
>As the number of  removed cells increases, how does this affect the probability of generating a unique sudoku?

The cell removal methods compared are:
- Random - Randomly remove cells until the desired number of missing cells.
- Greedy/Iterative - Pass through all cells and only remove cells if they maintain a unique solution.
- Recursive - Cells are removed if they maintain a unique solution and will backtrack if a unique solution cannot be found.

For each method Sudoku puzzles are generated the experiment generates a puzzle and tests measures its runtime and records uniqueness.

The report for this experiment can be found at ``` /docts/report_cell_removal_methods.pdf ```
# Project Structure
```text
Sudoku/
├── analysis/                             # Python scripts and generated graphs
│   ├── Comparison/
│   │   ├── results/          
│   │   └── compare_methods.py
│   ├── Iterative/
│   │   ├── results/
│   │   └── iterative_analyse_sudoku.py
│   ├── Random/
│   │   ├── results/
│   │   └── random_analyse_sudoku.py
│   ├── Recursive/
│   │   ├── results/
│   │   └── recursive_analyse_sudoku.py
│
├── docs/                                 # Experiment Report and 1000 trial data
│   ├── processed_results_1000/           
│   ├── raw_results_1000/                 
│   └── report_cell_removal_methods.pdf   
│
├── src/                                  # Java source code
│   ├── main/                             
│   └── test/                          
│
├── data/                                 # Raw CSV data                      
│   ├── Iterative/
│   ├── Random/
│   └── Recursive/
│
├── runner.bat                            # Windows experiment runner script
├── pom.xml                               # Maven config
└── README.md   
```

# Installation

Windows:

Clone this repository and checkout the experiments branch.

```bash
git clone https://github.com/tobiayodele/Sudoku.git
cd Sudoku
git checkout experiments
```

## Usage
Run the ``` runner.bat ``` file.
Enter the number of trials for each cell and then the runner with automatically run the experiments as well as the analysis scripts.

Raw results can be found in ```data/```.
Processed results can be found at ```analysis/*/results```.

## License

This project is licensed under the MIT License.

## Author
Tobi Ayodele
github.com/tobiayodele




