package descomplica.desenvolvimentomobile.aula08;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import descomplica.desenvolvimentomobile.aula08.model.Evento;
import descomplica.desenvolvimentomobile.aula08.viewmodel.EventoViewModel;

public class MainActivity extends AppCompatActivity {

    public static final int NOVO_EVENTO_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDITAR_EVENTO_ACTIVITY_REQUEST_CODE = 2;
    public static final int EXCLUIR_EVENTO_ACTIVITY_REQUEST_CODE = 3;
    private EventoViewModel eventoViewModel;
    private FloatingActionButton btnCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvListaEventos = findViewById(R.id.rvListaEventos);
        final EventoListAdapter adapter = new EventoListAdapter(new EventoListAdapter.EventoDiff());

        rvListaEventos.setAdapter(adapter);
        rvListaEventos.setLayoutManager(new LinearLayoutManager(this));

        eventoViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getApplication()))
                .get(EventoViewModel.class);
        eventoViewModel.listar().observe(this, eventos -> adapter.submitList(eventos));

        btnCadastro = findViewById(R.id.btnCadastro);
        btnCadastro.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FormularioEventoActivity.class);
            startActivityForResult(intent, NOVO_EVENTO_ACTIVITY_REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NOVO_EVENTO_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Evento evento = (Evento) (data != null ?
                    data.getSerializableExtra(FormularioEventoActivity.EXTRA_EVENTO) : null);
            if (evento != null) {
                eventoViewModel.cadastrar(evento);
                Toast.makeText(getApplicationContext(), R.string.sucesso_cadastro, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), R.string.erro_salvar_evento, Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == EDITAR_EVENTO_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Evento evento = (Evento) (data != null ?
                    data.getSerializableExtra(FormularioEventoActivity.EXTRA_EVENTO) : null);
            if (evento != null) {
                eventoViewModel.alterar(evento);
                Toast.makeText(getApplicationContext(), R.string.sucesso_editar_cadastro, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), R.string.erro_salvar_evento, Toast.LENGTH_LONG).show();
            }
        } else if (resultCode == EXCLUIR_EVENTO_ACTIVITY_REQUEST_CODE) {
            Evento evento = (Evento) (data != null ?
                    data.getSerializableExtra(FormularioEventoActivity.EXTRA_EVENTO) : null);
            if (evento != null) {
                eventoViewModel.excluir(evento);
                Toast.makeText(getApplicationContext(), R.string.sucesso_excluir_cadastro, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), R.string.erro_excluir_evento, Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), R.string.erro_salvar_evento, Toast.LENGTH_LONG).show();
        }
    }
}