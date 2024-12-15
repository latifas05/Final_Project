# Pet Adoption System

## YOUTUBE Video:

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
 
  ## Database:
  The **Pet Adoption System** uses **PostgreSQL** as the database to store the following information:
- **Pets Table**: Stores data related to pets available for adoption, including pet ID, name, breed, and type.
- **Adopters Table**: Stores data related to adopters, such as name, contact details, and adoption history.
- **Adoption Records Table**: Stores records of adopted pets, including adopter information and adoption date.


  ## SQL Queries for Database Operations

  ### 1. **Create Tables:**

  The following SQL queries create the necessary tables for storing data about pets, adopters, and adoption records.

```sql
-- Creating the Pets Table
CREATE TABLE pets (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    type VARCHAR(10) NOT NULL,
    age INT NOT NULL,
    description TEXT,
    breed VARCHAR(50),
    is_adopted BOOLEAN DEFAULT FALSE
);

-- Creating the Adopters Table
CREATE TABLE adopters (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    contact VARCHAR(100) NOT NULL
);

-- Creating the Adoption Records Table
CREATE TABLE adoption_records (
    id SERIAL PRIMARY KEY,
    adopter_id INT REFERENCES adopters(id),
    pet_id INT REFERENCES pets(id),
    adoption_date DATE DEFAULT CURRENT_DATE, 
    adopter_name VARCHAR(255),
    pet_name VARCHAR(255),
    adopter_contact VARCHAR(255),
    pet_type VARCHAR(255)
);
```
  ## Insert Sample Data:
  ```sql
  -- Inserting data into the Pets Table
INSERT INTO pets (name, type, age, description, breed) VALUES
('Bella', 'Dog', 2, 'Friendly and playful', 'Labrador'),
('Luna', 'Cat', 1, 'Calm and affectionate', 'Persian'),
('Max', 'Dog', 3, 'Energetic and friendly', 'Golden Retriever'),
('Chirpy', 'Bird', 1, 'Loves to sing', 'Canary'),
('Fluffy', 'Rabbit', 2, 'Cute and cuddly', 'Angora'),
('Zeus', 'Dog', 4, 'Loyal and protective', 'German Shepherd'),
('Shadow', 'Cat', 5, 'Independent and mysterious', 'Siamese'),
('Sunny', 'Bird', 2, 'Loves flying around', 'Parrot'),
('Rocky', 'Hamster', 1, 'Small and quick', 'Syrian'),
('Peanut', 'Bird', 1, 'Curious and playful', 'Budgie');
```

  ## Screenshots:
  **Main page:** ![image](https://github.com/user-attachments/assets/b0659411-9f48-4264-af7a-d29ed0899072)
  **Filter By Type:** ![image](https://github.com/user-attachments/assets/c9b65658-0fb3-42a7-92ae-7ec6b8fef29f)
  **View Adopters:** ![image](https://github.com/user-attachments/assets/99c2b5d6-e009-4679-b465-bd6c48ea0203)


  ## ERD Diagram and Relational model:
  ![Erd diagram](https://github.com/user-attachments/assets/b0706865-2373-482a-b5e8-eb375c437938)
  ![Relational model ](https://github.com/user-attachments/assets/99675332-1f26-445b-8420-9933dd197b2b)
  ## UML Diagram:
  ![pet_adoption](https://github.com/user-attachments/assets/4394596a-0de7-490a-9b7b-c441e0e3f1c5)
  
  ## Instructions for Use
  **Installation**
  - Ensure Java and PostgreSQL are installed on your machine.
  - Clone the repository from GitHub.
  - Import the project into your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
     **2. Running the Application:**
  - Open the main application class (HelloApplication) and run it.
  - The GUI will appear, allowing users to manage pets and adopters.
    **3. Using the Application:**
  - Add Pet: Fill in the pet details and click **Add Pet.**
  - Adopt Pet: Select a pet and fill in adopter details, then click **Adopt Pet.**
  - View Adopters: Click the **View Adopters** button to see the list of adopters.
    **4.Error Handling:**
  - The application will display alerts for any errors (e.g., incomplete information).
 
  ## Operation and Maintenance
  **Database Deployment**
  - Ensure PostgreSQL is running.
  - Execute the SQL scripts to create tables and insert sample data.
  - Verify the database connection settings in the Java application (URL, username, password).

  ## Presentation:
  [DataBaseProject.ppt.pdf](https://github.com/user-attachments/files/18141584/DataBaseProject.ppt.pdf)
