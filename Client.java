package trial;

import java.io.*;
import java.net.*;

/**
 * Client
 */
public class Client {
    private Socket socket = null;
    private BufferedReader input = null;
    private DataOutputStream output = null;
    private String line = "";
    private String response = "";
    private BufferedReader back;

    public Client(String addr, int port) {
        buildConnection(addr, port);
        sendCommands();
    }
    
	public void sendCommands() {
    	if(input != null) {
    		while (!line.equals("Over") || !line.equals("over") || !line.equals("exit") || !line.equals("Exit")) {
    			try {
    				line = input.readLine();
    				output.writeUTF(line);
    			} catch (Exception e) {
    				System.out.println(">>>Error: Communication");
    			}
    		}
    	
    		try {
    			input.close();
    			output.close();
    			socket.close();
    			System.out.println(">>>Exiting...");
    			System.exit(0);
    		} catch (IOException e) {
    			System.out.println(e);
    		}
    	} else {
    		System.out.println(">>>Exiting...");
    		System.exit(0);
    	}
    }
    
    public void rcvAndDisplay() throws Exception {
    	while((response = back.readLine()) != null) {
    		System.out.println(">>>" + response);
    	}
    }
    
    public void buildConnection(String addr, int port) {
    	try {
        	System.out.println(">>>Connection in Progress...");
            socket = new Socket(addr, port);
            System.out.println(">>>Connected");
            input = new BufferedReader(new InputStreamReader(System.in));
            output = new DataOutputStream(socket.getOutputStream());
            back = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            System.out.println(">>>Connection Failed");
        }
    }
    
    

    @SuppressWarnings("unused")
	public static void main(String[] args) {
        Client client = new Client("192.168.10.230", 12345);
//        RcvThread rcvthread = new RcvThread(client);
    }
}