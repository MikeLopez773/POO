# POO - Collection Management System

A comprehensive Object-Oriented Programming (OOP) project for managing personal collections such as books, coins, action figures, vinyl records, cards, games, plants, and more.

## Features

### 1. **Item Catalog Management**
- Add, remove, and search items in your collection
- Record detailed information for each item:
  - Name
  - Description
  - Category (Books, Coins, Action Figures, Vinyl, Cards, Games, Plants, etc.)
  - Acquisition Date
  - Paid Value
  - Estimated Value
- View all items or filter by category

### 2. **Contact Management**
- Manage contacts for potential purchases, sales, or trades
- Store contact information:
  - Name
  - Email
  - Phone
  - Type (Buyer/Seller/Trader)
- Search and filter contacts by type

### 3. **Statistics & Analytics**
- View collection statistics:
  - Total number of items
  - Total paid value
  - Total estimated value
  - Potential profit/loss
  - Items count by category
  
### 4. **History Tracking**
- Track collection evolution over time
- View history of all additions and removals

## Project Structure

```
src/main/java/com/collection/
├── Main.java           # Main application with CLI interface
├── Item.java           # Item model class
├── Contact.java        # Contact model class
├── Collection.java     # Collection management class
└── ContactManager.java # Contact management class
```

## How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher

### Compile the Project
```bash
javac -d bin src/main/java/com/collection/*.java
```

### Run the Application
```bash
java -cp bin com.collection.Main
```

## Usage

The application provides an interactive menu-driven interface:

### Main Menu
1. **Manage Items** - Add, remove, view, and search items
2. **Manage Contacts** - Add, remove, view, and search contacts
3. **View Statistics** - See collection statistics and analytics
4. **View History** - Track changes to your collection
5. **Exit** - Close the application

### Example: Adding an Item
1. Select "Manage Items" from the main menu
2. Choose "Add Item"
3. Enter item details:
   - Name: First Edition Harry Potter
   - Description: Signed first edition of Harry Potter and the Philosopher's Stone
   - Category: Books
   - Acquisition Date: 23/12/2020
   - Paid Value: 500.00
   - Estimated Value: 1200.00

### Example: Adding a Contact
1. Select "Manage Contacts" from the main menu
2. Choose "Add Contact"
3. Enter contact details:
   - Name: John Smith
   - Email: john.smith@example.com
   - Phone: +1234567890
   - Type: Buyer

## Design Principles

This project demonstrates key Object-Oriented Programming concepts:

- **Encapsulation**: Private fields with public getters/setters
- **Abstraction**: Separation of concerns with dedicated classes
- **Single Responsibility**: Each class has a specific purpose
- **Code Reusability**: Methods can be reused across the application

## Future Enhancements

Potential improvements:
- Data persistence (save/load from files or database)
- Export to CSV/PDF
- Image attachments for items
- Advanced search and filtering
- Transaction history for trades
- Multi-language support
- GUI interface

## License

This project is for educational purposes.