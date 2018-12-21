package hu.szakadth.math;

import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

/**
 * Created by bogrea on 2018.11.14..
 */
public class StandardDeviationTest {

    public static void main( String[] args ) {
        double[] data = {1.2, 2.3, 12.4,};
        double sd = new StandardDeviation().evaluate(data);
        System.out.println(sd);
    }
}
