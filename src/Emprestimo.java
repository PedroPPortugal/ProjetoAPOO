import java.util.ArrayList;
import java.util.Date;

public class Emprestimo {
    public int numero;
    public Date dataEmprestimo;
    public Date dataPrevistaDevolucao;
    public Date dataDevolucao;
    public Utente utente;
    public ArrayList<Livro> livros;

    public Emprestimo(int numero, Date dataEmprestimo, Date dataPrevistaDevolucao, Utente utente, ArrayList<Livro> livros) {
        this.numero = numero;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.utente = utente;
        this.livros = livros;
    }

    public void finalizarEmprestimo(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "numero=" + numero +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataPrevistaDevolucao=" + dataPrevistaDevolucao +
                ", dataDevolucao=" + dataDevolucao +
                ", utente=" + utente.nome +
                ", livros=" + livros +
                '}';
    }
}
