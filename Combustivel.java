public class Combustivel {
    private final double PRECO_ETANOL_LITRO = 2.27;
    private final double PRECO_GASOLINA_LITRO = 2.90;
    private final TipoCombustivel tipoCombustivel;
    private final double precoLitro;

    public Combustivel(TipoCombustivel tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
        this.precoLitro = this.getPrecoLitro();
    }

    public double getPrecoLitro() {
        if (tipoCombustivel == TipoCombustivel.ETANOL)
            return PRECO_ETANOL_LITRO;
        else
            return PRECO_GASOLINA_LITRO;
    }

    public TipoCombustivel getTipoCombustivel() {
        return tipoCombustivel;
    }

    @Override
    public String toString() {
        return "Combustivel {\n" +
                "\ttipoCombustivel = " + tipoCombustivel + ",\n" +
                "\tprecoLitro = " + precoLitro + "\n" +
                '}';
    }
}


