package trial;

public class SendThread implements Runnable {
	private Thread thread;
    private String threadName = "Sending Thread";
    private Client client;

    public SendThread(String name, Client client) {
        this.threadName = name;
        this.client = client;
        System.out.println("Thread " + threadName + " is created.");
        start();
    }
    
    public SendThread(Client client) {
        this.client = client;
        System.out.println("Thread " + threadName + " is created.");
        start();
    }

    public void run() {
        System.out.println(this.threadName + " is running.");
        client.sendCommands();
    }

    public void start() {
        System.out.println(this.threadName + " is starting.");
        if (this.thread == null) {
            thread = new Thread(this, this.threadName);
        }
        this.thread.start();
        System.out.println(this.threadName + " is started.");
    }
}
