import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class TestNetworkInterfaceUtil {

  public static void main(final String[] arguments) throws SocketException {
    final Enumeration<NetworkInterface> networkInterfaceEnumeration =
        NetworkInterface.getNetworkInterfaces();

    while (networkInterfaceEnumeration.hasMoreElements()) {
      final NetworkInterface networkInterface = networkInterfaceEnumeration.nextElement();

      if (!networkInterface.isLoopback()) {
        final Enumeration<InetAddress> inetAddressEnumeration = networkInterface.getInetAddresses();
        while (inetAddressEnumeration.hasMoreElements()) {
          final InetAddress inetAddress = inetAddressEnumeration.nextElement();

          if (!inetAddress.isLinkLocalAddress()) {
            handle(networkInterface, inetAddress);
          }
        }
      }
    }
  }

  private static void handle(NetworkInterface networkInterface, InetAddress inetAddress) {
    //            "inetAddress:("
    //                    + networkInterface.getDisplayName()
    //                    + "):"
    //                    + inetAddress.getHostAddress();
  }
}
