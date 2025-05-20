import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class cadastroVIEW extends JFrame {
    private JButton btnCadastrar;
    private JButton btnProdutos;
    private JTextField cadastroNome;
    private JTextField cadastroValor;
    private JLabel jLabel1;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;

    public cadastroVIEW() {
        initComponents();
    }

    private void initComponents() {
        jLabel1 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        cadastroNome = new JTextField();
        cadastroValor = new JTextField();
        jSeparator1 = new JSeparator();
        jSeparator2 = new JSeparator();
        btnCadastrar = new JButton();
        btnProdutos = new JButton();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jLabel1.setFont(new Font("Lucida Fax", 0, 24));
        jLabel1.setText("Sistema de Leilões");

        jLabel3.setText("Cadastre um novo produto");

        jLabel4.setFont(new Font("Segoe UI", 0, 14));
        jLabel4.setText("Nome:");

        jLabel5.setFont(new Font("Segoe UI", 0, 14));
        jLabel5.setText("Valor:");

        btnCadastrar.setBackground(new Color(153, 255, 255));
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(this::btnCadastrarActionPerformed);

        btnProdutos.setText("Consultar Produtos");
        btnProdutos.addActionListener(this::btnProdutosActionPerformed);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(230)
                        .addComponent(btnCadastrar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(154)
                        .addComponent(jLabel1)))
                .addContainerGap(166, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                    .addComponent(btnProdutos, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, GroupLayout.PREFERRED_SIZE, 477, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(202)
                            .addComponent(jLabel3))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(72)
                            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5))
                            .addGap(31)
                            .addGroup(layout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(cadastroNome)
                                .addComponent(cadastroValor, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(37)
                            .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 477, GroupLayout.PREFERRED_SIZE))))
                .addGap(32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55)
                .addComponent(jLabel1)
                .addGap(30)
                .addComponent(jSeparator2, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(54)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cadastroNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(31)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cadastroValor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(27)
                .addComponent(btnCadastrar)
                .addGap(18)
                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(btnProdutos)
                .addGap(22))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void btnCadastrarActionPerformed(ActionEvent evt) {
        try {
            String nome = cadastroNome.getText();
            String valorTexto = cadastroValor.getText();

            if (nome.isEmpty() || valorTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int valor = Integer.parseInt(valorTexto);
            ProdutosDTO produto = new ProdutosDTO();
            produto.setNome(nome);
            produto.setValor(valor);
            produto.setStatus("A Venda");

            ProdutosDAO produtodao = new ProdutosDAO();
            produtodao.cadastrarProduto(produto);

            JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");

            cadastroNome.setText("");
            cadastroValor.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor deve ser um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnProdutosActionPerformed(ActionEvent evt) {
        listagemVIEW listagem = new listagemVIEW();
        listagem.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(cadastroVIEW.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(() -> new cadastroVIEW().setVisible(true));
    }
}
