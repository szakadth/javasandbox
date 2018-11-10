package hu.szakadth.math;

import org.apache.commons.math3.stat.regression.SimpleRegression;

/**
 * Created by bogrea on 2018.10.31..
 */
public class RegressionTest {
    public static void main( String[] args ) {
        SimpleRegression regression = new SimpleRegression();
        regression.addData(1d, 2d);
        regression.addData(3d, 3d);
        regression.addData(3d, 3d);
        regression.addData(4d, 6d);
        regression.addData(5d, 10d);
        regression.addData(8d, 13d);
        regression.addData(12d, 16d);
        System.out.printf("Intercept %12.10f, Slope %12.10f", regression.getIntercept(), regression.getSlope());
    }
}
