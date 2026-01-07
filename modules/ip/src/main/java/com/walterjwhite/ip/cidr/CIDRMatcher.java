package com.walterjwhite.ip.cidr;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;
import lombok.Data;

@Data
public final class CIDRMatcher {
  private final InetAddress baseAddress;
  private final int prefixLength;
  private final BigInteger network;
  private final BigInteger mask;
  private final int bitLength;

  public static CIDRMatcher toCIDRMatcher(final String cidr) {
    final String[] parts = getParts(cidr);

    final InetAddress baseAddress = getBaseAddress(cidr, parts);
    final int prefixLength = Integer.parseInt(parts[1]);
    byte[] addressBytes = baseAddress.getAddress();
    final int bitLength = addressBytes.length * 8;

    if (prefixLength < 0 || prefixLength > bitLength) {
      throw new IllegalArgumentException(
          "Invalid prefix length for address family: " + prefixLength);
    }

    BigInteger allOnes = BigInteger.ONE.shiftLeft(bitLength).subtract(BigInteger.ONE);
    BigInteger hostBits = BigInteger.ZERO;
    if (bitLength - prefixLength > 0) {
      hostBits = BigInteger.ONE.shiftLeft(bitLength - prefixLength).subtract(BigInteger.ONE);
    }
    final BigInteger mask =
        allOnes.subtract(hostBits); // mask has prefixLength ones followed by zeros

    final BigInteger baseInt = new BigInteger(1, addressBytes);
    final BigInteger network = baseInt.and(mask);

    return new CIDRMatcher(baseAddress, prefixLength, network, mask, bitLength);
  }

  public static String[] getParts(final String cidr) {
    Objects.requireNonNull(cidr, "cidr must not be null");
    final String[] parts = cidr.trim().split("/");
    if (parts.length != 2) {
      throw new IllegalArgumentException("CIDR must be of form address/prefixLength: " + cidr);
    }

    return parts;
  }

  public static InetAddress getBaseAddress(final String cidr, final String[] parts) {
    try {
      return InetAddress.getByName(parts[0]);
    } catch (UnknownHostException e) {
      throw new IllegalArgumentException("Invalid base address in CIDR: " + cidr, e);
    }
  }

  public boolean matches(String ip) {
    if (ip == null) {
      return false;
    }
    try {
      InetAddress addr = InetAddress.getByName(ip.trim());
      byte[] addrBytes = addr.getAddress();
      if (addrBytes.length * 8 != this.bitLength) {
        return false;
      }

      BigInteger ipInt = new BigInteger(1, addrBytes);
      BigInteger ipNetwork = ipInt.and(mask);

      return network.equals(ipNetwork);
    } catch (UnknownHostException e) {
      return false;
    }
  }

  @Override
  public String toString() {
    return baseAddress.getHostAddress() + "/" + prefixLength;
  }
}
