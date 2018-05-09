package hu.szakadth.interfacetest;

import java.util.Date;

/**
 * Created by bogrea on 2018.03.22..
 */
public interface TestInterface {
    default String getMyName() {
        return getName();
    }
    static String getName() {
        return "Almafa";
    }
}
