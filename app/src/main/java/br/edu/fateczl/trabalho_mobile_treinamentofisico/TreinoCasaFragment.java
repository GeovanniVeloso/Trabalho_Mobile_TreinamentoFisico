package br.edu.fateczl.trabalho_mobile_treinamentofisico;

import static android.provider.SyncStateContract.Helpers.update;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class TreinoCasaFragment extends Fragment {

    public TreinoCasaFragment() {
        super();
    }

    private View view;
    private EditText etDateTC;
    private EditText etMuscTC;
    private EditText etExTC;
    private EditText etTimeTC;
    private Button btRegTC;
    private Button btUpTC;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_treino_casa, container, false);

        etDateTC = view.findViewById(R.id.etDateTC);
        etMuscTC = view.findViewById(R.id.etMuscTC);
        etExTC = view.findViewById(R.id.etExTC);
        etTimeTC = view.findViewById(R.id.etTimeTC);
        btRegTC = view.findViewById(R.id.btRegTC);
        btUpTC = view.findViewById(R.id.btUpTC);

        btRegTC.setOnClickListener( op -> register());
        btUpTC.setOnClickListener( op -> update());

        return view;
    }

    private void update() {
    }

    private void register() {
    }
}