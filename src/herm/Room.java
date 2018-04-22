package herm;

public enum Room implements CardOptions {
	STUDY("Study", "Kitchen", "S"),
	LIBRARY("Library", null, "Y"),
	CONSERVATORY("Conservatory", "Lounge", "C"),
	HALL("Hall", null, "H"),
	KITCHEN("Kitchen", "Study", "K"),
	BALLROOM("Ballroom", null, "A"),
	DINING_ROOM("Dining Room", null, "D"),
	LOUNGE("Lounge", "Conservatory", "L"),
	BILLIARD_ROOM("Billiard Room", null, "I");

	private final String printName;
	private final String passageExit;
	private final String representChar;

	private Room (String n, String exit, String c)
	{
		printName = n;
		passageExit = exit;
		representChar = c;
	}

	public String getRepresentChar()
	{
		return representChar;
	}

	public Room getPassageExit()
	{
		return valueOf(passageExit);
	}

	public String toString()
	{
		return printName;
	}

};
