package trial;

public class RcvThread implements Runnable {
	private Thread thread;
    private String threadName = "Sending Thread";
    private Client client;

    public RcvThread(String name, Client client) {
        this.threadName = name;
        this.client = client;
        System.out.println("Thread " + threadName + " is created.");
        start();
    }
    
    public RcvThread(Client client) {
        this.client = client;
        System.out.println("Thread " + threadName + " is created.");
        start();
    }

    public void run() {
        System.out.println(this.threadName + " is running.");
        try {
			client.rcvAndDisplay();
		} catch (Exception e) {
			System.out.println(">>>Response");
		}
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
