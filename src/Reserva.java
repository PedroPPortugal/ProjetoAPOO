import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Reserva {
    // Atributos
    private int numero;
    private Utente utente;
    private ArrayList<Livro> livros;
    private String dataRegisto;
    private String dataInicio;
    private String dataFim;

    // ArrayList estático para controlo
    private static ArrayList<Reserva> reservas = new ArrayList<>();
    private static int proximoNumero = 1;

    // Construtor
    public Reserva(int numero, Utente utente, ArrayList<Livro> livros,
                   String dataRegisto, String dataInicio, String dataFim) {
        this.numero = numero;
        this.utente = utente;
        this.livros = livros;
        this.dataRegisto = dataRegisto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    // Getters e Setters
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public Utente getUtente() { return utente; }
    public void setUtente(Utente utente) { this.utente = utente; }

    public ArrayList<Livro> getLivros() { return livros; }
    public void setLivros(ArrayList<Livro> livros) { this.livros = livros; }

    public String getDataRegisto() { return dataRegisto; }
    public void setDataRegisto(String dataRegisto) { this.dataRegisto = dataRegisto; }

    public String getDataInicio() { return dataInicio; }
    public void setDataInicio(String dataInicio) { this.dataInicio = dataInicio; }

    public String getDataFim() { return dataFim; }
    public void setDataFim(String dataFim) { this.dataFim = dataFim; }

    // Validação de data (formato dd-MM-yyyy)
    private static boolean validarData(String data) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate.parse(data, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // Verificar se há sobreposição de reservas
    private static boolean verificarSobreposicao(ArrayList<Livro> livros, String dataInicio, String dataFim) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate inicio = LocalDate.parse(dataInicio, formatter);
        LocalDate fim = LocalDate.parse(dataFim, formatter);

        // Verificar sobreposição com outras reservas
        for (Reserva res : reservas) {
            LocalDate resInicio = LocalDate.parse(res.getDataInicio(), formatter);
            LocalDate resFim = LocalDate.parse(res.getDataFim(), formatter);

            // Verificar se há livros em comum
            for (Livro livro : livros) {
                if (res.getLivros().contains(livro)) {
                    // Verificar sobreposição de datas
                    if (!(fim.isBefore(resInicio) || inicio.isAfter(resFim))) {
                        return true;
                    }
                }
            }
        }

        // Verificar sobreposição com empréstimos
        for (Emprestimo emp : Emprestimo.getEmprestimos()) {
            if (emp.getDataEfetivaDevolucao() == null) { // Se ainda não foi devolvido
                LocalDate empInicio = LocalDate.parse(emp.getDataInicio(), formatter);
                LocalDate empFim = LocalDate.parse(emp.getDataPrevistaDevolucao(), formatter);

                for (Livro livro : livros) {
                    if (emp.getLivros().contains(livro)) {
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
    public static boolean adicionar(Utente utente, ArrayList<Livro> livros,
                                    String dataRegisto, String dataInicio, String dataFim) {
        // Validar datas
        if (!validarData(dataRegisto) || !validarData(dataInicio) || !validarData(dataFim)) {
            System.out.println("Data inválida");
            return false;
        }

        // Verificar sobreposição
        if (verificarSobreposicao(livros, dataInicio, dataFim)) {
            System.out.println("Existe sobreposição com outra reserva ou empréstimo");
            return false;
        }

        reservas.add(new Reserva(proximoNumero++, utente, livros, dataRegisto, dataInicio, dataFim));
        return true;
    }

    public static Reserva procurar(int numero) {
        for (Reserva reserva : reservas) {
            if (reserva.numero == numero) {
                return reserva;
            }
        }
        return null;
    }

    public static boolean atualizar(int numero, Utente utente, ArrayList<Livro> livros,
                                    String dataRegisto, String dataInicio, String dataFim) {
        // Validar datas
        if (!validarData(dataRegisto) || !validarData(dataInicio) || !validarData(dataFim)) {
            System.out.println("Data inválida");
            return false;
        }

        Reserva res = procurar(numero);
        if (res != null) {
            res.utente = utente;
            res.livros = livros;
            res.dataRegisto = dataRegisto;
            res.dataInicio = dataInicio;
            res.dataFim = dataFim;
            return true;
        }
        return false;
    }

    public static boolean remover(int numero) {
        return reservas.removeIf(res -> res.numero == numero);
    }

    // Método para transformar reserva em empréstimo
    public static boolean transformarEmEmprestimo(int numeroReserva, String dataPrevistaDevolucao) {
        Reserva res = procurar(numeroReserva);
        if (res == null) {
            System.out.println("Reserva não encontrada");
            return false;
        }

        // Criar novo empréstimo
        boolean sucesso = Emprestimo.adicionar(
                res.utente,
                res.livros,
                res.dataInicio,
                dataPrevistaDevolucao
        );

        if (sucesso) {
            // Remover a reserva após criar o empréstimo
            remover(numeroReserva);
            return true;
        }
        return false;
    }

    // Métodos de ficheiros
    public static void gravarEmFicheiro(String nomeFicheiro) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeFicheiro))) {
            for (Reserva res : reservas) {
                // Converter ArrayList de livros para string de ISBNs
                StringBuilder livrosStr = new StringBuilder();
                for (Livro livro : res.livros) {
                    if (livrosStr.length() > 0) livrosStr.append(",");
                    livrosStr.append(livro.getIsbn());
                }

                writer.write(String.format("%d;%s;%s;%s;%s;%s",
                        res.numero,
                        res.utente.getNif(),
                        livrosStr.toString(),
                        res.dataRegisto,
                        res.dataInicio,
                        res.dataFim
                ));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao gravar ficheiro: " + e.getMessage());
        }
    }

    public static void lerDeFicheiro(String nomeFicheiro) {
        reservas.clear();
        proximoNumero = 1;

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeFicheiro))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(";");
                if (campos.length >= 6) {
                    // Processar livros
                    String[] isbns = campos[2].split(",");
                    ArrayList<Livro> livrosRes = new ArrayList<>();
                    for (String isbn : isbns) {
                        Livro livro = Livro.procurar(isbn);
                        if (livro != null) livrosRes.add(livro);
                    }

                    Reserva res = new Reserva(
                            Integer.parseInt(campos[0]),  // numero
                            Utente.procurar(campos[1]),  // utente
                            livrosRes,                   // livros
                            campos[3],                   // dataRegisto
                            campos[4],                   // dataInicio
                            campos[5]                    // dataFim
                    );
                    reservas.add(res);

                    // Atualizar proximoNumero
                    if (res.numero >= proximoNumero) {
                        proximoNumero = res.numero + 1;
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao ler ficheiro: " + e.getMessage());
        }
    }

    // Método para obter todas as reservas
    public static ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public Instant getDataReserva() {
        return null;
    }

    public String getItem() {
    }
}