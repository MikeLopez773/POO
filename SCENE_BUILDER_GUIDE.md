# Guia de Edição com Scene Builder

## O que é o Scene Builder?

Scene Builder é uma ferramenta visual para criar e editar arquivos FXML do JavaFX. Permite arrastar e soltar componentes para criar interfaces gráficas sem escrever código XML manualmente.

## Instalação

1. **Download**: Baixe o Scene Builder em https://gluonhq.com/products/scene-builder/
2. **Instalação**: 
   - Windows: Execute o instalador .msi ou .exe
   - macOS: Arraste para a pasta Applications
   - Linux: Use o AppImage ou instale via package manager

## Como Usar com Este Projeto

### Abrir o arquivo FXML

1. Inicie o Scene Builder
2. Vá em `File > Open`
3. Navegue até: `src/main/resources/com/poo/studentmanagement/view/MainView.fxml`
4. O layout aparecerá na área de design

### Estrutura do Layout

```
BorderPane (raiz)
├── Top: VBox com cabeçalho
├── Center: VBox com:
│   ├── GridPane (formulário)
│   ├── HBox (botões de ação)
│   ├── TableView (tabela de estudantes)
│   └── HBox (botões de persistência)
└── Bottom: HBox com rodapé
```

### Componentes Editáveis

#### 1. Cabeçalho (Top)
- **Labels**: Títulos da aplicação
- **Cores**: Modifique a propriedade `style`

#### 2. Formulário (GridPane)
- **Labels**: Rótulos dos campos
- **TextFields**: Campos de entrada
- **DatePicker**: Seletor de data
- **Propriedades úteis**:
  - `promptText`: Texto de dica
  - `prefWidth`: Largura preferida

#### 3. Botões (HBox)
- **Texto**: Propriedade `text`
- **Ação**: Propriedade `onAction` (mantém `#handleXxx`)
- **Estilo**: Propriedade `style` (CSS inline)
- **Tamanho**: `prefWidth`, `prefHeight`

#### 4. Tabela (TableView)
- **Colunas**: TableColumn
- **Larguras**: `prefWidth` de cada coluna
- **Propriedades**: `fx:id` deve corresponder ao controller

#### 5. Rodapé (Bottom)
- **Label**: Texto informativo
- **Estilo**: Background e cores

### Passos para Editar

#### Modificar Cores

1. Selecione o componente (ex: VBox do cabeçalho)
2. No painel Properties (direita), encontre `Style`
3. Edite o CSS inline:
   ```css
   -fx-background-color: #2c3e50; 
   -fx-padding: 20;
   ```

#### Adicionar Novo Campo

1. Arraste um `Label` do painel Library (esquerda) para o GridPane
2. Arraste um `TextField` ao lado
3. Configure as propriedades:
   - `GridPane.columnIndex`: Coluna (0 ou 1)
   - `GridPane.rowIndex`: Linha (próximo número)
   - `fx:id`: Identificador único (ex: `phoneField`)
   - `promptText`: Dica para o usuário

4. **Importante**: Atualize o controller!
   ```java
   @FXML
   private TextField phoneField;
   ```

#### Adicionar Coluna na Tabela

1. Selecione o TableView
2. No painel Hierarchy, expanda `<columns>`
3. Clique direito > Add Column
4. Configure:
   - `text`: Cabeçalho da coluna
   - `fx:id`: Identificador (ex: `phoneColumn`)
   - `prefWidth`: Largura

5. No controller, adicione:
   ```java
   @FXML
   private TableColumn<Student, String> phoneColumn;
   
   // No método initialize()
   phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
   ```

#### Modificar Layout

1. **Espaçamento**: Propriedade `spacing` em VBox/HBox
2. **Padding**: Propriedade `padding` ou via `style`
3. **Alinhamento**: Propriedade `alignment`
4. **Tamanho**: `prefWidth`, `prefHeight`, `minWidth`, etc.

### IDs importantes (fx:id)

Não altere estes IDs sem atualizar o controller:

**Campos de entrada:**
- `idField`, `nameField`, `emailField`
- `birthDatePicker`, `courseField`, `gpaField`

**Tabela e colunas:**
- `studentTable`
- `idColumn`, `nameColumn`, `emailColumn`
- `birthDateColumn`, `enrollmentDateColumn`
- `courseColumn`, `gpaColumn`

