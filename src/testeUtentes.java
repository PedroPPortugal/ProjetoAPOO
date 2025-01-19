import java.util.ArrayList;
import java.util.List;

class Utente {
    private String nome;

    public Utente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object obj) {
        /** Compara o nome para determinar se os utentes são iguais*/
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Utente utente = (Utente) obj;
        return nome != null && nome.equals(utente.nome);
    }

    @Override
    public String toString() {
        return nome;
    }
}

class Emprestimo {
    private Utente utente;

    public Emprestimo(Utente utente) {
        this.utente = utente;
    }

    public Utente getUtente() {
        return utente;
    }
}

class Reserva {
    private Utente utente;

    public Reserva(Utente utente) {
        this.utente = utente;
    }

    public Utente getUtente() {
        return utente;
    }
}

public class Biblioteca {
    public static void apresentarListaUtentes(List<Emprestimo> emprestimos, List<Reserva> reservas) {
        /**lista para armazenar os utentes*/

        List<Utente> utentes = new ArrayList<>();

        /** Vemos a lista de empréstimos e adicionamos os utentes à lista, caso não estejam lá*/

        for (Emprestimo e : emprestimos) {
            Utente utente = e.getUtente();
            boolean jaAdicionado = false;

            /** Ver se o utente já está na lista*/
            for (int i = 0; i < utentes.size(); i++) {
                if (utentes.get(i).equals(utente)) {
                    jaAdicionado = true;
                    break;
                }
            }

            /** Se ainda não foi adicionado, adicionamos à lista*/
            if (!jaAdicionado) {
                utentes.add(utente);
            }
        }

        /**Vemos a lista de reservas e adicionamos os utentes à lista, caso não estejam*/
        for (Reserva r : reservas) {
            Utente utente = r.getUtente();
            boolean jaAdicionado = false;

            /**Verificamos se o utente já está na lista*/
            for (int i = 0; i < utentes.size(); i++) {
                if (utentes.get(i).equals(utente)) {
                    jaAdicionado = true;
                    break;
                }
            }

            /**Se o utente ainda não foi adicionado, adicionamos*/
            if (!jaAdicionado) {
                utentes.add(utente);
            }
        }

        System.out.println("Lista de utentes da biblioteca:");
        for (int i = 0; i < utentes.size(); i++) {
            System.out.println(utentes.get(i));
        }
    }

    public static void main(String[] args) {
        /** Criando alguns utentes*/
        Utente utente1 = new Utente("Joel");
        Utente utente2 = new Utente("Gabriel");
        Utente utente3 = new Utente("Gisele");
        Utente utente4 = new Utente("Pedro");

        /**Criando empréstimos e reservas*/
        List<Emprestimo> emprestimos = new ArrayList<>();
        emprestimos.add(new Emprestimo(utente1));
        emprestimos.add(new Emprestimo(utente2));

        List<Reserva> reservas = new ArrayList<>();
        reservas.add(new Reserva(utente3));
        reservas.add(new Reserva(utente4));
        reservas.add(new Reserva(utente1));


        apresentarListaDeUtentes(emprestimos, reservas);
    }
}
