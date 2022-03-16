package connectFour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grid {

	private final String EMPTY_SPOT = "-";
	private final String[][] matrix;
	private final int nbLines = 6;
	private final int nbColumns = 7;

	public Grid() {
		this.matrix = new String[nbLines][nbColumns];
		for (String[] Strings : matrix) {
			Arrays.fill(Strings, EMPTY_SPOT);
		}
	}

	public int getNbLines() {
		return nbLines;
	}

	public int getNbColumns() {
		return nbColumns;
	}

	public void insert(int column, String token) {

		if (column < 0 || column > nbColumns - 1) {
			throw new IllegalArgumentException("Column should be within 0 and " + (nbColumns - 1));
		}

		if (token.equals(EMPTY_SPOT)){
			throw new IllegalArgumentException("Cannot insert empty spot to grid");
		}

		boolean foundEmptySpot = false;
		for (int line = nbLines - 1; line >= 0; line--) {
			if (this.matrix[line][column].equals(EMPTY_SPOT)) {
				foundEmptySpot = true;
				this.matrix[line][column] = token;
				break;
			}
		}
		if (!foundEmptySpot)
			throw new IllegalArgumentException("Column is full");
	}

	public void empty() {
		for (int i = 0; i < nbLines; i++) {
			for (int j = 0; j < nbColumns; j++) {
				matrix[i][j] = EMPTY_SPOT;
			}
		}
	}

	public boolean isEmpty() {
		for (int i = 0; i < nbLines; i++) {
			for (int j = 0; j < nbColumns; j++) {
				if (!matrix[i][j].equals(EMPTY_SPOT)) return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		List<String> tempo = new ArrayList<>();
		for (String[] line : matrix) {
			tempo.add(String.join("", line));
		}
		return String.join("\n", tempo);
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (!(other instanceof Grid)) return false;
		Grid grid = (Grid) other;
		return nbLines == grid.nbLines && nbColumns == grid.nbColumns && Arrays.equals(matrix, grid.matrix);
	}
}
