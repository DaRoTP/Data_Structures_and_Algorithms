public class Knuth_Morris_Pratt {
    public String pattern;
    public String text;

    public Knuth_Morris_Pratt(String pattern, String text) {
        this.pattern = pattern;
        this.text = text;
    }

    void computeLPSArray(int M, int lps[])
    {
        int len = 0;
        int i = 1;
        lps[0] = 0;

        while (i < M) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            }
            else {
                if (len != 0) {
                    len = lps[len - 1];
                }
                else {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

    void KMPSearch()
    {
        int M = pattern.length();
        int N = text.length();

        int lps[] = new int[M];
        int j = 0;

        computeLPSArray(M, lps);

        int i = 0;
        while (i < N) {
            if (pattern.charAt(j) == text.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                System.out.println("Pattern found at [" + (i - j + 1)+"]");
                j = lps[j - 1];
            }

            else if (i < N && pattern.charAt(j) != text.charAt(i)) {

                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
    }


}