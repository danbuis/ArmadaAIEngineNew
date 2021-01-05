package engine.parsers;

import gameComponents.Squadron;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Squadrons {
    public HashMap<String, Squadron> squadronMap = new HashMap<>();
    private String squadronsTextPath = "assets/data/squadrons.txt";
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

    public Squadrons() throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File(squadronsTextPath));
        String nextLine;
        while(fileScanner.hasNext()){
            nextLine = fileScanner.nextLine();
            if(!nextLine.equals("")){
                String[] parts = nextLine.split("|");
                String key = parts[0];
                String value = parts[1];
                switch(key){
                    case "SquadName": this.name = value;
                        break;
                    case "Unique": this.unique = value.equals("Y");
                        break;
                    case "SquadType": this.type = value;
                        break;
                    case "Faction": this.faction = value;
                        break;
                    case "Hull": this.hull = Integer.parseInt(value);
                        break;
                    case "Speed": this.speed = Integer.parseInt(value);
                        break;
                    case "AntiShipDice": this.antiShipDice = value;
                        break;
                    case "AntiSquadronDice": this.antiSquadronDice = value;
                        break;
                    case "Keywords":  String[] keywordArray = value.split(" ");
                        this.keywords = (ArrayList<String>) Arrays.asList(keywordArray);
                        break;
                    case "Points": this.points = Integer.parseInt(value);
                        break;
                    case "DefenseTokens": String[] defenseTokenArray = value.split(" ");
                        this.defenseTokens = (ArrayList<String>) Arrays.asList(defenseTokenArray);
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
}
