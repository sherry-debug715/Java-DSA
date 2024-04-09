package com.jiuzhang;

public class ReferenceExample {

    public static void main(String[] args) {
        runArrayTest();
    }

    static void runStudentExample1() {
        System.out.println("Student example 1");

        Student student1 = new Student();
        student1.name = "Tom";
        Student student2 = new Student();
        student2.name = "Jerry";
        student1.print();
        student2.print();

        student2.name = "Math";
        student1.print();
        student2.print();

        System.out.println();
    }

    static void runStudentExample2() {
        System.out.println("Student example 2");

        Student student1 = new Student();
        student1.name = "Tom";
        Student student2 = student1;
        student1.print();
        student2.print();

        student2.name = "Jack";
        student1.print();
        student2.print();

        System.out.println();
    }

    static void runStudentExample3() {
        System.out.println("Student example 3");

        Student student1 = new Student();
        student1.name = "Tom";
        Student student2 = new Student();
        student2.name = "Rose";

        student1.print();
        student2.print();

        Student t = student1;
        student1 = student2;
        student2 = t;

        student1.print();
        student2.print();

        System.out.println();
    }

    static void runNullExample() {
        System.out.println("Null example");

        Student student = new Student();
        student.name = "Jack";
        student.print();

        Student[] students = new Student[2];

        students[0] = new Student();
        students[0].name = "Jack";
        students[0].print();
    }

    static void runArrayTest() {
        System.out.println("Array test");

        int[][] arr = new int[3][3];
        print2DArray(arr);

        arr[0] = new int[10];
        print2DArray(arr);

        System.out.println();
    }

    static void print2DArray(int[][] arr) {

        for(int i = 0; i < arr.length; ++i) {
            for(int j = 0; j < arr[i].length; ++j) {
                System.out.print(arr[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

}

class Student {
    String name;
    int score;
    void print() {
        System.out.println(name + ": " + score);
    }
}
