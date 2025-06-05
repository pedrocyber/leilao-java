import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class vendasVIEW extends JFrame {

    private JTable tabelaVendas;
    private JButton btnVoltar;

    public vendasVIEW() {
        initComponents();
        listarVendas();
    }

    private void initComponents() {
        setTitle("Produtos Vendidos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        JScrollPane scrollPane = new JScrollPane();
        tabelaVendas = new JTable();

        tabelaVendas.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "ID", "Nome", "Valor", "Status" }
        ));
        scrollPane.setViewportView(tabelaVendas);

        btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> dispose());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(200)
                .addComponent(btnVoltar)
                .addContainerGap(200, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
            .addGap(20)
            .addComponent(btnVoltar)
            .addContainerGap()
        );
    }

    private void listarVendas() {
        try {
            ProdutosDAO dao = new ProdutosDAO();
            ArrayList<ProdutosDTO> lista = dao.vendas();

            DefaultTableModel model = (DefaultTableModel) tabelaVendas.getModel();
            model.setRowCount(0); // Limpa

            for (ProdutosDTO produto : lista) {
                model.addRow(new Object[]{
                    produto.getId(),
                    produto.getNome(),
                    produto.getValor(),
                    produto.getStatus()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar vendas: " + e.getMessage());
        }
    }
}
