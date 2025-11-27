package com.walterjwhite.shell.impl.provider;

import com.walterjwhite.infrastructure.inject.core.NodeId;
import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.shell.api.model.Node;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import jakarta.inject.Singleton;

@Singleton
public class NodeProvider implements Provider<Node> {
  protected final String nodeId;
  protected final Node node;

  @Inject
  public NodeProvider(@Property(NodeId.class) String nodeId) {

    this.nodeId = nodeId;
    this.node = getNode();
  }

  protected Node getNode() {
    return new Node(nodeId);
  }

  @Override
  public Node get() {
    return node;
  }
}
