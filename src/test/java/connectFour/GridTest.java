package connectFour;


import org.junit.Test;

import static org.junit.Assert.*;

public class GridTest {
	private static final String EMPTYGRID = String.join("\n", new String[]{"-------", "-------", "-------", "-------", "-------", "-------"});
	private static final String FILLEDGRID = String.join("\n", new String[]{"-------", "-------", "-------", "-------", "-------", "M------"});
	private static final String OVERFLOWGRID = String.join("\n", new String[]{"M------", "M------", "M------", "M------", "M------", "M------"});
	private static final String FULLGRID = String.join("\n", new String[]{"MMMMMMM", "MMMMMMM", "MMMMMMM", "MMMMMMM", "MMMMMMM", "MMMMMMM"});

	@Test
	public void shouldReturn6x7MatrixOnInit() {
		Grid grid = new Grid();

		assertEquals(6, grid.getNbLines());
		assertEquals(7, grid.getNbColumns());
	}

	@Test
	public void shouldContainDashInAllPointOnInit() {
		Grid grid = new Grid();
		assertEquals(grid.toString(), EMPTYGRID);
	}

	@Test
	public void shouldThrowOnEmptySpotInsert(){
		Grid grid = new Grid();
		assertThrows(IllegalArgumentException.class, () -> grid.insert(1, "-"));
	}

	@Test
	public void shouldContainsTheNewInsertedElement() {
		Grid grid = new Grid();
		grid.insert(0, "M");
		assertEquals(grid.toString(), FILLEDGRID);
	}

	@Test
	public void shouldThrowExceptionOnColumnFull() {
		Grid grid = new Grid();
		for (int i = 0; i < grid.getNbLines(); i++) {
			grid.insert(1, "M");
		}
		assertThrows(IllegalArgumentException.class, () -> grid.insert(1, "M"));
	}

	@Test
	public void shouldThrowExceptionOnColumnNegative() {
		Grid grid = new Grid();
		assertThrows(IllegalArgumentException.class, () -> grid.insert(-1, "M"));
	}

	@Test
	public void shouldThrowExceptionOnColumnOverflow() {
		Grid grid = new Grid();
		assertThrows(IllegalArgumentException.class, () -> grid.insert(grid.getNbColumns(), "M"));
	}

	@Test
	public void shouldThrowExceptionOnInsertFullGrid() {
		Grid grid = new Grid();
		for (int col = 0; col < grid.getNbColumns(); col++) {
			for (int line = 0; line < grid.getNbLines(); line++) {
				grid.insert(col, "M");
			}
		}
		assertThrows(IllegalArgumentException.class, () -> grid.insert(grid.getNbColumns(), "M"));
	}

	@Test
	public void shouldReturnEmptyGridOnEmptyCall() {
		Grid grid = new Grid();
		grid.insert(1,"M");grid.insert(1,"M");
		grid.insert(2,"M");
		grid.insert(3,"M");

		grid.empty();

		assertTrue(grid.isEmpty());
		assertEquals(grid.toString(),EMPTYGRID);
	}
}
