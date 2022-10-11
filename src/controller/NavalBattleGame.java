package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class NavalBattleGame {

	Player player1;
	Player player2;
	int cont=0;
	
	public NavalBattleGame() {
		player1 = new Player("Juan");
		player2 = new Player("Sofia");
		actions();
	}
	
   public boolean start() {
		boolean result=false;
		if(player1.isEnabled() && player2.isEnabled()) {
			if(cont==0) {
				player1.setTurn(true);	
			}	
			cont++;
			result=true;
		}else {
			JOptionPane.showMessageDialog(null, "All players need to start the game",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return result;
	}
	
	public void actions() {
		player1.getAttackButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if(start()) {	
				    	if(player1.isTurn()) {
				    		try {
				    			attack(player1,player2);
				    			if(player1.isGameOver()) {
				    				player1.gameOver();
									player2.gameOver();
				    				player1.getAttackButton().setEnabled(false);
				    				player2.getAttackButton().setEnabled(false);
				    			}
							} catch (Exception e2) {
								// TODO: handle exception
							}
						}else {
							JOptionPane.showMessageDialog(null, "It's "+player2.getName()+"'s turn",
									"Error", JOptionPane.ERROR_MESSAGE);
						}
				    }
			}
		});
		player2.getAttackButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if (start()) {
					if (player2.isTurn()) {
						try {
							attack(player2, player1);
							if(player2.isGameOver()) {
								player1.gameOver();
								player2.gameOver();
			    				player1.getAttackButton().setEnabled(false);
			    				player2.getAttackButton().setEnabled(false);
			    			}
						} catch (Exception e2) {
							// TODO: handle exception
						}

					} else {
						JOptionPane.showMessageDialog(null, "It's "+player1.getName()+"'s turn", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
	
	private void attack(Player attacker,Player attacked) {
		int [] pos= attacker.attackPos();
		int x= pos[0];
		int y= pos[1];
		if(attacker.isAvailableShot(x,y)) {
			String message = attacked.receiveAttack(x, y);
			attacker.markShot(x, y, message);
			if(message.equals("HUNDIDO")) {
				attacker.markBoatOnShotBoard(attacked.boat(x, y));
			}
			attacked.setTurn(true);
			attacker.setTurn(false);
				
		}else {
			JOptionPane.showMessageDialog(null, " The shot is not available. \n Please try again",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
