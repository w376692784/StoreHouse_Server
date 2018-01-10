package Db;

import Common.Message;
import Common.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class sql_update {

    Connection con = null;

    public sql_update(Connection con)
    {
        this.con = con;
    }

    public int Update_User(User u) throws SQLException {
        String sql = "update user set username=? ,userpwd=? ,usertype=? where userId=?";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setString(1,u.getUsername());
        psmt.setString(2,u.getUserpwd());
        psmt.setInt(3,u.getType());
        psmt.setInt(4,u.getUserId());
        return psmt.executeUpdate();
    }

    public int Update_Supplier(Message m) throws SQLException {
        String sql = "update supplier set Sname=? ,Saddr=?, Stel=? where Sno=?";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setString(1,(String)m.getV().get(1));
        psmt.setString(2,(String)m.getV().get(2));
        psmt.setString(3,(String)m.getV().get(3));
        psmt.setString(4,(String)m.getV().get(0));
        return psmt.executeUpdate();
    }

    public int Update_Storehouse(Message m) throws SQLException {
        String sql = "update warehouse set Wname=? ,Waddr=? where Wno=?";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setString(1,(String)m.getV().get(1));
        psmt.setString(2,(String)m.getV().get(2));
//        psmt.setString(3,(String)m.getV().get(3));
        psmt.setString(3,(String)m.getV().get(0));
//        System.out.println(m.getV());
        return psmt.executeUpdate();
    }

    public int Update_Custom(Message m) throws SQLException {
        String sql = "update custom set Cname=? , Csex=? ,Caddr=?,Ctel=? where Cno=?";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setString(1,(String)m.getV().get(1));
        psmt.setString(2,(String)m.getV().get(2));
        psmt.setString(3,(String)m.getV().get(3));
        psmt.setString(4,(String)m.getV().get(4));
        psmt.setString(5,(String)m.getV().get(0));
//        System.out.println(m.getV().get(3));
        return psmt.executeUpdate();
    }
}
