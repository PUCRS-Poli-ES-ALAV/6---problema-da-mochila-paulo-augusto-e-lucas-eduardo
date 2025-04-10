public class EditDistance {
    public static int distEdProgDina(String A, String B) {
        int m = A.length();
        int n = B.length();
        int[][] matriz = new int[m + 1][n + 1];
        int iterations = 0;

        for (int i = 1; i <= m; i++) {
            matriz[i][0] = matriz[i - 1][0] + 1;
            iterations++;
        }
        for (int j = 1; j <= n; j++) {
            matriz[0][j] = matriz[0][j - 1] + 1;
            iterations++;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int custoExtra = (A.charAt(i - 1) == B.charAt(j - 1)) ? 0 : 1;
                matriz[i][j] = Math.min(
                        matriz[i - 1][j] + 1,
                        Math.min(
                                matriz[i][j - 1] + 1,
                                matriz[i - 1][j - 1] + custoExtra));
                iterations++;
            }
        }

        System.out.println("Programação Dinâmica - Distância de Edição: " + matriz[m][n]);
        System.out.println("Programação Dinâmica - Número de Iterações: " + iterations);
        return matriz[m][n];
    }

    public static int distEdDivConq(String A, String B, int[] iterations) {
        if (A.isEmpty()) {
            iterations[0] += B.length();
            return B.length();
        }
        if (B.isEmpty()) {
            iterations[0] += A.length();
            return A.length();
        }

        iterations[0]++;

        if (A.charAt(0) == B.charAt(0)) {
            return distEdDivConq(A.substring(1), B.substring(1), iterations);
        }

        int inserir = distEdDivConq(A, B.substring(1), iterations);
        int remover = distEdDivConq(A.substring(1), B, iterations);
        int substituir = distEdDivConq(A.substring(1), B.substring(1), iterations);

        return 1 + Math.min(inserir, Math.min(remover, substituir));
    }

    public static int distEdForcaBruta(String A, String B, int[] iterations) {
        if (A.isEmpty()) {
            iterations[0] += B.length();
            return B.length();
        }
        if (B.isEmpty()) {
            iterations[0] += A.length();
            return A.length();
        }

        iterations[0]++;
        if (A.charAt(0) == B.charAt(0)) {
            return distEdForcaBruta(A.substring(1), B.substring(1), iterations);
        }

        int inserir = distEdForcaBruta(A, B.substring(1), iterations);
        int remover = distEdForcaBruta(A.substring(1), B, iterations);
        int substituir = distEdForcaBruta(A.substring(1), B.substring(1), iterations);

        return 1 + Math.min(inserir, Math.min(remover, substituir));
    }

    public static void main(String[] args) {
        String s1 = "Casablanca";
        String s2 = "Portentoso";

        distEdProgDina(s1, s2);

        int[] iterationsFBruta = { 0 };
        int resultadoBruto = distEdForcaBruta(s1, s2, iterationsFBruta);
        System.out.println("Força Bruta - Distância de Edição: " + resultadoBruto);
        System.out.println("Força Bruta - Número de Iterações: " + iterationsFBruta[0]);

        int[] iterationsDivConq = { 0 };
        int resultadoDivConq = distEdDivConq(s1, s2, iterationsDivConq);
        System.out.println("Divisão e Conquista - Distância de Edição: " + resultadoDivConq);
        System.out.println("Divisão e Conquista - Número de Iterações: " + iterationsDivConq[0]);

        s1 = "Maven, a Yiddish word meaning accumulator of knowledge, began as an attempt to " +
                "simplify the build processes in the Jakarta Turbine project. There were several " +
                "projects, each with their own Ant build files, that were all slightly different. " +
                "JARs were checked into CVS. We wanted a standard way to build the projects, a clear " +
                "definition of what the project consisted of, an easy way to publish project information " +
                "and a way to share JARs across several projects. The result is a tool that can now be " +
                "used for building and managing any Java-based project. We hope that we have created " +
                "something that will make the day-to-day work of Java developers easier and generally help " +
                "with the comprehension of any Java-based project.";
        s2 = "This post is not about deep learning. But it could be might as well. This is the power of " +
                "kernels. They are universally applicable in any machine learning algorithm. Why you might " +
                "ask? I am going to try to answer this question in this article. " +
                "Go to the profile of Marin Vlastelica Pogančić " +
                "Marin Vlastelica Pogančić Jun";

        distEdProgDina(s1, s2);

        iterationsFBruta[0] = 0;
        resultadoBruto = distEdForcaBruta(s1, s2, iterationsFBruta);
        System.out.println("Força Bruta - Distância de Edição (Strings Grandes): " + resultadoBruto);
        System.out.println("Força Bruta - Número de Iterações (Strings Grandes): " + iterationsFBruta[0]);

        iterationsDivConq[0] = 0;
        resultadoDivConq = distEdDivConq(s1, s2, iterationsDivConq);
        System.out.println("Divisão e Conquista - Distância de Edição (Strings Grandes): " + resultadoDivConq);
        System.out.println("Divisão e Conquista - Número de Iterações (Strings Grandes): " + iterationsDivConq[0]);
    }
}