import  pandas as pd
import matplotlib.pyplot as plt
import statistics

data = pd.read_csv("data/sudoku_experiment_1000.csv")

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


data = {
    "Missing Cells" : missing_cells,
    "Percentage for Unique Solution" : percentage_chance_of_unique_solution,
    "Median Runtime(ms)" : median_times,
    "Mean Runtime(ms)" : mean_times
}

table = pd.DataFrame(data)
table.to_excel("sudoku_results.xlsx", index=False)

table["Missing Cells"]= table["Missing Cells"].astype(str)
table["Percentage for Unique Solution"] = table["Percentage for Unique Solution"].round(2)
table["Median Runtime(ms)"] = table["Median Runtime(ms)"].round(3)
table["Mean Runtime(ms)"] = table["Mean Runtime(ms)"].round(3)

fig, ax = plt.subplots(figsize=(10, 12))

ax.axis("off")

table_plot = ax.table(
    cellText=table.values,
    colLabels=table.columns,
    loc="center",
    cellLoc="center"
)

table_plot.auto_set_font_size(False)
table_plot.set_fontsize(10)
table_plot.scale(1.2, 1.5)

plt.savefig("sudoku_results.png", bbox_inches="tight", dpi=300)
