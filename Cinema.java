package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int totalRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int totalCols = scanner.nextInt();
        int iNumPurchasedTickets = 0;
        int iCurrentIncome = 0;
        int iTotalIncome = 0;

        char[][] chrSeating = new char[totalRows][totalCols];
        for (char[] chars : chrSeating) {
            Arrays.fill(chars, 'S');
        }

        if (totalRows * totalCols <= 60)
            iTotalIncome = 10 * totalRows * totalCols;
        else
            iTotalIncome = (totalRows / 2 * 10 * totalCols) + ((totalRows - (totalRows / 2)) * 8 * totalCols);

        while (true) {

            System.out.println();
            System.out.println("""
                    1. Show the seats
                    2. Buy a ticket
                    3. Statistics
                    0. Exit""");

            int iChoice = scanner.nextInt();

            if (iChoice == 0)
                break;
            else if (iChoice == 1) {
                printGrid(chrSeating, totalCols);
            } else if (iChoice == 2) {
                while (true) {
                    System.out.println();
                    System.out.println("Enter a row number:");
                    int rowNum = scanner.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    int colNum = scanner.nextInt();

                    if (rowNum > 9 || colNum > 9) {
                        System.out.println("\nWrong input!");
                        continue;
                    }

                    if (chrSeating[rowNum - 1][colNum - 1] != 'B') {
                        chrSeating[rowNum - 1][colNum - 1] = 'B';

                        iNumPurchasedTickets++;

                        int iTicketPrice = 0;
                        if (totalRows * totalCols <= 60)
                            iTicketPrice = 10;
                        else {
                            if (rowNum <= (totalRows / 2))
                                iTicketPrice = 10;
                            if (rowNum >= (totalRows - (totalRows / 2)))
                                iTicketPrice = 8;
                        }

                        System.out.printf("\nTicket price: $%d", iTicketPrice);
                        System.out.println();
                        iCurrentIncome = iCurrentIncome + iTicketPrice;
                        break;
                    } else
                        System.out.println("\nThat ticket has already been purchased!");
                }

            } else if (iChoice == 3) {
                System.out.println();
                System.out.println("Number of purchased tickets: " + iNumPurchasedTickets);
                double getPercentValue = (double) iNumPurchasedTickets / (totalRows * totalCols);
                double usedPercentage = getPercentValue * 100;
                String strTemp = String.format("Percentage: %.2f", usedPercentage) + '%';
                System.out.println(strTemp);
                System.out.println("Current income: $" + iCurrentIncome);
                System.out.println("Total income: $" + iTotalIncome);
            }

        }

    }

    private static void printGrid(char[][] chrSeating, int totalCols) {
        System.out.println();
        System.out.println("Cinema:");

        String rowHeader = "  ";
        for (int i = 1; i <= totalCols; i++)
            rowHeader = rowHeader + i + " ";

        System.out.println(rowHeader);
        for (int i = 0; i < chrSeating.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < chrSeating[i].length; j++) {
                System.out.print(chrSeating[i][j] + " ");
            }
            System.out.println();
        }
    }
}
