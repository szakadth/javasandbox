package hu.szakadth.util;

/**
 * Created by bogrea on 2018.08.31..
 */
@FunctionalInterface
public interface ConsumerCheckException<T> {
    void accept(T elem) throws Exception;
}
