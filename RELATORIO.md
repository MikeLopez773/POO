# Relatório Técnico - Sistema de Gestão de Estudantes

**Disciplina**: Programação Orientada a Objetos (POO)  
**Projeto**: Sistema de Gestão de Estudantes com JavaFX  
**Data**: Dezembro 2024

---

## 1. Introdução

Este relatório descreve o desenvolvimento de um Sistema de Gestão de Estudantes implementado em Java, utilizando JavaFX para interface gráfica e persistência de dados através de arquivos binários. O projeto foi desenvolvido como demonstração prática dos conceitos fundamentais de Programação Orientada a Objetos.

### 1.1 Objetivos

- Implementar uma aplicação com interface gráfica usando JavaFX
- Demonstrar persistência de dados com File e ObjectStreams
- Utilizar LocalDate para manipulação de datas
- Aplicar princípios de POO (encapsulamento, herança, polimorfismo)
- Criar uma arquitetura MVC (Model-View-Controller)

---

## 2. Descrição da Aplicação

O Sistema de Gestão de Estudantes é uma aplicação desktop que permite gerenciar informações de estudantes em uma instituição de ensino. A aplicação oferece uma interface gráfica intuitiva para realizar operações CRUD (Create, Read, Update, Delete) e persistir os dados em arquivo.

### 2.1 Funcionalidades Principais

1. **Cadastro de Estudantes**
   - Adicionar novos estudantes com ID gerado automaticamente
   - Campos: Nome, Email, Data de Nascimento, Curso, Média (GPA)
   - Validação de dados de entrada

2. **Visualização de Dados**
   - Tabela interativa exibindo todos os estudantes
   - Colunas: ID, Nome, Email, Data de Nascimento, Data de Matrícula, Curso, Média
   - Seleção de linha para edição/exclusão

3. **Edição de Estudantes**
   - Seleção de estudante na tabela
   - Modificação de dados existentes
   - Atualização em tempo real

4. **Remoção de Estudantes**
   - Exclusão com confirmação
   - Atualização automática da interface

5. **Persistência de Dados**
   - Salvamento em arquivo binário (students.dat)
   - Carregamento de dados salvos
   - Uso de ObjectStreams para serialização

---

## 3. Estrutura da Solução

### 3.1 Arquitetura MVC

O projeto segue o padrão arquitetural Model-View-Controller:

```
┌─────────────┐       ┌──────────────┐       ┌─────────────┐
│    View     │◄─────►│  Controller  │◄─────►│    Model    │
│  (FXML)     │       │   (Java)     │       │   (Java)    │
└─────────────┘       └──────────────┘       └─────────────┘
                             │
                             ▼
                      ┌──────────────┐
                      │ Persistence  │
                      │   (File I/O) │
                      └──────────────┘
```

### 3.2 Pacotes e Classes

#### 3.2.1 Pacote Model (`com.poo.studentmanagement.model`)

**Classe: Student**
- Representa a entidade estudante
- Implementa `Serializable` para persistência
- Atributos:
  - `int id`: Identificador único
  - `String name`: Nome do estudante
  - `String email`: Email de contato
  - `LocalDate birthDate`: Data de nascimento
  - `LocalDate enrollmentDate`: Data de matrícula
  - `String course`: Curso matriculado
  - `double gpa`: Média acadêmica (0.0-4.0)

**Conceitos POO aplicados:**
- **Encapsulamento**: Atributos privados com getters/setters
- **Override**: Métodos equals(), hashCode() e toString()
- **Serialização**: Interface Serializable implementada

#### 3.2.2 Pacote Persistence (`com.poo.studentmanagement.persistence`)

**Classe: DataManager**
- Gerencia a persistência de dados
- Operações:
  - `saveData()`: Salva lista de estudantes em arquivo
  - `loadData()`: Carrega lista de estudantes do arquivo
  - `addStudent()`: Adiciona novo estudante
  - `removeStudent()`: Remove estudante
  - `updateStudent()`: Atualiza dados do estudante
  - `generateNewId()`: Gera ID único sequencial

**Tecnologias utilizadas:**
- `ObjectOutputStream`: Serialização de objetos
- `ObjectInputStream`: Deserialização de objetos
- `FileOutputStream/FileInputStream`: Manipulação de arquivos
- `ArrayList<Student>`: Estrutura de dados em memória

#### 3.2.3 Pacote Controller (`com.poo.studentmanagement.controller`)

**Classe: MainViewController**
- Controlador da interface principal
- Gerencia interações do usuário
- Conecta View (FXML) com Model/Persistence
- Responsabilidades:
  - Validação de entrada
  - Processamento de eventos
  - Atualização da interface
  - Tratamento de erros

