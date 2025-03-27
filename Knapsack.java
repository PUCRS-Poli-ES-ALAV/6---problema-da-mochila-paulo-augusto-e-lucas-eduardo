import java.util.Random;

public class Knapsack {
    private static int iterationCount = 0;

    public static int knapsackBruteForce(int[] weights, int[] values, int capacity, int n) {
        iterationCount++;
        if (n == 0 || capacity == 0) {
            return 0;
        }

        if (weights[n - 1] > capacity) {
            return knapsackBruteForce(weights, values, capacity, n - 1);
        } else {
            int includeItem = values[n - 1] + knapsackBruteForce(weights, values, capacity - weights[n - 1], n - 1);
            int excludeItem = knapsackBruteForce(weights, values, capacity, n - 1);
            return Math.max(includeItem, excludeItem);
        }
    }

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

    public static void measureExecutionTime(String methodName, Runnable method) {
        long startTime = System.nanoTime();
        method.run();
        long endTime = System.nanoTime();
        double elapsedTimeMs = (endTime - startTime) / 1_000_000.0;
        System.out.println(String.format("Método: %s, Tempo de execução: %.3f ms", methodName, elapsedTimeMs));
    }

    public static void main(String[] args) {
        int numItems = 10;
        int maxWeight = 15;
        int maxValue = 5;
        int capacity = 100;

        Random random = new Random();
        int[] weights = new int[numItems];
        int[] values = new int[numItems];

        for (int i = 0; i < numItems; i++) {
            weights[i] = random.nextInt(maxWeight) + 1;
            values[i] = random.nextInt(maxValue) + 1;
        }

        iterationCount = 0;
        measureExecutionTime("Brute Force", () -> {
            int maxValueBruteForce = knapsackBruteForce(weights, values, capacity, numItems);
            System.out.println("Brute Force - Valor máximo que pode ser carregado: " + maxValueBruteForce);
            System.out.println("Brute Force - Número de iterações: " + iterationCount);
        });

        iterationCount = 0;
        measureExecutionTime("Divide and Conquer", () -> {
            int maxValueDivideAndConquer = knapsackDivideAndConquer(weights, values, capacity, numItems);
            System.out.println("Divide and Conquer - Valor máximo que pode ser carregado: " + maxValueDivideAndConquer);
            System.out.println("Divide and Conquer - Número de iterações: " + iterationCount);
        });
    }
}