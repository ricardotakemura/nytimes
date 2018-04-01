package br.org.venturus.ricardotakemura.nytimes.exception;

public class JsonException extends Exception {

    public JsonException(final String message, final Throwable parent) {
        super(message);
        initCause(parent);
    }
}
