package trab_bolsa_de_valores;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import java.util.Scanner;

public class OrderProducer {
    private static final String EXCHANGE_NAME = "BOLSADEVALORES";
    public static void main(String[] args) {
        String rabbitMqServerAddress = "gull.rmq.cloudamqp.com"; // Passe o endereço como argumento
        Scanner s = new Scanner(System.in);

        System.out.println("Deseja realizar compra ou venda");
        String acao = s.nextLine();

        System.out.println("Qual a acao");
        String codigo = s.nextLine();

        System.out.println("Qual a quantidade");
        String quantidade = s.nextLine();

        System.out.println("Qual o preco desta acao");
        String preco = s.nextLine();

        String orderMessage = "<"+acao+"."+codigo+";"+quantidade+";"+preco+">";

        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUri("your_cloudamqp_url"); // Configure corretamente sua URL
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

            String routingKey = args[0] + "." + args[1]; // "compra.ABEV3" ou "venda.PETR4"
            String message = args[2]; // Mensagem formatada com quantidade e preço

            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");

            channel.close();        
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        s.close();
    }
}
