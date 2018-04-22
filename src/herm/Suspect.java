package herm;

public enum Suspect implements CardOptions {
	MRS_WHITE("Mrs White", 4, "W"),
	MRS_PEACOCK("Mrs Peacock", 1, "P"),
	MRS_SCARLET("Mrs Scarlet", 1, "S"),
	COL_MUSTARD("Colonel Mustard", 1, "M"),
	MR_GREEN("Mr Green", 4, "G"),
	PROF_PLUM("Professor Plum", 1, "P");

	private final String printName;
	private final int startingSpaces;
	private final String representChar;

	private Suspect (String n, int s, String c)
	{
		printName = n;
		startingSpaces = s;
		representChar = c;
	}

	public String toString()
	{
		return printName;
	}

};
