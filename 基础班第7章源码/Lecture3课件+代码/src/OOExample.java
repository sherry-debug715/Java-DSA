public class OOExample {
    public static void main(String[] args) {
        Student stu = new Student("Jack", 89);
        stu.setScore(70);
        System.out.println(stu.getScore());
        stu.setScore(-100);
        System.out.print(stu.getScore());
    }
}

class Student {
    public String name;
    public int score;

    public void speak() {
        System.out.println(name + ": " + score);
    }

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public void setScore(int score) {
        if (score >= 0 && score <= 100) {
            this.score = score;
        }
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
