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
    

    public static void main(String[] args) {
        int[] weights = { 12, 2, 1, 4, 1 };
        int[] values = { 4, 2, 1, 10, 2 };
        int capacity = 15;
        int n = weights.length;

        iterationCount = 0;

        int maxValue = knapsackBruteForce(weights, values, capacity, n);

        System.out.println("Valor máximo que pode ser carregado: " + maxValue);
        System.out.println("Número de iterações: " + iterationCount);
    }
}