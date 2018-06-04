package junittesting;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;

import de.hsa.games.fatsquirrel.entities.GoodBeast;
import de.hsa.games.fatsquirrel.space.XY;
import de.hsa.games.fatsquirrel.ui.EntityContext;

class GoodBeastTest {

	@BeforeEach
	public void setUp() {

	}

	@Test

	public void test_randStepEvery3() {
		// GoodBeast beast = spy(new GoodBeast(pos));
		EntityContext entityContext = Mockito.mock(EntityContext.class);
		XY pos = new XY(0, 0);
		XY moveDir = new XY(1, 0);
		GoodBeast spyBeast = Mockito.spy(new GoodBeast(new XY(5, 5)));

		
		// Mockito.when(entityContext.tryMove(spyBeast, moveDir));

		// Mockito.doReturn(0).when(entityContext).tryMove(spyBeast, moveDir);
//		verify(spyBeast.nextStep(entityContext));

		spyBeast.nextStep(entityContext);
		// verify(entityContext, times(1)).
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
