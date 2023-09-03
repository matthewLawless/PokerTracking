package HandSessionAbstraction;


public class Card {
    
    private char suit;
    private int value;

    /*Values:
     * 0 = unkown
     * 1 or 14= A 
     * 2 = 2
     * 3 = 3
     * 4 = 4
     * 5 = 5
     * 6 = 6
     * 7 = 7
     * 8 = 8
     * 9 = 9
     * 10 = 10
     * 11 = J
     * 12 = Q
     * 13 = K
     * 
     * Suits:
     * 
     * u = unkown
     * c = clubs
     * d = diamonds
     * h = hearts
     * s = spades
     * 
     */


    
    public Card(){



    }

    public Card(char s, int value){
        this.suit = s;
        this.value = value;
    }

    


    @Override
    public String toString(){

        //examples As: ace of spades
        //5c: five of clubs

        if (this.value > 10 || this.value == 1){

            switch (this.value){
                case 1:
                return ("A" + suit);
                case 14: 
                return ("A" + suit);
                case 11:
                return ("J" + suit);
                case 12:
                return ("Q" + suit);
                case 13:
                return ("K" + suit);
            }
        }
        else{
            return ("" + this.value + (suit));    
        }
        return "";
    }
}
