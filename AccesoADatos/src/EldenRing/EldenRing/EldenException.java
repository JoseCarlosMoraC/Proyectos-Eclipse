package EldenRing.EldenRing;

// Clase para manejar excepciones específicas del dominio Elden Ring
public class EldenException extends Exception {
    private static final long serialVersionUID = 1L;

    public EldenException(String mensaje) {
        super(mensaje);
    }
}
