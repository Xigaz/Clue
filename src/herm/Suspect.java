package herm;

public enum Suspect implements CardOptions {
	MRS_WHITE("Mrs White", 14, 24, "W"),
	MRS_PEACOCK("Mrs Peacock", 0, 18, "P"),
	MRS_SCARLET("Mrs Scarlet", 16, 0, "S"),
	COL_MUSTARD("Colonel Mustard", 23, 7, "M"),
	MR_GREEN("Mr Green", 9, 24, "G"),
	PROF_PLUM("Professor Plum", 0, 5, "L");

	private final String printName;
	private final int startingLocX;
	private final int startingLocY;
	private final String representChar;

	private Suspect (String n, int x, int y, String c)
	{
		printName = n;
		representChar = c;
		startingLocX = x;
		startingLocY = y;
	}

	public String getRepresentChar()
	{
		return representChar;
	}

	public String toString()
	{
		return printName;
	}

	public int[] getStartingLoc()
	{
		return new int[] {startingLocX, startingLocY};
	}
};
