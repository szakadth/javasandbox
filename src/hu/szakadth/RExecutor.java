package hu.szakadth;

import hu.szakadth.exception.RException;
import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;

/**
 * Created by bogrea on 2018.02.27..
 */
public class RExecutor {
    private Rengine engine;
    private boolean suppressWarning = false;
    REXP rexp = null;

    public RExecutor() {
        engine = Rengine.getMainEngine() != null ? Rengine.getMainEngine() : new Rengine(new String[]{"--no-save"}, false, null);
    }

    public RExecutor(boolean suppressWarning) {
        super();
        this.suppressWarning = suppressWarning;
    }

    public RExecutor evaluate(String expression) throws RException {
        rexp = engine.eval("tryCatch(suppressWarnings("+expression + "), error = function(e) { paste(\"error:\",e$message) }, warning = function(w) { paste(\"warning:\", w$message) })");
        checkResult();
        return this;
    }

    public RExecutor assign(String variable, double[] doubleVector) throws RException {
        boolean ok = engine.assign(variable, doubleVector);
        if (!ok) {
            throw new RException("Assign error " + variable);

        }
        return this;
    }
    public RExecutor assign(String variable, int[] intVector) throws RException {
        boolean ok = engine.assign(variable, intVector);
        if (!ok) {
            throw new RException("Assign error " + variable);

        }
        return this;
    }

    public RExecutor assign(String variable, boolean[] booleanVector) throws RException {
        boolean ok = engine.assign(variable, booleanVector);
        if (!ok) {
            throw new RException("Assign error " + variable);

        }
        return this;
    }
    public RExecutor assign(String variable, String[] stringVector) throws RException {
        boolean ok = engine.assign(variable, stringVector);
        if (!ok) {
            throw new RException("Assign error " + variable);

        }
        return this;
    }
    public RExecutor assign(String variable, String stringValue) throws RException {
        boolean ok = engine.assign(variable, stringValue);
        if (!ok) {
            throw new RException("Assign error " + variable);

        }
        return this;
    }

    public RExecutor assign(String variable, REXP rexp) throws RException {
        boolean ok = engine.assign(variable, rexp);
        if (!ok) {
            throw new RException("Assign error " + variable);

        }
        return this;
    }



    public REXP getResult() {
        return rexp;
    }

    public boolean isSuppressWarning() {
        return suppressWarning;
    }

    public void setSuppressWarning(boolean suppressWarning) {
        this.suppressWarning = suppressWarning;
    }

    private void checkResult() throws RException {
        if (rexp.getContent() != null) {
            if (rexp.getContent() instanceof String) {
                String content = (String) rexp.getContent();
                if (content.startsWith("error:")) {
                    throw new RException(content.substring(7));
                } else if (content.startsWith("warning:") && !suppressWarning) {
                    throw new RException(content.substring(9));
                }
            }
        }
    }
}
