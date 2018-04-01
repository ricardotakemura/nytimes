package br.org.venturus.ricardotakemura.nytimes.exception;

/**
 * Exceção de servidor
 * @author ricardotakemura
 */
public class ServerException extends Exception {

    private int statusServer;

    /**
     * local error
     */
    public static final Integer LOCAL_ERROR = -1;

    /**
     * Construtor da classe
     * @param statusServer status server error
     */
    public ServerException(final int statusServer) {
        super("Server status code: " + statusServer);
        this.statusServer = statusServer;
    }

    /**
     * Construtor da classe
     * @param statusServer status server error
     * @param parent parent exception (Throwable)
     */
    public ServerException(final int statusServer, final Throwable parent) {
        super("Server status code: " + statusServer);
        this.statusServer = statusServer;
        initCause(parent);
    }

    /**
     * status server
     * @return int
     */
    public int getStatusServer() {
        return statusServer;
    }

}
