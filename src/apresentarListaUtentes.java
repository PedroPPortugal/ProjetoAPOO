import java.util.ArrayList;
import java.util.List;

public class apresentarListaUtentes {
    public static void apresentarListaDeUtentes(List<Emprestimo> emprestimos, List<Reserva> reservas) {
        List<Utente> utentes = new ArrayList<>();

        for (Emprestimo e : emprestimos) {
            Utente utente = e.getUtente();
            boolean jaAdicionado = false;

            /** Verificamos se o utente já está na lista*/
            for (int i = 0; i < utentes.size(); i++) {
                if (utentes.get(i).equals(utente)) {
                    jaAdicionado = true;
                    break;
                }
            }

            if (!jaAdicionado) {
                utentes.add(utente);
            }
        }

        /** Percorremos a lista de reservas e adicionamos os utentes à lista, caso não estejam lá*/

        for (Reserva r : reservas) {
            Utente utente = r.getUtente();
            boolean jaAdicionado = false;

            for (int i = 0; i < utentes.size(); i++) {
                if (utentes.get(i).equals(utente)) {
                    jaAdicionado = true;
                    break;
                }
            }

            if (!jaAdicionado) {
                utentes.add(utente);
            }
        }

        System.out.println("Lista de utentes da biblioteca:");
        for (int i = 0; i < utentes.size(); i++) {
            System.out.println(utentes.get(i));
        }
    }
}