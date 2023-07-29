import java.io.*;
public class Player{

    private double currentStack;
    private boolean inHand;
    private boolean sittingOut;
    private String username;
    private boolean isButton;
    private Card[] cards;
    private int seatNumber;
    
    /**
     * constructor that just takes username, then must
     * manually set other private fields at a later time
     * 
     * @param username
     */
    public Player(String username, int seatNumber, double currentStack){

        this.username = username;
        this.seatNumber = seatNumber;
        this.currentStack = currentStack;

    }

    /**
     * full constructor ---> initializes all private fields
     * leaves cards as null
     * 
     * @param username
     * @param currentStack
     * @param inHand
     * @param sittingOut
     * @param isButton
     * @param seatNumber
     */
    public Player(String username, double currentStack, boolean inHand, boolean sittingOut, boolean isButton, int seatNumber){

        this.username = username;
        this.currentStack = currentStack;
        this.inHand = inHand;
        this.sittingOut = sittingOut;
        this.isButton = isButton;
        this.seatNumber = seatNumber;

        // this.cards[0] = new Card ('u', 0);
        // this.cards[1] = new Card ('u', 0);


    }

    //what are some methods we will need;
    //getCards()
    //getStack()
    //getBigs()
    //getUsername()
    //giveCards(Card[] c) how to give one or two cards?
    //addToStack(double amount)
    //negateStack(double amount)
    //setInHand(boolean b)
    //setIsButtun(boolean b)
    //setSeatNumber(int seatNumber)
    //setSittingOut(boolean b)

    private Card[] getCards(){

        return cards;

    }
    private double getStack(){

        return currentStack;

    }
    protected void setStack(double amount){

        this.currentStack = amount;

    }
    private double getBigs(){

        //DUMMY RETURN --> WILL NEED BLINDS FOR THIS WHICH WILL BE STORED IN HAND OBJECT NOT PLAYER
        return 0.0;

    }
    public String getUsername(){

        return username;

    }

    private void giveCards(Card[] c) throws IndexOutOfBoundsException {
        try{
            for (int i = 0; i < c.length; i++){

                this.cards[i] = c[i];

            }
        }

        catch (IndexOutOfBoundsException e){

            System.out.println("The input array's length was not correct.");

        }

    }

    protected void addToStack(double amount){

        currentStack += amount;

    }

    protected void negateStack(double amount){

        currentStack -= amount;

    }

    protected void setInHand(boolean b){

        this.inHand = b;

    }

    protected void setIsButton(boolean b){

        this.isButton = b;

    }

    protected void setSeatNumber(int seatNumber){

        this.seatNumber = seatNumber;

    }

    protected void setIsSittingOut(boolean b){

        this.sittingOut = b;

    }

    



}