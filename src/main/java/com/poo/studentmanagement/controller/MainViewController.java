package com.poo.studentmanagement.controller;

import com.poo.studentmanagement.model.Student;
import com.poo.studentmanagement.persistence.DataManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Controlador para a view principal da aplicação.
 * Gerencia as interações do usuário com a interface.
 */
public class MainViewController {
    
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private TextField courseField;
    @FXML
    private TextField gpaField;
    
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, Integer> idColumn;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> emailColumn;
    @FXML
    private TableColumn<Student, LocalDate> birthDateColumn;
    @FXML
    private TableColumn<Student, LocalDate> enrollmentDateColumn;
    @FXML
    private TableColumn<Student, String> courseColumn;
    @FXML
    private TableColumn<Student, Double> gpaColumn;
    
    @FXML
    private Label statusLabel;
    
    private ObservableList<Student> studentData = FXCollections.observableArrayList();
    private DataManager dataManager;
    
    /**
     * Inicializa o controlador. Chamado automaticamente após o FXML ser carregado.
     */
    @FXML
    private void initialize() {
        dataManager = new DataManager();
        
        // Configurar colunas da tabela
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        enrollmentDateColumn.setCellValueFactory(new PropertyValueFactory<>("enrollmentDate"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
        gpaColumn.setCellValueFactory(new PropertyValueFactory<>("gpa"));
        
        studentTable.setItems(studentData);
        
        // Adicionar listener para seleção na tabela
        studentTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showStudentDetails(newValue)
        );
        
        // Tornar idField não editável
        idField.setEditable(false);
        idField.setDisable(true);
        
        updateStatus("Pronto. Use 'Carregar Dados' para recuperar dados salvos.");
    }
    
    /**
     * Mostra os detalhes de um estudante nos campos de entrada
     */
    private void showStudentDetails(Student student) {
        if (student != null) {
            idField.setText(String.valueOf(student.getId()));
            nameField.setText(student.getName());
            emailField.setText(student.getEmail());
            birthDatePicker.setValue(student.getBirthDate());
            courseField.setText(student.getCourse());
            gpaField.setText(String.valueOf(student.getGpa()));
        }
    }
    
    /**
     * Adiciona um novo estudante
     */
    @FXML
    private void handleAddStudent() {
        if (validateInput()) {
            try {
                int id = dataManager.generateNewId();
                Student student = createStudentFromInput(id);
                
                dataManager.addStudent(student);
                studentData.add(student);
                
                handleClearFields();
                updateStatus("Estudante adicionado: " + student.getName());
            } catch (Exception e) {
                showError("Erro ao adicionar estudante", e.getMessage());
            }
        }
    }
    
    /**
     * Atualiza um estudante existente
     */
    @FXML
    private void handleUpdateStudent() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            showError("Nenhum estudante selecionado", "Por favor, selecione um estudante para atualizar.");
            return;
        }
        
        if (validateInput()) {
            try {
                Student updatedStudent = createStudentFromInput(selectedStudent.getId());
                
                if (dataManager.updateStudent(selectedStudent, updatedStudent)) {
                    int index = studentData.indexOf(selectedStudent);
                    studentData.set(index, updatedStudent);
                    updateStatus("Estudante atualizado: " + updatedStudent.getName());
                    handleClearFields();
                }
            } catch (Exception e) {
                showError("Erro ao atualizar estudante", e.getMessage());
            }
        }
    }
    
    /**
     * Remove um estudante
     */
    @FXML
    private void handleDeleteStudent() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            showError("Nenhum estudante selecionado", "Por favor, selecione um estudante para remover.");
            return;
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Remoção");
        alert.setHeaderText("Remover estudante: " + selectedStudent.getName());
        alert.setContentText("Tem certeza que deseja remover este estudante?");
        
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                dataManager.removeStudent(selectedStudent);
                studentData.remove(selectedStudent);
                updateStatus("Estudante removido: " + selectedStudent.getName());
                handleClearFields();
            }
        });
    }
    
    /**
     * Limpa os campos de entrada
     */
    @FXML
    private void handleClearFields() {
        idField.clear();
        nameField.clear();
        emailField.clear();
        birthDatePicker.setValue(null);
        courseField.clear();
        gpaField.clear();
        studentTable.getSelectionModel().clearSelection();
    }
    
    /**
     * Carrega dados do arquivo
     */
    @FXML
    private void handleLoadData() {
        try {
            dataManager.loadData();
            studentData.clear();
            studentData.addAll(dataManager.getStudents());
            updateStatus("Dados carregados: " + studentData.size() + " estudantes");
        } catch (IOException e) {
            showError("Erro ao carregar dados", "Não foi possível carregar os dados: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            showError("Erro ao carregar dados", "Formato de dados incompatível: " + e.getMessage());
        }
    }
    
    /**
     * Salva dados no arquivo
     */
    @FXML
    private void handleSaveData() {
        try {
            dataManager.saveData();
            updateStatus("Dados salvos com sucesso!");
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText("Dados Salvos");
            alert.setContentText("Os dados foram salvos com sucesso em students.dat");
            alert.showAndWait();
        } catch (IOException e) {
            showError("Erro ao salvar dados", "Não foi possível salvar os dados: " + e.getMessage());
        }
    }
    
    /**
     * Cria um objeto Student a partir dos campos de entrada
     */
    private Student createStudentFromInput(int id) {
        String name = nameField.getText();
        String email = emailField.getText();
        LocalDate birthDate = birthDatePicker.getValue();
        String course = courseField.getText();
        double gpa = Double.parseDouble(gpaField.getText());
        
        return new Student(id, name, email, birthDate, course, gpa);
    }
    
    /**
     * Valida os campos de entrada
     */
    private boolean validateInput() {
        String errorMessage = "";
        
        if (nameField.getText() == null || nameField.getText().trim().isEmpty()) {
            errorMessage += "Nome inválido!\n";
        }
        if (emailField.getText() == null || emailField.getText().trim().isEmpty()) {
            errorMessage += "Email inválido!\n";
        }
        if (birthDatePicker.getValue() == null) {
            errorMessage += "Data de nascimento inválida!\n";
        }
        if (courseField.getText() == null || courseField.getText().trim().isEmpty()) {
            errorMessage += "Curso inválido!\n";
        }
        
        try {
            double gpa = Double.parseDouble(gpaField.getText());
            if (gpa < 0.0 || gpa > 4.0) {
                errorMessage += "GPA deve estar entre 0.0 e 4.0!\n";
            }
        } catch (NumberFormatException e) {
            errorMessage += "GPA inválido! Use um número decimal.\n";
        }
        
        if (errorMessage.isEmpty()) {
            return true;
        } else {
            showError("Dados Inválidos", errorMessage);
            return false;
        }
    }
    
    /**
     * Mostra uma mensagem de erro
     */
    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    /**
     * Atualiza o texto do status
     */
    private void updateStatus(String message) {
        statusLabel.setText(message);
    }
}
