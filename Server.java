package Task1;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Server {
	private static ArrayList<Socket> sockets;
	static DateFormat dateForm;
	static Date date;
	
	public static void addSocket(Socket socket) {
		System.out.println("Connection from " + socket + " at " + dateForm.format(date));
		sockets.add(socket);
	}
	public static void sendMessage(String message, Socket sendersSocket) {
		for(Socket socket : sockets)
		{
			try {
				if(!socket.isClosed() && socket != sendersSocket) {
					PrintWriter sendingToUser = new PrintWriter(socket.getOutputStream(), true);
					sendingToUser.println(message);					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String [] args) {
		sockets = new ArrayList<>();
		try(ServerSocket serverSocket = new ServerSocket(8000)){
			dateForm = new SimpleDateFormat("E M d HH:mm:ss z y");
			date = new Date();
			System.out.println("Server started at " + dateForm.format(date) );
			while(true) {
				new Message(serverSocket.accept()).start();
			} 		
		}catch(IOException e) {
			System.out.println("Server Exception"+e.getMessage());
		}
	}
}