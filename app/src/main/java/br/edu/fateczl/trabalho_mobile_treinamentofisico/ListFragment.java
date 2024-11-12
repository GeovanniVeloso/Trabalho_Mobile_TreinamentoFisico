package br.edu.fateczl.trabalho_mobile_treinamentofisico;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import controller.TreinoAcademiaController;
import controller.TreinoCasaController;
import model.TreinoAcademia;
import model.TreinoCasa;
import persistence.TreinoAcademiaDAO;
import persistence.TreinoCasaDAO;

public class ListFragment extends Fragment {

    private View view;
    private Button btList;
    private TextView tvResList;
    private RadioButton rbList01;
    private RadioButton rbList02;
    private TreinoAcademiaController TAC;
    private TreinoCasaController TCC;

    public ListFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_list, container, false);

        btList = view.findViewById(R.id.btList);
        tvResList = view.findViewById(R.id.tvResList);
        rbList01 = view.findViewById(R.id.rb01List);
        rbList02 = view.findViewById(R.id.rb02List);

        btList.setOnClickListener(op -> search());

        return view;
    }

    private void search() {
        StringBuffer buffer = new StringBuffer();
        if (rbList01.isChecked()) {
            TAC = new TreinoAcademiaController(new TreinoAcademiaDAO(this.getContext()));
            try {
                List<TreinoAcademia> treinos = TAC.list();
                if (!treinos.isEmpty()) {
                    for (TreinoAcademia t : treinos) {
                        buffer.append(t.toString());
                        buffer.append("\n");
                    }
                    tvResList.setText(buffer.toString());
                } else {
                    Toast.makeText(view.getContext(), "Não há Treinos Cadastrados.", Toast.LENGTH_SHORT).show();
                }
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else if (rbList02.isChecked()) {
            TCC = new TreinoCasaController(new TreinoCasaDAO(this.getContext()));
            try {
                List<TreinoCasa> treinos = TCC.list();
                if(!treinos.isEmpty()){
                    for (TreinoCasa t:treinos) {
                        buffer.append(t.toString());
                        buffer.append("\n");
                    }
                    tvResList.setText(buffer.toString());
                }else{
                    Toast.makeText(view.getContext(), "Não há Treinos Cadastrados.", Toast.LENGTH_SHORT).show();
                }
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(view.getContext(), "Selecione o tipo de treino.", Toast.LENGTH_SHORT).show();
        }
    }
}