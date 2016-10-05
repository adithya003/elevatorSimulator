package project;

import java.util.TreeSet;

import project.Direction;

public class Elevator {
	private static Elevator elevator = null;
	private static TreeSet<Integer> floorSet = new TreeSet<Integer>();
    private int currentFloor = 0;
	
    private Direction direction = Direction.UP;

    private Elevator() {
    }

    public static Elevator getInstance() {
        if (elevator == null) {
            elevator = new Elevator();
        }
        return elevator; 
    }

    public synchronized void addFloor(int f) {
    	floorSet.add(f);
        // Notify the thread that a new request has come.
        notify();
    }

    public synchronized int nextFloor() {

        Integer floor = null;

        if (direction == Direction.UP) {
            if (floorSet.ceiling(currentFloor) != null) {
                floor = floorSet.ceiling(currentFloor);
            } else {
                floor = floorSet.floor(currentFloor);
            }
        } else {
            if (floorSet.floor(currentFloor) != null) {
                floor = floorSet.floor(currentFloor);
            } else {
                floor = floorSet.ceiling(currentFloor);
            }
        }
        if (floor == null) {
            try {
                System.out.println("Please click on floor number to simulate.");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Elevator moving to floor : " + floor);
            floorSet.remove(floor);
        }
        return (floor == null) ? -1 : floor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        if (this.currentFloor > currentFloor) {
            setDirection(Direction.DOWN);
        } else {
            setDirection(Direction.UP);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.currentFloor = currentFloor;
        System.out.println("Currently passing floor : " + currentFloor);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
