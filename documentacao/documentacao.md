# Documentação do Produto

## Backlog do Produto

### Sprint 1

**História do usuário:**

Como usuário, eu quero poder inserir um CSV e adicionar os registros a uma cidade e uma estação, a fim de ter essas informações na minha base de dados.

**Regras de negócio:**

1. Caso o nome do CSV selecionado esteja no padrão esperado e não exista o cadastro da cidade ou da estação, serão criados automaticamente. No caso da cidade, será solicitado o nome da cidade.
2. Caso o nome do CSV não esteja no padrão esperado, será possível adicionar manualmente: cidade, siglaCidade e estação.
3. Caso tente inserir dados de uma estação com uma cidade diferente da associada ao banco, será retornado um erro.
4. Registro que contém a mesma data, hora, número, estação e tipo de registros, são ignorados e não são salvos no banco para evitar dados duplicados.

**Interface do usuário:**

Tela inicial:
![alt text](image.png)

Arquivo selecionado com sucesso:
![alt text](image-1.png)

Após selecionar para salvar na base de dados:
![alt text](image-2.png)


Tratamento de erros na interface:

A sigla ainda não está associada a uma cidade.
![alt text](image-3.png)

