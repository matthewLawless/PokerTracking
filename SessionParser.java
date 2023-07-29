import java.io.*;
import java.util.*;
public class SessionParser  {

    public SessionParser(){

        

    }

    /**
     * This method parses files containing all of the hands in a single session. It
     * parses the first hand, and from the first hand should initialize the session object
     * updating it as we continue to parse more and more hands. It should update 
     * 
     * 
     * @param f
     * @return
     * @throws FileNotFoundException
     */
    private Session parseSession(File f) throws FileNotFoundException{

        boolean isCashSession = true;
        Scanner scnr = new Scanner(f);
        //here we are making the assumption that the file is not empty (aka that every file we parse will have a minimum of one hand). this should be true
        String firstLine = scnr.nextLine();

        Scanner l1scan = new Scanner(firstLine);
        String differentiator = l1scan.next();

        //if the session we are parsing contains tournament hands
        //the differentiator will be 'Game', otherwise it will be
        //hand, and we will know we are parsing a cash session
        if (differentiator.equals("Game")){

            return tournamentSessionParse(f);

        }
        else{

            return cashSessionParse(f);

        }

    }

    /**
     * 
     * @param f
     * @return
     */
    private CashSession cashSessionParse(File f){



    }

    /**
     * parses tournament session and returns a tournamentsession object
     * @param f
     * @return
     */
    private TournamentSession tournamentSessionParse(File f) throws FileNotFoundException{

        Session s = new Session();

        //right now, we just want to initialize the tournamentSession object
        //we aren't really worried about parsing specific hands at this point
        //we just want to get the tourney ID, table number, blinds, hand #, level,
        // etc, basic stuff found in the headers of each hand

        //the lines we want to deal with are the first two and then 
        //however many players there are, because we need an array of players
        //to init the object

        //start of line 1
        Scanner scnr = new Scanner(f);
        Scanner lscnr = new Scanner(scnr.nextLine());
        lscnr.next(); 
        lscnr.next();
        String tempHandNumber = lscnr.next();
        lscnr.next();
        lscnr.next();
        String tempTournamentID = lscnr.next();
        lscnr.next();
        String gameType = lscnr.next();
        lscnr.next();
        lscnr.next();
        int tempLevel = Integer.parseInt(lscnr.next());
        String tempStakes = lscnr.next();
        String tempDate = lscnr.next();
        String tempTime = lscnr.next();
        lscnr.close();
        //first line done

        //init a session using just date and time
        s = new TournamentSession(tempTime, tempDate);

        //start of line 2
        lscnr = new Scanner(scnr.nextLine());
        lscnr.next();
        String tempTableName = lscnr.next();
        int tempMaxPlayersAtTable = Integer.parseInt(lscnr.next().substring(0, 1));
        lscnr.next();
        //may or may not need this for the session
        int tempIsButton = Integer.parseInt(lscnr.next().substring(1));
        lscnr.close();
        //done with line 2

        //iterate through all 'Seat X' lines one by one (variable amount of lines),
        //create player objects, assign seats, stacks ect., 
        //max # of lines --> tempMaxPlayersAtTable
        int i = 0;
        while (i < tempMaxPlayersAtTable){

            String tempCurrentLine = scnr.nextLine();
            lscnr = new Scanner(tempCurrentLine);
            lscnr.next();//'Seat'
            String tempWord = lscnr.next();//if we are parsing a 'Seat' line, this NOT be 'posts'
            if (tempWord.equals("posts")){

                //we have iterated past the 'Seat' lines
                //will need to break out but also salvage the current line we are on
                lscnr = new Scanner(tempCurrentLine);
                break;

            }
            else{

                int tempSeatNumber = Integer.parseInt(tempWord.substring(1));//'1:' --> '1' --> 1
                String tempUsername = lscnr.next();
                String tStack = lscnr.next();
                double tempCurrentStack = Double.parseDouble(tStack.substring(1, tStack.length() - 1));
                //so we init the player using just their username and then set their seatnumber at the same time
                s.setSeat((new Player(tempUsername)), tempSeatNumber);

                //alternatively, we could make the constructor take in seatnumber and stack amount ----> think about later

                //now we can go back in and set more of the players fields that we know using the sessions player[]
                //first we set the stack
                s.getPlayersAtTable()[tempSeatNumber - 1].setStack(tempCurrentStack);
                //then seat number (may be slightly redundant)
                s.getPlayersAtTable()[tempSeatNumber - 1].setSeatNumber(tempSeatNumber);


                //NEED TO THROW IN A CONDITIONAL FOR IF THE SEAT NUMBER WE ARE ASSIGNING IS ALSO THE BUTTON
                if (tempSeatNumber == tempIsButton){

                    s.getPlayersAtTable()[tempSeatNumber - 1].setIsButton(true);

                }
                else{

                    s.getPlayersAtTable()[tempSeatNumber - 1].setIsButton(false);

                }

                //ALSO NEED TO CHECK IF SCNR.HASnEXT() IS TRUE, IN WHICH CASE WE HAVE A SPECIAL CASE, LIKE IS SITTING OUT
                if (lscnr.hasNext()){

                    //what are all of the special cases:
                    //sitting out
                    //will be allowed to play after the button
                    //idk if there are more or not
                    s.getPlayersAtTable()[tempSeatNumber - 1].setIsSittingOut(true);

                }
                else{

                    s.getPlayersAtTable()[tempSeatNumber - 1].setIsSittingOut(false);

                }
            }
        }


        //DONE WITH FIRST 2 + ALL SEAT LINES
        //NOW WE ENTER THE ACTUAL ACTION OF THE HANDS ----> CALL HANDPARSER?

        

        //dummy return
        TournamentSession ts;
        return ts;

    }

    /**
     * input is a string that has a dollars sign at the 
     * beginning of it, output is the same string without
     * the dollar sign
     * 
     * @param s
     * @return
     */
    private String removeDollarSign(String s){

        return s.substring(1);

    }
    
}
