import java.util.ArrayList;
import java.util.Date;

class Utente {
    Date data;
    String nome;
    String genero;
    String nif;
    String contacto;
    public static ArrayList<Reserva> reservas;

    public Utente(String nome, String genero, String nif, String contacto) {
        this.nome = nome;
        this.genero = genero;
        this.nif = nif;
        this.contacto = contacto;
        this.reservas = new ArrayList<>();
        this.data = new Date();
    }


    public String getNome() {return nome;}
    public String getGenero() {return genero;}
    public String getNif() {return nif;}
    public String getContacto() {return contacto;}
    public static ArrayList<Reserva> getReservas() {return reservas;}
    public String getData() {return data.toString();}

    public void adicionarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

        public static boolean retirarReserva(int numeroReserva) {
            return reservas.removeIf(reserva -> reserva.numero == numeroReserva);
        }

    @Override
    public String toString() {
        return "Empréstimo{" + "NIF='" + nif + '\'' + ", Data=" + data + "reservas=" + reservas + '}';

    }
}
