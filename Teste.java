import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Teste {
    /*
    * 1 - Um posto de gasolina com duas bombas abastecedoras independentes, uma de álcool e outra de gasolina, recebe veículos de diversas naturezas.
    * Os veículos adentram ao posto em fila unica, sendo direcionnados para filas individuais das bombas de abastecimento. Cada veículo po deutilizar um ou mais tipos de
    * combustível
    * Crie um programa que simule o abastecimento de uma lista de veícuois informados no arquivo fornecdico, realizando o abastecimento apropriado de cada veículo.
    * Pressupondo que todos os veículos estão com o tanque vazio e os terão completados, e minimizando a razão preco/km rodado ao realizar o direcionamento dos veículos
    * para as bombas, a saíde de ser produzida na ordem cronológica do seventos no seguinte formato:
    *
    * Resultado da simulação
    * ----------------------------------------
    * [00:05] Veiculo modelo FIAT-UNO, placa JGA-7389 foi abastecido com 48 litros de ETANOl
    * [00:10] Veículo modelo AUDI-A4, placa JGB-1234 foi abastecido com 65 litros de GASOLINA

        resumo da simulação
        * Total abastecido na bomba 1 (GASOLNA) 1417 litros
        * TOTAL Abastecido na bomba 2 (ETANOL) 115 litros
        * Total geral abastecido de (GASOLINA) 1516 litros
        * Total abastecido ETANOL 1125 litros
        *
        * informações adicionais
        *
        * O preço do livro da Gasolina é R$ 2,90
        * O preço do litro do Etanol é   R$ 2,27
        *
        * velocidade de abastecimento da bomba de gasolina: 10 litros/minutos
        * velocidade de abastecimento da bomba de álcool:   12 litros/minutos
        *
        * DICA:
        * privilegie baixo acoplamento e alta coesão na solução proposta,
        * minimizanod impactos no caso de alterações nas definicções do problema
     */

    public static void main(String[] args) {
//        testLeituraModelosCSV();
//        testLeituraVeiculosCSV();
//        testLeituraBomba();
//        testLeituraPosto();

        // Caminhos dos arquivos CSV
        String caminho_modelos = "C:\\Users\\Pichau\\Desktop\\teste-logus\\csv\\modelos.csv";
        String caminho_veiculos = "C:\\Users\\Pichau\\Desktop\\teste-logus\\csv\\veiculos.csv";
        String caminho_relatorio = "C:\\Users\\Pichau\\Desktop\\teste-logus\\csv\\teste.csv";

        // Instanciando bombas de gasolina e etanol do posto
        Bomba bomba_1_gasolina = new Bomba(new Combustivel(TipoCombustivel.GASOLINA), 10);
        Bomba bomba_2_etanol = new Bomba(new Combustivel(TipoCombustivel.ETANOL), 12);

        Queue<Veiculo> filaVeiculos = carregarVeiculos(caminho_modelos, caminho_veiculos); // Carregar os veículos do arquivo CSV

        // Criando um posto de gasolina
        Posto posto = new Posto(bomba_1_gasolina, bomba_2_etanol, filaVeiculos);

        // Abastecendo os veículos e gerando o relatório
        String relatorio = posto.abastecerVeiculos();

        System.out.println("Relatório: ");
        System.out.println(relatorio);

        // Gerando o relatório em um arquivo CSV
        EscritorDeArquivoCSV.salvarRelatorioCSV(caminho_relatorio, relatorio);
    }

    /**
     * Carrega os veículos a partir de arquivos CSV e retorna uma fila de veículos.
     * <p>
     * Este método lê os arquivos CSV de modelos e veículos, cria um mapa de modelos,
     * atualiza as placas dos veículos no mapa de modelos, cria um mapa de veículos com as placas atualizadas,
     * e finalmente cria e retorna uma fila de veículos usando o mapa de veículos atualizado.
     * <p>
     * @param caminhoArquivoModelos O caminho do arquivo CSV de modelos.
     * @param caminhoArquivoVeiculos O caminho do arquivo CSV de veículos.
     * @return Uma fila de veículos carregados dos arquivos CSV.
     * @throws IllegalArgumentException Se um modelo ou veículo não for encontrado nos mapas correspondentes.
     */
    public static Queue<Veiculo> carregarVeiculos(String caminhoArquivoModelos, String caminhoArquivoVeiculos) {
        Map<String, Veiculo> mapaModelos = criarMapaModelos(caminhoArquivoModelos);
        Map<String, Veiculo> mapaVeiculosPorPlaca = criarMapaVeiculosPorPlaca(caminhoArquivoVeiculos, mapaModelos);
        return new LinkedList<>(mapaVeiculosPorPlaca.values());
    }

    /**
     * Cria um mapa de modelos de veículos a partir de um arquivo CSV.
     * <p>
     * Este método lê o arquivo CSV de modelos, cria um mapa de modelos e retorna o mapa.
     * <p>
     * @param caminhoArquivoModelos O caminho do arquivo CSV de modelos.
     * @return Um mapa de modelos de veículos.
     */
    private static Map<String, Veiculo> criarMapaModelos(String caminhoArquivoModelos) {
        Map<String, Veiculo> mapaModelos = new HashMap<>();
        int indiceLinhaModelos = 0;
        for (String linha : LeitorDeArquivoCSV.lerArquivo(caminhoArquivoModelos).split("\n")) {
            if (indiceLinhaModelos++ == 0) continue; // Ignorar a primeira linha (cabeçalho)
            String[] dadosVeiculo = linha.split(";");
            Veiculo veiculo = new Veiculo(dadosVeiculo[0], dadosVeiculo[1], dadosVeiculo[2], dadosVeiculo[3]);
            mapaModelos.put(veiculo.getModelo().toLowerCase(), veiculo); // Case-insensitive key
        }
        return mapaModelos;
    }

    /**
     * Cria um mapa de veículos por placa a partir de um arquivo CSV e um mapa de modelos.
     * <p>
     * Este método lê o arquivo CSV de veículos, atualiza as placas dos veículos no mapa de modelos,
     * cria um mapa de veículos com as placas atualizadas e retorna o mapa.
     * <p>
     * @param caminhoArquivoVeiculos O caminho do arquivo CSV de veículos.
     * @param mapaModelos O mapa de modelos de veículos.
     * @return Um mapa de veículos por placa.
     * @throws IllegalArgumentException Se um modelo não for encontrado no mapa de modelos.
     */
    private static Map<String, Veiculo> criarMapaVeiculosPorPlaca(String caminhoArquivoVeiculos, Map<String, Veiculo> mapaModelos) {
        Map<String, Veiculo> mapaVeiculosPorPlaca = new HashMap<>();
        int indiceLinhaVeiculos = 0;
        for (String linha : LeitorDeArquivoCSV.lerArquivo(caminhoArquivoVeiculos).split("\n")) {
            if (indiceLinhaVeiculos++ == 0) continue; // Ignorar a primeira linha (cabeçalho)
            String[] dadosVeiculo = linha.split(";");
            String modelo = dadosVeiculo[0].trim().toLowerCase();
            String placa = dadosVeiculo[1].trim();
            Veiculo veiculoModelo = mapaModelos.get(modelo);
            if (veiculoModelo != null) {
                Veiculo veiculoComPlaca = new Veiculo(veiculoModelo);
                veiculoComPlaca.setPlaca(placa);
                mapaVeiculosPorPlaca.put(placa, veiculoComPlaca);
            } else {
                System.err.println("Warning: Modelo " + modelo + " não encontrado no mapa de modelos para placa: " + placa);
            }
        }
        return mapaVeiculosPorPlaca;
    }

    /**
     * Testa a leitura do arquivo CSV de modelos.
     * <p>
     * Este método lê o arquivo CSV de modelos e imprime seu conteúdo no console.
     */
    public static void testLeituraModelosCSV() {
        String modelos = "C:\\Users\\Pichau\\Desktop\\teste-logus\\csv\\modelos.csv";
        System.out.println("Modelos: ");
        System.out.println(LeitorDeArquivoCSV.lerArquivo(modelos));
    }

    /**
     * Testa a leitura do arquivo CSV de veículos.
     * <p>
     * Este método lê o arquivo CSV de veículos e imprime seu conteúdo no console.
     */
    public static void testLeituraVeiculosCSV() {
        String veiculos = "C:\\Users\\Pichau\\Desktop\\teste-logus\\csv\\veiculos.csv";
        System.out.println("Veiculos: ");
        System.out.println(LeitorDeArquivoCSV.lerArquivo(veiculos));
    }

    /**
     * Testa a criação de bombas de gasolina e etanol.
     * <p>
     * Este método cria uma bomba de gasolina e uma bomba de etanol e imprime suas informações no console.
     */
    public static void testLeituraBomba() {
        Bomba bomba_1_gasolina = new Bomba(new Combustivel(TipoCombustivel.GASOLINA), 10);
        System.out.println("Bomba 1 (Gasolina): ");
        System.out.println(bomba_1_gasolina);

        Bomba bomba_2_etanol = new Bomba(new Combustivel(TipoCombustivel.ETANOL), 12);
        System.out.println("Bomba 2 (Etanol): ");
        System.out.println(bomba_2_etanol);
    }

    /**
     * Testa a criação de um posto de gasolina.
     * <p>
     * Este método lê os arquivos CSV de modelos e veículos, cria um posto de gasolina com as bombas e os veículos,
     * e imprime as informações do posto no console.
     */
    public static void testLeituraPosto() {
        String modelos = "C:\\Users\\Pichau\\Desktop\\teste-logus\\csv\\modelos.csv";
        String veiculos = "C:\\Users\\Pichau\\Desktop\\teste-logus\\csv\\veiculos.csv";
        Bomba bomba_1_gasolina = new Bomba(new Combustivel(TipoCombustivel.GASOLINA), 10);
        Bomba bomba_2_etanol = new Bomba(new Combustivel(TipoCombustivel.ETANOL), 12);
        Queue<Veiculo> filaVeiculos = carregarVeiculos(modelos, veiculos);
        Posto posto = new Posto(bomba_1_gasolina, bomba_2_etanol, filaVeiculos);
        System.out.println("Posto: ");
        System.out.println(posto);
    }
}
