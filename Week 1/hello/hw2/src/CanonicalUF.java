public class CanonicalUF {
    private int[] id;

    public CanonicalUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;

    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++)
            if (id[i] == pid)
                id[i] = qid;
    }

    public int find(int p) {
        int max = p;
        int pid = id[p];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                max = Math.max(id[i], max);
            }
        }
        return max;
    }

    public static void main(String[] args) {

    }
}
