package game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class GuessNumberClient {

    public static void main(String[] args) {

        String host = "localhost";
        int port = 4269;

        if(args.length==1){
            port = Integer.parseInt(args[0]);            
        } else if(args.length ==2){
            host = args[0];
            port = Integer.parseInt(args[1]);  
        }

        try {
            Socket s = new Socket(host, port);
            System.out.println("Connected to Server");
            boolean exit = false;
            Scanner scan = new Scanner(System.in);

            InputStream is = s.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            OutputStream os = s.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);

            System.out.println(dis.readUTF());

            while (!exit) {
                String input = scan.nextLine();
                dos.writeUTF(input);

                String fromServer = dis.readUTF();
                System.out.println(fromServer);

                if (fromServer.contains("correct")) {
                    exit = true;
                }
            }
            s.close();
            dos.close();
            os.close();
            dis.close();
            is.close();
            scan.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
