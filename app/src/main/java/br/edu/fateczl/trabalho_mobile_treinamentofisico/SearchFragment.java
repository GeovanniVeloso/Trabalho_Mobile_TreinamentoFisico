package br.edu.fateczl.trabalho_mobile_treinamentofisico;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

import controller.TreinoAcademiaController;
import controller.TreinoCasaController;
import model.TreinoAcademia;
import model.TreinoCasa;
import persistance.TreinoAcademiaDAO;
import persistance.TreinoCasaDAO;

public class SearchFragment extends Fragment {

    private View view;
    private EditText etDateSC;
    private Button btSearch;
    private TextView tvResSC;
    private RadioButton rb01Search;
    private RadioButton rb02Search;
    private TreinoAcademiaController TAC;
    private TreinoCasaController TCC;

    public SearchFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_search, container, false);

        etDateSC = view.findViewById(R.id.etDateSC);
        btSearch = view.findViewById(R.id.btSearch);
        tvResSC = view.findViewById(R.id.tvResSC);
        rb01Search = view.findViewById(R.id.rb01Search);
        rb02Search = view.findViewById(R.id.rb02Search);

        btSearch.setOnClickListener( op -> search());

        return view;
    }

    private void search() {
        if (!etDateSC.getText().toString().isEmpty()){
            if (rb01Search.isChecked()){
                TAC = new TreinoAcademiaController(new TreinoAcademiaDAO(this.getContext()));
                TreinoAcademia ta = new TreinoAcademia();
                String teste = etDateSC.getText().toString();
                ta.setId(TAC.createId(teste));
                try {
                    TreinoAcademia treino = TAC.search(ta);
                    if(treino.getId() != 0){
                        tvResSC.setText(treino.toString());
                    }else{
                        Toast.makeText(view.getContext(), "Treino Não Encontrado", Toast.LENGTH_SHORT).show();
                    }
                } catch (SQLException e) {
                    Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else if (rb02Search.isChecked()) {
                TCC = new TreinoCasaController(new TreinoCasaDAO(this.getContext()));
                TreinoCasa tc = new TreinoCasa();
                tc.setId(TCC.createId(etDateSC.getText().toString()));
                try {
                    TreinoCasa treino = TCC.search(tc);
                    if(treino.getId() != 0){
                        tvResSC.setText(treino.toString());
                    }else{
                        Toast.makeText(view.getContext(), "Treino Não Encontrado", Toast.LENGTH_SHORT).show();
                    }
                } catch (SQLException e) {
                    Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(view.getContext(), "Selecione o tipo de Treino", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(view.getContext(), "Insira a data do treino", Toast.LENGTH_SHORT).show();
        }
    }
}