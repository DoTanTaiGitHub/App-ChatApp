/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
/**
 *
 * @author ACER
 */
public class Client implements Runnable {

    private Socket client;
    DataInputStream in;
    DataOutputStream out;
    ObjectOutputStream out1;
    ObjectInputStream in1;

    public Client(Socket client) throws IOException {
        this.client = client;
        InputStream is = client.getInputStream();
        OutputStream os = client.getOutputStream();
        in = new DataInputStream(is);
        out = new DataOutputStream(os);
        out1 = new ObjectOutputStream(os);
        in1 = new ObjectInputStream(is);
    }

    public User login(String user_name, String password) {
        try {
            out.writeUTF("login");
            out.flush();
            out.writeUTF(user_name);
            out.flush();
            out.writeUTF(password);
            out.flush();
            int size = Integer.valueOf(in.readUTF());
            if (size > 0) {
                byte[] t = new byte[size];
                in.read(t);
                return (User) Convert.fromByteArrayToObject(t);
            }
        } catch (IOException e) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean registration(User entity) {
        try {
            this.out.writeUTF("registration");
            out1.writeObject(entity);
            return in.readBoolean();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String getPass(String email) {
        try {
            this.out.writeUTF("getPass");
            this.out.writeUTF(email);
            return this.in.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List fillRoom(String name){
        try {
            this.out.writeUTF("fillRoom");
            this.out.writeUTF(name);
            return (List) this.in1.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void close() {
        try {
            out.writeUTF("disconnected");
            this.client.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        while (true) {
            System.out.println("test");
        }
    }
    
    public boolean updateUser(User entity){
        try {
            out.writeUTF("updateUser");
            out1.writeObject(entity);
            return in.readBoolean();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public List<Object[]> add_friend(String name, String friend_name){
        try {
            out.writeUTF("add_friend");
            out.writeUTF(name);
            out.writeUTF(friend_name);
            return (List<Object[]>) in1.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Object[]> add_group(String mainName, String tv1, String tv2){
        try {
            out.writeUTF("add_group");
            out.writeUTF(mainName);
            out.writeUTF(tv1);
            out.writeUTF(tv2);
            return (List<Object[]>) in1.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<User> selectByNickName(String nick_name){
        try {
            out.writeUTF("selectByNickName");
            out.writeUTF(nick_name);
            return (List<User>) in1.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<User> selectAllFriend(String name){
        try {
            out.writeUTF("selectAllFriend");
            out.writeUTF(name);
            return (List<User>) in1.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void main(String[] args) {
        try {
            int port = 10000;
            Client client = new Client(new Socket("localhost", port));
//            Thread t = new Thread(client);
//            t.start();
            List<Object[]> list = client.add_group("tai", "Team3", "giang");
            for(Object[] ob: list){
                for (Object a: ob){
                    System.out.println(a);
                }
            }
            client.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
