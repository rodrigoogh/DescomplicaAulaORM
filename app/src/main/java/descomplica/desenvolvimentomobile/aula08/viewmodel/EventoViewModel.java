package descomplica.desenvolvimentomobile.aula08.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import descomplica.desenvolvimentomobile.aula08.model.Evento;
import descomplica.desenvolvimentomobile.aula08.repository.EventoRepository;

public class EventoViewModel extends AndroidViewModel {

    private EventoRepository eventoRepository;
    private final LiveData<List<Evento>> eventos;

    public EventoViewModel(@NonNull Application application) {
        super(application);
        eventoRepository = new EventoRepository(application);
        eventos = eventoRepository.listar();
    }

    public LiveData<List<Evento>> listar() {
        return eventos;
    }

    public void cadastrar(Evento evento) {
        eventoRepository.cadastrar(evento);
    }

    public void alterar(Evento evento) {
        eventoRepository.alterar(evento);
    }

    public void excluir(Evento evento) {
        eventoRepository.excluir(evento);
    }

}
