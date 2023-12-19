package org.example;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentRepositoryTest {
    private StudentRepository repository;
    private Logger logger;

    @BeforeEach
    public void setUp() {
        repository = new StudentRepository();
        logger = Logger.getLogger(StudentRepository.class.getName());
    }

    @Test
    public void testAddStudent() {
        Student student = new Student("John", "Doe", 2000, 5, 15, "Male", "1234565432432");
        repository.addStudent(student);

        // Retrieve the student added to the repository by iteration
        boolean found = false;
        for (Student s : repository.students) {
            if (s.getId().equals("1234565432432")) {
                found = true;
                assertEquals(student, s);
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testDeleteStudent() {
        Student student = new Student("Alice", "Smith", 1998, 8, 25, "Female", "1234565432432");
        repository.addStudent(student);

        repository.deleteStudent("1234565432432");

        // Ensure the student is no longer in the repository by iteration
        boolean found = false;
        for (Student s : repository.students) {
            if (s.getId().equals("1234565432432")) {
                found = true;
                break;
            }
        }
        assertFalse(found);
    }

    @Test
    public void testRetrieveStudentsWithAge() {
        Student student1 = new Student("John", "Doe", 2000, 5, 15, "Male", "1234565432432");
        Student student2 = new Student("Alice", "Smith", 1998, 8, 25, "Female", "1234565432432");
        repository.addStudent(student1);
        repository.addStudent(student2);

        // Retrieve students with age 23
        int count = 0;
        for (Student s : repository.retrieveStudentsWithAge(23)) {
            count++;
        }
        assertEquals(1, count);
    }

    @Test
    public void testAddStudentWithEmptyID() {
        // Test adding a student with an empty ID
        Student student = null;
        try {
            student = new Student("Bob", "Johnson", 1995, 10, 8, "Male", "");
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("ID cannot be empty", e.getMessage());
        }
        assertNull(student);
    }

    @Test
    public void testAddStudentWithLessThan13Digits() {
        Student student = null;
        try {
            student = new Student("Bob", "Johnson", 1995, 10, 8, "Male", "634643HFD");
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("ID should be exactly 13 digits long", e.getMessage());
        }
        assertNull(student);
    }
    @Test
    public void testAddStudentWithLettersInID(){
        Student student = null;
        try{
            student = new Student("Mircea","CelBatran",1988,1,22,"Male","434TYH5645IOH");
            fail("Expected an IllegalArgumentException to be thrown");
        }catch (IllegalArgumentException e){
            assertEquals("ID should contain only digits",e.getMessage());
        }
        assertNull(student);
    }

    @Test
    public void testListStudentsOrderByLastName() {
        // Prepare test data
        Student student1 = new Student("John", "Doe", 1997, 5, 15, "Male", "1234567890123");
        Student student2 = new Student("Alice", "Smith", 1998, 8, 25, "Female", "9876543210987");
        Student student3 = new Student("Bob", "Johnson", 1996, 4, 10, "Male", "4567890123456");

        // Add students to the repository
        repository.addStudent(student1);
        repository.addStudent(student2);
        repository.addStudent(student3);

        // Call the method to test
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        repository.listStudentsOrderByLastName();

        // Capture the output
        String output = outContent.toString().trim();

        // Check if students are listed in the correct order by last name
        assertTrue(output.indexOf("Doe") < output.indexOf("Johnson"));
        assertTrue(output.indexOf("Johnson") < output.indexOf("Smith"));
    }
}