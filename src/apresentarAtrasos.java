import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class EmprestimoGestao {

    /**Apresentar a lista de utentes com atraso superior a um número de dias fornecido*/
    public static void apresentarUtentesComAtraso(List<Emprestimo> emprestimos, int diasAtraso) {

        /**Lista para armazenar os utentes com atraso superior aos dias especificados*/
        ArrayList<String> utentesComAtraso = new ArrayList<>();

        /**Verifica os empréstimos*/
        for (Emprestimo e : emprestimos) {

            /**Calcula o número de dias de atraso (se houver)*/
            long atraso = ChronoUnit.DAYS.between(e.getDataDevolucao(), LocalDate.now());

            /**Verifica se o atraso é maior que o número de dias informado pelo usuário*/
            if (atraso > diasAtraso) {
                utentesComAtraso.add(e.getUtente());  /**Adiciona o utente à lista*/
            }
        }

        /**Apresenta a lista de utentes com atraso*/
        if (!utentesComAtraso.isEmpty()) {
            System.out.println("Utentes com atraso superior a " + diasAtraso + " dias:");
            for (int i = 0; i < utentesComAtraso.size(); i++) {
                System.out.println(utentesComAtraso.get(i));
            }
        } else {
            System.out.println("Não há utentes com atraso superior a " + diasAtraso + " dias.");
        }
    }
}