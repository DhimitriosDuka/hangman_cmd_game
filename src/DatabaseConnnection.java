import java.sql.*;
public class DatabaseConnnection {

    static Statement Conn(){

        String url = "jdbc:mysql://localhost:3306/hangman_db?useSSL=false";
        String username = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
