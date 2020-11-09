package descomplica.desenvolvimentomobile.aula08.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import descomplica.desenvolvimentomobile.aula08.dao.EventoDao;
import descomplica.desenvolvimentomobile.aula08.database.AppDatabase;
import descomplica.desenvolvimentomobile.aula08.model.Evento;

public class EventoRepository {

    private EventoDao eventoDao;
    private LiveData<List<Evento>> eventos;

    public EventoRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getDatabase(application);
        eventoDao = appDatabase.getEventoDao();
        eventos = eventoDao.listar();
    }

    public LiveData<List<Evento>> listar() {
        return eventos;
    }

    public void cadastrar(Evento evento) {
        AppDatabase.appDatabaseExecutor.execute(() -> eventoDao.cadastrar(evento));
    }

    public void alterar(Evento evento) {
        AppDatabase.appDatabaseExecutor.execute(() -> eventoDao.alterar(evento));
    }

    public void excluir(Evento evento) {
        AppDatabase.appDatabaseExecutor.execute(() -> eventoDao.excluir(evento));
    }
}
