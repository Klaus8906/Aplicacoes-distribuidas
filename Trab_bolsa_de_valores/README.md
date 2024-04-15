# Aplicacoes-distribuidas
OrderProducer:
A classe OrderProducer age como um produtor de mensagens. Ela se conecta ao RabbitMQ usando as credenciais e informações do servidor fornecidas e cria um canal. Nesse canal, a classe declara uma fila chamada BROKER, onde as mensagens serão publicadas. Em seguida, a classe cria uma mensagem de texto simples que simula uma ordem de compra ou venda (neste exemplo, uma ordem de compra) e a publica na fila BROKER. Depois que a mensagem é publicada, a conexão e o canal são fechados.

OrderConsumer:
A classe OrderConsumer, por outro lado, atua como consumidor de mensagens. Ele estabelece uma conexão com o servidor RabbitMQ de maneira semelhante ao OrderProducer e cria um canal. Inicialmente destinado a escutar uma exchange do tipo fanout. Assim que a fila é declarada e o canal está configurado para consumir mensagens dessa fila, o OrderConsumer entra em um modo de espera, ouvindo as mensagens que chegam. A ordem é processada adequadamente e adicionada ao OrderBook, que representa um livro de ordens onde as ordens de compra e venda são armazenadas e gerenciadas.

Funcionamento Conjunto:
Quando o OrderProducer publica uma mensagem na fila BROKER e o OrderConsumer está corretamente escutando essa fila, o OrderConsumer receberá a mensagem quase que instantaneamente. O RabbitMQ garante a entrega das mensagens da fila aos consumidores conectados. Se houver mais de um consumidor conectado à fila, o RabbitMQ distribuirá as mensagens de maneira equitativa entre eles.

Conexão com RabbitMQ:
Ambas as classes usam uma abstração chamada RabbitMQConnection para se conectar ao RabbitMQ. Essa abstração gerencia detalhes como o endereço do servidor RabbitMQ, o número da porta, o nome do usuário e a senha, encapsulando as complexidades da conexão com o RabbitMQ.