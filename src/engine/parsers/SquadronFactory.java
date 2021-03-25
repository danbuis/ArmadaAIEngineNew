package engine.parsers;

import gameComponents.Squadrons.Squadron;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * A class to read text files and build the Squadrons defined by that file.  It holds a single instance of every type
 * of squadron in the files in a map.  Users can retrieve a Squadron by calling the getSquadron() function, which will
 * return a copy of that Squadron.
 */
public class SquadronFactory {
    private HashMap<String, Squadron> squadronMap = new HashMap<>();
    private String name;
    private Boolean unique;
    private String type;
    private String faction;
    private int hull;
    private int speed;
    private String antiShipDice;
    private String antiSquadronDice;
    private ArrayList<String> keywords;
    private int points;
    private ArrayList<String> defenseTokens = null;

    private final int NUMBER_OF_FIELDS = 11;

    private boolean renderSquadrons;

    /**
     * Constructor To be used in a non testing space
     * @throws FileNotFoundException Exception thrown if the file does not exist
     * @throws ParsingException Exception thrown if there is an error while Parsing
     */
    public SquadronFactory() throws FileNotFoundException, ParsingException {
        this("assets/data/squadrons.txt", true);
    }


    /**
     * Constructor that uses the default file path for the input text doc.  It ends up feeding into the other constructor.
     * @throws FileNotFoundException Exception thrown if the file does not exist
     * @throws ParsingException Exception thrown if there is an error while Parsing
     */
    public SquadronFactory(boolean renderSquadrons) throws FileNotFoundException, ParsingException {
        this("assets/data/squadrons.txt", renderSquadrons);
    }

    /**
     * Constructor that takes in a file path used to create some Squadron objects
     * @param pathToFile String containing the relative file path to the desired file to parse
     * @throws FileNotFoundException Exception thrown if the file does not exist
     * @throws ParsingException Exception thrown if there is an error while Parsing
     */
    public SquadronFactory(String pathToFile, boolean renderSquadrons) throws FileNotFoundException, ParsingException {
        this.renderSquadrons = renderSquadrons;
        Scanner fileScanner = new Scanner(new File(pathToFile));
        String nextLine;
        while(fileScanner.hasNext()){
            nextLine = fileScanner.nextLine();
            if(!nextLine.equals("")){
                String[] parts = ParsingUtils.splitLine(nextLine);
                String key = parts[0];
                String value = parts[1];
                switch(key){
                    case "SquadName": this.name = value;
                        break;
                    case "Unique": if(value.equals("Y")){
                            this.unique = Boolean.TRUE;
                        }else if (value.equals("N")){
                            this.unique = Boolean.FALSE;
                        }else{
                            throw new ParsingException("Illegal unique value : "+value+".  Legal values are Y and N");
                        }
                        break;
                    case "SquadType": this.type = value;
                        break;
                    case "Faction": this.faction = ParsingUtils.validCoreFaction(value);
                        break;
                    case "Hull":this.hull= ParsingUtils.parseInteger("Hull", value);
                        break;
                    case "Speed": this.speed = ParsingUtils.parseInteger("Speed", value);
                        break;
                    case "AntiShipDice": this.antiShipDice = value;
                        break;
                    case "AntiSquadronDice": this.antiSquadronDice = value;
                        break;
                    case "Keywords":  String[] keywordArray = value.split(" ");
                        this.keywords = new ArrayList<>(Arrays.asList(keywordArray));
                        break;
                    case "Points": this.points = ParsingUtils.parseInteger("Points", value);
                        break;
                    case "DefenseTokens": if(value.equals("None")){
                            this.defenseTokens = new ArrayList<>();
                        }else{
                            String[] defenseTokenArray = value.split(" ");
                            this.defenseTokens = new ArrayList<>(Arrays.asList(defenseTokenArray));
                        }
                        break;
                } // end switch block
                if (countNonNullFields() == NUMBER_OF_FIELDS){
                    buildSquadron();
                }
            }//end if next line exists
        }//end of while
        ParsingUtils.checkNotPartialObject(countNonNullFields(), 0);
    }

    /**
     * Function to actually create a Squadron object using the values parsed from the file.  After creation we need to
     * reset the fields used to build the next squadron, that way if we have a partially completed squadron, or a squadron
     * listing out of order, we can still tell when it is complete.
     */
    private void buildSquadron() {
        Squadron newSquadron = new Squadron(this.type, this.name, this.unique, this.hull, this.speed, this.antiShipDice,
            this.antiSquadronDice, this.keywords, this.points, this.defenseTokens);
        this.squadronMap.put(newSquadron.getName(), newSquadron);

        this.resetFields();
    }

    /**
     * Reset all the fields to a neutral value that would be invalid for a new Squadron
     */
    private void resetFields() {
        this.name=null;
        this.unique=null;
        this.type=null;
        this.faction=null;
        this.hull=0;
        this.speed=0;
        this.antiShipDice=null;
        this.antiSquadronDice=null;
        this.keywords=null;
        this.points=0;
        this.defenseTokens = null;
    }

    /**
     * Count how many fields are null/void or otherwise how they are reset.  This is to tell if we have a Squadron partially
     * parsed at the end of the file.
     * @return
     */
    private int countNonNullFields() {
        int nonNullCount = 0;

        if(this.name != null){
            nonNullCount++;
        }
        if(this.unique != null){
            nonNullCount++;
        }
        if(this.type != null){
            nonNullCount++;
        }
        if(this.faction != null){
            nonNullCount++;
        }
        if(this.antiShipDice != null){
            nonNullCount++;
        }
        if(this.antiSquadronDice != null){
            nonNullCount++;
        }
        if(this.keywords != null){
            nonNullCount++;
        }
        if(this.defenseTokens != null){
            nonNullCount++;
        }
        if(this.hull != 0){
            nonNullCount++;
        }
        if(this.speed != 0){
            nonNullCount++;
        }
        if(this.points != 0){
            nonNullCount++;
        }

        return nonNullCount;
    }

    /**
     * Get a copy of a squadron from the SquadronFactory instance.
     * @param name what Squadron do you want
     * @return a copy of that Squadron
     */
    public Squadron getSquadron(String name){
        Squadron original = this.squadronMap.get(name);
        return new Squadron(original, this.renderSquadrons);
    }
}
