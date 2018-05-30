# 
# Análise entre AG Binário e o AG Real
#

# Leitura obrigatória: 
# Prof. Felipe Campelo, Ph.D. UFMG
# https://github.com/fcampelo/Design-and-Analysis-of-Experiments

# Limpando o ambiente de trabalho
rm(list = ls())

# Definir a pasta de trabalho
# setwd("")
#"C:\\Users\\wagne\\OneDrive\\Wagner - UFOP\\Nono Per�?odo\\Computação Evolucionária\\Analise\\dadosDEReal.txt"
# Ler o arquivo com os dados
dados <- read.csv2("D:\\OneDrive\\Wagner - UFOP\\Nono Periodo\\Computa��o Evolucion�ria\\Analise\\dadosESReal.txt")
# Transformação da coluna com os números com E - isso depende da base de dados
dados$FO <- as.numeric(as.character.numeric_version(dados$FO))
dados$FOPior <- as.numeric(as.character.numeric_version(dados$FOPior))

### Teste preliminares 

# Carregando biblioteca
library(plyr)
# Contanto o número de observações de acordo com o teste
count(dados, c("Teste"))

# Menor valor
minimo <- min(dados$FO)
# Maior valor
maximo <- max(dados$FO)

# Menor valor
minimoFOPior <- min(dados$FOPior)
# Maior valor
maximoFOPior <- max(dados$FOPior)


# Desvio padrão
desvioTotal <- sd(dados$FO)
desvioReal1 <- sd(dados$FO[dados$Teste == "ESREAL1"])
desvioReal2 <- sd(dados$FO[dados$Teste == "ESREAL2"])
# Média
media <- mean(dados$FO)

# Plot do valor em função do teste
boxplot(FO~Teste, data=dados)

### Análise estat�?stica - dependendo do caso, pode ser aplicado o Teste T ou a AOV.
# As técnicas são aplicáveis quando as premissas de normalidade são válidas.
# Aqui, as premissão são assumidas.

# Separação dos valores conforme o método
 
real1 <- dados$FO[dados$Teste == "ESREAL1"]
real2 <- dados$FO[dados$Teste == "ESREAL2"]

# Menor valor
minimoReal1 <- min(dados$FO[dados$Teste == "ESREAL1"])
# Maior valor
maximoReal1 <- max(dados$FO[dados$Teste == "ESREAL1"])

# Menor valor
minimoReal2 <- min(dados$FO[dados$Teste == "ESREAL2"])
# Maior valor
maximoReal2 <- max(dados$FO[dados$Teste == "ESREAL2"])

 
# Maior valor
PiorGeradoReal1 <- max(dados$FOPior[dados$Teste == "ESREAL1"])

 
# Maior valor
PiorGeradoReal2 <- max(dados$FOPior[dados$Teste == "ESREAL2"])
 

## Teste T para os métodos separados
# http://www.portalaction.com.br/inferencia/52-teste-para-media-teste-t
# Podem haver implicações estat�?sticas da maneira que isso é verificado.

# Media1 != Media2
 
t.test(real1, real2)
 
# Media1 < Media2
 
t.test(real1, real2, alternative = 'l')
 

# Media1 > Media2
 
t.test(real1, real2, alternative = 'g')
 

## Análise de variância
# http://www.portalaction.com.br/anova
modelo <- aov(FO~Teste, data=dados)
# Exibição do resultado do teste
summary(modelo)

## Comparações múltiplas
# Teste de Tukey (TSD - Tukey Significant Difference)
# http://www.portalaction.com.br/anova/31-teste-de-tukey
require(multcomp)
modelo.Tukey <- glht(modelo, linfct=mcp(Teste="Tukey"))
par(mar = c(2,8,2,2), mfrow = c(1,1))
plot(modelo.Tukey)