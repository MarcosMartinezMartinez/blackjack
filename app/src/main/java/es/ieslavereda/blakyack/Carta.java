package es.ieslavereda.blakyack;

public class Carta {

    private int numero;

    private Palo palo;

    public Carta(int numero, Palo palo) {
        this.numero = numero;
        this.palo = palo;
    }

    public int getNumero() {
        return numero;
    }

    public Palo getPalo() {
        return palo;
    }

    public enum Palo{

        DIAMANTE(R.drawable.diamante),
        CORAZON(R.drawable.corazon),
        TREBOL(R.drawable.trebol),
        PICA(R.drawable.pica);

        private int idpalo;

        Palo(int idpalo) {
            this.idpalo = idpalo;
        }

        public int getIdpalo() {
            return idpalo;
        }
    }

}
