

public class Pratice11{
    public static void main(String[] args) {
        // calc1();
        // calc3(toint(args[0]), toint(args[1]), toint(args[2]));
    }
    public static int toint(String s){
        return Integer.parseInt(s);
    }
    public static void calc1(){
        System.out.println((0 + 15) / 2); // 7
        System.out.println(2.0e-6 * 100000000.1); // 
        System.out.println(true && false || true && true);
        System.out.println((1 + 2.236)/2);
    }
    public static void calc3(int x, int y, int z){
        if (x == y && x == z && y == z){
            System.out.println("equal");
        }
        else{
            System.out.println("not equal");
        }
    }
    public static void calc4(){
        
    }
}