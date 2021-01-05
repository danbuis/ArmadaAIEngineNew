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

    public Squadrons() throws FileNotFoundException, ParsingException {
        this("assets/data/squadrons.txt");
    }

    public Squadrons(String pathToFile) throws FileNotFoundException, ParsingException {
        Scanner fileScanner = new Scanner(new File(pathToFile));
        String nextLine;
        while(fileScanner.hasNext()){
            nextLine = fileScanner.nextLine();
            if(!nextLine.equals("")){
                String[] parts = nextLine.split("\\|");
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
                    case "Faction": if(GameConstants.CURRENT_FACTIONS.contains(value)){
                            this.faction = value;
                        }else{
                            throw new ParsingException("Illegal faction : "+value+".");
                        }
                        break;
                    case "Hull": try{
                            this.hull = Integer.parseInt(value);
                            if(this.hull <= 0){
                                throw new ParsingException("Illegal integer value found for Hull : "+value+".");
                            }
                        }
                        catch(NumberFormatException e){
                            throw new ParsingException("Non integer value found for Hull : "+value+".");
                        }
                        break;
                    case "Speed": try{
                        this.speed = Integer.parseInt(value);
                        if(this.speed <= 0){
                            throw new ParsingException("Illegal integer value found for Speed : "+value+".");
                        }
                    }
                    catch(NumberFormatException e){
                        throw new ParsingException("Non integer value found for Speed : "+value+".");
                    }
                        break;
                    case "AntiShipDice": this.antiShipDice = value;
                        break;
                    case "AntiSquadronDice": this.antiSquadronDice = value;
                        break;
                    case "Keywords":  String[] keywordArray = value.split(" ");
                        this.keywords = new ArrayList<>(Arrays.asList(keywordArray));
                        break;
                    case "Points": try{
                            this.points = Integer.parseInt(value);
                            if(this.points <= 0){
                                throw new ParsingException("Illegal integer value found for Points : "+value+".");
                            }
                        }
                        catch(NumberFormatException e){
                            throw new ParsingException("Non integer value found for Points : "+value+".");
                        }
                        break;
                    case "DefenseTokens": if(value.equals("None")){
                            this.defenseTokens = new ArrayList<>();
                        }else{
                            String[] defenseTokenArray = value.split(" ");
                            this.defenseTokens = new ArrayList<>(Arrays.asList(defenseTokenArray));
                        }
                        break;
                } // end switch block
                if (checkBuildSquadron()){
                    buildSquadron();
                }
            }//end while loop
        }
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

    private boolean checkBuildSquadron() {
        return this.name!=null && this.unique!=null && this.type!=null && this.faction!=null && this.hull!=0 &&
                this.speed!=0 && this.antiShipDice!=null && this.antiSquadronDice!=null && this.keywords!=null &&
                this.points!=0 && this.defenseTokens!=null;
    }

    public Squadron getSquadron(String name){
        Squadron original = this.squadronMap.get(name);
        return new Squadron(original);
    }
}
