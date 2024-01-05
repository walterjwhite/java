package com.walterjwhite.identity.api.model.account;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import javax.jdo.annotations.PersistenceCapable;

/**
 * ie. password? ie. device token? ie. what is your mother's maiden name? ie. what is your father's
 * middle name? ie. what is your mother's middle name?
 */
@PersistenceCapable
public class Challenge extends AbstractNamedEntity {}
