package descomplica.desenvolvimentomobile.aula08.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "evento")
public class Evento implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nome;

    private String data;

    private String endereco;

    private double latitude;

    private double longitude;

    private String descricao;

    public Evento(int id, String nome, String data, String endereco, double latitude, double longitude, String descricao) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.endereco = endereco;
        this.latitude = latitude;
        this.longitude = longitude;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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
