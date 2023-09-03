package HandSessionAbstraction;
public class TournamentHand extends Hand{

    private int currentLevel;
    private double currentAnte;
    private int tournamentID;


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    TournamentHand(int handID, int tournamentID, int currentLevel, String stakes, String date, String time, String tableName, int maxPlayersAtTable){

        super(handID, stakes, date, time, tableName, maxPlayersAtTable);
        this.tournamentID = tournamentID;
        this.currentLevel  = currentLevel;



    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private int getCurrentLevel(){

        return currentLevel;

    }

    private double getCurrentAnte(){

        return currentAnte;

    }

    private void setCurrentAnte(double a){

        this.currentAnte = a;

    }

    private int getTournamentID(){

        return tournamentID;

    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * simulates the player putting their ante into the pot at 
     * the beginning of the hand. negates the players stack by
     * the amount of the ante, increases the size of the pot by
     * the size of the ante.
     * 
     * @param p
     */
    private void postAnte(Player p){

        p.negateStack(currentAnte);
        this.currentPotSize += currentAnte;

        action.add(p.getUsername() + " posted ante " + currentAnte);

        //there is edge cases here we will tie up later;
    }

    

    

    
}
