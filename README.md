#Hotel Simulation A hotel is simulated by using threads and semaphores to model customer and employee.

Requirement: A hotel has two front desk employee to register customers/guests and two bellhops to handle bags of customers. The front desk employee will find an available room and assign it to the customer. A guest get a room number form front desk employee after registeration. If the guest has less than 2 bags, the guest proceeds directly to his/her room. Otherwise, the guest visits the bellhop to drop off the bags to their room. The guest will later meet the bellhop in the room to get the bags, at which time a tip is given. The main thread must wait till all the threds joined. Each threads can not use thread sleep(waiting) for sinchronization. Each threads should print it's own activities.

Guest:

25 guests visit the hotel (1 thread per guest created at start of simulation).
Each guest has a random number of bags (0-5).
A guest must check in to the hotel at the front desk.
Upon check in, a guest gets a room number from the front desk employee.
A guest with more than 2 bags requires a bellhop.
The guest enters the assigned room.
Receives bags from bellhop and gives tip (if more than 2 bags).
Retires for the evening.
Front Desk:

Two employees at the front desk (1 thread each).
Checks in a guest, finds available room, and gives room number to guest.
Bellhop:

Two bellhops (1 thread each).
Gets bags from guest.
The same bellhop that took the bags delivers the bags to the guest after the guest is in the room.
Accepts tip from guest.
