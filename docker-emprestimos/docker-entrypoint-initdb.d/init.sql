CREATE SCHEMA emprestimo;


CREATE TABLE emprestimo.pessoas (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    identificador VARCHAR(20) NOT NULL,
    data_nascimento DATE,
    tipo_identificador VARCHAR(40) NOT NULL,
    valor_minimo_mensal_parcelas NUMERIC(15, 2),
    valor_maximo_emprestimo NUMERIC(15, 2)
);


CREATE TABLE emprestimo.emprestimos (
    id SERIAL PRIMARY KEY,
    id_pessoa INT NOT NULL,   
    valor_emprestimo NUMERIC(18, 4) NOT NULL,
    numero_parcelas INT NOT NULL,
    status_pagamento VARCHAR(50) NOT NULL,
    data_criacao DATE NOT NULL,
    FOREIGN KEY (id_pessoa) REFERENCES emprestimo.pessoas (id)
    
);

CREATE TABLE emprestimo.pagamentos (
    id SERIAL PRIMARY KEY,
    data_pagamento DATE NOT null,
    id_emprestimo INT NOT NULL,
    numero_parcela INT NOT NULL,
    valor_pagamento NUMERIC(18, 4) NOT null,
    FOREIGN KEY (id_emprestimo) REFERENCES emprestimo.emprestimos (id)
);


INSERT INTO emprestimo.pessoas (id, nome, identificador, data_nascimento, tipo_identificador, valor_minimo_mensal_parcelas, valor_maximo_emprestimo) 
VALUES (1, 'João Silva', '12345678901', '1990-05-15', 'PESSOA_FISICA', 300.00, 10000.00);
INSERT INTO emprestimo.pessoas (id, nome, identificador, data_nascimento, tipo_identificador, valor_minimo_mensal_parcelas, valor_maximo_emprestimo) 
VALUES (2, 'Empresa X', '54924564000140', '1985-08-20', 'PESSOA_JURIDICA', 1000.00, 100000.00);
INSERT INTO emprestimo.pessoas (id, nome, identificador, data_nascimento, tipo_identificador, valor_minimo_mensal_parcelas, valor_maximo_emprestimo) 
VALUES (3, 'Ana Souza', '12345678', '2000-12-01', 'ESTUDANTE_UNIVERSITARIO', 100.00, 10000.00);
INSERT INTO emprestimo.pessoas (id, nome, identificador, data_nascimento, tipo_identificador, valor_minimo_mensal_parcelas, valor_maximo_emprestimo) 
VALUES (4, 'Carlos Lima', '1234567890', '1955-04-10', 'APOSENTADO', 400.00, 25000.00);
INSERT INTO emprestimo.pessoas (id, nome, identificador, data_nascimento, tipo_identificador, valor_minimo_mensal_parcelas, valor_maximo_emprestimo) 
VALUES (5, 'Mariana Costa', '98765432100', '1995-03-22', 'PESSOA_FISICA', 300.00, 10000.00);
INSERT INTO emprestimo.pessoas (id, nome, identificador, data_nascimento, tipo_identificador, valor_minimo_mensal_parcelas, valor_maximo_emprestimo) 
VALUES (6, 'Comércio XYZ Ltda', '98765432000123', '1992-11-15', 'PESSOA_JURIDICA', 1000.00, 100000.00);
INSERT INTO emprestimo.pessoas (id, nome, identificador, data_nascimento, tipo_identificador, valor_minimo_mensal_parcelas, valor_maximo_emprestimo) 
VALUES (7, 'Lucas Almeida', '87654321', '2001-07-18', 'ESTUDANTE_UNIVERSITARIO', 100.00, 10000.00);
INSERT INTO emprestimo.pessoas (id, nome, identificador, data_nascimento, tipo_identificador, valor_minimo_mensal_parcelas, valor_maximo_emprestimo) 
VALUES (8, 'José Souza', '8765432190', '1948-01-29', 'APOSENTADO', 400.00, 25000.00);
INSERT INTO emprestimo.pessoas (id, nome, identificador, data_nascimento, tipo_identificador, valor_minimo_mensal_parcelas, valor_maximo_emprestimo) 
VALUES (9, 'Clara Ribeiro', '23456789012', '1988-10-05', 'PESSOA_FISICA', 300.00, 10000.00);
INSERT INTO emprestimo.pessoas (id, nome, identificador, data_nascimento, tipo_identificador, valor_minimo_mensal_parcelas, valor_maximo_emprestimo) 
VALUES (10, 'Instituto ABC', '34567891000189', '2010-03-15', 'PESSOA_JURIDICA', 1000.00, 100000.00);


