class Biblioteca {
    Livro[] livros;
    JornalRevista[] periodicos;
    Utente[] utentes;
    Emprestimo[] emprestimos;
    Reserva[] reservas;
    int maxLivros = 100;
    int maxPeriodicos = 100;
    int maxUtentes = 100;
    int maxEmprestimos = 100;
    int maxReservas = 100;
    int numLivros = 0;
    int numPeriodicos = 0;
    int numUtentes = 0;
    int numEmprestimos = 0;
    int numReservas = 0;

    public Biblioteca(Livro[] livros, JornalRevista[] periodicos, Utente[] utentes, Emprestimo[] emprestimos, Reserva[] reservas) {
        this.livros = livros;
        this.periodicos = periodicos;
        this.utentes = utentes;
        this.emprestimos = emprestimos;
        this.reservas = reservas;
    }

    public Livro[] getLivros() {
        return livros;
    }

    public void setLivros(Livro[] livros) {
        this.livros = livros;
    }

    public JornalRevista[] getPeriodicos() {
        return periodicos;
    }

    public void setPeriodicos(JornalRevista[] periodicos) {
        this.periodicos = periodicos;
    }

    public Utente[] getUtentes() {
        return utentes;
    }

    public void setUtentes(Utente[] utentes) {
        this.utentes = utentes;
    }

    public Emprestimo[] getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(Emprestimo[] emprestimos) {
        this.emprestimos = emprestimos;
    }

    public Reserva[] getReservas() {
        return reservas;
    }

    public void setReservas(Reserva[] reservas) {
        this.reservas = reservas;
    }
}