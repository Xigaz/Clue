package herm;

public enum Suspect implements CardOptions {
	MRS_WHITE("Mrs White", 4),
	MRS_PEACOCK("Mrs Peacock", 1),
	MRS_SCARLET("Mrs Scarlet", 1),
	COL_MUSTARD("Colonel Mustard", 1),
	MR_GREEN("Mr Green", 4),
	PROF_PLUM("Professor Plum", 1);

	private final String printName;
	private final int startingSpaces;

	private Suspect (String n, int s)
	{
		printName = n;
		startingSpaces = s;
	}

	public String toString()
	{
		return printName;
	}

};
