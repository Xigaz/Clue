package herm;

public enum Weapon implements CardOptions {
	KNIFE("Knife"),
	REVOLVER("Revolver"),
	PIPE("Lead Pipe"),
	CANDLESTICK("Candlestick"),
	WRENCH("Wrench"),
	ROPE("Rope");

	private final String printName;

	private Weapon (String n)
	{
		printName = n;
	}

	public String toString()
	{
		return printName;
	}

};
