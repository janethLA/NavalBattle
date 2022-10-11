package view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import model.Boat;
import validations.DrawBoatValidation;
import validations.HeaderViewValidation;

public class HeaderView extends JPanel implements ComponentInterface {

	private JComboBox boatType;
	private JLabel initialPositionLabel;
	private JTextField initialPositionX;
	private JTextField initialPositionY;
	private JRadioButton horizontal;
	private JRadioButton vertical;
	private JButton drawButton;
	private JButton startGameButton;
	private JLabel message;
	private JButton attackButton;
	private JLabel title;
	private JLabel winnerPlayer;
	
	public HeaderView() {
		initComponent();
		drawComponent();
	}
	
	@Override
	public void initComponent() {
		setBounds(0, 0, 820, 200);
		setLayout(null); 
	    setBackground(new Color(255, 235, 177 ));
	}
	
	@Override
	public void drawComponent() {
		
		Font font = new Font("Arial", Font.BOLD, 20);
		Font fontMessage = new Font("Arial Rounded MT Bold", Font.BOLD, 35);
		Font font1 = new Font("Arial", Font.BOLD, 14);
		title=new JLabel("DRAW YOUR BOATS", SwingConstants.CENTER);title.setBounds(200,0,350,60);
		title.setForeground(new Color(143, 78, 13 ));title.setFont(font);
		add(title);
		boatType = new JComboBox();boatType.setBounds(40, 70, 150, 30);
        boatType.addItem("Portaaviones"); 
        boatType.addItem("Acorazado"); 
        boatType.addItem("Crucero"); 
        boatType.addItem("Submarino"); 
        boatType.addItem("Destructor");
        add(boatType);
		initialPositionLabel = new JLabel("Initial Position:");initialPositionLabel.setBounds(235, 70, 130, 30);
		initialPositionLabel.setFont(font1);
		add(initialPositionLabel);
		initialPositionX = new JTextField();initialPositionX.setBounds(340, 70, 30, 30);
		initialPositionX.setFont(new Font("Arial", Font.PLAIN, 16));
		add(initialPositionX);
		initialPositionY = new JTextField();initialPositionY.setBounds(380, 70, 30, 30);
		initialPositionY.setFont(new Font("Arial", Font.PLAIN, 16));
		add(initialPositionY);
		message = new JLabel("");message.setBounds(560,40,300,50);
		message.setFont(fontMessage);message.setForeground(new Color(24, 36, 108  ));
		add(message);
		winnerPlayer = new JLabel("");winnerPlayer.setBounds(580,80,300,50);
		winnerPlayer.setFont(font);winnerPlayer.setForeground(new Color(24, 36, 108  ));
		add(winnerPlayer);
		horizontal = new JRadioButton("Horizontal");horizontal.setBounds(425,40,100,50);
		horizontal.setBackground(new Color(255, 235, 177 ));
		add(horizontal);
        vertical = new JRadioButton("Vertical");vertical.setBounds(425,80,100,50);vertical.setBackground(new Color(255, 235, 177 ));
        add(vertical);
        ButtonGroup bg=new ButtonGroup();
        bg.add(horizontal);
        bg.add(vertical);
        
	    drawButton = new JButton("DRAW");
		drawButton.setBounds(600, 70, 80, 30);
		add(drawButton);
		
		startGameButton = new JButton("START GAME");
		startGameButton.setBounds(350, 150, 120, 40);
		startGameButton.setBackground(new Color(18, 46, 200));
		startGameButton.setForeground(Color.WHITE);
		startGameButton.setEnabled(false);
		add(startGameButton);
		
		startGameButton.setBackground(new Color(218, 220, 227 ));
		startGameButton.setBorder(new LineBorder(Color.WHITE,1));

		attackButton = new JButton("ATTACK");
		attackButton.setFont(font1);
		attackButton.setBounds(350, 150, 100, 40);
		attackButton.setBackground(new Color(18, 46, 200));
		attackButton.setForeground(Color.WHITE);
		attackButton.setVisible(false);
		add(attackButton);

	}

	public String[] actionDrawButton() {
		String[] result = new String[4];
		if (initialPositionX.getText().trim().isEmpty() ||initialPositionY.getText().trim().isEmpty()
				|| (horizontal.isSelected()==false && vertical.isSelected()==false)) {
			JOptionPane.showMessageDialog(null, "Fill/Select the fields. \n Please try again",
					"Error", JOptionPane.ERROR_MESSAGE);
		} else {
	
			
			String positionX = initialPositionX.getText().toUpperCase();
			if( HeaderViewValidation.validationForPositionField(positionX, initialPositionY.getText())) {
				
					result[0] = positionX;
					result[1] = initialPositionY.getText();
					result[2] = (String)boatType.getSelectedItem();
					if(horizontal.isSelected()) {
						result[3] = horizontal.getText();
					}else {
						result[3] = vertical.getText();
					}
					
			}else {
				JOptionPane.showMessageDialog(null, 
						initialPositionLabel.getText().substring(0,initialPositionLabel.getText().length()-1)+" field is not correct. \n Please try again",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		return result;
	}

	public void removeItem() {
		boatType.removeItem(boatType.getSelectedItem());
		if(boatType.getItemCount()==0) {
			startGameButton.setEnabled(true);
			startGameButton.setBackground(new Color(18, 46, 200));
			startGameButton.setBorder(new LineBorder(Color.BLACK,1));
			drawButton.setEnabled(false);
		}
	}
	public void actionStartGameButton() {

		drawButton.setVisible(false);
		horizontal.setVisible(false);
		vertical.setVisible(false);
		boatType.setVisible(false);
		initialPositionLabel.setText("Coordinates to attack:");
		initialPositionLabel.setBounds(185, 70, 200, 30);
		initialPositionX.setText("");
		initialPositionY.setText("");
		title.setText("NAVAL BATTLE        ");
		attackButton.setVisible(true);
		startGameButton.setVisible(false);

	}
	
	public String [] actionAttackButton() {

		String result [] = new String [2];
		if (initialPositionX.getText().trim().isEmpty() ||initialPositionY.getText().trim().isEmpty() ) {
			JOptionPane.showMessageDialog(null, "Fill the field. \n Please try again",
					"Error", JOptionPane.ERROR_MESSAGE);
		} else {
			String positionX = initialPositionX.getText().toUpperCase();
			if( HeaderViewValidation.validationForPositionField(positionX, initialPositionY.getText())) {
				result [0] = positionX;
				result [1] = initialPositionY.getText();
			}else {
				JOptionPane.showMessageDialog(null, 
						initialPositionLabel.getText().substring(0,initialPositionLabel.getText().length()-1)+" field is not correct. \n Please try again",
						"Error", JOptionPane.ERROR_MESSAGE);
			}	
		}
		return result;
	
	}
	 
	public JButton getDrawButton() {
		return drawButton;
	}

	public JButton getStartGameButton() {
		return startGameButton;
	}

	public JButton getAttackButton() {
		return attackButton;
	}

	public JLabel getMessage() {
		return message;
	}

	public JLabel getWinnerPlayer() {
		return winnerPlayer;
	}
}
