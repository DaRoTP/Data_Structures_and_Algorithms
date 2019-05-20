public class Naive {
    public String pattern;
    public String text;

    public Naive(String pattern, String text) {
        this.pattern = pattern;
        this.text = text;
    }

    public void find_pattern_Naive(){
        int M = pattern.length();
        int N = text.length();
        for (int i = 0; i <= N - M; i++) {
            int j;
            for (j = 0; j < M; j++)
                if (text.charAt(i + j) != pattern.charAt(j))
                    break;

            if (j == M) // if loop went through whole pattern print that pattern is found
                System.out.println("Pattern found at [" + (i + 1)+"]");
        }
    }
}