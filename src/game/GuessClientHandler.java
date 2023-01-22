package game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

public class GuessClientHandler implements Runnable{
    // Memebers
    private Socket s = new Socket();
    // Constructors
    public GuessClientHandler(Socket s) {this.s = s;}
    // Override with runnable interface
    @Override
    public void run() {
        Random rand = new Random();
        int guessNumber = rand.nextInt(0, 100);
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
                // System.out.println(myGuess);
                // System.out.println(guessNumber);

                while(myGuess!=guessNumber){
                    if(myGuess<guessNumber){
                        dos.writeUTF("higher");                        
                        break;
                    } else {
                        dos.writeUTF("lower");
                        break;
                    }
                }

                if(myGuess==guessNumber){
                    dos.writeUTF("Congratulations, you guessed correctly!");                        
                    break;
                }                
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
