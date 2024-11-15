package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class GenericDAO {

    private static final String Database = "Treino";
    private static final String URL = "jdbc:mysql://localhost:3306/" + Database;
    private static final String USER = "root";
    private static final String PASSWORD = "P4ssw0rd";

    private static final String Create_Table_Treino =
            "CREATE TABLE IF NOT EXISTS treino (" +
                    "id INT NOT NULL PRIMARY KEY, " +
                    "date VARCHAR(8) NOT NULL, " +
                    "muscularGroup VARCHAR(30) NOT NULL, " +
                    "exercises VARCHAR(100) NOT NULL);";

    private static final String Create_Table_TreinoCasa =
            "CREATE TABLE IF NOT EXISTS treinocasa (" +
                    "time INT NOT NULL, " +
                    "id INT, " +
                    "FOREIGN KEY (id) REFERENCES treino(id));";

    private static final String Create_Table_TreinoAcademia =
            "CREATE TABLE IF NOT EXISTS treinoacademia (" +
                    "academia VARCHAR(30) NOT NULL, " +
                    "id INT, " +
                    "FOREIGN KEY (id) REFERENCES treino(id));";

    public GenericDAO() {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(Create_Table_Treino);
            stmt.execute(Create_Table_TreinoCasa);
            stmt.execute(Create_Table_TreinoAcademia);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void onUpgrade(int newVersion) {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS treino");
            stmt.execute("DROP TABLE IF EXISTS treinocasa");
            stmt.execute("DROP TABLE IF EXISTS treinoacademia");

            // Recria as tabelas
            stmt.execute(Create_Table_Treino);
            stmt.execute(Create_Table_TreinoCasa);
            stmt.execute(Create_Table_TreinoAcademia);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
