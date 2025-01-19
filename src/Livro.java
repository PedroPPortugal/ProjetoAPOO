import java.util.ArrayList;
class Livro {
    String titulo;
    int anoEdicao;
    String editora;
    String isbn;
    String categoria;
    String[] autores;

    public Livro(String titulo, int anoEdicao, String editora, String isbn, String categoria, String[] autores) {
        this.titulo = titulo;
        this.anoEdicao = anoEdicao;
        this.editora = editora;
        this.isbn = isbn;
        this.categoria = categoria;
        this.autores = autores;
    }
    public String getIsbn() {return isbn;}
    public void setIsbn(String isbn) {this.isbn = isbn;}
}

