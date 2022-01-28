package engine.parsers;

import engine.GameConstants;

import java.util.ArrayList;
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
     * A util method to validate floats.  Assumes that all strings that need to be made to floats are positive
     * and non-zero
     * @param fieldName the field name you are checkng, used to populate error messages
     * @param input the string you are validating/converting
     * @return String as a float
     * @throws ParsingException
     */
    public static float parseFloat(String fieldName, String input) throws ParsingException {
        try{
            float returnFloat = Float.parseFloat(input);
            if(returnFloat < 0){
                throw new ParsingException("Illegal float value found for "+fieldName+" : "+input+".");
            }
            return returnFloat;
        }
        catch(NumberFormatException e){
            throw new ParsingException("Non float value found for "+fieldName+" : "+input+".");
        }//end try-catch
    }//end parseFloat

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

    public static void checkNotPartialObject(ArrayList<String> nullFields, int targetNumber, String name) throws ParsingException {
        if (nullFields.size() != targetNumber){
            String errorList = "";
            for (String error : nullFields){
                errorList = errorList + " " + error +",";
            }
            //strip trailing comma
            errorList = errorList.substring(0, errorList.lastIndexOf(","));
            throw new ParsingException("Reached end of file with a partially built object, " + name +
                    ", missing:" + errorList + " fields.");
        }
    }
}
