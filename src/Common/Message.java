package Common;

import java.sql.ResultSet;
import java.util.Vector;

public class Message implements java.io.Serializable{

    private String mesType;
    private String con;
    private User u;
    private Vector v;

    public String getMesType() {
        return mesType;
    }

    public void setMesType(String mesType) {
        this.mesType = mesType;
    }

    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public Vector getV() {
        return v;
    }

    public void setV(Vector v) {
        this.v = v;
    }
}
