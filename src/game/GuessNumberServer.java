package game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GuessNumberServer {
    public static void main(String[] args) throws IOException{

        ServerSocket ss = new ServerSocket(4269);

        // while(true){
        // }
        System.out.println("Waiting for connection...");
        Socket s = ss.accept();
        System.out.println("Connection Recieved...");
        GuessClientHandler handleMe = new GuessClientHandler(s);
        handleMe.run();
    }
}
