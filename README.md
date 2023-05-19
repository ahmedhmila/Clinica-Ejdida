# Clinica-Ejdida
The project is a Clinic Management System called "CLINICA EJDIDA". It provides functionality for managing patients, buildings, chambers, managers, and diseases in a clinic.

The system is implemented using Java programming language. It consists of several classes, including `Main`, which serves as the entry point for the application.

The `Main` class contains the main method, which is responsible for initializing the system and handling user interactions. It uses various service classes, such as `BuildingImpl`, `ManagerImpl`, `PatientImpl`, `MaladieImpl`, and `ChamberImpl`, to perform operations related to buildings, managers, patients, diseases, and chambers.

Upon starting the application, the user is greeted with a welcome message. The system then checks if the user has an existing account. If the user has an account, they are prompted to log in; otherwise, they can create a new account.

Once logged in as a manager, the user is presented with a menu of options to choose from. These options include adding a patient, evacuating a patient, viewing and modifying patient information, viewing all patients' information, checking empty rooms, viewing maladies list, adding a maladie, and logging out.

The `Main` class also contains various methods to handle each menu option. For example, the `addPatient()` method allows the manager to add a new patient to the system by entering their details, selecting a building and chamber, and assigning a maladie to the patient. The `modifyPatientInformation()` method allows the manager to modify an existing patient's information, such as their name, gender, chamber, and maladie.

The system uses other classes, such as `Building`, `Manager`, `Patient`, `Maladie`, and `Chamber`, to represent entities and store their information. It also interacts with the database through the service classes to perform CRUD (Create, Read, Update, Delete) operations.

Overall, the Clinic Management System provides a user-friendly interface for managing patients, buildings, chambers, managers, and diseases in a clinic setting. It offers essential features to streamline the clinic's operations and improve patient care.
There is the database file java.sql that contains all the tables needed also ,included, the presentation as clinica ejdida.mp4 and also the mysqlconnector for the database connections
