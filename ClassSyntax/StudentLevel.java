package ClassSyntax;

public class StudentLevel {
    /**
     * Declare two public attributes name(string) and score(int).
     */
    public String name;
    public int score;
    
    /**
     * Declare a constructor expect a name as a parameter.
     */
    
    public StudentLevel(String name) {
        this.name = name;
    }
    
    /**
     * Declare a public method `getLevel` to get the level(char) of this student.
     */
     public char getLevel() {
         if (score >= 90) return 'A';
         if (score >= 80) return 'B';
         if (score >= 60) return 'C';
         else return 'D';
     }
}