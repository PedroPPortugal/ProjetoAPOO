import java.io.*;
import java.util.ArrayList;

public class Utente {
    // Atributos
    private String nif;
    private String nome;
    private String genero;
    private String contacto;

    // ArrayList estático para controlo
    private static ArrayList<Utente> utentes = new ArrayList<>();

    // Construtor
    public Utente(String nif, String nome, String genero, String contacto) {
        this.nif = nif;
        this.nome = nome;
        this.genero = genero;
        this.contacto = contacto;
    }

    // Getters e Setters
    public String getNif() { return nif; }
    public void setNif(String nif) { this.nif = nif; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getContacto() { return contacto; }
    public void setContacto(String contacto) { this.contacto = contacto; }

    // Validação de NIF (9 dígitos)
    public static boolean validarNIF(String nif) {
        if (nif == null || nif.length() != 9) {
            return false;
        }

        for (char c : nif.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    // CRUD
    public static boolean adicionar(String nif, String nome, String genero, String contacto) {
        // Validar NIF
        if (!validarNIF(nif)) {
            System.out.println("NIF inválido");
            return false;
        }

        // Verificar se NIF já existe
        if (procurar(nif) != null) {
            System.out.println("NIF já registado");
            return false;
        }

        utentes.add(new Utente(nif, nome, genero, contacto));
        return true;
    }

    public static Utente procurar(String nif) {
        for (Utente utente : utentes) {
            if (utente.nif.equals(nif)) {
                return utente;
            }
        }
        return null;
    }

    public static boolean atualizar(String nif, String nome, String genero, String contacto) {
        for (Utente utente : utentes) {
            if (utente.nif.equals(nif)) {
                utente.nome = nome;
                utente.genero = genero;
                utente.contacto = contacto;
                return true;
            }
        }
        return false;
    }

    public static boolean remover(String nif) {
        // Verificar dependências antes de remover
        if (temDependencias(nif)) {
            System.out.println("Não é possível remover: utente tem empréstimos ou reservas ativos");
            return false;
        }

        return utentes.removeIf(utente -> utente.nif.equals(nif));
    }

    // Verificar dependências
    private static boolean temDependencias(String nif) {
        // Verificar empréstimos
        for (Emprestimo emp : Emprestimo.getEmprestimos()) {
            if (emp.getUtente().getNif().equals(nif)) {
                return true;
            }
        }

        // Verificar reservas
        for (Reserva res : Reserva.getReservas()) {
            if (res.getUtente().getNif().equals(nif)) {
                return true;
            }
        }

        return false;
    }

    // Métodos de ficheiros
    public static void gravarEmFicheiro(String nomeFicheiro) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeFicheiro))) {
            for (Utente utente : utentes) {
                writer.write(String.format("%s;%s;%s;%s",
                        utente.nif,
                        utente.nome,
                        utente.genero,
                        utente.contacto
                ));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao gravar ficheiro: " + e.getMessage());
        }
    }

    public static void lerDeFicheiro(String nomeFicheiro) {
        utentes.clear(); // Limpar lista atual

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeFicheiro))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(";");
                if (campos.length >= 4) {
                    Utente utente = new Utente(
                            campos[0],  // nif
                            campos[1],  // nome
                            campos[2],  // genero
                            campos[3]   // contacto
                    );
                    utentes.add(utente);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler ficheiro: " + e.getMessage());
        }
    }

    // Método para obter todos os utentes
    public static ArrayList<Utente> getUtentes() {
        return utentes;
    }
}