package com.example.demo.test.socket;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket服务端
 * @author raining_heavily
 * @date 2020/7/7 20:49
 **/
public class SocketServer {
    public static void main(String[] args) throws IOException {

        int port = 8895;
        ServerSocket server = new ServerSocket(port);
        Socket socket = server.accept();
        System.out.println("---------socket server started------------\n");
        InputStream in = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len;
        while((len=in.read(bytes))>0){
            System.out.println("---------get message from client---------------");
            System.out.println(new String(bytes, 0, len, "UTF-8"));
        }
//        in.close();
//        socket.close();
//        server.close();

    }
}
