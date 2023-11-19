import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * 基于并查集问题实现渗滤系统阈值求解。
 * 不断地在一个 n × n 的网格上均匀随机开放一个 site，然后判断系统是否渗滤，如果渗滤那么此时阈值的估计值就是总共开放的 site 除以总的 site。
 * 解决渗滤系统的关键在于它和并查集问题的关联是什么？
 * 在二维网格和 QuickFindUF 的 id 数组之间建立映射，完成二维数组到一维数组的转化。坐标 (i, j) 对应 i * n + j
 */
public class Percolation {
    private int n;
    private boolean[][] sites;
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private WeightedQuickUnionUF weightedQuickUnionUFFullSite;
    private int openSiteNum = 0;
    private int[] dx = { -1, 0, 0, 1 };
    private int[] dy = { 0, -1, 1, 0 };

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n 不能小于等于 0");
        }
        this.n = n;
        sites = new boolean[n][n];
        // 除了 n^2 个网格节点外，还有两个虚拟节点，索引是 n^2 和 n^2 + 1
        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
        weightedQuickUnionUFFullSite = new WeightedQuickUnionUF(n * n + 2);
        for (int j = 0; j < n; j++) {
            // 将虚拟的 top site 和首行的 site 连通，无论这些 site 是 blocked 还是 opened 的。
            weightedQuickUnionUF.union(n * n, j);
            weightedQuickUnionUF.union((n * n) + 1, (n - 1) * n + j);
            weightedQuickUnionUFFullSite.union(n * n, j);
        }
    }

    private void validate(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) {
            throw new IllegalArgumentException(
                    "index " + row + " is not between 1 and " + n);
        }
    }

    private boolean isInGrid(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) {
            return false;
        }
        return true;
    }

    private void connectNeighbours(int row, int col) {
        int p = (row - 1) * n + col - 1;
        int newRow, newCol;
        for (int i = 0; i < 4; i++) {
            newRow = row + dx[i];
            newCol = col + dy[i];
            if (isInGrid(newRow, newCol) && isOpen(newRow, newCol)) {
                weightedQuickUnionUF.union(p, (newRow - 1) * n + newCol - 1);
                weightedQuickUnionUFFullSite.union(p, (newRow - 1) * n + newCol - 1);
            }
        }
    }

    // opens the site (row, col) if it is not open already

    /**
     * 根据视频，开放一个 site，就是把这个 site 和它周围的 opened site 连通！这就需要调用 Union 操作
     *
     * @param row 1
     * @param col 1
     */
    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            sites[row - 1][col - 1] = true;
            openSiteNum++;
        }
        connectNeighbours(row, col);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return sites[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        /*
         A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites.
         */
        if (!isOpen(row, col)) {
            return false;
        }
        int p = (row - 1) * n + col - 1;
        return weightedQuickUnionUFFullSite.find(p) == weightedQuickUnionUFFullSite.find(n * n);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSiteNum;
    }

    // does the system percolate?
    public boolean percolates() {
        // StdOut.println(quickFindUF.find(n * n + 1));
        if (openSiteNum == 0) {
            return false;
        }
        return weightedQuickUnionUF.find(n * n) == weightedQuickUnionUF.find((n * n) + 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 5;
        Percolation percolation = new Percolation(n);
        while (!percolation.percolates()) {
            int randomNum = StdRandom.uniformInt(n * n);
            int row = randomNum / n + 1;
            int col = randomNum % n + 1;
            // StdOut.println("row " + row + ", col " + col);
            // StdOut.println(percolation.numberOfOpenSites());
            percolation.open(row, col);
        }
    }
}
