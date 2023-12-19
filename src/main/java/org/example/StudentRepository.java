package org.example;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentRepository {
    public List<Student> students;
    private Logger logger;

    public StudentRepository() {
        students = new ArrayList<>();
        logger = Logger.getLogger(StudentRepository.class.getName());
    }

    public void addStudent(Student student) {
        try {
            students.add(student);
            logger.log(Level.INFO, "New student added: " + student.toString());
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Error adding student: " + e.getMessage());
            throw e;
        }
    }

    public void deleteStudent(String id) {
        if (id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be empty");
        }
        boolean removed = students.removeIf(student -> student.getId().equals(id));
        if (!removed) {
            throw new NoSuchElementException("Student does not exist");
        }
        logger.log(Level.INFO, "Student with ID : " + id + " deleted");
    }

    public List<Student> retrieveStudentsWithAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        List<Student> studentWithAge = new ArrayList<>();
        for (Student student : students) {
            if (student.getAge() == age) {
                studentWithAge.add(student);
            }
        }
        return studentWithAge;
    }
    public void listStudentsOrderByLastName() {
        if (students.isEmpty()) {
            throw new IllegalArgumentException("No students available");
        }
        students.sort(Comparator.comparing(Student::getLastName));
        logger.log(Level.INFO, "Students listed in order of Last Name");
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }

    public void listStudentsOrderByBirthDate() {
        if (students.isEmpty()) {
            throw new IllegalArgumentException("No students available");
        }
        students.sort((s1, s2) -> {
            int yearComparison = Integer.compare(s1.getBirthYear(), s2.getBirthYear());
            if (yearComparison != 0) {
                return yearComparison;
            }
            int monthComparison = Integer.compare(s1.getBirthMonth(), s2.getBirthMonth());
            if (monthComparison != 0) {
                return monthComparison;
            }
            return Integer.compare(s1.getBirthDay(), s2.getBirthDay());
        });
        logger.log(Level.INFO, "Students listed in order of Birth Date");
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }
}
