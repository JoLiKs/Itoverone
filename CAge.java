import java.util.*;
import java.util.stream.Collectors;

public class CAge {
    public static void main(String[] args) throws InterruptedException {
        List<Car> list = new ArrayList<>();
        list.add(new Car(1996, 1.3, "Hyndai", "Accent"));
        list.add(new Car(2005, 1.8, "BMW", "X3"));
        list.add(new Car(1999, 2.5, "Reno", "Laguna"));
        list.add(new Car(2007, 2.0, "Hyndai", "Sonata"));
        list.add(new Car(1997, 1.5, "Lada", "Vesta"));

        List<Integer> list1 = new ArrayList<>();

        Collections.sort(list1);
        Collections.reverse(list1);
        Car car = new Car(2020, 4.4, "Audi", "rs6");
        list.stream().forEach((a) -> a.setAge(a.getCAge()));
        List<Car> finalList = list;
        list.stream().skip(list.size()-1)
                .forEach((a) -> finalList.stream().forEach((b) -> a.setAge(b.getCAge()+a.getAge())));

        print(list);
    }
    public static void print(Object s) {
        System.out.println(s);
    }
}
