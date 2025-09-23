package backend.Models;

public class Administrador extends Persona {
    private String usuario;
    private String password;
    private String cargo;

    public Administrador() {
        super();
    }

    public Administrador(String dni, String nombre, String apellido, String usuario, String password, String cargo) {
        super(dni, nombre, apellido);
        this.usuario = usuario;
        this.password = password;
        this.cargo = cargo;
    }

    public Administrador(int id, String dni, String nombre, String apellido, String usuario, String password, String cargo) {
        super(id, dni, nombre, apellido);
        this.usuario = usuario;
        this.password = password;
        this.cargo = cargo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    // Metodo para validar credenciales
    public boolean validarCredenciales(String usuario, String password) {
        return this.usuario.equals(usuario) && this.password.equals(password);
    }

    @Override
    public String toString() {
        return "Administrador{" +
                super.toString() +
                ", usuario='" + usuario + '\'' +
                ", cargo='" + cargo + '\'' +
                '}';
    }
}
