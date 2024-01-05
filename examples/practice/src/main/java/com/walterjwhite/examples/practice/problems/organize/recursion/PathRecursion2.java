package com.walterjwhite.examples.practice.problems.organize.recursion;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PathRecursion2 {
  protected final boolean[][] map;
  protected final Set<Node> visitedNodes = new HashSet<>();
  protected final Set<Vector> vistedVectors = new HashSet<>();

  // see where all we can go in the map
  public void traverse() {
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        if (map[i][j]) {
          continue;
        }

        final Node accessibleNode = new Node(i, j);
        visitedNodes.add(accessibleNode);

        // up
        if (canVisit(accessibleNode.i - 1, accessibleNode.j)) {}
      }
    }
  }

  protected void traverse(final Node currentNode, final int i, final int j) {}

  protected boolean canVisit(final int i, final int j) {
    return !isBlockedByARock(i, j) && isWithinBounds(i, j);
  }

  protected boolean isBlockedByARock(final int i, final int j) {
    return map[i][j];
  }

  protected boolean isWithinBounds(final int i, final int j) {
    return i >= 0 && j >= 0;
  }

  public boolean traversable(final Node start, final Node end) {
    return visitedNodes.contains(start) && visitedNodes.contains(end);
  }
}
