package views;

import java.sql.Date;
import java.util.List;

import javax.swing.JOptionPane;

import dao.SocioDAO;
import models.InscreveSe;
import models.Modalidade;
import models.Socio;

public class SocioView {
  public static void viewSocioMenu() {
    SocioDAO socioDAO = new SocioDAO();

    int opcao = 0;

    do {
      String escolha = JOptionPane.showInputDialog(
          "Menu de Socio\n\n" +
              "1. Adicionar Socio\n" +
              "2. Atualizar Socio\n" +
              "3. Excluir Socio\n" +
              "4. Visualizar Socio\n" +
              "5. Increver Socio em Modalidade\n" +
              "6. Visaulizar Socio em Modalidade\n" +
              "7. Sair\n\n" +
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
          String nome = JOptionPane.showInputDialog("Digite o nome do socio");
          String num_identidade = JOptionPane.showInputDialog("Digite o numero de identificacao do socio:");
          String telefone = JOptionPane.showInputDialog("Digite o numero de telefone do socio:");
          String rua = JOptionPane.showInputDialog("Digite o endereco de rua do socio:");
          String cidade = JOptionPane.showInputDialog("Digite a cidade de residencia do socio:");
          String cp = JOptionPane.showInputDialog("Digite o codigo postal de residencia do socio");

          if (nome != null && !nome.isEmpty() && num_identidade != null && !num_identidade.isEmpty()
              && telefone != null && !telefone.isEmpty() && rua != null && !rua.isEmpty()
              && cidade != null && !cidade.isEmpty() && cp != null && !cp.isEmpty()) {

            Date dataAdmissao = Date.valueOf(
                JOptionPane.showInputDialog("Digite a nova data de nascimento do sócio (AAAA-MM-DD):"));
            Date dataNacimento = Date.valueOf(
                JOptionPane.showInputDialog("Digite a nova data de nascimento do sócio (AAAA-MM-DD):"));

            Socio novoSocio = new Socio();
            novoSocio.setSocio_nome(nome);
            novoSocio.setSocio_data_admissao(dataAdmissao);
            novoSocio.setSocio_data_nacimento(dataNacimento);
            novoSocio.setSocio_num_identidade(num_identidade);
            novoSocio.setSocio_telefone(telefone);
            novoSocio.setSocio_endereco_rua(rua);
            novoSocio.setSocio_endereco_cidade(cidade);
            novoSocio.setSocio_endereco_codigo_postal(cp);

            socioDAO.save(novoSocio);
            JOptionPane.showMessageDialog(null, "Socio adicionado com sucesso!");
          } else {
            JOptionPane.showMessageDialog(null,
                "Ooops! algo ocorreu errado favor tente denovo");
          }
          break;
        case 2:
          int id;
          try {
            id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do socio a ser atualizado"));
          } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                "ID inválido. Por favor, tente novamente.");
            e.printStackTrace();
            continue;
          }

          String novoNome = JOptionPane.showInputDialog("Digite o nome do socio");
          String novoNum_identidade = JOptionPane
              .showInputDialog("Digite o numero de identificacao do socio:");
          String novoTelefone = JOptionPane.showInputDialog("Digite o numero de telefone do socio:");
          String novoRua = JOptionPane.showInputDialog("Digite o endereco de rua do socio:");
          String novoCidade = JOptionPane.showInputDialog("Digite a cidade de residencia do socio:");
          String novoCp = JOptionPane.showInputDialog("Digite o codigo postal de residencia do socio");

          if (novoNome != null && !novoNome.isEmpty() && novoNum_identidade != null
              && !novoNum_identidade.isEmpty()
              && novoTelefone != null && !novoTelefone.isEmpty() && novoRua != null && !novoRua.isEmpty()
              && novoCidade != null && !novoCidade.isEmpty() && novoCp != null && !novoCp.isEmpty()) {
            Date novoDataNacimento = Date.valueOf(
                JOptionPane.showInputDialog("Digite a nova data de nascimento do sócio (AAAA-MM-DD):"));

            Socio socioAtualizado = new Socio();
            socioAtualizado.setSocio_nome(novoNome);
            socioAtualizado.setSocio_data_nacimento(novoDataNacimento);
            socioAtualizado.setSocio_num_identidade(novoNum_identidade);
            socioAtualizado.setSocio_telefone(novoTelefone);
            socioAtualizado.setSocio_endereco_rua(novoRua);
            socioAtualizado.setSocio_endereco_cidade(novoCidade);
            socioAtualizado.setSocio_endereco_codigo_postal(novoCp);
            socioAtualizado.setIdsocio(id);

            socioDAO.updateSocio(socioAtualizado);
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
                .parseInt(JOptionPane.showInputDialog("Digite o ID do Socio a ser excluído:"));
          } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                "ID inválido. Por favor, tente novamente.");
            continue;
          }
          socioDAO.deleteByID(idExcluir);
          JOptionPane.showMessageDialog(null, "Socio excluído com sucesso!");
          break;
        case 4:
          StringBuilder sb = new StringBuilder();
          sb.append("Lista de Sócios:\n\n");
          for (Socio socio : socioDAO.findSocios()) {
            sb.append("ID: ").append(socio.getIdsocio()).append("\n");
            sb.append("Nome: ").append(socio.getSocio_nome()).append("\n");
            sb.append("Data de Admissão: ").append(socio.getSocio_data_admissao()).append("\n");
            sb.append("Número de Identificação: ").append(socio.getSocio_num_identidade()).append("\n");
            sb.append("Data de Nascimento: ").append(socio.getSocio_data_nacimento()).append("\n");
            sb.append("Telefone: ").append(socio.getSocio_telefone()).append("\n");
            sb.append("Endereço: ").append(socio.getSocio_endereco_rua() + ",  " +
                socio.getSocio_endereco_cidade() + ",  " +
                socio.getSocio_endereco_codigo_postal()).append("\n");
            sb.append("-----------------------------\n");

            JOptionPane.showMessageDialog(null, sb.toString());
          }
          break;
        case 5:
          int idSocio = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do socio"));
          int idModalidade = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da modalidade"));
          int idPagamento = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do pagamento"));

          if (idSocio != 0 && idModalidade != 0 && idPagamento != 0) {
            Socio socio = new Socio();
            socio.setIdsocio(idSocio);

            Modalidade modalidade = new Modalidade();
            modalidade.setIdmodalidade(idModalidade);

            InscreveSe inscricao = new InscreveSe();
            inscricao.setSocio_idsocio(idSocio);
            inscricao.setModalidade_idmodalidade(idModalidade);
            inscricao.setPagamento_idpagamento(idPagamento);

            socioDAO.inscribirModalidade(socio, inscricao, modalidade);
            JOptionPane.showMessageDialog(null, "Socio adicionado com sucesso!");
          } else {
            JOptionPane.showMessageDialog(null,
                "Ooops! algo ocorreu errado favor tente denovo");
          }
          break;
          case 6:
          /* List<Socio> sociosInModalidad = socioDAO.findSociosInModalidade();
          StringBuilder sbSociosInModalidade = new StringBuilder();
          sbSociosInModalidade.append("Lista de Sócios em Modalidade:\n\n");
          for (Socio s: sociosInModalidad ) {
            sbSociosInModalidade.append("ID Inscricao: ").append(s.getInscreveSe().getIdinscreve_se()).append("\n");
            sbSociosInModalidade.append("Nome do Socio: ").append(s.getSocio_nome()).append("\n");
            sbSociosInModalidade.append("Nome da Modalidade ").append(s.getModalidade().getModalidade_nome()).append("\n");

            JOptionPane.showMessageDialog(null, sbSociosInModalidade.toString()); */

          List<Socio> sociosInModalidad = socioDAO.findSociosInModalidade();
					StringBuilder sbSociosInModalidade = new StringBuilder();
					for (Socio s: sociosInModalidad) {
            sbSociosInModalidade.append("ID Inscricao: ").append(s.getInscreveSe().getIdinscreve_se()).append("\n");
            sbSociosInModalidade.append("Nome do Socio: ").append(s.getSocio_nome()).append("\n");
						sbSociosInModalidade.append("Nome da Modalidade: ").append(s.getModalidade().getModalidade_nome()).append("\n");
						sbSociosInModalidade.append("---------------------------------\n");
					}
					JOptionPane.showMessageDialog(null, sbSociosInModalidade.toString(),
							"Lista de Cargos y Funcionarios", JOptionPane.INFORMATION_MESSAGE);
          break;
        case 7:
          JOptionPane.showMessageDialog(null, "Voltando ao Menu Principal...");
          break;
        default:
          JOptionPane.showMessageDialog(null,
              "Opção inválida. Por favor, tente novamente.");
          break;
      }
    } while (opcao != 7);
  }
}
