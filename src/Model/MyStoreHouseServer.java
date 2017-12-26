package Model;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;

import Common.*;
import Db.*;

public class MyStoreHouseServer extends Thread {

    public MyStoreHouseServer()
    {
        try {
            System.out.println("我是服务器，我在8888监听");
            ServerSocket ss = new ServerSocket(8888);
            while (true){
                Socket s = ss.accept();

                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

                Message ms = (Message)ois.readObject();
                User u = ms.getU();

                SqlConnection sqlConnection = new SqlConnection();
                Connection con = sqlConnection.getCon();

                if(ms.getMesType().equals(MessageType.message_login))
                {
                    sql_select select = new sql_select(con);
                    Message ms2 = new Message();
                    if(select.Login(u))
                    {
                        ms2.setMesType(MessageType.message_login_success);
                        oos.writeObject(ms2);
                    }
                    else{
                        ms2.setMesType(MessageType.message_login_fail);
                        oos.writeObject(ms2);
                    }
                }
                else if(ms.getMesType().equals(MessageType.message_register))
                {

                }
                else if(ms.getMesType().equals(MessageType.message_select))
                {

                }

                sqlConnection.closeCon(con);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
