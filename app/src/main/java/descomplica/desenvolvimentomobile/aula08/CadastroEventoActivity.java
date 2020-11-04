package descomplica.desenvolvimentomobile.aula08;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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

public class CadastroEventoActivity extends AppCompatActivity {

    public static final String EXTRA_EVENTO = "descomplica.desenvolvimentomobile.aula08.CadastroEventoActivity";
    EditText etNome, etPesquisaEndereco, etData, etDescricao;
    double latitude, longitude;
    Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);

        Places.initialize(getApplicationContext(), "");

        etNome = findViewById(R.id.etNome);
        etDescricao = findViewById(R.id.etDescricao);
        etData = findViewById(R.id.etData);

        etPesquisaEndereco = findViewById(R.id.etPesquisaEndereco);
        etPesquisaEndereco.setFocusable(false);
        etPesquisaEndereco.setOnClickListener(view -> {
            List<Place.Field> estabelecimentos = Arrays.asList(Place.Field.NAME,
                    Place.Field.ADDRESS);
            Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY,
                    estabelecimentos).build(CadastroEventoActivity.this);
            startActivityForResult(intent, 100);
        });

        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(view -> {
            Intent intent = new Intent();
            if (TextUtils.isEmpty(etNome.getText())) {
                setResult(RESULT_CANCELED, intent);
            } else {
                Evento evento = new Evento(etNome.getText().toString(),
                        etData.getText().toString(), latitude, longitude,
                        etDescricao.getText().toString());
                intent.putExtra(EXTRA_EVENTO, evento);
                setResult(RESULT_OK, intent);
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