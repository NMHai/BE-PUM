/**
 * 
 */
package v2.org.analysis.transition_rule;

import v2.org.analysis.path.BPPath;

import java.util.List;

/**
 * @author NMHai
 * 
 */
public abstract class TransitionRule {
	public abstract void getNewState(BPPath path, List<BPPath> pathList, boolean cond);

}
