
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Hotel_Simulation {
	
	// used to store guest in the line to be  register. 
	static Queue<Guest> guest_in_queue = new LinkedList<>();
	
	// room_numbers queue is used to store available room numbers
	// room numbers starts 100 to 125; 
	static Queue<Integer> room_numbers = new LinkedList<>(); 
	
	// wait_for_bellhop is used to store guests who need help for their bags. 
	static Queue<Guest> wait_for_bellhop = new LinkedList<>(); 
	
	
	
	
    private static final  int guest_count = 25 ;
	
	//this semaphore suspend the main thread until all thread joined.
	static Semaphore wait_main_thread = new Semaphore(-24, true); 
	static Semaphore guest_semaphore = new Semaphore(25, true); 
	
	static Semaphore signal_bellhop = new Semaphore(0, true); 
	
	static Semaphore bellhop_employee_lock = new Semaphore(2, true);
	static Semaphore signal_front_desk = new Semaphore(0, true);
	//signal_guest is used to signal guest
	static Semaphore signal_guest = new Semaphore(0, true); 
	

	public static void main(String[] args) {
		System.out.println("Simulation starts"); 
		
		// populate room numbers to the available queue. 
		for(int i = 0; i < 25; i++)
			room_numbers.add(100 + i); 
		
		// create Front_Desk  objects
		Front_Desk front_desk_obj_zero = new Front_Desk(0); 
		Front_Desk front_desk_obj_one = new Front_Desk(1); 
		
		// create Front_Desk  threads
		Thread front_desk_thread_zero = new Thread(front_desk_obj_zero, "0"); 
		Thread front_desk_thread_one = new Thread(front_desk_obj_one, "1"); 
		// start Front_desk threads
		front_desk_thread_zero.start(); 
		front_desk_thread_one.start();
		
		// Create BellHop objects
		BellHop bellhop_obj_zero = new BellHop(0); 
		BellHop bellhop_obj_one = new BellHop(1); 
		
		// Create BellHop threads
		Thread bellhop_thread_zero= new Thread(bellhop_obj_zero, "0"); 
		Thread bellhop_thread_one = new Thread(bellhop_obj_one, "1"); 
		
		// starts bellhop threads
		bellhop_thread_zero.start();
		bellhop_thread_one.start(); 
		
		for(int i = 0; i < guest_count; i++)
		{
			Guest guest_obj = new Guest(i); 
			Thread guest_thread = new Thread(guest_obj); 
			guest_thread.start();
		}
		
		try {
			// wait the main thread until all the threads joined
			wait_main_thread.acquire();
			// join bellhop and front desk threads
			bellhop_thread_zero.join();
			bellhop_thread_one.join();
			front_desk_thread_zero.join();
			front_desk_thread_one.join();
			System.out.println("Simulation ends"); 
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
