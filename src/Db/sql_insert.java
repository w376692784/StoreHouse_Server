package Db;

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
        String sql = "insert into user values(?,?,3)";
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
}
