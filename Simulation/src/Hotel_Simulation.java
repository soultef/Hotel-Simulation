
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
/**
 * This class is used to simulate hotel, guest, bellhop, and front desk employee. 
 * It creates 25 guests, 2 bellhop, and 2 front desk employee threads
 * @author Solomon Demisse
 * @since November 30, 2023
 */
public class Hotel {
	// maximum number of guests
	private static final int max_guest = 25; 
	public static Guest guests [] = new Guest[max_guest]; 
	public static Bellhop bellhops[] = new Bellhop[2]; 
	public static Front_Desk frontdesk_employees[] = new Front_Desk[2]; 
	//guest waiting in the queue to check in
	public static Queue<Guest> guest_wait_frontdesk = new LinkedList<>(); 
	
	// guest waiting in the queue to get help for their luggage. 
	public static Queue<Guest> guest_wait_bellhop = new LinkedList<>(); 
	
	// room number starts from 100
	public static int room_numbers = 100; 
	// number of guests who has more than two bags
	public static int guest_morethan_two_bags; 

	public static Semaphore guest_morethan_two_bags_semaphore;  
	public static Semaphore guest_semaphore = new Semaphore(25);
	public static Semaphore frontdesk_wait_guest = new Semaphore(0, true); 
	public static Semaphore guest_wait_front_desk = new Semaphore(0, true); 
	 
	
	public static Semaphore bellhop_wait_guest = new Semaphore(0); 
	public static Semaphore bellhop_wait_guest_One = new Semaphore(0); 
	public static Semaphore guest_wait_bellhops = new Semaphore(0); 
	public static Semaphore guest_wait_bellhops_One = new Semaphore(0); 
	
	public static void main(String[] args)
	{
		System.out.println("Simulation starts"); 
		// create guest objects
		for(int i = 0; i < 25; i++)
			guests[i] = new Guest(i);
		
		guest_morethan_two_bags_semaphore = new Semaphore(guest_morethan_two_bags); 
		
		//create front desk employee objects and start thread
		for(int i = 0; i < 2; i++)
		{
			frontdesk_employees[i] = new Front_Desk(i); 
			frontdesk_employees[i].start();
		}
		
        //create bellhop objects and start thread
		for(int i = 0; i < 2; i++)
		{
			bellhops[i] = new Bellhop(i); 
			bellhops[i].start();
		}
	

		// start guest threads
		for(Guest a_guest: guests)
			a_guest.start();
		
	
		try
		{
			// joining guest threads
			for(Guest guest_thread: guests)
			guest_thread.join();
			
			//joining bellhop and front desk
			for(int i =0; i < 2; i++)
			{
			  frontdesk_employees[i].join();
			  bellhops[i].join(); 
			}
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("Simulation ends"); 
		}
		
		
		
	}
		
}
