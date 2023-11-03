package herm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Notebook
{
    Map<Weapon, Suspect> knownWeapons = new HashMap<>();
    Map<Room, Suspect> knownRooms = new HashMap<>();
    Map<Suspect, Suspect> knownSuspects = new HashMap<>();

    public Notebook(ArrayList<Card> cards, Suspect me)
    {
        System.out.println();
        for(Card x : cards)
        {
            CardOptions co = x.getTitle();

            if (co instanceof Suspect)
                knownSuspects.put((Suspect) co, me);
            else if (co instanceof Room)
                knownRooms.put((Room) co, me);
            else if (co instanceof Weapon)
                knownWeapons.put((Weapon) co, me);
        }
    }

    public Weapon[] getKnownWeapons() {
        Weapon[] w = new Weapon[knownWeapons.keySet().toArray().length];
		int i = 0;
		for(Object x : knownWeapons.keySet())
		{
			w[i] = (Weapon) x;
			i++;
		}

    	return w;
    }

    public void addKnownWeapon(Weapon w, Suspect s) {
        this.knownWeapons.put(w, s);
    }

    public Room[] getKnownRooms() {
        Room[] r = new Room[knownRooms.keySet().toArray().length];
    	int i = 0;
        for(Object x : knownRooms.keySet())
		{
			r[i] = (Room) x;
			i++;
		}
    	return r;
    }

    public void addKnownRooms(Room r, Suspect s) {
        this.knownRooms.put(r, s);
    }

    public Suspect[] getKnownSuspects() {
    	Suspect[] s = new Suspect[knownSuspects.keySet().toArray().length];
		int i = 0;
		for(Object x : knownSuspects.keySet())
		{
			s[i] = (Suspect) x;
			i++;
		}

        return s;
    }

    public double percentComplete()
    {
        return (knownRooms.size() + knownSuspects.size() + knownWeapons.size()) * 1.0 / (Suspect.values().length + Room.values().length + Weapon.values().length);
    }

    /**
     * Add suspect to known list
     * @param k The Suspect to be added to the known list
     * @param v Who showed the Suspect
     */
    public void addKnownSuspects(Suspect k, Suspect v) {
        this.knownSuspects.put(k, v);
    }

}
