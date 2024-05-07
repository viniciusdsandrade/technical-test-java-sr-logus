import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A classe {@code EscritorDeArquivoCSV} contém métodos para salvar relatórios em formato CSV.
 * Esta classe demonstra o uso de {@code BufferedWriter} para escrever texto em um arquivo.
 */
public class EscritorDeArquivoCSV {

    /**
     * Salva um relatório em formato CSV no caminho do arquivo especificado.
     *<p>
     * Este método utiliza um {@code BufferedWriter} para escrever o conteúdo do relatório
     * no arquivo especificado pelo caminho {@code caminhoArquivo}. Se ocorrer um erro durante
     * a escrita do arquivo, uma mensagem de erro será impressa no console de erro padrão.
     *
     * @param caminhoArquivo O caminho do arquivo onde o relatório será salvo.
     * @param relatorio O conteúdo do relatório a ser salvo no arquivo.
     */
    public static void salvarRelatorioCSV(String caminhoArquivo, String relatorio) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            writer.write(relatorio);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o relatório: " + e.getMessage());
        }
    }
}
