package com.walterjwhite.examples.spring.mock;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CSVWriter {
  private static final DateTimeFormatter csvDateFormatter =
      DateTimeFormatter.ofPattern("yyyy-MM-dd");

  public static String toCSV(final List<Transaction> transactions) throws IOException {
    StringBuilder sb = new StringBuilder();
    sb.append(
        "xid,orderId,cusip,transactionDate,transactionType,transactionStatus,quantity,pricePerShare,commissionFee,brokerId,clientId,settlementDate\n");

    for (Transaction t : transactions) {
      String xid;
      if (t.getXid() != null) {
        xid = t.getXid();
      } else {
        xid = "";
      }

      String orderId;
      if (t.getOrderId() != null) {
        orderId = t.getOrderId();
      } else {
        orderId = "";
      }

      String cusip;
      if (t.getCusip() != null) {
        cusip = t.getCusip();
      } else {
        cusip = "";
      }

      String transactionDate;
      if (t.getTransactionDate() != null) {
        transactionDate = t.getTransactionDate().format(csvDateFormatter);
      } else {
        transactionDate = "";
      }

      String transactionType;
      if (t.getTransactionType() != null) {
        transactionType = t.getTransactionType().name();
      } else {
        transactionType = "";
      }

      String transactionStatus;
      if (t.getTransactionStatus() != null) {
        transactionStatus = t.getTransactionStatus().name();
      } else {
        transactionStatus = "";
      }

      String quantity = Double.toString(t.getQuantity());
      String pricePerShare = Double.toString(t.getPricePerShare());
      String commissionFee = Double.toString(t.getCommissionFee());

      String brokerId;
      if (t.getBrokerId() != null) {
        brokerId = t.getBrokerId();
      } else {
        brokerId = "";
      }

      String clientId;
      if (t.getClientId() != null) {
        clientId = t.getClientId();
      } else {
        clientId = "";
      }

      String settlementDate;
      if (t.getSettlementDate() != null) {
        settlementDate = t.getSettlementDate().format(csvDateFormatter);
      } else {
        settlementDate = "";
      }

      sb.append(xid)
          .append(",")
          .append(orderId)
          .append(",")
          .append(cusip)
          .append(",")
          .append(transactionDate)
          .append(",")
          .append(transactionType)
          .append(",")
          .append(transactionStatus)
          .append(",")
          .append(quantity)
          .append(",")
          .append(pricePerShare)
          .append(",")
          .append(commissionFee)
          .append(",")
          .append(brokerId)
          .append(",")
          .append(clientId)
          .append(",")
          .append(settlementDate)
          .append("\n");
    }

    final String filename = "export-" + System.currentTimeMillis() + ".csv";
    final Path path = getPath(filename);
    Files.writeString(path, sb.toString(), StandardCharsets.UTF_8);
    LOGGER.info("Exported transactions to CSV: {}", path.toAbsolutePath());

    return filename;
  }

  public static Path getPath(final String filename) {
    return Paths.get(System.getProperty("java.io.tmpdir"), filename);
  }
}
