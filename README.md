# Sistema de Gestão de Estudantes - POO

Sistema de gestão de estudantes desenvolvido em Java com JavaFX, demonstrando conceitos de Programação Orientada a Objetos (POO) com interface gráfica e persistência de dados.

## 🎯 Características

- ✅ Interface gráfica moderna com JavaFX
- ✅ Persistência de dados usando File e ObjectStreams
- ✅ Utilização de LocalDate para datas
- ✅ Operações CRUD completas (Create, Read, Update, Delete)
- ✅ Compatível com Scene Builder para edição visual
- ✅ Arquitetura MVC (Model-View-Controller)

## 📋 Requisitos

- Java 11 ou superior
- Maven 3.6 ou superior
- JavaFX 17

## 🚀 Como Executar

### Usando Maven

```bash
# Compilar o projeto
mvn clean compile

# Executar a aplicação
mvn javafx:run
```

### Usando Java diretamente

```bash
# Compilar
mvn clean package

# Executar
java -jar target/student-management-1.0-SNAPSHOT.jar
```

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/poo/studentmanagement/
│   │       ├── MainApp.java                    # Classe principal JavaFX
│   │       ├── controller/
│   │       │   └── MainViewController.java     # Controlador da interface
│   │       ├── model/
│   │       │   └── Student.java                # Modelo de dados Student
│   │       └── persistence/
│   │           └── DataManager.java            # Gerenciador de persistência
│   └── resources/
│       └── com/poo/studentmanagement/view/
│           └── MainView.fxml                   # Layout da interface
└── pom.xml                                     # Configuração Maven
```

## 🎨 Funcionalidades

### Gestão de Estudantes

1. **Adicionar Estudante**: Preencha os campos e clique em "Adicionar"
2. **Atualizar Estudante**: Selecione um estudante na tabela, modifique os dados e clique em "Atualizar"
3. **Remover Estudante**: Selecione um estudante e clique em "Remover"
4. **Limpar Campos**: Clique em "Limpar" para resetar o formulário

### Persistência de Dados

- **Carregar Dados**: Carrega estudantes salvos anteriormente do arquivo `students.dat`
- **Salvar Dados**: Salva todos os estudantes atuais no arquivo `students.dat`

### Campos de Dados

- **ID**: Gerado automaticamente
- **Nome**: Nome completo do estudante
- **Email**: Endereço de email
- **Data de Nascimento**: Usando DatePicker (LocalDate)
- **Curso**: Nome do curso matriculado
- **Média (GPA)**: Nota média de 0.0 a 4.0

## 💾 Persistência de Dados

O sistema utiliza:
- **File**: Para manipulação de arquivos
- **ObjectOutputStream**: Para serializar e salvar objetos
- **ObjectInputStream**: Para deserializar e carregar objetos
- **Serializable**: Interface implementada na classe Student

Os dados são salvos no arquivo `students.dat` na raiz do projeto.

## 🏗️ Conceitos de POO Aplicados

### Encapsulamento
- Atributos privados com getters e setters
- Métodos públicos para operações controladas

### Herança
- Implementação da interface Serializable

### Polimorfismo
- Override dos métodos equals(), hashCode() e toString()

### Abstração
- Separação clara entre modelo, visualização e controle (MVC)
- DataManager abstrai a complexidade de persistência

## 🎨 Scene Builder

Os arquivos FXML são totalmente compatíveis com o Scene Builder:

1. Abra o Scene Builder
2. Carregue o arquivo `MainView.fxml`
3. Edite visualmente os componentes
4. Salve e execute a aplicação

## 📊 Tecnologias Utilizadas

- **Java 11**: Linguagem de programação
- **JavaFX 17**: Framework para interface gráfica
- **Maven**: Gerenciamento de dependências e build
- **FXML**: Linguagem de marcação para layouts
- **LocalDate**: API de datas do Java 8+

## 👨‍💻 Desenvolvimento

### Adicionar Novas Funcionalidades

1. Modifique o modelo em `Student.java`
2. Atualize a persistência em `DataManager.java`
3. Ajuste a interface em `MainView.fxml`
4. Implemente a lógica em `MainViewController.java`

### Testando

Execute a aplicação e teste:
- Adicionar vários estudantes
- Editar informações
- Remover estudantes
- Salvar dados
- Fechar e reabrir a aplicação
- Carregar dados salvos

## 📝 Relatório

Para documentação completa, consulte o arquivo `RELATORIO.md` que inclui:
- Descrição detalhada da aplicação
- Funcionalidades implementadas
- Estrutura da solução
- Justificativa das decisões técnicas

## 📄 Licença

Projeto acadêmico desenvolvido para fins educacionais.

## 🤝 Contribuições

Este é um projeto acadêmico. Sugestões e melhorias são bem-vindas!