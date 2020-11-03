package descomplica.desenvolvimentomobile.aula08.models;

import java.util.Date;

public class Evento {
    private long id;
    private String nome;
    private Date data;
    private float latitude;
    private float longitude;
    private String descricao;

    public Evento(long id, String nome, Date data, float latitude, float longitude, String descricao) {
        this.id = id;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
