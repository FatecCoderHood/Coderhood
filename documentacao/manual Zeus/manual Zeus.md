# Manual Zeus

Manual produzido pela equipe Coderhood - FATEC São José dos Campos - Prof. Jessen Vidal

Qualquer dúvida, entre em contato pelo telefone (12) 98242-7304.

- [Instalação do Java 17.0.10](#instalação-do-java-17010)
- [Instalação e configuração do PostgreSQL 16.3](#instalação-e-configuração-do-postgresql-163)
- [Configuração do Zeus](#configuração-do-zeus)
- [Upload do Arquivo CSV](#upload-do-arquivo-csv)
- [Relatório de Situação](#relatório-de-situação)
- [Relatório de Valor Médio](#relatório-de-valor-médio)
- [Gerenciamento de Cidades](#gerenciamento-de-cidades)
- [Gerenciamento de Estações](#gerenciamento-de-estações)
- [Gerenciamento de Dados Suspeitos](#gerenciamento-de-dados-suspeitos)
- [Relatório Boxplot](#relatório-boxplot)

## Instalação do Java 17.0.10

Para baixar o Java 17.0.10, visite o seguinte link: [Download Java 17.0.10](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

## Instalação e configuração do PostgreSQL 16.3

Para baixar e configurar o PostgreSQL 16.3, siga estas etapas:

1. Visite o seguinte link: [Download PostgreSQL 16.3](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads)

2. Selecione as seguintes opções:

![opções](../imagens/00%20-%20Instalação%20PostgreSQL/00%20Instalação%20PostgreSQL.png)

3. Senha padrão: `root`

![senha](../imagens/00%20-%20Instalação%20PostgreSQL/01%20Instalação%20PostgreSQL.png)

4. Porta: `5432`

![porta postgre](../imagens/00%20-%20Instalação%20PostgreSQL/02%20Instalação%20PostgreSQL.png)

5. Locale: `Default locale`

![locale](../imagens/00%20-%20Instalação%20PostgreSQL/03%20Instalação%20PostgreSQL.png)

Se o PostgreSQL não for instalado corretamente, um erro será exibido:

![erro de conexão](../imagens/00%20-%20Instalação%20PostgreSQL/04%20Instalação%20PostgreSQL.png)

## Configuração do Zeus

Ao iniciar o Zeus pela primeira vez, um erro será exibido, pois é necessário definir a faixa de valores para carregar o CSV:

![erro de faixa de valores](../imagens/01%20-%20Configuração%20Zeus/00%20Configuração%20Zeus.png)

Para definir a faixa de valores, vá até a aba de configurações:

![aba de configurações](../imagens/01%20-%20Configuração%20Zeus/01%20Configuração%20Zeus.png)

Insira os valores limites para cada unidade de medida nas duas primeiras colunas:

![aba de configurações](../imagens/01%20-%20Configuração%20Zeus/03%20Configuração%20Zeus.png)

As configurações do tipo climático podem ser alteradas conforme a intenção do usuário. Existe também a possibilidade de converter os dados gerados inserindo o nome da unidade de medida, uma descrição da conversão e a fórmula de conversão.

![aba de configurações](../imagens/01%20-%20Configuração%20Zeus/04%20Configuração%20Zeus.png)


## Upload do Arquivo CSV

Para fazer upload do arquivo CSV, execute o software e vá até a aba de Inserir Dados. Ao clicar no botão "Selecionar Arquivo", abrirá uma janela de busca. Selecione o arquivo CSV que você deseja fazer upload e clique em "Salvar".

![Upload CSV](../imagens/02%20-%20Upload%20CSV/00%20Upload%20CSV.png)

Caso esse arquivo possua uma cidade sem que nenhuma sigla esteja relacionada a ela, aparecerá uma janela pedindo para cadastrar a sigla.

![Upload CSV](../imagens/02%20-%20Upload%20CSV/01%20Upload%20CSV.png)

Se o upload for bem-sucedido, será exibida uma mensagem de sucesso.

![Upload CSV](../imagens/02%20-%20Upload%20CSV/02%20Upload%20CSV.png)


## Relatório de Situação

Para ter acesso ao Relatório de Situação, selecione a aba Situação. Na tela será exibida uma tabela com os últimos dados das cidades cadastradas no banco de dados.

![Situacao](../imagens/03%20-%20Relatorio%20Situação/00%20Relatorio%20Situação.jpeg)

Para saber a data e a hora do registro, o usuário deve posicionar o cursor do mouse sobre o dado.

![Situacao](../imagens/03%20-%20Relatorio%20Situação/01%20Relatorio%20Situação.png)

Caso o usuário deseje, ele pode exportar um arquivo CSV com os dados clicando no botão exportar. Uma pop-up será exibida indicando onde o arquivo foi salvo.

![Situacao](../imagens/03%20-%20Relatorio%20Situação/03%20Relatorio%20Situação.png)

## Relatório de Valor Médio

Para ter acesso ao Relatório de Valor Médio, selecione a aba Valor Médio. Clique em selecionar para receber uma lista com as cidades contidas no banco de dados.

![ValorMedio](../imagens/04%20-%20Relatorio%20Valor%20Medio/00%20Relatorio%20Valor%20Medio.png)

Após escolher a cidade, o usuário devera escolher a data inicial:

![ValorMedio](../imagens/04%20-%20Relatorio%20Valor%20Medio/01%20Relatorio%20Valor%20Medio.png)

E a data final:

![Valor Medio](../imagens/04%20-%20Relatorio%20Valor%20Medio/02%20Relatorio%20Valor%20Medio.png)

Após a escolha do periodo, será exibido o botão executar.

![ValorMedio](../imagens/04%20-%20Relatorio%20Valor%20Medio/03%20Relatorio%20Valor%20Medio.png)

Ao clicar o botão de executar a tabela de Valor Médio será exibido na tela.

![Valor Medio](../imagens/04%20-%20Relatorio%20Valor%20Medio/04%20Relatorio%20Valor%20Medio.png)


Caso o usuário deseje, ele pode exportar um arquivo CSV com os dados clicando no botão exportar. Uma pop-up será exibida indicando onde o arquivo foi salvo.

![Valor Medio](../imagens/04%20-%20Relatorio%20Valor%20Medio/07%20-%20Relatorio%20Valor%20Medio.png)


## Gerenciamento de Cidades

Para ter acesso ao Gerenciamento de Cidades, selecione a aba Cidade. Na tela inicial, será exibida uma lista com as cidades contidas no banco de dados.

![Gerenciamento de Cidades](../imagens/05%20-%20Gerenciamento%20de%20Cidade/00%20Gerenciamento%20de%20Cidade.png)

Para adicionar uma cidade, clique em adicionar cidade e insira um nome e uma sigla para a nova cidade.

![Gerenciamento de Cidades](../imagens/05%20-%20Gerenciamento%20de%20Cidade/01%20Gerenciamento%20de%20Cidade.png)

Ao cadastrar uma sigla que já está cadastrada, aparecerá uma mensagem.

![Gerenciamento de Cidades](../imagens/05%20-%20Gerenciamento%20de%20Cidade/02%20Gerenciamento%20de%20Cidade.png)

Para editar, clique duas vezes no nome da cidade pretendida e uma caixa de texto aparecerá para que o usuário possa inserir um novo nome.

![Gerenciamento de Cidades](../imagens/05%20-%20Gerenciamento%20de%20Cidade/03%20Gerenciamento%20de%20Cidade.png)

Para deletar uma cidade, selecione a cidade e clique em deletar. Aparecerá uma mensagem de confirmação.

![Gerenciamento de Cidades](../imagens/05%20-%20Gerenciamento%20de%20Cidade/04%20Gerenciamento%20de%20Cidade.png)

## Gerenciamento de Estações

Para ter acesso ao Gerenciamento de Estações, selecione a aba Estações. Na tela inicial, será exibida uma lista com as estações contidas no banco de dados.

![Gerenciamento de Estacoes](../imagens/06%20-%20Gerenciamento%20de%20Estação/00%20Gerenciamento%20de%20Estacao.png)

Para adicionar uma estação, clique no botão de Adicionar Nova Estação:

![Gerenciamento de Estacoes](../imagens/06%20-%20Gerenciamento%20de%20Estação/01%20Gerenciamento%20de%20Estacao.png)

Para editar nome, descrição, latitude ou longitude da estação, clique duas vezes para habilitar a funcionalidade e aperte enter para inserir os dados.

![Gerenciamento de Estacoes](../imagens/06%20-%20Gerenciamento%20de%20Estação/02%20Gerenciamento%20de%20Estacao.png)

Para deletar uma estação, clique em deletar estação, e aparecerá uma mensagem de confirmação.

![Gerenciamento de Estacoes](../imagens/06%20-%20Gerenciamento%20de%20Estação/03%20Gerenciamento%20de%20Estacao.png)

## Gerenciamento de Dados Suspeitos

Para ter acesso ao Gerenciamento de Dados Suspeitos, selecione a aba Registros Suspeitos. Na tela inicial, serão exibidos os dados que estiverem acima ou abaixo dos parâmetros estipulados inicialmente.

![Dados Suspeitos](../imagens/07%20-%20Registros%20Suspeitos/00%20-%20Registros%20Suspeitos.png)

Caso o usuário deseje, ele pode editar um dado suspeito. Ao clicar no botão, será exibida uma janela onde ele poderá inserir os novos dados.

![Dados Suspeitos](../imagens/07%20-%20Registros%20Suspeitos/01%20-%20Registros%20Suspeitos.png)

Ele também tem a opção de excluir aquele registro.

![Dados Suspeitos](../imagens/07%20-%20Registros%20Suspeitos/02%20-%20Registros%20Suspeitos.png)

## Relatório Box Plot

Para ter acesso ao Relatório Boxplot, selecione a aba Boxplot. Ao clicar na aba Boxplot, o usuário terá acesso à página inicial.

![BoxPlot](../imagens/08%20-%20Relatório%20Box%20Plot/00%20-%20Relatório%20Box%20Plot.png)

O usuário pode clicar no botão de seleção de estação e escolher na lista suspensa qual estação desejar.

![BoxPlot](../imagens/08%20-%20Relatório%20Box%20Plot/01%20-%20Relatório%20Box%20Plot.png)

Depois, pode escolher o período desejado para pesquisa.

![BoxPlot](../imagens/08%20-%20Relatório%20Box%20Plot/02%20-%20Relatório%20Box%20Plot.png)

Após escolher o período específico, aparecerá o botão de executar..

![BoxPlot](../imagens/08%20-%20Relatório%20Box%20Plot/03%20-%20Relatório%20Box%20Plot.png)

Ao apertar o botão de executar, aparecerão as informações referentes ao período selecionado. Após essa ação, também será exibido o botão de exportação.

![BoxPlot](../imagens/08%20-%20Relatório%20Box%20Plot/04%20-%20Relatório%20Box%20Plot.png)

Ao clicar no botão exportar, será gerado um aviso de que o CSV foi exportado para a pasta de downloads.

![BoxPlot](../imagens/08%20-%20Relatório%20Box%20Plot/05%20-%20Relatório%20Box%20Plot.png)

O relatório aparecerá em CSV e para converter em uma tabela em Excel, clique em Dados na barra superior.

![BoxPlot](../imagens/08%20-%20Relatório%20Box%20Plot/06%20-%20Relatório%20Box%20Plot.png)

Ao acessar a aba Dados, clique no ícone Texto para Colunas para ter acesso ao assistente para conversão de texto em colunas. Clique uma vez para avançar e marque na opção vírgula na caixa de delimitadores, depois clique em concluir.

![BoxPlot](../imagens/08%20-%20Relatório%20Box%20Plot/07%20-%20Relatório%20Box%20Plot.png)

O relatório será exportado um CSV.

![BoxPlot](../imagens/08%20-%20Relatório%20Box%20Plot/09%20-%20Relatório%20Box%20Plot.png)

Para mostrar um grafico boxplot, vá a aba inserir,selecione a linha que irá transformar em um gráfico , em seguida clique no ícone que irá mostrar o tipo de gráfico e selecione o grafico de Box Plot.

![BoxPlot](../imagens/08%20-%20Relatório%20Box%20Plot/10%20-%20Relatório%20Box%20Plot.png)

Realizando esses procedimentos com todos os registros da tabela, o usuário chegará ao seguinte resultado.

![BoxPlot](../imagens/08%20-%20Relatório%20Box%20Plot/11%20-%20Relatório%20Box%20Plot.jpg)

Caso necessite de suporte, por favor, entre em contato via e-mail:
coderhood.fatec@gmail.com

<img src="../imagens/CoderHood%20logo.png" width="160">