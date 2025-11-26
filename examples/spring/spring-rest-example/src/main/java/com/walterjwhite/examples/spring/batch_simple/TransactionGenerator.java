package com.walterjwhite.examples.spring.batch_simple;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class TransactionGenerator {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static List<Transaction> generate() {
        final List<Transaction> transactions = new ArrayList<>(){};
        Random random = new Random();

        for (int i = 0; i < random.nextInt(2000); i++) {
            final LocalDate transactionDate = getTransactionDate(random);
            transactions.add(new Transaction(getXid(random), getOrderId(random), getCusip(random),
                    transactionDate, getTransactionType(random), getTransactionStatus(random), getQuantity(random), getCommissionFee(random),
                    getPricePerShare(random), getBrokerId(random, 100), getClientId(random, 10000),
                    getSettlementDate(random, transactionDate)));
        }

        return transactions;
    }

    public static String getXid(final Random random) {
        return String.format("%09d", random.nextInt(1000000000));
    }

    public static String getOrderId(final Random random) {
        return String.format("%09d", random.nextInt(1000000000));
    }

    public static String getBrokerId(final Random random, final int brokerSize) {
        return String.format("BROKER%03d", random.nextInt(brokerSize));
    }

    public static String getClientId(final Random random, final int clientSize) {
        return String.format("CLIENT%03d", random.nextInt(clientSize));
    }

    public static String getCusip(final Random random) {
        return String.format("%09d", random.nextInt(1000000000));
    }

    public static double getCommissionFee(final Random random) {
        return Math.round((1 + (9 * random.nextDouble())) * 100.0) / 100.0;
    }

    public static double getQuantity(final Random random) {
        return random.nextInt(5000) + 1;
    }

    public static double getPricePerShare(final Random random) {
        return Math.round((5 + (95 * random.nextDouble())) * 100.0) / 100.0;
    }

    public static LocalDate getTransactionDate(final Random random) {
        return LocalDate.of(2008, 1, 1) // Start date
                .plusDays(random.nextInt((int) (LocalDate.of(2025, 12, 31).toEpochDay() - LocalDate.of(2008, 1, 1).toEpochDay())));
    }

    public static LocalDate getSettlementDate(final Random random, final LocalDate transactionDate) {
        return transactionDate.plusDays(random.nextInt(5));
    }

    public static TransactionType getTransactionType(final Random random) {
        final TransactionType[] transactionTypes = TransactionType.values();
        return transactionTypes[random.nextInt(transactionTypes.length)];
    }

    public static TransactionStatus getTransactionStatus(final Random random) {
        final TransactionStatus[] transactionStatuses = TransactionStatus.values();
        return transactionStatuses[random.nextInt(transactionStatuses.length)];
    }
}
