package com.walterjwhite.remote.impl.service;

import com.walterjwhite.remote.api.model.Client;
import java.util.HashSet;
import java.util.Set;
import jakarta.inject.Singleton;
import lombok.Data;
import lombok.ToString;

// this stores state and only should have a single instance ...
@Data
@ToString(doNotUseGetters = true)
@Singleton
public class ClientRepositoryService {
  protected Set<Client> clients = new HashSet<Client>();
}
