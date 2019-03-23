/*
Zaimplementować algorytm wyszukiwania najłuższego wspólnego podciągu metodą programowania dynamicznego. Zademonstrowć
działanie programu na reprezentatywnych danych testowych.
    Wersja iteracyjna (budowanie tablicy kolejno wierszami) i wydruk jednego najdłuższego wspólnego podciągu
*/
public class Main {

    //Zmienne
    private String x;
    private String y;
    int dlg_x;
    int dlg_y ;

    // -- Tablica Algorytmu LCS
    private int value_Tab[][];
    private String sign_Tab[][];

    //Konstruktor
    public Main(String x, String y) {
        this.x = x;
        this.y = y;
        this.dlg_x = this.x.length();
        this.dlg_y = this.y.length();
        this.value_Tab = new int[dlg_y+1][dlg_x+1];
        this.sign_Tab = new String[dlg_y+1][dlg_x+1];
    }


    // Longest Common Sequence
    private void LCS_length()
    {
        for(int i = 1; i <= dlg_y; i++)
        {
            for(int j = 1; j <= dlg_x; j++)
            {
                if(y.charAt(i-1) == x.charAt(j-1)) // jezeli znak z "x" rowna sie znakowi z "y"
                {
                    value_Tab[i][j] = value_Tab[i - 1][j - 1] + 1;
                    sign_Tab[i][j] = "\\";
                }
                else if( value_Tab[i - 1][j] > value_Tab[i][j - 1] )
                {
                    value_Tab[i][j] = value_Tab[i - 1][j];
                    sign_Tab[i][j] = "|";
                }
                else
                {
                    value_Tab[i][j] = value_Tab[i][j - 1];
                    sign_Tab[i][j] = "-";
                }
            }
        }
    }
    //PRINT LONGEST COMMON SEQUENCE
    public String print_LCS()
    {
        int i = dlg_y;
        int j = dlg_x;
        String Subsequence = "";

        while( sign_Tab[i][j] != null)
        {
            if(sign_Tab[i][j] == "\\")
            {
                Subsequence += x.charAt(j-1);
                i--;
                j--;
            }
            else if(sign_Tab[i][j] == "|")
                i--;
            else
                j--;
        }
        return Subsequence;
    }

    //ODWROCENIE CIAGU
    public static String reverse(String input)
    {
        char[] in = input.toCharArray();
        int begin=0;
        int end=in.length-1;
        char temp;
        while(end>begin){
            temp = in[begin];
            in[begin]=in[end];
            in[end] = temp;
            end--;
            begin++;
        }
        return new String(in);
    }
    public void jj(){
        Integer i = new Integer(20);
        String hex = Integer.toHexString(i);
        System.out.println("Hex value: "+hex);
    }

    public static void main(String[] args)
    {
        Main Ciagi = new Main("abcdaf","acbcf");

       Ciagi.LCS_length();
       System.out.println(Ciagi.reverse(Ciagi.print_LCS()));
       Ciagi.jj();

    }
}
