package com.walterjwhite.remote.impl.provider;

import com.walterjwhite.encryption.enumeration.DigestAlgorithm;
import com.walterjwhite.ip.api.service.PublicIPLookupService;
import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.api.model.ClientIPAddressHistory;
import com.walterjwhite.remote.api.service.ClientIdentifierService;
import com.walterjwhite.shell.api.model.IPAddress;
import com.walterjwhite.shell.api.model.Node;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import jakarta.inject.Singleton;
import javax.transaction.Transactional;

@Singleton
public class ClientProvider implements Provider<Client> {
  protected final Client client;
  protected final Node node;
  protected final PublicIPLookupService publicIPLookupService;
  protected final DigestAlgorithm digestAlgorithm;
  protected final ClientIdentifierService clientIdentifierService;


  @Inject
  public ClientProvider(
      Node node,
      final PublicIPLookupService publicIPLookupService,
      DigestAlgorithm digestAlgorithm,
      ClientIdentifierService clientIdentifierService)
      throws Exception {

    this.node = node;

    this.digestAlgorithm = digestAlgorithm;
    this.clientIdentifierService = clientIdentifierService;

    this.publicIPLookupService = publicIPLookupService;

    this.client = getClient();
  }

  protected Client getClient() {
    final int id = -1;
    try {
      return null;
    } catch (/*NoResultException*/ RuntimeException e) {
      return (createClient(/*id*/ "TODO: fix this"));
    }
  }

  @Transactional
  protected Client createClient(final String id) {
    Client client = new Client(node, id);

    try {
      client
          .getClientIPAddressHistoryList()
          .add(
              new ClientIPAddressHistory(
                  client, new IPAddress(publicIPLookupService.getPublicIPAddress())));
    } catch (Exception e) {
      throw new RuntimeException("Unable to set IP Address history record", e);
    }

    if (true) {
      throw new UnsupportedOperationException("currently broken until encryption code is updated");
    }


    return (client);
  }

  @Override
  public Client get() {

    return (client);
  }
}
