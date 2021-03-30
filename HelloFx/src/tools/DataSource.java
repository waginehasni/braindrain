package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private   String url="jdbc:mysql://localhost:3306/projet" ;
    private String user="root" ;
    private String password ="";
    static DataSource instance;
    Connection connection ;
    public static DataSource getInstance(){
        if(instance==null){
            instance = new DataSource();
        }
        return instance;
    }

    private DataSource() {


        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection(url, user, password) ;
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }

    }

    public Connection getConnection() {
        return connection;
    }
}
