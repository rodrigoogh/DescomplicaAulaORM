package descomplica.desenvolvimentomobile.aula08;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;

import descomplica.desenvolvimentomobile.aula08.model.Evento;

public class FormEventoActivity extends AppCompatActivity {

    public static final String EXTRA_EVENTO = "descomplica.desenvolvimentomobile.aula08.FormEventoActivity.EVENTO";
    EditText etNome, etPesquisaEndereco, etData, etDescricao;
    double latitude, longitude = 0;
    Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_evento);

        Bundle extras = getIntent().getExtras();
        Evento evento = extras != null ? (Evento) extras.getSerializable(EXTRA_EVENTO) : null;
        Places.initialize(getApplicationContext(), "AIzaSyD6oDx66j9lHn35AABWhYfFApkPSSZE5_g");

        etNome = findViewById(R.id.etNome);
        etNome.setText(evento != null ? evento.getNome() : "");

        etDescricao = findViewById(R.id.etDescricao);
        etDescricao.setText(evento != null ? evento.getDescricao() : "");

        etData = findViewById(R.id.etData);
        etData.setText(evento != null ? evento.getData() : "");

        etPesquisaEndereco = findViewById(R.id.etPesquisaEndereco);
        etPesquisaEndereco.setText(evento != null ? evento.getEndereco() : "");
        etPesquisaEndereco.setFocusable(false);
        etPesquisaEndereco.setOnClickListener(view -> {
            List<Place.Field> estabelecimentos = Arrays.asList(Place.Field.NAME,
                    Place.Field.ADDRESS);
            Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY,
                    estabelecimentos).build(FormEventoActivity.this);
            startActivityForResult(intent, 100);
        });

        latitude = evento != null ? evento.getLatitude() : latitude;

        longitude = evento != null ? evento.getLongitude() : longitude;

        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(view -> {
            Intent intent = new Intent();

            if (evento != null) {
                evento.setNome(etNome.getText().toString());
                evento.setDescricao(etDescricao.getText().toString());
                evento.setData(etData.getText().toString());
                evento.setEndereco(etPesquisaEndereco.getText().toString());
                evento.setLatitude(latitude);
                evento.setLongitude(longitude);

                intent.putExtra(EXTRA_EVENTO, evento);
                setResult(RESULT_OK, intent);

            } else {

                if (TextUtils.isEmpty(etNome.getText())) {
                    setResult(RESULT_CANCELED, intent);
                } else {
                    Evento novo_evento = new Evento(0, etNome.getText().toString(),
                            etData.getText().toString(), etPesquisaEndereco.getText().toString(),
                            latitude, longitude, etDescricao.getText().toString());
                    intent.putExtra(EXTRA_EVENTO, novo_evento);
                    setResult(RESULT_OK, intent);
                }
            }

            finish();
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {

            Place estabelecimento = Autocomplete.getPlaceFromIntent(data);
            etPesquisaEndereco.setText(estabelecimento.getAddress());

            LatLng coordenadas = estabelecimento.getLatLng();
            latitude = coordenadas != null ? coordenadas.latitude : 0;
            longitude = coordenadas != null ? coordenadas.longitude : 0;

        } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {

            Status status = Autocomplete.getStatusFromIntent(data);

            Toast.makeText(getApplicationContext(), status.getStatusMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}