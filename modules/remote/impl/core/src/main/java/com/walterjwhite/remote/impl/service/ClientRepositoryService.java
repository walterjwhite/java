package com.walterjwhite.remote.impl.service;

import com.walterjwhite.remote.api.model.Client;
import jakarta.inject.Singleton;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@Singleton
public class ClientRepositoryService {
  protected Set<Client> clients = new HashSet<Client>();
}
