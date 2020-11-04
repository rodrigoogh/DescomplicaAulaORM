package descomplica.desenvolvimentomobile.aula08.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import descomplica.desenvolvimentomobile.aula08.model.Evento;

public interface EventoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void cadastrar(Evento evento);

    @Delete
    void excluir(Evento evento);

    @Update
    void alterar(Evento evento);

    @Query("SELECT * FROM evento ORDER BY data ASC")
    LiveData<List<Evento>> listar();
}
