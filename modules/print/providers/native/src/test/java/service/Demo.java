package service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Demo {
  public static final int portNumber = 4444;

  public static void main(final String[] arguments) throws IOException, InterruptedException {
    int delta = 5000;

    long requestTime = 0;
    try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
      while (true) {
        try (Socket clientSocket = start(serverSocket)) {
          try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
              BufferedReader in =
                  new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String inputLine, outputLine;

            while ((inputLine = in.readLine()) != null) {
              long currentRequestTime = System.currentTimeMillis();

              if (currentRequestTime - requestTime > delta) {
                LOGGER.info("readLine:");

                showPDF();
                requestTime = currentRequestTime;
              } else {
                LOGGER.info("not enough time elapsed");
              }

              clientSocket.close();
              break;
            }
          }
        }
      }
    }
  }

  private static Socket start(final ServerSocket serverSocket) throws IOException {
    return (serverSocket.accept());
  }

  public static void showPDF() throws InterruptedException, IOException {
    final File pdfFile = new File("label.pdf");

    // simply launch and wait for us to close the viewer
    Process process =
        Runtime.getRuntime().exec(new String[] {"AcroRd32.exe", pdfFile.getAbsolutePath()});
    process.waitFor();
  }
}
