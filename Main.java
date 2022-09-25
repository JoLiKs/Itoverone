import java.sql.*;
import java.util.*;

public class Main {
    private static final String url = "jdbc:mysql://localhost/db1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "123123";
    private static Connection con;
    private static Statement statement;
    private static Statement statement2;
    private static int uid = 3;
    public static void main(String[] args) throws Exception {

        con = DriverManager.getConnection(url, user, password);
        statement = con.createStatement();
        statement2 = con.createStatement();
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
                    print(getAllUsersEntities());
                    break;
                case 4:
                   contactUserManagers();
                    break;
                case 5:
                    getAmount();
                    break;
            }
            command = scanner.nextInt();
        }
    }

    private static void getAmount() throws SQLException, InterruptedException {
        Integer amount = 0;
        ResultSet res = statement.executeQuery("SELECT * FROM client"+uid);
        while(res.next()) {
            amount++;
        }

        print(amount);
    }

    private static void contactUserManagers() throws SQLException {
        HashMap<Integer, Integer> ids = new HashMap<Integer, Integer>();
        ResultSet managers = statement.executeQuery("SELECT id FROM managers"+uid);
        ResultSet users = statement2.executeQuery("SELECT id FROM users"+uid);
        /*if (!users.next()) {
            print("Please, add users");
            return;
        }
        if (!managers.next()) {
            print("Please, add managers");
            return;
        }*/
        while (users.next() && managers.next()) {
            ids.put(users.getInt(1), managers.getInt(1));
        }
       for(Map.Entry<Integer, Integer> res : ids.entrySet()) {
statement.executeUpdate("insert into client"+uid+" (user_id, manager_id) values " + "('"+res.getKey()+"', '"+res.getValue()+"');");
        }
       print("Contacted!");
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
    private static List<User> getAllUsersEntities() throws Exception{
        String sql = getAllUsers();
        ResultSet rs = statement.executeQuery(sql);
        List<User> list = new ArrayList();
        while (rs.next()){
            User user1 = new User();
            user1.setName(rs.getString(2));
            user1.setLastName(rs.getString(3));
            user1.setAge(Integer.parseInt(rs.getString(4)));
            list.add(user1);
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
