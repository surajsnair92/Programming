public class QuickFindUF {
    private int[] id; // id[i] is component id for object i
    public QuickFindUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; ++i)
            id[i] = i;
    }
	
    public boolean connected(int p, int q) {
	    return id[p] == id[q];
    }
	
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; ++i)
            if (id[i] == pid) id[i] = qid;
    }
    public static void main(String...args){
    	QuickFindUF ds = new QuickFindUF(8);
       
        
        ds.union(1, 2);
        ds.union(2, 3);
        ds.union(4, 5);
        ds.union(6, 7);
        ds.union(5, 6);
        ds.union(3, 7);
        
       for(int i =0;i < ds.id.length;i++){
    	   System.out.println(ds.id[i]);
       }
    }
}