import java.sql.ResultSet;
import java.sql.SQLException;

public class Building {
    private int id;
    private String name;
    private int maxRoomsCount;
    private int occupiedRoomsCount;
    

    // Constructor

    public Building( String name, int maxRoomsCount,int occupied_roomsCount) {
        this.name = name;
        this.maxRoomsCount = maxRoomsCount;
        this.occupiedRoomsCount = 0;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxRoomsCount() {
        return maxRoomsCount;
    }

    public void setMaxRoomsCount(int maxRoomsCount) {
        this.maxRoomsCount = maxRoomsCount;
    }

    public int getOccupiedRoomsCount() {
        return occupiedRoomsCount;
    }
    public int getOccupiedRooms() {
        return occupiedRoomsCount;
    }

    public void setOccupiedRooms(int occupiedRooms) {
        this.occupiedRoomsCount = occupiedRooms;
    }
    public void setOccupiedRoomsCount(int occupiedRoomsCount) {
        this.occupiedRoomsCount = occupiedRoomsCount;
    }

    
   
   
    public static Building fromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int numberOfRooms = resultSet.getInt("number_of_rooms");
        int occupiedRooms = resultSet.getInt("occupied_rooms");
        return new Building(name, numberOfRooms,occupiedRooms);
    }


    
}
