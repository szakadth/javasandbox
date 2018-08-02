package hu.szakadth;

import hu.szakadth.r.RCallerExecutor;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by bogrea on 2018.05.17..
 *
 * To run it, set VM options:
 *      -Djava.library.path=U:\R\win-library\3.4\rJava\jri\x64 -Xms1024m -Xmx2048m
 * Set R_HOME env:
 *      c:\Program Files\R\R-3.4.3
 *
 */
public class IRRCalculator {

    private static final int MAX_ITERATION = 500;
    public static void main(String... args) {
        IRRCalculator irrCalculator = new IRRCalculator();
        irrCalculator.test();
    }

    public void test() {
        Random random = new Random();
        Supplier<Double> supplier = () -> new Double(random.nextInt(2000) + 2000);
        ArrayList<Double> cfArray = Stream.generate(supplier).limit(MAX_ITERATION).parallel().collect(Collectors.toCollection(ArrayList::new));
        double[] cfs = (double[])cfArray.stream().mapToDouble(p->p).toArray();
        double[] times = IntStream.range(1, MAX_ITERATION+1).mapToDouble(p -> new Double(p)).toArray();
        try {
            IRExecutor rExecutor = new RCallerExecutor();

            double result = rExecutor
                    .evaluate("library(FinancialMath)")
                    .assign("cfs", cfs)
                    .assign("times", times)
                    .evaluate("cf0 <- 4000")
                    .evaluate("result <- IRR(cf0,cfs,times)")
                    .evaluate("result * 100")
                    .getResultAsDouble();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
