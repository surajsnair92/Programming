public class EditDist {

  private int Minimum (int a, int b, int c) {
  int mi;

    mi = a;
    if (b < mi) {
      mi = b;
    }
    if (c < mi) {
      mi = c;
    }
    return mi;

  }

  public int LD (String s, String t) {
  int d[][]; 
  int n; 
  int m; 
  int i; 
  int j; 
  char s_i; 
  char t_j; 
  int cost; 

    // Step 1

    n = s.length ();
    m = t.length ();
    if (n == 0) {
      return m;
    }
    if (m == 0) {
      return n;
    }
    d = new int[n+1][m+1];

    // Step 2

    for (i = 0; i <= n; i++) {
      d[i][0] = i*3;
    }

    for (j = 0; j <= m; j++) {
      d[0][j] = j*4;
    }

    // Step 3

    for (i = 1; i <= n; i++) {

      s_i = s.charAt (i - 1);

      // Step 4

      for (j = 1; j <= m; j++) {

        t_j = t.charAt (j - 1);

        // Step 5

        if (s_i == t_j) {
          cost = 0;
        }
        else {
          cost = 5;
        }

        // Step 6

        d[i][j] = Minimum (d[i-1][j] + 3, d[i][j-1] +4, d[i-1][j-1] + cost);

      }

    }

    // Step 7

    return d[n][m];

  }
  public static void main(String...args){
	  EditDist e = new EditDist();
	  System.out.println(e.LD("a", "b"));
  }

}