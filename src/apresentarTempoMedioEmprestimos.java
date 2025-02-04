import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ApresentarTempoMedioEmprestimos {

    /** apresentar o tempo médio dos empréstimos no intervalo*/
    public static void apresentarTempoMedioEmprestimos(List<Emprestimo> emprestimos, LocalDate dataInicio, LocalDate dataFim) {
        long totalDias = 0;
        int count = 0;

        for (Emprestimo e : emprestimos) {

            /**o empréstimo está dentro do intervalo*/
            if (!e.getDataEmprestimo().isBefore(dataInicio) && !e.getDataEmprestimo().isAfter(dataFim)) {

                /** Calcular o número de dias do empréstimo*/
                long dias = ChronoUnit.DAYS.between(e.getDataEmprestimo(), e.getDataDevolucao());
                totalDias += dias;
                count++;
            }
        }

        if (count > 0) {

            /**Calcular e apresentar o tempo médio*/
            long tempoMedio = totalDias / count;
            System.out.println("Tempo médio dos empréstimos no intervalo: " + tempoMedio + " dias");
        } else {
            System.out.println("Não há empréstimos no intervalo.");
        }
    }

}