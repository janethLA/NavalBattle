package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import model.Boat;

public class BoatTest {

	@Test
	public void createPortaAvionesBoat() {
		String expectedCoordinates = "(1,1)(1,2)(1,3)(1,4)(1,5)";
		Boat boat = new Boat(1,1,"Portaaviones", "horizontal");
		boat.createBoat();
		assertEquals(expectedCoordinates, boat.toStringCoordinates());
	}
	@Test
	public void createAcorazadoBoat() {
		String expectedCoordinates = "(2,2)(3,2)(4,2)(5,2)";
		Boat boat = new Boat(2,2,"Acorazado", "vertical");
		boat.createBoat();
		assertEquals(expectedCoordinates, boat.toStringCoordinates());
	}
	@Test
	public void createCruceroBoat() {
		String expectedCoordinates = "(3,2)(3,3)(3,4)";
		Boat boat = new Boat(3,2,"Crucero", "horizontal");
		boat.createBoat();
		assertEquals(expectedCoordinates, boat.toStringCoordinates());
	}
	@Test
	public void createSubmarinoBoat() {
		String expectedCoordinates = "(2,4)(3,4)(4,4)";
		Boat boat = new Boat(2,4,"Submarino", "vertical");
		boat.createBoat();
		assertEquals(expectedCoordinates, boat.toStringCoordinates());
	}
	@Test
	public void createDestructorBoat() {
		String expectedCoordinates = "(1,1)(1,2)";
		Boat boat = new Boat(1,1,"Destructor", "horizontal");
		boat.createBoat();
		assertEquals(expectedCoordinates, boat.toStringCoordinates());
	}
	
}
