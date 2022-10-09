public class Car {
    private int age;
    private Double u_engine;
    private String mark;
    private String model;

    public Car(int age, Double u_engine, String mark, String model) {
        this.age = age;
        this.u_engine = u_engine;
        this.mark = mark;
        this.model = model;
    }

    public Double getU_engine() {
        return u_engine;
    }

    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return "Car{" +
                "age=" + age +
                ", u_engine=" + u_engine +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
