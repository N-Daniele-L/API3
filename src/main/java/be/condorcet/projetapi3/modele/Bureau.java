package be.condorcet.projetapi3.modele;

public class Bureau {
    private String sigle,tel;
    private int idbur;
    public Bureau(int idbur, String sigle, String tel) {
        this.idbur = idbur;
        this.sigle = sigle;
        this.tel = tel;
    }

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getIdbur() {
        return idbur;
    }

    public void setIdbur(int idbur) {
        this.idbur = idbur;
    }
}
