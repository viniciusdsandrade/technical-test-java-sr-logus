public class Veiculo {
    private final double PRECO_ETANOL_LITRO = 2.27;
    private final double PRECO_GASOLINA_LITRO = 2.90;
    private final String modelo;
    private String placa="";
    private final double capacidadeTanque;
    private final double kmPorLitroEtanol;
    private final double kmPorLitroGasolina;

    public Veiculo(String modelo,
                   String kmPorLitroEtanol,
                   String kmPorLitroGasolina,
                   String capacidadeTanque) {
        this.modelo = modelo;
        this.kmPorLitroEtanol = kmPorLitroEtanol.isEmpty() ? 0.0 : Double.parseDouble(kmPorLitroEtanol);
        this.kmPorLitroGasolina = kmPorLitroGasolina.isEmpty() ? 0.0 : Double.parseDouble(kmPorLitroGasolina);
        this.capacidadeTanque = capacidadeTanque.isEmpty() ? 0.0 : Double.parseDouble(capacidadeTanque);
        this.placa = "";
    }

    public Veiculo(Veiculo veiculoModelo) {
        this.modelo = veiculoModelo.modelo;
        this.kmPorLitroEtanol = veiculoModelo.kmPorLitroEtanol;
        this.kmPorLitroGasolina = veiculoModelo.kmPorLitroGasolina;
        this.capacidadeTanque = veiculoModelo.capacidadeTanque;
        this.placa = "";
    }

    public String getModelo() {
        return modelo;
    }
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public String getCapacidadeTanque() {
        return String.valueOf(capacidadeTanque);
    }
    public String getKmPorLitroEtanol() {
        return String.valueOf(kmPorLitroEtanol);
    }
    public String getKmPorLitroGasolina() {
        return String.valueOf(kmPorLitroGasolina);
    }

    /**
     * Decide qual combustível é mais vantajoso para o veículo com base no preço por litro
     * e no consumo por quilômetro.
     * <p>
     * O método calcula o preço por quilômetro para etanol e gasolina e, em seguida,
     * compara os custos totais para encher o tanque com cada combustível. O combustível
     * com o menor custo total é considerado o mais vantajoso.
     *
     * @return Um valor do tipo 'TipoCombustivel' indicando o combustível mais vantajoso
     * (ETANOL ou GASOLINA).
     */
    public TipoCombustivel decidirCombustivel() {
        double precoPorKmEtanol = PRECO_ETANOL_LITRO / kmPorLitroEtanol;
        double precoPorKmGasolina = PRECO_GASOLINA_LITRO / kmPorLitroGasolina;

        double precoTotalEtanol = precoPorKmEtanol * capacidadeTanque;
        double precoTotalGasolina = precoPorKmGasolina * capacidadeTanque;

        if (precoTotalEtanol < precoTotalGasolina)
            return TipoCombustivel.ETANOL;
        else
            return TipoCombustivel.GASOLINA;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "modelo='" + modelo + '\'' +
                "placa='" + placa + '\'' +
                ", kmPorLitroEtanol='" + kmPorLitroEtanol + '\'' +
                ", kmPorLitroGasolina='" + kmPorLitroGasolina + '\'' +
                ", tanque='" + capacidadeTanque + '\'' +
                '}';
    }
}
