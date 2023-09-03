package Parsing;
import java.util.*;
import HandSessionAbstraction.*;;
public class HandParser {

    public HandParser(){

        //nothing

    }

    protected Hand parseHand(String s){

        Hand h;
        //check if hand is tourney hand or if it is
        //a cash game hand
        // boolean b = checkType(s);
        // if (b){

            //cashHand branch

            //start of line 1
            Scanner scnr = new Scanner(s);
            Scanner lscnr = new Scanner(scnr.nextLine());
            System.out.println(lscnr.next()); // hand
            int tempHandID = Integer.parseInt((lscnr.next()).substring(1)); // handid
            
            lscnr.next(); // -
            
            // int tempTournamentID = Integer.parseInt((lscnr.next()).substring(1));

            // lscnr.next();
            String gameType = lscnr.next(); // gtype
            lscnr.next(); // -
            lscnr.next();
            
            // int tempLevel = Integer.parseInt(lscnr.next());
            String tempStakes = lscnr.next(); // stakes
            lscnr.next(); // -
            String tempDate = lscnr.next(); // date
            
            String tempTime = lscnr.next();
            lscnr.close();
            //first line done

            //start of line 2
            lscnr = new Scanner(scnr.nextLine());
            // lscnr.next();
            String tempTableName = lscnr.next();
            int tempMaxPlayersAtTable = Integer.parseInt(lscnr.next().substring(0, 1));
            
            lscnr.next();
            //may or may not need this for the session
            int tempIsButton = Integer.parseInt(lscnr.next().substring(1));
            lscnr.close();
            //done with line 2

            //we should now have enough to init the hand
            h = new CashHand(tempHandID, tempStakes, tempDate, tempTime, tempTableName, tempMaxPlayersAtTable);

            //now we move on to the players, their seat numbers, stack sizes, and usernames

            //iterate through all 'Seat X' lines one by one (variable amount of lines),
            //create player objects, assign seats, stacks ect., 
            //max # of lines --> tempMaxPlayersAtTable

            //start of player information
            int i = 0;
            while (i < tempMaxPlayersAtTable){

                String tempCurrentLine = scnr.nextLine();
                lscnr = new Scanner(tempCurrentLine);
                lscnr.next();//'Seat'
                String tempWord = lscnr.next();//if we are parsing a 'Seat' line, this NOT be 'posts'
                if (tempWord.equals("posts") || tempWord.charAt(tempWord.length() - 1) != ':'){

                    //we have iterated past the 'Seat' lines
                    //will need to break out but also salvage the current line we are on
                    lscnr = new Scanner(tempCurrentLine);
                    break;

                }
                else{

                    int tempSeatNumber = Integer.parseInt(tempWord.substring(0, 1));//'1:' --> '1' --> 1
                    String tempUsername = lscnr.next();
                    String tStack = lscnr.next();

                    //two string username
                    if (tStack.charAt(0) != '('){

                        tempUsername = tempUsername + " " +  tStack;
                        tStack = lscnr.next();

                    }

                    //in theory, it could be 3 strings or 4, we will solve later

                    double tempCurrentStack = Double.parseDouble(tStack.substring(2, tStack.length() - 1));
                    //so we init the player using just their username and then set their seatnumber at the same time
                    h.setPlayerToSeat((new Player(tempUsername, tempSeatNumber, tempCurrentStack)), tempSeatNumber);

                    //alternatively, we could make the constructor take in seatnumber and stack amount ----> think about later

                    //now we can go back in and set more of the players fields that we know using the sessions player[]
                    //first we set the stack
                    h.getPlayersAtTable()[tempSeatNumber - 1].setStack(tempCurrentStack);
                    //then seat number (may be slightly redundant)
                    h.getPlayersAtTable()[tempSeatNumber - 1].setSeatNumber(tempSeatNumber);


                    //NEED TO THROW IN A CONDITIONAL FOR IF THE SEAT NUMBER WE ARE ASSIGNING IS ALSO THE BUTTON
                    if (tempSeatNumber == tempIsButton){

                        h.getPlayersAtTable()[tempSeatNumber - 1].setIsButton(true);

                    }
                    else{

                        h.getPlayersAtTable()[tempSeatNumber - 1].setIsButton(false);

                    }

                    //ALSO NEED TO CHECK IF SCNR.HASnEXT() IS TRUE, IN WHICH CASE WE HAVE A SPECIAL CASE, LIKE IS SITTING OUT
                    if (lscnr.hasNext()){

                        //----------------------------------NOT COMPLETE!!!!!

                        //what are all of the special cases:
                        //sitting out
                        //will be allowed to play after the button
                        //idk if there are more or not
                        h.getPlayersAtTable()[tempSeatNumber - 1].setIsSittingOut(true);

                    }
                }
            }
                //end of player information


                //now we are onto the actions of the hand
                //t = 0, actions consist of a players name followed
                //by the action they are performing, there are interuptions
                //such as the dealing of the card ("*** HOLE CARDS ***")
                //generally though, this will be considered a string of actions
                //with certain exceptions, and will be parsed as such

                String currentLine = "";
                while (!currentLine.equals("*** SUMMARY ***")){

                    currentLine = scnr.nextLine();
                    lscnr = new Scanner(currentLine);

                    String firstWord = lscnr.next();

                    //all special cases aka all 'actions' that do not start with a players name
                    if (firstWord.equals("Main")){

                        //one case --> Main pot (some double)


                    }
                    else if (firstWord.equals("***")){

                        //five cases --> HOLE CARDS, FLOP, TURN, RIVER, SHOW DOWN, SUMMARY



                    }
                    else if (firstWord.equals("Dealt")){

                        //one case --> Dealt to (owner's Name) [c1, c2]



                    }
                    else if (firstWord.equals("Uncalled")){

                        //one case --> Uncalled bet ((double)) returned to (player)


                    }
                    else{

                        //find this ^ player
                        Player p = null;
                        for (Player e : h.getPlayersAtTable()){

                            if (e.getUsername().equals(firstWord)){

                                p = e;
                                break;

                            }

                        }

                        String secondWord = lscnr.next();
                        if (!secondWord.equals("posts") && !secondWord.equals("folds") && !secondWord.equals("raises") && !secondWord.equals("calls") && !secondWord.equals("checks") && !secondWord.equals("bets")){

                            firstWord = firstWord + " " + secondWord;

                            for (Player e : h.getPlayersAtTable()){

                                if (e.getUsername().equals(firstWord)){

                                    p = e;
                                    break;

                                }

                            }

                            secondWord = lscnr.next();

                        }

                        //all branches of 'player based actions'
                        //posts (for posts ante and posts big/small blind)
                        //folds
                        //raises
                        //calls
                        //checks
                        //bets
                        //

                        if (secondWord.equals("posts")){

                            //current string: "p posts"
                            //so we are looking for the amount, and whether or not it
                            //is a blind (which one) or an ante

                            String thirdWord = lscnr.next();
                            if (thirdWord.equals("the")){

                                //current string: "p posts the"
                                //its a blind

                                String fourthWord = lscnr.next();
                                if (fourthWord.equals("small")){

                                    h.postsSB(p);

                                }
                                else{

                                    h.postsBB(p);

                                }



                            }
                            else if (thirdWord.equals("ante")){

                                //ante is difficult because this will be the first time seeing
                                //the number, so we cant just call postsAnte because the number
                                //isnt set in the object yet

                            }
                            else{

                                //oopsie

                            }

                        }
                        else if (secondWord.equals("folds")){

                            h.folds(p);

                        }
                        else if (secondWord.equals("checks")){

                            h.checks(p);

                        }
                        else if (secondWord.equals("raises")){

                            //for a raise, they will of the format:
                            //blackchair raises 20Cents to 25cents
                            //this means that blackchair put 20cents in the pot
                            //and that now the current bet is 25cents
                            //this implies that blackchair already had 5 cents in the pot.
                            
                            Double first = Double.parseDouble((lscnr.next()).substring(1));
                            lscnr.next();
                            Double second = Double.parseDouble((lscnr.next()).substring(1));

                            h.raise(p, first, second);

                        }
                        else if (secondWord.equals("calls")){

                            Double first = Double.parseDouble((lscnr.next()).substring(1));
                            h.calls(p, first);
                            
                        }
                        else if (secondWord.equals("bets")){

                            Double first = Double.parseDouble((lscnr.next()).substring(1));
                            h.bets(p, first);

                        }
                        else if (secondWord.equals("shows")){

                            

                        }
                        else if (secondWord.equals("collected")){

                            Double first = Double.parseDouble((lscnr.next()).substring(1));
                            h.collected(p, first);

                        }

                    }

                }
    


        return h;

    }

