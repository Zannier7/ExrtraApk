package upeu.agenda.exa.agendaexa.Entidad;

public class Contactos {
    private Integer idcontact;
    private String number;
    private String name;

    public Contactos() {
    }

    public Contactos(Integer idcontact, String number, String name) {
        this.idcontact = idcontact;
        this.number = number;
        this.name = name;
    }

    public Contactos(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public Integer getIdcontact() {
        return idcontact;
    }

    public void setIdcontact(Integer idcontact) {
        this.idcontact = idcontact;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
