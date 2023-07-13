package views;

import java.sql.Date;

import javax.swing.JOptionPane;

import dao.ReservaDAO;
import models.Reserva;

public class ReservaView {
  public static void viewReservaMenu() {
    ReservaDAO reservaDAO = new ReservaDAO();

    int opcao = 0;

    do {
      String escolha = JOptionPane.showInputDialog(
          "Menu de Reserva\n\n" +
              "1. Adicionar Reserva\n" +
              "2. Atualizar Reserva\n" +
              "3. Excluir Reserva\n" +
              "4. Visualizar Reserva\n" +
              "5. Sair\n\n" +
              "Escolha uma opção:");

      if (escolha == null) {
        break;
      }

      try {
        opcao = Integer.parseInt(escolha);
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null,
            "Opção inválida. Por favor, tente novamente.");
        continue;
      }

      switch (opcao) {
        case 1:
          int recintoDesportivoId;
          int socioId;
          int pagamentoId;
          Date resDate;
          try {
            recintoDesportivoId = Integer
                .parseInt(JOptionPane.showInputDialog("Digite o ID do Recinto Desportivo"));
            socioId = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID so Socio"));
            pagamentoId = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do Pagamento"));
            resDate = Date.valueOf(
                JOptionPane.showInputDialog("Digite a data da Reserva (AAAA-MM-DD):"));
          } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                "Input inválido. Por favor, tente novamente.");
            e.printStackTrace();
            continue;
          }

          Reserva reserva = new Reserva();
          reserva.setResData(resDate);
          reserva.setRecintoDesportivoId(recintoDesportivoId);
          reserva.setSocioId(socioId);
          reserva.setPagamentoId(pagamentoId);

          reservaDAO.save(reserva);
          JOptionPane.showMessageDialog(null, "Reserva adicionada com sucesso!");
          break;
        case 2:
          int id;
          int novoRecintoDesportivoId;
          int novoSocioId;
          int novoPagamentoId;
          Date novoResDate;
          try {
            id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da Reserva a ser atualizada"));
            novoRecintoDesportivoId = Integer
                .parseInt(JOptionPane.showInputDialog("Digite o ID do Recinto Desportivo"));
            novoSocioId = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID so Socio"));
            novoPagamentoId = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do Pagamento"));
            novoResDate = Date.valueOf(
                JOptionPane.showInputDialog("Digite a data da Reserva (AAAA-MM-DD):"));
          } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                "ID inválido. Por favor, tente novamente.");
            e.printStackTrace();
            continue;
          }

          Reserva reservaActualizada = new Reserva();
          reservaActualizada.setIdReserva(id);
          reservaActualizada.setRecintoDesportivoId(novoRecintoDesportivoId);
          reservaActualizada.setSocioId(novoSocioId);
          reservaActualizada.setPagamentoId(novoPagamentoId);
          reservaActualizada.setResData(novoResDate);

          reservaDAO.updateReserva(reservaActualizada);
          JOptionPane.showMessageDialog(null, "Reserva atualizado com sucesso!");
          break;
        case 3:
          int idExcluir;
          try {
            idExcluir = Integer
                .parseInt(JOptionPane.showInputDialog("Digite o ID do Socio a ser excluído:"));
          } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                "ID inválido. Por favor, tente novamente.");
            continue;
          }
          reservaDAO.deletById(idExcluir);
          JOptionPane.showMessageDialog(null, "Reserva excluído com sucesso!");
          break;
        case 4:
          StringBuilder sb = new StringBuilder();
          sb.append("Lista de Reserva:\n\n");
          for (Reserva r : reservaDAO.findReserva()) {
            sb.append("ID: ").append(r.getIdReserva()).append("\n");
            sb.append("Data da Reserva: ").append(r.getResData()).append("\n");
            sb.append("ID Pagamento: ").append(r.getPagamentoId()).append("\n");
            sb.append("ID Socio: ").append(r.getSocioId()).append("\n");
            sb.append("ID Recinto Desportivo: ").append(r.getRecintoDesportivoId()).append("\n");
            sb.append("-----------------------------\n");
            JOptionPane.showMessageDialog(null, sb.toString());
          }
          break;
        case 5:
          JOptionPane.showMessageDialog(null, "Voltando ao Menu Principal...");
          break;
        default:
          JOptionPane.showMessageDialog(null,
              "Opção inválida. Por favor, tente novamente.");
          break;
      }
    } while (opcao != 5);
  }
}
