import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChamberImpl implements IChamber {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/java";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    @Override
    public void addChamber(Chamber chamber) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO chamber (number, building) VALUES ( ?, ?)")) {
            stmt.setInt(1, chamber.getNumber());
            stmt.setString(2, chamber.getBuilding());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
public void occupyChamber(int chamberNumber, String building) {
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
         PreparedStatement stmt = conn.prepareStatement("UPDATE chamber SET isOccupied = ? WHERE number = ? AND building = ?")) {

        stmt.setBoolean(1, true);
        stmt.setInt(2, chamberNumber);
        stmt.setString(3, building);

        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

@Override
public boolean isOccupied(int number, String building) {
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
         PreparedStatement stmt = conn.prepareStatement("SELECT isOccupied FROM chamber WHERE number = ? AND building = ?")) {

        stmt.setInt(1, number);
        stmt.setString(2, building);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getBoolean("isOccupied");
            } else {
                System.out.println("Chamber with number " + number + " in building " + building + " does not exist.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}
public void evacuateChamber(int id) {
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
         PreparedStatement stmt = conn.prepareStatement("UPDATE chamber SET isOccupied = ? WHERE id = ?")) {

        stmt.setBoolean(1, false);
        stmt.setInt(2, id);

        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public int getChamberId(String building, int chamberNumber) {
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
         PreparedStatement stmt = conn.prepareStatement("SELECT id FROM chamber WHERE building = ? AND number = ?")) {

        stmt.setString(1, building);
        stmt.setInt(2, chamberNumber);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                System.out.println("Chamber with building '" + building + "' and number " + chamberNumber + " does not exist.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return -1;
}

public void evacuateChamberById(int chamberId) {
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
         PreparedStatement stmt = conn.prepareStatement("UPDATE chamber SET isOccupied = ? WHERE id = ?")) {

        // Set the parameter values
        stmt.setBoolean(1, false);
        stmt.setInt(2, chamberId);

        // Execute the query
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}



@Override
public int getOccupiedRoomsCountByBuilding(String buildingName) {
    int count = 0;
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
         PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) AS occupied_rooms FROM chamber WHERE building = ? AND isOccupied = ?")) {

        stmt.setString(1, buildingName);
        stmt.setBoolean(2, true);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt("occupied_rooms");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return count;
}


public int getChamberNumberById(int chamberId) {
    int chamberNumber = -1;  // Default value louken  chamber is not found

    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
         PreparedStatement stmt = conn.prepareStatement("SELECT number FROM chamber WHERE id = ?")) {

        stmt.setInt(1, chamberId);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                chamberNumber = rs.getInt("number");
            } else {
                System.out.println("Chamber with ID " + chamberId + " does not exist.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return chamberNumber;
}


public String getBuildingNameByChamberId(int chamberId) {
    String buildingName = null;

    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
         PreparedStatement stmt = conn.prepareStatement("SELECT building FROM chamber WHERE id = ?")) {

        stmt.setInt(1, chamberId);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                buildingName = rs.getString("building");
            } else {
                System.out.println("Chamber with ID " + chamberId + " does not exist.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return buildingName;
}

}


    // ...

