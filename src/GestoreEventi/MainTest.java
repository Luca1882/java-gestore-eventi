package GestoreEventi;

import java.time.DateTimeException;
import java.util.Scanner;
import java.time.LocalDate;

public class MainTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //Chiedere all'utente Titolo, Data, Posti totali
        System.out.println("Inserisci il titolo dell'evento: ");
        String titolo = scan.nextLine();
        System.out.println("Inserisci la data dell'evento: ");
        System.out.println("Giorno: ");
        int giorno = scan.nextInt();
        System.out.println("Mese: ");
        int mese = scan.nextInt();
        System.out.println("Anno: ");
        int anno = scan.nextInt();
        LocalDate dataEvento = null;
        try {
            dataEvento = LocalDate.of(anno, mese, giorno);
        } catch (DateTimeException e) {
            System.out.println("La data fornita non è valida");
            return;
        }
        System.out.println("Inserire i posti dell'evento: ");
        int postiTotali = scan.nextInt();

        //Crea Evento
        Evento event = null;
        try {
            event = new Evento(titolo, dataEvento, postiTotali);
        } catch (Exception e) {
            System.out.println("Errore creazione evento: " + e.getMessage());
            return;
        }
        //Chiedere all'utemte il numero di posti da prenotare
        System.out.println("Quanti posti vuoi prenotare ? : ");
        int postiDaPrenotare = scan.nextInt();

        // Effettua la prenotazione
        // - Effettuare controllo data e se non ci sono abbastanza posti disponibili
        if(event.getData().isBefore(LocalDate.now())){
            System.out.println("Non puoi disdire i posti per un evento già passato");
            return;
        }
        if (event.getPostiDisponibili() < postiDaPrenotare) {
            System.out.println("Non ci sono abbastanza posti disponibili");
            return;
        }
        try {
            for (int i = 1; i <= postiDaPrenotare; i++) {
                event.prenota();
            }
        } catch (Exception e) {
            System.out.println("Errore prenotazione: " + e.getMessage());
            return;
        }
        //Stampare posti prenotati e posti disponibili
        System.out.println("I posti prenotati sono: " + postiDaPrenotare + "\nI posti disponibili sono: "
                + event.getPostiDisponibili());

        //Chiedere all'utente quanti posti disdire
        System.out.println("Quanti posti vuoi disdire ? : ");
        int postiDaDisdire = scan.nextInt();

        //Effettuare la disdetta
        // - Effettuare il controllo della data e se ci sono posti da disdire
        if (postiDaDisdire > event.getPostiPrenotati()) {
            System.out.println("Non puoi disdire più posti di quelli prenotati");
            return;
        }
        try {
            for (int i = 0; i < postiDaDisdire; i++) {
                event.disdici();
            }
        } catch (Exception e) {
            System.out.println("Errore disdetta: " + e.getMessage());
        }
        //Stampare posti disdetti e posti ancora disponibili
        System.out.println("I posti disdetti sono: " + postiDaDisdire + "\nI posti disponibili sono: "
                + event.getPostiDisponibili());
    }
}
