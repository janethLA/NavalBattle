package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import model.Boat;
import view.PlayerBoardView;

public class Player {

	private ArrayList<Boat> boats;
	private ArrayList<Boat> sunkenBoats;
	private PlayerBoardView playerBoard;
	private boolean enabled;
	private boolean turn;
	private String name;
	
	public Player(String name) {
		this.name = name;
		boats = new ArrayList<Boat>();
		sunkenBoats = new ArrayList<Boat>();
		playerBoard = new PlayerBoardView();
		playerBoard.setTitle(playerBoard.getTitle()+" - Player: "+name);
		playerBoard.setVisible(true);
		enabled = false;
		turn = false;
		actions();
	}
	
	public void actions() {
		playerBoard.getHeaderView().getDrawButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					drawBoat(playerBoard.getHeaderView().actionDrawButton());
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});	
		playerBoard.getHeaderView().getStartGameButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerBoard.getHeaderView().actionStartGameButton();
				enabled = true;
			}
		});
	}
	
	public void drawBoat(String [] arg) {
		int x = convertStringToNumber(arg[0]);
		Boat boat = new Boat(x, Integer.parseInt(arg[1]), arg[2], arg[3]);
		for(int i=0;i< boat.getCoordinates().length;i++) {
			playerBoard.getBoatBoard().setCellColor(boat.getCoordinates()[i].getX(),boat.getCoordinates()[i].getY(), new Color(169, 119, 69 ));
		}	
		boats.add(boat);

	}
	
	public boolean isAvailableShot(int x, int y) {
		boolean result = false;
		if (playerBoard.getShotBoard().getMatrix()[x-1][y-1].getText().isEmpty()) {
			System.out.println("isDisponible: "+playerBoard.getShotBoard().getMatrix()[x-1][y-1].getText());
			result = true;
		}
		return result;
	}
	
	public String receiveAttack(int x, int y) {
		String result ="AGUA";
		boolean isFound = false;
		for(int i = 0;i<boats.size() && isFound==false;i++) {
			for(int j = 0;j<boats.get(i).getCoordinates().length && isFound==false;j++) {
				if(x==boats.get(i).getCoordinates()[j].getX() && y==boats.get(i).getCoordinates()[j].getY()) {
					isFound =  true;
					playerBoard.getBoatBoard().setCellColor(x,y,Color.RED);
					result = "TOCADO";
					if(isSunk(boats.get(i))) {
						result = "HUNDIDO";
					}
				}
			}	
		}
		return result;
	}
	
	public void markShot(int x, int y, String message) {
		playerBoard.getHeaderView().getMessage().setText(message);
		if (message.equalsIgnoreCase("AGUA")) {
			playerBoard.getShotBoard().getMatrix()[x-1][y-1].setText("X");
			playerBoard.getShotBoard().getMatrix()[x-1][y-1].setForeground(Color.RED);
		}else {
			playerBoard.getShotBoard().getMatrix()[x-1][y-1].setText("✔");
			playerBoard.getShotBoard().getMatrix()[x-1][y-1].setForeground(new Color(39, 167, 41));;
		}
		
	}
	
	public void markBoatOnShotBoard(Boat boat) {
		for(int i=0;i< boat.getCoordinates().length;i++) {
			playerBoard.getShotBoard().setCellColor(boat.getCoordinates()[i].getX(), boat.getCoordinates()[i].getY(), Color.YELLOW);
		}
		sunkenBoats.add(boat);
	}
	
	public boolean isGameOver() {	
		if(sunkenBoats.size()==5) {
		    return true;
	    }else {
	    	return false;
	    }
	}
	
	public void gameOver() {
		playerBoard.getHeaderView().getMessage().setText("GAME OVER!");
	    playerBoard.getHeaderView().getWinnerPlayer().setText("Winner player: "+name);
	}
	
	public Boat boat(int x, int y) {
		Boat boat=null;
		boolean isFound = false;
		for(int i = 0;i<boats.size() && isFound==false;i++) {
			for(int j = 0;j<boats.get(i).getCoordinates().length && isFound==false;j++) {
				if(x==boats.get(i).getCoordinates()[j].getX() && y==boats.get(i).getCoordinates()[j].getY()) {
					System.out.println("When hundido: "+boats.get(i).getName() +"x:"+x+", y:"+y);
					isFound =  true;
					boat = boats.get(i);
				}
			}	
		}
		return boat;
	}
	
	public boolean isSunk(Boat boat) {
		boolean result = true;
		for(int i = 0;i<boat.getCoordinates().length;i++) {
			if(!playerBoard.getBoatBoard().getMatrix()[boat.getCoordinates()[i].getX()-1]
					[boat.getCoordinates()[i].getY()-1].getBackground().equals(Color.RED)) {
				result = false;
			}
		}
		return result;
	}
	
	private int convertStringToNumber(String m) {
		int result = 0, cont =0  ;
		char c = m.charAt(0);
		for(char i = 'A'; i <= 'J' ; i++) {
			cont++;
			 if(c== i)
				 result = cont;
		}
   	    return result;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isTurn() {
		return turn;
	}
		
	public String getName() {
		return name;
	}

	public PlayerBoardView getPlayerBoard() {
		return playerBoard;
	}

	public void setPlayerBoard(PlayerBoardView playerBoard) {
		this.playerBoard = playerBoard;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	public JButton getAttackButton() {
		return playerBoard.getHeaderView().getAttackButton();
	}
	
	public int[] attackPos()  {
		int[] result = new int[2];
		String [] pos= playerBoard.getHeaderView().actionAttackButton();
		result[0]= convertStringToNumber(pos[0]);
		result[1]= Integer.parseInt( pos[1]);
		return result;
	}
}
