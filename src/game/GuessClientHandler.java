package game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

public class GuessClientHandler {
    private Socket s = new Socket();

    public GuessClientHandler(Socket s) {
        this.s = s;
    }

    public void run() {
        Random rand = new Random();
        // int guessNumber = rand.nextInt(0, 100);
        int guessNumber = 10;
        int myGuess = 0;
        String fromClient = "";
        boolean exit = false;

        try {
            OutputStream os = s.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            InputStream is = s.getInputStream();
            DataInputStream dis = new DataInputStream(is);

            dos.writeUTF("Enter your guess: ");

            while (!exit) {
                fromClient = dis.readUTF();
                myGuess = Integer.parseInt(fromClient);
                System.out.println(myGuess);
                System.out.println(guessNumber);

                dos.writeUTF("wtf is happening");
                // if(myGuess<guessNumber){
                //     dos.writeUTF("higher");
                //     continue;
                // } else if(myGuess<guessNumber){
                //     dos.writeUTF("lower");
                //     continue;
                // } else if(myGuess==guessNumber){
                //     dos.writeUTF("congrats");
                //     exit = true;
                //     continue;
                // }
            }

            dos.close();
            os.close();
            dis.close();
            is.close();
            s.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
