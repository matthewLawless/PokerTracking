import java.util.ArrayList;
import java.util.LinkedList;
public abstract class Hand {

    /*
     * Running through a poker hand:
     * Dealer deals the cards to the players that aren't
     * sitting out. In this sim, only one player, the player who we are
     * accessing the text file from, will have known cards at the beginning
     * of the hand. every other player will be constructed upon our first inter
     * action with them. there are two ways that we will encounter new players during
     * a session: 1) when we join the table, there will be a group of players already at the
     * table playing 2) when a player leaves a table that we are at, a new player will join
     * in their place. Both of these actions are part of the session and will be abstracted
     * so, in other words, they will not be tracked directly by the hand object and 
     * therefore we shouldnt really worry about them too much in our development of 
     * the hand object. those details will be abstracted to a different object like the
     * session itself or possibly a table object containing players.
     * 
     * 
     * hard part about this object is that it has a sort of sequencing that is difficult to capture
     * in an object. I have done projects with a similar challenge to them in school
     * 
     * we dont want to have to construct new players every time that we begin a new hand, this
     * would be insanely inefficient. for this reason I think it is best 
     * 
     */

     
    /**
     * An array containing the flop (3 Card objects) of the hand if there is not a winner declared preflop
     */
     private Card[] flop;

     /**
      * A card object that represents the turn card of the hand if there is not a winner declared prior
      */
     private Card turn;

     /**
      * A card object representing the river card of the hand if there is not a winner declared prior;
      */
     private Card river;

     /**
      * An array containing all of the players at the table at the start of the hand.
      */
     private Player[] playersAtTable;

     /**
      * An array containing all of the players dealt cards (AKA not sitting out or waiting for BB) in the hand
      * the player at seat 1 should be in index 0, seat 2 should be at index 1 and so on. when a player folds
      * , lets say the player in seat 2, index 1 is just null
      */
     private Player[] playersInHand;

     /**
      * The final size of the pot before it is awarded to the winner. 
      * So the amount awarded to the winner of the hand, if the hand is split
      * this is the total amount in the pot before it is split and given to the winners. 
      */
     private double potSize;

     /**
      * the correct amount in the pot at any given moment in the hand, initialized to the BB
      * + the SB + ante if its a tourney
      */
     protected double currentPotSize;

     /**
      * true if the hand has multiple winners, false otherwise (only one winner)
      */
     private boolean isSplit;

     /**
      * a list of the player(s) who won the hand. most of the time this will contain one player
      * but in the case of splits it will have >1
      */
     private ArrayList<Player> winners;

     /**
      * Specific identification number for this instance of the hand, an int of length 10.
      * 
      */
     private int handID;

     /**
      * also could probably be stored in the session object and likely will be
      * big blind of the hand
      */
     private double BB;
     /**
      * again, session object
      * small blind of the hand
      */
     private double SB;
     
     /**
      * date that the hand was played, harvested hand by hand
      */
     private String date;

     /**
      * time that the hand was played, harvested by hand
      */
     private String time;

     /**
      * could be stored in the session object
      * name of the table the hand was played at
      */
     private String tableName;

     /**
      * total number of seats at the table.
      */
     private int numberOfSeatsAtTable;

     /**
      * action of all events that happened during the hand, useful for analysis
      * and if we ever want to replay the hands in a GUI. should be added to every time
      * an event in the hand occurs.
      */
     protected LinkedList<String> action;


     public Hand(){};

     public Hand(int handID, String stakes, String date, String time, String tableName, int numberOfSeatsAtTable){

        this.handID = handID;
        this.stakes = stakes;
        this.date = date;
        this.time = time;
        this.tableName = tableName;
        this.numberOfSeatsAtTable = numberOfSeatsAtTable;

     }


     ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

       /**
        * 
        * @return array containing cards on the flop
        */
    private Card[] getFlop(){

        return flop;

    }
        /**
         * sets the hands flop to c
         * @param c
         */
    private void setFlop(Card[] c){

        if (c.length == 3){
            this.flop = c;
        }
        else{

            throw new IllegalArgumentException("flop");

        }

    }

    /**
     * 
     * 
     * @return card object that is the turn
     */
    private Card getTurn(){

        return turn;

    }

    /**
     * sets the turn of this hand to c
     * @param c
     */
    private void setTurn(Card c){

            this.turn = c;

    }

    /**
     * returns card that is the river of this hand
     * @return
     */
    private Card getRiver(){

        return river;

    }

    /**
     * sets the river of this hand to c
     * @param c
     */
    private void setRiver(Card c){

        this.river = c;

    }

    /**
     * returns a list of all the players sitting at the table
     * @return
     */
    private Player[] getPlayersAtTable(){

        return playersAtTable;

    }

