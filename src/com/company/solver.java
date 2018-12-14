package com.company;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Objects;

/**
 * Created by tw073 on 12/11/18.
 */
public class solver {

    private String str;

    public solver(String a){

        str = a;

    }

    public static int solve (String str, int a, int b)
    {



        str = str.replaceAll("!A", (a == 0) ? "1" : "0");
        str = str.replaceAll("!B", (b == 0) ? "1" : "0");

        str = str.replaceAll("A", Integer.toString(a));
        str = str.replaceAll("B", Integer.toString(b));



        int startp = 0;
        int endp = 0;
        int nump = 1;
        int place = 0;
        String temp = "(0*0)";
        if(str.indexOf('!') !=-1)
        {
            //System.out.println("OUTPTPTTP   " + str.indexOf('(', str.indexOf('!')));
            startp = str.indexOf('(', str.indexOf('!'));
            place = startp;
            System.out.println("Negation Found, Checking for char in place: ");
            while (nump != 0)
            {
                place++;
                if(Objects.equals(str.charAt(place) , '('))                     ///Might have to use Objects equal
                    nump++;
                if((Objects.equals(str.charAt(place) , ')')))
                    nump--;
                System.out.println(place);

            }
            endp = place + 1;


            temp = str.substring(startp, endp );
            //temp = str.substring(1, 6);

            System.out.println("Start position of \"(\" : " + startp + "\tEnd position of \")\" : " + endp + "\t Full expression" + str + "\tTEMP (Expression to send back into method to be solved) - " + temp + "\n");
            str = str.substring(0,startp - 1) +           ((solver.solve(temp, a, b) == 0) ? 1 : 0)         + str.substring(endp,str.length()) ;
            System.out.println("REVISED (after substituting)   " + str);
        }


        ScriptEngine engine = new ScriptEngineManager().getEngineByExtension("js");

        int end = -1;

        try {
            // Evaluate the expression
            Object result = engine.eval(str);

            //System.out.println(result);
            if(result.equals(0) ){
                end = 0;
            }else if (!result.equals(0)){
                end = 1;
            }
        }
        catch (ScriptException e) {
            // Something went wrong
            //e.printStackTrace();
            System.out.println("Error");
            end = -1;
        }
        return end;



    }


}
