public static void main(String[] args) {
        /** Exemplo de dados para teste */
        List<Emprestimo> emprestimos = List.of(new Emprestimo(new Utente("Pedro")));
        List<Reserva> reservas = List.of(new Reserva(new Utente("Joel")));

        /** Chamar o método*/
        apresentarListaDeUtentes(emprestimos, reservas);
        }
