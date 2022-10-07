import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Homeworks {
public static void main(String[] args) throws Exception {
        
}
        
  public static void top3(String reg) throws Exception {
   File file = new File("inp.txt");
        StringBuilder str = new StringBuilder();
        HashMap<String, Integer> map = new HashMap<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                str.append(scanner.nextLine());
            }
            String[] arr = str.toString().split(reg);
            int[] carr = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                for (String s : arr) {
                    if (arr[i].equals(s)) {
                        carr[i]++;
                    }
                }
                map.put(arr[i], carr[i]);
            }
            int[] topc = new int[3];
            String[] tops = {"", "", ""};
            for (int i = 0; i < topc.length; i++) {
                for (Map.Entry<String, Integer> en : map.entrySet()) {
                    if (en.getValue() > topc[i]) {
                        if (i > 0 && tops[i - 1].equals(en.getKey())) {
                        } else if (i > 1 && tops[i - 2].equals(en.getKey())) {
                        } else {
                            topc[i] = en.getValue();
                            tops[i] = en.getKey();
                        }
                    }
                }
            }
            for (String t : tops) print(t);
        }
  }
public static void timer() {
int s = 10; 
 int m = 0; 
 int h = 0; 
 for(int i = 0;i<1000000;i++) { 
 s--; 
 if (s == 0) { 
 if (m>0) { 
 m--; 
 s = 60; 
 } 
 } 
 if (m == 0) { 
 if (h>0) { 
 h--; 
 m = 60; 
 } 
 } 
 print(h+":"+m+":"+s); 
 Thread.sleep(1000); 
 if (h == 0 && m == 0 && h == 0) { 
 print("Time!!!"); 
 return; 
 } 
 }
}
    private static void print(Object s) {
        System.out.println(s);
    } 
  }
