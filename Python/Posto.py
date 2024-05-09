from Combustivel import TipoCombustivel
from datetime import datetime
from Bomba import Bomba


class Posto:
    def __init__(self, bomba_gasolina, bomba_etanol, fila_veiculos):
        self.bomba_gasolina = bomba_gasolina
        self.bomba_etanol = bomba_etanol
        self.fila_veiculos = fila_veiculos

    def abastecer_veiculos(self):
        relatorio = "Resultado da simulação\n"
        relatorio += "-----------------------------------------------------------------------------------------------------\n"
        while self.fila_veiculos:  # Verifica se a lista não está vazia
            veiculo = self.remover_veiculo()
            bomba = self.escolher_bomba(veiculo)
            litros_abastecidos = bomba.abastecer(veiculo)

            # Obter a hora atual e formatá-la como HH:mm:ss
            hora_atual = datetime.now().strftime("%H:%M:%S")

            relatorio += f"[{hora_atual}] Veículo modelo {veiculo.get_modelo()} Placa: {veiculo.get_placa()} foi abastecido com {litros_abastecidos:.2f} litros de {bomba.get_combustivel().get_tipo_combustivel()}\n"

        relatorio += "\nResumo da simulação\n"
        relatorio += f"* Total abastecido na bomba 1 GASOLINA  {self.bomba_gasolina.get_total_abastecido():.2f} litros\n"
        relatorio += f"* Total abastecido na bomba 2 ETANOL    {self.bomba_etanol.get_total_abastecido():.2f} litros\n"
        relatorio += f"* Total abastecido            GASOLINA  {Bomba.total_gasolina:.2f} litros\n"
        relatorio += f"* Total abastecido            ETANOL    {Bomba.total_etanol:.2f} litros\n"
        relatorio += "-----------------------------------------------------------------------------------------------------\n"

        return relatorio

    def escolher_bomba(self, veiculo):
        tipo_combustivel = veiculo.decidir_combustivel()
        if tipo_combustivel == TipoCombustivel.GASOLINA:
            return self.bomba_gasolina
        else:
            return self.bomba_etanol

    def remover_veiculo(self):
        # Alterado para remover e retornar o primeiro elemento da lista
        return self.fila_veiculos.pop(0)

    def __str__(self):
        sb = "Posto {\n"
        sb += f"\tbomba_gasolina = {self.bomba_gasolina},\n"
        sb += f"\tbomba_etanol = {self.bomba_etanol},\n"
        sb += "\tfila_veiculos = [\n"
        for veiculo in self.fila_veiculos:  # Alterado para iterar diretamente sobre a lista
            sb += f"\t\t{veiculo},\n"
        sb += "\t]\n"
        sb += '}'
        return sb
