package game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GuessNumberServer {
    public static void main(String[] args) throws IOException{        
        int port = 4269;
        boolean exit = false;
        if(args.length>0){
            port = Integer.parseInt(args[0]);
        }
        ServerSocket ss = new ServerSocket(port);
        ExecutorService executorService = Executors.newCachedThreadPool();

        while(!exit){
            System.out.println("Waiting for connection...");
            Socket s = ss.accept();
            System.out.println("Connection Recieved...");
            GuessClientHandler handleMe = new GuessClientHandler(s);
            executorService.execute(handleMe);            
        }
        ss.close();
    }
}
