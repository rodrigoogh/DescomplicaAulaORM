package descomplica.desenvolvimentomobile.aula08;

import android.content.Intent;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import descomplica.desenvolvimentomobile.aula08.model.Evento;

public class EventoListAdapter extends ListAdapter<Evento, EventoViewHolder> {

    public EventoListAdapter(@NonNull DiffUtil.ItemCallback<Evento> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return EventoViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull EventoViewHolder holder, int position) {
        Evento evento = getItem(position);
        holder.bind(evento);
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), ViewEventoActivity.class);
            intent.putExtra(FormEventoActivity.EXTRA_EVENTO, evento);
            intent.setAction(Intent.ACTION_VIEW);

            ((MainActivity) holder.itemView.getContext()).startActivityForResult(intent,
                    MainActivity.VER_EVENTO_ACTIVITY_REQUEST_CODE);
        });
    }

    public static class EventoDiff extends DiffUtil.ItemCallback<Evento> {

        @Override
        public boolean areItemsTheSame(@NonNull Evento oldItem, @NonNull Evento newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Evento oldItem, @NonNull Evento newItem) {
            return oldItem.getData().equals(newItem.getData())
                    && oldItem.getLatitude() == newItem.getLatitude()
                    && oldItem.getLongitude() == newItem.getLongitude()
                    && oldItem.getNome().equals(newItem.getNome())
                    && oldItem.getDescricao().equals(newItem.getDescricao());
        }
    }
}
