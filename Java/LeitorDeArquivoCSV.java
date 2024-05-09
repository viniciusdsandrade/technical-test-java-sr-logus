import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A classe 'LeitorDeArquivoCSV' fornece uma maneira conveniente de ler o conteúdo
 * de um arquivo CSV em uma string, que pode ser posteriormente processada por seu
 * aplicativo. Esta classe pode lidar com arquivos CSV padrão que usam vírgulas (",")
 * como delimitadores e aspas duplas (") como caracteres de aspas.
 *
 * <p> Esta classe não é thread-safe.
 */
public class LeitorDeArquivoCSV {

    /**
     * Lê o conteúdo de um arquivo CSV e o retorna como uma única string. Cada linha
     * do arquivo CSV será separada por um caractere de nova linha ("\n").
     *
     * @param caminhoArquivo O caminho para o arquivo CSV a ser lido.
     * @return Uma string contendo o conteúdo do arquivo CSV, ou uma string vazia
     * se o arquivo não existir ou não puder ser lido.
     * @Example: String conteudoCSV = LeitorDeArquivoCSV.lerArquivo("caminho/para/meu_arquivo.csv");
     */
    public static String lerArquivo(String caminhoArquivo) {
        StringBuilder conteudoArquivo = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null)
                conteudoArquivo.append(linha).append("\n");
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return conteudoArquivo.toString();
    }
}
