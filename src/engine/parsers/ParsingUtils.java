package engine.parsers;

public class ParsingUtils {
    /**
     * A util method to validate ints.  Assumes that all Strings that need to be made into ints need to be
     * both non-zero and positive.
     * @param fieldName the field name you are checking, used to populate the error messages
     * @param input the String you are validating/converting
     * @return String as an int.
     * @throws ParsingException
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
}
