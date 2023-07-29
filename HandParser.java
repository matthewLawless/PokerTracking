import java.util.*;
public class HandParser {

    public HandParser(){

        //nothing

    }

    private Hand parseHand(String s){

        return new Hand()

    }

    /**
     * 
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
    
}
