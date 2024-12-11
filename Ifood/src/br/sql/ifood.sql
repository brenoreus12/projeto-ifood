CREATE DATABASE IF NOT EXISTS ifood;
USE ifood;

CREATE TABLE Restaurante (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    endereco VARCHAR(255),
    telefone VARCHAR(15),
    categorias VARCHAR(100),
    horario_funcionamento VARCHAR(50),
    historico_retirada VARCHAR(255)
);

CREATE TABLE Produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10, 2) NOT NULL,
    id_categoria INT,
    id_restaurante INT,
    FOREIGN KEY (id_restaurante) REFERENCES Restaurante(id)
);

CREATE TABLE Acompanhamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    valor DECIMAL(10, 2) NOT NULL
);

CREATE TABLE Produto_Acompanhamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_produto INT,
    id_acompanhamento INT,
    FOREIGN KEY (id_produto) REFERENCES Produto(id),
    FOREIGN KEY (id_acompanhamento) REFERENCES Acompanhamento(id)
);

CREATE TABLE Pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data DATETIME NOT NULL,
    id_restaurante INT,
    valor DECIMAL(10, 2) NOT NULL,
    taxa_entrega DECIMAL(10, 2),
    id_promocao INT,
    id_status_entrega INT,
    id_forma_pagamento INT,
    observacoes TEXT,
    troco DECIMAL(10, 2),
    id_endereco INT,
    FOREIGN KEY (id_restaurante) REFERENCES Restaurante(id)
);

CREATE TABLE Pedido_Produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT,
    id_produto INT,
    quantidade INT NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES Pedido(id),
    FOREIGN KEY (id_produto) REFERENCES Produto(id)
);

CREATE TABLE Pedido_Produto_Acompanhamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido_produto INT,
    id_acompanhamento INT,
    FOREIGN KEY (id_pedido_produto) REFERENCES Pedido_Produto(id),
    FOREIGN KEY (id_acompanhamento) REFERENCES Acompanhamento(id)
);

CREATE TABLE Endereco (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rua VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    estado VARCHAR(2),
    numero VARCHAR(10),
    complemento VARCHAR(100),
    ponto_referencia VARCHAR(255),
    cep VARCHAR(8)
);

CREATE TABLE Promocao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    codigo VARCHAR(20),
    tipo VARCHAR(20),
    valor DECIMAL(10, 2),
    validade DATE
);

CREATE TABLE Status_Entrega (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50)
);

CREATE TABLE Forma_Pagamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(50)
);

CREATE TABLE Restaurante_Pagamento (
    id_restaurante INT,
    id_pagamento INT,
    PRIMARY KEY (id_restaurante, id_pagamento),
    FOREIGN KEY (id_restaurante) REFERENCES Restaurante(id),
    FOREIGN KEY (id_pagamento) REFERENCES Forma_Pagamento(id)
);

CREATE TABLE Avaliacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nota INT NOT NULL,
    id_pedido INT,
    id_restaurante INT,
    FOREIGN KEY (id_pedido) REFERENCES Pedido(id),
    FOREIGN KEY (id_restaurante) REFERENCES Restaurante(id)
);

CREATE TABLE Historico_Entrega (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_status_entrega INT,
    id_pedido INT,
    datetime DATETIME,
    FOREIGN KEY (id_status_entrega) REFERENCES Status_Entrega(id),
    FOREIGN KEY (id_pedido) REFERENCES Pedido(id)
);

CREATE TABLE Categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(50),
    nome VARCHAR(100),
    descricao TEXT
);

