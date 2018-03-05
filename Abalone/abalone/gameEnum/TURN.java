package abalone.gameEnum;


public enum TURN {

    PLAYER1("PLAYER1"),

    PLAYER2("PLAYER2");
    
    private final String text;
    
    TURN(final String text) {
        this.text = text;
    }
    
    @Override
    public String toString() {
        return text;
    }

}