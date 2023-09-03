package HandSessionAbstraction;
public class CashSession extends Session{


    public CashSession(String startTime, String date, String stakes, int maxPlayersAtTable, String tableName, Player[] playersAtTable){

        //call parent constructor
        super();
        //set BB and SB from the stakes field
        // int splitIndex = this.stakes.indexOf('/');
        // String tempSB = this.stakes.substring(1, splitIndex - 1);
        // String tempBB = this.stakes.substring(splitIndex + 2);
        // this.SB = Double.parseDouble(tempSB);
        // this.BB = Double.parseDouble(tempBB);

    }

}