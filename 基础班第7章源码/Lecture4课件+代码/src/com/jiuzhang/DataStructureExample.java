package com.jiuzhang;

import java.util.ArrayList;
import java.util.List;

public class DataStructureExample {

    public static void main(String[] args) {

        List<Integer> arrayList = new ArrayList<>();  // generic 范型

        // 增删改查 CRUD

        // Create
        arrayList.add(10);   // [10]
        arrayList.add(0, 11);  // [11, 10]

        // Read
        System.out.println(arrayList.get(0));  // 11

        for (int i = 0; i < arrayList.size(); ++i) {
            System.out.println(arrayList.get(i));
        }

        // Update
        arrayList.set(1, 12);  // [11, 12]

        // Delete
        arrayList.remove(0);  // [12]
        arrayList.clear();  // []
    }
}
