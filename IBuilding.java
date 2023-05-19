import java.util.List;


public interface IBuilding {
   

    void incrementOccupiedRooms(Building building);
    void decrementOccupiedRooms(Building building);
    Building getBuildingById(int id);

    Building getBuildingByName(String name);
    List<Building> getAllBuildings();
    void addBuilding(Building building);
}
