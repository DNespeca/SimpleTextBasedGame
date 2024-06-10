/**
 *
 */
package osu.cse2123;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Dom Nespeca
 * @version 12/08/2021
 *
 */
public class SimpleRoom implements Room {
    // private member variable(s)
    private String name;
    private List<String> desc;
    private Map<String, String> exits;
    private Map<String, Item> items;

    /**
     * No-argument constructor - creates an empty PlayList
     *
     * @postcond Room object is empty
     */
    public SimpleRoom() {
        this.name = "";
        this.desc = new ArrayList<String>();
        this.exits = new HashMap<String, String>();
        this.items = new HashMap<String, Item>();
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
    public void setDesc(List<String> desc) {
        this.desc = desc;
    }

    @Override
    public String getDesc() {
        String desc = "";
        for (int k = 0; k < this.desc.size(); k++) {
            desc = desc + this.desc.get(k) + System.lineSeparator();
        }
        return desc;
    }

    @Override
    public void setExit(String direction, String dest) {
        this.exits.put(direction, dest);
    }

    @Override
    public boolean hasExit(String direction) {
        boolean hasExit = false;
        if (this.exits.containsKey(direction)) {
            hasExit = true;
        }
        return hasExit;
    }

    @Override
    public String getExit(String direction) {
        return this.exits.get(direction);
    }

    @Override
    public void addItem(Item it) {
        this.items.put(it.getName(), it);
    }

    @Override
    public boolean hasItem(String name) {
        boolean hasItem = false;
        if (this.items.containsKey(name)) {
            hasItem = true;
        }
        return hasItem;
    }

    @Override
    public Item removeItem(String name) {
        Item remove = new SimpleItem();
        if (this.hasItem(name)) {
            remove = this.items.remove(name);
        }
        return remove;
    }

    @Override
    public String toString() {
        String room = "";
        room = this.getName() + System.lineSeparator();
        room = room + this.getDesc();
        room = room + "There are exits in the following directions: ";
        int cnt = 0;
        //if statements to check which exits are present in the room before printing
        if (this.exits.containsKey("east")) {
            room = room + "east";
            cnt++;
        }
        if (this.exits.containsKey("north")) {
            if (cnt > 0) {
                room = room + ", north";
            } else {
                room = room + "north";
            }
            cnt++;
        }
        if (this.exits.containsKey("south")) {
            if (cnt > 0) {
                room = room + ", south";
            } else {
                room = room + "south";
            }
            cnt++;
        }
        if (this.exits.containsKey("west")) {
            if (cnt > 0) {
                room = room + ", west";
            } else {
                room = room + "west";
            }
            cnt++;
        }
        if (this.exits.containsKey("up")) {
            if (cnt > 0) {
                room = room + ", up";
            } else {
                room = room + "up";
            }
            cnt++;
        }
        if (this.exits.containsKey("down")) {
            if (cnt > 0) {
                room = room + ", down";
            } else {
                room = room + "down";
            }
            cnt++;
        }
        room = room + System.lineSeparator();
        //if statements to check for any items in the room and print them
        if (this.hasItem("Flashlight")) {
            room = room + this.items.get("Flashlight").toString()
                    + System.lineSeparator();
        }
        if (this.hasItem("Mirror")) {
            room = room + this.items.get("Mirror").toString()
                    + System.lineSeparator();
        }
        if (this.hasItem("Bowl")) {
            room = room + this.items.get("Bowl").toString()
                    + System.lineSeparator();
        }
        if (this.hasItem("Knife")) {
            room = room + this.items.get("Knife").toString()
                    + System.lineSeparator();
        }

        return room;
    }

}
