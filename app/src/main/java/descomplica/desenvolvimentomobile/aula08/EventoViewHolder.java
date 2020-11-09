package descomplica.desenvolvimentomobile.aula08;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import descomplica.desenvolvimentomobile.aula08.model.Evento;

public class EventoViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvItemNomeEvento;
    private final TextView tvItemDataEvento;

    public EventoViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        tvItemNomeEvento = itemView.findViewById(R.id.tvItemNomeEvento);
        tvItemDataEvento = itemView.findViewById(R.id.tvItemDataEvento);
    }

    public void bind(Evento evento) {
        tvItemNomeEvento.setText(evento.getNome());
        tvItemDataEvento.setText(evento.getData());
    }

    public static EventoViewHolder create(ViewGroup parent) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.recyclerview_item_lista, parent, false);
        return new EventoViewHolder(view, context);
    }

}
