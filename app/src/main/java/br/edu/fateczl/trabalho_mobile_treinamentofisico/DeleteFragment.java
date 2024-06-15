package br.edu.fateczl.trabalho_mobile_treinamentofisico;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.sql.SQLException;

import controller.TreinoAcademiaController;
import controller.TreinoCasaController;
import model.TreinoAcademia;
import model.TreinoCasa;
import persistance.TreinoAcademiaDAO;
import persistance.TreinoCasaDAO;

public class DeleteFragment extends Fragment {

    private View view;
    private EditText etDateDL;
    private EditText etMuscDL;
    private Button btDelete;
    private RadioButton rb01Del;
    private RadioButton rb02Del;
    private TreinoAcademiaController TAC;
    private TreinoCasaController TCC;

    public DeleteFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_delete, container, false);

        etDateDL = view.findViewById(R.id.etDateDL);
        etMuscDL = view.findViewById(R.id.etMuscDL);
        btDelete = view.findViewById(R.id.btDelete);
        rb01Del = view.findViewById(R.id.rB01Del);
        rb02Del = view.findViewById(R.id.rb02Del);

        btDelete.setOnClickListener( op -> delete());

        return view;
    }

    private boolean testFields() {
        boolean teste = etDateDL.getText().toString().isEmpty() ||
                etMuscDL.getText().toString().isEmpty();

        return !teste;
    }

    private void delete() {
        boolean teste = testFields();
        if(teste == true){
            if(rb01Del.isChecked()){
                TreinoAcademia ta = createTA();
                TAC = new TreinoAcademiaController(new TreinoAcademiaDAO(this.getContext()));
                try {
                    TAC.delete(ta);
                    Toast.makeText(view.getContext(), "Treino Removido Com Sucesso", Toast.LENGTH_SHORT).show();
                } catch (SQLException e) {
                    Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }else if (rb02Del.isChecked()){
                TreinoCasa tc = createTC();
                TCC = new TreinoCasaController(new TreinoCasaDAO(this.getContext()));
                try {
                    TCC.delete(tc);
                    Toast.makeText(view.getContext(), "Treino Removido Com Sucesso", Toast.LENGTH_SHORT).show();
                } catch (SQLException e) {
                    Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(view.getContext(), "Selecione o tipo de treino", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(view.getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
    }

    private int createID() {
        String date = etDateDL.getText().toString();
        String split[] = date.split("");
        String Year = "";
        String Month = "";
        String Day = "";
        for (int i = 0; i < split.length; i++) {
            if (i < 4) {
                Year += split[i];
            } else {
                if (i < 6) {
                    Month += split[i];
                } else {
                    Day += split[i];
                }
            }
        }
        date = Year + Month + Day;
        return Integer.parseInt(date);
    }

    private TreinoCasa createTC() {
        TreinoCasa tc = new TreinoCasa();
        tc.setId(createID());
        tc.setMuscularGroup(etMuscDL.getText().toString());
        return tc;
    }

    private TreinoAcademia createTA() {
        TreinoAcademia ta = new TreinoAcademia();
        ta.setId(createID());
        ta.setMuscularGroup(etMuscDL.getText().toString());
        return ta;
    }
}