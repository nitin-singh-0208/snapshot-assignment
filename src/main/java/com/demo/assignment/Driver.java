package com.demo.assignment;

import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        SynchronizedUnbalancedSnapshotList<Integer> list = new SynchronizedUnbalancedSnapshotList<>();
        int element, index, version;
        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println("1: Add Element");
        System.out.println("2: Create Snapshot");
        System.out.println("3: Get at Index from Version");
        System.out.println("4: Show Latest Version Number");
        System.out.println("5: Show List at Version");
        System.out.println("6: Show Entire Version History");
        System.out.println("7: Show History from a particular version");
        System.out.println("8: Show menu");
        System.out.println("9: Program termination");
        lp:
        while (true) {
            System.out.print("Make your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter the value to be entered in List: \n");
                    element = sc.nextInt();
                    list.add(element);
                    break;
                case 2:
                    System.out.print("Snapshot created with version " + list.snapshot() + " List details : " + list);
                    break;
                case 3:
                    System.out.print("Enter the index : \n");
                    index = sc.nextInt();
                    System.out.print("Enter the version : \n");
                    version = sc.nextInt();
                    try {
                        int value = list.getAtVersion(index, version);
                        System.out.println("Value at index " + index + " in version " + version + " :: " + value);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Latest Version :" + list.version() + "\n\n");
                    break;
                case 5:
                    System.out.print("Enter the version : \n");
                    version = sc.nextInt();
                    try {
                        List<Integer> snapshot = list.getSnapshotAtVersion(version);
                        System.out.println("Version " + version + " snapshot: " + snapshot);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 6:
                    System.out.print("Version History : \n" + list.getVersionHistory());
                    break;
                case 7:
                    System.out.print("Enter the version : \n");
                    version = sc.nextInt();
                    System.out.print("Version History : \n" + list.getVersionHistory(version));
                    break;
                case 8:
                    System.out.println("1: Add Element");
                    System.out.println("2: Create Snapshot");
                    System.out.println("3: Get at Index from Version");
                    System.out.println("4: Show Latest Version Number");
                    System.out.println("5: Show List at Version");
                    System.out.println("6: Show menu");
                    System.out.println("7: Program termination");
                    break;
                case 9:
                    break lp;
                default:
                    System.out.println("Invalid choice! Please make a valid choice. \n\n");
            }
        }

    }
}
