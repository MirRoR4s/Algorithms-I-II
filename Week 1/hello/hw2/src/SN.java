public class SN {
    private int[] friendShip;
    private int count;
    private int time;

    public SN(int n) {
        friendShip = new int[n];
        for (int i = 0; i < n; i++) {
            friendShip[i] = i;
        }
    }

    public void union(int p, int q, int time) {
        if (friendShip[p] == friendShip[q]) {
            return;
        }
        for (int i = 0; i < friendShip.length; i++) {
            if (friendShip[i] == friendShip[p]) {
                friendShip[i] = friendShip[q];
            }
        }
        count--;
        if (count == 0) {
            this.time = time;
        }
    }

    public int getETime() {
        return time;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        SN sn = new SN(n);
        // read timestamps from files ??


    }
}
