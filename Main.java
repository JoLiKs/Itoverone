import java.sql.*;
import java.util.*;

public class Main {
    private static final String url = "jdbc:mysql://localhost/db1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "123123";
    private static Connection con;
    private static Statement statement, statement2, statement3;
    private static int uid = 5;
    public static void main(String[] args) throws Exception {

        con = DriverManager.getConnection(url, user, password);
        statement = con.createStatement();
        statement2 = con.createStatement();
        statement3 = con.createStatement();
        statement.executeUpdate(
        "CREATE TABLE if not exists users"+uid+" " +
                "(id INT not NULL AUTO_INCREMENT, " +
                " username VARCHAR(50), " +
                " lastname VARCHAR (50), " +
                " age INTEGER not NULL, " +
                " sum INTEGER not NULL, " +
                " PRIMARY KEY (id));");
       statement.executeUpdate(
                "CREATE TABLE if not exists managers"+uid+" " +
                        "(id INT not NULL AUTO_INCREMENT, " +
                        " manager_name VARCHAR(50), " +
                        " amount INTEGER not NULL, " +
                        " dept INTEGER not NULL, " +
                        " PRIMARY KEY (id));");
        statement.executeUpdate(
                "CREATE TABLE if not exists client"+uid+" " +
                        "(id int NOT NULL AUTO_INCREMENT, " +
                        " user_id INTEGER, " +
                        " manager_id INTEGER, " +
                        " PRIMARY KEY (id));");
        Scanner scanner = new Scanner(System.in);
        allCommands();
        int command = scanner.nextInt();

        while (command!=0){
            allCommands();
            switch (command){
                case 0:
                    System.exit(0);
                case 1:
                    print("Enter username");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    String lasName = scanner.nextLine();
                    int age = scanner.nextInt();
                    int sum = scanner.nextInt();
                    String sql = setUser(name, lasName, age, sum);
                    statement.executeUpdate(sql);
                    connectUsers();
                    break;
                case 2:
                    print("Enter manager name");
                    scanner.nextLine();
                    String manager_name = scanner.nextLine();
                    int amount = scanner.nextInt();
                    int depth = scanner.nextInt();
                    String msql = setManager(manager_name, depth);
                    statement.executeUpdate(msql);
                    break;
                case 3:
                    Map<Integer, User> map = new HashMap<>();
                    Map<Integer, Manager> map1 = new HashMap<>();
                    map = getAllUsersEntities();
                    map1 = getAllManagersEntities();
                    print("Enter user and manager numbers");
                    int usId, mId;
                    usId = scanner.nextInt();
                    mId = scanner.nextInt();
                    String sql2 = connectUsers(usId, mId, map, map1);
                    statement.executeUpdate(sql2);
                    break;
                case 4:

                    break;
                case 5:
                    Map<User, Manager> usman = getMapConnection();
                    setAmount(usman);
                    break;
            }
            command = scanner.nextInt();
        }
    }

    private static void setAmount(Map<User, Manager> map) throws SQLException, InterruptedException {
        for (Map.Entry<User, Manager> en : map.entrySet()) {
            User user1 = en.getKey();
        }
        Integer amount = 0;
        ResultSet res = statement.executeQuery("SELECT * FROM client"+uid);
        while(res.next()) {
            amount++;
        }

        print(amount);
    }
public static String connectUsers(int u, int m, Map<Integer, User> map1, Map<Integer, Manager> map2) {
        User user1 = map1.get(u);
        Manager manager1 = map2.get(m);
        String sql = "insert into client"+uid+" (user_id, manager_id) values "+"('"+user1.getId()+"', '"+manager1.getId()+"');";

        return sql;
}
    public static void connectUsers() throws SQLException {
        ResultSet rsm = statement.executeQuery("SELECT * FROM managers"+uid+" ORDER BY id DESC LIMIT 1");
        ResultSet rsu = statement2.executeQuery("SELECT * FROM users"+uid+" ORDER BY id DESC LIMIT 1");
       if (rsm.next() && rsu.next()) statement3.executeUpdate("insert into client"+uid+" (user_id, manager_id) values "+"('"+rsu.getInt(1)+"', '"+rsm.getInt(1)+"');");
    } //тут я добавляю в таблицу client нового пользователя последнему менеджеру
    public static Map<User, Manager> getMapConnection() throws SQLException {
        Map<User, Manager> usmaids = new HashMap<User, Manager>();
        ResultSet rs = statement.executeQuery("SELECT * FROM client"+uid);
        while (rs.next()) {
            ResultSet rsu = statement2.executeQuery("SELECT * FROM users"+uid+" where id = "+rs.getString(2));
            User user1 = new User();
            while (rsu.next()) {
                user1.setId(rsu.getString(1));
                user1.setName(rsu.getString(2));
                user1.setLastName(rsu.getString(3));
                user1.setAge(Integer.parseInt(rsu.getString(4)));
            }
            ResultSet rsm = statement3.executeQuery("SELECT * FROM managers"+uid+" where id = "+rs.getString(3));
            Manager manager1 = new Manager();
            while (rsm.next()) {
                manager1.setId(rsm.getString(1));
                manager1.setName(rsm.getString(2));
                manager1.setDept(rsm.getInt(4));
            }
        }
        return usmaids;
    }


    private static void print(Object s) {
        System.out.println(s);
    }

    public static String getAllManagers() {
        String sql = "select * from managers"+uid;
        return sql;
    }
    public static String getAllUsers() {
        String sql = "select * from users"+uid;
        return sql;
    }
    private static Map<Integer, Manager> getAllManagersEntities() throws Exception {
        String sql = getAllManagers();
        ResultSet rs = statement.executeQuery(sql);
        Map<Integer, Manager> list = new HashMap<>();
        int i = 1;
        while (rs.next()) {
            Manager manager1 = new Manager();
            manager1.setId(rs.getString(1));
            manager1.setName(rs.getString(2));
            manager1.setDept(rs.getInt(4));
            list.put(i++, manager1);
        }
        return list;
    }
    private static Map<Integer, User> getAllUsersEntities() throws Exception{
        String sql = getAllUsers();
        ResultSet rs = statement.executeQuery(sql);
        Map<Integer, User> list = new HashMap<>();
        int i = 1;
        while (rs.next()){
            User user1 = new User();
            user1.setId(rs.getString(1));
            user1.setName(rs.getString(2));
            user1.setLastName(rs.getString(3));
            user1.setAge(Integer.parseInt(rs.getString(4)));
            list.put(i++, user1);
        }
        return list;
    }
    public static void allCommands(){
        print("type 0 to close program");
        print("type 1 to add user");
        print("type 2 to add  manager");
        print("type 3 to get all users entities");
        print("type 4 to contact users and managers");
        print("type 5 to get amount");
    }

    public static String setUser(String name, String lastName, int age, int sum){
        String sql = "insert into users"+uid+" (username, lastname, age, sum) values " +
                "('"+name+"', '"+lastName+"', '"+age+"', '"+sum+"')";
        print(sql);
        allCommands();
        return sql;
    }
    public static String setManager(String manager_name, int dept){
        String sql = "insert into managers"+uid+" (manager_name, dept, amount) values " +
                "('"+manager_name+"', '"+dept+"', '"+0+"')";
        print(sql);
        allCommands();
        return sql;
    }
    public static int getId(String table) throws SQLException {
        int lastid = 0;
        ResultSet resultSet = statement.executeQuery("SELECT * FROM "+table);
        while (resultSet.next()) {
          if (lastid < resultSet.getInt(1)) lastid = resultSet.getInt(1);
        }
        return lastid;
    }
}