#### 3.2.4 Classe Principal

**Classe: MainApp**
- Estende `javafx.application.Application`
- Inicializa a aplicação JavaFX
- Carrega arquivo FXML
- Configura janela principal

#### 3.2.5 View (FXML)

**Arquivo: MainView.fxml**
- Define layout da interface
- Componentes:
  - GridPane: Formulário de entrada
  - TableView: Exibição de dados
  - Buttons: Ações do usuário
  - DatePicker: Seleção de datas (LocalDate)
- Compatível com Scene Builder
- Estilização inline CSS

---

## 4. Tecnologias e Ferramentas

### 4.1 Linguagem e Framework

- **Java 11**: Linguagem de programação base
- **JavaFX 17**: Framework para interface gráfica
- **FXML**: Linguagem de marcação para layouts

### 4.2 Gerenciamento de Projeto

- **Maven**: Build automation e gerenciamento de dependências
- **pom.xml**: Configuração de dependências e plugins

### 4.3 Persistência

- **File I/O**: Manipulação de arquivos
- **ObjectOutputStream/ObjectInputStream**: Serialização de objetos
- **Serializable**: Interface para permitir serialização

### 4.4 API de Datas

- **LocalDate**: API moderna do Java 8+ para datas
- **DatePicker**: Componente JavaFX para seleção de datas

---

## 5. Justificativa das Decisões Técnicas

### 5.1 Escolha do JavaFX

**Vantagens:**
- Framework moderno e poderoso para desktop
- Suporte nativo a FXML e Scene Builder
- Componentes ricos (TableView, DatePicker)
- Boa separação entre lógica e apresentação
- Documentação extensa e comunidade ativa

### 5.2 Padrão MVC

**Benefícios:**
- Separação de responsabilidades
- Facilita manutenção e evolução
- Permite testes independentes
- Reutilização de componentes
- Código mais organizado e legível

### 5.3 Persistência com ObjectStreams

**Justificativa:**
- Requisito explícito do projeto
- Simplicidade de implementação
- Serialização automática de objetos
- Não requer biblioteca externa
- Adequado para aplicações de pequeno/médio porte

**Limitações conhecidas:**
- Não é ideal para grandes volumes de dados
- Formato binário não legível
- Versionamento pode causar problemas
- Para produção, considerar: JSON, XML ou banco de dados

### 5.4 Uso de LocalDate

**Vantagens:**
- API moderna e intuitiva
- Imutável e thread-safe
- Melhor que Date/Calendar legados
- Integração perfeita com DatePicker
- Suporte a serialização

### 5.5 Maven para Build

**Razões:**
- Padrão da indústria
- Gerenciamento automático de dependências
- Estrutura de projeto padronizada
- Plugin JavaFX disponível
- Facilita compartilhamento do projeto

---

## 6. Fluxo de Dados

### 6.1 Adição de Estudante

```
Usuário preenche formulário
        ↓
MainViewController valida dados
        ↓
DataManager gera novo ID
        ↓
Cria objeto Student
        ↓
Adiciona à lista (memória)
        ↓
Atualiza TableView
```

### 6.2 Persistência

```
Usuário clica "Salvar Dados"
        ↓
MainViewController chama DataManager.saveData()
        ↓
DataManager cria ObjectOutputStream
        ↓
Serializa ArrayList<Student>
        ↓
Escreve em students.dat
        ↓
Fecha streams
        ↓
Exibe confirmação ao usuário
```

### 6.3 Carregamento

```
Usuário clica "Carregar Dados"
        ↓
MainViewController chama DataManager.loadData()
        ↓
DataManager verifica existência do arquivo
        ↓
Cria ObjectInputStream
        ↓
Deserializa ArrayList<Student>
        ↓
Fecha streams
        ↓
MainViewController atualiza ObservableList
        ↓
TableView é automaticamente atualizada
```

---

## 7. Conceitos de POO Demonstrados

### 7.1 Encapsulamento

```java
private String name;  // Atributo privado

public String getName() {  // Getter público
    return name;
}

public void setName(String name) {  // Setter público com validação
    this.name = name;
}
```

### 7.2 Herança e Interfaces

```java
public class Student implements Serializable {
    // Implementa interface para serialização
}

public class MainApp extends Application {
    // Herda de Application do JavaFX
}
```

### 7.3 Polimorfismo

```java
@Override
public boolean equals(Object o) {
    // Implementação personalizada
}

@Override
public String toString() {
    // Representação em string customizada
}
```

