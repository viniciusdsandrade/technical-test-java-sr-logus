import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Queue;

public class Posto {
    private final Bomba bombaGasolina;
    private final Bomba bombaEtanol;
    private final Queue<Veiculo> filaVeiculos;

    public Posto(Bomba bombaGasolina, Bomba bombaEtanol, Queue<Veiculo> filaVeiculos) {
        this.bombaGasolina = bombaGasolina;
        this.bombaEtanol = bombaEtanol;
        this.filaVeiculos = filaVeiculos;
    }

    /**
     * Abastece os veículos na fila e gera um relatório de abastecimento.
     * <p>
     * Este método percorre a fila de veículos e abastece cada veículo na bomba correta,
     * seja ela de gasolina ou etanol, com base no método de decisão do veículo.
     * O método de decisão do veículo considera o preço por litro e o consumo por km de cada tipo de combustível.
     * <p>
     * Após abastecer cada veículo, o método gera um relatório de abastecimento.
     * O relatório inclui o modelo do veículo, a quantidade de litros abastecidos e o tipo de combustível.
     * Além disso, o relatório fornece um resumo do total de litros abastecidos por cada bomba e o total geral de cada tipo de combustível.
     * <p>
     * O relatório é impresso no console.
     */
    public String abastecerVeiculos() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Resultado da simulação\n");
        relatorio.append("-----------------------------------------------------------------------------------------------------\n");
        while (!filaVeiculos.isEmpty()) {
            Veiculo veiculo = removerVeiculo();
            Bomba bomba = escolherBomba(veiculo);
            double litrosAbastecidos = bomba.abastecer(veiculo);

            // Obter a hora atual e formatá-la como HH:mm
            String horaAtual = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

            relatorio.append(
                    String.format("[%s] Veículo modelo %s Placa: %s foi abastecido com %.2f litros de %s\n",
                            horaAtual,
                            veiculo.getModelo(),
                            veiculo.getPlaca(),
                            litrosAbastecidos,
                            bomba.getCombustivel().getTipoCombustivel())
            );
        }
        relatorio.append("\nResumo da simulação\n");
        relatorio.append(String.format("* Total abastecido na bomba 1 GASOLINA  %.2f litros\n", bombaGasolina.getTotalAbastecido()));
        relatorio.append(String.format("* Total abastecido na bomba 2 ETANOL    %.2f litros\n", bombaEtanol.getTotalAbastecido()));
        relatorio.append(String.format("* Total abastecido            GASOLINA  %.2f litros\n", Bomba.totalGasolina));
        relatorio.append(String.format("* Total abastecido            ETANOL    %.2f litros\n", Bomba.totalEtanol));
        relatorio.append("-----------------------------------------------------------------------------------------------------\n");

        return relatorio.toString();
    }

    /**
     * Escolhe a bomba correta para abastecer o veículo com base no tipo de combustível.
     * <p>
     * Este método usa o método de decisão do veículo para determinar o tipo de combustível mais vantajoso.
     * Se o tipo de combustível for GASOLINA, a bomba de gasolina é escolhida.
     * Se o tipo de combustível for ETANOL, a bomba de etanol é escolhida.
     *
     * @param veiculo O veículo que precisa ser abastecido.
     * @return A bomba correta para abastecer o veículo.
     */
    private Bomba escolherBomba(Veiculo veiculo) {
        TipoCombustivel tipoCombustivel = veiculo.decidirCombustivel();
        if (tipoCombustivel == TipoCombustivel.GASOLINA)
            return bombaGasolina;
        else
            return bombaEtanol;
    }

    public Veiculo removerVeiculo() {
        return filaVeiculos.poll();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Posto {\n");
        sb.append("\tbombaGasolina = ").append(bombaGasolina).append(",\n");
        sb.append("\tbombaEtanol = ").append(bombaEtanol).append(",\n");
        sb.append("\tfilaVeiculos = [\n");
        for (Veiculo veiculo : filaVeiculos) {
            sb.append("\t\t").append(veiculo).append(",\n");
        }
        sb.append("\t]\n");
        sb.append('}');
        return sb.toString();
    }
}
