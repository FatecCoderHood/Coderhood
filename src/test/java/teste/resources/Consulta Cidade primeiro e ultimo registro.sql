SELECT 
    Cidade.nome AS nome, 
    Cidade.sigla AS sigla, 
    MIN(Registro.data) AS data_primeiro_registro, 
    MAX(Registro.data) AS data_ultimo_registro 
FROM 
    Cidade 
JOIN 
    Registro ON Cidade.sigla = Registro.siglaCidade 
WHERE 
    Registro.siglaCidade = Cidade.sigla 
GROUP BY 
    Cidade.nome, Cidade.sigla;