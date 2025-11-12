package Simulacro.BancoDeAlimentos.Exception;

/*
 * Excepción propia del Banco de Alimentos.
 * Se usa para los errores indicados en el enunciado (centro existente, colaborador repetido, etc.)
 */
public class BancoException extends Exception {
    public BancoException(String mensaje) {
        super(mensaje);
    }
}