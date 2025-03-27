public class Fibonacci {
    public static int fiboRec(int n) {
        if (n <= 1) {
            return n;
        }
        return fiboRec(n - 1) + fiboRec(n - 2);
    }

    // FIBO (Iterativo)
    public static int fiboIter(int n) {
        if (n <= 1) {
            return n;
        }
        int[] f = new int[n + 1];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[n];
    }

    // MEMOIZED-FIBO (Com Memoization)
    public static int memoizedFibo(int n) {
        int[] memo = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            memo[i] = -1;
        }
        return lookupFibo(memo, n);
    }

    private static int lookupFibo(int[] memo, int n) {
        if (memo[n] >= 0) {
            return memo[n];
        }
        if (n <= 1) {
            memo[n] = n;
        } else {
            memo[n] = lookupFibo(memo, n - 1) + lookupFibo(memo, n - 2);
        }
        return memo[n];
    }

    public static void measureExecutionTime(String methodName, int n, Runnable method) {
        long startTime = System.nanoTime(); // Alterado para maior precisão
        method.run();
        long endTime = System.nanoTime();
        double elapsedTimeMs = (endTime - startTime) / 1_000_000.0; // Convertendo para milissegundos
        System.out.println(String.format("Método: %s, n=%d, Tempo de execução: %.3f ms", methodName, n, elapsedTimeMs));
    }

    public static void main(String[] args) {
        int[] testValues = { 4, 8, 16, 32, 48 };
        int[] extendedTestValues = { 128, 1000, 10000 };

        System.out.println("Fibonacci Recursivo:");
        for (int n : testValues) {
            measureExecutionTime("FiboRec", n, () -> System.out.println("Resultado: " + fiboRec(n)));
        }

        System.out.println("\nFibonacci (Iterativo):");
        for (int n : testValues) {
            measureExecutionTime("FiboIter", n, () -> System.out.println("Resultado: " + fiboIter(n)));
        }
        for (int n : extendedTestValues) {
            measureExecutionTime("FiboIter", n, () -> System.out.println("Resultado: " + fiboIter(n)));
        }

        System.out.println("\nFibonacci Memoização:");
        for (int n : testValues) {
            measureExecutionTime("MemoizedFibo", n, () -> System.out.println("Resultado: " + memoizedFibo(n)));
        }
        for (int n : extendedTestValues) {
            measureExecutionTime("MemoizedFibo", n, () -> System.out.println("Resultado: " + memoizedFibo(n)));
        }
    }
}