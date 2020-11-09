package descomplica.desenvolvimentomobile.aula08;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import descomplica.desenvolvimentomobile.aula08.model.Evento;
import descomplica.desenvolvimentomobile.aula08.viewmodel.EventoViewModel;

import static descomplica.desenvolvimentomobile.aula08.MainActivity.EXCLUIR_EVENTO_ACTIVITY_REQUEST_CODE;

public class ViewEventoActivity extends AppCompatActivity {


    public static final int EDITAR_EVENTO_ACTIVITY_REQUEST_CODE = 2;

    private EventoViewModel eventoViewModel;
    private Evento evento;
    TextView tvNomeEvento, tvDescricaoEvento, tvDataEvento, tvEnderecoEvento;
    Button btnAbrirMapa;
    AlertDialog.Builder alertDialogBuilder;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.btnExcluirEvento:
                alertDialogBuilder.show();
                return true;
            case R.id.btnEditarEvento:
                editarEvento();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity_view_evento, menu);
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_evento);

        Bundle extras = getIntent().getExtras();
        evento = extras != null ?
                (Evento) extras.getSerializable(FormEventoActivity.EXTRA_EVENTO) : null;

        eventoViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getApplication()))
                .get(EventoViewModel.class);

        tvNomeEvento = findViewById(R.id.tvNomeEvento);
        tvDataEvento = findViewById(R.id.tvDataEvento);
        tvEnderecoEvento = findViewById(R.id.tvEnderecoEvento);
        tvDescricaoEvento = findViewById(R.id.tvDescricaoEvento);
        btnAbrirMapa = findViewById(R.id.btnAbrirMapa);

        if (evento != null) {

            updateView();

            alertDialogBuilder = new AlertDialog.Builder(ViewEventoActivity.this);
            alertDialogBuilder.setTitle(R.string.titulo_dialog_excluir_evento)
                    .setMessage(R.string.confirmacao_dialog_excluir_evento)
                    .setPositiveButton(R.string.btn_excluir_evento,
                            (dialogInterface, i) -> excluirEvento())
                    .setNegativeButton(R.string.btn_cancelar_excluir_evento,
                            ((dialogInterface, i) -> {

                    }))
                    .create();
        }

    }

    private void editarEvento() {
        Intent intent = new Intent(ViewEventoActivity.this, FormEventoActivity.class);

        if (evento == null) {
            setResult(RESULT_CANCELED, intent);
            finish();
        } else {
            intent.putExtra(FormEventoActivity.EXTRA_EVENTO, evento);
            startActivityForResult(intent, EDITAR_EVENTO_ACTIVITY_REQUEST_CODE);
        }
    }

    private void excluirEvento() {
        Intent intent = new Intent();

        if (evento == null) {
            setResult(RESULT_CANCELED, intent);
        } else {
            intent.putExtra(FormEventoActivity.EXTRA_EVENTO, evento);
            setResult(EXCLUIR_EVENTO_ACTIVITY_REQUEST_CODE, intent);
        }
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDITAR_EVENTO_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Evento evento_extra = (Evento) (data != null ?
                    data.getSerializableExtra(FormEventoActivity.EXTRA_EVENTO) : null);
            if (evento_extra != null) {
                eventoViewModel.alterar(evento_extra);
                Toast.makeText(getApplicationContext(),
                        R.string.sucesso_editar_cadastro, Toast.LENGTH_LONG).show();
                evento = evento_extra;
                updateView();
            } else {
                Toast.makeText(getApplicationContext(),
                        R.string.erro_salvar_evento, Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(),
                    R.string.erro_salvar_evento, Toast.LENGTH_LONG).show();
        }
    }

    public void updateView() {

        tvNomeEvento.setText(evento.getNome());
        tvDataEvento.setText(evento.getData());
        tvDescricaoEvento.setText(evento.getDescricao());
        tvEnderecoEvento.setText(evento.getEndereco());

        btnAbrirMapa.setOnClickListener(view -> {
            Uri consulta = Uri.parse(
                    String.format("geo:%f,%f?q=%s", evento.getLatitude(), evento.getLongitude()
                            , evento.getEndereco()));
            Intent intent = new Intent(Intent.ACTION_VIEW, consulta);
            intent.setPackage("com.google.android.apps.maps");
            startActivity(intent);
        });

    }
}
