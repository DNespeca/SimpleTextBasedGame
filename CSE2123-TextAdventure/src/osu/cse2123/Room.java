package osu.cse2123;

/**
 * An interface for rooms in a text adventure game
 *
 * @author Dom Nespeca
 * @version 12/08/2021
 */
import java.util.List;

public interface Room {

    /**
     * Sets the name of the room
     *
     * @param name
     *            name to set the room to
     * @postcond the name of this room will be the name parameter
     */
    public void setName(String name);

    /**
     * Returns the name of the room
     *
     * @return the name of the room
     */
    public String getName();

    /**
     * Sets the description of room as lines of text
     *
     * @param desc
     *            the text of the room description
     * @postcond the description of the room is set to desc
     */
    public void setDesc(List<String> desc);

    /**
     * Returns the description of the room as a block of text
     *
     * @return a single String that holds the description of the room, with each
     *         line separated by a line separator character
     */
    public String getDesc();

    /**
     * Sets the exit in a room to a particular destination
     *
     * @param direction
     *            the direction of the exit
     * @param dest
     *            the name of the room the exit leads to
     * @postcond adds the direction and its destination as exits to this room
     */
    public void setExit(String direction, String dest);

    /**
     * Checks to see if an exit exists in a particular direction
     *
     * @param direction
     *            direction to check for an exit
     * @return true if there is an exit in that direction, false otherwise
     */
    public boolean hasExit(String direction);

    /**
     * Returns the room that connects with a direction
     *
     * @param direction
     *            direction to get the room for
     * @precond this room has an exit in the direction given
     * @return the name of the room connected to this room via the exit
     *         direction
     */
    public String getExit(String direction);

    /**
     * Adds an item to the room
     *
     * @param it
     *            item to add to the room
     * @postcond the item it is now in the room
     */
    public void addItem(Item it);

    /**
     * Checks to see if an item is in the room
     *
     * @param name
     *            name of the item to look for
     * @return true if the item is in the room, false otherwise
     */
    public boolean hasItem(String name);

    /**
     * Removes the item from the room
     *
     * @param name
     *            name of the item to look for
     * @precond an item associated with the variable name is in the room
     * @postcond the item named name is no longer in this room
     * @return the item associated with the name given
     */
    public Item removeItem(String name);

}
