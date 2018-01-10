package Model;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;

import Common.*;
import Db.*;
import sun.misc.resources.Messages_sv;

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

                new sql_select(con).Select_OutOfDate();
//                System.out.println("success");

                if(ms.getMesType().equals(MessageType.message_login))
                {
                    sql_select select = new sql_select(con);
                    Message ms2 = new Message();
                    u.setType(select.Select_User(u));
                    if(u.getType() != 0)
                    {
                        ms2.setMesType(MessageType.message_login_success);
                        ms2.setU(u);
                        oos.writeObject(ms2);
                    }
                    else{
                        ms2.setMesType(MessageType.message_login_fail);
                        oos.writeObject(ms2);
                    }
                }

                else if(ms.getMesType().equals(MessageType.message_register))
                {
                    sql_select select = new sql_select(con);
                    Message ms2 = new Message();
                    u.setType(select.Select_User(u));
//                    System.out.println(u.getType());

                    if(u.getType() == 0)
                    {
                        sql_insert insert = new sql_insert(con);
                        u.setType((insert.insert_User(u)));
                        ms2.setU(u);
//                        System.out.println("2"+u.getType());
                        ms2.setMesType(MessageType.message_register_success);
                        oos.writeObject(ms2);
                    }
                    else{
                        ms2.setMesType(MessageType.message_register_fail);
                        oos.writeObject(ms2);
                    }
                }

                else if(ms.getMesType().equals(MessageType.message_select))
                {

                }

                else if(ms.getMesType().equals(MessageType.message_select_userTable))
                {
                    sql_select select = new sql_select(con);
                    Message ms2 = new Message();
                    ms2.setV(select.Select_userTable(ms.getU()));
                    oos.writeObject(ms2);
                }

                else if(ms.getMesType().equals(MessageType.message_select_storeTable))
                {
                    sql_select select = new sql_select(con);
                    Message ms2 = new Message();
                    ms2.setV(select.Select_storeTable(ms));
                    oos.writeObject(ms2);
                }

                else if(ms.getMesType().equals(MessageType.message_select_storeTable_detl))
                {
                    sql_select select = new sql_select(con);
                    Message ms2 = new Message();
                    ms2.setV(select.Select_storeTable_detl(ms));
                    oos.writeObject(ms2);
                }

                else if(ms.getMesType().equals(MessageType.message_select_storeTable_dintinct))
                {
                    sql_select select = new sql_select(con);
                    Message ms2 = new Message();
                    ms2.setV(select.Select_storeTable_distinct(ms));
                    oos.writeObject(ms2);
                }

                else if(ms.getMesType().equals(MessageType.message_select_OutOfDate))
                {
                    sql_select select = new sql_select(con);
                    Message ms2 = new Message();
                    ms2.setV(select.Select_Outofdate(ms));
                    oos.writeObject(ms2);
                }

                else if(ms.getMesType().equals(MessageType.message_select_customsTable))
                {
                    sql_select select = new sql_select(con);
                    Message ms2 = new Message();
                    ms2.setV(select.Select_customsTable(ms));
                    oos.writeObject(ms2);
                }

                else if(ms.getMesType().equals(MessageType.message_select_supplierTable))
                {
                    sql_select select = new sql_select(con);
                    Message ms2 = new Message();
                    ms2.setV(select.Select_supplierTable(ms));
                    oos.writeObject(ms2);
                }

                else if(ms.getMesType().equals(MessageType.message_select_storehouseTable))
                {
                    sql_select select = new sql_select(con);
                    Message ms2 = new Message();
                    ms2.setV(select.Select_storehouseTable(ms));
                    oos.writeObject(ms2);
                }

                else if(ms.getMesType().equals(MessageType.message_select_goods))
                {
                    sql_select select = new sql_select(con);
                    Message ms2 = new Message();
                    ms2.setV(select.Select_goods(ms));
                    oos.writeObject(ms2);
                }

                else if(ms.getMesType().equals(MessageType.message_select_inputTable))
                {
                    sql_select select = new sql_select(con);
                    Message ms2 = new Message();
                    ms2.setV(select.Select_inputTable(ms));
                    oos.writeObject(ms2);
                }

                else if(ms.getMesType().equals(MessageType.message_select_outputTable))
                {
                    sql_select select = new sql_select(con);
                    Message ms2 = new Message();
                    ms2.setV(select.Select_outputTable(ms));
                    oos.writeObject(ms2);
                }

                else if(ms.getMesType().equals(MessageType.message_select_stores_Gno))
                {
                    sql_select select = new sql_select(con);
                    Message ms2 = new Message();
                    ms2.setV(select.Select_stores_Gno(ms));
                    oos.writeObject(ms2);
                }

                else if(ms.getMesType().equals(MessageType.message_update_user))
                {
                    sql_update update = new sql_update(con);
                    Message ms2 = new Message();
                    ms2.setCon(String.valueOf(update.Update_User(ms.getU())));
                    oos.writeObject(ms2);
                }

                else if(ms.getMesType().equals(MessageType.message_delete_user))
                {
                    sql_delete delete = new sql_delete(con);
                    Message ms2 = new Message();
                    ms2.setCon(String.valueOf(delete.Delete_User(ms.getU())));
                    oos.writeObject(ms2);
                }

                else if(ms.getMesType().equals(MessageType.message_delete_supplierTable))
                {
                    sql_delete delete = new sql_delete(con);
                    Message ms2 = new Message();
                    ms2.setCon(String.valueOf(delete.Delete_supplier(ms.getCon())));
                    oos.writeObject(ms2);
                }

                else if(ms.getMesType().equals(MessageType.message_delete_customsTable))
                {
                    sql_delete delete = new sql_delete(con);
                    Message ms2 = new Message();
//                    System.out.println(ms.getCon());
                    ms2.setCon(String.valueOf(delete.Delete_custom(ms.getCon())));
                    oos.writeObject(ms2);
                }

                else if(ms.getMesType().equals(MessageType.message_delete_storehouseTable))
                {
                    sql_delete delete = new sql_delete(con);
                    Message ms2 = new Message();
                    ms2.setCon(String.valueOf(delete.Delete_storehouse(ms.getCon())));
                    oos.writeObject(ms2);
                }

                else if(ms.getMesType().equals(MessageType.message_insert_supplierTable))
                {
                    sql_insert insert = new sql_insert(con);
                    Message ms2 = new Message();
                    ms2.setCon(String.valueOf(insert.insert_supplier(ms)));
                    oos.writeObject(ms2);
                }

                else if(ms.getMesType().equals(MessageType.message_insert_customsTable))
                {
                    sql_insert insert = new sql_insert(con);
                    Message ms2 = new Message();
                    ms2.setCon(String.valueOf(insert.insert_customs(ms)));
                    oos.writeObject(ms2);
                }

                else if(ms.getMesType().equals(MessageType.message_insert_storehouseTable))
                {
                    sql_insert insert = new sql_insert(con);
                    Message ms2 = new Message();
                    ms2.setCon(String.valueOf(insert.insert_storehouse(ms)));
                    oos.writeObject(ms2);
                }

                else if(ms.getMesType().equals(MessageType.message_insert_InputTable))
                {
                    sql_insert insert = new sql_insert(con);
                    Message ms2 = new Message();
                    ms2.setCon(String.valueOf(insert.insert_InputTable(ms)));
                    oos.writeObject(ms2);
                }

                else if(ms.getMesType().equals(MessageType.message_insert_OutputTable))
                {
                    sql_insert insert = new sql_insert(con);
                    Message ms2 = new Message();
                    ms2.setCon(String.valueOf(insert.insert_OutputTable(ms)));
//                    System.out.println(ms2.getCon());
                    oos.writeObject(ms2);
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
