package Db;

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
}
