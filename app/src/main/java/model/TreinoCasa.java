package model;

public class TreinoCasa extends Treino {

    private int time;

    public TreinoCasa(){
        super();
    }

    @Override
    public String toString() {
        return "Treino feito em casa para "+getMuscularGroup()+" em "+getDate()+" durante "+time+" minutos.";
    }
}
