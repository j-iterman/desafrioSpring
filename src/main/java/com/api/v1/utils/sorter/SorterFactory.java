package com.api.v1.utils.sorter;

import com.api.v1.utils.sorter.impl.HeapSortSorterImple;

public class SorterFactory {

    public static Sorter getInstance() {
        return new HeapSortSorterImple();
    }
}

