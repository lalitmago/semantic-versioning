package org.tenx.assignment;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        VersionComparison obj = new VersionComparison();

        System.out.println("Enter the 2 strings for comparison - one in each line below:");
        Scanner scanner = new Scanner(System.in);

        String firstString = scanner.nextLine();
        String secondString = scanner.nextLine();

        System.out.println(obj.compareVersionStrings(firstString, secondString));
    }
}
