package controller;

import java.sql.SQLException;
import java.time.LocalDate;
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
        TreinoCasa tc = search(treinoCasa);
        if (tc != null){
            throw new RuntimeException("Treino com data já cadastrada");
        }else{
            TCDAO.insert(treinoCasa);
        }

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

    public int createId(String data) {
        String split[] = data.split("");
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
        data = Year + Month + Day;
        return Integer.parseInt(data);
    }
    public LocalDate createDate(String data) {
        String split[] = data.split("");
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
        LocalDate date = LocalDate.of(Integer.parseInt(Year), Integer.parseInt(Month), Integer.parseInt(Day));
        return date;
    }
}
