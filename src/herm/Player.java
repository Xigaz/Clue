package herm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Player
{
	private static ArrayList<String> nameOptions = new ArrayList<>(Arrays.asList(
			"Phineas",
			"Ferb",
			"Dr. Doof",
			"Candace",
			"Perry",
			"Baljeet",
			"Carl",
			"Isabella",
			"Buford",
			"Jeremy",
			"Stacy",
			"Vanessa"
	));

	private static ArrayList<Suspect> suspectOptions = new ArrayList<>(Arrays.asList(Suspect.values()));

	private String name;
	private ArrayList<Card> hand;
	private Notebook myNotebook;
    private Suspect suspect;

	public Player (String n, Suspect s, ArrayList<Card> h)
	{
		name = n;
		hand = h;
		suspect = s;
		Player.removeSuspect(s);
	}

	public Player (ArrayList<Card> h)
	{
		name = Player.getNameOption();
		hand = h;
		suspect = Player.getSuspectOption();
	}

	public ArrayList<Card> getHand()
	{
		return hand;
	}

	public String getName()
	{
		return name;
	}

	public Suspect getSuspect()
    {
        return suspect;
    }

	//TODO: Handle printing Notebook
	//TODO:

    private static Suspect getSuspectOption()
    {
        return suspectOptions.remove(new Random().nextInt(suspectOptions.size()));
    }

    private static void removeSuspect(Suspect x)
    {
        suspectOptions.remove(x);
    }

	private static String getNameOption()
	{
		return nameOptions.remove(new Random().nextInt(nameOptions.size()));
	}

	public static void resetSuspectOptions()
    {
        suspectOptions = new ArrayList<>(Arrays.asList(Suspect.values()));
    }

	public static void resetNameOptions()
	{
		nameOptions = new ArrayList<>(Arrays.asList(
				"Phineas",
				"Ferb",
				"Dr. Doof",
				"Candace",
				"Perry",
				"Baljeet",
				"Carl",
				"Isabella",
				"Buford",
				"Jeremy",
				"Stacy",
				"Vanessa"
		));
	}

	public String toString()
	{
		return String.format("%s (%s)\tHand: %s\n", suspect, name, hand);
	}

}
