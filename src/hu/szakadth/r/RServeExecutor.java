package hu.szakadth.r;

import hu.szakadth.IRExecutor;
import hu.szakadth.RExecutorBase;
import hu.szakadth.exception.RException;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import java.util.Objects;

/**
 * Created by bogrea on 2018.05.25..
 */
public class RServeExecutor extends RExecutorBase implements IRExecutor {
    RConnection c = null;
    REXP rexp;
    private String host;
    private int port;

    public RServeExecutor() {
        this("127.0.0.1", 6311);
    }

    public RServeExecutor(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public RServeExecutor(int port) {
        this("127.0.0.1", port);
    }

    public RServeExecutor(String host) {
        this(host, 6311);
    }

    private RConnection getConnection() throws RException{
        try {
            if (c == null) {
                c = new RConnection(host, port);
            }
            return c;
        } catch (RserveException e) {
            throw new RException(e.getMessage(),e);
        }
    }

    @Override
    public IRExecutor evaluate(String expression) throws RException {
        try {
            rexp = getConnection().eval("tryCatch(suppressWarnings(" + expression + "), error = function(e) { paste(\"error:\",e$message) }, warning = function(w) { paste(\"warning:\", w$message) })");
            checkResult();
            return this;
        } catch (RserveException e) {
            throw new RException(e.getMessage(), e);
        }
    }

    @Override
    public IRExecutor assign(String variable, double[] doubleVector) throws RException {
        try {
            getConnection().assign(variable, doubleVector);
        } catch (REngineException e) {
            throw new RException("Assign error " + variable, e);
        }
        return this;
    }

    @Override
    public IRExecutor assign(String variable, int[] intVector) throws RException {
        try {
            getConnection().assign(variable, intVector);
        } catch (REngineException e) {
            throw new RException("Assign error " + variable, e);
        }
        return this;
    }

    @Override
    public IRExecutor assign(String variable, boolean[] booleanVector) throws RException {
        throw new RException("Not supported ");
    }

    @Override
    public IRExecutor assign(String variable, String[] stringVector) throws RException {
        try {
            getConnection().assign(variable, stringVector);
        } catch (REngineException e) {
            throw new RException("Assign error " + variable, e);
        }
        return this;
    }

    @Override
    public IRExecutor assign(String variable, String stringValue) throws RException {
        try {
            getConnection().assign(variable, stringValue);
        } catch (REngineException e) {
            throw new RException("Assign error " + variable, e);
        }
        return this;
    }

    @Override
    public String getResultAsString() throws RException {
        try {
            if (Objects.nonNull(rexp) && rexp.isString()) {
                return rexp.asString();
            }
        } catch (REXPMismatchException e) {
            throw new RException(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public double getResultAsDouble() throws RException {
        try {
            if (Objects.nonNull(rexp)) {
                return rexp.asDouble();
            }
        } catch (REXPMismatchException e) {
            throw new RException(e.getMessage(), e);
        }
        throw new RException("Result is null");
    }
    @Override
    public double[] getResultAsDoubleArray() throws RException {
        try {
            if (Objects.nonNull(rexp)) {
                return rexp.asDoubles();
            }
        } catch (REXPMismatchException e) {
            throw new RException(e.getMessage(), e);
        }
        throw new RException("Result is null");
    }
}
