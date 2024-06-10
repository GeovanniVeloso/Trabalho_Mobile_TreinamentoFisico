package controller;

import java.sql.SQLException;
import java.util.List;

import model.TreinoCasa;
import persistance.TreinoCasaDAO;

public class TreinoCasaController implements IController<TreinoCasa> {

    private final TreinoCasaDAO TCDAO;

    public TreinoCasaController(TreinoCasaDAO tcdao) {
        TCDAO = tcdao;
    }

    @Override
    public void insert(TreinoCasa treinoCasa) throws SQLException {
        if(TCDAO.open() == null){
            TCDAO.open();
        }
        TCDAO.insert(treinoCasa);

        TCDAO.close();
    }

    @Override
    public void update(TreinoCasa treinoCasa) throws SQLException {
        if(TCDAO.open() == null){
            TCDAO.open();
        }
        TCDAO.update(treinoCasa);

        TCDAO.close();
    }

    @Override
    public void delete(TreinoCasa treinoCasa) throws SQLException {
        if(TCDAO.open() == null){
            TCDAO.open();
        }
        TCDAO.delete(treinoCasa);

        TCDAO.close();
    }

    @Override
    public TreinoCasa search(TreinoCasa treinoCasa) throws SQLException {
        if(TCDAO.open() == null){
            TCDAO.open();
        }
        return TCDAO.findOne(treinoCasa);
    }

    @Override
    public List<TreinoCasa> list() throws SQLException {
        if(TCDAO.open() == null){
            TCDAO.open();
        }
        return TCDAO.findAll();
    }
}
