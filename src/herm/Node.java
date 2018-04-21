package herm;

import java.util.ArrayList;

public class Node
{
	private ArrayList<Player> occupants;
	private Room roomName;
	private boolean accessable;
	private Weapon itemInRoom;

    public Node()
    {
        roomName = null;
        accessable = true;
        itemInRoom = null;
    }

    public Node(Room rm, boolean a, Weapon item)
    {
        roomName = rm;
        accessable = a;
        itemInRoom = item;
    }

    public Node(Room rm, boolean a)
    {
        roomName = rm;
        accessable = a;
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

        if (roomName != null)
        {
            returnValue = itemInRoom;
            itemInRoom = w;
        }
        return returnValue;
    }

    public Room getRoomName()
    {
        return roomName;
    }

    public boolean isAccessable()
    {
        return accessable;
    }

    public Weapon getItemInRoom()
    {
        return itemInRoom;
    }






}
