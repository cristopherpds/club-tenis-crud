import javax.swing.JOptionPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import views.SocioView;
import views.RecintoDesportivoView;
import views.ReservaView;
import views.InscreveSeView;
import views.ModalidadeView;
import views.PagamentoView;

public class App extends JFrame implements ActionListener {
    private JButton funcionariosButton;
    private JButton recintoDespButton;
    private JButton pagamentoButton;
    private JButton reservaButton;
    private JButton modalidadeButton;
    private JButton inscreveSeButton;
    private JButton sairButton;

    public App() {
        setTitle("Club de Tenis Rivera - Santana do Livramento");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));
        setResizable(false);

        // Define uma fonte personalizada para os botões
        Font buttonFont = new Font("Inter", Font.BOLD, 16);

        funcionariosButton = new JButton("Socios");
        recintoDespButton = new JButton("Recintos Desportivos");
        pagamentoButton = new JButton("Pagamento");
        reservaButton = new JButton("Reserva");
        modalidadeButton = new JButton("Modalidade");
        inscreveSeButton = new JButton("Inscricao");
        sairButton = new JButton("Sair");

        funcionariosButton.addActionListener(this);
        recintoDespButton.addActionListener(this);
        pagamentoButton.addActionListener(this);
        reservaButton.addActionListener(this);
        modalidadeButton.addActionListener(this);
        inscreveSeButton.addActionListener(this);
        sairButton.addActionListener(this);

        // Aplica a fonte personalizada aos botões
        funcionariosButton.setFont(buttonFont);
        recintoDespButton.setFont(buttonFont);
        pagamentoButton.setFont(buttonFont);
        reservaButton.setFont(buttonFont);
        modalidadeButton.setFont(buttonFont);
        inscreveSeButton.setFont(buttonFont);
        sairButton.setFont(buttonFont);

        // Define uma cor de fundo personalizada para os botões
        Color buttonBackgroundColor = new Color(220, 220, 220);
        funcionariosButton.setBackground(buttonBackgroundColor);
        recintoDespButton.setBackground(buttonBackgroundColor);
        pagamentoButton.setBackground(buttonBackgroundColor);
        reservaButton.setBackground(buttonBackgroundColor);
        modalidadeButton.setBackground(buttonBackgroundColor);
        inscreveSeButton.setBackground(buttonBackgroundColor);
        sairButton.setBackground(buttonBackgroundColor);

        add(funcionariosButton);
        add(recintoDespButton);
        add(pagamentoButton);
        add(reservaButton);
        add(modalidadeButton);
        add(inscreveSeButton);
        add(sairButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == funcionariosButton) {
            SocioView.viewSocioMenu();
        } else if (e.getSource() == recintoDespButton) {
            RecintoDesportivoView.viewRecintoDesportivoMenu();
        } else if (e.getSource() == pagamentoButton) {
            PagamentoView.viewPagamentoMenu();
        } else if (e.getSource() == reservaButton) {
            ReservaView.viewReservaMenu();
        } else if (e.getSource() == modalidadeButton) {
            ModalidadeView.viewModalidadeMenu();
        }else if (e.getSource() == inscreveSeButton) {
            InscreveSeView.viewInscreveSeMenu();
        }else if (e.getSource() == sairButton) {
            JOptionPane.showMessageDialog(null, "Encerrando o programa...");
            dispose(); // Fechar a janela
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                App menu = new App();
                menu.setVisible(true);
            }
        });
    }
}