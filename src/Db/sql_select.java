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
//        System.out.println(u.getUsername());
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
        String sql = "select Gno,Gname,Tno,Tname,Wno,Wname,sum(Gnum) as Gnum from store group by Gno,Gname,Tno,Tname,Wno,Wname;";
//        System.out.println(u.getUsername());
        if (checkEmpty.isNotEmpty(ms.getCon())) {
            sql = sql + " HAVING Gno = " + "'"+ms.getCon()+"'";
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
            v.addElement(rs.getString("Wname"));
            v.addElement(rs.getInt("Gnum"));
        }
        rs.close();
        psmt.close();
        return v;
    }

    public Vector Select_storeTable_distinct(Message ms) throws SQLException {
        String sql = "select DISTINCT Gno,Gname,Tno,Tname from store";
        PreparedStatement psmt = con.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery();
        Vector v = new Vector();
        while (rs.next()) {
            v.addElement(rs.getString(1));
            v.addElement(rs.getString(2));
            v.addElement(rs.getString(3));
            v.addElement(rs.getString(4));
        }
        rs.close();
        psmt.close();
        return v;
    }

    public Vector Select_storeTable_detl(Message ms) throws SQLException {
        String sql = "select * from store";
        PreparedStatement psmt = con.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery();
        Vector v = new Vector();
        while (rs.next()) {
            v.addElement(rs.getString(1));
            v.addElement(rs.getString(2));
            v.addElement(rs.getString(3));
            v.addElement(rs.getString(4));
            v.addElement(rs.getString(5));
            v.addElement(rs.getString(6));
            v.addElement(rs.getString(7));
            v.addElement(rs.getString(8));
            v.addElement(rs.getString(9));
            v.addElement(rs.getString(10));
        }
        rs.close();
        psmt.close();
        return v;
    }

    public Vector Select_storeTable_SID(Message ms) throws SQLException {
        String sql = "select SID,Gno,Wno from store";
        PreparedStatement psmt = con.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery();
        Vector v = new Vector();
        while (rs.next()) {
            v.addElement(rs.getString(1));
            v.addElement(rs.getString(2));
            v.addElement(rs.getString(3));
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
            v.addElement(rs.getString("Wu_size"));
            v.addElement(rs.getString("Wa_size"));
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

    public Vector Select_inputTable(Message ms) throws SQLException {
        String sql = "select * from Input";
        PreparedStatement psmt = con.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery();
        Vector v = new Vector();
        while (rs.next()) {
            v.addElement(rs.getString("Gno"));
            v.addElement(rs.getString("Gname"));
            v.addElement(rs.getString("Tno"));
            v.addElement(rs.getString("Tname"));
            v.addElement(rs.getString("Sno"));
            v.addElement(rs.getString("Wno"));
            v.addElement(rs.getString("Wname"));
            v.addElement(rs.getString("Inum"));
            v.addElement(rs.getDate("Idate"));
//            v.addElement(rs.getString("Idate"));
        }
        rs.close();
        psmt.close();
        return v;
    }

    public Vector Select_outputTable(Message ms) throws SQLException {
        String sql = "select * from Output";
        PreparedStatement psmt = con.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery();
        Vector v = new Vector();
        while (rs.next()) {
            v.addElement(rs.getString("Gno"));
            v.addElement(rs.getString("Gname"));
            v.addElement(rs.getString("Tno"));
            v.addElement(rs.getString("Tname"));
            v.addElement(rs.getString("Wno"));
            v.addElement(rs.getString("Cno"));
            v.addElement(rs.getString("Cname"));
            v.addElement(rs.getString("Onum"));
            v.addElement(rs.getDate("Odate"));
        }
        rs.close();
        psmt.close();
        return v;
    }

    public Vector Select_stores_Gno(Message ms) throws SQLException {
        String sql = "select DISTINCT Wno from store where Gno=?";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setString(1,(String)ms.getCon());
        ResultSet rs = psmt.executeQuery();
        Vector v = new Vector();
        while (rs.next()) {
            v.addElement(rs.getString(1));
        }
//        System.out.println(v);
        rs.close();
        psmt.close();
        return v;
    }

    public void Select_OutOfDate() throws SQLException {
        String sql = "select * from store where Out_date < curdate()";
        PreparedStatement psmt = con.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery();
        Vector v = new Vector();
        while(rs.next())
        {
            v.addElement(rs.getString(1));
            v.addElement(rs.getString(2));
            v.addElement(rs.getString(3));
            v.addElement(rs.getString(4));
            v.addElement(rs.getString(5));
            v.addElement(rs.getString(6));
            v.addElement(rs.getString(7));
            v.addElement(rs.getString(8));
            v.addElement(rs.getString(9));
            v.addElement(rs.getString(10));
        }

//        System.out.println(v);

        String sql2 = "delete from OutOfDate";
        PreparedStatement psmt2 = con.prepareStatement(sql2);
        psmt2.executeUpdate();

        String sql3 = "insert into OutOfDate values (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement psmt3 = con.prepareStatement(sql3);
        for(int i = 0;i<v.size();i+=10)
        {
            psmt3.setString(1,String.valueOf(v.get(i)));
            psmt3.setString(2,String.valueOf(v.get(i+1)));
            psmt3.setString(3,String.valueOf(v.get(i+2)));
            psmt3.setString(4,String.valueOf(v.get(i+3)));
            psmt3.setString(5,String.valueOf(v.get(i+4)));
            psmt3.setString(6,String.valueOf(v.get(i+5)));
            psmt3.setString(7,String.valueOf(v.get(i+6)));
            psmt3.setString(8,String.valueOf(v.get(i+7)));
            psmt3.setString(9,String.valueOf(v.get(i+8)));
            psmt3.setString(10,String.valueOf(v.get(i+9)));
            psmt3.executeUpdate();
        }
    }

    public Vector Select_Outofdate(Message ms) throws SQLException {
        String sql = "select * from OutOfDate";
        PreparedStatement psmt = con.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery();
        Vector v = new Vector();
        while (rs.next()) {
            v.addElement(rs.getString(1));
            v.addElement(rs.getString(2));
            v.addElement(rs.getString(3));
            v.addElement(rs.getString(4));
            v.addElement(rs.getString(5));
            v.addElement(rs.getString(6));
            v.addElement(rs.getString(7));
            v.addElement(rs.getString(8));
            v.addElement(rs.getString(9));
            v.addElement(rs.getString(10));
        }
        rs.close();
        psmt.close();
        return v;
    }

}
