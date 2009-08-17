package hudson.plugins.promoted_builds;

import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.Hudson;
import hudson.model.InvisibleAction;

/**
 * Remembers what build it's promoting.
 *
 * @author Kohsuke Kawaguchi
 */
public class PromotionTargetAction extends InvisibleAction {
    private final String jobName;
    private final int number;

    public PromotionTargetAction(AbstractBuild<?,?> build) {
        jobName = build.getParent().getFullName();
        number = build.getNumber();
    }

    public AbstractBuild<?,?> resolve() {
        AbstractProject<?,?> j = Hudson.getInstance().getItemByFullName(jobName, AbstractProject.class);
        if (j==null)    return null;
        return j.getBuildByNumber(number);
    }
}
