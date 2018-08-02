package hu.szakadth;

import hu.szakadth.exception.RException;
import org.rosuda.JRI.REXP;

/**
 * Created by bogrea on 2018.05.25..
 */
public abstract class RExecutorBase {
    protected boolean suppressWarning = false;

    public RExecutorBase() {
        super();
    }

    public abstract String getResultAsString() throws RException;

    protected void checkResult() throws RException {
        String content = getResultAsString();
        if (content != null) {
            if (content.startsWith("error:")) {
                throw new RException(content.substring(7));
            } else if (content.startsWith("warning:") && !suppressWarning) {
                throw new RException(content.substring(9));
            }
        }
    }

    public boolean isSuppressWarning() {
        return suppressWarning;
    }

    public void setSuppressWarning(boolean suppressWarning) {
        this.suppressWarning = suppressWarning;
    }

}
