package upeu.agenda.exa.agendaexa.Entidad;

public class Contactos {
    private int idcontact;
    private int number;
    private String name;

    public Contactos() {
    }

    public Contactos(int idcontact, int number, String name) {
        this.idcontact = idcontact;
        this.number = number;
        this.name = name;
    }

    public Contactos(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getIdcontact() {
        return idcontact;
    }

    public void setIdcontact(int idcontact) {
        this.idcontact = idcontact;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
