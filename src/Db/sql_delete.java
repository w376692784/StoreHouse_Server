package Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Common.*;

public class sql_delete {

    Connection con = null;

    public sql_delete(Connection con)
    {
        this.con = con;
    }

    public int Delete_User(User u) throws SQLException {
        String sql = "delete from user where userId=?";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setInt(1,u.getUserId());
        return psmt.executeUpdate();
    }

    public int Delete_supplier(String Sno) throws SQLException {
        String sql = "delete from supplier where Sno=" + "'"+Sno+"'";
        PreparedStatement psmt = con.prepareStatement(sql);
        return psmt.executeUpdate();
    }

    public int Delete_custom(String Cno) throws SQLException {
        String sql = "delete from custom where Cno=" + "'"+Cno+"'";
        PreparedStatement psmt = con.prepareStatement(sql);
        return psmt.executeUpdate();
    }

    public int Delete_storehouse(String Wno) throws SQLException {
        String sql = "delete from warehouse where Wno=" + "'"+Wno+"'";
        PreparedStatement psmt = con.prepareStatement(sql);
        return psmt.executeUpdate();
    }
}
