import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * 通过蒙特卡罗仿真估计系统渗漏阈值。
 */
public class Percolation {
    // 正方形网格的长宽
    private final int gridSize;
    // 正方形网格
    private final boolean[][] sites;
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private WeightedQuickUnionUF weightedQuickUnionUFFullSite;
    private int openSiteNum = 0;
    private final int[] dx = { -1, 0, 0, 1 };
    private final int[] dy = { 0, -1, 1, 0 };

    // creates gridSize-by-gridSize grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("gridSize 不能小于等于 0");
        }
        this.gridSize = n;
        sites = new boolean[gridSize][gridSize];
        // 除了 gridSize^2 个网格节点外，顶部和底部分别各有一个虚拟节点，索引分别是 gridSize^2 和 gridSize^2 + 1
        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
        weightedQuickUnionUFFullSite = new WeightedQuickUnionUF(n * n + 2);
        for (int j = 0; j < n; j++) {
            // 将虚拟的 top site 和首行的 site 连通，无论这些 site 是 blocked 还是 opened 的。
            weightedQuickUnionUF.union(n * n, j);
            // 底部的虚拟节点和底部的 site 连通
            weightedQuickUnionUF.union((n * n) + 1, (n - 1) * n + j);

            weightedQuickUnionUFFullSite.union(n * n, j);
        }
    }

    /**
     * 判断指定坐标是否在网格中
     * @param row 行坐标，从 1 开始
     * @param col 列坐标，从 1 开始
     * @return 返回真，如果坐标在网格中
     */
    private boolean isInGrid(int row, int col) {
        return row >= 1 && row <= gridSize && col >= 1 && col <= gridSize;
    }

    /**
     * 打开指定坐标的 site
     * @param row 行坐标
     * @param col 列坐标
     */
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            sites[row - 1][col - 1] = true;
            openSiteNum += 1;
        }
        // 打开 site 要将该 site 和周围已打开的 site 连通起来
        connectNeighbours(row, col);
    }

    /**
     * 判断指定坐标对应的 site 是否打开
     * @param row 行坐标，从 1 开始
     * @param col 列坐标，从 1 开始
     * @return 打开则返回真
     */
    public boolean isOpen(int row, int col) {
        if (!isInGrid(row, col)) {
            throw new IllegalArgumentException("index " + row + " is not between 1 and " + gridSize);
        }
        return sites[row - 1][col - 1];
    }

    /**
     * 将指定坐标的 site 和其周围已打开的 site 相连
     * @param row 行坐标，从 1 开始
     * @param col 列坐标，从 1 开始
     */
    private void connectNeighbours(int row, int col) {
        // 将当前 site 的二维坐标转为对应的一维坐标
        int p = (row - 1) * gridSize + (col - 1);
        int newRow, newCol;
        for (int i = 0; i < 4; i++) {
            newRow = row + dx[i];
            newCol = col + dy[i];
            if (isOpen(newRow, newCol)) {
                weightedQuickUnionUF.union(p, (newRow - 1) * gridSize + newCol - 1);
                weightedQuickUnionUFFullSite.union(p, (newRow - 1) * gridSize + newCol - 1);
            }
        }
    }

    /**
     * 判断 site 是否 full
     * @param row site 的行坐标，从 1 开始
     * @param col site 的列坐标，从 1 开始
     * @return 是 full 则返回真
     */
    public boolean isFull(int row, int col) {
        /*
        A full site is an open site that can be connected to an open site in the
        top row via a chain of neighboring (left, right, up, down) open sites.
         */
        if (!isOpen(row, col)) {
            return false;
        }
        int p = (row - 1) * gridSize + col - 1;
        return weightedQuickUnionUFFullSite.find(p) == weightedQuickUnionUFFullSite.find(gridSize * gridSize);
    }

    public int numberOfOpenSites() {
        return openSiteNum;
    }

    /**
     * 判断系统是否渗漏
     * @return
     */
    public boolean percolates() {
        // StdOut.println(quickFindUF.find(gridSize * gridSize + 1));
        if (openSiteNum == 0) {
            return true;
        }
        // 当顶部和底部的两虚拟节点连通时则系统是渗滤的
        return weightedQuickUnionUF.find(gridSize * gridSize) != weightedQuickUnionUF.find((gridSize * gridSize) + 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 5;
        Percolation percolation = new Percolation(n);
        while (percolation.percolates()) {
            // 随机获取一个整数
            int randomNum = StdRandom.uniformInt(n * n);
            int row = randomNum / n + 1;
            int col = randomNum % n + 1;
            // StdOut.println("row " + row + ", col " + col);
            // StdOut.println(percolation.numberOfOpenSites());
            percolation.open(row, col);
        }
    }
}
