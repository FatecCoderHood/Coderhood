CREATE TABLE IF NOT EXISTS registro (
    id SERIAL PRIMARY KEY,
    data DATE,
    hora TIME,
    estacao VARCHAR(255),
    siglaCidade VARCHAR(05),
    tipo VARCHAR(255),
    valor DECIMAL(5,2),
    suspeito BOOLEAN,
    UNIQUE (data, hora, estacao, siglaCidade, tipo)
);

CREATE TABLE IF NOT EXISTS cidade (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    sigla VARCHAR(05)
);

CREATE TABLE IF NOT EXISTS estacao (
    id SERIAL PRIMARY KEY,
    numero VARCHAR(255) UNIQUE,
    siglaCidade VARCHAR(05),
    nome VARCHAR(255),
    descricao VARCHAR(255),
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION
);

CREATE TABLE IF NOT EXISTS variavel_climatica (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(255),
    valorMinimo DECIMAL(5,2),
    valorMaximo DECIMAL(5,2),
    unidadeMedida VARCHAR(20),
    descricaoConversao VARCHAR(255),
    formulaConversao VARCHAR(255)
);

-- Popular tabela cidade
INSERT INTO cidade (nome, sigla) VALUES 
('São José dos Campos', 'SJC'),
('São Paulo', 'SP'),
('Taubaté', 'TBT'),
('São Carlos', 'SC');

-- Popular tabela estacao
INSERT INTO estacao (numero, siglaCidade, nome, descricao, latitude, longitude) VALUES
('83726', 'SC', 'TESTE', 'TESTE', -23.223701, -45.900907),
('777', 'SP', 'TESTE', 'TESTE', -23.223701, -45.900907),
('420', 'SJC', 'TESTE', 'TESTE', -23.223701, -45.900907),
('728', 'TBT', 'TESTE', 'TESTE', -23.223701, -45.900907);

-- Popular tabela variavel_climatica
INSERT INTO variavel_climatica (tipo, valorMinimo, valorMaximo, unidadeMedida, descricaoConversao, formulaConversao) VALUES 
('temperaturaMedia', -20.0, 60.0, '°C', 'Celsius para Kelvin', 'c = kelvin - 273'),
('umidadeMedia', 0.0, 100.0, '%', 'Umidade relativa', 'umidade'),
('velVento', 0.0, 30.0, 'hPa', 'Pressão atmosférica', 'pressao'),
('dirVento', 0.0, 360.0, 'm/s', 'Direção do vento', 'direcao'),
('chuva', 0.0, 400.0, 'mm', 'Velocidade do vento', 'velocidade');

-- Popular tabela registro
INSERT INTO registro (data, hora, estacao, siglaCidade, tipo, valor, suspeito) VALUES 
('2021-01-01', '00:00:00', '83726', 'SC', 'temperaturaMedia', 20.0, false),
('2021-01-01', '00:00:00', '83726', 'SC', 'umidadeMedia', 50.0, false),
('2021-01-01', '00:00:00', '83726', 'SC', 'velVento', 10.0, false),
('2021-01-01', '00:00:00', '83726', 'SC', 'dirVento', 180.0, false),
('2021-01-01', '00:00:00', '83726', 'SC', 'chuva', 0.0, false),
('2021-01-01', '01:00:00', '83726', 'SC', 'chuva', 0.0, false),
('2021-01-01', '01:00:00', '83726', 'SC', 'temperaturaMedia', 20.0, false),
('2021-01-01', '01:00:00', '83726', 'SC', 'umidadeMedia', 50.0, false),
('2021-01-01', '01:00:00', '83726', 'SC', 'velVento', 10.0, false),
('2021-01-01', '01:00:00', '83726', 'SC', 'dirVento', 180.0, false),
('2021-01-01', '02:00:00', '83726', 'SC', 'chuva', 0.0, false),
('2021-01-01', '02:00:00', '83726', 'SC', 'temperaturaMedia', 20.0, false),
('2021-01-01', '02:00:00', '83726', 'SC', 'umidadeMedia', 50.0, false),
('2021-01-01', '02:00:00', '83726', 'SC', 'velVento', 10.0, false),
('2021-01-01', '02:00:00', '83726', 'SC', 'dirVento', 180.0, false),
('2021-01-01', '03:00:00', '83726', 'SC', 'chuva', 0.0, false),
('2021-01-01', '00:00:00', '777', 'SP', 'temperaturaMedia', 20.0, false),
('2021-01-01', '00:00:00', '777', 'SP', 'umidadeMedia', 50.0, false),
('2021-01-01', '00:00:00', '777', 'SP', 'velVento', 10.0, false),
('2021-01-01', '00:00:00', '777', 'SP', 'dirVento', 180.0, false),
('2021-01-01', '00:00:00', '777', 'SP', 'chuva', 0.0, false),
('2021-01-01', '00:00:00', '728', 'TBT', 'temperaturaMedia', 20.0, false),
('2021-01-01', '00:00:00', '728', 'TBT', 'umidadeMedia', 50.0, false),
('2021-01-01', '00:00:00', '728', 'TBT', 'velVento', 10.0, false),
('2021-01-01', '00:00:00', '728', 'TBT', 'dirVento', 180.0, false),
('2021-01-02', '00:00:00', '728', 'TBT', 'chuva', 0.0, false);