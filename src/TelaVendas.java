import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TelaVendas extends JFrame {

    private final JTable tabelaVendas;
    private final DefaultTableModel modeloTabela;
    
    public TelaVendas() {
        setTitle("Tela de Vendas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configura a tabela
        tabelaVendas = new JTable();
        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("ID");
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Valor");
        modeloTabela.addColumn("Status");
        tabelaVendas.setModel(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaVendas);
        add(scrollPane, BorderLayout.CENTER);

        // Carregar as vendas
        carregarVendas();
    }

    // Método para carregar os produtos vendidos
    private void carregarVendas() {
        ProdutosDAO produtosDAO = new ProdutosDAO();
        ArrayList<ProdutosDTO> produtosVendidos = produtosDAO.listarVendas();

        // Limpar a tabela antes de adicionar os dados
        modeloTabela.setRowCount(0);

        // Adicionar as vendas na tabela
        for (ProdutosDTO produto : produtosVendidos) {
            modeloTabela.addRow(new Object[]{
                produto.getId(),
                produto.getNome(),
                produto.getValor(),
                produto.getStatus()
            });
        }
    }

    // Método principal para testar a tela
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaVendas tela = new TelaVendas();
            tela.setVisible(true);
        });
    }
}
