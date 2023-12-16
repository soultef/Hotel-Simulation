
public class Guest extends Thread {

	@Override
	public void run() {
		System.out.println("Guest " + Thread.currentThread().getName() + " created"); 
		
		
		
		
		
		
		
		try {
			
			this.join();
			Hotel.wait_main_thread.release();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

}
