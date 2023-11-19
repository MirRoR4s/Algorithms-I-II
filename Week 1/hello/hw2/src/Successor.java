import java.util.ArrayList;
import java.util.Collections;

public class Successor {
    private ArrayList<Integer> numbers;

    public Successor(int n) {
        numbers = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            numbers.add(i);
        }
        Collections.sort(numbers);
    }

    public int remove(int x) {
        int index = numbers.indexOf(x) + 1;
        int ans = numbers.get(index);
        numbers.remove(x);
        return ans;
    }


    public static void main(String[] args) {

    }
}
