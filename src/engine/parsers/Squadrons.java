package engine.parsers;

import gameComponents.Squadron;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Squadrons {
    public HashMap<String, Squadron> squadronMap = new HashMap<>();
    private String squadronsTextPath = "assets/data/squadrons.txt";
    private String name, unique, type, faction, hull, speed, antiShipDice, antiSquadronDice,
            keywords, points, defenseTokens = null;

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
                    case "Unique": this.unique = value;
                        break;
                    case "SquadType": this.type = value;
                        break;
                    case "Faction": this.faction = value;
                        break;
                    case "Hull": this.hull = value;
                        break;
                    case "Speed": this.speed = value;
                        break;
                    case "AntiShipDice": this.antiShipDice = value;
                        break;
                    case "AntiSquadronDice": this.antiSquadronDice = value;
                        break;
                    case "Keywords": this.keywords = value;
                        break;
                    case "Points": this.points = value;
                        break;
                    case "DefenseTokens": this.defenseTokens = value;
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
        this.hull=null;
        this.speed=null;
        this.antiShipDice=null;
        this.antiSquadronDice=null;
        this.keywords=null;
        this.points=null;
        this.defenseTokens = null;
    }

    private boolean checkBuildSquadron() {
        return this.name!=null && this.unique!=null && this.type!=null && this.faction!=null && this.hull!=null &&
                this.speed!=null && this.antiShipDice!=null && this.antiSquadronDice!=null && this.keywords!=null &&
                this.points!=null && this.defenseTokens!=null;
    }
}
