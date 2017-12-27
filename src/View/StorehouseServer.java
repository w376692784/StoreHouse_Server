package View;

import Model.MyStoreHouseServer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StorehouseServer extends JFrame implements ActionListener {

    JPanel jp1;
    JButton jb1,jb2;
    MyStoreHouseServer myStoreHouseServer;

    public static void main(String [] args)
    {
        StorehouseServer storehouseServer = new StorehouseServer();
        new MyStoreHouseServer();
    }

    public StorehouseServer()
    {
        jp1 = new JPanel();
        jb1 = new JButton("启动服务器");
        jb1.addActionListener(this);
        jb2 = new JButton("关闭服务器");
        jb2.addActionListener(this);
        jp1.add(jb1);
        jp1.add(jb2);

        this.add(jp1);
        this.setSize(300,35);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jb1)
        {
            myStoreHouseServer = new MyStoreHouseServer();

        }
    }
}
