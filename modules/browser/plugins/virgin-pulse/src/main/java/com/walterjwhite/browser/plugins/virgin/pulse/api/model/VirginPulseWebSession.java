package com.walterjwhite.browser.plugins.virgin.pulse.api.model;

import com.walterjwhite.browser.api.authentication.AuthenticatedWebSession;
import com.walterjwhite.browser.plugins.virgin.pulse.api.enumeration.HealthyHabitDataEntry;
import com.walterjwhite.browser.plugins.virgin.pulse.api.enumeration.HealthyHabitToggle;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString(doNotUseGetters = true)
@Data
public class VirginPulseWebSession extends AuthenticatedWebSession {
  protected Set<HealthyHabitToggle> healthyHabitToggles = new HashSet<>();
  protected Map<HealthyHabitDataEntry, String> healthyHabitDataEntries = new HashMap<>();

  protected boolean todayOnly = true;
}
