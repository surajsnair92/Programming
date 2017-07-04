import java.util.HashMap;
import java.util.Iterator;

public class ques3 {
	public static void main(String...args){
		  int mat[][] = new int[][]{ {1, 2, 3, 4, 5},
                  {2, 4, 5, 8, 10},
                  {3, 5, 7, 9, 11},
                  {1, 3, 5, 7, 9},
                };
            
                HashMap<Integer, Integer> hm = new HashMap<Integer,Integer>();
                for(int i =0;i<4;i++){
                	for(int j=0;j<5;j++){
                		if(hm.containsKey(mat[i][j])){
                			hm.put(mat[i][j], hm.get(mat[i][j])+1);
                		}
                		else{
                			hm.put(mat[i][j], 0);
                		}
                	}
                }	
                	
	}
}
