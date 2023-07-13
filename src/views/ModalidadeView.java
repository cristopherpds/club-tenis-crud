package views;

import javax.swing.JOptionPane;

import dao.ModalidadeDAO;
import models.Modalidade;

public class ModalidadeView {
  public static void viewModalidadeMenu() {
    ModalidadeDAO modalidadeDAO = new ModalidadeDAO();

    int opcao = 0;

    do {
      String escolha = JOptionPane.showInputDialog(
          "Menu de Modalidade\n\n" +
              "1. Adicionar Modalidade\n" +
              "2. Atualizar Modalidade\n" +
              "3. Excluir Modalidade\n" +
              "4. Visualizar Modalidade\n" +
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
          String nomeModalidade = JOptionPane.showInputDialog("Digite o nome da Modalidade");
          int modalidadeVaga;
          try {
            modalidadeVaga = Integer
                .parseInt(JOptionPane.showInputDialog("Digite a quantidade de vagas da modalidade"));
          } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                "ID inválido. Por favor, tente novamente.");
            e.printStackTrace();
            continue;
          }
          if (nomeModalidade != null && !nomeModalidade.isEmpty()) {
            Modalidade novaModalidade = new Modalidade();
            novaModalidade.setModalidade_nome(nomeModalidade);
            novaModalidade.setModalidade_vagas(modalidadeVaga);

            modalidadeDAO.save(novaModalidade);
            JOptionPane.showMessageDialog(null, "Modalidade adicionada com sucesso!");
          } else {
            JOptionPane.showMessageDialog(null,
                "Ooops! algo ocorreu errado favor tente denovo");
          }
          break;
        case 2:
          int id;
          int novaVagas;
          try {
            id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da modalidade a ser actualizado"));
          } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                "ID inválido. Por favor, tente novamente.");
            e.printStackTrace();
            continue;
          }
          String novoNomeModalidade = JOptionPane.showInputDialog("Digite o nome da modalidade");

          try {
            novaVagas = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da vagas da modalidade"));
          } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                "Valor inválido. Por favor, tente novamente.");
            e.printStackTrace();
            continue;
          }

          if (novoNomeModalidade != null && !novoNomeModalidade.isEmpty()) {
            Modalidade modalidadeActualizada = new Modalidade();
            modalidadeActualizada.setModalidade_nome(novoNomeModalidade);
            modalidadeActualizada.setModalidade_vagas(novaVagas);

            modalidadeActualizada.setIdmodalidade(id);

            modalidadeDAO.updateModalidade(modalidadeActualizada);
            JOptionPane.showMessageDialog(null, "Socio atualizado com sucesso!");
          } else {
            JOptionPane.showMessageDialog(null,
                "Ooops! algo ocorreu errado favor tente denovo");
          }
          break;
        case 3:
          int idExcluir;
          try {
            idExcluir = Integer
                .parseInt(JOptionPane.showInputDialog("Digite o ID da Modalidade a ser excluída:"));
          } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                "ID inválido. Por favor, tente novamente.");
            continue;
          }

          modalidadeDAO.deleteByID(idExcluir);
          JOptionPane.showMessageDialog(null, "Modalidade excluído com sucesso!");
          break;
        case 4:
          StringBuilder sb = new StringBuilder();
          sb.append("Lista de Modalidades:\n\n");
          for (Modalidade m : modalidadeDAO.findModalidade()) {
            sb.append("ID: ").append(m.getIdmodalidade()).append("\n");
            sb.append("Nome da Modalidade: ").append(m.getModalidade_nome()).append("\n");
            sb.append("Quantidade de Vagas: ").append(m.getModalidade_vagas()).append("\n");
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
