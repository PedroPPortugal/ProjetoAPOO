import java.io.*;
import java.util.ArrayList;

public class JornalRevista {
    // Atributos
    private String titulo;
    private String editora;
    private String categoria;
    private String issn;
    private String dataPublicacao;

    // ArrayList estático para controlo
    private static ArrayList<JornalRevista> periodicos = new ArrayList<>();

    // Construtor
    public JornalRevista(String titulo, String editora, String categoria, String issn, String dataPublicacao) {
        this.titulo = titulo;
        this.editora = editora;
        this.categoria = categoria;
        this.issn = issn;
        this.dataPublicacao = dataPublicacao;
    }

    // Getters e Setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getEditora() { return editora; }
    public void setEditora(String editora) { this.editora = editora; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getIssn() { return issn; }
    public void setIssn(String issn) { this.issn = issn; }

    public String getDataPublicacao() { return dataPublicacao; }
    public void setDataPublicacao(String dataPublicacao) { this.dataPublicacao = dataPublicacao; }

    // Método simplificado para validar ISSN
    public static boolean validarISSN(String issn) {
        // Se ISSN for nulo ou vazio, retorna falso
        if (issn == null || issn.isEmpty()) {
            return false;
        }

        // Remover hífen do ISSN
        String issnLimpo = issn.replace("-", "");

        // ISSN deve ter 8 caracteres
        if (issnLimpo.length() != 8) {
            return false;
        }

        // Verificar se os primeiros 7 dígitos são números
        for (int i = 0; i < 7; i++) {
            if (!Character.isDigit(issnLimpo.charAt(i))) {
                return false;
            }
        }

        // Verificar se o último caractere é um dígito ou 'X'
        char ultimoChar = issnLimpo.charAt(7);
        return Character.isDigit(ultimoChar) || ultimoChar == 'X';
    }

    // CRUD
    public static boolean adicionar(String titulo, String editora, String categoria, String issn, String dataPublicacao) {
        // Validar ISSN
        if (!validarISSN(issn)) {
            System.out.println("ISSN inválido");
            return false;
        }

        // Verificar se ISSN já existe
        if (procurar(issn) != null) {
            System.out.println("ISSN já registado");
            return false;
        }

        periodicos.add(new JornalRevista(titulo, editora, categoria, issn, dataPublicacao));
        return true;
    }

    public static JornalRevista procurar(String issn) {
        for (JornalRevista periodico : periodicos) {
            if (periodico.issn.equals(issn)) {
                return periodico;
            }
        }
        return null;
    }

    public static boolean atualizar(String issn, String titulo, String editora, String categoria, String dataPublicacao) {
        for (JornalRevista periodico : periodicos) {
            if (periodico.issn.equals(issn)) {
                periodico.titulo = titulo;
                periodico.editora = editora;
                periodico.categoria = categoria;
                periodico.dataPublicacao = dataPublicacao;
                return true;
            }
        }
        return false;
    }

    public static boolean remover(String issn) {
        return periodicos.removeIf(periodico -> periodico.issn.equals(issn));
    }

    // Métodos de ficheiros
    public static void gravarEmFicheiro(String nomeFicheiro) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeFicheiro))) {
            for (JornalRevista periodico : periodicos) {
                writer.write(String.format("%s;%s;%s;%s;%s",
                        periodico.titulo,
                        periodico.editora,
                        periodico.categoria,
                        periodico.issn,
                        periodico.dataPublicacao
                ));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao gravar ficheiro: " + e.getMessage());
        }
    }

    public static void lerDeFicheiro(String nomeFicheiro) {
        periodicos.clear(); // Limpar lista atual

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeFicheiro))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(";");
                if (campos.length >= 5) {
                    JornalRevista periodico = new JornalRevista(
                            campos[0],  // titulo
                            campos[1],  // editora
                            campos[2],  // categoria
                            campos[3],  // issn
                            campos[4]   // dataPublicacao
                    );
                    periodicos.add(periodico);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler ficheiro: " + e.getMessage());
        }
    }

    // Método para obter todos os periódicos
    public static ArrayList<JornalRevista> getPeriodicos() {
        return periodicos;
    }
}