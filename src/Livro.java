import java.io.*;
import java.util.ArrayList;

public class Livro {
    // Atributos básicos de cada livro
    private String titulo;
    private String editora;
    private String categoria;
    private int anoEdicao;
    private String isbn;
    private String[] autores;

    // ArrayList estático para controlo dos livros
    private static ArrayList<Livro> livros = new ArrayList<>();

    // Construtor
    public Livro(String titulo, String editora, String categoria, int anoEdicao, String isbn, String[] autores) {
        this.titulo = titulo;
        this.editora = editora;
        this.categoria = categoria;
        this.anoEdicao = anoEdicao;
        this.isbn = isbn;
        this.autores = autores;
    }

    // Getters e Setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getEditora() { return editora; }
    public void setEditora(String editora) { this.editora = editora; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public int getAnoEdicao() { return anoEdicao; }
    public void setAnoEdicao(int anoEdicao) { this.anoEdicao = anoEdicao; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String[] getAutores() { return autores; }
    public void setAutores(String[] autores) { this.autores = autores; }

    // Método simplificado para validar ISBN
    public static boolean validarISBN(String isbn) {
        // Se ISBN for nulo ou vazio, retorna falso
        if (isbn == null || isbn.isEmpty()) {
            return false;
        }

        // Remover hífens do ISBN
        String isbnLimpo = isbn.replace("-", "");

        // Verificar se é ISBN-10
        if (isbnLimpo.length() == 10) {
            // Verificar se os primeiros 9 dígitos são números
            for (int i = 0; i < 9; i++) {
                if (!Character.isDigit(isbnLimpo.charAt(i))) {
                    return false;
                }
            }
            // Verificar se o último caractere é um dígito ou 'X'
            char ultimoChar = isbnLimpo.charAt(9);
            return Character.isDigit(ultimoChar) || ultimoChar == 'X';
        }

        // Verificar se é ISBN-13
        if (isbnLimpo.length() == 13) {
            // Verificar se todos os caracteres são dígitos
            for (char c : isbnLimpo.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            return true;
        }

        // Se não for nem ISBN-10 nem ISBN-13, retorna falso
        return false;
    }

    // CRUDs
    public static boolean adicionar(String titulo, String editora, String categoria, int ano, String isbn, String[] autores) {
        // Validar ISBN
        if (!validarISBN(isbn)) {
            System.out.println("ISBN inválido");
            return false;
        }

        // Verificar se ISBN já existe
        if (procurar(isbn) != null) {
            System.out.println("ISBN já registado");
            return false;
        }

        livros.add(new Livro(titulo, editora, categoria, ano, isbn, autores));
        return true;
    }

    public static Livro procurar(String isbn) {
        for (Livro livro : livros) {
            if (livro.isbn.equals(isbn)) {
                return livro;
            }
        }
        return null;
    }

    public static boolean atualizar(String isbn, String titulo, String editora, String categoria, int ano, String[] autores) {
        for (Livro livro : livros) {
            if (livro.isbn.equals(isbn)) {
                livro.titulo = titulo;
                livro.editora = editora;
                livro.categoria = categoria;
                livro.anoEdicao = ano;
                livro.autores = autores;
                return true;
            }
        }
        return false;
    }

    public static boolean remover(String isbn) {
        // Verificar dependências
        if (temDependencias(isbn)) {
            System.out.println("Não é possível remover: livro tem empréstimos ou reservas");
            return false;
        }

        return livros.removeIf(livro -> livro.isbn.equals(isbn));
    }

    // Verificar dependências
    private static boolean temDependencias(String isbn) {
        // Verificar empréstimos
        for (Emprestimo emp : Emprestimo.getEmprestimos()) {
            for (Livro livro : emp.getLivros()) {
                if (livro.getIsbn().equals(isbn)) {
                    return true;
                }
            }
        }

        // Verificar reservas
        for (Reserva res : Reserva.getReservas()) {
            for (Livro livro : res.getLivros()) {
                if (livro.getIsbn().equals(isbn)) {
                    return true;
                }
            }
        }

        return false;
    }

    // Métodos de ficheiros
    public static void gravarEmFicheiro(String nomeFicheiro) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeFicheiro))) {
            for (Livro livro : livros) {
                // Converter array de autores para string
                String autoresStr = String.join(",", livro.autores);

                writer.write(String.format("%s;%s;%s;%d;%s;%s",
                        livro.titulo,
                        livro.editora,
                        livro.categoria,
                        livro.anoEdicao,
                        livro.isbn,
                        autoresStr
                ));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao gravar ficheiro: " + e.getMessage());
        }
    }

    public static void lerDeFicheiro(String nomeFicheiro) {
        livros.clear(); // Limpar lista atual

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeFicheiro))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(";");
                if (campos.length >= 6) {
                    String[] autores = campos[5].split(",");
                    Livro livro = new Livro(
                            campos[0],                    // titulo
                            campos[1],                    // editora
                            campos[2],                    // categoria
                            Integer.parseInt(campos[3]),  // anoEdicao
                            campos[4],                    // isbn
                            autores                       // autores
                    );
                    livros.add(livro);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao ler ficheiro: " + e.getMessage());
        }
    }

    // Método para obter todos os livros
    public static ArrayList<Livro> getLivros() {
        return livros;
    }
}