import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ItemMaisRequisitado {

    /**
     * Apresentar o item mais requisitado durante o intervalo de datas
     */
    public static void apresentarItemMaisRequisitado(List<Emprestimo> emprestimos, List<Reserva> reservas, LocalDate dataInicio, LocalDate dataFim) {
        List<String> itens = new ArrayList<>();
        List<Integer> contagens = new ArrayList<>();

        /** Contar os itens dos empréstimos no intervalo de datas */
        for (Emprestimo e : emprestimos) {
            if (!e.getDataEmprestimo().isBefore(dataInicio) && !e.getDataEmprestimo().isAfter(dataFim)) {
                String item = e.getItem();
                boolean encontrado = false;

                /**Analisar se o item já está na lista*/
                for (int i = 0; i < itens.size(); i++) {
                    if (itens.get(i).equals(item)) {
                        contagens.set(i, contagens.get(i) + 1);
                        encontrado = true;
                        break;
                    }
                }
                /**Se o item não está na lista, adicioná-lo com contagem inicial 1*/
                if (!encontrado) {
                    itens.add(item);
                    contagens.add(1);
                }
            }
        }

        /** Contar os itens das reservas no intervalo de datas */
        for (Reserva r : reservas) {
            if (!r.getDataReserva().isBefore(dataInicio) && !r.getDataReserva().isAfter(dataFim)) {
                String item = r.getItem();
                boolean encontrado = false;

                /** Analisar se o item já está na lista*/
                for (int i = 0; i < itens.size(); i++) {
                    if (itens.get(i).equals(item)) {
                        contagens.set(i, contagens.get(i) + 1);
                        encontrado = true;
                        break;
                    }
                }

                /**Se o item não está na lista, adicioná-lo com contagem inicial 1*/
                if (!encontrado) {
                    itens.add(item);
                    contagens.add(1);
                }
            }
        }

        /** Encontrar o item mais requisitado */
        String itemMaisRequisitado = null;
        int maxRequisicoes = 0;

        for (int i = 0; i < itens.size(); i++) {
            if (contagens.get(i) > maxRequisicoes) {
                maxRequisicoes = contagens.get(i);
                itemMaisRequisitado = itens.get(i);
            }
        }

        /**Imprimir o resultado*/
        if (itemMaisRequisitado != null) {
            System.out.println("O item mais requisitado no intervalo foi: " + itemMaisRequisitado);
            System.out.println("Total de requisições: " + maxRequisicoes);
        } else {
            System.out.println("Não houve requisições ou reservas no intervalo.");
        }
    }
}