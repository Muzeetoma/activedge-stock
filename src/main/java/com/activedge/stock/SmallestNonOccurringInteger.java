package com.activedge.stock;
import java.util.HashSet;
import java.util.Set;

public class SmallestNonOccurringInteger {
    public static void main(String[] args) {
        int[] array1 = {1, 3, 6, 4, 1, 2};
        int[] array2 = {5, -1, -3};

        System.out.println(findSmallestNonOccurringInteger(array1));
        System.out.println(findSmallestNonOccurringInteger(array2));
    }

    public static int findSmallestNonOccurringInteger(int[] arr) {
        Set<Integer> numbers = new HashSet<>();

        // Add all positive numbers to the set
        for (int num : arr) {
            if (num > 0) {
                numbers.add(num);
            }
        }

        // Find the smallest positive integer not in the set
        int smallestMissing = 1;
        while (numbers.contains(smallestMissing)) {
            smallestMissing++;
        }

        return smallestMissing;
    }
}
