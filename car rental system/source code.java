import java.util.ArrayList;
import java.util.Scanner;

// ---- Car Class ----
class Car {
    private String carId;
    private String model;
    private String brand;
    private double pricePerDay;
    private boolean isAvailable;

    public Car(String carId, String model, String brand, double pricePerDay) {
        this.carId = carId;
        this.model = model;
        this.brand = brand;
        this.pricePerDay = pricePerDay;
        this.isAvailable = true;
    }

    public String getCarId() { return carId; }
    public String getModel() { return model; }
    public String getBrand() { return brand; }
    public double getPricePerDay() { return pricePerDay; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public void showCarDetails() {
        System.out.println("Car ID: " + carId + ", Brand: " + brand + ", Model: " + model +
                ", Price/Day: " + pricePerDay + ", Available: " + isAvailable);
    }
}

// ---- Customer Class ----
class Customer {
    private String customerId;
    private String name;
    private String phone;
    private Car rentedCar;

    public Customer(String customerId, String name, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.rentedCar = null;
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public Car getRentedCar() { return rentedCar; }
    public void setRentedCar(Car car) { this.rentedCar = car; }

    public void showCustomerDetails() {
        System.out.println("Customer ID: " + customerId + ", Name: " + name + ", Phone: " + phone +
                ", Rented Car: " + (rentedCar != null ? rentedCar.getCarId() : "None"));
    }
}

// ---- RentalAgency Class ----
class RentalAgency {
    private ArrayList<Car> cars;
    private ArrayList<Customer> customers;

    public RentalAgency() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void listAvailableCars() {
        boolean found = false;
        for (Car car : cars) {
            if (car.isAvailable()) {
                car.showCarDetails();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available cars.");
        }
    }

    public Customer findCustomerById(String id) {
        for (Customer c : customers) {
            if (c.getCustomerId().equals(id)) return c;
        }
        return null;
    }

    public Car findCarById(String id) {
        for (Car car : cars) {
            if (car.getCarId().equals(id)) return car;
        }
        return null;
    }

    public void rentCar(String customerId, String carId) {
        Customer customer = findCustomerById(customerId);
        Car car = findCarById(carId);

        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        if (car == null) {
            System.out.println("Car not found!");
            return;
        }

        if (!car.isAvailable()) {
            System.out.println("Car is already rented!");
            return;
        }

        if (customer.getRentedCar() != null) {
            System.out.println("Customer already rented a car!");
            return;
        }

        car.setAvailable(false);
        customer.setRentedCar(car);
        System.out.println("Car rented successfully!");
    }

    public void returnCar(String customerId) {
        Customer customer = findCustomerById(customerId);

        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        Car car = customer.getRentedCar();

        if (car == null) {
            System.out.println("Customer has no rented car!");
            return;
        }

        car.setAvailable(true);
        customer.setRentedCar(null);
        System.out.println("Car returned successfully!");
    }

    public void listAllRentals() {
        boolean found = false;
        for (Customer c : customers) {
            if (c.getRentedCar() != null) {
                System.out.println(c.getName() + " rented " + c.getRentedCar().getCarId());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No active rentals.");
        }
    }
}

// ---- Main Class ----
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        RentalAgency agency = new RentalAgency();

        // Add sample cars
        agency.addCar(new Car("C1", "Corolla", "Toyota", 50));
        agency.addCar(new Car("C2", "Civic", "Honda", 60));
        agency.addCar(new Car("C3", "Model S", "Tesla", 120));

        while (true) {
            System.out.println("\n--- Car Rental System ---");
            System.out.println("1. Register Customer");
            System.out.println("2. List Available Cars");
            System.out.println("3. Rent Car");
            System.out.println("4. Return Car");
            System.out.println("5. View Rentals");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = input.nextInt();
            input.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Customer ID: ");
                    String cid = input.nextLine();
                    System.out.print("Enter Name: ");
                    String name = input.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = input.nextLine();
                    agency.addCustomer(new Customer(cid, name, phone));
                    System.out.println("Customer registered.");
                    break;

                case 2:
                    System.out.println("Available Cars:");
                    agency.listAvailableCars();
                    break;

                case 3:
                    System.out.print("Enter Customer ID: ");
                    String custId = input.nextLine();
                    System.out.print("Enter Car ID: ");
                    String carId = input.nextLine();
                    agency.rentCar(custId, carId);
                    break;

                case 4:
                    System.out.print("Enter Customer ID: ");
                    String custId2 = input.nextLine();
                    agency.returnCar(custId2);
                    break;

                case 5:
                    System.out.println("Current Rentals:");
                    agency.listAllRentals();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
