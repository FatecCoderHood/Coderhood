# Documentação do Produto

## Backlog do Produto

## Sprint 1

**História do usuário:**

Como analista, eu quero poder inserir um CSV e adicionar os registros a uma cidade e uma estação, a fim de ter essas informações na minha base de dados.

**Regras de negócio:**

1. **Inserção de CSV Padrão:**
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
     - O nome registrado será utilizado..

2. **Tratamento de erros na inserção:**
   - Caso a estação já exista e esteja associada à outra cidade, Retornará um erro indicando que a estação está relacionada a uma cidade diferente.

## **Interface do usuário:**

**Tela inicial:**

![Tela Inicial](image.png)

**Arquivo selecionado com sucesso:**

![Arquivo Selecionado](image-1.png)

**Após selecionar para salvar na base de dados:**

![Salvar na Base de Dados](image-2.png)

## **Tratamento de erros na interface:**

**A estação inserida está relacionada com uma cidade diferente:**

![Erro de Estação](image-5.png)

**A sigla ainda não está associada a uma cidade:**

![Erro de Sigla](image-3.png)

**O nome do CSV não está no padrão esperado:**

![Erro de CSV](image-4.png)


**História do usuário:**

Como analista, eu quero poder extrair um relatório de valor médio de uma cidade, com periodicidade de uma hora, a partir de uma data inicial e data final. Afim de entender qual a melhor época do ano para os meus negócios

**Regras de negócio:**

- Será solicitado uma cidade, uma data inicial e outra final.
- Será mostrado todos os registros desse periodo com periodicidade de uma hora.
- Dados suspeitos não apareceram.
     
**Interface do usuário:**

**Selecionar cidade e datas:**

![alt text](image-7.png)

**Tabela com registros:**

![alt text](image-8.png)


## Sprint 2

**História do usuário:**


