package glimmer.zsh.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            //监听
            Socket socket = serverSocket.accept();
            //键盘读入
            InputStreamReader sysin = new InputStreamReader(System.in);
            BufferedReader sysbuf = new BufferedReader(sysin);
            //Socket读入
            InputStreamReader socin = new InputStreamReader(socket.getInputStream());
            BufferedReader socbuf = new BufferedReader(socin);
            //写出
            PrintWriter socout = new PrintWriter(socket.getOutputStream());
            //进行通信
            String readline = socbuf.readLine();
            String readline1;
            while(!readline.equals("bye")){//如果接收到"bye"，则退出循环
                System.out.println("Client:"+readline);
                readline1 = sysbuf.readLine();
                socout.println(readline1);
                socout.flush();
                System.out.println("Server:"+readline1);
                readline = socbuf.readLine();
            }
            System.out.println("Client:"+readline);
            readline1 = sysbuf.readLine();
            socout.println(readline1);
            socout.flush();
            System.out.println("Server:bye");

            //关闭IO和Socket
            sysbuf.close();
            socbuf.close();
            socket.close();
            serverSocket.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
