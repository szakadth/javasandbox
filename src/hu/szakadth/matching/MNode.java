package hu.szakadth.matching;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by bogrea on 2018.05.15..
 */


public class MNode {
    int label = 0;
    MNodeType type;
    MNode parent = null;
    LinkedHashSet<MNode> nbrs = new LinkedHashSet<>();
    boolean contracted = false;
    MNode matchedWith = null;
    int forIteration;

    public Set<MNode> getNbrs() {
        return nbrs;
    }

    void addNbr(MNode nbr) {
        if (nbr == null) {
            return;
        }
        if (nbr == this) {
            return;
        }
        if (nbrs.contains(nbr)) {
            return;
        }
        nbrs.add(nbr);
    }

    void removeNbr(MNode nbr) {

        nbrs.remove(nbr);

    }

    public MNode getMatchedWith() {
        return matchedWith;
    }

    public void setMatchedWith(MNode matchedWith) {
        this.matchedWith = matchedWith;
    }

    @Override
    public String toString() {
        return "" + label;
    }
}
