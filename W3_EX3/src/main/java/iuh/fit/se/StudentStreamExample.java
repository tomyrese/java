package iuh.fit.se;


import java.util.*;
import java.util.stream.*;

public class StudentStreamExample {

    public static class Student {
        private String firstName;
        private String lastName;
        private double age;
        private int grade;
        private final boolean isCurrent;

        public Student(String firstName, String lastName, double age, int grade, boolean isCurrent) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.grade = grade;
            this.isCurrent = isCurrent;
        }

        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }

        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }

        public double getAge() { return age; }
        public void setAge(double age) { this.age = age; }

        public int getGrade() { return grade; }
        public void setGrade(int grade) { this.grade = grade; }

        public boolean isCurrent() { return isCurrent; }
    }

    // danh sach ten mau
    private static String[] firstNames = {"Thanh", "Thao", "Tuan", "Hong", "Lan", "Phuong"};
    private static String[] lastNames = {"Nguyen", "Le", "Tran", "Mai", "Lam", "Ha"};

    // ham sinh du lieu sinh vien ngau nhien
    private static Student[] generateStudentData(int nStudents, int nCurrentStudents) {
        Student[] students = new Student[nStudents];
        Random r = new Random(123);

        for (int i = 0; i < nStudents; i++) {
            String firstName = firstNames[r.nextInt(firstNames.length)];
            String lastName = lastNames[r.nextInt(lastNames.length)];
            double age = r.nextDouble() * 100.0; // tuoi 0..100
            int grade = 1 + r.nextInt(100);      // diem 1..100
            boolean current = (i < nCurrentStudents); // true neu dang hoc

            students[i] = new Student(firstName, lastName, age, grade, current);
        }
        return students;
    }

    public static void main(String[] args) {
        int nStudents = 1_000_000;       // tong so sinh vien
        int nCurrentStudents = 700_000;  // so sinh vien dang hoc

        Student[] students = generateStudentData(nStudents, nCurrentStudents);

        // ---------------------------------------
        // 1. Tinh tuoi trung binh cua sinh vien dang hoc
        // ---------------------------------------

        // a) dung vong lap
        long start = System.currentTimeMillis();
        double sumAge = 0;
        int count = 0;
        for (Student s : students) {
            if (s.isCurrent()) {
                sumAge += s.getAge();
                count++;
            }
        }
        double avgLoop = sumAge / count;
        long end = System.currentTimeMillis();
        System.out.println("Tuoi TB (vong lap): " + avgLoop + " Thoi gian: " + (end - start) + " ms");

        // b) dung stream
        start = System.currentTimeMillis();
        double avgStream = Arrays.stream(students)
                .filter(Student::isCurrent)
                .mapToDouble(Student::getAge)
                .average()
                .orElse(0);
        end = System.currentTimeMillis();
        System.out.println("Tuoi TB (stream): " + avgStream + " Thoi gian: " + (end - start) + " ms");

        // c) dung parallel stream
        start = System.currentTimeMillis();
        double avgParallel = Arrays.stream(students)
                .parallel()
                .filter(Student::isCurrent)
                .mapToDouble(Student::getAge)
                .average()
                .orElse(0);
        end = System.currentTimeMillis();
        System.out.println("Tuoi TB (parallel stream): " + avgParallel + " Thoi gian: " + (end - start) + " ms");

        // ---------------------------------------
        // 2. Dem so sinh vien (parallel stream) voi dieu kien
        //    - khong con hoc
        //    - tuoi > 20
        //    - diem < 65
        // ---------------------------------------
        long countFiltered = Arrays.stream(students)
                .parallel()
                .filter(s -> !s.isCurrent())
                .filter(s -> s.getAge() > 20)
                .filter(s -> s.getGrade() < 65)
                .count();

        System.out.println("So sinh vien thoa dieu kien: " + countFiltered);
    }
}
