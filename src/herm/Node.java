package herm;

import java.util.ArrayList;

public class Node
{
	private ArrayList<Suspect> occupants = new ArrayList<>();

	private Room room;
	private boolean accessible;
	private Weapon itemInRoom;
	private int nodeLocX;
	private int nodeLocY;
	private int weight = 1;

    /**
     *  Used for Hallways
     */
    public Node(int x, int y)
    {
        room = null;
        accessible = true;
        itemInRoom = null;
        nodeLocX = x;
        nodeLocY = y;
    }

    /**
     * Used for Room Squares that are impassible
     * @param a If the node is passible
     */
    public Node(boolean a, int x, int y)
    {
        room = null;
        itemInRoom = null;
        accessible = a;
        weight = a ? 1 : 99;
    }

    /**
     * Generic Constructor used to do most of the work
     * @param rm    Room this Node represents
     * @param item  Any item that this Node holds
     */
    public Node(Room rm, Weapon item, int x, int y)
    {
        room = rm;
        accessible = true;
        itemInRoom = item;
        weight = 1;
    }

    /**
     * Used to Create a room without a weapon
     * @param rm    Room represented by this Node
     */
    public Node(Room rm, int x, int y)
    {
        room = rm;
        accessible = true;
        itemInRoom = null;
        weight = 1;
    }

	public int getNodeLocX()
	{
		return nodeLocX;
	}

	public int getNodeLocY()
	{
		return nodeLocY;
	}

	public void playerMoveIn(Suspect s)
    {
        occupants.add(s);
    }

    public void playerMoveOut(Suspect s)
    {
        occupants.remove(s);
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

    public ArrayList<Suspect> getOccupants()
    {
        return occupants;
    }




}
