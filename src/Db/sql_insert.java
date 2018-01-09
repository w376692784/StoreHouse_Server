package Db;

import Common.Message;
import Common.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class sql_insert {

    Connection con = null;

    public sql_insert(Connection con)
    {
        this.con = con;
    }

    public int insert_User(User u) throws SQLException {
        int sym = 0;
        String sql = "insert into user values(null,?,?,3)";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setString(1,u.getUsername());
        psmt.setString(2,u.getUserpwd());
        int i = psmt.executeUpdate();
        if(i == 1)
        {
            sym = 3;
        }
        return sym;
    }

    public int insert_supplier(Message m) {
        String sql = "insert into supplier values (?,?,?,?)";
        try {PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1,String.valueOf(m.getV().get(0)));
            psmt.setString(2, (String) m.getV().get(1));
            psmt.setString(3, (String) m.getV().get(2));
            psmt.setString(4,String.valueOf(m.getV().get(3)));
//            System.out.println(String.valueOf(psmt));
//            System.out.println(m.getV().get(2));
            return psmt.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public int insert_customs(Message m) {
        String sql = "insert into custom values (?,?,?,?,?)";
        try {PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1,String.valueOf(m.getV().get(0)));
            psmt.setString(2,String.valueOf(m.getV().get(1)));
            psmt.setString(3,String.valueOf(m.getV().get(2)));
            psmt.setString(4,String.valueOf(m.getV().get(3)));
            psmt.setString(5,String.valueOf(m.getV().get(4)));
            System.out.println(String.valueOf(psmt));
            System.out.println(m.getV().get(3));
            return psmt.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public int insert_storehouse(Message m) {
        String sql = "insert into warehouse values (?,?,?,?)";
        try {PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1,String.valueOf(m.getV().get(0)));
            psmt.setString(2,String.valueOf(m.getV().get(1)));
            psmt.setInt(3,(Integer)(m.getV().get(2)));
            psmt.setString(4,String.valueOf(m.getV().get(3)));
            System.out.println((Integer)m.getV().get(2));
            return psmt.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public int insert_InputTable(Message m) {
        String sql = "insert into Input values (?,?,?,?,?,?,?,?)";
        try {PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1,String.valueOf(m.getV().get(0)));
            psmt.setString(2,String.valueOf(m.getV().get(1)));
            psmt.setString(3,String.valueOf(m.getV().get(2)));
            psmt.setString(4,String.valueOf(m.getV().get(3)));
            psmt.setString(5,String.valueOf(m.getV().get(4)));
            psmt.setString(6,String.valueOf(m.getV().get(5)));
            psmt.setString(7,String.valueOf(m.getV().get(6)));
            psmt.setString(8,String.valueOf(m.getV().get(7)));
            System.out.println(m.getV());
            return psmt.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }
}
