import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TesteEmprestimosMaisRequisitados {

    public static void main(String[] args) {
        
        /**lista de empréstimos*/
        List<Emprestimo> emprestimos = new ArrayList<>();
        emprestimos.add(new Emprestimo(LocalDate.of(2024, 10, 5), "Livro A"));
        emprestimos.add(new Emprestimo(LocalDate.of(2024, 12, 10), "Livro B"));
        emprestimos.add(new Emprestimo(LocalDate.of(2024, 11, 15), "Livro A"));
        emprestimos.add(new Emprestimo(LocalDate.of(2024, 12, 3), "Livro C"));

        /**lista de reservas*/
        List<Reserva> reservas = new ArrayList<>();
        reservas.add(new Reserva(LocalDate.of(2024, 10, 10), "Livro A"));
        reservas.add(new Reserva(LocalDate.of(2024, 11, 15), "Livro A"));
        reservas.add(new Reserva(LocalDate.of(2024, 12, 20), "Livro B"));

        /**Definindo o intervalo de datas fornecido pelo usuário*/
        LocalDate dataInicio = LocalDate.of(2024, 10, 1);
        LocalDate dataFim = LocalDate.of(2024, 12, 31);


        EmprestimoGestapo.apresentarItemMaisRequisitado(emprestimos, reservas, dataInicio, dataFim);
    }
}
