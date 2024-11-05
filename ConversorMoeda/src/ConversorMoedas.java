import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner; // Importação da classe Scanner

public class ConversorMoedas {
    private static final String API_KEY = "6f8ddf476f9c7a31fb8f4852"; // Substitua pela sua API Key
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public void converter(String de, String para) {
        try {
            double taxa = obterTaxaCambio(de, para);

            System.out.println("Digite o valor a ser convertido: ");
            // Scanner scanner = new Scanner(System.in);
            double valor = Converter.scanner.nextDouble(); // Use o scanner da classe Converter

            double resultado = valor * taxa;
            System.out.println(String.format("%.2f %s = %.2f %s%n", valor, de, resultado, para));

        } catch (Exception e) {
            System.out.println("Erro ao converter moeda: " + e.getMessage());
        }
    }

    private double obterTaxaCambio(String de, String para) {
        try {
            URL url = new URL(API_URL + de);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(content.toString()).getAsJsonObject();
            JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");
            return conversionRates.get(para).getAsDouble();
        } catch (IOException e) {
            System.out.println("Erro ao obter taxa de câmbio: " + e.getMessage());
            return 0.0; // Retorna 0.0 em caso de erro
        }
    }
}