package glimmer.zsh.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args){
        try {
            //创建Socket
            Socket socket = new Socket("127.0.0.1",5000);
            //键盘读入
            InputStreamReader sysin = new InputStreamReader(System.in);
            BufferedReader sysbuf = new BufferedReader(sysin);
            //Socket读入
            InputStreamReader socin = new InputStreamReader(socket.getInputStream());
            BufferedReader socbuf = new BufferedReader(socin);
            //写出
            PrintWriter socout = new PrintWriter(socket.getOutputStream());
            //进行通信
            String readline = sysbuf.readLine();
            while(!readline.equals("bye")){ //如果传输"bye"，则退出循环
                socout.println(readline);
                socout.flush();
                System.out.println("Client:"+readline);
                System.out.println("Server:"+socbuf.readLine());
                readline = sysbuf.readLine();
            }
            System.out.println("Client:"+readline);
            socout.println(readline);
            socout.flush();
            System.out.println("Server:"+socbuf.readLine());
            //关闭IO和Socket
            sysbuf.close();
            socbuf.close();
            socket.close();

        } catch (Exception e) {
        }
    }
}
