# votacao-almoco
votacao-almoco

Requisitos de Ambiente
	
	Java 1.8
	Gradle 6.2.2
 
Compilação

	gradle build

Execução

	gradle bootRun
	
	Ao finalizar o comando acima, a API REST estará disponível em http://localhost:8080/votacao-almoco-api/

API REST /votacao-almoco-api

GET /votacao-almoco-api/restaurantes	

	Objetivo: Listar restaurantes já cadastrados
	Retorno: Json contendo a lista de restaurantes já cadastrados
	Exemplo de chamada (curl): 
		curl http://localhost:8080/votacao-almoco-api/restaurantes
	Exemplo de retorno:
		[{"id":1,"nome":"Panela da Vovo"},{"id":2,"nome":"Porta Larga"},{"id":3,"nome":"Xis Calota"},{"id":4,"nome":"Bar do Waguinho"}]
		
POST /votacao-almoco-api/restaurantes

	Objetivo: Cadastrar novos restaurantes
	Corpo da Requisição: Json com atributo "nome"
	Retorno: Status 201, Dados do Restaurante criado
	Exemplo de chamada (curl):
		curl -X POST http://localhost:8080/votacao-almoco-api/restaurantes -H "Content-Type: application/json" -d '{"nome": "Dog da Esquina"}'
	Exemplo de retorno:
		{"id":4,"nome":"Dog da Esquina"}
		
POST /votacao-almoco-api/votos

	Objetivo: Votar em um restaurante para o próximo almoço
	Corpo da Requisição: Json com atributos "matricula" e "idRestaurante"
	Retorno: Status 201
	Exemplo de chamada (curl):
		curl -X POST http://localhost:8080/votacao-almoco-api/votos -H "Content-Type: application/json" -d '{"idRestaurante": 1, "matricula": "9999gold"}'

GET /votacao-almoco-api/votos

	Objetivo: Verificar os votos já realizados
	Retorno: Json com lista de votos, contendo dados do restaurante, matrícula do votante, data do almoço votado e hora da última atualização (que é usada como critério de desempate)
	Exemplo de chamada (curl):
		curl http://localhost:8080/votacao-almoco-api/votos
	Exemplo de retorno:
		[{"idRestaurante":1,"matricula":"x123456","dataAlmoco":"2020-03-27","ultimaAtualizacao":"2020-03-26T11:14:13.851444"}]
		
GET /votacao-almoco-api/resultados-votacao/ultima-votacao

	Objetivo: Obter o resultado da última votação, apurada via job executado diariamente no horário limite configurado
	Retorno: Json com o resultado da última votação, contendo os dados do restaurante escolhido e a data do almoço
	Exemplo de chamada (curl):
		curl http://localhost:8080/votacao-almoco-api/resultados-votacao/ultima-votacao
	Exemplo de retorno:
		{"escolhido":{"id":2,"nome":"Porta Larga"},"dataAlmoco":"2020-03-26"}

Informações sobre a Implementação

	Frameworks e bibliotecas utilizados: 
		- Spring Boot, 
		- Spock (testes)
		- H2 (banco de dados em memória)
		- Lombok
		
	Organização interna da aplicação:
		De modo geral, a aplicação é organizada em camadas de controle (comunicação externa), serviço (regras de negócio) e repositório (armazenamento de dados).
		Além disso, é utlizado um sistema de agendamento de tarefas (job scheduler), que funciona de acordo com propriedades configuradas na aplicação.
			
Decisões de Negócio

	Para o desenvolvimento da primeira estória, foram disponibilizadas 3 operações na API: o cadastro de restaurantes, a obtenção de restaurantes cadastrados 
	(necessária para que o usuário saiba quais restaurantes já estão disponíveis para voto) e o registro de votos.
	O uso de um cadastro de restaurantes pareceu ser uma boa solução para facilitar o processo de escolha por parte dos usuários.
	Além disso, nessa estória não fica claro qual o comportamento ideal do sistema quando o usuário vota novamente para o mesmo almoço, mas escolhendo outro
	restaurante. Visando novamente facilitar o uso do sistema e torná-lo mais flexível, foi implementada uma operação de troca de voto nessa situação.
	Nessa primeira versão do sistema, é permitido votar apenas para o próximo almoço. Desse modo, votos realizados após o horário de apuração de votos acabam
	sendo contabilizados para o próximo almoço. Apesar de não ter nada na estória indicando a possibilidade de voto para vários almoços à frente, esta é uma 
	possível feature a ser estudada para implementação futura. Também não foi especificado na estória os dias de almoço, mas foi assumido que são de segunda a
	sexta-feira.
	
	Para o desenvolvimento da segunda estória, foi utilizado um repositório de resutados de votações da semana, que é limpado semanalmente.
	
	

Próximos Passos

