package br.edu.fateczl.trabalho_mobile_treinamentofisico;

import static java.nio.file.Files.delete;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class DeleteFragment extends Fragment {

    private View view;
    private EditText etDateDL;
    private EditText etMuscDL;
    private Button btDelete;

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

        btDelete.setOnClickListener( op -> delete());

        return view;
    }

    private void delete() {
    }
}