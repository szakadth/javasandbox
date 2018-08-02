package hu.szakadth;

import hu.szakadth.exception.RException;


/**
 * Created by bogrea on 2018.05.25..
 */
public interface IRExecutor {
    IRExecutor evaluate(String expression) throws RException;

    IRExecutor assign(String variable, double[] doubleVector) throws RException;

    IRExecutor assign(String variable, int[] intVector) throws RException;

    IRExecutor assign(String variable, boolean[] booleanVector) throws RException;

    IRExecutor assign(String variable, String[] stringVector) throws RException;

    IRExecutor assign(String variable, String stringValue) throws RException;

    String getResultAsString() throws RException;

    double getResultAsDouble() throws RException;

    double[] getResultAsDoubleArray() throws RException;



}
