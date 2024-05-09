import csv


class LeitorDeArquivoCSV:

    @staticmethod
    def ler_arquivo(caminho_arquivo):
        conteudo_arquivo = []
        try:
            with open(caminho_arquivo, 'r', newline='') as arquivo_csv:
                leitor_csv = csv.reader(arquivo_csv)
                for linha in leitor_csv:
                    conteudo_arquivo.append(linha)
        except IOError as e:
            print(f"Erro ao ler o arquivo: {e}")
        return conteudo_arquivo
