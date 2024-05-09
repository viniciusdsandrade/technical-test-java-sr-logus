from Bomba import Bomba
from Combustivel import Combustivel
from Combustivel import TipoCombustivel
from EscritoDeArquivoCSV import EscritorDeArquivoCSV
from LeitorDeArquivoCSV import LeitorDeArquivoCSV
from Posto import Posto
from Veiculo import Veiculo


class Teste:
    def __init__(self):
        pass


def main():
    # Paths to the CSV files
    caminho_modelos = "C:\\Users\\vinic\\OneDrive\\Área de Trabalho\\teste-tecnico-logus\\Python\\csv\\modelos.csv"
    caminho_veiculos = "C:\\Users\\vinic\\OneDrive\\Área de Trabalho\\teste-tecnico-logus\\Python\\csv\\veiculos.csv"
    caminho_relatorio = "C:\\Users\\vinic\\OneDrive\\Área de Trabalho\\teste-tecnico-logus\\Python\\csv\\teste.csv"

    # Instantiating gasoline and ethanol pumps at the station
    bomba_1_gasolina = Bomba(Combustivel(TipoCombustivel.GASOLINA), 10)
    bomba_2_etanol = Bomba(Combustivel(TipoCombustivel.ETANOL), 12)

    fila_veiculos = Teste.carregar_veiculos(caminho_modelos, caminho_veiculos)  # Load the vehicles from the CSV file

    # Creating a gas station
    posto = Posto(bomba_1_gasolina, bomba_2_etanol, fila_veiculos)

    # Refuelling the vehicles and generating the report
    relatorio = posto.abastecer_veiculos()

    print("Relatório: ")
    print(relatorio)

    # Generating the report in a CSV file
    EscritorDeArquivoCSV.salvar_relatorio_csv(caminho_relatorio, relatorio)


if __name__ == "__main__":
    main()
