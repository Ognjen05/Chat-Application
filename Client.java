package Task1;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static Socket clientsSocket;
    public static String clientsName;
    public static void main(String[] args) {
        System.out.println("Enter your name");
        Scanner scanner = new Scanner(System.in);
        clientsName = scanner.nextLine();
        try {
            clientsSocket = new Socket("localhost", 8000);
            new Sender().start();
            new Receiver().start();
        } catch (IOException e) {
            System.out.println("Client Error: Could not connect to server");
        }
    }
}
