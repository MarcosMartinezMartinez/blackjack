package es.ieslavereda.blakyack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyReciclerViewAdapter extends RecyclerView.Adapter<MyReciclerViewAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private List<Carta> cartas;

    public MyReciclerViewAdapter(@NonNull Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        cartas = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyReciclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.simple_element, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyReciclerViewAdapter.MyViewHolder holder, int position) {
        Carta carta = cartas.get(position);
        holder.numero.setText(String.valueOf(carta.getNumero()));
        holder.imageView.setImageResource(carta.getPalo().getIdpalo());
    }

    @Override
    public int getItemCount() {
        return cartas.size();
    }

    public void updateCartas(List<Carta> nuevasCartas) {
        cartas.clear();
        cartas.addAll(nuevasCartas);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView numero;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            numero = itemView.findViewById(R.id.textView);
        }
    }
}
