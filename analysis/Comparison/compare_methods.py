import  pandas as pd
import matplotlib.pyplot as plt

import os

SCRIPT_DIRECTORY = os.path.dirname(os.path.abspath(__file__))
PROJECT_ROOT = os.path.abspath(os.path.join(SCRIPT_DIRECTORY, "..", ".."))

iterative_data = pd.read_csv(os.path.join(PROJECT_ROOT, "data", "Iterative", "iterative_sudoku_experiment_1000.csv"))
recursive_data = pd.read_csv(os.path.join(PROJECT_ROOT, "data", "Recursive", "recursive_sudoku_experiment_1000.csv"))
random_data = pd.read_csv(os.path.join(PROJECT_ROOT, "data", "Random", "random_sudoku_experiment_1000.csv"))


def calculate_single_mean_times(data):
    ## calculate mean time by dividing the total by the number of values in the group
    missing_cells = []
    mean_times = []
    for number_of_missing_cells, group in data.groupby("missing_cells"):
        total = 0
        count = 0 
    
        for solution_time in group["total_time_ns"]:
            total += solution_time
            count += 1

        mean = (total / count) /1e6 
  
        missing_cells.append(number_of_missing_cells)
        mean_times.append(mean)

    return missing_cells, mean_times


def calculate_iterative_success_chance(data):
    ## find chance of success for generating a puzzle with iterative method
    chance_of_success = []
    for number_of_missing_cells, group in data.groupby("missing_cells"):
        success_count = 0
        for successful_count in group["success"]:
            if successful_count == 1:
                success_count += 1

        chance = (success_count / len(group) ) 
        chance_of_success.append(chance)
    return chance_of_success

def calculate_random_success_chance(data):
    ## find the chance to randomly generate a unique puzzle
    chance_of_success = []
    for number_of_missing_cells, group in data.groupby("missing_cells"):
        unique_count = 0
        for unique_solution_count in group["solution_count"]:
            if unique_solution_count == 1:
                unique_count += 1
        chance = (unique_count / len(group) ) 
        chance_of_success.append(chance)
    return chance_of_success

def calculate_total_runtime(single_mean_times,chance_of_success):
    ## calculate average time to create a unique puzzle by dividing the time taken
    ## to generate a puzzle by the chance of generating a valid puzzle.
    total_runtimes = []

    for i in range (len(single_mean_times)):
        ## can not divide by 0, so set to infinity to show it would take infinitely 
        ## long to generate valid solution
        if chance_of_success[i] == 0:
            total_runtime = float("inf")
        else:
             total_runtime = single_mean_times[i] / chance_of_success[i]

        total_runtimes.append(total_runtime)

    return total_runtimes


iterative_missing_cells, iterative_mean_times = calculate_single_mean_times(iterative_data)
recursive_missing_cells, recursive_mean_times = calculate_single_mean_times(recursive_data)
random_missing_cells, random_mean_times = calculate_single_mean_times(random_data)

iterative_chance_of_success = calculate_iterative_success_chance(iterative_data)
random_chance_of_success = calculate_random_success_chance(random_data)

iterative_total_mean_times = calculate_total_runtime(iterative_mean_times,iterative_chance_of_success)
random_total_mean_times= calculate_total_runtime(random_mean_times,random_chance_of_success)

## plot the graph to compare runtimes of all three methods
plt.figure()
plt.plot(iterative_missing_cells,iterative_total_mean_times, marker = "o", label = "Iterative")
plt.plot(recursive_missing_cells, recursive_mean_times, marker = "o", label = "Recursive")
plt.plot(random_missing_cells, random_total_mean_times, marker = "o", label = "Random")
plt.xlabel("Number of Missing Cells")
plt.ylabel("Total Runtime(ms)")
plt.yscale("log")
plt.title("Comparison of runtimes between different methods")
plt.legend()
plt.savefig("runtime_comparison.png")

##not recorded due to recursion explosion so represent with inf
for i  in range(56,65):
    recursive_mean_times.append(float("inf"))


data = {
    "Missing Cells" : iterative_missing_cells,
    "Iterative Runtime(ms)" : iterative_total_mean_times,
    "Recursive Runtime(ms)" : recursive_mean_times,
    "Random Runtime(ms)" : random_total_mean_times
}

table = pd.DataFrame(data)
table.to_excel("mean_runtime_comparison.xlsx", index=False)

## round results 
table["Missing Cells"]= table["Missing Cells"].astype(str)
table["Iterative Runtime(ms)"] = table["Iterative Runtime(ms)"].round(2)
table["Recursive Runtime(ms)"] = table["Recursive Runtime(ms)"].round(3)
table["Random Runtime(ms)"] = table["Random Runtime(ms)"].round(3)

fig, ax = plt.subplots(figsize=(10, 12))

ax.axis("off")

table_plot = ax.table(
    cellText=table.values,
    colLabels=table.columns,
    loc="center",
    cellLoc="center"
)

columns = ["Iterative Runtime(ms)", "Recursive Runtime(ms)", "Random Runtime(ms)"]

## highlight fastest runtime
for i, row in table.iterrows():
    values = row[columns].astype(float)
    min_column = values.idxmin()

    ## ignore when all values are inf, no fastest time here
    if values[min_column] != float("inf"):
        min_column_id = table.columns.get_loc(min_column)
        min_cell = table_plot[i + 1, min_column_id]  
        min_cell.set_facecolor("#d1e7dd")
        min_cell.set_text_props(color="#0f5132", weight="bold")
    



missing_cells_column = table["Missing Cells"].astype(int)
missing_cells_column_id = table.columns.get_loc("Missing Cells")

for i in range(len(missing_cells_column)):
    value = missing_cells_column[i]
    if 40 <= value and value <= 60:
        cell = table_plot[i + 1, missing_cells_column_id] ## increment by 1 to avoid the header
        cell.set_text_props(color="#842029", weight="bold")

table_plot.auto_set_font_size(False)
table_plot.set_fontsize(10)
table_plot.scale(1.2, 1.5)

plt.savefig("mean_runtime_comparison_table.png", bbox_inches="tight", dpi=300)