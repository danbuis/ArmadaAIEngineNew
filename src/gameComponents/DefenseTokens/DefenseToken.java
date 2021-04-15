package gameComponents.DefenseTokens;

import engine.GameConstants;

public class DefenseToken {
    /**
    A defense Token is a token that can be assigned to squadrons or ships. A defense token has a variety of states
    The Defense token class allows for the creation of defense tokens and allows for the various state changes
    it may undergo during normal use
     */

    //The type of token
    private GameConstants.defenseTokenType type;
    //The status of the token. All defense tokens start in the ready state
    private GameConstants.defenseTokenStatus status = GameConstants.defenseTokenStatus.READY;

    //No Null constructor

    //Typed Constructor
    public DefenseToken(GameConstants.defenseTokenType type){
        this.type = type;
    }
    //TODO: A String based constructor to use in ship/squadron parsers / factories

    public DefenseToken(String tokenType){
        switch (tokenType){
            case "Br" :
                this.type = GameConstants.defenseTokenType.BRACE;
                break;
            case "Sc" :
                this.type = GameConstants.defenseTokenType.SCATTER;
                break;
            case "Ev" :
                this.type = GameConstants.defenseTokenType.EVADE;
                break;
            case "Rd" :
                this.type = GameConstants.defenseTokenType.REDIRECT;
                break;
            case "Sal" :
                this.type = GameConstants.defenseTokenType.SALVO;
                break;
            case "Cn" :
                this.type = GameConstants.defenseTokenType.CONTAIN;
                break;
        }
    }

    /**
     * Generic getter method for token status
     * @return the status of the defense token
     */
    public GameConstants.defenseTokenStatus getStatus(){
        return status;
    }

    /**
     * Generic getter for token type
     * @return the type of defense token
     */
    public GameConstants.defenseTokenType getType(){
        return type;
    }

    /**
     * Is the defense token ready?
     * @return true if token status is ready, else false
     */
    public boolean isReady(){
        return (status == GameConstants.defenseTokenStatus.READY);
    }

    /**
     * Is the defense token exhausted
     * @return true if token status is exhausted, else false
     */
    public boolean isExhausted(){
        return (status == GameConstants.defenseTokenStatus.EXHAUSTED);
    }

    /**
     * Is the defense token discarded?
     * @return true if token status is discarded, else false
     */
    public boolean isDiscarded(){
        return (status == GameConstants.defenseTokenStatus.DISCARDED);
    }

    /**
     * Is the defense token a specific status?
     * @param status the status of the defense token
     * @return true if token status matches, else false
     */
    public boolean isStatus(GameConstants.defenseTokenStatus status){
        return (this.status == status);
    }

    /**
     * Is the defense token a specified type
     * @param type the type of defense token
     * @return true if token is the specified type, else false
     */
    public boolean isType(GameConstants.defenseTokenType type){
        return (this.type == type);
    }

    /**
     * Exhausting a token changes it from a ready state to an exhausted state.
     * Action cannot be performed on already exhausted or discarded tokens
     * @return action performed successfully
     */
    public boolean exhaust(){
        if(isReady()){
            status = GameConstants.defenseTokenStatus.EXHAUSTED;
            return true;
        } else {
            return false;
        }
    }
    /**
     * Discarding a token changes it from any non-discarded state to a discarded state
     * Action cannot be performed on already exhausted tokens
     * @return action performed successfully
     */
    public boolean discard(){
        if(!isDiscarded()){
            status = GameConstants.defenseTokenStatus.DISCARDED;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Spending a token changes a ready token to an exhausted token,
     * and turns an exhausted token to a discarded token
     * Action cannot be performed on discarded tokens
     * @return action performed successfully
     */
    public boolean spend(){
        if(isReady()){
            return exhaust();
        } else {
            if (isExhausted()) {
                return discard();
            } else {
                return false;
            }
        }
    }
    /**
     * readying a defense token changed it from an exhausted state to a ready state
     * Action cannot be performed on ready or discarded tokens
     */
    public boolean ready(){
        if(isExhausted()){
            status = GameConstants.defenseTokenStatus.READY;
            return true;
        } else {
            return false;
        }
    }
    /**
     * recovering a defense token changes it from the discarded state to the ready state
     * Action cannot be performed on ready or exhausted tokens
     */
    public boolean recover(){
        if(isDiscarded()) {
            status = GameConstants.defenseTokenStatus.READY;
            return true;
        } else {
            return false;
        }
    }

}
