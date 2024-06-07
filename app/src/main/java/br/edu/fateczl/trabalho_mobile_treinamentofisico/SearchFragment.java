package br.edu.fateczl.trabalho_mobile_treinamentofisico;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchFragment extends Fragment {

    private View view;
    private EditText etDateSC;
    private Button btSearch;
    private TextView tvResSC;

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

        btSearch.setOnClickListener( op -> search());

        return view;
    }

    private void search() {
    }
}