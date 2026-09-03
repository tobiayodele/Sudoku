# Sudoku Cell Removal Methods Experiment

Java-based Sudoku experiment to compare the efficiency in generating a **unique** Sudoku puzzle.

## Contents

- [Experiment](#experiment)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
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

How it works.
1. Generate a complete 9x9 Sudoku board using recursive backtracking.
2. Remove cells with one of the three methods above
3. Check for uniqueness
   - Random checked for uniqueness at end of generation
   - Greedy/Iterative checked if puzzle is return as any puzzle returned will be unique
   - Recursive always returns unique puzzle
4. Record trial time and uniqueness to CSV
5. Analyse the CSVs using Python scripts to find and compare the estimated cost to generate a unique Sudoku puzzle for each removal method.

The report and 1000 trial data and graphs for this experiment can be found at ``` /docts/ ```

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
# Prerequisites
- Java 26 or later
- Python 3
  - `pandas`
  - `matplotlib`
  - `openpyxl` (required for writing `.xlsx` output)
# Installation

Windows:

Clone this repository and checkout the experiments branch.

```bash
git clone https://github.com/tobiayodele/Sudoku.git
cd Sudoku
git checkout experiments
```
Install requirements.
```bash
pip install pandas
pip install matplotlib
pip install openyxl
```
Run the ``` runner.bat ``` script.

## Usage
Run the runner script for your operating system.
Enter the number of trials for each cell and then the runner will automatically run the experiments as well as the analysis scripts.

Raw results can be found in ```data/```.
Processed graphs and results tables can be found at ```analysis/*/results```.

## License

This project is licensed under the MIT License.

## Author
Tobi Ayodele
github.com/tobiayodele




