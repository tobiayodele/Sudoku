import  pandas as pd
import matplotlib.pyplot as plt
import statistics

data = pd.read_csv("data/Recursive/recursive_sudoku_experiment_1000.csv")

missing_cells =[]
mean_times = []
median_times = []


for number_of_missing_cells, group in data.groupby("missing_cells"):
    total = 0
    count = 0 
    minimum = None
    maximum = None

    for solution_time in group["total_time_ns"]:
        total += solution_time
        count += 1

        if minimum is None or solution_time < minimum :
            minimum = solution_time 

        if maximum is None or solution_time > maximum :
            maximum = solution_time

    mean = (total / count) /1e6 
    median = statistics.median(group["total_time_ns"]) /1e6
    print (number_of_missing_cells, minimum, maximum, mean)


    missing_cells.append(number_of_missing_cells)
    mean_times.append(mean)
    median_times.append(median)



plt.figure()
plt.plot(missing_cells,mean_times, marker = "o")
plt.plot(missing_cells, median_times, marker = "o")
plt.xlabel("Number of Missing Cells")
plt.ylabel("Total Runtime(ms)")
plt.yscale("log")

plt.title("Relationship between Missing Cells and Runtime")
plt.savefig("runtime_relationship_recursive.png")


data = {
    "Missing Cells" : missing_cells,
    "Median Runtime(ms)" : median_times,
    "Mean Runtime(ms)" : mean_times
}

table = pd.DataFrame(data)
table.to_excel("sudoku_results_recursive.xlsx", index=False)

table["Missing Cells"]= table["Missing Cells"].astype(str)
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

plt.savefig("sudoku_results_recursive.png", bbox_inches="tight", dpi=300)
