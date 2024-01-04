
import java.util.concurrent.Semaphore;

public class BellHop extends Thread
{
	private int bellhop_id; 
	public static Semaphore locker = new Semaphore(1, true); 
	
	public BellHop(int bellhop_id)
	{
		this.bellhop_id  = bellhop_id; 
		System.out.println("Bellhop " + this.bellhop_id + " created"); 
	}
	
	
	@Override
	public void run()
	{
		Guest guest; 
		int guest_id; 
		try
		{
			while(Hotel.guest_morethan_two_bags_semaphore.tryAcquire())
			{
				Hotel.bellhop_wait_guest.acquire();
				
				locker.acquire();
				guest = Hotel.guest_wait_bellhop.poll(); 
				locker.release();
				
				
				guest_id = guest.getGuest_id(); 
				guest.setBellhop_id(bellhop_id);
				
				System.out.println("Bellhop " + bellhop_id + " receives bags from guest " + guest_id); 
				
				Hotel.guest_wait_bellhops.release(); 
				Hotel.bellhop_wait_guest_One.acquire();
				System.out.println("Bellhop " + bellhop_id + " delivers bags to guest " + guest_id); 
				Hotel.guest_wait_bellhops_One.release();
				
		
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}

}
