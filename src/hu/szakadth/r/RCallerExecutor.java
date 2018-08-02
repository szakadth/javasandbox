package hu.szakadth.r;

import com.github.rcaller.rstuff.*;
import com.github.rcaller.util.Globals;
import hu.szakadth.IRExecutor;
import hu.szakadth.RExecutorBase;
import hu.szakadth.exception.RException;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by bogrea on 2018.05.25..
 */
public class RCallerExecutor extends RExecutorBase implements IRExecutor{
    private boolean canRun = true;
    private static final String RESULT = "result___";
    private RCaller rCaller;
    private RCode code;
    private String rHomePath = null;

    public RCallerExecutor () throws RException {
        this.rHomePath = detectRHome();
        init();
    }

    public RCallerExecutor (String rHomePath) throws RException {
        this.rHomePath = rHomePath;
        init();
    }

    private void init() throws RException{
        RCallerOptions options;
        if (Objects.nonNull(rHomePath)) {
            if (Globals.isWindows()) {
                options = RCallerOptions.create(rHomePath + "\\bin\\Rscript.exe", rHomePath + "\\bin\\R.exe",
                    FailurePolicy.RETRY_5, 9223372036854775807L, 100L, RProcessStartUpOptions.create());
            } else {
                options = RCallerOptions.create(rHomePath + "/bin/Rscript", rHomePath + "/bin/R",
                    FailurePolicy.RETRY_5, 9223372036854775807L, 100L, RProcessStartUpOptions.create());
            }
            rCaller = RCaller.create(options);
        } else {
            rCaller = RCaller.create();
        }
        if (Objects.isNull(rCaller)) {
            throw new RException("R not detected! Set R_HOME environment variable or use -Dr.home at start ");
        }
        code = RCode.create();
        canRun = true;
    }
    @Override
    public IRExecutor evaluate(String expression) throws RException {
        if (!canRun) {
            init();
        }
        code.addRCode("tryCatch(suppressWarnings("+expression + "), error = function(e) { paste(\"error:\",e$message) }, warning = function(w) { paste(\"warning:\", w$message) })");
        return this;
    }

    @Override
    public IRExecutor assign(String variable, double[] doubleVector) throws RException {
        code.addDoubleArray(variable, doubleVector);
        return this;
    }

    @Override
    public IRExecutor assign(String variable, int[] intVector) throws RException {
        code.addIntArray(variable, intVector);
        return this;
    }

    @Override
    public IRExecutor assign(String variable, boolean[] booleanVector) throws RException {
        code.addLogicalArray(variable, booleanVector);
        return this;
    }

    @Override
    public IRExecutor assign(String variable, String[] stringVector) throws RException {
        code.addStringArray(variable, stringVector);
        return this;
    }

    @Override
    public IRExecutor assign(String variable, String stringValue) throws RException {
        code.addString(variable, stringValue);
        return this;
    }

    @Override
    public double getResultAsDouble() throws RException {
        prepareResult();
        checkResult();
        return rCaller.getParser().getAsDoubleArray(RESULT)[0];

    }

    @Override
    public double[] getResultAsDoubleArray() throws RException {
        prepareResult();
        checkResult();
        return rCaller.getParser().getAsDoubleArray(RESULT);
    }

    @Override
    public String getResultAsString() throws RException {
        prepareResult();
        checkResult();
        return rCaller.getParser().getAsStringArray(RESULT)[0];
    }


    private void prepareResult() {
        code.addRCode(RESULT + " <- .Last.value");
        rCaller.setRCode(code);
        rCaller.runAndReturnResult(RESULT);
        canRun = false;

    }

    private static String detectRHome () {
        String rHome = System.getProperty("r.home");
        if (Objects.isNull(rHome)) {
            rHome = System.getenv("R_HOME");
        }
        return rHome;
    }

    @Override
    protected void checkResult() throws RException {
        String[] content = rCaller.getParser().getAsStringArray(RESULT);
        if (content != null) {
            if (content[0].startsWith("error:")) {
                throw new RException(content[0].substring(7));
            } else if (content[0].startsWith("warning:") && !suppressWarning) {
                throw new RException(content[0].substring(9));
            }
        }
    }
}
