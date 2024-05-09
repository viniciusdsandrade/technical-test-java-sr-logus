from Combustivel import TipoCombustivel


class Bomba:
    total_gasolina = 0
    total_etanol = 0

    def __init__(self, combustivel, velocidade_abastecimento):
        self.combustivel = combustivel
        self.velocidade_abastecimento = velocidade_abastecimento
        self.total_abastecido = 0

    def get_velocidade_abastecimento(self):
        return self.velocidade_abastecimento

    def get_combustivel(self):
        return self.combustivel

    def get_total_abastecido(self):
        return self.total_abastecido

    def abastecer(self, veiculo):
        litros_abastecidos = float(veiculo.get_capacidade_tanque())  # Supondo que o tanque esteja vazio
        self.total_abastecido += litros_abastecidos
        if self.combustivel.get_tipo_combustivel() == TipoCombustivel.GASOLINA:
            Bomba.total_gasolina += litros_abastecidos
        elif self.combustivel.get_tipo_combustivel() == TipoCombustivel.ETANOL:
            Bomba.total_etanol += litros_abastecidos
        return litros_abastecidos

    def __str__(self):
        return f"Bomba {{\n\tcombustivel = {self.combustivel},\n\tvelocidade_abastecimento = {self.velocidade_abastecimento}\n}}"
