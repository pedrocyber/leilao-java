import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexaoDAO {
    public Connection conectaBD() {
        Connection conn = null;

        try {
            String url = "jdbc:mysql://localhost:3306/sistemaleilao?useSSL=false&serverTimezone=UTC";
            conn = DriverManager.getConnection(url, "root", "");
        } catch (SQLException erro) {
            System.out.println("Erro de conexão: " + erro.getMessage());
        }

        return conn;
    }
}


