import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TesteEmprestimos {

    public static void main(String[] args) {
        /**empréstimos para o teste*/
        List<Emprestimo> emprestimos = new ArrayList<>();

        /**empréstimos com datas de início e fim*/
        emprestimos.add(new Emprestimo(LocalDate.of(2024, 10, 5), LocalDate.of(2024, 10, 15))); /** Empréstimo 1*/
        emprestimos.add(new Emprestimo(LocalDate.of(2024, 10, 15), LocalDate.of(2024, 10, 25))); /** Empréstimo 2*/
        emprestimos.add(new Emprestimo(LocalDate.of(2024, 12, 20), LocalDate.of(2024, 12, 27))); /** Empréstimo 3*/
        emprestimos.add(new Emprestimo(LocalDate.of(2024, 8, 5), LocalDate.of(2024, 8, 10))); /** Empréstimo 4*/
        emprestimos.add(new Emprestimo(LocalDate.of(2024, 11, 10), LocalDate.of(2024, 11, 15))); /** Empréstimo 5*/

        /** intervalo de datas fornecido pelo usuário*/
        LocalDate dataInicio = LocalDate.of(2024, 1, 10); /** Data de início*/
        LocalDate dataFim = LocalDate.of(2024, 12, 31);   /**Data de fim*/

        /**calcular o tempo médio de empréstimos no intervalo*/
        EmprestimoGestao.apresentarTempoMedioEmprestimos(emprestimos, dataInicio, dataFim);
    }
}