INSERT INTO Restaurante (nome, endereco, telefone, categorias, horario_funcionamento, historico_retirada)
VALUES 
('Pizza Hut', 'Rua das Flores, 123', '11987654321', 'Pizzaria', '10:00-23:00', 'Retirada 15/11/2024'),
('McDonald\'s', 'Av. Paulista, 456', '11987654322', 'Fast Food', '08:00-22:00', 'Retirada 14/11/2024'),
('Burger King', 'Rua Augusta, 789', '11987654323', 'Fast Food', '08:00-22:00', 'Retirada 13/11/2024'),
('Subway', 'Rua do Comércio, 101', '11987654324', 'Sanduíches', '09:00-20:00', 'Retirada 12/11/2024'),
('Dominos', 'Av. Brasil, 202', '11987654325', 'Pizzaria', '11:00-00:00', 'Retirada 10/11/2024'),
('Outback', 'Rua da Liberdade, 303', '11987654326', 'Steakhouse', '12:00-22:00', 'Retirada 11/11/2024'),
('KFC', 'Av. Central, 404', '11987654327', 'Frango Frito', '10:00-23:00', 'Retirada 09/11/2024'),
('Taco Bell', 'Rua Verde, 505', '11987654328', 'Mexicano', '11:00-21:00', 'Retirada 08/11/2024'),
('Habib\'s', 'Av. Amarela, 606', '11987654329', 'Árabe', '08:00-20:00', 'Retirada 07/11/2024'),
('Sushi Bar', 'Rua Azul, 707', '11987654330', 'Sushi', '12:00-23:00', 'Retirada 06/11/2024');

INSERT INTO Produto (nome, descricao, preco, id_categoria, id_restaurante)
VALUES
('Big Mac', 'Sanduíche clássico com carne bovina', 19.90, 2, 2),
('Whopper', 'Sanduíche com carne grelhada e alface', 22.90, 2, 3),
('Pizza de Calabresa', 'Pizza com calabresa e cebola', 39.90, 1, 1),
('Pizza Marguerita', 'Pizza com tomate, queijo e manjericão', 35.90, 1, 5),
('Combo Frango Crocante', 'Frango frito com batata frita', 29.90, 2, 7),
('Burrito', 'Tortilha recheada com carne e feijão', 18.90, 3, 8),
('Esfiha de Carne', 'Esfiha aberta com carne temperada', 5.90, 3, 9),
('Coca-Cola Lata', 'Refrigerante lata 350ml', 4.90, 4, 2),
('Temaki de Salmão', 'Cone de arroz e salmão fresco', 15.90, 5, 10),
('Costela Outback', 'Costela suculenta com molho barbecue', 89.90, 6, 6);

INSERT INTO Acompanhamento (nome, descricao, valor)
VALUES
('Batata Frita', 'Batata frita crocante', 9.90),
('Onion Rings', 'Anéis de cebola empanados', 12.90),
('Molho Barbecue', 'Molho barbecue especial', 4.90),
('Molho Ranch', 'Molho ranch cremoso', 3.90),
('Guacamole', 'Creme de abacate temperado', 6.90),
('Sour Cream', 'Creme de leite azedo', 5.90),
('Cheddar', 'Molho de queijo cheddar', 4.90),
('Chili', 'Feijão apimentado', 7.90),
('Salada Verde', 'Salada com alface, rúcula e tomate', 12.90),
('Arroz Japonês', 'Arroz típico japonês', 5.90);

INSERT INTO Categoria (tipo, nome, descricao)
VALUES
('Comida', 'Pizzaria', 'Especializada em pizzas'),
('Comida', 'Fast Food', 'Comida rápida'),
('Comida', 'Mexicana', 'Típica do México'),
('Bebidas', 'Refrigerantes', 'Refrigerantes e sucos'),
('Comida', 'Japonesa', 'Culinária japonesa'),
('Comida', 'Steakhouse', 'Carnes premium'),
('Comida', 'Árabe', 'Culinária árabe'),
('Acompanhamentos', 'Batatas', 'Batatas fritas e assadas'),
('Sobremesas', 'Sorvetes', 'Sorvetes e milkshakes'),
('Vegana', 'Vegana', 'Opções sem origem animal');

INSERT INTO Endereco (rua, bairro, cidade, estado, numero, complemento, ponto_referencia, cep)
VALUES
('Rua das Flores', 'Centro', 'São Paulo', 'SP', '123', 'Ap 12', 'Próximo ao mercado', '01001000'),
('Av. Paulista', 'Bela Vista', 'São Paulo', 'SP', '456', '', 'Em frente ao MASP', '01311300'),
('Rua Augusta', 'Consolação', 'São Paulo', 'SP', '789', '', 'Perto do shopping', '01311123'),
('Rua do Comércio', 'Centro', 'Rio de Janeiro', 'RJ', '101', '', 'Ao lado do banco', '20040000'),
('Av. Brasil', 'Zona Norte', 'Rio de Janeiro', 'RJ', '202', '', 'Próximo ao hospital', '20050000'),
('Rua da Liberdade', 'Liberdade', 'São Paulo', 'SP', '303', '', 'Ao lado do metrô', '01512000'),
('Av. Central', 'Centro', 'Curitiba', 'PR', '404', '', 'Em frente ao parque', '80040000'),
('Rua Verde', 'Zona Leste', 'Campinas', 'SP', '505', '', 'Perto do mercado', '13000000'),
('Av. Amarela', 'Zona Sul', 'Fortaleza', 'CE', '606', '', 'Ao lado da escola', '60000000'),
('Rua Azul', 'Centro', 'Salvador', 'BA', '707', '', 'Próximo ao elevador', '40000000');

