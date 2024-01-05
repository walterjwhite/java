package com.walterjwhite.examples.practice.problems.organize.recursion;

import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PathRecursion {
  protected final boolean[][] map;
  protected final Set<Vector> traversed = new HashSet<>();
  protected int[] start;
  protected int[] end;

  public boolean pathExistsBetween() {
    // outside boundary
    if (isOutsideBoundary(start)) return false;

    if (isOutsideBoundary(end)) return false;

    // hit a rock, change direction
    // if()
    // change direction

    return false;
  }

  protected boolean pathExistsBetween(Direction direction) {
    return false;
  }

  protected int[] nextPosition(final int[] currentPosition, final Direction direction) {
    try {
      if (Direction.Up.equals(direction))
        return move(new int[] {currentPosition[0] - 1, currentPosition[1]}, direction);
      if (Direction.Down.equals(direction))
        return move(new int[] {currentPosition[0] + 1, currentPosition[1]}, direction);
      if (Direction.Left.equals(direction))
        return move(new int[] {currentPosition[0], currentPosition[1] - 1}, direction);
      if (Direction.Right.equals(direction))
        return move(new int[] {currentPosition[0], currentPosition[1] + 1}, direction);
    } catch (IllegalArgumentException e) {
      // other direction
    }

    throw new IllegalArgumentException("Invalid direction:" + direction);

    // change direction
  }

  protected int[] move(final int[] coordinates, final Direction direction) {
    if (isOutsideBoundary(coordinates)) throw new IllegalArgumentException("outside boundary");

    if (hitRock(coordinates)) throw new IllegalArgumentException("hit rock");

    if (traversed.add(new Vector(coordinates[0], coordinates[1], direction))) return coordinates;

    // we already traversed this, abort
    // return false;
    throw new IllegalArgumentException("already visited this vector");
  }

  protected boolean isOutsideBoundary(final int[] coordinates) {
    if (coordinates[0] < 0) return true;
    return coordinates[1] < 0;
  }

  protected boolean hitRock(final int[] coordinates) {
    return map[coordinates[0]][coordinates[1]];
  }

  protected boolean atTarget(final int[] coordinates) {
    return end[0] == coordinates[0] && end[1] == coordinates[1];
  }
}
