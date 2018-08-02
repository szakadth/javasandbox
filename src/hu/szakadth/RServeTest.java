package hu.szakadth;

import hu.szakadth.exception.RException;
import hu.szakadth.r.RServeExecutor;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by bogrea on 2018.05.25..
 */
public class RServeTest {

    private static final int MAX_ITERATION = 500;

    public static void main(String... args) {
        RServeTest rServeTest = new RServeTest();
        rServeTest.test();
    }

    public void test() {
        Random random = new Random();
        Supplier<Double> supplier = () -> new Double(random.nextInt(2000) + 2000);
        ArrayList<Double> cfArray = Stream.generate(supplier).limit(MAX_ITERATION).parallel().collect(Collectors.toCollection(ArrayList::new));
        double[] cfs = (double[]) cfArray.stream().mapToDouble(p -> p).toArray();
        double[] times = IntStream.range(1, MAX_ITERATION + 1).mapToDouble(p -> new Double(p)).toArray();
        try {
            RServeExecutor rExecutor = new RServeExecutor();
            System.out.println(rExecutor.evaluate("R.version.string").getResultAsString());

            double result = rExecutor
                    .evaluate("library(FinancialMath)")
                    .assign("cfs", cfs)
                    .assign("times", times)
                    .evaluate("cf0 <- 4000")
                    .evaluate("result <- IRR(cf0,cfs,times)")
                    .evaluate("result * 100")
                    .getResultAsDouble();
            System.out.println(result);

        } catch (RException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}