import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Reserva {
    public int numero;
    public LocalDate dataRegistro;
    public LocalDate dataInicio;
    public LocalDate dataFim;
    public Utente utente;
    public ArrayList<Livro> livros;

    public Reserva(int numero, LocalDate dataRegistro, LocalDate dataInicio, LocalDate dataFim, Utente utente, ArrayList<Livro> livros) {
        this.numero = numero;
        this.dataRegistro = dataRegistro;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.utente = utente;
        this.livros = livros;
    }


    public Emprestimo transformarEmEmprestimo(Date dataEmprestimo, Date dataPrevistaDevolucao) {
        return new Emprestimo(numero, dataEmprestimo, dataPrevistaDevolucao, utente, livros);
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "numero=" + numero +
                ", dataRegistro=" + dataRegistro +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", utente=" + utente.nome +
                ", livros=" + livros +
                '}';
    }
}
