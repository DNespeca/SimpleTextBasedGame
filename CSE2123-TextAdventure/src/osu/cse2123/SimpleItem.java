package osu.cse2123;

/**
 * An interface for items in a text adventure game
 *
 * @author Dom Nespeca
 * @version 12/08/2021
 *
 */

public class SimpleItem implements Item {
    // private member variable(s)
    private String name;
    private String desc;

    /**
     * No-argument constructor - creates an empty PlayList
     *
     * @postcond Item object is empty
     */
    public SimpleItem() {
        this.name = "";
        this.desc = "";
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    @Override
    public String toString() {
        String item = "";
        item = item + "There is " + this.getDesc() + " here.";
        return item;
    }

}
