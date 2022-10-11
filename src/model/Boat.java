package model;

public class Boat {

	private Coordinate[] coordinates;
	private String name;
	private int x;
	private int y;
	private String orientation;
	
	public Boat(int x, int y, String name, String orientation) {
		this.x = x;
		this.y = y;
		this.name = name;
		this.orientation = orientation;
		init();
		createBoat();
	}
	
	public void init() {
		int quantity = 0;
		if(name.equalsIgnoreCase("PortaAviones")) {
			quantity = 5;
		}
		if(name.equalsIgnoreCase("Acorazado")) {
			quantity = 4;
		}
		if(name.equalsIgnoreCase("Crucero") || name.equalsIgnoreCase("Submarino")) {
			quantity = 3;
		}
		if(name.equalsIgnoreCase("Destructor")) {
			quantity = 2;
		}
		coordinates = new Coordinate[quantity];
	}
	
	public void createBoat() {
		for (int i = 0; i < coordinates.length; i++) {
			coordinates[i] = new Coordinate(x,y);
			if (orientation.equalsIgnoreCase("horizontal")) {
				y++;
			} else {
				x++;
			}
		}
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getOrientation() {
		return orientation;
	}

	public Coordinate[] getCoordinates() {
		return coordinates;
	}

	public String getName() {
		return name;
	}
	
	public String toStringCoordinates() {
		String result = "";
		for (int i = 0; i < coordinates.length; i++) {
			result +="("+coordinates[i].getX()+","+coordinates[i].getY()+")";
		}
		System.out.println(result);
		return result;
		
	}

}
