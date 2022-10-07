import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Alarm {
    public static void main(String[] args) throws InterruptedException {
Scanner scanner = new Scanner(System.in);
alarm(scanner.nextLine());
    }
    public static void alarm(String time) throws InterruptedException {
        String[] tr = time.split(":");
        int ha = Integer.parseInt(tr[0]);
        int ma = Integer.parseInt(tr[1]);
        print(ma);
        if(tr.length != 2 || ha < 0 || ha > 23 || ma < 0 || ma > 59) {
            print("Type correct time!\nExample: \"8:30\"");
            return;
        }
        int ctm = (int) System.currentTimeMillis();
        Calendar calendar = new GregorianCalendar();
        int ch = calendar.getTime().getHours();
        int h = 0;
        int m = 0;
        int cm = calendar.getTime().getMinutes();
        int s = 60-calendar.getTime().getSeconds();
       /* if (ha == ch && ma<cm) {
            h = 24-ch+ha;
        }*/
        if (ha <= ch) {
            h = 24-ch+ha;
            m = 60-cm+ma;
        }
        else {
            h = ha-ch;
        }
        if (h > 0) m+=ma;
        else m = ma-cm;
        if (m == 1) m--;
        for(int i = 0;i<1000000;i++) {
            if (s > 0) s--;
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
            if (h == 0 && m == 0 && s == 0) {
                print("Time!!!");
                return;
            }
        }
    }
    public static void print(Object str) {
        System.out.println(str);
    }
}
