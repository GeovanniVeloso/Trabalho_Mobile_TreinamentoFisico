package br.edu.fateczl.trabalho_mobile_treinamentofisico;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

import controller.TreinoCasaController;
import model.TreinoCasa;
import persistance.TreinoCasaDAO;

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

    private TreinoCasaController TCC;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_treino_casa, container, false);

        etDateTC = view.findViewById(R.id.etDateTC);
        etMuscTC = view.findViewById(R.id.etMuscTC);
        etExTC = view.findViewById(R.id.etExTC);
        etTimeTC = view.findViewById(R.id.etTimeTC);
        btRegTC = view.findViewById(R.id.btRegTC);
        btUpTC = view.findViewById(R.id.btUpTC);

        TCC = new TreinoCasaController(new TreinoCasaDAO(this.getContext()));


        btRegTC.setOnClickListener(op -> register());
        btUpTC.setOnClickListener(op -> update());

        return view;
    }

    private void update() {
        boolean teste = testFields();
        if(teste){
            TreinoCasa tc = create();
            try {
                TCC.update(tc);
                Toast.makeText(view.getContext(), "Treino atualizado com sucesso", Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            clearFields();
        }else{
            Toast.makeText(view.getContext(), "Preencha todos os campos antes de continuar.", Toast.LENGTH_SHORT).show();
        }
    }
    private void register() {
        boolean teste = testFields();
        if(teste == true){
            TreinoCasa ta = create();
            try {
                TCC.insert(ta);
                Toast.makeText(view.getContext(), "Treino salvo com sucesso", Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            clearFields();
        }else{
            Toast.makeText(view.getContext(), "Preencha todos os campos antes de continuar.", Toast.LENGTH_SHORT).show();
        }
    }

    private TreinoCasa create() {
        TreinoCasa tc = new TreinoCasa();

        tc.setId(TCC.createId(etDateTC.getText().toString()));
        tc.setDate(TCC.createDate(etDateTC.getText().toString()));
        tc.setMuscularGroup(etMuscTC.getText().toString());
        tc.setExercises(etExTC.getText().toString());
        tc.setTime(Integer.parseInt(etTimeTC.getText().toString()));

        return tc;
    }

    private boolean testFields() {
        if(!(etDateTC.getText().toString().isEmpty()) &&
                !(etMuscTC.getText().toString().isEmpty()) &&
                !(etExTC.getText().toString().isEmpty()) &&
                !(etTimeTC.getText().toString().isEmpty())){
            return true;
        }else{
            return false;
        }
    }

    private void clearFields(){
        etDateTC.setText("");
        etMuscTC.setText("");
        etExTC.setText("");
        etTimeTC.setText("");
    }
}