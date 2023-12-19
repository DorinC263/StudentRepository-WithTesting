package org.example;
import java.time.LocalDate;
import java.time.Period;

public class Student {
    private String firstName;
    private String lastName;
    private int birthYear;
    private int birthMonth;
    private int birthDay;
    private String gender;
    private String id;

    public Student(String firstName, String lastName, int birthYear, int birthMonth, int birthDay, String gender, String id) {
        validateName(firstName, lastName);
        validateDateOfBirth(birthYear, birthMonth, birthDay);
        validateGender(gender);
        validateID(id);

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.gender = gender.toLowerCase().equals("male") || gender.toLowerCase().equals("m") ? "Male" : "Female";
        this.id = id;
    }

    private void validateName(String firstName, String lastName) {
        if (firstName.isEmpty() || lastName.isEmpty()) {
            throw new IllegalArgumentException("First name and last name required!");
        }
    }

    private void validateDateOfBirth(int birthYear, int birthMonth, int birthDay) {
        int currentYear = getCurrentYear();
        int currentMonth = getCurrentMonth();
        int currentDay = getCurrentDay();
        if (birthYear < 1900 || birthYear > currentYear - 18 ||
                (birthYear == currentYear - 18 && (birthMonth > currentMonth ||
                        (birthMonth == currentMonth && birthDay > currentDay)))) {
            throw new IllegalArgumentException("Date of birth should be between 1900 and current year");
        }
    }

    private int getCurrentYear() {
        return 2023;
    }

    private int getCurrentMonth() {
        return 12;
    }

    private int getCurrentDay() {
        return 11;
    }
    private void validateID(String id) {
        if (id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be empty");
        }
        if (id.length() != 13) {
            throw new IllegalArgumentException("ID should be exactly 13 digits long");
        }
        if (!id.matches("\\d+")) {
            throw new IllegalArgumentException("ID should contain only digits");
        }
    }
    private void validateGender(String gender) {
        if (!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female") &&
                !gender.equalsIgnoreCase("m") && !gender.equalsIgnoreCase("f")) {
            throw new IllegalArgumentException("Invalid gender");
        }
    }

    public String getId() {
        return id;
    }
    public int getBirthYear(){
        return birthYear;
    }

    public int getBirthMonth() {
        return birthMonth;
    }
    public int getBirthDay() {
        return birthDay;
    }
    public String getLastName(){
        return lastName;
    }
    public int getAge() {
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(birthYear, birthMonth, birthDay);
        return Period.between(birthday, today).getYears();
    }

    @Override
    public String toString() {
        return "Student: " +
                "FistName: " + firstName +
                ", LastName: " + lastName +
                ", BirthYear: " + birthYear +
                ", BirthMonth: " + birthMonth +
                ", BirthDay: " + birthDay +
                ", Gender: " + gender +
                ", Id: " + id ;
    }

}
