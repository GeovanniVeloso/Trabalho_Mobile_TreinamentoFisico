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

import controller.TreinoAcademiaController;
import model.TreinoAcademia;
import persistance.TreinoAcademiaDAO;

public class TreinoAcademiaFragment extends Fragment {

    private View view;
    private EditText etDateTA;
    private EditText etMuscTA;
    private EditText etLocalTA;
    private EditText etExTA;
    private Button btRegTA;
    private Button btUpTA;
    private TreinoAcademiaController TAC;

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

        TAC = new TreinoAcademiaController(new TreinoAcademiaDAO(this.getContext()));
        btRegTA.setOnClickListener( op -> register());
        btUpTA.setOnClickListener( op -> update());

        return view;
    }


    private void update() {
        boolean teste = testCampos();
        if(teste){
            TreinoAcademia ta = create();
            try{
                TAC.update(ta);
                Toast.makeText(view.getContext(), "Treino atualizado com sucesso", Toast.LENGTH_LONG).show();
            }catch (SQLException e){
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            clearFields();
        }else{
            Toast.makeText(view.getContext(), "Preencha todos os campos antes de continuar.", Toast.LENGTH_SHORT).show();
        }
    }

    private void register() {
        boolean teste = testCampos();
        if(teste){
            TreinoAcademia ta = create();
            try{
                TAC.insert(ta);
                Toast.makeText(view.getContext(), "Treino salvo com sucesso", Toast.LENGTH_LONG).show();
            }catch (SQLException e){
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            clearFields();
        }else{
            Toast.makeText(view.getContext(), "Preencha todos os campos antes de continuar.", Toast.LENGTH_SHORT).show();
        }
    }

    private TreinoAcademia create() {
        TreinoAcademia ta = new TreinoAcademia();

        ta.setId(TAC.createId(etDateTA.getText().toString()));
        ta.setDate(TAC.createDate(etDateTA.getText().toString()));
        ta.setMuscularGroup(etMuscTA.getText().toString());
        ta.setExercises(etExTA.getText().toString());
        ta.setAcademia(etLocalTA.getText().toString());

        return ta;
    }

    private void clearFields() {
        etDateTA.setText("");
        etMuscTA.setText("");
        etLocalTA.setText("");
        etExTA.setText("");
    }

    private Boolean testCampos() {
        boolean teste = etDateTA.getText().toString().isEmpty() ||
                etMuscTA.getText().toString().isEmpty() ||
                etLocalTA.getText().toString().isEmpty() ||
                etExTA.getText().toString().isEmpty();

        return !teste;
    }
}