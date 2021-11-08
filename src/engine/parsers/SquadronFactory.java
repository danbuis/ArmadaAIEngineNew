package engine.parsers;

import gameComponents.DefenseTokens.DefenseToken;
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
    private ArrayList<DefenseToken> defenseTokens = null;
    private String mostRecentlyCompleted = null;

    private final int NUMBER_OF_FIELDS = 11;


    /**
     * Constructor that uses the default file path for the input text doc.  It ends up feeding into the other constructor.
     * @throws FileNotFoundException Exception thrown if the file does not exist
     * @throws ParsingException Exception thrown if there is an error while Parsing
     */
    public SquadronFactory() throws FileNotFoundException, ParsingException {
        this("assets/data/squadrons.txt");
    }

    /**
     * Constructor that takes in a file path used to create some Squadron objects
     * @param pathToFile String containing the relative file path to the desired file to parse
     * @throws FileNotFoundException Exception thrown if the file does not exist
     * @throws ParsingException Exception thrown if there is an error while Parsing
     */
    public SquadronFactory(String pathToFile) throws FileNotFoundException, ParsingException {
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
                    case "DefenseTokens":
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
                } // end switch block
                ArrayList<String> nulls = listNullFields();
                if (nulls.size() == 0){
                    buildSquadron();
                    this.mostRecentlyCompleted = this.name;
                    this.resetFields();
                }
            }//end if next line exists
        }//end of while
        ParsingUtils.checkNotPartialObject(listNullFields(), NUMBER_OF_FIELDS, this.mostRecentlyCompleted);
    }

    /**
     * Function to actually create a Squadron object using the values parsed from the file.  After creation we need to
     * reset the fields used to build the next squadron, that way if we have a partially completed squadron, or a squadron
     * listing out of order, we can still tell when it is complete.
     */
    private void buildSquadron() {
        Squadron newSquadron = new Squadron(this.type, this.name, this.unique, this.faction, this.hull, this.speed, this.antiShipDice,
            this.antiSquadronDice, this.keywords, this.points, this.defenseTokens);
        this.squadronMap.put(newSquadron.getName(), newSquadron);
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
    private ArrayList<String> listNullFields() {
        ArrayList<String> nullFields = new ArrayList<>();

        if(this.name == null){
            nullFields.add("name");
        }
        if(this.unique == null){
            nullFields.add("unique");
        }
        if(this.type == null){
            nullFields.add("type");
        }
        if(this.faction == null){
            nullFields.add("faction");
        }
        if(this.antiShipDice == null){
            nullFields.add("anti-ship dice");
        }
        if(this.antiSquadronDice == null){
            nullFields.add("anti-squadron dice");
        }
        if(this.keywords == null){
            nullFields.add("keywords");
        }
        if(this.defenseTokens == null){
            nullFields.add("defense tokens");
        }
        if(this.hull == 0){
            nullFields.add("hull");
        }
        if(this.speed == 0){
            nullFields.add("speed");
        }
        if(this.points == 0){
            nullFields.add("points");
        }

        return nullFields;
    }

    /**
     * Get a copy of a squadron from the SquadronFactory instance.
     * @param name what Squadron do you want
     * @return a copy of that Squadron
     */
    public Squadron getSquadron(String name){
        Squadron original = this.squadronMap.get(name);
        return new Squadron(original);
    }

    /**
     *
     */
    public Set<String> getSquadronTypes(){
        return this.squadronMap.keySet();
    }
}
