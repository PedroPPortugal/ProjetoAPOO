import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TesteEmprestimos {

    public static void main(String[] args) {

        /**Criando a lista de empréstimos*/
        List<Emprestimo> emprestimos = new ArrayList<>();

        /**Empréstimos para o teste*/
        emprestimos.add(new Emprestimo("Pedro Portugal", LocalDate.of(2024, 10, 5), LocalDate.of(2024, 10, 15)));
        emprestimos.add(new Emprestimo("Gisele Branco", LocalDate.of(2024, 10, 10), LocalDate.of(2024, 11, 5)));
        emprestimos.add(new Emprestimo("Joel Sa", LocalDate.of(2024, 10, 15), LocalDate.of(2024, 11, 20)));
        emprestimos.add(new Emprestimo("Gabriel Mota", LocalDate.of(2024, 10, 1), LocalDate.of(2024, 12, 10)));

        /**obter a entrada do usuário*/
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o número de dias de atraso: ");
        int diasAtraso = scanner.nextInt();

        EmprestimoGestao.apresentarUtentesComAtraso(emprestimos, diasAtraso);

        scanner.close();
    }
}