    /**
     * sets player p to the seat s
     * 
     * @param p
     * @param s
     */
    private void setPlayerToSeat(Player p, int s){

        //array shows [0 1 2 3 4 5]
        //reality [1 2 3 4 5 6]
        playersAtTable[s - 1] = p;

    }

    /**
     * returns array of all players currently in the hand (aka those who have not folded)
     * 
     * @return
     */
    private Player[] getPlayersInHand(){

        return playersInHand;

    }

    /**
     * takes a player and a boolean, sets players inHand fields to the boolean input
     * note: don't know if this is necessary as player object can do this already but for now keep
     * 
     * @param p
     * @param b
     */
    private void setPlayerInHand(Player p, boolean b){

        p.setInHand(b);

    }

    /**
     * returns total pot of the hand
     * 
     * @return
     */
    private double getPotSize(){

        return potSize;

    }

    /**
     * gives the pot size in BB
     * 
     * @return
     */
    private double getPotSizeInBigs(){

        return potSize / BB;

    }

    /**
     * dont think i need this keeping for now
     * @return
     */
    private double getCurrentPotSize(){

        return currentPotSize;

    }

    /**
     * dont think i need this keeping for now
     * @return
     */
    private double getCurrentPotSizeInBigs(){

        return currentPotSize / BB;

    }

    /**
     * returns the boolean field isSplit
     * 
     * @return
     */
    private boolean getIsSplit(){

        return isSplit;

    }

    /**
     * sets the boolean field isSplit
     * 
     * @param b
     */
    private void setIsSplit(boolean b){

        isSplit = b;

    }

    /**
     * returns the list of winner(s)
     * most of the time there will be one, but we must 
     * accomadate split pots
     * 
     * @return
     */
    private ArrayList<Player> getWinners(){

        return winners;

    }

    /**
     * sets the winners of the hand, I think we need this, unless
     * I'm overlooking something simple
     * 
     * @param p
     */
    private void setWinners(Player[] p){

        for (Player e : p){

            winners.add(e);

        }

    }

    /**
     * returns field handID
     * 
     * @return
     */
    private int getHandID(){

        return handID;

    }

    private void setHandID(int id){

        handID = id;

    }

    private double getBB(){

        return BB;

    }

    private void setBB(double bb){

        this.BB = bb;

    }

    private double getSB(){

        return SB;

    }

    private void setSB(double sb){

        this.SB = sb;

    }

    private String getDate(){

        return date;

    }

    private void setDate(String s){

        this.date = s;

    }

    private String getTime(){

        return time;

    }

    private void setTime(String s){

        this.time = s;

    }


    private String getTableName(){

        return tableName;

    }

    private void setTableName(String s){

        this.tableName = s;

    }

    private int getNumberOfSeatsAtTable(){

        return numberOfSeatsAtTable;

    }

    private void setNumberOfSeatsAtTable(int n){

        this.numberOfSeatsAtTable = n;

    }



    //-----------------

    /**
     * 
     * represents a player making a bet into 
     * the pot. this is different than a raise. 
     * 
     * their difference is important. 'raising' implies that there is already an outstanding bet and that we are making the going rate more than that amount. betting is 
     * opening the action on street. if the BB is 5 and UTG makes it 15 this is a raise. if after the flop the BB and UTG are in the hand still and the BB checks and the makes
     * it 35, this is a bet, because there is no prior 'price'.
     * 
     * 
     * 
     *  changes the size of the pot, 
     * the size of the player's stack, and changes
     * the action (who's turn it is)
     * 
     * @param p
     * @param amount
     */
    private void bets(Player p, double amount){



    }

    /**
     * represents a player calling a bet.
     * flatting pre is 'calling' the BB. this 
     * effects the pot size, and the players stack
     * the idea is to have some sort of currentBet
     * field so that we just know internally how much Player p
     * is calling.
     * 
     * @param p
     */
    private void calls(Player p){



    }

    /**
     * represents a player folding their hand. doesn't change the pot or the player's stack
     * should trigger Player p's inHand field to be false. takes player p out of the 
     * playersInHand field.
     * 
     * @param p
     */
    private void folds(Player p){



    }

    /**
     * represents a player raising an already made bet that is not
     * a BB. p is the player raising, amountTotal is the total bet 
     * after player p makes the raise.
     * @param p
     * @param amountTotal
     */
    private void raise(Player p, double amountTotal){


    }

    /**
     * represents a player paying the BB at
     * the beginning of the hand. should increase the 
     * pot and decrease the players stack.
     * @param p
     */
    private void postsBB(Player p){



    }

    /**
     * represents a player paying the SB at the beginning
     * of the hand
     * @param p
     */
    private void postsSB(Player p){



    }


    
}
