package Parsing;
import java.util.*;


import HandSessionAbstraction.Hand;

import java.io.*;
public class Test {
    
    public static void main(String[] args) throws FileNotFoundException{


        // Card c = new Card('c', 1);
        // System.out.println(c.toString());

        // String s = "newstring 1 2 3 \n 22";
        // Scanner scnr = new Scanner(s);

        // // while (scnr.hasNext()){

        // //     System.out.println(scnr.next());

        // // }


        // System.out.println(s.contains("\n"));



        // SessionParser sp = new SessionParser();
        File f = new File("HH20230624 CASHID-G29913950T624 TN-La Harpe GAMETYPE-Hold'em LIMIT-no CUR-REAL OND-F BUYIN-0 MIN-1 MAX-2.txt");
        // Session s = sp.parseSession(f);
        
        // s.allHands.toString();

        SessionParser op = new SessionParser();
        Hand h = op.cashSessionParse(f).getHands().get(0);
        h.getActions().toString();


    }

}
