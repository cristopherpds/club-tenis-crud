package views;

import javax.swing.JOptionPane;

import dao.InscreveSeDAO;
import models.InscreveSe;

public class InscreveSeView {
  public static void viewInscreveSeMenu() {
    InscreveSeDAO inscreveSeDAO = new InscreveSeDAO();

    int opcao = 0;

    do {
      String escolha = JOptionPane.showInputDialog(
          "Menu de Inscricao\n\n" +
              "1. Realizar Inscricao\n" +
              "2. Atualizar Inscricao\n" +
              "3. Excluir Inscricao\n" +
              "4. Visualizar Inscricao\n" +
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
          int socio_idsocio;
          int modalidade_idmodalidade;
          int pagamento_idpagamento;
          try {
            socio_idsocio = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do Socio"));
          } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                "ID inválido. Por favor, tente novamente.");
            e.printStackTrace();
            continue;
          }

          try {
            modalidade_idmodalidade = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da Modalidade"));
          } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                "ID inválido. Por favor, tente novamente.");
            e.printStackTrace();
            continue;
          }

          try {
            pagamento_idpagamento = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do Pagamento"));
          } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                "ID inválido. Por favor, tente novamente.");
            e.printStackTrace();
            continue;
          }

          if (socio_idsocio != 0 && modalidade_idmodalidade != 0 && pagamento_idpagamento != 0) {
            InscreveSe novoInscreveSe = new InscreveSe();
            novoInscreveSe.setSocio_idsocio(socio_idsocio);
            novoInscreveSe.setModalidade_idmodalidade(modalidade_idmodalidade);
            novoInscreveSe.setPagamento_idpagamento(pagamento_idpagamento);

            inscreveSeDAO.save(novoInscreveSe);
            JOptionPane.showMessageDialog(null, "Inscricao realizada com sucesso!");
          } else {
            JOptionPane.showMessageDialog(null,
                "Ooops! algo ocorreu errado favor tente denovo");
          }
          break;
        case 2:
          int id;
          int novoSocio_idsocio;
          int novoModalidade_idmodalidade;
          int novoPagamento_idpagamento;
          try {
            id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da Inscricao a ser atualizado"));
          } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                "ID inválido. Por favor, tente novamente.");
            e.printStackTrace();
            continue;
          }
          try {
            novoSocio_idsocio = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do socio a ser atualizado"));
          } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                "ID inválido. Por favor, tente novamente.");
            e.printStackTrace();
            continue;
          }

          try {
            novoModalidade_idmodalidade = Integer
                .parseInt(JOptionPane.showInputDialog("Digite o ID da Modalidade a ser atualizado"));
          } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                "ID inválido. Por favor, tente novamente.");
            e.printStackTrace();
            continue;
          }

          try {
            novoPagamento_idpagamento = Integer
                .parseInt(JOptionPane.showInputDialog("Digite o ID do Pagamento a ser atualizado"));
          } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                "ID inválido. Por favor, tente novamente.");
            e.printStackTrace();
            continue;
          }

          if (id != 0 && novoSocio_idsocio != 0 && novoModalidade_idmodalidade != 0 && novoPagamento_idpagamento != 0) {
            InscreveSe inscreveSeActualizado = new InscreveSe();
            inscreveSeActualizado.setSocio_idsocio(novoSocio_idsocio);
            inscreveSeActualizado.setModalidade_idmodalidade(novoModalidade_idmodalidade);
            inscreveSeActualizado.setPagamento_idpagamento(novoPagamento_idpagamento);

            inscreveSeActualizado.setIdinscreve_se(id);
            inscreveSeDAO.updateInscreveSe(inscreveSeActualizado);
            JOptionPane.showMessageDialog(null, "Inscricao atualizada com sucesso!");
          } else {
            JOptionPane.showMessageDialog(null,
                "Ooops! algo ocorreu errado favor tente denovo");
          }
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
          inscreveSeDAO.deleteByID(idExcluir);
          JOptionPane.showMessageDialog(null, "Socio excluído com sucesso!");
          break;
        case 4:
          StringBuilder sb = new StringBuilder();
          sb.append("Lista de Inscricoes:\n\n");
          for (InscreveSe I : inscreveSeDAO.findInscreveSe()) {
            sb.append("ID: ").append(I.getIdinscreve_se()).append("\n");
            sb.append("ID Modalidade: ").append(I.getModalidade_idmodalidade()).append("\n");
            sb.append("ID Pagamento: ").append(I.getPagamento_idpagamento()).append("\n");
            sb.append("ID Socio: ").append(I.getSocio_idsocio()).append("\n");
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