**Labels:**
- `statusLabel`

### Ações dos Botões (onAction)

Mantenha estas referências aos métodos do controller:
- `#handleAddStudent` - Adicionar estudante
- `#handleUpdateStudent` - Atualizar estudante
- `#handleDeleteStudent` - Remover estudante
- `#handleClearFields` - Limpar formulário
- `#handleLoadData` - Carregar dados
- `#handleSaveData` - Salvar dados

### Dicas de Design

#### Cores Sugeridas
- **Cabeçalho**: `#2c3e50` (azul escuro)
- **Botão Adicionar**: `#27ae60` (verde)
- **Botão Atualizar**: `#f39c12` (laranja)
- **Botão Remover**: `#e74c3c` (vermelho)
- **Botão Limpar**: `#95a5a6` (cinza)
- **Botão Carregar**: `#3498db` (azul)
- **Botão Salvar**: `#2ecc71` (verde claro)
- **Rodapé**: `#34495e` (cinza escuro)

#### Fontes
```css
-fx-font-size: 24px;
-fx-font-weight: bold;
-fx-text-fill: white;
```

#### Botões Estilizados
```css
-fx-background-color: #27ae60;
-fx-text-fill: white;
-fx-font-weight: bold;
-fx-background-radius: 5;
-fx-cursor: hand;
```

### Workflow Completo

1. **Editar no Scene Builder**:
   - Modifique o layout visual
   - Adicione/remova componentes
   - Ajuste estilos e cores
   - Salve o arquivo FXML

2. **Atualizar Controller** (se necessário):
   - Adicione campos `@FXML` para novos componentes
   - Crie métodos `handleXxx()` para novos botões
   - Inicialize novos componentes em `initialize()`

3. **Atualizar Model** (se necessário):
   - Adicione atributos em `Student.java`
   - Crie getters/setters
   - Atualize `equals()`, `hashCode()`, `toString()`

4. **Testar**:
   ```bash
   mvn javafx:run
   ```

### Exemplos de Customização

#### Adicionar Campo Telefone

**1. No Scene Builder:**
- Adicione Label "Telefone:" em GridPane (linha 6)
- Adicione TextField com fx:id="phoneField"

**2. Em Student.java:**
```java
private String phone;

public String getPhone() { return phone; }
public void setPhone(String phone) { this.phone = phone; }
```

**3. Em MainViewController.java:**
```java
@FXML
private TextField phoneField;

// No createStudentFromInput:
student.setPhone(phoneField.getText());

// No showStudentDetails:
phoneField.setText(student.getPhone());

// No handleClearFields:
phoneField.clear();
```

#### Adicionar Coluna de Status

**1. No Scene Builder:**
- Adicione TableColumn com fx:id="statusColumn"
- Configure text="Status" e prefWidth="80"

**2. Em Student.java:**
```java
private String status = "Ativo";

public String getStatus() { return status; }
public void setStatus(String status) { this.status = status; }
```

**3. Em MainViewController.java:**
```java
@FXML
private TableColumn<Student, String> statusColumn;

// No initialize():
statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
```

### Recursos Adicionais

- **Documentação JavaFX**: https://openjfx.io/javadoc/17/
- **CSS Reference**: https://openjfx.io/javadoc/17/javafx.graphics/javafx/scene/doc-files/cssref.html
- **Tutoriais**: https://jenkov.com/tutorials/javafx/index.html

### Solução de Problemas

**Erro: Controller não encontrado**
- Verifique se o atributo `fx:controller` está correto no BorderPane raiz

**Erro: Campo não inicializado (NullPointerException)**
- Verifique se o `fx:id` no FXML corresponde ao nome do campo no controller
- Certifique-se de que o campo tem anotação `@FXML`

**Layout quebrado após edição**
- Use `Preview > Show Preview in Window` no Scene Builder para testar
- Verifique constraints de layout (GridPane.rowIndex, etc.)

**Botão não funciona**
- Verifique se `onAction` está configurado (ex: `#handleAddStudent`)
- Certifique-se de que o método existe no controller com anotação `@FXML`

### Conclusão

O Scene Builder facilita muito o desenvolvimento de interfaces JavaFX. Combine a edição visual com conhecimento de código para criar aplicações profissionais. Sempre teste após modificações e mantenha a consistência entre FXML, Controller e Model.
