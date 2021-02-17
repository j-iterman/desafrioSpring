package com.api.v1.utils.sorter;

import java.util.ArrayList;
import java.util.Comparator;

public interface Sorter<T> {

    public <T> void sort(ArrayList<T> arr, Comparator<T> c);
}
