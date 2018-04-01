package br.org.venturus.ricardotakemura.nytimes.exception;

public class BusinessException extends Exception {

    private Integer resourceStringId;

    public BusinessException(final int resourceStringId, final Exception e) {
        super();
        this.resourceStringId = resourceStringId;
        initCause(e);
    }

    public BusinessException(final int resourceStringId) {
        super();
        this.resourceStringId = resourceStringId;
    }

    public Integer getResourceStringId() {
        return resourceStringId;
    }

}
