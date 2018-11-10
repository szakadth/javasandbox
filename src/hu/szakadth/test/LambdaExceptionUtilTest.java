package hu.szakadth.test;

import hu.szakadth.exception.RException;

import java.util.stream.IntStream;

/**
 * Created by bogrea on 2018.09.04..
 */
public class LambdaExceptionUtilTest {

    public static void main( String[] args ) {

    }

    public void test() throws RException {
        try {
            IntStream.range(0, 10).map(p -> p).forEach(p -> System.out.print("a"));  //LambdaExceptionUtil.rethrowConsumer(this::doSomethingWrong));
        } catch (Exception e) {

        }
    }

    private void doSomethingWrong(Integer i) throws RException {
        if (i == 0) {
            throw new RException("i is 0");
        }
    }
}
