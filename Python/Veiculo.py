from Combustivel import TipoCombustivel


class Veiculo:
    PRECO_ETANOL_LITRO = 2.27
    PRECO_GASOLINA_LITRO = 2.90

    def __init__(self, modelo, km_por_litro_etanol="", km_por_litro_gasolina="", capacidade_tanque=""):
        self.modelo = modelo
        self.kmPorLitroEtanol = float(km_por_litro_etanol) if km_por_litro_etanol else 0.0
        self.kmPorLitroGasolina = float(km_por_litro_gasolina) if km_por_litro_gasolina else 0.0
        self.capacidadeTanque = float(capacidade_tanque) if capacidade_tanque else 0.0
        self.placa = ""

    def get_modelo(self):
        return self.modelo

    def get_placa(self):
        return self.placa

    def set_placa(self, placa):
        self.placa = placa

    def get_capacidade_tanque(self):
        return str(self.capacidadeTanque)

    def get_km_por_litro_etanol(self):
        return str(self.kmPorLitroEtanol)

    def get_km_por_litro_gasolina(self):
        return str(self.kmPorLitroGasolina)

    def decidir_combustivel(self):
        preco_por_km_etanol = self.PRECO_ETANOL_LITRO / self.kmPorLitroEtanol
        preco_por_km_gasolina = self.PRECO_GASOLINA_LITRO / self.kmPorLitroGasolina

        preco_total_etanol = preco_por_km_etanol * self.capacidadeTanque
        preco_total_gasolina = preco_por_km_gasolina * self.capacidadeTanque

        if preco_total_etanol < preco_total_gasolina:
            return TipoCombustivel.ETANOL
        else:
            return TipoCombustivel.GASOLINA

    def __str__(self):
        return f"Veiculo{{modelo='{self.modelo}', placa='{self.placa}', " \
               f"kmPorLitroEtanol='{self.kmPorLitroEtanol}', " \
               f"kmPorLitroGasolina='{self.kmPorLitroGasolina}', " \
               f"tanque='{self.capacidadeTanque}'}}"
