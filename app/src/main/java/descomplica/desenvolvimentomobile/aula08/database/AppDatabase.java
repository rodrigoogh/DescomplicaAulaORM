package descomplica.desenvolvimentomobile.aula08.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import descomplica.desenvolvimentomobile.aula08.dao.EventoDao;
import descomplica.desenvolvimentomobile.aula08.model.Evento;

@Database(entities = {Evento.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract EventoDao getEventoDao();
    private static volatile AppDatabase INSTANCIA;
    private static final int NUMERO_THREADS = 4;
    public static final ExecutorService appDatabaseExecutor = Executors.newFixedThreadPool(NUMERO_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCIA == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCIA == null) {
                    INSTANCIA = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "database_evento").build();
                }
            }
        }
        return INSTANCIA;
    }
}
