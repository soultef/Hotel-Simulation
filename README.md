#Hotel Simulation
A hotel is simulated by using threads and semaphores to model customer and employee. 

Requirement: 
A hotel has two front desk employees to register customers/guests and two bellhops to handle bags of customers. 
The front desk employee will find an available room and assign it to the customer. 
A guest gets a room number from the front desk employee after registration. 
If the guest has less than 2 bags, the guest proceeds directly to his/her room.
Otherwise, the guest visits the bellhop to drop off the bags in their room. 
The guest will later meet the bellhop in the room to get the bags, at which time a tip is given.
The main thread must wait till all the threads are joined. 
Each thread cannot use thread sleep(waiting) for synchronization. 
Each thread should print its own activities.

Guest:
1)	25 guests visit the hotel (1 thread per guest created at start of simulation).
2)	Each guest has a random number of bags (0-5).
3)	A guest must check in to the hotel at the front desk.
4)	Upon check in, a guest gets a room number from the front desk employee.
5)	A guest with more than 2 bags requires a bellhop.
6)	The guest enters the assigned room.
7)	Receives bags from bellhop and gives tip (if more than 2 bags).
8)	Retires for the evening.

Front Desk:
1)	Two employees at the front desk (1 thread each).
2)	Checks in a guest, finds an available room, and gives room number to guest.

Bellhop:
1)	Two bellhops (1 thread each).
2)	Gets bags from guest.
3)	The same bellhop that took the bags delivers the bags to the guest after the guest is in the room.
4)	Accepts tips from guest.
