package engine.parsers;

import components.ship.ArmadaShip;
import components.tokens.DefenseToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * A class to read text files and build the ArmadaShips defined by that file.  It holds a single instance of every type
 * of ship in the files in a map.  Users can retrieve a Ship by calling the getShip() function, which will
 * return a copy of that Ship.
 */
public class ShipFactory {
    private HashMap<String, ArmadaShip> shipMap = new HashMap<>();
    private String name;
    private String type;
    private ArrayList<String> keywords;
    private String faction;
    private String size;
    private int points;
    private int hull;
    private ArrayList<DefenseToken> defenseTokens = null;
    private int command;
    private int squad;
    private int engineering;
    private String speed;
    private String shields;
    private String antiShipDice;
    private String antiSquadronDice;
    private String upgrades;
    private float frontOffset;
    private float rearOffset;
    private float frontConjunction;
    private float rearConjunction;

    private String mostRecentlyCompleted = null;
    private final int NUMBER_OF_FIELDS = 20;

    /**
     * Constructor that uses the default file path for the input text doc.  It ends up feeding into the other constructor.
     * @throws FileNotFoundException Exception thrown if the file does not exist
     * @throws ParsingException Exception thrown if there is an error while Parsing
     */
    public ShipFactory() throws FileNotFoundException, ParsingException {
        this("assets/data/ships.txt");
    }

    /**
     * Constructor that takes in a file path used to create some Ship objects
     * @param pathToFile String containing the relative file path to the desired file to parse
     * @throws FileNotFoundException Exception thrown if the file does not exist
     * @throws ParsingException Exception thrown if there is an error while Parsing
     */
    public ShipFactory(String pathToFile) throws FileNotFoundException, ParsingException {
         Scanner fileScaner = new Scanner(new File(pathToFile));
         String nextLine;
         while(fileScaner.hasNext()){
             nextLine = fileScaner.nextLine();
             if(!nextLine.equals("")){
                 String[] parts = ParsingUtils.splitLine(nextLine);
                 String key = parts[0];
                 String value = parts[1];
                 switch (key) {
                     case "Name": this.name = value;
                        break;
                     case "Type": this.type = value;
                        break;
                     case "Keywords": String[] keywordArray = value.split(" ");
                         this.keywords = new ArrayList<>(Arrays.asList(keywordArray));
                         break;
                     case "Faction": this.faction = ParsingUtils.validCoreFaction(value);
                        break;
                     case "Size": this.size = value;
                        break;
                     case "Points": this.points = ParsingUtils.parseInteger("Points", value);
                        break;
                     case "Hull": this.hull = ParsingUtils.parseInteger("Hull", value);
                        break;
                     case "DefenseTokens" :
                         ArrayList<DefenseToken> tokens = new ArrayList<>();

                         if(!value.equals("None")){
                             String[] defenseTokenArray = value.split(" ");
                             for(String token: defenseTokenArray) {
                                 ParsingUtils.checkValidDefenseToken(token);
                                 tokens.add(new DefenseToken(token));
                             }
                         }
                         this.defenseTokens = tokens;
                         break;
                     case "Command" : this.command = ParsingUtils.parseInteger("Command", value);
                        break;
                     case "Squad" : this.squad = ParsingUtils.parseInteger("Squad", value);
                        break;
                     case "Eng" : this.engineering = ParsingUtils.parseInteger("Engineering", value);
                        break;
                     case "Speed" : this.speed = value;
                        break;
                     case "Shields" : this.shields = value;
                        break;
                     case "AntiShip" : this.antiShipDice = value;
                        break;
                     case "AntiSquadron" : this.antiSquadronDice = value;
                        break;
                     case "Upgrade" : this.upgrades = value;
                        break;
                     case "FrontArcOffset" : this.frontOffset = ParsingUtils.parseFloat("Front Offset", value);
                        break;
                     case "FrontArcConjunction" : this.frontConjunction = ParsingUtils.parseFloat("Front Conjunction", value);
                        break;
                     case "RearArcOffset" : this.rearOffset = ParsingUtils.parseFloat("Rear Offsest", value);
                         break;
                     case "RearArcConjunction" : this.rearConjunction = ParsingUtils.parseFloat("Rear Conjunction", value);
                         break;
                 }//end switch block
                 ArrayList<String> nulls = listNullFields();
                 if (nulls.size() == 0){
                     buildShip();
                     this.mostRecentlyCompleted = this.name;
                     this.resetFields();
                 }
             }//end if next line
         }//end of while
    }

