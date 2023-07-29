import java.util.ArrayList;
public class Session {
    

    /**
     * list of all hands played during this session (txt file)
     */
    protected ArrayList<Hand> allHands;

    /**
     * list of all players that played in hands during this session
     * used so that players are reusable, and we don't have to construct
     * new ones every hand
     * 
     * eventually maybe switch to binary splay tree
     */
    protected ArrayList<Player> allPlayers;

    /**
     * whoever is being dealt cards we can see at the beginning of each hand
     */
    protected Player sessionOwner;
    
    

    //only need to set session owner and allHands, it's more convenient to 
    public Session(){

  

    }

    /**
     * sets player p as the owner of the session
     * @param p
     */
    private void setSessionOwner(Player p){

        this.sessionOwner = p;

    }

    private Player getSessionOwnerName(){

        return sessionOwner;

    }

    /**
     * adds a hand to the session
     * @param h
     */
    private void addHand(Hand h){

        allHands.add(h);

    }
    private ArrayList<Hand> getHands(){

        return allHands;

    }

    private void addPlayer(Player p){

        this.allPlayers.add(p);

    }

    private ArrayList<Player> getAllPlayers(){

        return allPlayers;

    }






}
