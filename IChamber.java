public interface IChamber {
    void addChamber(Chamber chamber);
    void occupyChamber(int chamberNumber , String building);
    boolean isOccupied(int number , String building);
    void evacuateChamber(int id);
    int getChamberId(String building, int chamberNumber);
    void evacuateChamberById(int chamberId);

    int getOccupiedRoomsCountByBuilding(String buildingName);
    int getChamberNumberById(int chamberId);
    String getBuildingNameByChamberId(int chamberId);

}