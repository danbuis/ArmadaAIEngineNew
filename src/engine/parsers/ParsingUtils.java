package engine.parsers;

import engine.GameConstants;

import java.util.Arrays;

public class ParsingUtils {
        private static String[] VALID_DEFENSE_TOKEN_ABBR ={"Br", "Rd", "Ev", "Sc", "Cn", "Sal"};

    /**
     * A util method to validate ints.  Assumes that all Strings that need to be made into ints need to be
     * both non-zero and positive.
     * @param fieldName the field name you are checking, used to populate the error messages
     * @param input the String you are validating/converting
     * @return String as an int.
     * @throws ParsingException Exception thrown if the input is bad
     */
    public static int parseInteger(String fieldName, String input) throws ParsingException {
        try{
            int returnInteger = Integer.parseInt(input);
            if(returnInteger <= 0){
                throw new ParsingException("Illegal integer value found for "+fieldName+" : "+input+".");
            }
            return returnInteger;
        }
        catch(NumberFormatException e){
            throw new ParsingException("Non integer value found for "+fieldName+" : "+input+".");
        }//end try-catch
    }//end parseInteger

    /**
     * A util method to validate a line and split it into a key/value pair.
     * @param line the line of text from the input file
     * @return an array of parts of the line
     * @throws ParsingException Exception thrown if the input is bad
     */
    public static String[] splitLine(String line) throws ParsingException {
        String[] parts = line.split("\\|");
        if(parts.length != 2){
            throw new ParsingException("All rows must have 1 '|' character with content both before and after. : "+line+".");
        }
        return parts;
    }

    /**
     * A util method to validate a faction name
     * @param faction the faction name to check
     * @return the same faction name if it is a valid faction
     * @throws ParsingException Exception thrown if the input is bad
     */
    public static String validCoreFaction(String faction) throws ParsingException {
        if(GameConstants.CURRENT_FACTIONS.contains(faction)){
           return faction;
        }else{
            throw new ParsingException("Illegal faction : "+faction+".");
        }
    }

    /**
     * A util function to validate a defense token input
     * @param token abbreviation of token
     * @throws ParsingException Exception thrown if the input is bad
     */
    public static void checkValidDefenseToken(String token) throws ParsingException {
        if(!Arrays.asList(VALID_DEFENSE_TOKEN_ABBR).contains(token)){
            throw new ParsingException("Invalid defense token passed in : " + token + ".");
        }
    }

    public static void checkNotPartialObject(int nonNullCount, int targetNumber, String name) throws ParsingException {
        if (nonNullCount != targetNumber){
            throw new ParsingException("Reached end of file with a partially built object, " + name +
                    ", missing " + nonNullCount + " fields.");
        }
    }
}
