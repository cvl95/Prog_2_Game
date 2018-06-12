package de.hsa.games.fatsquirrel.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.entities.BadBeast;
import de.hsa.games.fatsquirrel.entities.GoodBeast;
import de.hsa.games.fatsquirrel.space.XY;
import de.hsa.games.fatsquirrel.util.commandscanner.Command;
import de.hsa.games.fatsquirrel.util.commandscanner.CommandScanner;
import de.hsa.games.fatsquirrel.util.commandscanner.CommandTypeInfo;
import de.hsa.games.fatsquirrel.util.commandscanner.GameCommandType;
import de.hsa.games.fatsquirrel.util.commandscanner.ScanException;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class FxUI extends Scene implements UI, ActionListener {
	private Canvas boardCanvas;
	private Label msgLabel;
	private static final int CELL_SIZE = 16;
	private static String keyCode = "stay"; // saved last input

	public FxUI(Parent parent, Canvas boardCanvas, Label msgLabel) {
		super(parent);
		this.boardCanvas = boardCanvas;
		this.msgLabel = msgLabel;
	}

	public static FxUI createInstance(XY boardSize) {

		Canvas boardCanvas = new Canvas(boardSize.getX() * CELL_SIZE, boardSize.getY() * CELL_SIZE);
		Label statusLabel = new Label();
		VBox top = new VBox();
		top.getChildren().add(boardCanvas);
		top.getChildren().add(statusLabel);
		statusLabel.setText("READY");
		final FxUI fxUI = new FxUI(top, boardCanvas, statusLabel);
		fxUI.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {

				KeyCode code = keyEvent.getCode();
				setCommand(code);
			}

			/**
			 * setter for last fetched input
			 * 
			 * @param code
			 */
			private void setCommand(KeyCode code) {
				keyCode = code.toString();
			}
		});
		return fxUI;
	}

	@Override
	public void render(final BoardView view) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				repaintBoardCanvas(view);
			}
		});
	}

	private void repaintBoardCanvas(BoardView view) {
		GraphicsContext gc = boardCanvas.getGraphicsContext2D();
		gc.clearRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
		XY viewSize = view.getSize();

		for (int i = 0; i < viewSize.getX(); i++) {
			for (int j = 0; j < viewSize.getY(); j++) {
				XY currPos = new XY(i, j);
				EntityType et = view.getEntityType(currPos);
				if (et != null)
					System.out.println("FXui:  " + et.toString());
					
				Color color = Color.WHITE;
				switch (et) {
				case BadBeast:
					color = Color.RED;
					break;
				case GoodBeast:
					color = Color.GREEN;
					break;
				case BadPlant:
					color = Color.VIOLET;
					break;
				case GoodPlant:
					color = Color.TURQUOISE;
					break;
				case MasterSquirrel:
					color = Color.BLUE;
					break;
				case HandOperatedMasterSquirrel:
					color = Color.BLUE;
					break;
				case MiniSquirrel:
					color = Color.BROWN;
					break;
				case Wall:
					color = Color.ORANGE;
					break;
				case EMPTY:
					color = Color.WHITE;
					break;

				}

				gc.setFill(color);
				gc.fillRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
			}
		}

	}

	@Override
	public void message(final String msg) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				msgLabel.setText(msg);
			}
		});
	}

	@Override
	public Command getCommand() {
		Command newCommand;
		CommandScanner comScan = new CommandScanner(GameCommandType.values(),
				new BufferedReader(new InputStreamReader(System.in)));
		newCommand = comScan.getCommand(keyCode);
		return newCommand;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}
}
