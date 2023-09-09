package HandSessionAbstraction;
import java.util.ArrayList;
public class Session {
    
    private int session_count = 0;

    /**
     * list of all hands played during this session (txt file)
     */
    protected ArrayList<Hand> allHands = new ArrayList<Hand>();

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
    
    

    //only need to set session owner and allHands, it's more convenient to just init
    //w date and time
    public Session(){

        session_count++;

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
    public void addHand(Hand h){

        allHands.add(h);

    }
    public ArrayList<Hand> getHands(){

        return allHands;

    }

    private void addPlayer(Player p){

        this.allPlayers.add(p);

    }

    private ArrayList<Player> getAllPlayers(){

        return allPlayers;

    }

    public int getCount(){

        return session_count;

    }






}
