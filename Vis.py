import matplotlib.pyplot as plt

# 1k
# dijkstra = [0, 0, 0, 1, 3, 11]
# bf = [14, 29, 54, 94, 176, 369]
# bfneg = [24, 63, 106, 215, 402, 827]
# edges = [2500, 5000, 10_000, 20_000, 40_000, 80_000]

# 5k
dijkstra = [1, 2, 5, 9]
bf = [464, 761, 1484, 3297]
bfneg = [919, 1722, 3031, 5850]
edges = [10_000, 20_000, 40_000, 80_000]

plt.plot(edges, dijkstra, label="Dijkstra")
plt.plot(edges, bf, label="Bellman-Ford")
plt.plot(edges, bfneg, label="Bellman-Ford with negative edge weights")


plt.title("5 000 vertices")
plt.xlabel("Edges")
plt.ylabel("Time (ms)")
plt.legend()
plt.show()