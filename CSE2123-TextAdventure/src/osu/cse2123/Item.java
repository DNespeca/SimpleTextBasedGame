package osu.cse2123;

/**
 * An interface for items in a text adventure game
 *
 * @author Dom Nespeca
 * @version 12/08/2021
 *
 */

public interface Item {

    /**
     * Sets the name of the item
     *
     * @param name
     *            name to set the item to
     * @postcond the name of this item will be the name parameter
     */
    public void setName(String name);

    /**
     * Returns the name of the item
     *
     * @return the name of the item
     */
    public String getName();

    /**
     * Sets the description of the item
     *
     * @param desc
     *            String to use as the description
     * @postcond this item will have desc as a description
     */
    public void setDesc(String desc);

    /**
     * Returns the description of the item
     *
     * @return the description of the item
     */
    public String getDesc();

}
