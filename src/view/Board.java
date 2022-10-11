package view;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Board extends JPanel implements JPanelComponent{

	private JLabel[][] matrix;
	private String title;
	private Color color;
	private final int size = 31;
	
	public Board(String title,Color color) {
		this.title = title;
		this.color = color;
		matrix = new JLabel[10][10];
		setSize(385, 420);
		setBackground(Color.BLACK);
        setLayout(null);
		initComponent();
	}
    
	@Override
	public void initComponent() {
		startHeader();
		drawBoard();
	}
	
	public void startHeader() {
		JLabel titleLabel = new JLabel(title,SwingConstants.CENTER);
		titleLabel.setOpaque(true);titleLabel.setBackground(new Color(137, 238, 212 ));
		titleLabel.setBounds(2, 2, 381, 32);
		add(titleLabel);
		 int x = 35, y = 35;
		 for(int i = 0; i < 10 ; i++) {
			 JLabel label = new JLabel(""+(i+1),SwingConstants.CENTER);
			 label.setOpaque(true);		 
			 label.setBounds(x, y, size, size);
			 add(label);
			 x += 35;
		 }
		 x = 0;
		 y = 70;
		 for(char i = 'A'; i < 'K' ; i++) {
			 JLabel label = new JLabel(""+i,SwingConstants.CENTER);
			 label.setOpaque(true);
			 label.setBounds(x, y, size, size);
			 add(label);
			 y += 35;
		 }
	}
	
	public void drawBoard() {
		int x = 35, y = 70;
		 for(int i = 0; i < matrix.length ; i++) {
			 for(int j = 0; j < matrix[0].length ; j++) {
				 JLabel label = new JLabel("",SwingConstants.CENTER);
				 label.setBounds(x, y, size, size);
				 label.setOpaque(true);
				 label.setBackground(color);
				 matrix[i][j] = label;
				 add(label);
				 x += 35;
			 }
			x = 35;
			y += 35;
		 }
	}
	
	public void setCellColor(int x, int y, Color color) {
		matrix[x-1][y-1].setBackground(color);
	}

	public JLabel[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(JLabel[][] matrix) {
		this.matrix = matrix;
	}
	
}
