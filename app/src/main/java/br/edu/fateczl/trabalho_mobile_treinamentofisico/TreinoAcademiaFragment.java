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
import java.time.LocalDate;

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
        TreinoAcademia ta = create();
        try{
            TAC.update(ta);
            Toast.makeText(view.getContext(), "Treino atualizado com sucesso", Toast.LENGTH_LONG).show();
        }catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private TreinoAcademia create() {
        TreinoAcademia ta = new TreinoAcademia();

        ta.setId(createId());
        ta.setDate(createDate());
        ta.setMuscularGroup(etMuscTA.getText().toString());
        ta.setExercises(etExTA.getText().toString());
        ta.setAcademia(etLocalTA.getText().toString());

        return ta;
    }

    private LocalDate createDate() {
        String date = etDateTA.getText().toString();
        String split[] = date.split("");
        String Year = "";
        String Month = "";
        String Day = "";
        for (int i = 0; i < split.length; i ++){
            if (i < 4) {
                Year += split[i];
            }else{
                if(i < 6){
                    Month += split[i];
                }else{
                    Day += split[i];
                }
            }
        }
        LocalDate data = LocalDate.of(Integer.parseInt(Year),Integer.parseInt(Month), Integer.parseInt(Day));
        return data;
    }
    private int createId(){
        String date = etDateTA.getText().toString();
        String split[] = date.split("");
        String Year = "";
        String Month = "";
        String Day = "";
        for (int i = 0; i < split.length; i++){
            if (i < 4) {
                Year += split[i];
            }else{
                if(i < 6){
                    Month += split[i];
                }else{
                    Day += split[i];
                }
            }
        }
        date = Year + Month + Day;
        return Integer.parseInt(date);
    }

    private void register() {

        TreinoAcademia ta = create();

        try{
            TAC.insert(ta);
            Toast.makeText(view.getContext(), "Treino salvo com sucesso", Toast.LENGTH_LONG).show();
        }catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}