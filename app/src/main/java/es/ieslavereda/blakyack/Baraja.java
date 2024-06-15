package es.ieslavereda.blakyack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baraja {

    private List<Carta> cartas;

    public Baraja() {
        cartas = new ArrayList<>();
        inicializar();
    }


    private void inicializar(){

        for (int i = 1; i <14 ; i++) {
            cartas.add(new Carta(i , Carta.Palo.CORAZON));
            cartas.add(new Carta(i , Carta.Palo.DIAMANTE));
            cartas.add(new Carta(i , Carta.Palo.PICA));
            cartas.add(new Carta(i , Carta.Palo.TREBOL));
        }
        Barajar();
    }

    public void Barajar(){
        Collections.shuffle(cartas);
    }


    public Carta getcarta(){
        return cartas.remove(0);
    }









}
