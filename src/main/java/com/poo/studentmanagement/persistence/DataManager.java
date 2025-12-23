package com.poo.studentmanagement.persistence;

import com.poo.studentmanagement.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pela persistência de dados usando File e ObjectStreams.
 * Implementa operações de leitura e escrita de objetos Student.
 */
public class DataManager {
    private static final String DATA_FILE = "students.dat";
    private List<Student> students;
    
    public DataManager() {
        this.students = new ArrayList<>();
    }
    
    /**
     * Salva a lista de estudantes em arquivo usando ObjectOutputStream
     */
    public void saveData() throws IOException {
        File file = new File(DATA_FILE);
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(file))) {
            oos.writeObject(students);
            System.out.println("Dados salvos com sucesso em: " + file.getAbsolutePath());
        }
    }
    
    /**
     * Carrega a lista de estudantes do arquivo usando ObjectInputStream
     */
    @SuppressWarnings("unchecked")
    public void loadData() throws IOException, ClassNotFoundException {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(file))) {
                students = (List<Student>) ois.readObject();
                System.out.println("Dados carregados com sucesso: " + students.size() + " estudantes");
            }
        } else {
            System.out.println("Arquivo de dados não encontrado. Iniciando com lista vazia.");
            students = new ArrayList<>();
        }
    }
    
    /**
     * Adiciona um novo estudante
     */
    public void addStudent(Student student) {
        students.add(student);
    }
    
    /**
     * Remove um estudante
     */
    public boolean removeStudent(Student student) {
        return students.remove(student);
    }
    
    /**
     * Atualiza um estudante existente
     */
    public boolean updateStudent(Student oldStudent, Student newStudent) {
        int index = students.indexOf(oldStudent);
        if (index != -1) {
            students.set(index, newStudent);
            return true;
        }
        return false;
    }
    
    /**
     * Retorna a lista de estudantes
     */
    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }
    
    /**
     * Busca estudante por ID
     */
    public Student findStudentById(int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Gera um novo ID único para estudante
     */
    public int generateNewId() {
        return students.stream()
                .mapToInt(Student::getId)
                .max()
                .orElse(0) + 1;
    }
    
    /**
     * Verifica se um ID já existe
     */
    public boolean idExists(int id) {
        return students.stream().anyMatch(s -> s.getId() == id);
    }
}
