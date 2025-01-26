import java.time.LocalDate;
import java.util.*;

public class EmprestimoGestao {

    /**apresentar o item mais requisitado durante o intervalo de datas*/
    public static void apresentarItemMaisRequisitado(List<Emprestimo> emprestimos, List<Reserva> reservas, LocalDate dataInicio, LocalDate dataFim) {

        /**Contar a quantidade de vezes que cada item foi requisitado*/
        Map<String, Integer> itemContagem = new HashMap<>();

        /**Contar os itens dos empréstimos no intervalo de datas*/
        for (Emprestimo e : emprestimos) {
            if (!e.getDataEmprestimo().isBefore(dataInicio) && !e.getDataEmprestimo().isAfter(dataFim)) {
                itemContagem.put(e.getItem(), itemContagem.getOrDefault(e.getItem(), 0) + 1);
            }
        }

        /**Contar os itens das reservas no intervalo de datas*/
        for (Reserva r : reservas) {
            if (!r.getDataReserva().isBefore(dataInicio) && !r.getDataReserva().isAfter(dataFim)) {
                itemContagem.put(r.getItem(), itemContagem.getOrDefault(r.getItem(), 0) + 1);
            }
        }

        /** Encontrar o item mais requisitado*/
        String itemMaisRequisitado = null;
        int maxRequisicoes = 0;

        for (Map.Entry<String, Integer> entry : itemContagem.entrySet()) {
            if (entry.getValue() > maxRequisicoes) {
                maxRequisicoes = entry.getValue();
                itemMaisRequisitado = entry.getKey();
            }
        }

        if (itemMaisRequisitado != null) {
            System.out.println("O item mais requisitado no intervalo foi: " + itemMaisRequisitado);
            System.out.println("Total de requisições: " + maxRequisicoes);
        } else {
            System.out.println("Não houve requisições ou reservas no intervalo.");
        }
    }

}