package engine.parsers;

import engine.GameConstants;
import gameComponents.Squadron;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Squadrons {
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

    public Squadrons() throws FileNotFoundException, ParsingException {
        this("assets/data/squadrons.txt");
    }

    public Squadrons(String pathToFile) throws FileNotFoundException, ParsingException {
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

    private void buildSquadron() {
        Squadron newSquadron = new Squadron(this.type, this.name, this.unique, this.hull, this.speed, this.antiShipDice,
            this.antiSquadronDice, this.keywords, this.points, this.defenseTokens);
        this.squadronMap.put(newSquadron.getName(), newSquadron);

        this.resetFields();
    }

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



    public Squadron getSquadron(String name){
        Squadron original = this.squadronMap.get(name);
        return new Squadron(original);
    }
}
