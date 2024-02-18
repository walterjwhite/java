package com.walterjwhite.inject.cli.util;

import com.walterjwhite.property.api.property.ConfigurableProperty;
import lombok.RequiredArgsConstructor;

import java.util.*;





@Deprecated
@RequiredArgsConstructor
public class HandlerArgumentsHelper {
    protected final String[] arguments;

    


    
    protected String[] getUnusedArguments(
            final Map<Class<? extends ConfigurableProperty>, String> configurablePropertyMap) {
        final List<String> allArguments = new ArrayList<>();
        final Set<Integer> indexesToAvoid = new HashSet<>();

        for (final Class<? extends ConfigurableProperty> configurableProperty :
                configurablePropertyMap.keySet()) {
            final String propertyName = "-" + configurableProperty.getSimpleName();

            final int argumentIndex = contains(propertyName);
            if (argumentIndex >= 0) {
                indexesToAvoid.add(argumentIndex);
                indexesToAvoid.add(argumentIndex + 1);
            }
        }

        for (int i = 0; i < arguments.length; i++) {
            if (indexesToAvoid.contains(Integer.valueOf(i))) {
                continue;
            }

            allArguments.add(arguments[i]);
        }

        return allArguments.toArray(new String[allArguments.size()]);
    }

    protected int contains(final String key) {
        for (int i = 0; i < arguments.length; i++)
            if (key.equals(arguments[i])) {
                return i;
            }

        return -1;
    }
}
