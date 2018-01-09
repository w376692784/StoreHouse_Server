package Db;

import Common.Message;
import Common.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import Model.checkEmpty;

public class sql_select {

    Connection con = null;

    public sql_select(Connection con) {
        this.con = con;
    }

    public int Select_User(User u) throws SQLException {
        int sym = 0;
        String sql = "select * from user where username = ? and userpwd = ?";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setString(1, u.getUsername());
        psmt.setString(2, u.getUserpwd());
        ResultSet rs = psmt.executeQuery();
        if (rs.next()) {
            sym = rs.getInt("usertype");
        }
        rs.close();
        psmt.close();
        return sym;
    }

    public Vector Select_userTable(User u) throws SQLException {
        String sql = "select * from user";
        System.out.println(u.getUsername());
        if (checkEmpty.isNotEmpty(u.getUsername())) {
            sql = sql + " where username =" +"'"+ u.getUsername()+"'";
        }
        PreparedStatement psmt = con.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery();
        Vector v = new Vector();
        while (rs.next()) {
            if (rs.getInt("usertype") == 1)
                continue;
            else {
                v.addElement(rs.getInt("userId"));
                v.addElement(rs.getString("username"));
                v.addElement(rs.getString("userpwd"));
                if (rs.getInt("usertype") == 2)
                    v.addElement(new String("Manager"));
                else v.addElement(new String("User"));
            }
        }
        rs.close();
        psmt.close();
        return v;
    }

    public Vector Select_storeTable(Message ms) throws SQLException {
        String sql = "select * from store";
//        System.out.println(u.getUsername());
        if (checkEmpty.isNotEmpty(ms.getCon())) {
            sql = sql + " where Gno = " + "'"+ms.getCon()+"'";
        }
//        System.out.println(sql);
        PreparedStatement psmt = con.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery();
        Vector v = new Vector();
        while (rs.next()) {
            v.addElement(rs.getString("Gno"));
            v.addElement(rs.getString("Gname"));
            v.addElement(rs.getString("Tno"));
            v.addElement(rs.getString("Tname"));
            v.addElement(rs.getString("Wno"));
            v.addElement(rs.getInt("Gnum"));
        }
        rs.close();
        psmt.close();
        return v;
    }

    public Vector Select_customsTable(Message ms) throws SQLException {
        String sql = "select * from custom";
//        System.out.println(u.getUsername());
        if (checkEmpty.isNotEmpty(ms.getCon())) {
            sql = sql + " where Cno =" + "'"+ms.getCon()+"'";
        }
        PreparedStatement psmt = con.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery();
        Vector v = new Vector();
        while (rs.next()) {
            v.addElement(rs.getString("Cno"));
            v.addElement(rs.getString("Cname"));
            v.addElement(rs.getString("Csex"));
            v.addElement(rs.getString("Caddr"));
            v.addElement(rs.getString("Ctel"));
        }
        rs.close();
        psmt.close();
        return v;
    }

    public Vector Select_supplierTable(Message ms) throws SQLException {
        String sql = "select * from supplier";
//        System.out.println(u.getUsername());
        if (checkEmpty.isNotEmpty(ms.getCon())) {
            sql = sql + " where Sno =" + "'"+ms.getCon()+"'";
        }
        PreparedStatement psmt = con.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery();
        Vector v = new Vector();
        while (rs.next()) {
            v.addElement(rs.getString("Sno"));
            v.addElement(rs.getString("Sname"));
            v.addElement(rs.getString("Saddr"));
            v.addElement(rs.getString("Stel"));
        }
        rs.close();
        psmt.close();
        return v;
    }

    public Vector Select_storehouseTable(Message ms) throws SQLException {
        String sql = "select * from warehouse";
//        System.out.println(u.getUsername());
        if (checkEmpty.isNotEmpty(ms.getCon())) {
//            sql = sql + " where Wno =" + "'"+ms.getCon()+"'";
        }
        PreparedStatement psmt = con.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery();
        Vector v = new Vector();
        while (rs.next()) {
            v.addElement(rs.getString("Wno"));
            v.addElement(rs.getString("Wname"));
            v.addElement(rs.getString("Wsize"));
            v.addElement(rs.getString("Waddr"));
        }
        rs.close();
        psmt.close();
        return v;
    }

    public Vector Select_goods(Message ms) throws SQLException {
        String sql = "select * from goods";
        PreparedStatement psmt = con.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery();
        Vector v = new Vector();
        while (rs.next()) {
            v.addElement(rs.getString("Gno"));
            v.addElement(rs.getString("Gname"));
            v.addElement(rs.getString("Tno"));
            v.addElement(rs.getString("Tname"));
            v.addElement(rs.getString("Sno"));
        }
        rs.close();
        psmt.close();
        return v;
    }


}
