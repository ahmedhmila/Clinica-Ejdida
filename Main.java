import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
   

    private static Scanner scanner = new Scanner(System.in);
    private static IBuilding buildingService = new BuildingImpl();
    private static IManager managerService = new ManagerImpl();
    private static IPatient patientService = new PatientImpl();
    private static IMaladie maladieService = new MaladieImpl();
    private static IChamber chamberService = new ChamberImpl();

    private static Manager loggedInManager;

    public static void main(String[] args) {
        initializeBuildings();
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║             Welcome to           ║");
        System.out.println("║           CLINICA EJDIDA!        ║");
        System.out.println("╚══════════════════════════════════╝");
        
        if (hasAccount()) {
            login();
        } else {
            createAccount();
        }

        if (loggedInManager != null) {
            boolean running = true;
            while (running) {
                displayManagerMenu();
                int choice = getUserChoice(1, 9);

                switch (choice) {
                    case 1:
                        addPatient();
                        break;
                    case 2:
                        evacuatePatient();
                        break;
                    case 3:
                    viewPatientInformation();
                                            break;
                    case 4:
                    modifyPatientInformation();
                        break;
                    case 5:
                    viewAllPatientsInformation();
                    break;
                    case 6:
                    checkEmptyRooms();
                    break;
                    case 7:
                        viewMaladiesList();
                        break;
                    case 8:
                    addMaladie();
                    break;
                    case 9:
                    logout();
                        running = false;
                        break;
                        
                }
            }
        }

        System.out.println("Thank you for using the Clinic Management System!");
        main(null); 
    }

    private static void initializeBuildings() {
        List<Building> buildings = buildingService.getAllBuildings();
        if (buildings.isEmpty()) {
            Building urgenceBuilding = new Building("Urgence", 15, 0); 
            Building mainBlocBuilding = new Building("Main Bloc", 30, 0);
            buildingService.addBuilding(urgenceBuilding);
            buildingService.addBuilding(mainBlocBuilding);

            List<Chamber> chambersUrgence = new ArrayList<>();
        List<Chamber> chambersMainBloc = new ArrayList<>();

        for (int i = 1; i <= urgenceBuilding.getMaxRoomsCount(); i++) {
            Chamber chamber = new Chamber(i, false, urgenceBuilding.getName());
            chambersUrgence.add(chamber);
        }

        for (int i = 1; i <= mainBlocBuilding.getMaxRoomsCount(); i++) {
            Chamber chamber = new Chamber(i, false, mainBlocBuilding.getName());
            chambersMainBloc.add(chamber);
        }

        for (Chamber chamber : chambersUrgence) {
            chamberService.addChamber(chamber); // Save  chamber lel database
        }

        for (Chamber chamber : chambersMainBloc) {
            chamberService.addChamber(chamber); 
        }
        } else {
        }
    }
    

    private static boolean hasAccount() {
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║           Do you have an         ║");
        System.out.println("║           account? (Y/N):        ║");
        System.out.println("╚══════════════════════════════════╝");
        
        String choice = scanner.nextLine().trim().toUpperCase();
        return choice.equals("Y");
    }

    private static void login() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine().trim();

        Manager manager = managerService.getManagerByName(name);
        if (manager != null && manager.getMdp().equals(password)) {
            loggedInManager = manager;
            System.out.println("Login successful. Welcome, " + loggedInManager.getNom() + "!");
        } else {
            System.out.println("Invalid credentials. Please try again.");
            login();
        }
    }

    private static void createAccount() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine().trim();

        Manager manager = new Manager(name, password);
        managerService.addManager(manager);

        loggedInManager = manager;
        System.out.println("Account created successfully. Welcome, " + loggedInManager.getNom() + "!");
    }

    private static void displayManagerMenu() {
        System.out.println("╔═══════════════════════════╗");
        System.out.println("║        Manager Menu       ║");
        System.out.println("╠═══════════════════════════╣");
        System.out.println("║ 1. Add a patient          ║");
        System.out.println("║ 2. Evacuate a patient     ║");
        System.out.println("║ 3. View a patient's info  ║");
        System.out.println("║ 4. Modify a patient's info║");
        System.out.println("║ 5. View all patients'info ║");
        System.out.println("║ 6. Check empty rooms      ║");
        System.out.println("║ 7. View maladies list     ║");
        System.out.println("║ 8. Add a maladie          ║");
        System.out.println("║ 9. Logout                 ║ ");  
        System.out.println("╚═══════════════════════════╝");
        System.out.print("Enter your choice: ");
       
    }

    private static int getUserChoice(int min, int max) {
        int choice;
        do {
            System.out.print("Enter your choice (" + min + "-" + max + "): ");
            choice = scanner.nextInt();
            scanner.nextLine(); 
        } while (choice < min || choice > max);
        return choice;
    }
    private static void addPatient() {
        System.out.println("╔═════════════════════════╗");
        System.out.println("║    Ajout d'un patient   ║");
        System.out.println("╚═════════════════════════╝");
        System.out.print("Enter patient's name: ");
        String nom = scanner.nextLine();
        System.out.print("Enter patient's gender (M/F): ");
        String sexe = scanner.nextLine();
      
        String nCIN = "";
        while (nCIN.length() != 8) {
            System.out.print("Enter patient's nCIN (8 characters): ");
            nCIN = scanner.nextLine();
    
            if (nCIN.length() != 8) {
                System.out.println("Error: nCIN length must be 8.");
            }
        }
     
        System.out.println("Select a building:");
    
        List<Building> buildings = buildingService.getAllBuildings();
        System.out.println("╔════════════════════════════╗");
        System.out.println("║      Available Buildings   ║");
        System.out.println("╠════════════════════════════╣");
        for (int i = 0; i < buildings.size(); i++) {
            System.out.println("║"+(i + 1) + ". " + buildings.get(i).getName());
        }
        System.out.println("╚════════════════════════════╝");

        int buildingIndex;
        while (true) {
            System.out.print("Enter the building index: ");
            buildingIndex = Integer.parseInt(scanner.nextLine());
    
            if (buildingIndex >= 1 && buildingIndex <= buildings.size()) {
                break;
            } else {
                System.out.println("Invalid building index. Please try again.");
            }
        }
    
        Building selectedBuilding = buildingService.getBuildingById(buildingIndex);
    
        if (selectedBuilding != null) {
            int occupiedRoomsCount = selectedBuilding.getOccupiedRooms();
            int maxRoomsCount = selectedBuilding.getMaxRoomsCount();
    
            if (occupiedRoomsCount < maxRoomsCount) {
                int chamberNumber;
                boolean isOccupied;
    
                do {
                    System.out.println("Enter chamber number , remember the total rooms is : "+maxRoomsCount);
                    chamberNumber = Integer.parseInt(scanner.nextLine());
    
                    isOccupied = chamberService.isOccupied(chamberNumber, selectedBuilding.getName());
    
                    if (isOccupied) {
                        System.out.println("Chamber is occupied. Please enter another chamber number.");
                    }
                } while (isOccupied);
    
                chamberService.occupyChamber(chamberNumber, selectedBuilding.getName());
                int chamberId = chamberService.getChamberId(selectedBuilding.getName(), chamberNumber);

                List<Maladie> maladies = viewMaladiesList();
    
                System.out.print("Enter patient's maladie (choose from the above list): ");
                int maladieIndex = Integer.parseInt(scanner.nextLine());
    
                if (maladieIndex >= 1 && maladieIndex <= maladies.size()) {
                    String maladie = maladies.get(maladieIndex - 1).getName();
    
                    String phoneNumber = "";
                    while (phoneNumber.length() != 8) {
                        System.out.print("Enter patient's phone number (8 characters): ");
                        phoneNumber = scanner.nextLine();
                
                        if (phoneNumber.length() != 8) {
                            System.out.println("Error: phoneNumber length must be 8.");
                        }
                    }
                    Patient patient = new Patient(nom, sexe, nCIN, maladie,chamberId , phoneNumber);
    
                    buildingService.incrementOccupiedRooms(selectedBuilding);
    
                    patientService.addPatient(patient);
                    System.out.println("╔══════════════════════════════════╗");
                    System.out.println("║ ✓ Patient added successfully ✓  ║");
                    System.out.println("╚══════════════════════════════════╝");
                    System.out.println("");
                } else {
                    System.out.println("Invalid maladie index.");
                }
            } else {
                System.out.println("Cannot add patient. Building is at maximum occupancy.");
            }
        } else {
            System.out.println("Invalid building index.");
        }
    }
    
    
    
    private static void modifyPatientInformation() {
        System.out.println("╔═════════════════════════════╗");
        System.out.println("║  Modify Patient Information ║");
        System.out.println("╚═════════════════════════════╝");
        System.out.print("Enter patient's national identification number (nCIN): ");
        String nCIN = scanner.nextLine().trim();

        Patient patient = patientService.getPatientByNCIN(nCIN);
        if (patient != null) {
            System.out.println("Current Information:");
            System.out.println("Name: " + patient.getNom());
            System.out.println("Gender: " + patient.getSexe());
            System.out.println("nCIN: " + patient.getNCIN());
            System.out.println("Maladie: " + patient.getMaladie());
            System.out.println("Assigned Chamber: " + chamberService.getChamberNumberById(patient.getChamber()));
            System.out.println("Phone Number: " + patient.getPhoneNumber());

            System.out.println("Enter new information (leave blank to keep current value):");
            System.out.print("Name: ");
            String newName = scanner.nextLine().trim();
            if (!newName.isEmpty()) {
                patient.setNom(newName);
            }

            System.out.print("Gender (M/F): ");
            String newGender = scanner.nextLine().trim();
            if (!newGender.isEmpty()) {
                patient.setSexe(newGender);
            }

            System.out.println("Select a building:");
    
            List<Building> buildings = buildingService.getAllBuildings();
            System.out.println("╔════════════════════════════╗");
            System.out.println("║      Available Buildings   ║");
            System.out.println("╠════════════════════════════╣");
            for (int i = 0; i < buildings.size(); i++) {
                System.out.println("║"+(i + 1) + ". " + buildings.get(i).getName());
            }
            System.out.println("╚════════════════════════════╝");
    
            int buildingIndex;
            while (true) {
                System.out.print("Enter the building index: ");
                buildingIndex = Integer.parseInt(scanner.nextLine());
        
                if (buildingIndex >= 1 && buildingIndex <= buildings.size()) {
                    break;
                } else {
                    System.out.println("Invalid building index. Please try again.");
                }
            }
        
            Building selectedBuilding = buildingService.getBuildingById(buildingIndex);
        
            if (selectedBuilding != null) {
                int occupiedRoomsCount = selectedBuilding.getOccupiedRooms();
                int maxRoomsCount = selectedBuilding.getMaxRoomsCount();
        
                if (occupiedRoomsCount < maxRoomsCount) {
                    int chamberNumber;
                    boolean isOccupied;
        
                    do {
                        System.out.print("Enter chamber number: ");
                        chamberNumber = Integer.parseInt(scanner.nextLine());
        
                        isOccupied = chamberService.isOccupied(chamberNumber, selectedBuilding.getName());
        
                        if (isOccupied) {
                            System.out.println("Chamber is occupied. Please enter another chamber number.");
                        }
                    } while (isOccupied);
        
                    chamberService.occupyChamber(chamberNumber, selectedBuilding.getName());
                    int chamberId = chamberService.getChamberId(selectedBuilding.getName(), chamberNumber);
                    String k=Integer.toString(chamberId);
                    patient.setChamber(k);

                }}



            List<Maladie> maladies = viewMaladiesList();

            System.out.println("╔════════════════════════════╗");
            System.out.println("║      Available Maladies    ║");
            System.out.println("╠════════════════════════════╣");
            for (int i = 0; i < maladies.size(); i++) {
                Maladie maladie = maladies.get(i);
                System.out.println("║ " + (i + 1) + ". " + maladie.getName());
            }
            System.out.println("╚════════════════════════════╝");
            System.out.print("Enter patient's maladie (choose from the above list): ");
            int maladieIndex = Integer.parseInt(scanner.nextLine());

            if (maladieIndex >= 1 && maladieIndex <= maladies.size()) {
                String maladie = maladies.get(maladieIndex - 1).getName();
                        patient.setMaladie(maladie);
           

            System.out.print("Phone Number: ");
            String newPhoneNumber = scanner.nextLine().trim();
            if (!newPhoneNumber.isEmpty()) {
                patient.setPhoneNumber(newPhoneNumber);
            }
            patientService.updatePatient(nCIN, patient);

            System.out.println("Patient information modified successfully!");
        }} else {
            System.out.println("Patient not found.");
        }
    }
        

        
        private static void evacuatePatient() {
            System.out.println("╔═════════════════════════════╗");
            System.out.println("║      Evacuate Patient       ║");
            System.out.println("╚═════════════════════════════╝");
            System.out.print("Enter patient's national identification number (nCIN): ");
            String nCIN = scanner.nextLine().trim();
        
            Patient patient = patientService.getPatientByNCIN(nCIN);
            if (patient != null) {
                int chamberId = patient.getChamber();

                chamberService.evacuateChamberById(chamberId);
                System.out.println("Patient evacuated successfully!");
                patientService.evacuatePatient(nCIN);
            } else {
                System.out.println("Patient not found.");
            }
        }
       
        public static void checkEmptyRooms() {
            List<Building> buildings = buildingService.getAllBuildings();
            for (Building building : buildings) {
                int occupiedRooms = chamberService.getOccupiedRoomsCountByBuilding(building.getName());
                int maxRooms = building.getMaxRoomsCount();
                int freeRooms = maxRooms - occupiedRooms;
                System.out.println("╔═════════════════════════════╗");
                System.out.println("║    Checking empty chambres  ║");
                System.out.println("╠═════════════════════════════╝");

                System.out.println("║ Building: " + building.getName());
                System.out.println("║ Occupied Rooms: " + occupiedRooms);
                System.out.println("║ Free Rooms: " + freeRooms);
                System.out.println("╚═════════════════════════════╝");

                System.out.println("------------------------");
            }
        }

    
    
    private static void viewPatientInformation() {
        System.out.println("╔═════════════════════════════╗");
        System.out.println("║   View Patient Information  ║");
        System.out.println("╚═════════════════════════════╝");
        System.out.print("Enter patient's national identification number (nCIN): ");
        String nCIN = scanner.nextLine().trim();

        Patient patient = patientService.getPatientByNCIN(nCIN);
        if (patient != null) {
            System.out.print(patient.toString());
            System.out.print("║ Assigned Chamber: " + chamberService.getChamberNumberById(patient.getChamber())+"\n" ) ;
            System.out.print("║ Assigned Building: " + chamberService.getBuildingNameByChamberId(patient.getChamber())+"\n" ) ;

            System.out.println("╚═══════════════════════════════════════════════════════╝");

        } else {
            System.out.println("Patient not found.");
        }
    }
    private static void addMaladie() {
        System.out.println("╔═════════════════════════════╗");
        System.out.println("║          Add Maladie        ║");
        System.out.println("╚═════════════════════════════╝");
        
   
        System.out.print("Enter maladie name: ");
        String maladieName = scanner.nextLine().trim();

        Maladie maladie = new Maladie(maladieName);
        maladieService.addMaladie(maladie);

        System.out.println("Maladie added successfully!");
    }

    private static  List<Maladie>  viewMaladiesList() {
        List<Maladie> maladies = maladieService.getAllMaladies();
     
    if (!maladies.isEmpty()) {
        System.out.println("╔════════════════════════════╗");
        System.out.println("║      Maladies List:        ║");
        System.out.println("╠════════════════════════════╣");

        for (int i = 0; i < maladies.size(); i++) {
            Maladie maladie = maladies.get(i);
            System.out.println("║ " +(i + 1) + ". " + maladie.getName());
            
        }System.out.println("╚════════════════════════════╝");

    } else {
        System.out.println("No maladies found.");
    }

    return maladies;
}

    private static void viewAllPatientsInformation() {
        System.out.println("╔═════════════════════════════╗");
        System.out.println("║  All Patients' Information: ║");
        System.out.println("╚═════════════════════════════╝");
        
        System.out.println("-----------------");
                List<Patient> patients = patientService.getAllPatients();
        for (Patient patient : patients) {
            System.out.print(patient.toString());
            System.out.print("║ Assigned Chamber: " + chamberService.getChamberNumberById(patient.getChamber())+"\n" ) ;
            System.out.print("║ Assigned Building: " + chamberService.getBuildingNameByChamberId(patient.getChamber())+"\n" ) ;

            System.out.println("╚═══════════════════════════════════════════════════════╝");

            System.out.println("-----------------");
        }
    }

    private static void logout() {
        loggedInManager = null;
        System.out.println("Logout successful. Goodbye!");
    }
}
