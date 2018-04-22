package herm;

import java.util.ArrayList;

public class Node
{
	private ArrayList<Player> occupants = new ArrayList<>();
	private Room room;
	private boolean accessible;
	private Weapon itemInRoom;

    /**
     *  Used for Hallways
     */
    public Node()
    {
        room = null;
        accessible = true;
        itemInRoom = null;
    }

    /**
     * Used for Room Squares that are impassible
     * @param a If the node is passible
     */
    public Node(boolean a)
    {
        room = null;
        itemInRoom = null;
        accessible = a;
    }

    /**
     * Generic Constructor used to do most of the work
     * @param rm    Room this Node represents
     * @param item  Any item that this Node holds
     */
    public Node(Room rm, Weapon item)
    {
        room = rm;
        accessible = true;
        itemInRoom = item;
    }

    /**
     * Used to Create a room without a weapon
     * @param rm    Room represented by this Node
     */
    public Node(Room rm)
    {
        room = rm;
        accessible = true;
        itemInRoom = null;
    }

    public void playerMoveIn(Player p)
    {
        occupants.add(p);
    }

    public void playerMoveOut(Player p)
    {
        occupants.remove(p);
    }

    public Weapon putWeaponInRoom(Weapon w)
    {
        Weapon returnValue = null;

        if (room != null)
        {
            returnValue = itemInRoom;
            itemInRoom = w;
        }
        return returnValue;
    }

    public Room getRoom()
    {
        return room;
    }

    public boolean isAccessible()
    {
        return accessible;
    }

    public Weapon getItemInRoom()
    {
        return itemInRoom;
    }

    public ArrayList<Player> getOccupants()
    {
        return occupants;
    }




}
