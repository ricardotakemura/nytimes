package br.org.venturus.ricardotakemura.nytimes.exception;

/**
 * Classe que representa as exceção de negócio
 * @author ricardotakemura
 */
public class BusinessException extends Exception {

    private Integer resourceStringId;

    /**
     * Construtor da classe
     * @param resourceStringId resource string id (int)
     * @param e parent exception (Throwable)
     */
    public BusinessException(final int resourceStringId, final Throwable e) {
        super();
        this.resourceStringId = resourceStringId;
        initCause(e);
    }

    /**
     * Construtor da classe
     * @param resourceStringId resource string id (int)
     */
    public BusinessException(final int resourceStringId) {
        super();
        this.resourceStringId = resourceStringId;
    }

    /**
     * resource string id
     * @return Integer
     */
    public Integer getResourceStringId() {
        return resourceStringId;
    }

}
