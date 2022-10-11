package validations;

import java.awt.Color;
import java.util.ArrayList;

import model.Boat;
import model.Coordinate;

public class DrawBoatValidation {
	
	public static boolean validationDrawBoat(ArrayList<Boat> boats,int x, int y) {
		boolean result = true;
		boolean isFound = false;
		for(int i = 0;i<boats.size() && isFound==false;i++) {
			for(int j = 0;j<boats.get(i).getCoordinates().length && isFound==false;j++) {
				if(!isPosible(boats.get(i).getCoordinates()[j].getX() , boats.get(i).getCoordinates()[j].getY(),x,y)) {
					result =false;
					isFound =  true;
				}
			}	
		}
		return result;
	}
	
	public static boolean isPosible(int presentX, int presentY, int futureX, int futureY) {
		boolean result = true;
		Coordinate[] c = getPosNotValidates( presentX,  presentY);
		for(int i = 0;i< c.length; i++) {
			if(c[i].getX() == futureX && c[i].getY()  == futureY ) {
				result = false;
			}
		}
		return result;
		
	}
	
	public static Coordinate[] getPosNotValidates(int presentX, int presentY) {
		int x = presentX-1;
		int y = presentY-1;
		Coordinate[] noValidates= new Coordinate[9];
		int cont = 0;
		for(int i = 0;i< 3; i++) {
			for(int j = 0; j< 3; j++) {
				Coordinate c = new Coordinate( x,y);
				y++;
				noValidates[cont]=c;
				cont++;
			}
			y=presentY-1;
			x++;
		}
		return noValidates;
	}

	public static boolean boatValited(Boat boat) {
		boolean result =  true;
		for(int j = 0;j<boat.getCoordinates().length ;j++) {
			if(!HeaderViewValidation.validationCoorY(boat.getCoordinates()[j].getX()) || !HeaderViewValidation.validationCoorY(boat.getCoordinates()[j].getY())) {
				result =false;
			}
		}
		return result;
	}

}