    /**
     * helper method
     * 
     * 
     * @param s string of form "[Kh Qd]" of any length
     * @return array containing card objects taken from string;
     */
    private Card[] cardReader(String s){

        String withoutBraces = s.substring(1, s.length() - 1);
        Scanner scnr = new Scanner(withoutBraces);
        ArrayList<Card> cList = new ArrayList<Card>();
        String tempCard = "";
        while (scnr.hasNext()){

            tempCard = scnr.next();
            int tempValue;
            if (tempCard.substring(1).equals("A")){

                tempValue = 14;

            }
            else if (tempCard.substring(1).equals("K")){

                tempValue = 13;

            }
            else if (tempCard.substring(1).equals("Q")){

                tempValue = 12;

            }
            else if (tempCard.substring(1).equals("J")){

                tempValue = 11;

            }
            else{

                tempValue = Integer.parseInt(tempCard.substring(1));

            }

            char tempSuit = tempCard.charAt(1);
            cList.add(new Card(tempSuit, tempValue));
        }

        Card[] c = new Card[cList.size()];
        for (int i  = 0; i < c.length; i++){

            c[i] = cList.get(i);

        }

        return c;
        

    }


    /**
     * 
     * returns true if is a cash hand, false if is a tournament hand
     * @param s
     * @return
     */
    private boolean checkType(String s) throws IllegalFormatException{

        Scanner scnr = new Scanner(s);
        String first = scnr.next();
        if (first.equals("Game")){

            return false;

        }
        else if (first.equals("Hand")){

            return true;

        }
        else{

            System.out.println("Problem");
            // throw new IllegalFormatException();
            return true;

        }
        



        }

    }
    

