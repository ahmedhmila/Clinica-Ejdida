public class Chamber {
    private int number;
    private boolean isOccupied;
    private String building;

    public Chamber(int number, boolean isOccupied, String building) {
        this.number = number;
        this.isOccupied = isOccupied;
        this.building = building;
    }



    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }
}
