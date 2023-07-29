public class CashHand extends Hand{

    //Cash game hand

    private boolean isBombPot;
    private double rake;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //depending on how bomb pots are documented, we would want the constructor to include them
    public CashHand(int handID, String stakes, String date, String time, String tableName, int numberOfSeatsAtTable){

        super(handID, stakes, date, time, tableName, numberOfSeatsAtTable);

     }


     ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

     private boolean getIsBombPot(){

        return isBombPot;

     }

     private void setIsBombPot(boolean b){

        isBombPot = b;  

     }

     private void addToRake(double amount){

        this.rake += amount;

     }
    
}
