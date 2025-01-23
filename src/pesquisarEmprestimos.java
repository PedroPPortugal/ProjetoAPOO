import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class pesquisarEmprestimos {

    public static void pesquisarEmprestimos() {
        Scanner scanner = new Scanner(System.in);


        // Solicitar o NIF do usuárioSystem.out.print("Digite o NIF do utente: ");
        String nif = scanner.nextLine();

        // Solicitar a data de início e fim para o intervalo de pesquisa
        System.out.print("Digite a data de início (dd/MM/yyyy): ");
        String[] dataInicioInput = scanner.nextLine().split("/");
        Date dataInicio = new Date(Integer.parseInt(dataInicioInput[2]) - 1900, Integer.parseInt(dataInicioInput[1]) - 1, Integer.parseInt(dataInicioInput[0]));

        System.out.print("Digite a data de fim (dd/MM/yyyy): ");
        String[] dataFimInput = scanner.nextLine().split("/");
        Date dataFim = new Date(Integer.parseInt(dataFimInput[2]) - 1900, Integer.parseInt(dataFimInput[1]) - 1, Integer.parseInt(dataFimInput[0]));

        // Filtrar empréstimos associados ao NIF e dentro do intervalo de datas
        List<Emprestimo> emprestimosDoUtente = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimosDoUtente) {
            if (emprestimo.utente.nif.equals(nif) &&
                    !emprestimo.dataEmprestimo.before(dataInicio) && !emprestimo.dataEmprestimo.after(dataFim)) {
                emprestimosDoUtente.add(emprestimo);
            }
        }

        // Verificar se existem empréstimos e exibir o resultado
        if (emprestimosDoUtente.isEmpty()) {
            System.out.println("O utente não possui empréstimos no intervalo especificado.");
        } else {
            System.out.println("O utente possui os seguintes empréstimos no intervalo de tempo especificado:");
            for (Emprestimo emprestimo : emprestimosDoUtente) {
                System.out.println(emprestimo);
            }
        }

        scanner.close();
    }
}



