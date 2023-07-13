package views;

import javax.swing.JOptionPane;

import dao.RecintoDesportivoDAO;
import models.RecintoDesportivo;

public class RecintoDesportivoView {
  public static void viewRecintoDesportivoMenu() {
    RecintoDesportivoDAO recintoDesportivoDAO = new RecintoDesportivoDAO();

    int opcao = 0;

    do {
      String escolha = JOptionPane.showInputDialog(
          "Menu de Recinto Desportivos\n\n" +
              "1. Adicionar Recinto Desportivo\n" +
              "2. Atualizar Recinto Desportivo\n" +
              "3. Excluir Recinto Desportivo\n" +
              "4. Visualizar Recintos Desportivo\n" +
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
          String nome = JOptionPane.showInputDialog("Digite o nome do Recinto desportivo");

          if (nome != null && !nome.isEmpty()) {
            RecintoDesportivo nRecintoDesportivo = new RecintoDesportivo();
            nRecintoDesportivo.setRecinto_desportivo_nome(nome);

            recintoDesportivoDAO.save(nRecintoDesportivo);
            JOptionPane.showMessageDialog(null, "Recinto desportivo adicionado com sucesso!");
          } else {
            JOptionPane.showMessageDialog(null,
                "Ooops! algo ocorreu errado favor tente denovo");
          }
          break;
        case 2:
          int id;
          try {
            id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do recinto desportivo a ser atualizado"));
          } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                "ID inválido. Por favor, tente novamente.");
            e.printStackTrace();
            continue;
          }

          String novoNome = JOptionPane.showInputDialog("Digite o nome do Recinto desportivo");

          if (novoNome != null && !novoNome.isEmpty()) {
            RecintoDesportivo recintoDesportivoActualizado = new RecintoDesportivo();
            recintoDesportivoActualizado.setRecinto_desportivo_nome(novoNome);
            recintoDesportivoActualizado.setIdrecinto_desportivo(id);

            recintoDesportivoDAO.uRecintoDesportivo(recintoDesportivoActualizado);
            JOptionPane.showMessageDialog(null, "Recinto desportivo atualizado com sucesso!");
          } else {
            JOptionPane.showMessageDialog(null,
                "Ooops! algo ocorreu errado favor tente denovo");
          }
          break;
        case 3:
          int idExcluir;
          try {
            idExcluir = Integer
                .parseInt(JOptionPane.showInputDialog("Digite o ID do Recinto desportivo a ser excluído:"));
          } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                "ID inválido. Por favor, tente novamente.");
            continue;
          }

          recintoDesportivoDAO.deleteByID(idExcluir);
          JOptionPane.showMessageDialog(null, "Recinto desportivo excluído com sucesso!");
          break;
        case 4:
          StringBuilder sb = new StringBuilder();
          for (RecintoDesportivo r : recintoDesportivoDAO.fRecintoDesportivos()) {
            sb.append("ID: ").append(r.getIdrecinto_desportivo()).append("\n");
            sb.append("Nome: ").append(r.getRecinto_desportivo_nome()).append("\n");
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
