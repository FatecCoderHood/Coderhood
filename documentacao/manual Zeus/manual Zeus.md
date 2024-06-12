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

![opções](../imagens/00%20-%20Instala%C3%A7%C3%A3o%20PostgreSQL%2001.png)

3. Senha padrão: `root`

![senha](../imagens/01%20-%20Instal%C3%A7ao%20Postgre%20SQL%2002.png)

4. Porta: `5432`

![porta postgre](../imagens/03%20-%20Instala%C3%A7%C3%A3o%20PostgreSQL%2004.png)

5. Locale: `Default locale`

![locale](../imagens/02%20-%20Instala%C3%A7%C3%A3o%20PostgreSQL%2003.png)

Se o PostgreSQL não for instalado corretamente, um erro será exibido:

![erro de conexão]()

## Configuração do Zeus:

Ao iniciar o aplicativo pela primeira vez, um erro será exibido, pois é necessário definir a faixa de valores para carregar o CSV:

![erro de faixa de valores](imagens/image(5).png)

Para definir a faixa de valores, vá até a aba de configurações:

![aba de configurações](imagens/image(6).png)

---

## Fazer upload do arquivo CSV.

<br/>

Para fazer upload do arquivo CSV, abra o Zeus, vá até a aba de Inserir Dados, clique em selecionar arquivo. 

<br/>

![Upload CSV](../imagens/image.png)

<br/>

Selecione o arquivo CSV que você deseja fazer upload e clique em "Salvar".
Se a sigla ainda não estiver relacionada a uma cidade, aparecerá uma mensagem para cadastrar a sigla.

<br/>

![Upload CSV](../imagens/image-3.png)

<br/>

Se o upload for bem-sucedido, você verá uma mensagem de sucesso.

<br/>

![Upload CSV](../imagens/image-2.png)

<br/>

## Situação.

## Valor Médio.

<br/>

Clique na aba Valor Médio, abrirá a tela com os espaços para inserir as informações necessária: Cidade, data e hora.
Após o preenchimento das informações, clique em executar.

<br/>

![Upload CSV](../imagens/image-6.png)

<br/>

O tabela de valor médio será exibido na tela.

<br/>

![Upload CSV](../imagens/image-8.png)

<br/>

## Gerenciamento de Cidades.

<br/>

Clique na aba Cidade, tera a opção de deletar e adicionar cidades.

<br/>

![Upload CSV](../imagens/image-12.png)

<br/>

Para adicionar uma cidade, clique em adicionar cidade e preencha as informações necessárias: Cidade e Sigla.

<br/>

![Upload CSV](../imagens/image-13.png)

<br/>

Para deletar uma cidade, selecione a cidade e clique em deletar. Aparecerá uma mensagem de confirmação.

<br/>

![Upload CSV](../imagens/image-14.png)

<br/>

## Gerenciamento de Estações.

<br/>

Clique na aba Estação, aparecera as informações das estações cadastradas, tera a opção de nomear estação, descrição, deletar e adicionar estações.

<br/>

![Upload CSV](../imagens/image-22.png)

<br/>

Para nomear, fazer alguma descrição e adicionar as coordenadas de uma estação, clique na respectiva caixa de mensagem, preencha com as informações necessárias e aperte enter.

<br/>

![Upload CSV](../imagens/image-23.png)

<br/>

Para adicionar uma estação, clique em adicionar estação e preencha as informações necessárias:

<br/>

![Upload CSV](../imagens/image-10.png)

<br/>

Para deletar uma estação, clique em deletar estação, aparecerá uma mensagem de confirmação.

<br/>

![Upload CSV](../imagens/image-9.png)
