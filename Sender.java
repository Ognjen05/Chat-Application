package Task1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Sender extends Thread{
	@Override
	public void run() {
		 try {
			 Scanner scan;
			 String messages;
			 PrintWriter message = new PrintWriter(Client.clientsSocket.getOutputStream(), true);
			 message.println(Client.clientsName + " joined the chat");
			 do {
				 scan = new Scanner(System.in);
				 messages = scan.nextLine();
				 message.println(Client.clientsName + ": "+ messages);
			 }while(!messages.equals("exit"));
			 message.println(Client.clientsName + " exit the chat");
			 Client.clientsSocket.close();
			 scan.close();
			 System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}