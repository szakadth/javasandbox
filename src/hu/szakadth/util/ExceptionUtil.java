package hu.szakadth.util;

import java.util.function.Consumer;

/**
 * Created by bogrea on 2018.08.31..
 */
public class ExceptionUtil {
    public static <T> Consumer<T> rethrow(ConsumerCheckException<T> c) {
        return elem -> {
            try {
                c.accept(elem);
            } catch (Exception ex) {
                /**
                 * within sneakyThrow() we cast to the parameterized type T.
                 * In this case that type is RuntimeException.
                 * At runtime, however, the generic types have been erased, so
                 * that there is no T type anymore to cast to, so the cast
                 * disappears.
                 */
                ExceptionUtil.<RuntimeException>sneakyThrow(ex);
            }
        };
    }

    /**
     * Reinier Zwitserloot who, as far as I know, had the first mention of this
     * technique in 2009 on the java posse mailing list.
     * http://www.mail-archive.com/javaposse@googlegroups.com/msg05984.html
     */
    public static <T extends Throwable> T sneakyThrow(Throwable t) {
       return null;
        // throw (T) t;
    }
}
