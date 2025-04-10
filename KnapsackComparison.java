public class KnapsackComparison {
    private static int iterationCount = 0;

    public static int knapsackDivideAndConquer(int[] weights, int[] values, int capacity, int n) {
        iterationCount++;
        if (n == 0 || capacity == 0) {
            return 0;
        }

        if (weights[n - 1] > capacity) {
            return knapsackDivideAndConquer(weights, values, capacity, n - 1);
        } else {
            int includeItem = values[n - 1]
                    + knapsackDivideAndConquer(weights, values, capacity - weights[n - 1], n - 1);
            int excludeItem = knapsackDivideAndConquer(weights, values, capacity, n - 1);
            return Math.max(includeItem, excludeItem);
        }
    }

    public static int knapsackDynamicProgramming(int N, int C, int[][] items) {
        int[][] maxTab = new int[N + 1][C + 1];
        iterationCount = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= C; j++) {
                iterationCount++;
                if (items[i][0] <= j) {
                    maxTab[i][j] = Math.max(maxTab[i - 1][j], items[i][1] + maxTab[i - 1][j - items[i][0]]);
                } else {
                    maxTab[i][j] = maxTab[i - 1][j];
                }
            }
        }

        return maxTab[N][C];
    }

    public static void measureExecutionTime(String methodName, Runnable method) {
        long startTime = System.nanoTime();
        method.run();
        long endTime = System.nanoTime();
        double elapsedTimeMs = (endTime - startTime) / 1_000_000.0;
        System.out.println(String.format(
                "Método: %s, Tempo de execução: %.3f ms, Iterações: %d, Instruções: %d",
                methodName, elapsedTimeMs, iterationCount, iterationCount));
    }

    public static void main(String[] args) {
        int capacity = 7;
        int[][] items = {
            {0, 0},    // Índice 0 (não utilizado)
            {5, 2},    // Item 1: peso = 5, valor = 2
            {2, 4},    // Item 2: peso = 2, valor = 4
            {2, 2},    // Item 3: peso = 2, valor = 2
            {1, 3}     // Item 4: peso = 1, valor = 3
        };

        int[] weights = {5, 2, 2, 1};
        int[] values = {2, 4, 2, 3};
        int numItems = weights.length;

        iterationCount = 0;
        measureExecutionTime("Divide and Conquer", () -> {
            int maxValueDivideAndConquer = knapsackDivideAndConquer(weights, values, capacity, numItems);
            System.out.println("Divide and Conquer - Valor máximo que pode ser carregado: " + maxValueDivideAndConquer);
        });

        iterationCount = 0;
        measureExecutionTime("Dynamic Programming", () -> {
            int maxValueDynamicProgramming = knapsackDynamicProgramming(numItems, capacity, items);
            System.out.println("Dynamic Programming - Valor máximo que pode ser carregado: " + maxValueDynamicProgramming);
        });
    }
}