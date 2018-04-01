package br.org.venturus.ricardotakemura.nytimes.exception;

/**
 * Classe que representa as exceções de Json
 * @author ricardotakemura
 */
public class JsonException extends Exception {

    /**
     * Construtor da classe
     * @param message mensagem (String)
     * @param parent parent exception (Throwable)
     */
    public JsonException(final String message, final Throwable parent) {
        super(message);
        initCause(parent);
    }
}
