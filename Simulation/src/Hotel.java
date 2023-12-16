import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Hotel {
	private static final  int guest_count = 25 ;
	public  static Queue<Thread> each_guest_thread = new LinkedList<>(); 
	
	static Semaphore wait_main_thread = new Semaphore(-24, true); 

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Simulation starts"); 
		// create Front_Desk  objects
		Front_Desk front_desk_obj_one = new Front_Desk(); 
		Front_Desk front_desk_obj_two = new Front_Desk(); 
		
		// create Front_Desk  threads
		Thread front_desk_thread_one = new Thread(front_desk_obj_one, "0"); 
		Thread front_desk_thread_two = new Thread(front_desk_obj_two, "1"); 
		// start Front_desk threads
		front_desk_thread_one.start(); 
		front_desk_thread_two.start();
		
		// Create BellHop objects
		BellHop bellhop_obj_one = new BellHop(); 
		BellHop bellhop_obj_two = new BellHop(); 
		
		// Create BellHop threads
		Thread bellhop_thread_one = new Thread(bellhop_obj_one, "0"); 
		Thread bellhop_thread_two = new Thread(bellhop_obj_two, "1"); 
		
		// starts bellhop threads
		bellhop_thread_one.start();
		bellhop_thread_two.start(); 
		
		for(int i = 0; i < guest_count; i++)
		{
			Guest guest_obj = new Guest(); 
			Thread guest_thread = new Thread(guest_obj, String.valueOf(i)); 
			//each_guest_thread.add(guest_thread); 
			guest_thread.start();
		}
		
		wait_main_thread.acquire();
		System.out.println("Simulation ends"); 
		
		
	}
	
}
