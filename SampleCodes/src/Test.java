import java.io.*;
import java.util.*;

public class Test {
   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       Long N = scanner.nextLong();
       Long M = scanner.nextLong();
       scanner.nextLine();

       ArrayList<Long> A = new ArrayList<Long>();
       ArrayList<Long> B = new ArrayList<Long>();
       ArrayList<Long> C = new ArrayList<Long>();

       A.add(0L);
       for (String string : scanner.nextLine().split(" ")) {
           A.add(new Long(string));
       }
       B.add(0L);
       for (String string : scanner.nextLine().split(" ")) {
           B.add(new Long(string));
       }
       C.add(0L);
       for (String string : scanner.nextLine().split(" ")) {
           C.add(new Long(string));
       }
   }
}