UPDATE Restaurante
SET telefone = '11999999999'
WHERE id = 1;

DELETE FROM Restaurante
WHERE id = 10;

SELECT 
    p.nome AS Produto, 
    SUM(pp.quantidade) AS Quantidade_Vendida
FROM 
    Produto p
INNER JOIN 
    Pedido_Produto pp ON p.id = pp.id_produto
GROUP BY 
    p.id
ORDER BY 
    Quantidade_Vendida DESC
LIMIT 1;

SELECT 
    p.nome AS Produto, 
    SUM(pp.quantidade) AS Quantidade_Vendida
FROM 
    Produto p
LEFT JOIN 
    Pedido_Produto pp ON p.id = pp.id_produto
GROUP BY 
    p.id
ORDER BY 
    Quantidade_Vendida ASC
LIMIT 1;

SELECT 
    a.nome AS Acompanhamento, 
    COUNT(ppa.id_acompanhamento) AS Total_Usos
FROM 
    Acompanhamento a
INNER JOIN 
    Pedido_Produto_Acompanhamento ppa ON a.id = ppa.id_acompanhamento
GROUP BY 
    a.id
ORDER BY 
    Total_Usos DESC
LIMIT 1;

SELECT 
    MONTH(p.data) AS Mes, 
    COUNT(p.id) AS Total_Vendas
FROM 
    Pedido p
GROUP BY 
    Mes
ORDER BY 
    Total_Vendas DESC
LIMIT 1;

SELECT 
    fp.descricao AS Forma_Pagamento, 
    COUNT(p.id_forma_pagamento) AS Total_Usos
FROM 
    Forma_Pagamento fp
INNER JOIN 
    Pedido p ON fp.id = p.id_forma_pagamento
GROUP BY 
    fp.id
ORDER BY 
    Total_Usos DESC
LIMIT 1;

SELECT 
    e.rua, e.bairro, e.cidade, 
    COUNT(p.id_endereco) AS Total_Entregas
FROM 
    Endereco e
INNER JOIN 
    Pedido p ON e.id = p.id_endereco
GROUP BY 
    e.id
ORDER BY 
    Total_Entregas DESC
LIMIT 1;

SELECT 
    p.id AS Pedido_ID, 
    SUM(pp.quantidade) AS Total_Produtos
FROM 
    Pedido p
INNER JOIN 
    Pedido_Produto pp ON p.id = pp.id_pedido
GROUP BY 
    p.id
ORDER BY 
    Total_Produtos DESC
LIMIT 1;

SELECT 
    fp.descricao AS Forma_Pagamento, 
    COUNT(p.id) AS Total_Vendas
FROM 
    Pedido p
INNER JOIN 
    Forma_Pagamento fp ON p.id_forma_pagamento = fp.id
WHERE 
    MONTH(p.data) = MONTH(CURRENT_DATE())
    AND YEAR(p.data) = YEAR(CURRENT_DATE())
GROUP BY 
    fp.id
ORDER BY 
    Total_Vendas DESC;

SELECT 
    fp.descricao AS Forma_Pagamento, 
    COUNT(p.id) AS Total_Vendas
FROM 
    Pedido p
INNER JOIN 
    Forma_Pagamento fp ON p.id_forma_pagamento = fp.id
WHERE 
    MONTH(p.data) = MONTH(CURRENT_DATE()) - 1
    AND YEAR(p.data) = YEAR(CURRENT_DATE())
GROUP BY 
    fp.id
ORDER BY 
    Total_Vendas DESC;

