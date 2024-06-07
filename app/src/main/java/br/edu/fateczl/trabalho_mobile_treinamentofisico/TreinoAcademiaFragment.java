package br.edu.fateczl.trabalho_mobile_treinamentofisico;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class TreinoAcademiaFragment extends Fragment {

    private View view;
    private EditText etDateTA;
    private EditText etMuscTA;
    private EditText etLocalTA;
    private EditText etExTA;
    private Button btRegTA;
    private Button btUpTA;

    public TreinoAcademiaFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_treino_academia, container, false);

        etDateTA = view.findViewById(R.id.etDateTA);
        etMuscTA = view.findViewById(R.id.etMuscTA);
        etLocalTA = view.findViewById(R.id.etLocalTA);
        etExTA = view.findViewById(R.id.etExTA);
        btRegTA = view.findViewById(R.id.btRegTA);
        btUpTA = view.findViewById(R.id.btUpTA);

        btRegTA.setOnClickListener( op -> register());
        btUpTA.setOnClickListener( op -> update());

        return view;
    }

    private void update() {
    }

    private void register() {
    }
}