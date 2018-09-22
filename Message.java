package Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Message extends Thread{
    private Socket socket;
    public Message(Socket socket) {
        this.socket = socket;
        Server.addSocket(this.socket);
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true) {
                String clientString = input.readLine();
                if(clientString == null || clientString.equals("exit")) {
                    break;
                }
                Server.sendMessage(clientString, socket);
                System.out.println("Client Input: " + clientString);
            }
        }catch(IOException e) {
            System.out.println("Client is dropped: " + e.getMessage());
        }finally {
            try {
                socket.close();
            }catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
