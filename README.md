# <p align = "center"> Coderhood - API 2º Semestre - BD 2024

# <p align = "center"> ![Coderhood2](https://github.com/CoderhoodFatec-2024-1/Coderhood/assets/87550162/771a8ed8-4c0b-46b4-838d-0358347ca0e5)


<p align="center">
  <a href ="#mortar_board-integrantes-da-equipe"> Integrantes </a>  •
  <a href ="#anger-descrição-do-desafio"> Desafio </a>  •
  <a href ="#dart-objetivo"> Objetivo </a>  •
  <a href="#page_facing_up-requisitos-funcionais"> Requisitos Funcionais </a> •
  <a href="#page_with_curl-requisitos-não-funcionais"> Requisitos Não Funcionais </a>
</p>
<p align="center">
  <a href ="#calendar-cronograma"> Cronograma </a>  •
  <a href="#date-product-backlog"> Product Backlog </a> •
  <a href="#bookmark-tecnologias-utilizadas"> Tecnologias Utilizadas </a>
</p>


## :mortar_board: Integrantes:

| **Nome**                   | **Função**            | **LinkedIn**                                                  |
|:----------------------:|:-----------------:|:----------------------------------------------------------:|
| César Truyts           | Scrum Master      | [![LinkedIn](https://img.shields.io/badge/LinkedIn-Profile-blue?style=flat-square&logo=linkedin&labelColor=blue)](https://shorturl.at/BC169) |
| Mateus Marques         | Product Owner     | [![LinkedIn](https://img.shields.io/badge/LinkedIn-Profile-blue?style=flat-square&logo=linkedin&labelColor=blue)](https://shorturl.at/BMRT2) |
| Caique Almeida         | Desenvolvedor     | [![LinkedIn](https://img.shields.io/badge/LinkedIn-Profile-blue?style=flat-square&logo=linkedin&labelColor=blue)](https://shorturl.at/acghx) |
| Carlos Costa           | Desenvolvedor     | [![LinkedIn](https://img.shields.io/badge/LinkedIn-Profile-blue?style=flat-square&logo=linkedin&labelColor=blue)](https://shorturl.at/alST4) |
| Juan Cursino           | Desenvolvedor     | [![LinkedIn](https://img.shields.io/badge/LinkedIn-Profile-blue?style=flat-square&logo=linkedin&labelColor=blue)](https://shorturl.at/gpDES) |
| Julio Araujo           | Desenvolvedor     | [![LinkedIn](https://img.shields.io/badge/LinkedIn-Profile-blue?style=flat-square&logo=linkedin&labelColor=blue)](https://shorturl.at/eCIXZ) |
| Michel Momose          | Desenvolvedor     | [![LinkedIn](https://img.shields.io/badge/LinkedIn-Profile-blue?style=flat-square&logo=linkedin&labelColor=blue)](https://shorturl.at/ciLS3) |
| Rennerson Vasconcelos  | Desenvolvedor     | [![LinkedIn](https://img.shields.io/badge/LinkedIn-Profile-blue?style=flat-square&logo=linkedin&labelColor=blue)](https://shorturl.at/mpF39) |


## :dart: Objetivo

Desenvolver uma ferramenta eficiente para consolidar e gerenciar dados climáticos de cidades do estado de São Paulo, permitindo a análise e geração de relatórios a partir de múltiplos arquivos CSV provenientes de diversas estações de monitoramento.

## :page_facing_up: Requisitos Funcionais
* O sistema deve carregar e validar de arquivos CSV.
* Relatório de valor médio das variáveis climáticas por cidade. Deve ser possível escolher uma cidade e um período de tempo.
* Relatório de situação, apresentando os valores médios das últimas medidas para cada cidade.
* Gerar dados que possibilitem a criação de um gráfico boxplot com dados de uma estação em uma determinada data.
* O sistema deve conter gerenciamento de estações, cidades e unidades de medida.
* O sistema deve possibilitar o tratamento de registros suspeitos

## :page_with_curl: Requisitos Não Funcionais

* Linguagem de programação Java e tecnologias relacionadas
* Banco de Dados Relacionais
* Documentação: manual de usuário, diagrama entidade-relacionamento e instruções de instalação.

## :calendar: Cronograma

| Sprint  | Nome | Data inicio  | Data Fim | Status |
| ------------- | ------------- | ------------- | ------------- | ------------- |
| --  | KickOff   | 04/03   | 08/03 | Ok |
|  1  | Sprint 1   | 25/03   | 14/04 |    |
|  2  | Sprint 2   | 15/04   | 05/05 |    |
|  3  | Sprint 3   | 06/05   | 26/05 |    |
|  4  | Sprint 4   | 27/05   | 16/06 |    |
|  5  | Feira de Soluções  | 27/06 |    |


## :date: Product BackLog
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>User Stories</th>
        <th>Épico</th>
        <th>Sprint</th>
        <th>Prioridade</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>1</td>
        <td>Como usuário, eu quero poder modelar o banco de dados.</td>
        <td>Modelagem do Banco de Dados</td>
        <td>1</td>
        <td>1</td>
    </tr>
    <tr>
        <td>2</td>
        <td>Como usuário, eu quero poder inserir um CSV e adicionar os registros a uma cidade e uma estação.</td>
        <td>Importar Dados Climáticos Via Arquivo CSV</td>
        <td>1</td>
        <td>1</td>
    </tr>
    <tr>
        <td>3</td>
        <td>Como usuário, eu quero poder extrair um relatório de valor médio de uma cidade, com periodicidade de uma hora, a partir de uma data inicial e data final.</td>
        <td>Gerar Relatório</td>
        <td>1</td>
        <td>1</td>
    </tr>
    <tr>
        <td>4</td>
        <td>Como usuário, eu quero poder alterar o nome e a sigla da cidade.</td>
        <td>Alterar Informações da Cidade</td>
        <td>2</td>
        <td>2</td>
    </tr>
    <tr>
        <td>5</td>
        <td>Como usuário, eu quero poder alterar o número e a sigla da cidade da estação.</td>
        <td>Alterar Informações da Estação</td>
        <td>2</td>
        <td>2</td>
    </tr>
    <tr>
        <td>6</td>
        <td>Como usuário, eu quero poder tratar valores marcados como suspeitos.</td>
        <td>Tratamento de Dados Suspeitos</td>
        <td>2</td>
        <td>2</td>
    </tr>
    <tr>
        <td>7</td>
        <td>Como usuário, eu quero poder visualizar o relatório de situação.</td>
        <td>Visualizar Relatórios</td>
        <td>3</td>
        <td>2</td>
    </tr>
    <tr>
        <td>8</td>
        <td>Como usuário, eu quero poder visualizar dados para gerar relatório boxPlot.</td>
        <td>Visualizar Relatórios</td>
        <td>3</td>
        <td>2</td>
    </tr>
    <tr>
        <td>9</td>
        <td>Como usuário, eu quero documentações detalhadas, incluindo um manual de usuário e instruções de instalação, para facilitar o uso e a manutenção do sistema.</td>
        <td>Criar Documentação</td>
        <td>4</td>
        <td>3</td>
    </tr>
    </tbody>
</table>



## :bookmark: Tecnologias Utilizadas
> * [Java](https://www.java.com/pt-BR/) - Versão 17
> * [JavaFX](https://openjfx.io/) - versão 17.0.6
> * [PostgreSQL](https://www.postgresql.org/) - Versão 16.2
> * [JDBC PostgreSQL](https://jdbc.postgresql.org/) -  Versão 42.2.5
> * [Git](https://git-scm.com/)
> * [GitHub](https://github.com/)
> * [Itellij](https://www.jetbrains.com/pt-br/idea/)
> * [VisualStudioCode](https://visualstudio.microsoft.com/pt-br/)
> * [Miro](https://miro.com)
> * [Discord](https://discord.com/)
> * [Slack](https://slack.com/)
> * [Google Docs](https://docs.google.com/)