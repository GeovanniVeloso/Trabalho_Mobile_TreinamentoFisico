package br.edu.fateczl.trabalho_mobile_treinamentofisico;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ListFragment extends Fragment {
    
    private View view;
    private Button btList;
    private TextView tvResList;
    
    public ListFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        view = inflater.inflate(R.layout.fragment_list, container, false);
        
        btList = view.findViewById(R.id.btList);
        tvResList = view.findViewById(R.id.tvResList);

        btList.setOnClickListener( op -> search());
        
        return view;
    }

    private void search() {
    }
}