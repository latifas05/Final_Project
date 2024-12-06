# Pet Adoption System

## Description:
The **Pet Adoption System** is a JavaFX-based application designed to facilitate the adoption process of pets. This system allows users to view available pets for adoption, manage adopter information, and track adoption records. The application integrates with a **PostgreSQL** database to store and manage data related to pets, adopters, and adoption transactions.

## Project Requirements List:
1. Display available pets for adoption in a table format.
2. Allow users to filter pets by type (e.g., dog, cat).
3. CRUD operations for managing pet details.
4. CRUD operations for managing adopter information.
5. Create an adoption record when a pet is adopted.
6. Implement a user-friendly interface using JavaFX.
7. Handle input validation for adopter information.
8. Provide confirmation dialogs for adoption actions.
9. Store data persistently using DAO (Data Access Object) design pattern.
10. Allow users to view a history of all adoption transactions.

## Team Members List:
- **Latifa** - Lead Developer, responsible for implementing core functionalities and integrating the DAO layer.
- **Saadat** - UI Designer, responsible for designing the user interface and ensuring the application is user-friendly.

## Project Structure:
- **Model**:
  - `Pet`: Represents the pet entity with attributes like pet ID, name, breed, etc.
  - `Adopter`: Represents the adopter entity with attributes like adopter name, contact information, etc.
  - `AdoptionRecords`: Stores information about pet adoptions, including the adopter details and adoption date.

  - **DAO**:
  - `PetDAO`: Handles database operations related to pets.
  - `AdopterDAO`: Manages database operations for adopters.
  - `AdoptionRecordsDAO`: Responsible for managing adoption records in the database.


- **Controllers**:
  - `HelloController`: Controller class for managing pet-related functionality.
  - `AdoptersController`: Manages adopter-related functionality.
 
  - **Views**:
  - `HelloApplication`: The main JavaFX application class that launches the pet adoption system.
  - `hello-view.fxml`: The FXML file for the pet management view.
  - `adopters-view.fxml`: The FXML file for managing adopter information.
 
  - **Dialogs**:
  - `PetDialog`: Used to handle the addition or editing of pet details.
 
  ## Database:
  The **Pet Adoption System** uses **PostgreSQL** as the database to store the following information:
- **Pets Table**: Stores data related to pets available for adoption, including pet ID, name, breed, and type.
- **Adopters Table**: Stores data related to adopters, such as name, contact details, and adoption history.
- **Adoption Records Table**: Stores records of adopted pets, including adopter information and adoption date.
 
  ## Screenshots:


  ## UML Class Diagram:
  ![pet_adoption](https://github.com/user-attachments/assets/4394596a-0de7-490a-9b7b-c441e0e3f1c5)

  ## Weekly Meeting Documentation:
  Week 1: [Weekly Meeting Documentation - Week 1 (1).docx](https://github.com/user-attachments/files/18037969/Weekly.Meeting.Documentation.-.Week.1.1.docx)
  
  Week 2: [Weekly Meeting Documentation - Week 2 (1).docx](https://github.com/user-attachments/files/18037976/Weekly.Meeting.Documentation.-.Week.2.1.docx)
  
  Week 3: [Summary of Weekly Meetings.docx](https://github.com/user-attachments/files/18037979/Summary.of.Weekly.Meetings.docx)
