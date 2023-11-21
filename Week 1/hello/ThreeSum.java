public class ThreeSum {

    public int threeSum(int[] numbers) {
        // 对数组进行排序，此处假设 numbers 是一个递增序的数组
        int count = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                int lo = j + 1;
                int hi = numbers.length;
                while (lo <= hi) {
                    int mid = lo + (hi - lo) / 2;
                    if (numbers[mid] > (-i + -j)) {
                        lo = mid + 1;
                    }
                    else if (numbers[mid] < (-i + -j)) {
                        hi = mid - 1;
                    }
                    else {
                        if (i < j && j < mid) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {

    }
}
