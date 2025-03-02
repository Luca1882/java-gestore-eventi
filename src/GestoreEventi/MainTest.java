package GestoreEventi;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.Scanner;
import java.time.LocalDate;

public class MainTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //Chiedere all'utente se vuole creare un evento generico o un concerto
        System.out.println("Vuoi creare un evento o un concerto ?: 1 = Evento, 2 = Concerto");
        int scelta = scan.nextInt();
        scan.nextLine();

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

        //Creazione della scelta utente
        if(scelta == 1){
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
        } else if (scelta == 2) {
            //Chiedere all'utente ora e prezzo del biglietto
            System.out.println("Inserisci l'ora del concerto: ");
            int ora = scan.nextInt();
            System.out.println("Inserisci i minuti del concerto: ");
            int min = scan.nextInt();
            LocalTime oraConcerto = LocalTime.of(ora, min);

            //Chiedere prezzo del biglietto
            System.out.println("Inserisci il prezzo del biglietto: ");
            double prezzo = scan.nextDouble();

            //Creazione del concerto
            Concerto concerto = null;
            try {
                concerto = new Concerto(titolo, dataEvento, oraConcerto, postiTotali, prezzo);
            } catch (Exception e){
                System.out.println("Errore nella creazione: " + e.getMessage());
                return;
            }

            //Stampare
            System.out.println("Dettagli concerto: " + concerto.toString());
        } else {
            System.out.println("Scelta non valida");
        }
    }
}
