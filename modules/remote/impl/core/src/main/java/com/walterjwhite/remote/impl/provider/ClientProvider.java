package com.walterjwhite.remote.impl.provider;

import com.walterjwhite.datastore.api.repository.Repository;
// import com.walterjwhite.encryption.impl.RuntimeEncryptionConfiguration;
import com.walterjwhite.encryption.service.DigestService;
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

/** This is the local client (self) */
@Singleton
public class ClientProvider implements Provider<Client> {
  protected final Client client;
  protected final Node node;
  protected final Provider<Repository> repositoryProvider;
  protected final PublicIPLookupService publicIPLookupService;
  protected final DigestService digestService;
  protected final ClientIdentifierService clientIdentifierService;
  //  protected final RuntimeEncryptionConfiguration runtimeEncryptionConfiguration;

  @Inject
  public ClientProvider(
      //      final RuntimeEncryptionConfiguration runtimeEncryptionConfiguration,
      Node node,
      Provider<Repository> repositoryProvider,
      final PublicIPLookupService publicIPLookupService,
      DigestService digestService,
      ClientIdentifierService clientIdentifierService)
      throws Exception {

    this.node = node;

    this.repositoryProvider = repositoryProvider;
    this.digestService = digestService;
    this.clientIdentifierService = clientIdentifierService;

    this.publicIPLookupService = publicIPLookupService;
    //    this.runtimeEncryptionConfiguration = runtimeEncryptionConfiguration;

    this.client = getClient();
  }

  protected Client getClient() {
    // final String id = clientIdentifierService.get();
    final int id = -1;
    try {
      return (repositoryProvider.get().findById(Client.class, id));
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

    //    if (client.getInitializationVector() == null) {
    //      client.setInitializationVector(runtimeEncryptionConfiguration.getIvData());
    //    }

    repositoryProvider.get().create(client);
    return (client);
  }

  @Override
  public Client get() {
    //    repositoryProvider.get().refresh(client);

    return (client);
  }
}
