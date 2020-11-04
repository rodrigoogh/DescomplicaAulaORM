package descomplica.desenvolvimentomobile.aula08.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "evento")
public class Evento implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String nome;

    private String data;

    private double latitude;

    private double longitude;

    private String descricao;


    public Evento(long id, String nome, String data, double latitude, double longitude, String descricao) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.latitude = latitude;
        this.longitude = longitude;
        this.descricao = descricao;
    }

    public Evento(String nome, String data, double latitude, double longitude, String descricao) {
        this.nome = nome;
        this.data = data;
        this.latitude = latitude;
        this.longitude = longitude;
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
