package project.gui;

import project.MoveElevator;

public class App {

	public static void main(String[] args) {
		Thread requestListenerThread = new Thread(new StartStage(),"StageThread");
        // RequestProcessorThread to read Set and process requested floor
        Thread requestProcessorThread = new Thread(new MoveElevator(),
                "MoveElevatorThread");
        requestListenerThread.start();
        requestProcessorThread.start();
	}

}
