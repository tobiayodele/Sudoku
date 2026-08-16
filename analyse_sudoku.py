import  pandas as pd
import matplotlib.pyplot as plt
import statistics

data = pd.read_csv("sudoku_experiment_1000.csv")

missing_cells =[]
mean_times = []
median_times = []
percentage_chance_of_unique_solution = []



for number_of_missing_cells, group in data.groupby("missing_cells"):
    total = 0
    count = 0 
    minimum = None
    maximum = None

    for solution_time in group["total_time_ns"]:
        total += solution_time
        count += 1

        if minimum == None or solution_time < minimum : 
            minimum = solution_time 

        if maximum == None or solution_time > maximum :
            maximum = solution_time

    mean = (total / count) /1e6 
    median = statistics.median(group["total_time_ns"]) /1e6
    print (number_of_missing_cells, minimum, maximum, mean)


    unique_count = 0
    for unique_solution_count in group["solution_count"]:
        if unique_solution_count == 1:
            unique_count += 1

    percentage = (unique_count / len(group) ) * 100
    print(number_of_missing_cells, unique_count)

    missing_cells.append(number_of_missing_cells)
    mean_times.append(mean)
    median_times.append(median)
    percentage_chance_of_unique_solution.append(percentage)

plt.figure()
plt.plot(missing_cells,percentage_chance_of_unique_solution, marker= "o")
plt.xlabel("Number of Missing Cells")
plt.ylabel("Percentage with Unique Solution(%)")
plt.title("Effect of Missing Cells on the Probability of a Unique Solution")
plt.savefig("percentage_relationship.png")

plt.figure()
plt.plot(missing_cells,mean_times, marker = "o")
plt.plot(missing_cells, median_times, marker = "o")
plt.xlabel("Number of Mssing Cells")
plt.ylabel("Total Runtime(ms)")
plt.yscale("log")

plt.title("Relationship between Missing Cells and Runtiem")
plt.savefig("runtime_relationship.png")