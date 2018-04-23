package herm;

public class Guess
{
	private Suspect guessSuspect;
	private Weapon guessWeapon;
	private Room guessRoom;

	public Guess (Suspect s, Weapon w, Room r)
	{
		guessRoom = r;
		guessSuspect = s;
		guessWeapon = w;
	}

	public Suspect getGuessSuspect()
	{
		return guessSuspect;
	}

	public Weapon getGuessWeapon()
	{
		return guessWeapon;
	}

	public Room getGuessRoom()
	{
		return guessRoom;
	}

	public String toString()
	{
		return String.format("Room: %s\nSuspect: %s\nWeapon: %s\n", guessRoom, guessSuspect, guessWeapon);
	}
}
