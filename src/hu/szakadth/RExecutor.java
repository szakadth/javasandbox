package hu.szakadth;

import hu.szakadth.exception.RException;
import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;

/**
 * Created by bogrea on 2018.02.27..
 */
public class RExecutor extends RExecutorBase implements IRExecutor {
    private Rengine engine;
    private REXP rexp;

    public RExecutor() {
        engine = Rengine.getMainEngine() != null ? Rengine.getMainEngine() : new Rengine(new String[]{"--no-save"}, false, null);
    }

    public RExecutor(boolean suppressWarning) {
        super();
        this.suppressWarning = suppressWarning;
    }

    @Override
    public IRExecutor evaluate(String expression) throws RException {
        rexp = engine.eval("tryCatch(suppressWarnings("+expression + "), error = function(e) { paste(\"error:\",e$message) }, warning = function(w) { paste(\"warning:\", w$message) })");

        checkResult();
        return this;
    }

    @Override
    public IRExecutor assign(String variable, double[] doubleVector) throws RException {
        boolean ok = engine.assign(variable, doubleVector);
        if (!ok) {
            throw new RException("Assign error " + variable);

        }
        return this;
    }
    @Override
    public IRExecutor assign(String variable, int[] intVector) throws RException {
        boolean ok = engine.assign(variable, intVector);
        if (!ok) {
            throw new RException("Assign error " + variable);

        }
        return this;
    }

    @Override
    public IRExecutor assign(String variable, boolean[] booleanVector) throws RException {
        boolean ok = engine.assign(variable, booleanVector);
        if (!ok) {
            throw new RException("Assign error " + variable);

        }
        return this;
    }
    @Override
    public IRExecutor assign(String variable, String[] stringVector) throws RException {
        boolean ok = engine.assign(variable, stringVector);
        if (!ok) {
            throw new RException("Assign error " + variable);

        }
        return this;
    }
    @Override
    public IRExecutor assign(String variable, String stringValue) throws RException {
        boolean ok = engine.assign(variable, stringValue);
        if (!ok) {
            throw new RException("Assign error " + variable);

        }
        return this;
    }

    public IRExecutor assign(String variable, REXP rexp) throws RException {
        boolean ok = engine.assign(variable, rexp);
        if (!ok) {
            throw new RException("Assign error " + variable);

        }
        return this;
    }

    @Override
    public String getResultAsString() {
        if (rexp.getContent() != null) {
            if (rexp.getContent() instanceof String) {
                return rexp.asString();
            }
        }
        return null;
    }

    @Override
    public double getResultAsDouble() throws RException {
        if (rexp.getContent() != null) {
                return rexp.asDouble();
        }
        throw new RException("Type mismatch");
    }

    @Override
    public double[] getResultAsDoubleArray() throws RException {
        if (rexp.getContent() != null) {
                return rexp.asDoubleArray();
        }
        throw new RException("Type mismatch");
    }
}
