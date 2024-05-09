import csv


class EscritorDeArquivoCSV:

    @staticmethod
    def salvar_relatorio_csv(caminho_arquivo, relatorio):
        try:
            with open(caminho_arquivo, 'w', newline='') as arquivo_csv:
                escritor_csv = csv.writer(arquivo_csv)
                escritor_csv.writerows(relatorio)  # Assumindo que 'relatorio' é uma lista de listas
        except IOError as e:
            print(f"Erro ao salvar o relatório: {e}")
