# Clinica-Ejdida - Clinic Management System

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Introduction

The Clinica-Ejdida is a Java-based Clinic Management System designed to efficiently manage patients, buildings, chambers, managers, and diseases in a clinical setting. This README provides an overview of the project's structure, functionality, and setup instructions.

## Features

- **User Authentication**: Users can log in as managers or create new accounts.

- **Patient Management**: Managers can add new patients, modify patient information, view all patients' details, and evacuate patients.

- **Building and Chamber Management**: Managers can check empty rooms and assign patients to specific chambers within buildings.

- **Disease Management**: Managers can view a list of diseases, add new diseases, and assign diseases to patients.

- **Database Integration**: The system uses a MySQL database to store and retrieve data, ensuring data persistence.

## Project Structure

The project is organized as follows:

- `Main`: Entry point for the application, handling user interactions and menu options.
- `BuildingImpl`, `ManagerImpl`, `PatientImpl`, `MaladieImpl`, `ChamberImpl`: Service classes for operations related to buildings, managers, patients, diseases, and chambers.
- `Building`, `Manager`, `Patient`, `Maladie`, `Chamber`: Classes representing entities and storing their information.
- `java.sql`: Database file containing tables and data structure.
- MySQL Connector: Included for establishing database connections.

## Getting Started

### Prerequisites

- Web browser (Google Chrome, Mozilla Firefox, etc.)
- MySQL database

### Steps

1. **Clone the Repository**: Clone this repository to your local machine using the following command:

   ```shell
   git clone https://github.com/ahmedhmila/Clinica-Ejdida.git
   ```

2. **Database Setup**:
   - Ensure you have MySQL installed.
   - Create a new database and import the `java.sql` file to set up the necessary tables and data.
   - Update the database connection details (username and password) in the appropriate service classes (`BuildingImpl`, `ManagerImpl`, `PatientImpl`, `MaladieImpl`, `ChamberImpl`) to match your MySQL configuration.
   
   3. **MySQL Connector**: Ensure you have the MySQL Connector for Java in your project's classpath to establish database connections.

4. **Run the Application**:
   - Navigate to the project directory.
   - Compile and run the `Main` class to start the application.

   ```shell
   javac Main.java
   java Main
   ```

## Contributing

Feel free to contribute to this project by submitting bug reports, feature requests, or pull requests on GitHub.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contact

- Project Creator: Ahmed Hmila 
- Project Link: [https://github.com/ahmedhmila/Clinica-Ejdida](https://github.com/ahmedhmila/Clinica-Ejdida)
- 📧 **Email:** [ahmedhmiladev@gmail.com](mailto:ahmedhmiladev@gmail.com)
- 💼 **LinkedIn:** [Let's connect on LinkedIn!](https://www.linkedin.com/in/ahmed-hmila/)
---
