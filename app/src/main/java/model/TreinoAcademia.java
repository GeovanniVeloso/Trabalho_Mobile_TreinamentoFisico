package model;

import java.util.List;

public class TreinoAcademia extends Treino{

    private String Academia;


    @Override
    public String toString() {
        return "Treino feito em "+getDate()+" na "+Academia+" para trabalhar "+getMuscularGroup();
    }

    public String getAcademia() {
        return Academia;
    }

    public void setAcademia(String academia) {
        Academia = academia;
    }
}
