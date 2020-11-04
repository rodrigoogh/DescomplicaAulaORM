package descomplica.desenvolvimentomobile.aula08;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import descomplica.desenvolvimentomobile.aula08.model.Evento;

public class EventoViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvItemLista;

    public EventoViewHolder(@NonNull View itemView) {
        super(itemView);
        tvItemLista = itemView.findViewById(R.id.tvItemLista);
    }

    public void bind(Evento evento) {
        tvItemLista.setText(evento.getNome());
    }

    public static EventoViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_lista, parent, false);
        return new EventoViewHolder(view);
    }
}
