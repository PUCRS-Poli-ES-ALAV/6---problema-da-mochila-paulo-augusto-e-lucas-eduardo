public class Fibonacci {
    private static int recCalls = 0;
    private static int iterIterations = 0;
    private static int memoCalls = 0;

    public static int fiboRec(int n) {
        recCalls++;
        if (n <= 1) {
            return n;
        }
        return fiboRec(n - 1) + fiboRec(n - 2);
    }

    public static int fiboIter(int n) {
        iterIterations = 0;
        if (n <= 1) {
            return n;
        }
        int[] f = new int[n + 1];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i <= n; i++) {
            iterIterations++;
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[n];
    }

    public static int memoizedFibo(int n) {
        memoCalls = 0;
        int[] memo = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            memo[i] = -1;
        }
        return lookupFibo(memo, n);
    }

    private static int lookupFibo(int[] memo, int n) {
        memoCalls++;
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
        long startTime = System.nanoTime();
        method.run();
        long endTime = System.nanoTime();
        double elapsedTimeMs = (endTime - startTime) / 1_000_000.0;
        System.out.println(String.format("Método: %s, Valor de n: %d, Tempo de execução: %.3f ms", methodName, n, elapsedTimeMs));
    }

    public static void main(String[] args) {
        int[] testValues = { 4, 8, 16, 32 };

        System.out.println("Fibonacci Recursivo:");
        for (int n : testValues) {
            recCalls = 0;
            measureExecutionTime("Fibonacci Recursivo", n, () -> System.out.println("Resultado: " + fiboRec(n)));
            System.out.println("Chamadas no método recursivo: " + recCalls);
        }

        System.out.println("\nFibonacci (Iterativo):");
        for (int n : testValues) {
            measureExecutionTime("Fibonacci Iterativo", n, () -> System.out.println("Resultado: " + fiboIter(n)));
            System.out.println("Chamadas no método iterativo: " + iterIterations);
        }

        System.out.println("\nFibonacci Memoização:");
        for (int n : testValues) {
            measureExecutionTime("FIbonacci Memoizado", n, () -> System.out.println("Resultado: " + memoizedFibo(n)));
            System.out.println("Chamadas no método memoizado: " + memoCalls);
        }
    }
}