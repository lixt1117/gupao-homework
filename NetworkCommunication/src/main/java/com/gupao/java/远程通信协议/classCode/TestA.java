package com.gupao.java.远程通信协议.classCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class TestA {

    public static void main(String[] args) {
        ServerSocket serverSocket=null;
        Socket socket=null;
        try {
            serverSocket=new ServerSocket(8080);
            socket=serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line;
        try {
            BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter os=new PrintWriter(socket.getOutputStream());
            BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
            System.out.println("receve Client msg:"+is.readLine());
            line=sin.readLine();
            while(!line.equals("bye")){
                os.println(line);
                os.flush();
                System.out.println("receve Client msg:"+is.readLine());
                line=sin.readLine();
            }
            os.close();
            is.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
