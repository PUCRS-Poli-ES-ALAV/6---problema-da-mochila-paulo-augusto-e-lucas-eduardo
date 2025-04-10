import java.math.BigInteger;

public class Fibonacci {
    private static int recCalls = 0;
    private static int iterIterations = 0;
    private static int memoCalls = 0;

    public static BigInteger fiboRec(int n) {
        recCalls++;
        if (n <= 1) {
            return BigInteger.valueOf(n);
        }
        return fiboRec(n - 1).add(fiboRec(n - 2));
    }

    public static BigInteger fiboIter(int n) {
        iterIterations = 0;
        if (n <= 1) {
            return BigInteger.valueOf(n);
        }
        BigInteger[] f = new BigInteger[n + 1];
        f[0] = BigInteger.ZERO;
        f[1] = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            iterIterations++;
            f[i] = f[i - 1].add(f[i - 2]);
        }
        return f[n];
    }

    public static BigInteger memoizedFibo(int n) {
        memoCalls = 0;
        BigInteger[] memo = new BigInteger[n + 1];
        for (int i = 0; i <= n; i++) {
            memo[i] = null;
        }
        return lookupFibo(memo, n);
    }

    private static BigInteger lookupFibo(BigInteger[] memo, int n) {
        memoCalls++;
        if (memo[n] != null) {
            return memo[n];
        }
        if (n <= 1) {
            memo[n] = BigInteger.valueOf(n);
        } else {
            memo[n] = lookupFibo(memo, n - 1).add(lookupFibo(memo, n - 2));
        }
        return memo[n];
    }

    public static void measureExecutionTime(String methodName, int n, Runnable method, int iterations, int instructions) {
        long startTime = System.nanoTime();
        method.run();
        long endTime = System.nanoTime();
        double elapsedTimeMs = (endTime - startTime) / 1_000_000.0;
        System.out.println(String.format(
                "Método: %s, Valor de n: %d, Tempo de execução: %.3f ms, Iterações: %d, Instruções: %d",
                methodName, n, elapsedTimeMs, iterations, instructions));
    }

    public static void main(String[] args) {
        int[] testValues = {4, 8, 16, 32};
        int[] extendedTestValues = {128, 1000, 10000};

        System.out.println("Fibonacci Recursivo:");
        for (int n : testValues) {
            recCalls = 0;
            measureExecutionTime("Fibonacci Recursivo", n, () -> System.out.println("Resultado: " + fiboRec(n)), recCalls, recCalls);
        }

        System.out.println("\nFibonacci (Iterativo):");
        for (int n : testValues) {
            iterIterations = 0;
            BigInteger result = fiboIter(n);
            measureExecutionTime("Fibonacci Iterativo", n, () -> System.out.println("Resultado: " + result), iterIterations, iterIterations);
        }
        for (int n : extendedTestValues) {
            iterIterations = 0;
            BigInteger result = fiboIter(n);
            measureExecutionTime("Fibonacci Iterativo", n, () -> System.out.println("Resultado: " + result), iterIterations, iterIterations);
        }

        System.out.println("\nFibonacci Memoização:");
        for (int n : testValues) {
            memoCalls = 0;
            BigInteger result = memoizedFibo(n);
            measureExecutionTime("Fibonacci Memoizado", n, () -> System.out.println("Resultado: " + result), memoCalls, memoCalls);
        }
        for (int n : extendedTestValues) {
            memoCalls = 0;
            BigInteger result = memoizedFibo(n);
            measureExecutionTime("Fibonacci Memoizado", n, () -> System.out.println("Resultado: " + result), memoCalls, memoCalls);
        }
    }
}