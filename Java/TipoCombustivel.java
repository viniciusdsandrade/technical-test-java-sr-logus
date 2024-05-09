public enum TipoCombustivel {
    GASOLINA("Gasolina"),
    ETANOL("Etanol");

    private final String descricao;

    TipoCombustivel(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
