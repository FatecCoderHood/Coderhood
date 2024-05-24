# Documentação do Produto

## Backlog do Produto
- [Sprint 1:](#sprint-1)
  - [Carregar e validar CSV](#carregar-e-validar-csv)
  - [Relatório de valor médio](#relatório-de-valor-médio) 

- [Sprint 2:](#sprint-2)
  - [Relatório de valor médio](#relatório-de-valor-médio)
  - [Faixa de valores suspeitos configurável](#faixa-de-valores-suspeitos-configurável)
  - [Gerenciamento de cidades](#gerenciamento-de-cidades)
  - [Gerenciamento de estações](#gerenciamento-de-estações)
  - [Tratamento de registros suspeitos](#tratamento-de-registros-suspeitos)  

- [Sprint 3:](#sprint-3)
  - [Relatório de Situação](#Relatório-de-Situação)
  - [Dados BoxPlot](#dados-boxplot)
  - [Gerenciamento de unidades de mediade]
  - [Adicionado mais informações as estações]


## Entidade Relacionamento
![entidade-relacionamento](imagens/conceito_entidade-relacionamento.jpg)


## Sprint 1

### Carregar e validar CSV:

**História do usuário:**
Como meteorologista, eu quero inserir um CSV e adicionar os dados coletados de uma estação, afim de garantir que suas informações estejam corretas.

**Regras de negócio:**

1. **Inserção de CSV:**
   - Caso o nome do CSV selecionado esteja no padrão esperado e não exista o cadastro da cidade:
     - Será solicitado o nome da cidade para a sigla correspondente do nome do arquivo.
     - Será criado a estação se não existir.
     
   - A cidade já exista:
     - Será utilizada a cidade já cadastrada.

   - Caso o nome do CSV não esteja no padrão esperado:
     - Será solicitado o nome da cidade.
     - Será solicitada a sigla da cidade.
     - Será solicitado o número da estação.
   - A sigla da cidade já exista:
     - O nome registrado será utilizado.

2. **Tratamento de erros na inserção:**
   - Caso a estação já exista e esteja associada à outra cidade, Retornará um erro indicando que a estação está relacionada a uma cidade diferente.

**Interface do usuário:**

**Tela inicial:**

![Tela Inicial](imagens/image.png)

**Arquivo selecionado com sucesso:**

![Arquivo Selecionado](imagens/image-1.png)

**Após selecionar para salvar na base de dados:**

![Salvar na Base de Dados](imagens/image-2.png)

##


**Tratamento de erros na interface:**

**A estação inserida está relacionada com uma cidade diferente:**

![Erro de Estação](imagens/image-5.png)

**A sigla ainda não está associada a uma cidade:**

![Erro de Sigla](imagens/image-3.png)

**O nome do CSV não está no padrão esperado:**

![Erro de CSV](imagens/image-4.png)


### Relatório de valor médio:

**História do usuário:**
Como meteorologista, eu quero gerar um relatório de valores médios de uma cidade, a partir de uma data inicial e data final, com registros de hora em hora. Afim de prever as melhores condições para desenvolver as atividades do meu negócio.

**Regras de negócio:**

- Será solicitado uma cidade, uma data inicial e outra final.
- Será mostrado todos os registros desse periodo com periodicidade de uma hora.
- Dados suspeitos não apareceram.
     
**Interface do usuário:**

**Selecionar cidade e datas:**

![alt text](imagens/image-7.png)

**Tabela com registros:**

![alt text](imagens/image-8.png)




## Sprint 2

### Relatório de valor médio:

**História do usuário:**

Como pesquisador, eu quero relatórios de valor médio de cada váriavel climatica

**Regras de negócio:**
- Será selecionado uma cidade, data inicial e final, será retornado uma tabela com o valor médio para cada variavel com peridicidade de uma hora.
- Se houver registros para mesma data e hora mas com estações diferentes, deve ser feito uma média.

**Interface do usuário:**
![registro valor médio](imagens/image-20.png)

##

### Faixa de valores suspeitos configurável:

**História do usuário:**

Como pesquisador, eu quero alterar os valores minimos e máximos de cada tipo climático, afim de poder personalizar os filtros da leitura do CSV.

**Regras de negócio:**
- Alterar os valores minimos e máximos fará efeito somente nas novas leituras, registros já processados não serão válidados.

**Interface do usuário:**
![conf dados suspeitos em configurações](imagens/image-21.png)

##

### Gerenciamento de Cidades:

**História do usuário:**

Como pesquisador, eu quero gerenciar cidades, podendo assim escalar e manejar as cidades cadastradas.

**Regras de negócio:**
- Criar uma nova cidade.
- Deletar uma cidade.
- Não será possível repetir a sigla de uma cidade que já exista ao criar.
- Ao deletar será deletado todos as estações e registros ligado a essas cidades.

**Interface do usuário:**

![cidade-1](imagens/image-12.png)
![cidade-2](imagens/image-13.png)
![cidade-3](imagens/image-14.png)

##


### Gerenciamento de Estações:

**História do usuário:**
Como pesquisador, eu quero gerenciar estações, podendo assim escalar e manejar estações cadastradas.

**Regras de negócio:**
- Criar uma nova estação.
- Deletar uma estação.
- Não será possível repetir um número de estação que já exista ao criar.
- Ao deletar será deletado todos os registros ligados a essa estação.

**Interface do usuário:**

 ![estacao-1](imagens/image-9.png)

 ![estacao-2](imagens/image-10.png)

![estaco-3](imagens/image-11.png)

##

### Tratamento de Registros Suspeitos:

**História do usuário:**
Como pesquisador, eu quero gerenciar registros que contêm valores suspeitos, afim de poder revisar os registros identificados como suspeitos ou deletá-los.

**Regras de negócio:**
 - Ao alterar o valor suspeito para um valor dentro do estipulado nas configurações, ele será salvo.
 - Ao alterar o valor suspeito para um valor fora do estipulado nas configurações, será retornado um erro.
 - Ao deletar o registro com valor suspeito, os demais registros relacionados serão deletados.

 **Interface do usuário:**

![suspeito-1](imagens/image-15.png)
![suspeito-2](imagens/image-16.png)
![suspeito-3](imagens/image-17.png)


##

## Sprint 3

### Relatório de Situação:

**História do usuário:**
Como pesquisador, posso visualizar relatório de situação, afim de verificar as últimas medidas de cada cidade.

**Regras de negócio:**
- Ao selecionar a aba de *Situação* será visualizado as últimas medidas de cada cada.


 **Interface do usuário:**
 
 ![Aba Relatório Situação](imagens/image-18.png)

##

 ### Dados BoxPlot:

**História do usuário:**
Como pesquisador, posso gerar dados necessários para a criação de um gráfico boxplot, para entender melhor as variações e tendências climáticas.


**Regras de negócio:**
- Deve ser selecionado uma estação, uma data inicial eu uma data final.
- Será gerado para cada váriavel climatica("Temperatura Média", "Umidade Média", "Velocidade do Vento", "Direção do Vento", "Chuva"): 
  - Mínimo
  - Primeiro quartil
  - Mediana
  - Terceiro quartil
  - Máximo

- Será possível exportar em formato excel(.xlsx).


 **Interface do usuário:**
 
![Aba Dados BoxPlot](imagens/image-19.png)
