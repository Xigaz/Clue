package herm;

public enum Room implements CardOptions {
	STUDY("Study", Room.KITCHEN),
	LIBRARY("Library", null),
	CONSERVATORY("Conservatory", Room.LOUNGE),
	HALL("Hall", null),
	KITCHEN("Kitchen", Room.STUDY),
	BALLROOM("Ballroom", null),
	DINING_ROOM("Dining Room", null),
	LOUNGE("Lounge", Room.CONSERVATORY),
	BILLIARD_ROOM("Billiard Room", null);

	private final String printName;
	private final Room passageExit;

	private Room (String n, Room exit)
	{
		printName = n;
		passageExit = exit;

	}

	public Room getPassageExit()
	{
		return passageExit;
	}

	public String toString()
	{
		return printName;
	}

};