### 7.4 Abstração

- Separação de camadas (Model, View, Controller)
- DataManager abstrai complexidade de I/O
- Interface limpa e focada em responsabilidades específicas

---

## 8. Validações e Tratamento de Erros

### 8.1 Validações Implementadas

1. **Campos Obrigatórios**: Nome, Email, Data de Nascimento, Curso
2. **GPA**: Deve ser número entre 0.0 e 4.0
3. **Data de Nascimento**: Não pode ser nula
4. **Email**: Não pode ser vazio

### 8.2 Tratamento de Exceções

- `IOException`: Erros de leitura/escrita de arquivo
- `ClassNotFoundException`: Problemas de deserialização
- `NumberFormatException`: Conversão de GPA inválida
- Mensagens de erro amigáveis ao usuário
- Logs no console para debug

---

## 9. Possíveis Melhorias Futuras

### 9.1 Funcionalidades

- Pesquisa e filtros avançados
- Exportação para PDF/Excel
- Gráficos e estatísticas
- Histórico de alterações
- Autenticação de usuários

### 9.2 Técnicas

- Banco de dados (SQLite, PostgreSQL)
- Testes unitários (JUnit)
- Integração contínua (CI/CD)
- Internacionalização (i18n)
- Temas customizáveis

### 9.3 Arquitetura

- Injeção de dependências
- Repository pattern
- Service layer
- DTO (Data Transfer Objects)

---

## 10. Como Compilar e Executar

### 10.1 Pré-requisitos

```bash
# Verificar Java
java -version  # Deve ser 11+

# Verificar Maven
mvn -version   # Deve ser 3.6+
```

### 10.2 Compilação

```bash
# Navegar para o diretório do projeto
cd /caminho/para/POO

# Limpar e compilar
mvn clean compile
```

### 10.3 Execução

```bash
# Executar com Maven
mvn javafx:run
```

### 10.4 Edição com Scene Builder

1. Instalar Scene Builder (https://gluonhq.com/products/scene-builder/)
2. Abrir arquivo: `src/main/resources/com/poo/studentmanagement/view/MainView.fxml`
3. Editar visualmente
4. Salvar e recompilar

---

## 11. Testes Realizados

### 11.1 Testes Funcionais

- ✅ Adicionar estudante com dados válidos
- ✅ Adicionar estudante com dados inválidos (deve rejeitar)
- ✅ Editar estudante existente
- ✅ Remover estudante com confirmação
- ✅ Limpar formulário
- ✅ Salvar dados em arquivo
- ✅ Carregar dados do arquivo
- ✅ Persistência entre execuções

### 11.2 Testes de Interface

- ✅ Responsividade da tabela
- ✅ Seleção de linhas
- ✅ DatePicker com LocalDate
- ✅ Mensagens de status
- ✅ Diálogos de erro
- ✅ Diálogos de confirmação

### 11.3 Testes de Dados

- ✅ ID gerado automaticamente
- ✅ Data de matrícula preenchida automaticamente
- ✅ Serialização de objetos
- ✅ Deserialização de objetos
- ✅ Integridade dos dados após save/load

---

## 12. Conclusão

O Sistema de Gestão de Estudantes atende todos os requisitos especificados:

✅ **Interface Gráfica**: JavaFX com FXML e compatibilidade com Scene Builder  
✅ **Persistência**: File e ObjectStreams para armazenamento de dados  
✅ **Datas**: LocalDate utilizado para datas de nascimento e matrícula  
✅ **POO**: Encapsulamento, herança, polimorfismo e abstração aplicados  
✅ **Arquitetura**: Padrão MVC implementado  
✅ **Funcionalidades**: CRUD completo implementado  

O projeto demonstra com sucesso a aplicação prática dos conceitos de Programação Orientada a Objetos em uma aplicação real, com interface gráfica moderna e persistência de dados funcional.

### 12.1 Aprendizados

- Desenvolvimento de interfaces com JavaFX e FXML
- Persistência de objetos com serialização
- Arquitetura MVC em aplicações desktop
- Uso de API moderna de datas (LocalDate)
- Validação de dados e tratamento de erros
- Integração de componentes gráficos com lógica de negócio

### 12.2 Valor Acadêmico

Este projeto serve como exemplo completo para estudantes de POO, demonstrando:
- Como estruturar uma aplicação orientada a objetos
- Como separar responsabilidades em camadas
- Como persistir dados sem banco de dados
- Como criar interfaces gráficas profissionais
- Boas práticas de programação Java

---

**Fim do Relatório**
