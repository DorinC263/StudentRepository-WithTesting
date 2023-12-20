package org.example;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static Logger logger;

    static{
        System.setProperty("java.util.logging.config.file", "src/main/java/org/example/logging.properties");
        logger = Logger.getLogger(Main.class.getName());
    }
    public static void main(String[] args) {

        logger.info("Starting Log");
        try {
            StudentRepository repository = new StudentRepository();
            Student student1 = new Student("Alice", "Mihai", 2000, 5, 14, "Female", "2322341232123");
            Student student2 = new Student("Cristian", "Andrei", 1998, 12, 22, "Male", "1323422323221");
            Student student3 = new Student("Mihai", "Popovici", 1988, 1, 9, "Male", "1323212232218");
            Student student4 = new Student("Cristina", "Longbottom", 2001, 6, 2, "Female", "2323422321231");
            repository.addStudent(student1);
            repository.addStudent(student2);
            repository.addStudent(student3);
            repository.addStudent(student4);

            repository.listStudentsOrderByLastName();
            repository.listStudentsOrderByBirthDate();
//            repository.deleteStudent("2323422321231");

            int ageToRetrieve = 23;
            System.out.println("Students with the age of " + ageToRetrieve + ": ");
            for (Student student : repository.retrieveStudentsWithAge(ageToRetrieve)) {
                System.out.println(student.toString());
            }
            logger.info("Ending Log");
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE,"Error creating student: "+e.getMessage(),e);
            System.err.println("Error creating student: " + e.getMessage());
        }
    }
}
