import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BibliotecaApp {
    private static ArrayList<Livro> livros = new ArrayList<>();
    private static ArrayList<JornalRevista> jornaisRevistas = new ArrayList<>();
    private static ArrayList<Utente> utentes = new ArrayList<>();
    private static ArrayList<Emprestimo> emprestimos = new ArrayList<>();
    private static ArrayList<Reserva> reservas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int opcao;
        do {
            System.out.println("Menu Principal:");
            System.out.println("1. Gerir Livros");
            System.out.println("2. Gerir Jornais/Revistas");
            System.out.println("3. Gerir Utentes");
            System.out.println("4. Gerir Empréstimos");
            System.out.println("5. Gerir Reservas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    menuLivros();
                    break;
                case 2:
                    menuJornaisRevistas();
                    break;
                case 3:
                    menuUtentes();
                    break;
                case 4:
                    menuEmprestimos();
                    break;
                case 5:
                    menuReservas();
                    break;
                case 0:
                    System.out.println("A sair do programa");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 0);
    }

    private static void menuLivros() {
        int opcao;
        do {
            System.out.println("\nGerir Livros:");
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Listar Livros");
            System.out.println("3. Atualizar Livro");
            System.out.println("4. Remover Livro");
            System.out.println("5-Pesquisar Livro");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    adicionarLivro();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    if (livros.isEmpty()) {
                        System.out.println("Nenhum livro registado. Operação cancelada.");
                    } else {
                        atualizarLivro();
                    }
                    break;
                case 4:
                    if (livros.isEmpty()) {
                        System.out.println("Nenhum livro registado. Operação cancelada.");
                    } else {
                        removerLivro();
                    }
                    break;

                case 5:
                    pesquisarLivro();
                    break;

                    case 0:
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 0);
    }


    private static void menuJornaisRevistas() {
        int opcao;
        do {
            System.out.println("\nGerir Jornais/Revistas:");
            System.out.println("1. Adicionar Jornal/Revista");
            System.out.println("2. Listar Jornais/Revistas");
            System.out.println("3. Atualizar Jornal/Revista");
            System.out.println("4. Remover Jornal/Revista");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    adicionarJornalRevista();
                    break;
                case 2:
                    listarJornaisRevistas();
                    break;
                case 3:
                    if (jornaisRevistas.isEmpty()) {
                        System.out.println("Nenhum jornal ou revista registado. Operação cancelada.");
                    } else {
                        atualizarJornalRevista();
                    }
                    break;
                case 4:
                    if (jornaisRevistas.isEmpty()) {
                        System.out.println("Nenhum jornal ou revista registado. Operação cancelada.");
                    } else {
                        removerJornalRevista();
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 0);
    }


    private static void adicionarJornalRevista() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Editora: ");
        String editora = scanner.nextLine();
        System.out.print("ISSN: ");
        String issn = scanner.nextLine();
        System.out.print("Data de Publicação (yyyy-MM-dd): ");
        String dataPublicacao = scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        jornaisRevistas.add(new JornalRevista(titulo, editora, issn, dataPublicacao, categoria));
        System.out.println("Jornal/Revista adicionado com sucesso");
    }

    private static void listarJornaisRevistas() {
        if (jornaisRevistas.isEmpty()) {
            System.out.println("Nenhum jornal ou revista registado");
        } else {
            for (int i = 0; i < jornaisRevistas.size(); i++) {
                JornalRevista jr = jornaisRevistas.get(i);
                System.out.println((i + 1) + ". " + jr.titulo + " (ISSN: " + jr.issn + ")");
            }
        }
    }

    private static void atualizarJornalRevista() {
        listarJornaisRevistas();
        System.out.print("Escolha o número do jornal/revista a atualizar: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index >= 0 && index < jornaisRevistas.size()) {
            JornalRevista jr = jornaisRevistas.get(index);
            System.out.print("Novo Título (atual: " + jr.titulo + "): ");
            jr.titulo = scanner.nextLine();
            System.out.print("Nova Editora (atual: " + jr.editora + "): ");
            jr.editora = scanner.nextLine();
            System.out.print("Novo ISSN (atual: " + jr.issn + "): ");
            jr.issn = scanner.nextLine();
            System.out.print("Nova Data de Publicação (atual: " + jr.dataPublicacao + "): ");
            jr.dataPublicacao = scanner.nextLine();
            System.out.print("Nova Categoria (atual: " + jr.categoria + "): ");
            jr.categoria = scanner.nextLine();
            System.out.println("Jornal/Revista atualizado com sucesso");
        } else {
            System.out.println("Número inválido");
        }
    }

    private static void removerJornalRevista() {
        listarJornaisRevistas();
        System.out.print("Escolha o número do jornal/revista a remover: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index >= 0 && index < jornaisRevistas.size()) {
            jornaisRevistas.remove(index);
            System.out.println("Jornal/Revista removido com sucesso");
        } else {
            System.out.println("Número inválido");
        }
    }

    private static void adicionarLivro() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Ano de edição: ");
        int anoEdicao = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Editora: ");
        String editora = scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        System.out.print("Número de autores: ");
        int numAutores = scanner.nextInt();
        scanner.nextLine();
        String[] autores = new String[numAutores];
        for (int i = 0; i < numAutores; i++) {
            System.out.print("Autor " + (i + 1) + ": ");
            autores[i] = scanner.nextLine();
        }

        livros.add(new Livro(titulo, anoEdicao, editora, isbn, categoria, autores));
        System.out.println("Livro adicionado com sucesso");
    }

    private static void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registado");
        } else {
            for (int i = 0; i < livros.size(); i++) {
                Livro livro = livros.get(i);
                System.out.println((i + 1) + ". " + livro.titulo + " (" + livro.isbn + ")");
            }
        }
    }
        private static void pesquisarLivro () {
            System.out.println("Digite o isbn do numero que pretende pesquisar:");
            String isbn = scanner.nextLine();
            if (isbn.length() != 10) {
                System.out.println("O isbn do livro tem que conter 10 digitos");
            } else if(isbn.length() == 10) {
                Livro livro = livros.get(Integer.parseInt(isbn));
                System.out.println("O livro com esse codigo isbn é:"+livro);

            }
        }

    private static void atualizarLivro() {
        listarLivros();
        System.out.print("Escolha o número do livro a atualizar: ");

        if (scanner.hasNextInt()) { // Verifica se a entrada é um número inteiro
            int index = scanner.nextInt() - 1;
            scanner.nextLine(); // Limpa o buffer

            if (index >= 0 && index < livros.size()) { // Verifica se o índice é válido
                Livro livro = livros.get(index);
                System.out.print("Novo título (atual: " + livro.titulo + "): ");
                livro.titulo = scanner.nextLine();
                System.out.print("Novo ano de edição (atual: " + livro.anoEdicao + "): ");

                if (scanner.hasNextInt()) { // Valida o ano de edição
                    livro.anoEdicao = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer
                } else {
                    System.out.println("Entrada inválida para o ano de edição. Operação cancelada.");
                    scanner.nextLine(); // Limpa a entrada inválida
                    return;
                }

                System.out.print("Nova editora (atual: " + livro.editora + "): ");
                livro.editora = scanner.nextLine();
                System.out.print("Novo ISBN (atual: " + livro.isbn + "): ");
                livro.isbn = scanner.nextLine();
                System.out.print("Nova categoria (atual: " + livro.categoria + "): ");
                livro.categoria = scanner.nextLine();
                System.out.println("Livro atualizado com sucesso");
            } else {
                System.out.println("Número inválido");
            }
        } else {
            System.out.println("Entrada inválida. Por favor, insira um número.");
            scanner.nextLine(); // Limpa a entrada inválida
        }
    }


    private static void removerLivro() {
        listarLivros();
        System.out.print("Escolha o número do livro a remover: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index >= 0 && index < livros.size()) {
            livros.remove(index);
            System.out.println("Livro removido com sucesso");
        } else {
            System.out.println("Número inválido");
        }
    }

    // Métodos para gerir livros, jornais/revistas já implementados
    // ---------------------
    // Métodos para gerir utentes
    private static void menuUtentes() {
        int opcao;
        do {
            System.out.println("\nGerir Utentes:");
            System.out.println("1. Adicionar Utente");
            System.out.println("2. Listar Utentes");
            System.out.println("3. Atualizar Utente");
            System.out.println("4. Remover Utente");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarUtente();
                    break;
                case 2:
                    listarUtentes();
                    break;
                case 3:
                    if (utentes.isEmpty()) {
                        System.out.println("Nenhum utente registado. Operação cancelada.");
                    } else {
                        atualizarUtente();
                    }
                    break;
                case 4:
                    if (utentes.isEmpty()) {
                        System.out.println("Nenhum utente registado. Operação cancelada.");
                    } else {
                        removerUtente();
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 0);
    }


    private static void adicionarUtente() {
        // Solicita os dados do utente ao utilizador
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Género: ");
        String genero = scanner.nextLine();
        System.out.print("NIF: ");
        String nif = scanner.nextLine();
        System.out.print("Contacto: ");
        String contacto = scanner.nextLine();

        // Adiciona o novo utente à lista de utentes
        utentes.add(new Utente(nome, genero, nif, contacto));
        System.out.println("Utente adicionado com sucesso");
    }

    private static Utente selecionarUtente() {
        if (utentes.isEmpty()) {
            System.out.println("Nenhum utente registado. Operação cancelada.");
            return null;
        }

        listarUtentes();
        System.out.print("Escolha o número do utente: ");
        int utenteIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (utenteIndex >= 0 && utenteIndex < utentes.size()) {
            return utentes.get(utenteIndex);
        } else {
            System.out.println("Utente não encontrado. Operação cancelada.");
            return null;
        }
    }

    private static void listarUtentes() {
        // Verifica se existem utentes na lista
        if (utentes.isEmpty()) {
            System.out.println("Nenhum utente registado");
        } else {
            // Itera sobre os utentes e exibe as suas informações
            for (int i = 0; i < utentes.size(); i++) {
                Utente utente = utentes.get(i);
                System.out.println((i + 1) + ". " + utente.nome + " (NIF: " + utente.nif + ")");
            }
        }
    }

    private static void atualizarUtente() {
        listarUtentes();
        System.out.print("Escolha o número do utente a atualizar: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index >= 0 && index < utentes.size()) {
            Utente utente = utentes.get(index);
            // Solicita os novos dados ao utilizador
            System.out.print("Novo Nome (atual: " + utente.nome + "): ");
            utente.nome = scanner.nextLine();
            System.out.print("Novo Género (atual: " + utente.genero + "): ");
            utente.genero = scanner.nextLine();
            System.out.print("Novo NIF (atual: " + utente.nif + "): ");
            utente.nif = scanner.nextLine();
            System.out.print("Novo Contacto (atual: " + utente.contacto + "): ");
            utente.contacto = scanner.nextLine();
            System.out.println("Utente atualizado com sucesso");
        } else {
            System.out.println("Número inválido");
        }
    }

    private static void removerUtente() {
        listarUtentes();
        System.out.print("Escolha o número do utente a remover: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index >= 0 && index < utentes.size()) {
            // Remove o utente selecionado
            utentes.remove(index);
            System.out.println("Utente removido com sucesso");
        } else {
            System.out.println("Número inválido");
        }
    }

    // Métodos para gerir empréstimos
    private static void menuEmprestimos() {
        int opcao;
        do {
            System.out.println("\nGerir Empréstimos:");
            System.out.println("1. Registar Empréstimo");
            System.out.println("2. Listar Empréstimos");
            System.out.println("3. Finalizar Empréstimo");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    if (utentes.isEmpty() || livros.isEmpty()) {
                        System.out.println("Não há utentes ou livros registados. Operação cancelada.");
                    } else {
                        registarEmprestimo();
                    }
                    break;
                case 2:
                    listarEmprestimos();
                    break;
                case 3:
                    if (emprestimos.isEmpty()) {
                        System.out.println("Nenhum empréstimo registado. Operação cancelada.");
                    } else {
                        finalizarEmprestimo();
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 0);
    }

    private static void registarEmprestimo() {
        if (utentes.isEmpty()) {
            System.out.println("Nenhum utente registado. Operação cancelada.");
            return;
        }

        listarUtentes();
        System.out.print("Escolha o número do utente: ");
        if (scanner.hasNextInt()) {
            int utenteIndex = scanner.nextInt() - 1;
            scanner.nextLine();

            if (utenteIndex >= 0 && utenteIndex < utentes.size()) {
                Utente utente = utentes.get(utenteIndex);
                ArrayList<Livro> livrosEmprestimo = new ArrayList<>();
                

                while (true) {
                    listarLivros();
                    if (livros.isEmpty()) {
                        System.out.println("Nenhum livro disponível para empréstimo.");
                        return;
                    }

                    System.out.print("Escolha o número do livro para adicionar ao empréstimo (0 para terminar): ");
                    if (scanner.hasNextInt()) {
                        int livroIndex = scanner.nextInt() - 1;
                        scanner.nextLine();

                        if (livroIndex == -1) { // Condição de término
                            break;
                        } else if (livroIndex >= 0 && livroIndex < livros.size()) {
                            Livro livro = livros.get(livroIndex);
                            if (!livrosEmprestimo.contains(livro)) {
                                livrosEmprestimo.add(livro);
                                System.out.println("Livro adicionado ao empréstimo.");
                            } else {
                                System.out.println("Este livro já foi adicionado.");
                            }
                        } else {
                            System.out.println("Número inválido. Tente novamente.");
                        }
                    } else {
                        System.out.println("Entrada inválida. Por favor, insira um número.");
                        scanner.nextLine(); // Limpar a entrada inválida
                    }
                }

                if (!livrosEmprestimo.isEmpty()) {
                    System.out.print("Data prevista de devolução (yyyy-MM-dd): ");
                    String dataPrevista = scanner.nextLine();
                    emprestimos.add(new Emprestimo(emprestimos.size() + 1, new Date(), new Date(dataPrevista), utente, livrosEmprestimo));
                    System.out.println("Empréstimo registado com sucesso");
                } else {
                    System.out.println("Nenhum livro foi adicionado ao empréstimo. Operação cancelada.");
                }
            } else {
                System.out.println("Número de utente inválido.");
            }
        } else {
            System.out.println("Entrada inválida. Operação cancelada.");
            scanner.nextLine(); // Limpar a entrada inválida
        }
    }


    private static void listarEmprestimos() {
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo registado");
        } else {
            for (Emprestimo e : emprestimos) {
                System.out.println("Empréstimo " + e.numero + ": Utente: " + e.utente.nome);
            }
        }
    }

    private static void finalizarEmprestimo() {
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo registado.");
            return;
        }
        listarEmprestimos();
        System.out.print("Escolha o número do empréstimo a finalizar: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index >= 0 && index < emprestimos.size()) {
            Emprestimo e = emprestimos.get(index);
            e.finalizarEmprestimo(new Date());
            emprestimos.remove(index);
            System.out.println("Empréstimo finalizado com sucesso");
        } else {
            System.out.println("Número inválido");
        }
    }


    // Métodos para reservas
    private static void menuReservas() {
        int opcao;
        do {
            System.out.println("\nGerir Reservas:");
            System.out.println("1. Registar Reserva");
            System.out.println("2. Listar Reservas");
            System.out.println("3. Transformar Reserva em Empréstimo");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    registarReserva();
                    break;
                case 2:
                    listarReservas();
                    break;
                case 3:
                    transformarReserva();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 0);
    }

    private static void registarReserva() {
        if (utentes.isEmpty()) {
            System.out.println("Nenhum utente registado. Operação cancelada.");
            return;
        }

        listarUtentes();
        System.out.print("Escolha o número do utente: ");
        if (scanner.hasNextInt()) {
            int utenteIndex = scanner.nextInt() - 1;
            scanner.nextLine();

            if (utenteIndex >= 0 && utenteIndex < utentes.size()) {
                Utente utente = utentes.get(utenteIndex);
                ArrayList<Livro> livrosReserva = new ArrayList<>();

                while (true) {
                    listarLivros();
                    if (livros.isEmpty()) {
                        System.out.println("Nenhum livro disponível para reserva.");
                        return;
                    }

                    System.out.print("Escolha o número do livro para adicionar à reserva (0 para terminar): ");
                    if (scanner.hasNextInt()) {
                        int livroIndex = scanner.nextInt() - 1;
                        scanner.nextLine();

                        if (livroIndex == -1) { // Condição de término
                            break;
                        } else if (livroIndex >= 0 && livroIndex < livros.size()) {
                            livrosReserva.add(livros.get(livroIndex));
                            System.out.println("Livro adicionado à reserva.");
                        } else {
                            System.out.println("Número inválido. Tente novamente.");
                        }
                    } else {
                        System.out.println("Entrada inválida. Por favor, insira um número.");
                        scanner.nextLine(); // Limpar entrada inválida
                    }
                }

                if (!livrosReserva.isEmpty()) {
                    try {
                        System.out.print("Data de início da reserva (yyyy-MM-dd): ");
                        LocalDate dataInicio = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);

                        System.out.print("Data de fim da reserva (yyyy-MM-dd): ");
                        LocalDate dataFim = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);

                        if (dataFim.isBefore(dataInicio)) {
                            System.out.println("A data de fim não pode ser anterior à data de início. Operação cancelada.");
                            return;
                        }

                        reservas.add(new Reserva(reservas.size() + 1, LocalDate.now(), dataInicio, dataFim, utente, livrosReserva));
                        System.out.println("Reserva registada com sucesso");
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato de data inválido. Por favor, use o formato yyyy-MM-dd.");
                    }
                } else {
                    System.out.println("Nenhum livro foi adicionado à reserva. Operação cancelada.");
                }
            } else {
                System.out.println("Número de utente inválido.");
            }
        } else {
            System.out.println("Entrada inválida. Operação cancelada.");
            scanner.nextLine(); // Limpar entrada inválida
        }
    }

    private static void listarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva registada");
            return;
        }

        for (Reserva r : reservas) {
            System.out.println("Reserva " + r.numero + ": Utente: " + r.utente.nome);
        }
    }

    private static void transformarReserva() {
        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva registada. Operação cancelada.");
            return;
        }

        listarReservas();
        System.out.print("Escolha o número da reserva a transformar em empréstimo: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index >= 0 && index < reservas.size()) {
            Reserva r = reservas.get(index);
            Emprestimo e = r.transformarEmEmprestimo(new Date(), new Date());
            emprestimos.add(e);
            reservas.remove(index);
            System.out.println("Reserva transformada em empréstimo com sucesso");
        } else {
            System.out.println("Número inválido");
        }
    }

}
