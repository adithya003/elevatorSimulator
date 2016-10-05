package project.gui;

import java.io.PrintStream;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import project.Elevator;

public class StartStage extends Application implements EventHandler<ActionEvent>, Runnable {

	public StartStage() {
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane borderPane = new BorderPane();
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.TOP_CENTER);
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color: #336699;");
		Button[] buttons = new Button[5];
		int floor = 0;
		for (Button button : buttons) {
			button = new Button("Floor " + ++floor);
			hbox.getChildren().add(button);
			button.setOnAction(this);
		}
		borderPane.setTop(hbox);
		TextArea ta = new TextArea();
		Console console = new Console(ta);
		PrintStream ps = new PrintStream(console, true);
		System.setOut(ps);
		System.setErr(ps);
		
		borderPane.setCenter(ta);
		Scene scene = new Scene(borderPane);
		primaryStage.setScene(scene);

		// primaryStage.setWidth(400);
		// primaryStage.setHeight(800);
		primaryStage.show();
	}

	@Override
	public void handle(ActionEvent event) {
		Button button = (Button) event.getSource();
		switch (button.getText()) {
		case "Floor 1":
			addFloor(1);
			break;
		case "Floor 2":
			addFloor(2);
			break;
		case "Floor 3":
			addFloor(3);
			break;
		case "Floor 4":
			addFloor(4);
			break;
		case "Floor 5":
			addFloor(5);
			break;
		default:
			break;
		}

	}

	private void addFloor(int floorNumber) {
		Elevator elevator = Elevator.getInstance();
		elevator.addFloor(floorNumber);
	}

	@Override
	public void run() {
		launch("");
	}
}
