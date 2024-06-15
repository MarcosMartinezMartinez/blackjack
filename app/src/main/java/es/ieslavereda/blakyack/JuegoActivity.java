package es.ieslavereda.blakyack;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class JuegoActivity extends AppCompatActivity {

    Baraja baraja;
    List<Carta> mano;
    List<Carta> manoBanca;

    private boolean quiereMas;
    int catidad1;
    int catidad2;

    AlertDialog dialogperder;
    AlertDialog dialogganar;
    private Button sacarCarta;
    private Button plantarse;

    private RecyclerView recyclerViewJugador;
    private RecyclerView recyclerViewBanca;
    private MyReciclerViewAdapter adapterJugador;
    private MyReciclerViewAdapter adapterBanca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sacarCarta = findViewById(R.id.sacarCarta);
        plantarse = findViewById(R.id.plantarse);
        recyclerViewJugador = findViewById(R.id.recyclerView);
        recyclerViewBanca = findViewById(R.id.recyclerView2);

        adapterJugador = new MyReciclerViewAdapter(this);
        adapterBanca = new MyReciclerViewAdapter(this);

        recyclerViewJugador.setAdapter(adapterJugador);
        recyclerViewJugador.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewBanca.setAdapter(adapterBanca);
        recyclerViewBanca.setLayoutManager(new LinearLayoutManager(this));

        dialogos();

        startJuego();

        sacarCarta.setOnClickListener(view -> {
            mano.add(baraja.getcarta());
            catidad1 = sacarPuntos(mano);
            adapterJugador.updateCartas(mano);
            if (catidad1 > 21) {
                dialogperder.show();
            }
        });

        plantarse.setOnClickListener(view -> {
            juegaBanca();
            adapterBanca.updateCartas(manoBanca);
            if (catidad1 < catidad2 && catidad2 <= 21) {
                dialogperder.show();
            } else {
                dialogganar.show();
            }
        });
    }

    public void dialogos() {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(JuegoActivity.this);
        builder2.setMessage("Perdiste ¿quieres jugar otra partida?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startJuego(); // Iniciar un nuevo juego
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish(); // Finalizar la actividad
                    }
                });
        dialogperder = builder2.create();

        AlertDialog.Builder builder3 = new AlertDialog.Builder(JuegoActivity.this);
        builder3.setMessage("Ganaste! ¿quieres jugar otra partida?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startJuego(); // Iniciar un nuevo juego
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish(); // Finalizar la actividad
                    }
                });
        dialogganar = builder3.create();
    }

    public void startJuego() {
        baraja = new Baraja();
        mano = new ArrayList<>();
        manoBanca = new ArrayList<>();
        quiereMas = false;
        catidad1 = 0;
        catidad2 = 0;

        mano.add(baraja.getcarta());
        mano.add(baraja.getcarta());
        catidad1 = sacarPuntos(mano);

        manoBanca.add(baraja.getcarta());
        manoBanca.add(baraja.getcarta());
        catidad2 = sacarPuntos(manoBanca);

        adapterJugador.updateCartas(mano);
        adapterBanca.updateCartas(manoBanca);
    }

    public void juegaBanca() {
        // Lógica para que la banca saque cartas
        do {
            manoBanca.add(baraja.getcarta());
            catidad2 = sacarPuntos(manoBanca);
        } while (catidad1 > catidad2 && catidad2 < 22);
    }

    public int sacarPuntos(List<Carta> cartas) {
        int suma = 0;
        for (Carta carta : cartas) {
            if (carta.getNumero() == 11 || carta.getNumero() == 12 || carta.getNumero() == 13) {
                suma += 10;
            } else if (carta.getNumero() == 1) {
                if (suma > 10) {
                    suma += 1;
                } else {
                    suma += 11;
                }
            } else {
                suma += carta.getNumero();
            }
        }
        return suma;
    }
}
