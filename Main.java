import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Car> list = new ArrayList<>();
        list.add(new Car(1996, 1.3, "Hyndai", "Accent"));
        list.add(new Car(2005, 1.8, "BMW", "X3"));
        list.add(new Car(1999, 2.5, "Reno", "Laguna"));
        list.add(new Car(2007, 2.0, "Hyndai", "Sonata"));
        list.add(new Car(1997, 1.5, "Lada", "Vesta"));

        list = list.stream().limit(3)
                .sorted(Comparator.comparing(Car::getU_engine))
                .collect(Collectors.toList());
        print(list);
    }
    public static void print(Object s) {
        System.out.println(s);
    }
}
