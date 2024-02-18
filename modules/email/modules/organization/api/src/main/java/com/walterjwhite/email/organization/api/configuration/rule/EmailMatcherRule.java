package com.walterjwhite.email.organization.api.configuration.rule;


import com.walterjwhite.email.api.model.Email;
import com.walterjwhite.email.organization.api.Action;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(doNotUseGetters = true)
public class EmailMatcherRule implements Comparable<EmailMatcherRule> {
    protected String name;
    @EqualsAndHashCode.Exclude
    protected int ordering;
    @EqualsAndHashCode.Exclude
    protected AbstractRule rule;
    @EqualsAndHashCode.Exclude
    protected List<String> actionClassNames;

    @EqualsAndHashCode.Exclude
    protected List<Class<? extends Action>> actionClasses = new ArrayList<>();

    public boolean matches(final Email email) {
        return rule.matches(email);
    }

    @Override
    public int compareTo(EmailMatcherRule emailMatcherRule) {
        if (emailMatcherRule == null) {
            return -1;
        }
        if (emailMatcherRule.getOrdering() < 0) {
            return -1;
        }

        return Integer.signum(ordering - emailMatcherRule.getOrdering());
    }

    public void initializeActionClasses() {
        actionClassNames.stream().forEach(actionClassName -> initializeActionClass(actionClassName));
    }

    protected void initializeActionClass(final String actionClassName) {
        try {
            actionClasses.add((Class<? extends Action>) Class.forName(actionClassName));
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Improperly configured", e);
        }
    }
}
