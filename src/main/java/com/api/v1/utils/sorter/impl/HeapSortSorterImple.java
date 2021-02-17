package com.api.v1.utils.sorter.impl;

import com.api.v1.utils.sorter.Sorter;

import java.util.ArrayList;
import java.util.Comparator;

public class HeapSortSorterImple implements Sorter {

    public void sort(ArrayList arr, Comparator c) {
        int n = arr.size();

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i, c);

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            Object temp = arr.get(0);
            arr.set(0, arr.get(i));
            arr.set(i, temp);

            // call max heapify on the reduced heap
            heapify(arr, i, 0, c);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void heapify(ArrayList arr, int n, int i, Comparator c)
    {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && (c.compare(arr.get(l), arr.get(largest)) > 0))
            largest = l;

        // If right child is larger than largest so far
        if (r < n && (c.compare(arr.get(r), arr.get(largest)) > 0))
            largest = r;

        // If largest is not root
        if (largest != i) {
            Object swap = arr.get(i);
            arr.set(i, arr.get(largest));
            arr.set(largest, swap);

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest, c);
        }
    }

}
