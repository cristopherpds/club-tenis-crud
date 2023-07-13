package views;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

import dao.PagamentoDAO;
import models.Pagamento;

public class PagamentoView {
  public static void viewPagamentoMenu() {
    PagamentoDAO pagDAO = new PagamentoDAO();

    int opcao = 0;

    do {
      String escolha = JOptionPane.showInputDialog(
          "Menu de Pagamento\n\n" +
              "1. Adicionar Pagamento\n" +
              "2. Atualizar Pagamento\n" +
              "3. Excluir Pagamento\n" +
              "4. Visualizar Pagamento\n" +
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
          BigDecimal pagamento_valor = new BigDecimal(JOptionPane.showInputDialog("Digite o valor a ser pago: "));
          //fazer um try
          LocalDateTime now = LocalDateTime.now();
          Timestamp pagamento_date = Timestamp.valueOf(now);
          Pagamento novoPagamento = new Pagamento();
          novoPagamento.setPagamento_data(pagamento_date);
          novoPagamento.setPagamento_valor(pagamento_valor);

          pagDAO.save(novoPagamento);
          JOptionPane.showMessageDialog(null, "Pagamento realizado com sucesso!");
          break;
        case 2:
          int id;
          BigDecimal novoPagamento_valor = new BigDecimal(10);
          try {
            id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do pagamento a ser actualizado."));
          } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                "ID inválido. Por favor, tente novamente.");
            e.printStackTrace();
            continue;
          }

          if (!novoPagamento_valor.equals(BigDecimal.ZERO)) {
            try {
              novoPagamento_valor = new BigDecimal(JOptionPane.showInputDialog("Digite o valor a ser pago: "));
            } catch (NumberFormatException e) {
              JOptionPane.showMessageDialog(null, "Valor invalido, tente novamente.");
            }
            LocalDateTime pnow = LocalDateTime.now();
            Timestamp novoPagamento_date = Timestamp.valueOf(pnow);
            Pagamento pagamentoAtualizado = new Pagamento();
            pagamentoAtualizado.setPagamento_data(novoPagamento_date);
            pagamentoAtualizado.setPagamento_valor(novoPagamento_valor);

            pagamentoAtualizado.setIdpagamento(id);

            pagDAO.updatePagamento(pagamentoAtualizado);
            JOptionPane.showMessageDialog(null, "Pagamento atualizado com sucesso!");
          } else {
            JOptionPane.showMessageDialog(null,
                "Ooops! algo ocorreu errado favor tente denovo");
          }
          break;
        case 3:
          int idExcluir;
          try {
            idExcluir = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do pagamento a ser excluido"));
          } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                "ID inválido. Por favor, tente novamente.");
            continue;
          }

          pagDAO.deleteById(idExcluir);
          JOptionPane.showMessageDialog(null, "Pagamneto excluído com sucesso!");
          break;
        case 4:
          StringBuilder sb = new StringBuilder();
          sb.append("Lista de Pagamentos:\n\n");
          for (Pagamento p : pagDAO.findPagamentos()) {
            sb.append("ID: ").append(p.getIdpagamento()).append("\n");
            sb.append("Data pagamento: ").append(p.getPagamento_data()).append("\n");
            sb.append("Valor pagamento: ").append(p.getPagamento_valor()).append("\n");
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
