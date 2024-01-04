import java.util.concurrent.Semaphore;
public class Guest extends Thread
{

	private int guest_id; 
	private int luggage_count; 
	private int room_number;
	private int bellhop_id; 
	private int frontdesk_id; 
	
	public static Semaphore locker = new Semaphore(1, true); 
	public static Semaphore lock_queued_guest = new Semaphore(1, true);
	
	public Guest(int guest_id)
	{
		this.guest_id = guest_id; 
		this.luggage_count = (int)(Math.random() * 6); 
		if(luggage_count  > 2) 
			Hotel.guest_morethan_two_bags++; 
	
	}
	
	@Override
	public void run()
	{
		try
		{
			System.out.println("Guest " + this.guest_id + " created"); 
			System.out.println("Guest " + this.guest_id + " enters hotel with " + luggage_count + " bags"); 
			
			lock_queued_guest.acquire();
			Hotel.guest_wait_frontdesk.add(this); 
			lock_queued_guest.release();
			
			Hotel.frontdesk_wait_guest.release();
			Hotel.guest_wait_front_desk.acquire();
			System.out.println("Guest " + this.guest_id + " receives room key for room " +
			this.room_number + " from front desk employee " + this.frontdesk_id);
			if(luggage_count > 2)
			{
				locker.acquire();
				Hotel.guest_wait_bellhop.add(this); 
				locker.release();
				System.out.println("Guest " + guest_id + " requests help with bags"); 
				
				Hotel.bellhop_wait_guest.release();
				Hotel.guest_wait_bellhops.acquire(); 
				System.out.println("Guest " + this.guest_id + " enters room " + this.room_number); 
				Hotel.bellhop_wait_guest_One.release();
				Hotel.guest_wait_bellhops_One.acquire();
				System.out.println("Guest " + this.guest_id + " receives bags from bellhop "
				+ this.bellhop_id + " and gives tip" ); 
				
			}
			
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("Guest " + this.guest_id + " retired for the evening"); 
			System.out.println("Guest " + this.guest_id + " joined"); 
		}
	
	}
	
	/**
	 * @return the guest_id
	 */
	public int getGuest_id() {
		return guest_id;
	}

	/**
	 * @param guest_id the guest_id to set
	 */
	public void setGuest_id(int guest_id) {
		this.guest_id = guest_id;
	}

	/**
	 * @return the room_number
	 */
	public int getRoom_number() {
		return room_number;
	}

	/**
	 * @param room_number the room_number to set
	 */
	public void setRoom_number(int room_number) {
		this.room_number = room_number;
	}

	/**
	 * @return the bellhop_id
	 */
	public int getBellhop_id() {
		return bellhop_id;
	}

	/**
	 * @param bellhop_id the bellhop_id to set
	 */
	public void setBellhop_id(int bellhop_id) {
		this.bellhop_id = bellhop_id;
	}

	/**
	 * @return the frontdesk_id
	 */
	public int getFrontdesk_id() {
		return frontdesk_id;
	}

	/**
	 * @param frontdesk_id the frontdesk_id to set
	 */
	public void setFrontdesk_id(int frontdesk_id) {
		this.frontdesk_id = frontdesk_id;
	}
}
