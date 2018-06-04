package junittesting;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import de.hsa.games.fatsquirrel.space.XY;

public class XYtest {

	XY testXY;
	XY addingVec;
	XY invertTest;
	XY invertExpect;
	XY vecToDir;
	XY vecToDirExpect;

	@BeforeEach
	public void setUp() {
		testXY = new XY(0, 0);
		addingVec = new XY(0, 1);
		invertTest = new XY(3,5);
		invertExpect = new XY(-3,-5);
		vecToDir = new XY(0,1);
		vecToDirExpect = new XY(0,1);
		
	}
	
	

	@Test
	public void addVec() {
		
		assert (testXY.addVec(addingVec).equals(addingVec));
		assertEquals(addingVec, testXY.addVec(addingVec));
		assertNotEquals(invertExpect, testXY.addVec(addingVec));
	}
	
	@Test
	public void invertVec() {
		assert(XY.invertVec(invertTest).equals(invertExpect));
	}
	
	@Test
	public void vecToDir() {
		assert(XY.vecToDir(vecToDir).equals(vecToDirExpect));
	}

}
