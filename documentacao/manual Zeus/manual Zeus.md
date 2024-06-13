# Manual Zeus

Manual produzido pela equipe Coderhood - FATEC São José dos Campos - Prof. Jessen Vidal

Qualquer dúvida entrar em contato (12) 98242-7304.


- [Instalação do Java 17.0.10.](#instalação-do-java-17010)
- [Instalação e configuração do PostgreSQL 16.3.](#instalação-e-configuração-do-postgresql-163)
- [Configuração do Zeus.](#configuração-do-zeus)

## Instalação do Java 17.0.10:

Para baixar o Java 17.0.10, visite o seguinte link: [Download Java 17.0.10](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

## Instalação e configuração do PostgreSQL 16.3:

Para baixar e configurar o PostgreSQL 16.3, siga estas etapas:

1. Visite o seguinte link: [Download PostgreSQL 16.3](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads)

2. Selecione as seguintes opções:

![opções](../imagens/image(0).png)
<br/><br/>

3. Senha padrão: `root`

![senha](../imagens/image(1).png)
<br/><br/>

4. Porta: `5432`

![porta postgre](../imagens/image(3).png)
<br/><br/>

5. Locale: `Default locale`

![locale](../imagens/image(2).png)
<br/><br/>

Se o PostgreSQL não for instalado corretamente, um erro será exibido:

![erro de conexão](../imagens/image(4).png)
<br/><br/>

## Configuração do Zeus:

Ao iniciar o aplicativo pela primeira vez, um erro será exibido, pois é necessário definir a faixa de valores para carregar o CSV:

![erro de faixa de valores](../imagens/image(5).png)
<br/><br/>


Para definir a faixa de valores, vá até a aba de configurações:

![aba de configurações](../imagens/image(6).png)
<br/><br/>
## Fazer upload do arquivo CSV.

Para fazer upload do arquivo CSV, abra o Zeus, vá até a aba de Inserir Dados, clique em selecionar arquivo.(imagens/image.png).

![Upload CSV](../imagens/image(7).png)
<br/><br/>

Selecione o arquivo CSV que você deseja fazer upload e clique em "Salvar".
Se a sigla ainda não estiver relacionada a uma cidade, aparecerá uma mensagem para cadastrar a sigla.<br/>![Upload CSV](../imagens/image(8).png)
<br/><br/>

Se o upload for bem-sucedido, você verá uma mensagem de sucesso.![Upload CSV](../imagens/image(9).png)
<br/><br/>

## Situação.
Clique na aba Situação, o usuário receberá uma tabela com as informações das ultimas cidades que
 constam no banco de dados, com o registro mais recente de cada tipo de dado.

 <br/><br/>![](../imagens/08_Situacao.jpeg)
<br/><br/>

Para saber a data e a hora do registro o usuario deve posicionar o cursor do mouse por cima do dado.
<br/><br/>![](../imagens/image(10).png)
<br/><br/>

Caso o usuario deseje, ele pode exportar um arquivo CSV clicando no botão exportar. O Registro de Situação será exibido como na imagem abaixo: 
<br/><br/>![](../imagens/09_Relatorio_%20de_%20Situação.jpeg)
<br/><br/>

## Valor Médio.

Clique na aba do Valor Médio escolha a cidade desejada na lista suspensa qie ira aparecer na tela
<br/><br/>![Upload CSV](../imagens/1%20tela%20Valor%20Medio%20.png)
<br/><br/>

Após escolher a cidade, o usuário devera escolher a data inicial:  
<br/><br/>![Upload CSV](../imagens/2%20Tela%20valor%20medio%20.png)
<br/><br/>
E a data final:
.<br/><br/>![Upload CSV](../imagens/3%20Tela%20Valor%20Medio%20.png)
<br/><br/>

Apos a escolha do periodo , aparecerá os botoes
de Executar eo de Exportar.
<br/><br/>![](../imagens/10_Executar_e_Exportar.png)
<br/><br/>

Ao clicar o botão de executar a tabela de valor médio será exibido na tela.<br/><br/>
![Upload CSV](../imagens/image(12).png)
<br/><br/>

Ao clicar o botão de Exportar o CSV a tabela de valor médio ira gerar um relatorio em excel.<br/><br/>
![](../imagens/11-Executar_o_CSV.jpeg)
<br/><br/>

## Gerenciamento de Cidades.

Clique na aba Cidade, tera a opção de: adicionar,editar, deletar as cidades disponiveis em seu banco de dados.
<br/>![Upload CSV](../imagens/1%20Tela%20Cidade%20.png)
<br/><br/>
Para editar, clique duas vezes no nome da cidade pretendida e assim uma caixa de texto aparecera para que o usuario possa inserir um novo nome.
<br/>![Upload CSV](../imagens/12_Tela_Cidade.png)
<br/><br/>

Para adicionar uma cidade, clique em adicionar cidade e preencha as informações necessárias: Cidade e Sigla.<br/><br/>![Upload CSV](../imagens/image(14).png)
<br/><br/>

Para deletar uma cidade, selecione a cidade e clique em deletar. Aparecerá uma mensagem de confirmação.
<br/><br/>![Upload CSV](../imagens/image(15).png)
<br/><br/>

Ao cadastrar uma sigla que a mesma ja esta cadastrada aparecera uma mensagem.
<br/><br/>![](../imagens/13_Aviso_Cidade.png)
<br/><br/>

## Gerenciamento de Estações.

Clique na aba Estação, aparecera as informações das estações cadastradas, tera a opção de nomear estação, descrição, deletar e adicionar estações.<br/><br/>![Upload CSV](../imagens/image(16).png)
<br/><br/>

Para nomear, fazer alguma descrição e adicionar as coordenadas de uma estação, clique na respectiva caixa de mensagem, preencha com as informações necessárias e aperte enter.<br/><br/>![Upload CSV](../imagens/image(17).png)
<br/><br/>

Para adicionar uma estação, clique em adicionar estação e preencha as informações necessárias:<br/><br/>![Upload CSV](../imagens/image(18).png)
<br/><br/>

Para deletar uma estação, clique em deletar estação, aparecerá uma mensagem de confirmação.<br/><br/>![Upload CSV](../imagens/image(19).png)
<br/><br/>

## Registros Suspeitos 

Clique na aba Registros Suspeitos, aparecera os 
registros com valores descrepantes  
<br/><br/>![](../imagens/image-15.png)
<br/><br/>

O usuario pode editar a onde estiver o registro com os valores contraditorios.
<br/><br/>![](../imagens/image-16.png)
<br/><br/>

O usuario pode tambem ao inves de editar e pode excluir o registro.
<br/><br/>![](../imagens/image-17.png)
<br/><br/>

## Boxplot 

Clique na aba Boxplot (boxplot é uma ferramenta gráfica utilizada na estatística para exibir a distribuição de um conjunto de dados)
o usuario pode escolher a cidade com seus registros 
<br/><br/>![](../imagens/boxplot.png)
<br/><br/>

O usuario pode executar as informações, que aparecera na tela.
<br/><br/>![](../imagens/image(12).png)
<br/><br/>

Ou se não o usuario pode exportar as informações 
para gerar o relatorio de excell.

##

No qual o usuario pode gerar os graficos 
com as informaçoçes do boxplot
<br/><br/>![](../imagens/csv_boxplot.jpg)
<br/><br/>