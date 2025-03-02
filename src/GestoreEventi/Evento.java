package GestoreEventi;
import java.time.LocalDate;

    public class Evento{
        private String titolo;
        private LocalDate data;
        private final int postiTotale;
        private int postiPrenotati;

        //Costruttore con metodo per controllare la data e i posti
        public Evento(String titolo, LocalDate data, int postiTotale){
            if(data.isBefore(LocalDate.now())){
                throw new IllegalArgumentException("La data dell'evento è già passata");
            }
            if(postiTotale <= 0){
                throw new IllegalArgumentException("Il numero dei posti deve essere positivo");
            }
            this.titolo = titolo;
            this.data = data;
            this.postiTotale = postiTotale;
            this.postiPrenotati = 0;
        }
        //Getter/Setter
        public String getTitolo() {
            return titolo;
        }

        public void setTitolo(String titolo) {
            this.titolo = titolo;
        }

        public LocalDate getData() {
            return data;
        }

        public void setData(LocalDate data) {
            this.data = data;
        }
        //Getter
        public int getPostiTotale() {
            return postiTotale;
        }

        public int getPostiPrenotati() {
            return postiPrenotati;
        }
        //Metodo per prenotare, più eccezione
        public void prenota() throws Exception {
            if(isEnded()){
                throw new Exception("Non è possibile prenotare su un evento passato");
            }
            if(postiPrenotati >= postiTotale){
                throw new Exception("Non ci sono più posti disponibili, l'evento è al completo");
            }
            postiPrenotati++;
        }
        //Metodo disdetta più eccezione
        public void disdici() throws Exception {
            if(isEnded()){
                throw new Exception("Non puoi disdire il posto per un evento già passato");
            }
            if(postiPrenotati == 0){
                throw new Exception("Non ci sono prenotazioni da disdire");
            }
            postiPrenotati--;
        }
        //toString
        @Override
        public String toString(){
            return getData() + " - " + titolo;
        }
        //Refactoring date
        private boolean isEnded(){
            return data.isBefore(LocalDate.now());
        }
        //Metodo calcolo posti disponibili
        public int getPostiDisponibili(){
            return postiTotale - postiPrenotati;
        }
    }
