package com.poo.studentmanagement.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Classe que representa um estudante no sistema.
 * Implementa Serializable para permitir persistência com ObjectStreams.
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private LocalDate enrollmentDate;
    private String course;
    private double gpa;
    
    /**
     * Construtor padrão
     */
    public Student() {
        this.enrollmentDate = LocalDate.now();
    }
    
    /**
     * Construtor com parâmetros
     */
    public Student(int id, String name, String email, LocalDate birthDate, 
                   String course, double gpa) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.enrollmentDate = LocalDate.now();
        this.course = course;
        this.gpa = gpa;
    }
    
    // Getters e Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public LocalDate getBirthDate() {
        return birthDate;
    }
    
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    
    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }
    
    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
    
    public String getCourse() {
        return course;
    }
    
    public void setCourse(String course) {
        this.course = course;
    }
    
    public double getGpa() {
        return gpa;
    }
    
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", enrollmentDate=" + enrollmentDate +
                ", course='" + course + '\'' +
                ", gpa=" + gpa +
                '}';
    }
}
