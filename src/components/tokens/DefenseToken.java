package components.tokens;

public class DefenseToken {
    /*
    A defense Token is a token that can be assigned to squadrons or ships. A defense token has a variety of states
    The Defense token class allows for the creation of defense tokens and allows for the various state changes
    it may undergo during normal use
     */

    /**
     *Defense Token Constants
     */
    public enum Type {BRACE, REDIRECT, EVADE, SCATTER, CONTAIN, SALVO}

    public enum Status {READY, EXHAUSTED, DISCARDED}

    /**
     * Defense Token fields
     */
    //The type of token
    private Type type;
    //The status of the token. All defense tokens start in the ready state
    private Status status = Status.READY;

    //No Null constructor

    //Typed Constructor
    public DefenseToken(Type type){
        this.type = type;
    }
    //TODO: A String based constructor to use in ship/squadron parsers / factories

    public DefenseToken(String tokenType){
        switch (tokenType){
            case "Brace" :
                this.type = Type.BRACE;
                break;
            case "Scatter" :
                this.type = Type.SCATTER;
                break;
            case "Evade" :
                this.type = Type.EVADE;
                break;
            case "Redirect" :
                this.type = Type.REDIRECT;
                break;
            case "Salvo" :
                this.type = Type.SALVO;
                break;
            case "Contain" :
                this.type = Type.CONTAIN;
                break;
        }
    }

    /**
     * Generic getter method for token status
     * @return the status of the defense token
     */
    public Status getStatus(){
        return status;
    }

    /**
     * Generic getter for token type
     * @return the type of defense token
     */
    public Type getType(){
        return type;
    }

    /**
     * Is the defense token ready?
     * @return true if token status is ready, else false
     */
    public boolean isReady(){
        return (status == Status.READY);
    }

    /**
     * Is the defense token exhausted
     * @return true if token status is exhausted, else false
     */
    public boolean isExhausted(){
        return (status == Status.EXHAUSTED);
    }

    /**
     * Is the defense token discarded?
     * @return true if token status is discarded, else false
     */
    public boolean isDiscarded(){
        return (status == Status.DISCARDED);
    }

    /**
     * Is the defense token a specific status?
     * @param status the status of the defense token
     * @return true if token status matches, else false
     */
    public boolean isStatus(Status status){
        return (this.status == status);
    }

    /**
     * Is the defense token a specified type
     * @param type the type of defense token
     * @return true if token is the specified type, else false
     */
    public boolean isType(Type type){
        return (this.type == type);
    }

    /**
     * Exhausting a token changes it from a ready state to an exhausted state.
     * Action cannot be performed on already exhausted or discarded tokens
     * @return action performed successfully
     */
    public boolean exhaust(){
        if(isReady()){
            status = Status.EXHAUSTED;
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
            status = Status.DISCARDED;
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
            status = Status.READY;
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
            status = Status.READY;
            return true;
        } else {
            return false;
        }
    }


}
