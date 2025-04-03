public class KnapsackDP {
    private static int iterationCount = 0;

    public static int backPackPD(int N, int C, int[][] items) {
        int[][] maxTab = new int[N + 1][C + 1];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= C; j++) {
                maxTab[i][j] = 0;
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= C; j++) {
                iterationCount++;
                if (items[i][0] <= j) {
                    maxTab[i][j] = Math.max(maxTab[i - 1][j],
                            items[i][1] + maxTab[i - 1][j - items[i][0]]);
                } else {
                    maxTab[i][j] = maxTab[i - 1][j];
                }
            }
        }

        return maxTab[N][C];
    }

    public static void main(String[] args) {
        int N = 5;
        int C = 15;

        int[][] items = {
                { 0, 0 },
                { 12, 4 },
                { 2, 2 },
                { 1, 1 },
                { 4, 10 },
                { 1, 2 }
        };

        iterationCount = 0;

        long startTime = System.nanoTime();
        int maxValue = backPackPD(N, C, items);
        long endTime = System.nanoTime();

        double runtime = (endTime - startTime) / 1_000_000.0;

        System.out.println("Valor máximo que pode ser carregado: " + maxValue);
        System.out.println("Número de iterações: " + iterationCount);
        System.out.println(String.format("Tempo de execução: %.3f ms", runtime));
    }
}