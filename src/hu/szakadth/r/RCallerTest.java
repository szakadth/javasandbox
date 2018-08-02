package hu.szakadth.r;

import com.github.rcaller.rstuff.RCaller;
import com.github.rcaller.rstuff.RCode;
import hu.szakadth.exception.RException;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 * Created by bogrea on 2018.05.25..
 */
public class RCallerTest {

    public static void main(String[] args) {
        RCaller rCaller = RCaller.create();
        RCode code = RCode.create();


        String javaVector = "c(1,2,3,4,5)";
        double [] data = new double[] {1d,4d,3d,1d,2d};
        double [] result;

        try {
            RCallerExecutor executor = new RCallerExecutor();

            result = executor
                .evaluate("library('mFilter')")
                .assign("input_data", data)
                .evaluate("hp_output <- hpfilter(input_data, freq=1600)")
                .evaluate("hp_output$cycle")
                .getResultAsDoubleArray();


            System.out.println(String.format("result of hpfilter %S,freq=1600) is %s",
                DoubleStream.of(data).boxed().map(p->new Double(p).toString()).collect(Collectors.joining(",", "[", "]")),
                DoubleStream.of(result).boxed().map(p->new Double(p).toString()).collect(Collectors.joining(",", "[", "]"))));

            executor = new RCallerExecutor();
            double d = executor
                .evaluate("library('mFilter')")
                .assign("input_data", data)
                .evaluate("mean(input_data)")
                .getResultAsDouble();

            System.out.println(d);
            executor = new RCallerExecutor();
            String s = executor
//                .evaluate("library('mFilter')")
                .assign("data", "Almafa")
                .evaluate("data")
                .getResultAsString();

            System.out.println(s);
        } catch (RException e) {
            e.printStackTrace();
        }
    }

    private static String detectRHome () {
        String rHome = System.getProperty("r.home");
        if (Objects.isNull(rHome)) {
            rHome = System.getenv("R_HOME");
        }
        return rHome;
    }

}
