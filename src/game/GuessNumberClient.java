package game;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class GuessNumberClient {

    public static void main(String[] args) {

        try {
            Socket s = new Socket("localhost", 4269);
            System.out.println("Connected to Server");
            boolean exit = false;

            InputStream is = s.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            OutputStream os = s.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);

            Console con = System.console();

            while (!exit) {
                String input = con.readLine("Enter your guess");
                dos.writeUTF(input);

                String fromServer = dis.readUTF();
                System.out.println(fromServer);

                if (fromServer.contains("congrats")) {
                    exit = true;
                }
            }
            s.close();
            dos.close();
            os.close();
            dis.close();
            is.close();
            // scan.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
