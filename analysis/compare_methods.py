import  pandas as pd
import matplotlib.pyplot as plt


iterative_data = pd.read_csv("data/Iterative/iterative_sudoku_experiment_1000.csv")
recursive_data = pd.read_csv("data/Recursive/recursive_sudoku_experiment_1000.csv")
random_data = pd.read_csv("data/Random/random_sudoku_experiment_1000.csv")


def calculate_single_mean_times(data):
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
        ## can not divide by 0 so infinity to show it would take infinitely long to generate
        ## a valid solution
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

