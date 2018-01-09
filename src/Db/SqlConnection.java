package Db;

import java.sql.*;

public class SqlConnection {

    private final String url = "jdbc:mysql://localhost:3306/storehouse?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    private final String name = "root";
    private final String pwd = "123";
    private final String jdbc = "com.mysql.jdbc.Driver";

    public Connection getCon() throws Exception {
        Class.forName(jdbc);
        Connection con = DriverManager.getConnection(url,name,pwd);
        return con;
    }

    public void closeCon(Connection con) throws SQLException {
        if(con != null)
            con.close();
    }

    public static void main(String [] args)
    {
        SqlConnection sql = new SqlConnection();
        try {
            sql.getCon();
            System.out.println("Connection succeed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}