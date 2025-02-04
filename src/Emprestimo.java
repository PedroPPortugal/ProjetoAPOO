import java.io.*;
import java.time.Instant;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Emprestimo {
    private int numero;
    private Utente utente;
    private ArrayList<Livro> livros;
    private String dataInicio;
    private String dataPrevistaDevolucao;
    private String dataEfetivaDevolucao;


    private static ArrayList<Emprestimo> emprestimos = new ArrayList<>();
    private static int proximoNumero = 1;


    public Emprestimo(int numero, Utente utente, ArrayList<Livro> livros, String dataInicio,
                      String dataPrevistaDevolucao, String dataEfetivaDevolucao) {
        this.numero = numero;
        this.utente = utente;
        this.livros = livros;
        this.dataInicio = dataInicio;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.dataEfetivaDevolucao = dataEfetivaDevolucao;
    }

    // Getters e Setters
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public Utente getUtente() { return utente; }
    public void setUtente(Utente utente) { this.utente = utente; }

    public ArrayList<Livro> getLivros() { return livros; }
    public void setLivros(ArrayList<Livro> livros) { this.livros = livros; }

    public String getDataInicio() { return dataInicio; }
    public void setDataInicio(String dataInicio) { this.dataInicio = dataInicio; }

    public String getDataPrevistaDevolucao() { return dataPrevistaDevolucao; }
    public void setDataPrevistaDevolucao(String dataPrevistaDevolucao) { this.dataPrevistaDevolucao = dataPrevistaDevolucao; }

    public String getDataEfetivaDevolucao() { return dataEfetivaDevolucao; }
    public void setDataEfetivaDevolucao(String dataEfetivaDevolucao) { this.dataEfetivaDevolucao = dataEfetivaDevolucao; }

    // Validar data(formato dd-MM-yyyy)
    private static boolean validarData(String data) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate.parse(data, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // Verificar se há sobreposição de empréstimos
    private static boolean verificarSobreposicao(ArrayList<Livro> livros, String dataInicio, String dataPrevistaDevolucao) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate inicio = LocalDate.parse(dataInicio, formatter);
        LocalDate fim = LocalDate.parse(dataPrevistaDevolucao, formatter);

        for (Emprestimo emp : emprestimos) {
            if (emp.getDataEfetivaDevolucao() == null) { // Se o livro ainda não foi devolvido
                LocalDate empInicio = LocalDate.parse(emp.getDataInicio(), formatter);
                LocalDate empFim = LocalDate.parse(emp.getDataPrevistaDevolucao(), formatter);

                // Verificar se há livros em comum
                for (Livro livro : livros) {
                    if (emp.getLivros().contains(livro)) {
                        // Verificar sobreposição de datas
                        if (!(fim.isBefore(empInicio) || inicio.isAfter(empFim))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // CRUD
    public static boolean adicionar(Utente utente, ArrayList<Livro> livros, String dataInicio,
                                    String dataPrevistaDevolucao) {
        // Validar datas
        if (!validarData(dataInicio) || !validarData(dataPrevistaDevolucao)) {
            System.out.println("Data inválida");
            return false;
        }

        // Verificar sobreposição
        if (verificarSobreposicao(livros, dataInicio, dataPrevistaDevolucao)) {
            System.out.println("Existe sobreposição com outro empréstimo");
            return false;
        }

        emprestimos.add(new Emprestimo(proximoNumero++, utente, livros, dataInicio,
                dataPrevistaDevolucao, null));
        return true;
    }

    public static Emprestimo procurar(int numero) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.numero == numero) {
                return emprestimo;
            }
        }
        return null;
    }

    public static boolean atualizar(int numero, Utente utente, ArrayList<Livro> livros,
                                    String dataInicio, String dataPrevistaDevolucao,
                                    String dataEfetivaDevolucao) {
        Emprestimo emp = procurar(numero);
        if (emp != null) {
            emp.utente = utente;
            emp.livros = livros;
            emp.dataInicio = dataInicio;
            emp.dataPrevistaDevolucao = dataPrevistaDevolucao;
            emp.dataEfetivaDevolucao = dataEfetivaDevolucao;
            return true;
        }
        return false;
    }

    public static boolean registarDevolucao(int numero, String dataEfetivaDevolucao) {
        if (!validarData(dataEfetivaDevolucao)) {
            System.out.println("Data inválida");
            return false;
        }

        Emprestimo emp = procurar(numero);
        if (emp != null) {
            emp.dataEfetivaDevolucao = dataEfetivaDevolucao;
            return true;
        }
        return false;
    }

    public static boolean remover(int numero) {
        return emprestimos.removeIf(emp -> emp.numero == numero);
    }

    // Métodos de ficheiros
    public static void gravarEmFicheiro(String nomeFicheiro) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeFicheiro))) {
            for (Emprestimo emp : emprestimos) {
                // Converter ArrayList de livros para string de ISBNs
                StringBuilder livrosStr = new StringBuilder();
                for (Livro livro : emp.livros) {
                    if (livrosStr.length() > 0) livrosStr.append(",");
                    livrosStr.append(livro.getIsbn());
                }

                writer.write(String.format("%d;%s;%s;%s;%s;%s",
                        emp.numero,
                        emp.utente.getNif(),
                        livrosStr.toString(),
                        emp.dataInicio,
                        emp.dataPrevistaDevolucao,
                        emp.dataEfetivaDevolucao != null ? emp.dataEfetivaDevolucao : ""
                ));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao gravar ficheiro: " + e.getMessage());
        }
    }

    public static void lerDeFicheiro(String nomeFicheiro) {
        emprestimos.clear();
        proximoNumero = 1;

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeFicheiro))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(";");
                if (campos.length >= 6) {
                    // Processar livros
                    String[] isbns = campos[2].split(",");
                    ArrayList<Livro> livrosEmp = new ArrayList<>();
                    for (String isbn : isbns) {
                        Livro livro = Livro.procurar(isbn);
                        if (livro != null) livrosEmp.add(livro);
                    }

                    Emprestimo emp = new Emprestimo(
                            Integer.parseInt(campos[0]),      // numero
                            Utente.procurar(campos[1]),      // utente
                            livrosEmp,                       // livros
                            campos[3],                       // dataInicio
                            campos[4],                       // dataPrevistaDevolucao
                            campos[5].isEmpty() ? null : campos[5]  // dataEfetivaDevolucao
                    );
                    emprestimos.add(emp);

                    // Atualizar proximoNumero
                    if (emp.numero >= proximoNumero) {
                        proximoNumero = emp.numero + 1;
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao ler ficheiro: " + e.getMessage());
        }
    }

    // Método para obter todos os empréstimos
    public static ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public Instant getDataEmprestimo() {
    }

    public String getItem() {
    }

    public Temporal getDataDevolucao() {
    }
}