package ClassSyntax;

class Student {
    public int id;
    
    public Student(int id) {
        this.id = id;
    }
}

public class StudentId {
        /**
     * Declare a public attribute `students` which is an array of `Student`
     * instances
     */
    // write your code here.
    public Student[] students;
    /**
     * Declare a constructor with a parameter n which is the total number of
     * students in the *class*. The constructor should create n Student
     * instances and initialized with student id from 0 ~ n-1
     */
    public StudentId(int n) {
// new: This keyword is used to create a new object or array instance in Java. 
// - It allocates memory for the new object or array.
// Student[]: This specifies the type of the array being created. 
// - In this case, it's an array that will hold elements of type Student. 
// - The square brackets [] indicate that this is an array type.
// [n]: This specifies the size of the array
        this.students = new Student[n];
        for (int i = 0; i < n; i++) {
            this.students[i] = new Student(i);
        }
    }
}
