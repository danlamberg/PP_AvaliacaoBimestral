Avaliação 2o. Bimestre
UNIVERSIDADE POSITIVO
Padrões de Projetos

Prof Escobar

AVALIAÇÃO PRÁTICA – 2º. Bimestre
INSTRUÇÕES GERAIS


- Entrega: 17/11/2025

- Documente as decisões de design no código e justifique a escolha de cada padrão para o problema específico

- O uso de princípios SOLID e de Design Patterns é exigido, sob pena de anualação da questão relativa



QUESTÃO 1 (Nota: 0,5: 
Contexto: Você está desenvolvendo um sistema de processamento de dados para uma empresa financeira que lida com diferentes tipos de análise de risco. O sistema precisa calcular métricas de risco usando diferentes algoritmos (Value at Risk, Expected Shortfall, Stress Testing) que podem mudar dinamicamente durante a execução.

Problema:

- Cada algoritmo de risco deve ser intercambiável em tempo de execução

- Os algoritmos devem ser capazes de compartilhar um contexto complexo com múltiplos parâmetros financeiros

- Deve ser possível trocar de algoritmo de acordo com a necessidade de negócios



Restrições:

- O cliente deve poder mudar de algoritmo sem conhecer os detalhes de implementação

- Implemente pelo menos 3 algoritmos diferentes com cálculos dummy (podem ser mensagens de texto) distintos



QUESTÃO 2 (0,5):
Contexto: Sua empresa está integrando com um legado bancário que possui uma interface complexa para processamento de transações. A interface legada `SistemaBancarioLegado` possui métodos com assinaturas incompatíveis e usa tipos de dados obsoletos.

Problema:

- Converter a interface atualizada ProcessadorTransacoes (com métodos autorizar(String cartao, double valor, String moeda)) para a interface legada

- O sistema legado usa: processarTransacao(HashMap<String, Object> parametros)

- Implemente de forma que o funcionamento seja bidirecional, ou seja, que também permita converter respostas do legado para o formato atualizado

- Adicione tratamento para campos obrigatórios do legado que não existem na interface moderna (ao menos um)



Restrições:

- O legado exige codificação específica para moedas (USD=1, EUR=2, BRL=3)



QUESTÃO 3 (0,25):
Contexto: Você está modelando um sistema de controle para uma usina nuclear com estados complexos de operação. A usina pode estar em: DESLIGADA, OPERACAO_NORMAL, ALERTA_AMARELO, ALERTA_VERMELHO, EMERGENCIA.

Problema:

- Cada transição de estado deve validar condições complexas (temperatura, pressão, nível de radiação)

- Algumas transições são bidirecionais, outras são unidirecionais

- Previna transições circulares perigosas

- O estado EMERGENCIA só pode ser ativado após passar por ALERTA_VERMELHO

- Adicione um modo "manutenção" que sobreescreva temporariamente os estados normais



Regras:

- OPERACAO_NORMAL → ALERTA_AMARELO: se temperatura > 300°C

- ALERTA_AMARELO → ALERTA_VERMELHO: se temperatura > 400°C por mais de 30 segundos

- ALERTA_VERMELHO → EMERGENCIA: se sistema de resfriamento falhar



QUESTÃO 4 (0,25):
Contexto: Desenvolva um sistema de validação de documentos fiscais eletrônicos (NF-e) que precisa aplicar múltiplas regras de validação em cadeia.

Problema:

- Cada validador especializado verifica um aspecto específico do documento

- A cadeia deve suportar validações condicionais (se validador X falhar, pule Y)

- Implemente um mecanismo de "circuit breaker" que interrompa a cadeia após 3 validações falharem

- Adicione capacidade de rollback para validadores que modificam o documento



Validadores Requeridos:

1. Validador de Schema XML contra XSD

2. Validador de Certificado Digital (expiração e revogação)

3. Validador de Regras Fiscais (cálculo de impostos)

4. Validador de Banco de dados (duplicidade de número)

5. Validador de Serviço SEFAZ (consulta online)



Restrições:

- Os validadores 3 e 5 devem ser executados apenas se os anteriores passarem

- O validador 4 deve fazer rollback da inserção se validações subsequentes falharem

- Implemente timeout individual para cada validador
