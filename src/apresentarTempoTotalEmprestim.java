import java.time.LocalDate;
import java.util.List;

public class apresentarTempoTotalEmprestim {
    public static void apresentarTotalEmprestimos(List<Emprestimo> emprestimos, LocalDate dataInicio, LocalDate dataFim) {
        long total = 0;

        for (Emprestimo e : emprestimos) {

            /** Verificar se a data do empréstimo está dentro do intervalo de datas*/

            if (!e.getDataEmprestimo().isBefore(dataInicio) && !e.getDataEmprestimo().isAfter(dataFim)) {
                total++;
            }
            /** Se estiver dentro do intervalo, incrementa o total*/

        }
        System.out.println("Total de empréstimos no intervalo: " + total);
    }
}