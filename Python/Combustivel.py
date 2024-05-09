from enum import Enum


class Combustivel:
    PRECO_ETANOL_LITRO = 2.27
    PRECO_GASOLINA_LITRO = 2.90

    def __init__(self, tipo_combustivel):
        self.tipo_combustivel = tipo_combustivel
        self.preco_litro = self.get_preco_litro()

    def get_preco_litro(self):
        if self.tipo_combustivel == TipoCombustivel.ETANOL:
            return Combustivel.PRECO_ETANOL_LITRO
        else:
            return Combustivel.PRECO_GASOLINA_LITRO

    def get_tipo_combustivel(self):
        return self.tipo_combustivel

    def __str__(self):
        return f"Combustivel {{\n\ttipo_combustivel = {self.tipo_combustivel},\n\tpreco_litro = {self.preco_litro}\n}}"


class TipoCombustivel(Enum):
    GASOLINA = 1
    ETANOL = 2
