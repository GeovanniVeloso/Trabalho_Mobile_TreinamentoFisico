package controller;

import java.sql.SQLException;
import java.time.LocalDate;
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

        TreinoAcademia ta = search(treinoAcademia);
        if(ta != null){
            throw new RuntimeException("Treino com data já cadastrada");
        }else{
            TADAO.insert(treinoAcademia);
        }


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

    public int createId(String data){
        String split[] = data.split("");
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
        data = Year + Month + Day;
        return Integer.parseInt(data);
    }

    public LocalDate createDate(String date) {
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

}
