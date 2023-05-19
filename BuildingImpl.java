import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuildingImpl implements IBuilding {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/java";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
    private int getBuildingId(Building building) {
        String buildingName = building.getName();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement selectStatement = connection.prepareStatement("SELECT id FROM buildings WHERE name = ?")) {
    
            selectStatement.setString(1, buildingName);
            ResultSet resultSet = selectStatement.executeQuery();
    
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return 0; 
    }
    @Override
    public void incrementOccupiedRooms(Building building) {
        int buildingId = getBuildingId(building); 
        if (buildingId != 0) {
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                 PreparedStatement updateStatement = connection.prepareStatement("UPDATE buildings SET occupied_rooms = occupied_rooms + 1 WHERE id = ?")) {
    
                updateStatement.setInt(1, buildingId);
                updateStatement.executeUpdate();
    
                building.setOccupiedRooms(building.getOccupiedRooms() + 1);
    
                System.out.println("Occupied rooms count for building " + building.getName() + " incremented.");
    
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Building not found.");
        }
    }
    @Override
    public void decrementOccupiedRooms(Building building) {
        int buildingId = getBuildingId(building); 
        if (buildingId != 0) {
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                 PreparedStatement updateStatement = connection.prepareStatement("UPDATE buildings SET occupied_rooms = occupied_rooms - 1 WHERE id = ?")) {
    
                updateStatement.setInt(1, buildingId);
                updateStatement.executeUpdate();
    
                building.setOccupiedRooms(building.getOccupiedRooms() + 1);
    
                System.out.println("Occupied rooms count for building " + building.getName() + " decremented.");
    
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Building not found.");
        }
    }

    
 
public Building getBuildingByName(String name) {
    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
         PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM buildings WHERE name = ?")) {

        selectStatement.setString(1, name);
        ResultSet resultSet = selectStatement.executeQuery();

        if (resultSet.next()) {
            int numberOfRooms = resultSet.getInt("number_of_rooms");
            int occupiedRooms = resultSet.getInt("occupied_rooms");

            // Create and return the Building object
            return new Building(name, numberOfRooms, occupiedRooms);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null; 
}


    @Override
    public List<Building> getAllBuildings() {
        List<Building> buildings = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM buildings")) {

            while (resultSet.next()) {
                Building building = Building.fromResultSet(resultSet);
                buildings.add(building);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return buildings;
    }
    @Override
    public Building getBuildingById(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM buildings WHERE id = ?")) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Building.fromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public void addBuilding(Building building) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO buildings (name, number_of_rooms, occupied_rooms) VALUES (?, ?, ?)")) {

            statement.setString(1, building.getName());
            statement.setInt(2, building.getMaxRoomsCount());
            statement.setInt(3, building.getOccupiedRoomsCount());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
