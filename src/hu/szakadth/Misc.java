package hu.szakadth;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by bogrea on 2018.09.05..
 */
public class Misc {


    public static void main( String[] args ) {

        String s1 = "Alma %s% Körte %s% Dió";
        String s2 = s1.replace("%s%", "1,2,3,4,5");
        System.out.println(s2);

        int x = 20;
        int y = 30;

        x = x + y;
        y = x - y;
        x = x - y;

        System.out.printf("x= %d y=%d\n", x, y);


        int[] ints = {2,3,4,3,5,7,6,8,12,33,45,65};
        String s = IntStream.of(ints).boxed().map(p -> p.toString()).collect(Collectors.joining(",", "(", ")"));
        System.out.println(s);

        ArrayList<String> where = new ArrayList<String>() {{
            add("FIRST_NAME = :first_name");
            add("LAST_NAME = :last_name");
        }} ;
        System.out.println(where.stream().collect(Collectors.joining(" and ", "where ", "")));


    }
}
