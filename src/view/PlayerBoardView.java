package view;

import java.awt.Color;
import javax.swing.JFrame;


public class PlayerBoardView extends JFrame {

	private Board boatsBoard;
	private Board shotBoard;
	private HeaderView headerView;
	
	public PlayerBoardView(){
		setTitle("Naval Battle");
		setSize(820, 800);
		setLocationRelativeTo(null);
		setLayout(null);
		this.dispose();
		start();
	}
	
	public void start() {
		startHeader();
        startsBoatBoard();
        startShotBoard();
     
	}
	
    public void startHeader() {	
		headerView= new HeaderView();
		headerView.setLocation(0,0);
		add(headerView);
	}
	
	public void startsBoatBoard() {
		boatsBoard = new Board("BOATS  BOARD",new Color(186, 232, 252 ));
		boatsBoard.setLocation(5, 250);
		add(boatsBoard);
	}
	
	public void startShotBoard() {
		shotBoard = new Board("SHOTS   BOARD",Color.WHITE);
		shotBoard.setLocation(415, 250);
		add(shotBoard);
	}
	
	public void markShot(int x, int y) {	
		shotBoard.setCellColor(x,y, Color.RED);
		shotBoard.getMatrix()[x][y].setText("X");
	}
		
	public Board getBoatBoard() {
		return boatsBoard;
	}

	public Board getShotBoard() {
		return shotBoard;
	}

	public HeaderView getHeaderView() {
		return headerView;
	}

}
