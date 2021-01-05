package engine.parsers;

public class ParsingUtils {
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
}
