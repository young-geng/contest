/**
 * UVa Problem Set 100.
 * Cached search with Hashtable.
 */



import java.util.*;

public class Main {
    public static HashMap<Integer, Integer> map;


    public static int collaz(int number) {
        if (map.containsKey(number)) {
            return map.get(number);
        }
        int ans = 0;
        if (number % 2 == 0) {
            ans = collaz(number / 2) + 1;
        } else {
            ans = collaz(number * 3 + 1) + 1;
        }
        map.put(number, ans);
        return ans;
    }

    public static int min(int a, int b) {
        if (a > b)
            return b;
        return a;
    }

    public static int max(int a, int b) {
        if (a < b)
            return b;
        return a;
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        map = new HashMap<Integer, Integer>();
        map.put((int)1, (int)1);
        while (cin.hasNextInt()) {
            int max = 0;
            int a = (int)cin.nextInt();
            int b = (int)cin.nextInt();
            for (int i = min(a, b); i <= max(a, b); i++) {
                int temp = collaz(i);
                if (temp > max) {
                    max = temp;
                }
            }
            System.out.println(a + " " + b + " " + max);
        }
    }
}
                
                
        
