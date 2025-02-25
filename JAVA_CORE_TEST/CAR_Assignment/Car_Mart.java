import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

abstract class Car {
    private int CarID;
    private String Company;
    private String Model;
    private int Seater;
    private String FuelType;
    private String Type;
    private double Price;
    private boolean Sold;

    Car(String Type, boolean Sold) {
        this.Type = Type;
        this.Sold = Sold;
        this.Company = CompanyNameInput.readCompanyName();
        this.Model = CarModelInput.readCarModel();
        this.Seater = SeaterInput.readCarSeater();
        this.FuelType = CarFuelTypeInput.readCarFuelType();
        this.Price =  CarPriceInput.readCarPrice();
    }

    public String getCompany() {
        return Company;
    }
    public String getModel() {
        return Model;
    }
    public int getSeater() {
        return Seater;
    }
    public String getFuelType() {
        return FuelType;
    }
    public String getType() {
        return Type;
    }
    public double getPrice() {
        return Price;
    }
    public boolean getSold() {
        return Sold;
    }
}

final class Sedan extends Car {
    Sedan() {
        super("Sedan", false);
    }

    private static Sedan sedan = null;

    public static Sedan getSedan() {
        if (sedan == null) {
            sedan = new Sedan();
        }
        return sedan;
    }
    
}    

final class SUV extends Car {
    SUV() {
        super("SUV", false);
    }
    private static SUV suv = null;

    public static SUV getSUV() {
        if (suv == null) {
            suv = new SUV();
        }
        return suv;
    }
}

final class Hatchback extends Car {
    Hatchback() {
        super("Hatchback", false);
    }
    private static Hatchback hatchback = null;

    public static Hatchback getHatchback() {
        if (hatchback == null) {
            hatchback = new Hatchback();
        }
        return hatchback;
    }
}
final class OtherType extends Car {
    OtherType(String type) {
        super(type, false);
    }

    private static OtherType otherType = null;

    public static OtherType getOtherType() {
        if (otherType == null) {
            otherType = new OtherType(CarTypeInput.readCarType());
        }
        return otherType;
    }
}

// Singleton class to get the buffer connection
final class BufferConn {
    private static BufferedReader br = null;
 
    private BufferConn() {
    }

    public static BufferedReader getBufferConnection() {
        if (br == null) {
        	br = new BufferedReader(new InputStreamReader(System.in));
        }
        return br;
    }

    public static void closeBufferConnection(){
        try {
			br.close();
		} catch (IOException e) {
			System.out.println(e);
		}
    }
}

final class JdbcRowSetConnection {
    private static JdbcRowSet rs = null;

    private JdbcRowSetConnection() {
    }

    public static JdbcRowSet getJdbcRowSetConnection() throws SQLException {
        if (rs == null) {
            rs = RowSetProvider.newFactory().createJdbcRowSet();
            rs.setUrl("jdbc:postgresql://localhost:5432/CarDB");
            rs.setUsername("postgres");
            rs.setPassword("tiger");
        }
        return rs;
    }

    public static void closeJdbcRowSetConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
    }
}

interface CarDAO {
    abstract void storeCar(Car car);
    abstract void searchAllUnsoldCar();
    abstract void searchCarByCompany();
    abstract void searchCarByType();
    abstract void searchCarByPriceRange();
    abstract void updateCarPrice();
    abstract void displaySoldCars();
    abstract void soldCar();
}

