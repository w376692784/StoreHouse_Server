package Db;

import Common.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class sql_select {

    Connection con = null;

    public sql_select(Connection con)
    {
        this.con = con;
    }

    public int Select_User(User u) throws SQLException {
        int sym = 0;
        String sql = "select * from user where username = ? and userpwd = ?";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setString(1,u.getUsername());
        psmt.setString(2,u.getUserpwd());
        ResultSet rs = psmt.executeQuery();
        if(rs.next())
        {
            sym = rs.getInt("usertype");
        }
        rs.close();
        psmt.close();
        return sym;
    }
}
