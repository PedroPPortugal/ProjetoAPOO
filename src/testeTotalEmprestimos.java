import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TesteEmprestimos {
    public static void main(String[] args) {
        /** empréstimos para o teste*/
        List<Emprestimo> emprestimos = new ArrayList<>();

        /** Criando empréstimos com datas*/
        emprestimos.add(new Emprestimo(LocalDate.of(2024, 1, 5))); /**Empréstimo 1*/
        emprestimos.add(new Emprestimo(LocalDate.of(2024, 1, 15))); /**Empréstimo 2*/
        emprestimos.add(new Emprestimo(LocalDate.of(2024, 2, 20))); /**Empréstimo 3*/
        emprestimos.add(new Emprestimo(LocalDate.of(2024, 3, 5))); /**Empréstimo 4*/
        emprestimos.add(new Emprestimo(LocalDate.of(2024, 4, 10))); /**Empréstimo 5*/

        /** Definindo o intervalo de datas que o usuário vai fornecer*/
        LocalDate dataInicio = LocalDate.of(2024, 1, 10);
        LocalDate dataFim = LocalDate.of(2024, 3, 1);

        /**apresentar o total de empréstimos no intervalo*/
        apresentarTotalEmprestimos(emprestimos, dataInicio, dataFim);
    }

    public static void apresentarTotalEmprestimos(List<Emprestimo> emprestimos, LocalDate dataInicio, LocalDate dataFim) {
        long total = 0;

        for (Emprestimo e : emprestimos) {
            if (!e.getDataEmprestimo().isBefore(dataInicio) && !e.getDataEmprestimo().isAfter(dataFim)) {
                total++;
            }
        }

        System.out.println("Total de empréstimos no intervalo: " + total);
    }
}

/**Classe Emprestimo para o exemplo de teste*/
class Emprestimo {
    private LocalDate dataEmprestimo;

    public Emprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
}
