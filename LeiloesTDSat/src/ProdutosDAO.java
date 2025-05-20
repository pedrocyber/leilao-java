import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProdutosDAO {

    private Connection conectar() {
        try {
            // Ajuste a URL, usuário e senha conforme seu banco
            String url = "jdbc:mysql://localhost:3306/seubanco";
            String user = "root";
            String password = "sua_senha";
            Connection con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro conexão: " + e.getMessage());
            return null;
        }
    }

    public void cadastrarProduto(ProdutosDTO produto) {
        Connection con = conectar();
        if (con == null) return;
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, produto.getNome());
            pst.setInt(2, produto.getValor());
            pst.setString(3, produto.getStatus());
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + e.getMessage());
        }
    }

    public List<ProdutosDTO> listarProdutos() {
        List<ProdutosDTO> lista = new ArrayList<>();
        Connection con = conectar();
        if (con == null) return lista;
        String sql = "SELECT * FROM produtos";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ProdutosDTO p = new ProdutosDTO();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getInt("valor"));
                p.setStatus(rs.getString("status"));
                lista.add(p);
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar: " + e.getMessage());
        }
        return lista;
    }
}
