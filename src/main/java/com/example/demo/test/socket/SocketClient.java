package com.example.demo.test.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * socket客户端
 * @author raining_heavily
 * @date 2020/7/8 21:11
 **/
public class SocketClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1",8895);
        OutputStream out = socket.getOutputStream();
        for(int i = 0;i<5;i++){
            String str = i+",hello world";
            socket.getOutputStream().write(str.getBytes("UTF-8"));
            Thread.sleep(3000);
        }
        socket.close();
    }

}
