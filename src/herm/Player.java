package herm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Player
{


    private String name;
    private ArrayList<Card> hand;
    private Notebook myNotebook;
    private Suspect suspect;
    private int currentLocX, currentLocY;
    private boolean canGuess = false;

	public Player (String n, Suspect s, ArrayList<Card> h)
	{
		name = n;
		hand = h;
		suspect = s;
		AIPlayer.removeSuspect(s);
        currentLocX = suspect.getStartingLoc()[1];
        currentLocY = suspect.getStartingLoc()[0];
        myNotebook = new Notebook(hand, suspect);
	}

	public boolean isCanGuess()
	{
		return canGuess;
	}

	public void setCanGuess(boolean canGuess)
	{
		this.canGuess = canGuess;
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

    public void addToNotebook(Suspect shown, Suspect shower)
    {
        myNotebook.addKnownSuspects(shown, shower);
    }

    public void addToNotebook(Weapon w, Suspect shower)
    {
        myNotebook.addKnownWeapon(w, shower);
    }

    public void addToNotebook(Room r, Suspect shower)
    {
        myNotebook.addKnownRooms(r, shower);
    }

    public Suspect[] getNotebookSuspect()
    {
        return myNotebook.getKnownSuspects();
    }

    public Weapon[] getNotebookWeapon()
    {
        return myNotebook.getKnownWeapons();
    }

    public Room[] getNotebookRoom()
    {
        return myNotebook.getKnownRooms();
    }



    public String toString()
	{
		return String.format("%s (%s)\tHand: %s\n", suspect, name, hand);
	}

}
