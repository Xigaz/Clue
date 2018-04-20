package herm;

public enum Room implements CardOptions {
	STUDY("Study"),
	LIBRARY("Library"),
	CONSERVATORY("Conservatory"),
	HALL("Hall"),
	KITCHEN("Kitchen"),
	BALLROOM("Ballroom"),
	DINING_ROOM("Dining Room"),
	LOUNGE("Lounge"),
	BILLARD_ROOM("Billard Room");

	private final String printName;

	private Room (String n)
	{
		printName = n;
	}

	public String toString()
	{
		return printName;
	}

};
