import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    /*
 * Complete the function below.
 */

    static int maxLength(int[] a, int k) {
        int sum;
        int end;
        for (int len = a.length; len > 0; len--) {
            for (int start = 0; start + len <= a.length; start++) {
                sum = 0;
                end = start + len;
                for (int i = start; i < end; i++) {
                    sum += a[i];
                }
                if (sum <= k)
                    return len;
            }
        }
        return 0;

    }

 public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        //final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"));
        int res;
        
        int _a_size = 0;
        _a_size = Integer.parseInt(in.nextLine().trim());
        int[] _a = new int[_a_size];
        int _a_item;
        for(int _a_i = 0; _a_i < _a_size; _a_i++) {
            _a_item = Integer.parseInt(in.nextLine().trim());
            _a[_a_i] = _a_item;
        }
        
        int _k;
        _k = Integer.parseInt(in.nextLine().trim());
        
        res = maxLength(_a, _k);
        bw.write(String.valueOf(res));
        bw.newLine();
        
        bw.close();
    }
}
