public class Bomba {
    private final Combustivel combustivel;
    private final double velocidadeAbastecimento;
    private double totalAbastecido = 0;
    public static double totalGasolina = 0;
    public static double totalEtanol = 0;

    public Bomba(Combustivel combustivel, double velocidadeAbastecimento) {
        this.combustivel = combustivel;
        this.velocidadeAbastecimento = velocidadeAbastecimento;
    }

    public double getVelocidadeAbastecimento() {
        return velocidadeAbastecimento;
    }
    public Combustivel getCombustivel() {
        return combustivel;
    }
    public double getTotalAbastecido() {
        return totalAbastecido;
    }

    /**
     * Abastece um veículo e atualiza o total de combustível abastecido.
     * <p>
     * Este método abastece um veículo com base na capacidade do tanque do veículo.
     * Ele assume que o tanque do veículo está vazio. O total de combustível abastecido pela bomba é atualizado.
     * Além disso, o total de gasolina ou etanol abastecido é atualizado com base no tipo de combustível da bomba.
     * <p>
     * @param veiculo O veículo a ser abastecido.
     * @return A quantidade de combustível (em litros) com que o veículo foi abastecido.
     */
    public double abastecer(Veiculo veiculo) {
        double litrosAbastecidos = Double.parseDouble(veiculo.getCapacidadeTanque()); // Supondo que o tanque esteja vazio
        totalAbastecido += litrosAbastecidos;
        if (combustivel.getTipoCombustivel() == TipoCombustivel.GASOLINA)
            totalGasolina += litrosAbastecidos;
        else if (combustivel.getTipoCombustivel() == TipoCombustivel.ETANOL)
            totalEtanol += litrosAbastecidos;
        return litrosAbastecidos;
    }

    @Override
    public String toString() {
        return "Bomba {\n" +
                "\tcombustivel = " + combustivel + ",\n" +
                "\tvelocidadeAbastecimento = " + velocidadeAbastecimento + "\n" +
                '}';
    }
}
