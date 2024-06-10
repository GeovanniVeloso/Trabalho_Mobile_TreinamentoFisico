package controller;

import java.sql.SQLException;
import java.util.List;

import model.TreinoAcademia;
import persistance.TreinoAcademiaDAO;

public class TreinoAcademiaController implements IController<TreinoAcademia> {

    public final TreinoAcademiaDAO TADAO;

    public TreinoAcademiaController(TreinoAcademiaDAO tadao) {
        TADAO = tadao;
    }

    @Override
    public void insert(TreinoAcademia treinoAcademia) throws SQLException {
        if (TADAO.open() == null){
            TADAO.open();
        }

        TADAO.insert(treinoAcademia);

        TADAO.close();
    }

    @Override
    public void update(TreinoAcademia treinoAcademia) throws SQLException {
        if (TADAO.open() == null){
            TADAO.open();
        }

        TADAO.update(treinoAcademia);

        TADAO.close();
    }

    @Override
    public void delete(TreinoAcademia treinoAcademia) throws SQLException {
        if (TADAO.open() == null){
            TADAO.open();
        }

        TADAO.delete(treinoAcademia);

        TADAO.close();
    }

    @Override
    public TreinoAcademia search(TreinoAcademia treinoAcademia) throws SQLException {
        if(TADAO.open() == null){
            TADAO.open();
        }
        return TADAO.findOne(treinoAcademia);
    }

    @Override
    public List<TreinoAcademia> list() throws SQLException {
        if (TADAO.open() == null){
            TADAO.open();
        }
        return TADAO.findAll();
    }
}
