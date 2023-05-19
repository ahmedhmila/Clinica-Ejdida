
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaladieImpl implements IMaladie {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/java";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    public void addMaladie(Maladie maladie) {
        String name = maladie.getName();
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Maladie name cannot be empty.");
            return;
        }
    
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO maladies (NOM) VALUES (?)")) {
    
            statement.setString(1, name);
            statement.executeUpdate();
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Maladie> getAllMaladies() {
        List<Maladie> maladies = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM maladies")) {

            while (resultSet.next()) {
                String name = resultSet.getString("nom");
                Maladie maladie = new Maladie(name);
                maladies.add(maladie);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maladies;
    }
}
