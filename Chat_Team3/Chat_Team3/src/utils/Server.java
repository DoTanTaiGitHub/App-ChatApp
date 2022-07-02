/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.*;
import java.io.IOException;
import static java.lang.System.in;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.InetAddress;
import java.net.URL;
import DAO.*;
import entity.User;
import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import entity.*;

/**
 *
 * @author ACER
 */
public class Server {

    final static int PORT = 10000;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ServerSocket server_socket = new ServerSocket(PORT);
            System.out.println("[SERVER IS LISTENING...]");
            while (true) {
                Socket client_socket = server_socket.accept();
                System.out.println("[" + client_socket.getInetAddress() + " is connected...]");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            boolean is_connected = true;
                            OutputStream os = client_socket.getOutputStream();
                            InputStream is = client_socket.getInputStream();
                            DataInputStream in = new DataInputStream(is);
                            DataOutputStream out = new DataOutputStream(os);
                            ObjectInputStream in1 = new ObjectInputStream(is);
                            ObjectOutputStream out1 = new ObjectOutputStream(os);
                            while (is_connected) {
                                String header = "";
                                try {
                                    while (true) {
                                        header = in.readUTF();
                                        if (!header.equals("")) {
                                            break;
                                        }
                                    }
                                } catch (EOFException e) {
                                }
                                if (header.equals("login")) {
                                    String user = in.readUTF();
                                    String pass = in.readUTF();
                                    User entity = new UserDAO().selectByIdAndPass(user, pass);
                                    byte[] b = Convert.fromObjectToByteArray(entity);
                                    if (b != null) {
                                        out.writeUTF(String.valueOf(b.length));
                                        out.write(b);
                                    } else {
                                        out.writeUTF("0");
                                    }

                                } else if (header.equals("registration")) {
                                    User entity = (User) in1.readObject();
                                    boolean ok = new UserDAO().insert(entity);
                                    out.writeBoolean(ok);
                                } else if (header.equals("getPass")) {
                                    String email = in.readUTF();
                                    String t = new UserDAO().getPass(email);
                                    if (t == null) {
                                        out.writeUTF("null");
                                    } else {
                                        out.writeUTF(t);
                                    }
                                } else if (header.equals("fillRoom")) {
                                    String name = in.readUTF();
                                    ResultSet r = XJdbc.query("{call all_room(?)}", name);
                                    List list = new ArrayList();
                                    while (r.next()) {
                                        Object[] o = new Object[]{
                                            r.getObject(1),
                                            r.getObject(2),
                                            r.getObject(3),
                                            r.getObject(4)
                                        };
                                        list.add(o);
                                    }
                                    out1.writeObject(list);
                                } else if (header.equals("updateUser")) {
                                    User entity = (User) in1.readObject();
                                    boolean rs = new UserDAO().update(entity);
                                    out.writeBoolean(rs);
                                } else if (header.equals("selectByNickName")) {
                                    String nick_name = in.readUTF();
                                    List<User> list = new UserDAO().selectByNickName(nick_name);
                                    out1.writeObject(list);
                                } else if (header.equals("add_friend")) {
                                    String name = in.readUTF();
                                    String friend_name = in.readUTF();
                                    new RoomDAO().insert_room();
                                    int id_room = (int) XJdbc.value("select max(id) from rooms");
                                    new MemberDAO().insert(new Member(id_room, name));
                                    new MemberDAO().insert(new Member(id_room, friend_name));
                                    int id_member = new MemberDAO().selectByIdRoomUserName(id_room, name);
                                    new MessageDAO().insert(new Message("hello", 1, id_member));
                                    new MessageDAO().insert(new Message("hello", 1, id_member + 1));
                                    ResultSet r = XJdbc.query("{call add_friend(?,?)}", name, id_room);
                                    List list = new ArrayList();
                                    while (r.next()) {
                                        Object[] o = new Object[]{
                                            r.getObject(1),
                                            r.getObject(2),
                                            r.getObject(3),
                                            r.getObject(4)
                                        };
                                        list.add(o);
                                    }
                                    out1.writeObject(list);
                                } else if (header.equals("add_group")) {
                                    String mainName = in.readUTF();
                                    String tv1 = in.readUTF();
                                    String tv2 = in.readUTF();
                                    new RoomDAO().insert_room();
                                    int id_room = (int) XJdbc.value("select max(id) from rooms");
                                    new MemberDAO().insert(new Member(id_room, mainName));
                                    new MemberDAO().insert(new Member(id_room, tv1));
                                    new MemberDAO().insert(new Member(id_room, tv2));
                                    int id_member1 = new MemberDAO().selectByIdRoomUserName(id_room, mainName);
                                    int id_member2 = new MemberDAO().selectByIdRoomUserName(id_room, tv1);
                                    int id_member3 = new MemberDAO().selectByIdRoomUserName(id_room, tv2);
                                    new MessageDAO().insert(new Message("hello", 1, id_member1));
                                    new MessageDAO().insert(new Message("hello", 1, id_member2));
                                    new MessageDAO().insert(new Message("hello", 1, id_member3));
                                    ResultSet r = XJdbc.query("{call add_friend(?,?)}", mainName, id_room);
                                    List list = new ArrayList();
                                    while (r.next()) {
                                        Object[] o = new Object[]{
                                            r.getObject(1),
                                            r.getObject(2),
                                            r.getObject(3),
                                            r.getObject(4)
                                        };
                                        list.add(o);
                                    }
                                    out1.writeObject(list);
                                } else if (header.equals("selectAllFriend")) {
                                    String name = in.readUTF();
                                    List<User> list = new UserDAO().selectAllFriend(name);
                                    out1.writeObject(list);
                                } else if (header.equals("disconnected")) {
                                    client_socket.close();
                                    is_connected = false;
                                }
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                            try {
                                client_socket.close();
                                in.close();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                        System.out.println("[" + client_socket.getInetAddress() + " is disconnected...]");
                    }
                }).start();
                System.out.println("[Current client]: " + (Thread.activeCount() - 1));
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
