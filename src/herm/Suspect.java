package herm;

public enum Suspect implements CardOptions {
	MRS_WHITE("Mrs White"),
	MRS_PEACOCK("Mrs Peacock"),
	MRS_SCARLET("Mrs Scarlet"),
	COL_MUSTARD("Colonel Mustard"),
	MR_GREEN("Mr Green"),
	PROF_PLUM("Professor Plum");

	private final String printName;

	private Suspect (String n)
	{
		printName = n;
	}

	public String toString()
	{
		return printName;
	}

};
