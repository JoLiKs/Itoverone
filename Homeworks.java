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
    private static void print(Object s) {
        System.out.println(s);
    } 
  }
