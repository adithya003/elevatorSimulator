package project;

public class MoveElevator implements Runnable{
	@Override
	public void run() {
		while (true) {
            Elevator elevator = Elevator.getInstance();
            int floor = elevator.nextFloor();
            int currentFloor = elevator.getCurrentFloor();
            if (floor >= 0) {
                if (currentFloor > floor) {
                    while (currentFloor > floor) {
                    	int oldFloor = floor;
                    	floor = elevator.getCurrentFloor();
                    	if(oldFloor != floor){
                    		elevator.addFloor(oldFloor);
                    	}
                    	elevator.setCurrentFloor(--currentFloor);
                    }
                } else {
                    while (currentFloor < floor) {
                    	int oldFloor = floor;
                    	floor = elevator.getCurrentFloor();
                    	if(oldFloor != floor){
                    		elevator.addFloor(oldFloor);
                    	}
                        elevator.setCurrentFloor(++currentFloor);
                    }
                }
            }
            if(currentFloor==floor){
            	System.out.println("The Elevator reached floor " + floor + ".");
            }
        }
		
	}

}
