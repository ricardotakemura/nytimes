package br.org.venturus.ricardotakemura.nytimes.exception;

public class ServerException extends Exception {

    private int statusServer;

    public static final Integer LOCAL_ERROR = -1;

    public ServerException(final int statusServer) {
        super("Server status code: " + statusServer);
        this.statusServer = statusServer;
    }

    public ServerException(final int statusServer, final Throwable parent) {
        super("Server status code: " + statusServer);
        this.statusServer = statusServer;
        initCause(parent);
    }

    public int getStatusServer() {
        return statusServer;
    }

}
