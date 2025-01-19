public static void apresentarListaUtentes(List<Emprestimo> emprestimos,List<Reserva> reservas)
        /** List<Emprestimo> emprestimos: recebe uma lista de objetos do tipo Emprestimo.
        * List<Reserva> reservas: recebe uma lista de objetos do tipo Reserva */
        {
            Set<Utente> utentes = new HashSet<>()
        /** Set nao permite duplicados, HashSet organiza de forma eficiente para nao haver duplicados */

        emprestimos.forEach(e -> utentes.add(e.getUtente()));
        /** forEach executa a acao para cada elemento da lista
         * e representa o emprestimo atual durante a repeticao
         * e.getUtente(): Chama o método getUtente do objeto Emprestimo, que retorna o utente associado ao mesmo
         * utentes.add(...): Adiciona o utente ao conjunto */

        reservas.forEach(r -> utentes.add(r.getUtente()));
        /** parecida com a linha anterior mas trabalha na lista de reservas
         * r representa a reserva atual durante a repeticao */

        System.out.println("Lista de utentes da biblioteca:");
        utentes.forEach(u -> System.out.println(u));
        /** o println sera chamado para cada elemento do conjunto utente para ser exibido*/
        }