class CarDAOImpl implements CarDAO {
    @Override
    public void storeCar(Car car) {
        try {
            JdbcRowSet rs = JdbcRowSetConnection.getJdbcRowSetConnection();
            rs.setCommand("SELECT * FROM car WHERE 1=0");
            rs.execute();
            rs.moveToInsertRow();
            rs.updateString("company", car.getCompany());
            rs.updateString("model", car.getModel());
            rs.updateInt("seater", car.getSeater());
            rs.updateString("fueltype", car.getFuelType());
            rs.updateString("type", car.getType());
            rs.updateDouble("price", car.getPrice());
            rs.updateBoolean("sold", car.getSold());
            rs.insertRow();
            rs.moveToCurrentRow();
            System.out.println("Car Added Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void searchAllUnsoldCar() {
        try {
            JdbcRowSet rs = JdbcRowSetConnection.getJdbcRowSetConnection();
            rs.setCommand("SELECT * FROM car WHERE sold = false");
            rs.execute();
            displayCar(rs);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void searchCarByCompany() {
        try {
            JdbcRowSet rs = JdbcRowSetConnection.getJdbcRowSetConnection();
            rs.setCommand("SELECT * FROM car WHERE company = '" + CompanyNameInput.readCompanyName() + "'");
            rs.execute();
            displayCar(rs);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void searchCarByType() {
        try {
            JdbcRowSet rs = JdbcRowSetConnection.getJdbcRowSetConnection();
            rs.setCommand("SELECT * FROM car WHERE type = '" + CarTypeInput.readCarType() + "'");
            rs.execute();
            displayCar(rs);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void searchCarByPriceRange() {
        try {
            double[] priceRange = PriceRangeInput.readPriceRange();
            JdbcRowSet rs = JdbcRowSetConnection.getJdbcRowSetConnection();
            rs.setCommand("SELECT * FROM car WHERE price BETWEEN " + priceRange[0] + " AND " + priceRange[1]);
            rs.execute();
            displayCar(rs);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void updateCarPrice() {
        try {
            JdbcRowSet rs = JdbcRowSetConnection.getJdbcRowSetConnection();
            rs.setCommand("SELECT * FROM car WHERE cid = " + CIdInput.readCId());
            rs.execute();
            if (rs.next()) {
                displayCar(rs);
                rs.updateDouble("price", CarPriceInput.readCarPrice());
                rs.updateRow();
                System.out.println("Car price updated successfully");
            } else {
                System.out.println("No Car Found with the given ID");
            }
            rs.updateDouble("price", CarPriceInput.readCarPrice());
            rs.updateRow();
            System.out.println("Car price updated successfully");
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

    @Override
    public void displaySoldCars() {
        try {
            JdbcRowSet rs = JdbcRowSetConnection.getJdbcRowSetConnection();
            rs.setCommand("SELECT * FROM car WHERE sold = true");
            rs.execute();
            displayCar(rs);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Override
    public void soldCar() {
        try {
            JdbcRowSet rs = JdbcRowSetConnection.getJdbcRowSetConnection();
            rs.setCommand("SELECT * FROM car WHERE cid = " + CIdInput.readCId());
            rs.execute();
            rs.next();
            rs.updateBoolean("sold", true);
            rs.updateRow();
            System.out.println("Car Sold Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void displayCar(JdbcRowSet rs) {
        System.out.println("-----------------------------------------------");
        try {
            if (!rs.next()) {
                System.out.println("No Car Found");
                return;
            }
            do {
                System.out.println("Car ID: " + rs.getInt("cid"));
                System.out.println("Company: " + rs.getString("company"));
                System.out.println("Model: " + rs.getString("model"));
                System.out.println("Seater: " + rs.getInt("seater"));
                System.out.println("Fuel Type: " + rs.getString("fueltype"));
                System.out.println("Type: " + rs.getString("type"));
                System.out.println("Price: " + rs.getDouble("price"));
                System.out.println("Sold: " + rs.getBoolean("sold"));
                System.out.println("------------------------------------------------");
            }while (rs.next());

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class CarFactory {
    public static Car addCar(String type) {
        return switch (type) {
            case "Sedan" -> Sedan.getSedan();
            case "SUV" -> SUV.getSUV();
            case "Hatchback" -> Hatchback.getHatchback();
            case "OtherType" -> OtherType.getOtherType();
            default -> null;
        };
    }
}

public class Car_Mart {
    public static void main(String[] args) {
        System.out.println("------------- Welcome to Car Mart -------------");
        int choiceOne = 0, choiceTwo = 0, choiceThree = 0, choiceFour = 0;
        CarDAOImpl carMenuOperation = new CarDAOImpl();
        do {
            System.out.println("----------------- Main Menu -----------------");
            System.out.println("1. Add a Car");
            System.out.println("2. Search a Car");
            System.out.println("3. Update a Car price");
            System.out.println("4. Sold Car");
            System.out.println("5. Exit");
            System.out.println("---------------------------------------------");
            choiceOne = Menu.readChoice(5);
            switch (choiceOne) {
                case 1 :
                    do {
                        System.out.println();
                        System.out.println("----------------- Add Car Menu -----------------");
                        System.out.println("1. Sedan");
                        System.out.println("2. SUV");
                        System.out.println("3. Hatchback");
                        System.out.println("4. Other Type");
                        System.out.println("5. Exit");
                        System.out.println("------------------------------------------------");
                        choiceTwo = Menu.readChoice(5);
                        switch(choiceTwo) {
                            case 1 -> carMenuOperation.storeCar(CarFactory.addCar("Sedan"));
                            case 2 -> carMenuOperation.storeCar(CarFactory.addCar("SUV"));
                            case 3 -> carMenuOperation.storeCar(CarFactory.addCar("Hatchback"));
                            case 4 -> carMenuOperation.storeCar(CarFactory.addCar("OtherType"));
                            case 5 -> choiceTwo = 5;
                        }
                    } while (choiceTwo != 5);
                    break;
                case 2 :
                    do { 
                        System.out.println();
                        System.out.println("-------- Search Car Menu (Search Based) --------");
                        System.out.println("1. All Unsold Cars");
                        System.out.println("2. Company of car");
                        System.out.println("3. Type of car");
                        System.out.println("4. Price Range");
                        System.out.println("5. Exit");
                        System.out.println("-----------------------------------------------");
                        choiceThree = Menu.readChoice(5);
                        switch(choiceThree) {
                            case 1 -> carMenuOperation.searchAllUnsoldCar();
                            case 2 -> carMenuOperation.searchCarByCompany();
                            case 3 -> carMenuOperation.searchCarByType();
                            case 4 -> carMenuOperation.searchCarByPriceRange();
                            case 5 -> choiceThree = 5;
                        }
                    } while (choiceThree != 5) ;
                    break;
                case 3 :
                    carMenuOperation.updateCarPrice();
                    break;
                case 4 :
                    do {
                        System.out.println();
                        System.out.println("-------- Sold Car Menu --------");
                        System.out.println("1. All Sold Car");
                        System.out.println("2. Update Unsold car to Sold");
                        System.out.println("3. Exit");
                        System.out.println("------------------------------");
                        choiceFour = Menu.readChoice(3);
                        switch (choiceFour) {
                            case 1 -> carMenuOperation.displaySoldCars();
                            case 2 -> carMenuOperation.soldCar();
                            case 3 -> choiceFour = 3;
                        }
                    } while(choiceFour != 3);
                break;
            }
        } while(choiceOne != 5);
        BufferConn.closeBufferConnection();
    }


}


class Menu {
    public static int readChoice(int maxChoice) {
        try {
            BufferedReader br = BufferConn.getBufferConnection();
        while (true) {
            System.out.println("Enter your choice:");
            try {
                int choice = Integer.parseInt(br.readLine());
                if (choice < 1 || choice > maxChoice) {
                    throw new InvalidChoiceException();
                }
                return choice;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number only.");
            } catch (InvalidChoiceException e) {
                e.displayMessage(maxChoice);
                return 0;
            }
        }
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
}

class CIdInput {
    public static int readCId() {
        BufferedReader reader = BufferConn.getBufferConnection();
        while (true) {
            System.out.println("Enter your Car Id:");
            try {
                int id = Integer.parseInt(reader.readLine());
                return id;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number only.");
            } catch (InvalidIDException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("Error reading input: " + e.getMessage());
            }
        }
    }
}


class CompanyNameInput {
    public static String readCompanyName() {
        BufferedReader reader = BufferConn.getBufferConnection();
        while (true) {
            System.out.println("Enter Company Name: ");
            try {
                return reader.readLine();
            } catch (IOException e) {
                System.out.println("Error reading input: " + e.getMessage());
            }
        }
    }
}

class CarModelInput {
    public static String readCarModel() {
        BufferedReader reader = BufferConn.getBufferConnection();
        while (true) {
            System.out.println("Enter Car Model: ");
            try {
                return reader.readLine();
            } catch (IOException e) {
                System.out.println("Error reading input: " + e.getMessage());
            }
        }
    }
}

class CarTypeInput {
    public static String readCarType() {
        BufferedReader reader = BufferConn.getBufferConnection();
        while (true) {
            System.out.println("Enter Car Model: ");
            try {
                return reader.readLine();
            } catch (IOException e) {
                System.out.println("Error reading input: " + e.getMessage());
            }
        }
    }
}

class SeaterInput {
    public static int readCarSeater() {
        BufferedReader reader = BufferConn.getBufferConnection();
        while (true) {
            System.out.println("Enter Seater: ");
            try {
                return Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number only.");
            } catch (IOException e) {
                System.out.println("Error reading input: " + e.getMessage());
            }
        }
    }
}

class CarFuelTypeInput {
    public static String readCarFuelType() {
        BufferedReader reader = BufferConn.getBufferConnection();
        while (true) {
            System.out.println("Enter Fuel Type: ");
            try {
                return reader.readLine();
            } catch (IOException e) {
                System.out.println("Error reading input: " + e.getMessage());
            }
        }
    }
}


class CarPriceInput {

    public static double readCarPrice() {
        BufferedReader reader = BufferConn.getBufferConnection();
        while (true) {
            System.out.println("Enter Price : ");
            try {
                return Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number only.");
            } catch (IOException e) {
                System.out.println("Error reading input: " + e.getMessage());
            }
        }
    }
}

class PriceRangeInput {
    public static double[] readPriceRange() {
        BufferedReader reader = BufferConn.getBufferConnection();
        double min_price = 0, max_price = 0;
        while(true) {
            System.out.println("Enter Min Price: ");
            try {
                min_price = Double.parseDouble(reader.readLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number only.");
            } catch (IOException e) {
                System.out.println("Error reading input: " + e.getMessage());
            }
        }
        while(true) {
            System.out.println("Enter max Price: ");
            try {
                max_price = Double.parseDouble(reader.readLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number only.");
            } catch (IOException e) {
                System.out.println("Error reading input: " + e.getMessage());
            }
        }
        return new double[] {min_price, max_price};
    }
}

class InvalidChoiceException extends RuntimeException {
    public void displayMessage(int maxChoice) {
        System.out.println("Please enter a choice between 1 and " + maxChoice);
    }
}
class InvalidIDException extends RuntimeException {
    InvalidIDException(String msg) {
        super(msg);
    }

    public void displayMessage() {
        System.out.println("Please enter a number only");
    }
}
