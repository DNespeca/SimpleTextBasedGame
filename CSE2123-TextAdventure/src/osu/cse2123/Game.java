package osu.cse2123;

/**
 * An interface for items in a text adventure game
 *
 * @author Dom Nespeca
 * @version 12/08/2021
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    /**
     * Creates a list of Rooms in the map based off the input from the file
     *
     *
     * @return List<SimpleRoom> List of all rooms in the map
     */
    public static List<SimpleRoom> createMap() throws FileNotFoundException {
        File file = new File("rooms.txt");
        Scanner fileIn = new Scanner(file);
        List<SimpleRoom> gameMap = new ArrayList<SimpleRoom>();
        //while the file has more data in it, parse the data and create Room objects
        while (fileIn.hasNext()) {
            String name = fileIn.nextLine();
            String[] exprArr = fileIn.nextLine().split(",");
            String temp = "";
            temp = temp + fileIn.nextLine();
            List<String> descT = new ArrayList<String>();
            //description ends with "---" in the line following, loops through
            //until then to fill description
            while (!temp.equalsIgnoreCase("---")) {
                descT.add(temp);
                temp = fileIn.nextLine();
            }
            SimpleRoom room = createRoom(name, exprArr, descT);
            gameMap.add(room);
        }
        fileIn.close();
        return createItems(gameMap);
    }

    /**
     * Creates a Room in the map based off the input from parameters
     *
     * @param name
     *            String with the name for the Room object
     *
     * @param keys
     *            Array of Strings with the exits and their directions
     *
     * @param descList
     *            List<String> holding the description split into lines
     *            separated with System.lineseparator
     *
     *
     * @return SimpleRoom SimpleRoom object created from the parameters
     */
    public static SimpleRoom createRoom(String name, String[] keys,
            List<String> descList) {
        SimpleRoom room = new SimpleRoom();
        room.setName(name);
        //loop through array of exits to create a Map with the direction as the key
        //and the Destination as the Value
        for (int k = 0; k < keys.length; k++) {
            String[] exprArr = keys[k].split(":");
            //loop to check which direction the letter indicates, adding the full
            //direction as the key
            for (int y = 0; y < exprArr.length; y++) {
                if (exprArr[y].equalsIgnoreCase("n")) {
                    room.setExit("north", exprArr[1]);
                } else if (exprArr[y].equalsIgnoreCase("s")) {
                    room.setExit("south", exprArr[1]);
                } else if (exprArr[y].equalsIgnoreCase("e")) {
                    room.setExit("east", exprArr[1]);
                } else if (exprArr[y].equalsIgnoreCase("w")) {
                    room.setExit("west", exprArr[1]);
                } else if (exprArr[y].equalsIgnoreCase("u")) {
                    room.setExit("up", exprArr[1]);
                } else if (exprArr[y].equalsIgnoreCase("d")) {
                    room.setExit("down", exprArr[1]);
                }
            }
        }
        room.setDesc(descList);
        return room;
    }

    /**
     * Creates a list of Items in the Rooms based off the input from the file
     *
     * @param rooms
     *            List of all room objects
     *
     *
     * @return List<SimpleRoom> List of all items in the rooms
     */
    public static List<SimpleRoom> createItems(List<SimpleRoom> rooms)
            throws FileNotFoundException {
        File file = new File("items.txt");
        Scanner fileIn = new Scanner(file);
        //while item file has data, parse through creating SimpleItem objects
        while (fileIn.hasNext()) {
            String name = fileIn.nextLine();
            String desc = fileIn.nextLine();
            String room = fileIn.nextLine();
            fileIn.nextLine();
            SimpleItem item = new SimpleItem();
            item.setName(name);
            item.setDesc(desc);
            //loop to add item objects to their respective rooms
            for (int k = 0; k < rooms.size(); k++) {
                if (rooms.get(k).getName().equalsIgnoreCase(room)) {
                    rooms.get(k).addItem(item);
                }
            }
        }
        fileIn.close();
        return rooms;
    }

    /**
     * Moves player from their current Room into the new Room if possible
     *
     * @param direction
     *            String with the direction desired to move
     *
     * @param gameMap
     *            List of SimpleRoom objects
     *
     * @param playerPos
     *            int storing the player position in gameMap
     *
     *
     * @return int player position
     */
    public static int go(String direction, List<SimpleRoom> gameMap,
            int playerPos) {
        //if there isn't an exit in the desired direction, report it
        //otherwise, move the player to the new room through the exit
        if (!gameMap.get(playerPos).hasExit(direction)) {
            System.out.println("There is no exit in that direction");
        } else if (gameMap.get(playerPos).hasExit(direction)) {
            String exit = gameMap.get(playerPos).getExit(direction);
            for (int k = 0; k < gameMap.size(); k++) {
                if (gameMap.get(k).getName().equalsIgnoreCase(exit)) {
                    playerPos = k;
                }
            }
        }
        return playerPos;
    }

    /**
     * Gets item from Room and adds it into player's inventory
     *
     * @param item
     *            String with the name for the Item object to get
     *
     * @param gameMap
     *            List of SimpleRoom objects
     *
     * @param playerPos
     *            int storing the player position in gameMap
     *
     * @param inventory
     *            List<Item> holding the Items in the player's inventory
     *
     *
     * @return List<SimpleRoom> Updated list of Items in inventory
     */
    public static List<Item> get(String item, List<SimpleRoom> gameMap,
            int playerPos, List<Item> inventory) {
        String itemName = item.substring(0, 1).toUpperCase()
                + item.substring(1);
        if (gameMap.get(playerPos).hasItem(itemName)) {
            Item itemRemove = gameMap.get(playerPos).removeItem(itemName);
            System.out.println("You pick up " + itemRemove.getDesc() + "."
                    + System.lineSeparator());
            inventory.add(itemRemove);
        } else {
            System.out.println("There is no " + item + " here.");
            System.out.println();
        }
        return inventory;
    }

    /**
     * Outputs players current inventory
     *
     * @param inventory
     *            List<Item> holding the Items in the player's inventory
     *
     */
    public static void showInventory(List<Item> inventory) {
        System.out.println("You are currently carrying:");
        if (inventory.size() == 0) {
            System.out.print("   nothing" + System.lineSeparator()
                    + System.lineSeparator());
        } else if (inventory.size() == 1) {
            System.out.print("   " + inventory.get(0).getDesc()
                    + System.lineSeparator());
            System.out.println();
        } else {
            for (Item temp : inventory) {
                System.out.println(
                        "   " + temp.getDesc() + System.lineSeparator());
                System.out.println();
            }
        }
    }

    /**
     * Drops item from players inventory
     *
     * @param item
     *            String with the name for the Item object to get
     *
     * @param gameMap
     *            List of SimpleRoom objects
     *
     * @param playerPos
     *            int storing the player position in gameMap
     *
     * @param inventory
     *            List<Item> holding the Items in the player's inventory
     *
     *
     * @return List<Item> Updated list of Items in inventory
     */
    public static List<Item> drop(String item, List<SimpleRoom> gameMap,
            int playerPos, List<Item> inventory) {
        if (inventory.size() > 0) {
            for (int k = 0; k < inventory.size(); k++) {
                if (inventory.get(k).getName().equalsIgnoreCase(item)) {
                    System.out.println(
                            "You drop " + inventory.get(k).getDesc() + ".");
                    System.out.println();
                    gameMap.get(playerPos).addItem(inventory.remove(k));
                }
            }
        } else {
            System.out.println("You are not carrying a " + item);
        }
        return inventory;
    }

    /**
     * Prints first Room entry and starts game
     *
     *
     * @param gameMap
     *            List<SimpleRoom> List of Rooms in the game
     *
     */
    public static void startGame(List<SimpleRoom> gameMap) {
        System.out.print(gameMap.get(0).toString());
    }

    /**
     * Plays game continuing to prompt user for inputs until they enter quit
     *
     * @param gameMap
     *            List of SimpleRoom objects
     *
     * @param playerPos
     *            int storing the player position in gameMap
     *
     * @param inventory
     *            List<Item> holding the Items in the player's inventory
     *
     */
    public static void playGame(List<SimpleRoom> gameMap, int playerPos,
            List<Item> inventory) {
        Scanner textScanner = new Scanner(System.in);
        System.out.print(System.lineSeparator());
        System.out.print("> ");
        String input = textScanner.nextLine();
        //while the user hasn't input "quit" continue to prompt user for actions
        while (!input.equalsIgnoreCase("quit")) {
            String[] inputString = input.split(" ");
            //checks user input and determines which action to take, calling the
            //appropriate method for each action
            if (inputString[0].equalsIgnoreCase("go")) {
                playerPos = go(inputString[1], gameMap, playerPos);
                System.out.println(gameMap.get(playerPos).toString());
            } else if (inputString[0].equalsIgnoreCase("get")) {
                inventory = get(inputString[1], gameMap, playerPos, inventory);
                System.out.println(gameMap.get(playerPos).toString());
            } else if (inputString[0].equalsIgnoreCase("drop")) {
                inventory = drop(inputString[1], gameMap, playerPos, inventory);
                System.out.println(gameMap.get(playerPos).toString());
            } else if (inputString[0].equalsIgnoreCase("inventory")) {
                showInventory(inventory);
                System.out.println(gameMap.get(playerPos).toString());
            }

            System.out.print("> ");
            input = textScanner.nextLine();
        }
        System.out.println("Are you sure you want to quit?");
        System.out.print("> ");
        input = textScanner.nextLine();
        //double checks with user to confirm they want to quit
        if (input.equalsIgnoreCase("no") || input.equalsIgnoreCase("n")) {
            startGame(gameMap);
            playGame(gameMap, playerPos, inventory);
        }
        textScanner.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        int playerPos = 0;
        List<SimpleRoom> gameMap = createMap();
        List<Item> inventory = new ArrayList<Item>();
        startGame(gameMap);
        playGame(gameMap, playerPos, inventory);

    }

}
