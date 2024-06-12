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
Na aba Situação, o usuário receberá uma tabela com as informações do registro mais recente de cada dado meteorológico feito pela estação, para saber a data e a hora do registro o usuario deve posicionar o cursor do mouse por cima do registro.<br/><br/>![alt text](image(10).png)
<br/><br/>


## Valor Médio.

Clique na aba Valor Médio, abrirá a tela com os espaços para inserir as informações necessária: Cidade, data e hora.
Após o preenchimento das informações, clique em executar.<br/><br/>![Upload CSV](../imagens/image(11).png)
<br/><br/>

O tabela de valor médio será exibido na tela.<br/><br/>
![Upload CSV](../imagens/image(12).png)
<br/>

## Gerenciamento de Cidades.

Clique na aba Cidade, tera a opção de deletar e adicionar cidades.<br/>![Upload CSV](../imagens/image(13).png)
<br/>

Para adicionar uma cidade, clique em adicionar cidade e preencha as informações necessárias: Cidade e Sigla.<br/><br/>![Upload CSV](../imagens/image(14).png)
<br/><br/>

Para deletar uma cidade, selecione a cidade e clique em deletar. Aparecerá uma mensagem de confirmação.
<br/><br/>![Upload CSV](../imagens/image(15).png)
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

