package GestoreEventi;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;

public class Concerto extends Evento{
    private LocalTime time;
    private double prezzo;

    //Costruttore e richiamo della classe Evento(super)
    public Concerto(String titolo, LocalDate data, LocalTime time, int postiTotale, double prezzo ){
        super(titolo, data, postiTotale);
        this.time = time;
        this.prezzo = prezzo;
    }

    //Getter/Setter
    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
    //Metodo per la formattazione della data
    public String getDataOraFormattata() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " " + time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
    //Metodo per la formattazione del prezzo
    public String getPrezzoFormattato() {
        DecimalFormat df = new DecimalFormat("#,##0.00€");
        return df.format(prezzo);
    }
    //toString con dateTime e prezzo formattato
    @Override
    public String toString() {
        return getDataOraFormattata() + " - " + getTitolo() + " - " + getPrezzoFormattato();
    }
}
