import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {


        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.print("Entre com os dados do aluguel\n" +
                "Modelo do carro:");
        Vehicle carModel = new Vehicle(sc.nextLine());

        System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);

        System.out.print("Retorno (dd/MM/yyyy hh:mm): ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);

        CarRental carRental = new CarRental(start, finish, carModel);

        System.out.print("Entre com o preço por hora: ");
        Double hourPrice = sc.nextDouble();

        System.out.print("Entre com o preço por dia: ");
        Double dayPrice = sc.nextDouble();


        RentalService rentalService = new RentalService(hourPrice, dayPrice, new BrazilTaxService());
        rentalService.processInvoice(carRental);

        System.out.println("FATURA: ");
        System.out.print("Pagamento basico: " + String.format("%.2f \n", carRental.getInvoice().getBasicPayment()));
        System.out.print("Imposto: " + String.format("%.2f \n", carRental.getInvoice().getTax()));
        System.out.print("Pagamento total: " + String.format("%.2f \n", carRental.getInvoice().getTotalPayment()));


    }
}