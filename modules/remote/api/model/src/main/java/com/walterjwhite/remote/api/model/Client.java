package com.walterjwhite.remote.api.model;

import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.shell.api.model.Node;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@PersistenceCapable
public class Client {
  protected int id;

  @EqualsAndHashCode.Exclude protected transient String uuid;

  protected String hashedIdentifier;

  @EqualsAndHashCode.Exclude /*(cascade = CascadeType.ALL)*/ protected Node node;

  @EqualsAndHashCode.Exclude
  protected List<ClientIPAddressHistory> clientIPAddressHistoryList = new ArrayList<>();

  @EqualsAndHashCode.Exclude protected List<Message> sentMessages = new ArrayList<>();

  @EqualsAndHashCode.Exclude protected byte[] initializationVector;

  public Client(Node node, String hashedIdentifier, byte[] initializationVector) {

    this.node = node;
    this.hashedIdentifier = hashedIdentifier;
    this.initializationVector = initializationVector;
  }

  public Client(Node node, String hashedIdentifier) {
    this(node, hashedIdentifier, null);
  }
}