    /**
     * Function to actually create a Ship object using the values parsed from the file.  After creation we need to
     * reset the fields used to build the next ship, that way if we have a partially completed ship, or a ship
     * listing out of order, we can still tell when it is complete.
     */
    private void buildShip() {
        ArmadaShip newShip = new ArmadaShip(name, type, keywords, faction, size, points, hull, defenseTokens, command,
                squad, engineering, speed, shields, antiShipDice, antiSquadronDice, upgrades, frontOffset,
                frontConjunction, rearOffset, rearConjunction);
        this.shipMap.put(newShip.getName(), newShip);
    }

    /**
     * Reset all the fields to a neutral value that would be invalid for a new Squadron
     */
    private void resetFields() {
        name = null;
        type = null;
        keywords = null;
        faction = null;
        size = null;
        points = 0;
        hull = 0;
        defenseTokens = null;
        command = 0;
        squad = 0;
        engineering = 0;
        speed = null;
        shields = null;
        antiShipDice = null;
        antiSquadronDice = null;
        upgrades = null;
        frontOffset = 0.0f;
        rearOffset = 0.0f;
        frontConjunction = 0.0f;
        rearConjunction = 0.0f;
    }

    /**
     * Count how many fields are null/void or otherwise how they are reset.  This is to tell if we have a Ship partially
     * parsed at the end of the file.
     * @return
     */
    private ArrayList<String> listNullFields() {
        ArrayList<String> nullFields = new ArrayList<>();

        if (name == null){
            nullFields.add("name");
        }
        if (type == null){
            nullFields.add("type");
        }
        if (keywords == null){
            nullFields.add("keywords");
        }
        if (faction == null){
            nullFields.add("faction");
        }
        if (size == null){
            nullFields.add("size");
        }
        if (points == 0){
            nullFields.add("points");
        }
        if (hull == 0){
            nullFields.add("hull");
        }
        if (defenseTokens == null){
            nullFields.add("defenseTokens");
        }
        if (command == 0){
            nullFields.add("command");
        }
        if (squad == 0){
            nullFields.add("squad");
        }
        if (engineering == 0){
            nullFields.add("engineering");
        }
        if (speed == null){
            nullFields.add("speed");
        }
        if (shields == null){
            nullFields.add("shields");
        }
        if (antiShipDice == null){
            nullFields.add("antiShipDice");
        }
        if (antiSquadronDice == null){
            nullFields.add("antiSquadronDice");
        }
        if (upgrades == null){
            nullFields.add("upgrades");
        }
        if (frontOffset == 0.0f){
            nullFields.add("frontOffset");
        }
        if (rearOffset == 0.0f){
            nullFields.add("rearOffset");
        }
        if (frontConjunction == 0.0f){
            nullFields.add("frontConjunction");
        }
        if (rearConjunction == 0.0f){
            nullFields.add("rearConjunction");
        }

        return nullFields;
    }

    /**
     * Get a copy of a ship from the ShipFactory instance.
     * @param name what Ship do you want
     * @return a copy of that Ship
     */
    public ArmadaShip getShip(String name){
        ArmadaShip original = this.shipMap.get(name);
        return new ArmadaShip(original);
    }

    public Set<String> getShipTypes(){
        return this.shipMap.keySet();
    }
}
