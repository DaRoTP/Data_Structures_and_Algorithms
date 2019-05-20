public class RabinKarp {
    public String pattern;
    public String text;
    public int d;
    public int q;

    public RabinKarp(String pattern, String text) {
        this.pattern = pattern;
        this.text = text;
    }

    public void RKsearch(int q)
    {
        int M = pattern.length();
        int N = text.length();
        int i, j;
        int p = 0;
        int t = 0;
        int h = 1;

        for (i = 0; i < M - 1; i++)
            h = (h * d) % q;

        for (i = 0; i < M; i++) {
            p = (d * p + pattern.charAt(i)) % q;
            t = (d * t + text.charAt(i)) % q;
        }

        for (i = 0; i <= N - M; i++) {
            if (p == t) {
                for (j = 0; j < M; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j))
                        break;
                }
                if (j == M)
                    System.out.println("Pattern found at [" + (i + 1)+"]");
            }
            if (i < N - M) {
                t = (d * (t - text.charAt(i) * h) + text.charAt(i + M)) % q;

                if (t < 0)
                    t = (t + q);
            }
        }
    }
}