
import java.util.concurrent.Semaphore;

public class Front_Desk extends Thread
{
	private int frontdesk_id; 
	public static Semaphore locker = new Semaphore(1, true);
	public Front_Desk(int frontdesk_id)
	{
		this.frontdesk_id = frontdesk_id; 
		System.out.println("Front desk employee " + this.frontdesk_id + " created"); 
	}
	
	
	@Override
	public void run()
	{
		try 
		{
			Guest guest; 
			int room_number, guest_id; 
			while(Hotel.guest_semaphore.tryAcquire())
			{
				Hotel.frontdesk_wait_guest.acquire();
				locker.acquire();
				guest = Hotel.guest_wait_frontdesk.poll(); 
				room_number = Hotel.room_numbers++; 
				locker.release();
				guest_id = guest.getGuest_id(); 
				guest.setFrontdesk_id(frontdesk_id);
				guest.setRoom_number(room_number);
				System.out.println("Front desk employee " + frontdesk_id + 
						" registers guest " + guest_id + " and assigns room "
						+ room_number); 
				Hotel.guest_wait_front_desk.release();
			}
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
			
		
	}
}