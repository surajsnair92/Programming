public class QuickFind {
    private int[] id; // id[i] is component id for object i
    public QuickFind(int N) {
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
    	QuickFind ds = new QuickFind(8);        
    	

        ds.union(1, 2);
        ds.union(2, 3);
        ds.union(4, 5);
        ds.union(6, 7);
        ds.union(5, 6);
        ds.union(3, 7);

        for(int i =0;i<ds.id.length;i++){
        	System.out.println(ds.id[i]);
        }
//        System.out.println(ds.find(1));
//        System.out.println(ds.find(2));
//        System.out.println(ds.find(3));
//        System.out.println(ds.find(4));
//        System.out.println(ds.find(5));
//        System.out.println(ds.find(6));
//        System.out.println(ds.find(7));
	}
}