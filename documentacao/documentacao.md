# Documentação Zeus

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

- [Sprint 3:](#sprint-3) Todos os requisitos funcionais foram entregues ✅
  - [Relatório de Situação](#relatório-de-situação)
  - [Dados BoxPlot](#dados-boxplot)
  - [Gerenciamento de unidades de medida](#gerenciamento-de-unidades-de-medida)
  - [Adicionado mais informações às estações](#adicionado-mais-informações-às-estações)
  - [Retirado a obrigatoriedade do nome da cidade](#retirado-a-obrigatoriedade-do-nome-da-cidade)

- [Sprint 4:](#sprint-4)
  - [Otimização do banco](#otimização-do-banco)
  - [Otimização de código](#otimização-de-código)
  - [Testes de Unidade](#testes-de-unidade)
  - [Manual do Usuário](#manual-de-usuário)
  - [Ajuste Relatório Situação](#ajuste-na-exibição-dos-dados-do-relatório-de-situação)
  - [Exportação CSV Relatório Situação e Valor Médio](#exportação-de-csv-para-relatório-de-situação-e-relatório-de-valor-médio)

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
- Clicar no botão para selecionar o arquivo CSV.

![Tela Inicial](../documentacao/imagens/02%20-%20Upload%20CSV/00%20Upload%20CSV.png)

**Arquivo selecionado com sucesso:**
- Serão exibidos: o nome da cidade, a estação processada, quantos registros foram feitos, e se existem registros suspeitos.

![Arquivo Selecionado](./imagens/02%20-%20Upload%20CSV/02%20Upload%20CSV.png)

**Após arquivo selecionado, clique em salvar para que o arquivo seja adicionado à base de dados:**

- Será exibido um alerta de confirmação com as informações do registro.

![Salvar na Base de Dados](./imagens/02%20-%20Upload%20CSV/03%20Upload%20CSV.png)

##


**Tratamento de erros na interface:**

**A estação inserida está relacionada com uma cidade diferente:**

![Erro de Estação](../documentacao/imagens/05%20-%20Gerenciamento%20de%20Cidade/02%20Gerenciamento%20de%20Cidade.png)

**A sigla ainda não está associada a uma cidade:**

![Erro de Sigla](/documentacao/imagens/02%20-%20Upload%20CSV/01%20Upload%20CSV.png)

**O nome do CSV não está no padrão esperado:**

![Erro de CSV](./imagens/02%20-%20Upload%20CSV/04%20Upload%20CSV.png)


### Relatório de valor médio:

**História do usuário:**
Como meteorologista, eu quero gerar um relatório de valores médios de uma cidade, a partir de uma data inicial e data final, com registros de hora em hora. Afim de prever as melhores condições para desenvolver as atividades do meu negócio.

**Regras de negócio:**

- Será solicitado uma cidade, uma data inicial e outra final.
- Será mostrado todos os registros desse periodo com periodicidade de uma hora.
- Dados suspeitos não apareceram.
     
**Interface do usuário:**

**Selecionar cidade e datas:**

![alt text](./imagens/04%20-%20Relatorio%20Valor%20Medio/03%20Relatorio%20Valor%20Medio.png)

**Tabela com registros:**

![alt text](./imagens/04%20-%20Relatorio%20Valor%20Medio/04%20Relatorio%20Valor%20Medio.png)




## Sprint 2

### Relatório de valor médio:

**História do usuário:**

Como pesquisador, eu quero relatórios de valor médio de cada váriavel climatica

**Regras de negócio:**
- Será selecionado uma cidade, data inicial e final, será retornado uma tabela com o valor médio para cada variavel com peridicidade de uma hora.
- Se houver registros para mesma data e hora mas com estações diferentes, deve ser feito uma média.

**Interface do usuário:**
![registro valor médio](imagens/04%20-%20Relatorio%20Valor%20Medio/08%20Relatorio%20Valor%20Medio.png)

##

### Faixa de valores suspeitos configurável:

**História do usuário:**

Como pesquisador, eu quero alterar os valores minimos e máximos de cada tipo climático, afim de poder personalizar os filtros da leitura do CSV.

**Regras de negócio:**
- Alterar os valores minimos e máximos fará efeito somente nas novas leituras, registros já processados não serão válidados.

**Interface do usuário:**
![conf dados suspeitos em configurações](imagens/04%20-%20Relatorio%20Valor%20Medio/09%20Relatorio%20Valor%20Medio.png)

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

![Gerenciamento de Cidade](imagens/05%20-%20Gerenciamento%20de%20Cidade/01%20Gerenciamento%20de%20Cidade.png)<br/><br/>
![Gerenciamento de Cidade](imagens/05%20-%20Gerenciamento%20de%20Cidade/02%20Gerenciamento%20de%20Cidade.png)<br/><br/>
![Gerenciamento de Cidade](imagens/05%20-%20Gerenciamento%20de%20Cidade/04%20Gerenciamento%20de%20Cidade.png)<br/><br/>

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

 ![Gerenciamento de Estação](imagens/06%20-%20Gerenciamento%20de%20Estação/01%20Gerenciamento%20de%20Estacao.png)

 ![Gerenciamento de Estação](imagens/06%20-%20Gerenciamento%20de%20Estação/03%20Gerenciamento%20de%20Estacao.png)

##

### Tratamento de Registros Suspeitos:

**História do usuário:**
Como pesquisador, eu quero gerenciar registros que contêm valores suspeitos, afim de poder revisar os registros identificados como suspeitos ou deletá-los.

**Regras de negócio:**
 - Ao alterar o valor suspeito para um valor dentro do estipulado nas configurações, ele será salvo.
 - Ao alterar o valor suspeito para um valor fora do estipulado nas configurações, será retornado um erro.
 - Ao deletar o registro com valor suspeito, os demais registros relacionados serão deletados.

 **Interface do usuário:**

![Registro suspeito](./imagens/07%20-%20Registros%20Suspeitos/00%20-%20Registros%20Suspeitos.png)
![Registro suspeito](imagens/07%20-%20Registros%20Suspeitos/01%20-%20Registros%20Suspeitos.png)
![Registro suspeito](imagens/07%20-%20Registros%20Suspeitos/02%20-%20Registros%20Suspeitos.png)


##

## Sprint 3

### Relatório de Situação:

**História do usuário:**
Como pesquisador, posso visualizar relatório de situação, afim de verificar as últimas medidas de cada cidade.

**Regras de negócio:**
- Ao selecionar a aba de *Situação* será visualizado as últimas medidas de cada cada.


 **Interface do usuário:**
 
 ![Aba Relatório Situação](./imagens/03%20-%20Relatorio%20Situação/00%20Relatorio%20Situação.jpeg)

##

 ### Dados BoxPlot:

**História do usuário:**
Como pesquisador, posso gerar dados necessários para a criação de um gráfico boxplot, para entender melhor as variações e tendências climáticas.


**Regras de negócio:**
- Deve ser selecionado uma estaçãoe uma estação.
- Será gerado para cada váriavel climatica("Temperatura Média", "Umidade Média", "Velocidade do Vento", "Direção do Vento", "Chuva"):

  - Mínimo
  - Primeiro quartil
  - Mediana
  - Terceiro quartil
  - Máximo


- Será possível exportar em formato CSV.


 **Interface do usuário:**
 
![Dados BoxPlot](./imagens/08%20-%20Relatório%20Box%20Plot/04%20-%20Relatório%20Box%20Plot.png)

 **CSV exportado:**

![csv e boxplot](./imagens/08%20-%20Relatório%20Box%20Plot/11%20-%20Relatório%20Box%20Plot.jpg)



  ### Gerenciamento de unidades de medida:

**História do usuário:**
Como pesquisador, quero poder informar o nome das unidades e exibir suas formulas de conversão de unidade, afim de manter a congiuração clara a todos os usuários.


**Regras de negócio:**
- Deve ser possível informar para cada variavel climática:
  - Unidade de medida
  - Descrição da conversão
  - Formula de conversão


 **Interface do usuário:**

![Configuracoes tipo climatico](./imagens/01%20-%20Configuração%20Zeus/02%20Configuração%20Zeus.png)



  ### Adicionado mais informações às estações:

  **História do usuário:**
Como pesquisador, quero poder adicionar dados adicionais aos extraidos do CSV, afim de identificar cada estação pela localização ou pelo nome.


**Regras de negócio:**
- Deve ser possível informar para cada variavel climática:
  - Unidade de medida
  - Descrição da conversão
  - Formula de conversão

 **Interface do usuário:**

![estacao com informacoes adicionais](./imagens/06%20-%20Gerenciamento%20de%20Estação/04%20Gerenciamento%20de%20Estacao.png)



  ### Retirado a obrigatoriedade do nome da cidade:

  **Regras de negócio:**
  - Somente a sigla é exigida ao se criar uma cidade.


  ### Sprint 4:

#### Otimização do banco
  Melhorias na consulta SQL, estrutura do controlador de tela e fluxos de lógica.

#### Otimização de código:
Revisão do código para identificar áreas que podem ser otimizadas. Isso inclui refatoração para melhor legibilidade, melhor desempenho e manutenibilidade.


 - Otimizado a estrutura do Relatório Box Plot do Controller e do Model.
 - Otimizado a estrutura do Relatório Valor Médio do Controller e do Model.
 - Otimizado a estrutura do Relatório de Valor Médio SQL.


#### Testes de Unidade:
Para garantir a qualidade do código, estaremos escrevendo e executando Testes de Unidade. Isso nos ajudará a identificar e corrigir bugs antes que eles cheguem ao ambiente de produção.

- Construído Testes de Unidade para as funcionalidades da camada de serviço:
  
  - Csv Resolve
  - Leitor de CSV Service
  - Estação Service
  - Cidade Service
  - Situação Service
  - Suspeito Service
  - Valor Médio Service
  - Box Plot Service
  - Variável Climática Service

  ![Testes Jacoco 1](./imagens/09%20-%20Testes/Jacoco%201.jpg)

  ![Testes Jacoco 2](./imagens/09%20-%20Testes/Jacoco%202.jpg)

#### Manual de Usuário:
Visando garantir a melhor utilização do Zeus pelo usuário, construimos um manual de instruções, com um direcionamento claro e objetivo com os detalhes de cada funcionalidade.

 - [Manual Zeus](./manual%20Zeus/manual%20Zeus.md)

#### Ajuste na exibição dos dados do Relatório de Situação.
Alterado exibição da data e hora da coleta de cada dado. Agora, o pesquisador encontra a data e hora da coleta do registro descansando o mouse em cima do dado.

![Relatório Situção](./imagens/03%20-%20Relatorio%20Situação/01%20Relatorio%20Situação.png)

#### Exportação de CSV para Relatório de Situação e Relatório de Valor Médio.
Criado a possibilidade de exportação de arquivo CSV com os dados do Relatório de Situação e Relatório de Valor Médio.

- Exportando Relatório de Situação:

![Reltório Situação CSV](./imagens/03%20-%20Relatorio%20Situação/03%20Relatorio%20Situação.png)

- Exportando Relatório de Valor Médio:

![Relatório Valor Médio CSV](./imagens/04%20-%20Relatorio%20Valor%20Medio/06%20Relatorio%20Valor%20Medio.png)



