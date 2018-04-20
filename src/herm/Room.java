package herm;

public enum Room implements CardOptions {
	STUDY("Study", "Kitchen"),
	LIBRARY("Library", null),
	CONSERVATORY("Conservatory", "Lounge"),
	HALL("Hall", null),
	KITCHEN("Kitchen", "Study"),
	BALLROOM("Ballroom", null),
	DINING_ROOM("Dining Room", null),
	LOUNGE("Lounge", "Conservatory"),
	BILLIARD_ROOM("Billiard Room", null);

	private final String printName;
	private final String passageExit;

	private Room (String n, String exit)
	{
		printName = n;
		passageExit = exit;

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
