import java.util.Scanner;

// 1.
// public class sheet5 {
//     public static void main(String[] args){
//         try {
//             int a = 100;
//             int b = 0;
//             int c = a / b;
//         }
//         catch(ArithmeticException e) {
//             System.out.println("Arithmetic Exception" + e.getMessage());
//         }
//         try {
//             String str = null; 
//             System.out.println(str.length()); 
//             } 
//         catch (NullPointerException e) {
//             System.out.println("NullPointerException: " + e.getMessage()); 
//         }
//         try {
//             int[] arr = {10, 20, 30}; 
//             System.out.println(arr[5]); 
//         } 
//         catch (ArrayIndexOutOfBoundsException e) {
//              System.out.println( "ArrayIndexOutOfBoundsException: " + e.getMessage() ); }
//         try { 
//             String number = "ABC"; 
//             int n = Integer.parseInt(number); 
//         } catch (NumberFormatException e) {
//              System.out.println("NumberFormatException: " + e.getMessage()); }
//     }
// }

// 2.
// public class sheet5 {
//     public static void main(String[] args) {
//         try {
//             System.out.println("Outer try block starts");
//             try {
//                 System.out.println("Inner try block starts");

//                 String[] arr = new String[5];
//                 arr[10] = "Hello";  // ArrayIndexOutOfBoundsException
//             }
//             catch (ArithmeticException e) {
//                 System.out.println("Arithmetic Exception: " + e.getMessage());
//             }
//         }
//         catch (ArrayIndexOutOfBoundsException e) {
//             System.out.println("ArrayIndexOutOfBoundsException: " + e.getMessage());
//         }
//         catch (Exception e) {
//             System.out.println("Exception: " + e.getMessage());
//         }
//         System.out.println("Program ends");
//     }
// }

// 3. 
// import java.util.InputMismatchException;

// public class sheet5 {
//     public static double add(double a, double b) {
//         return a + b;
//     }
//     public static double sub(double a, double b) {
//         return a - b;
//     }
//     public static double mul(double a, double b) {
//         return a * b;
//     }
//     public static double div(double a, double b) {
//         if (b == 0) {
//             throw new ArithmeticException("Cannot divide by zero.");
//         }
//         return a / b;
//     }
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         try {
//             System.out.print("Enter first number: ");
//             double a = sc.nextDouble();
//             System.out.print("Enter second number: ");
//             double b = sc.nextDouble();
//             System.out.print("Enter operator (+, -, *, /): ");
//             char op = sc.next().charAt(0);
//             switch (op) {
//                 case '+':
//                     System.out.println("Result: " + add(a, b));
//                     break;
//                 case '-':
//                     System.out.println("Result: " + sub(a, b));
//                     break;
//                 case '*':
//                     System.out.println("Result: " + mul(a, b));
//                     break;
//                 case '/':
//                     System.out.println("Result: " + div(a, b));
//                     break;
//                 default:
//                     throw new IllegalArgumentException("Invalid operator.");
//             }
//         }
//         catch (InputMismatchException e) {
//             System.out.println("Error: Invalid numeric input.");
//         }
//         catch (ArithmeticException e) {
//             System.out.println("Error: " + e.getMessage());
//         }
//         catch (IllegalArgumentException e) {
//             System.out.println("Error: " + e.getMessage());
//         }
//         sc.close();
//     }
// }

// 4.
// class InvalidMarksException extends Exception {
//     public InvalidMarksException(String message){
//         super(message);
//     }
// }

// class sheet5{
//     public static void main(String[] agrs){
//         Scanner sc = new Scanner(System.in);
//         System.out.print("Enter number of subjects: ");
//         int n = sc.nextInt();

//         int[] marks = new int[n];
//         int total=0;
//         try {
//             for (int i = 0; i < n; i++) {
//                 System.out.print("Enter marks for subject " + (i + 1) + ": ");
//                 marks[i] = sc.nextInt();
//                 if (marks[i] < 0 || marks[i] > 100) {
//                     throw new InvalidMarksException(
//                         "Invalid marks! Marks must be between 0 and 100."
//                     );
//                 }
//                 total += marks[i];
//             }
//             double percentage = (double) total / n;
//             String grade;
//             if (percentage >= 90) {
//                 grade = "A";
//             }
//             else if (percentage >= 80) {
//                 grade = "B";
//             }
//             else if (percentage >= 70) {
//                 grade = "C";
//             }
//             else if (percentage >= 60) {
//                 grade = "D";
//             }
//             else {
//                 grade = "F";
//             }
//             System.out.println("\nTotal Marks: " + total);
//             System.out.println("Percentage: " + percentage + "%");
//             System.out.println("Grade: " + grade);
//         }
//         catch (InvalidMarksException e) {
//             System.out.println("\nError: " + e.getMessage());

//         }
//         sc.close();
//     }
// }


// 5.
// class InsufficientBalanceException extends Exception {
//     public InsufficientBalanceException(String message) {
//         super(message);
//     }
// }
// class InvalidAmountException extends Exception {
//     public InvalidAmountException(String message) {
//         super(message);
//     }
// }
// class AccountNotFoundException extends Exception {

//     public AccountNotFoundException(String message) {
//         super(message);
//     }
// }
// class Account {

//     private int accountNumber;
//     private double balance;

//     public Account(int accountNumber, double balance) {
//         this.accountNumber = accountNumber;
//         this.balance = balance;
//     }

//     public int getAccountNumber() {
//         return accountNumber;
//     }

//     public void deposit(double amount) throws InvalidAmountException {

//         if (amount <= 0) {
//             throw new InvalidAmountException(
//                 "Deposit amount must be greater than zero."
//             );
//         }

//         balance = balance + amount;

//         System.out.println("Deposit successful.");
//         System.out.println("Current Balance: " + balance);
//     }

//     public void withdraw(double amount)
//             throws InvalidAmountException, InsufficientBalanceException {

//         if (amount <= 0) {
//             throw new InvalidAmountException(
//                 "Withdrawal amount must be greater than zero."
//             );
//         }

//         if (amount > balance) {
//             throw new InsufficientBalanceException(
//                 "Insufficient balance."
//             );
//         }

//         balance = balance - amount;

//         System.out.println("Withdrawal successful.");
//         System.out.println("Current Balance: " + balance);
//     }
// }


// public class sheet5 {
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         Account account = new Account(101, 5000);
//         try {
//             System.out.print("Enter account number: ");
//             int accountNumber = sc.nextInt();
//             // Check account number
//             if (accountNumber != account.getAccountNumber()) {

//                 throw new AccountNotFoundException("Account number not found.");
//             }
//             System.out.print("Enter deposit amount: ");
//             double depositAmount = sc.nextDouble();
//             account.deposit(depositAmount);
//             System.out.print("Enter withdrawal amount: ");
//             double withdrawalAmount = sc.nextDouble();
//             account.withdraw(withdrawalAmount);
//         }
//         catch (InvalidAmountException e) {
//             System.out.println("Error: " + e.getMessage());
//         }
//         catch (InsufficientBalanceException e) {
//             System.out.println("Error: " + e.getMessage());
//         }
//         catch (AccountNotFoundException e) {
//             System.out.println("Error: " + e.getMessage());
//         }
//         sc.close();
//     }
// }

// 6.
// class InvalidUsernameException extends Exception {

//     public InvalidUsernameException(String message) {
//         super(message);
//     }
// }
// class InvalidPasswordException extends Exception {

//     public InvalidPasswordException(String message) {
//         super(message);
//     }
// }
// class AccountLockedException extends Exception {

//     public AccountLockedException(String message) {
//         super(message);
//     }
// }
// public class sheet5 {

//     public static void main(String[] args) {

//         Scanner sc = new Scanner(System.in);

//         String correctUsername = "Tanish";
//         String correctPassword = "12345";

//         int attempts = 0;
//         int maxAttempts = 3;

//         while (attempts < maxAttempts) {

//             try {

//                 System.out.print("Enter username: ");
//                 String username = sc.nextLine();

//                 System.out.print("Enter password: ");
//                 String password = sc.nextLine();

//                 if (!username.equals(correctUsername)) {

//                     attempts++;

//                     throw new InvalidUsernameException(
//                         "Invalid Username."
//                     );
//                 }

//                 if (!password.equals(correctPassword)) {

//                     attempts++;

//                     throw new InvalidPasswordException(
//                         "Invalid Password."
//                     );
//                 }

//                 System.out.println("Login Successful!");
//                 break;
//             }

//             catch (InvalidUsernameException e) {
//                 System.out.println(
//                     "Login Failed: " + e.getMessage()
//                 );

//                 System.out.println(
//                     "Remaining attempts: "
//                     + (maxAttempts - attempts)
//                 );
//             }

//             catch (InvalidPasswordException e) {

//                 System.out.println(
//                     "Login Failed: " + e.getMessage()
//                 );

//                 System.out.println(
//                     "Remaining attempts: "
//                     + (maxAttempts - attempts)
//                 );
//             }

//             finally {
//                 System.out.println("Login attempt completed.\n");
//             }
//         }
//         try {
//             if (attempts == maxAttempts) {
//                 throw new AccountLockedException("Account Locked! Maximum login attempts reached.");
//             }
//         }
//         catch (AccountLockedException e) {

//             System.out.println(e.getMessage());

//         }
//         sc.close();
//     }
// }

// 7.
import java.util.ArrayList;
class ApplicationException extends Exception {
    public ApplicationException(String message) {
        super(message);
    }
}
class ProductException extends ApplicationException {
    public ProductException(String message) {
        super(message);
    }
}
class ProductNotFoundException extends ProductException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
class OutOfStockException extends ProductException {
    public OutOfStockException(String message) {
        super(message);
    }
}
class PaymentException extends ApplicationException {
    public PaymentException(String message) {
        super(message);
    }
}
class InvalidPaymentException extends PaymentException {
    public InvalidPaymentException(String message) {
        super(message);
    }
}
class InsufficientFundsException extends PaymentException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
class OrderException extends ApplicationException {
    public OrderException(String message) {
        super(message);
    }
}
class EmptyCartException extends OrderException {
    public EmptyCartException(String message) {
        super(message);
    }
}
class Product {
    String name;
    double price;
    int stock;
    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
class ShoppingCart {
    ArrayList<Product> products = new ArrayList<>();
    public void addProduct(Product product)
            throws OutOfStockException {
        if (product.stock <= 0) {
            throw new OutOfStockException(
                    product.name + " is out of stock."
            );
        }
        products.add(product);
        product.stock--;
        System.out.println(product.name + " added to cart.");
    }
    public Product searchProduct(String name)
            throws ProductNotFoundException {
        for (Product p : products) {
            if (p.name.equalsIgnoreCase(name)) {
                return p;
            }
        }
        throw new ProductNotFoundException(
                "Product not found."
        );
    }
    public void removeProduct(String name)
            throws ProductNotFoundException {
        Product product = searchProduct(name);
        products.remove(product);
        System.out.println(
                product.name + " removed from cart."
        );
    }
    public double getTotal() {
        double total = 0;
        for (Product p : products) {
            total = total + p.price;
        }
        return total;
    }
    public void makePayment(double amount)
            throws EmptyCartException,
            InvalidPaymentException,
            InsufficientFundsException {
        if (products.isEmpty()) {
            throw new EmptyCartException(
                    "Cart is empty."
            );
        }
        if (amount <= 0) {
            throw new InvalidPaymentException(
                    "Payment amount must be greater than zero."
            );
        }
        double total = getTotal();
        if (amount < total) {
            throw new InsufficientFundsException(
                    "Insufficient funds. Total amount is: "
                            + total
            );
        }
        System.out.println("Payment successful.");
        System.out.println("Total amount: " + total);
        products.clear();
    }
}
public class sheet5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Product p1 = new Product("Laptop", 50000, 2);
        Product p2 = new Product("Phone", 20000, 1);
        Product p3 = new Product("Headphones", 3000, 0);
        ShoppingCart cart = new ShoppingCart();
        try {
            System.out.println("Adding products...");
            cart.addProduct(p1);
            cart.addProduct(p2);
            System.out.println(
                    "\nCart Total: "
                            + cart.getTotal()
            );
            System.out.print(
                    "\nEnter payment amount: "
            );
            double amount = sc.nextDouble();
            cart.makePayment(amount);
        }
        catch (ProductException e) {
            System.out.println(
                    "Product Error: "
                            + e.getMessage()
            );
        }
        catch (PaymentException e) {
            System.out.println(
                    "Payment Error: "
                            + e.getMessage()
            );
        }
        catch (OrderException e) {
            System.out.println(
                    "Order Error: "
                            + e.getMessage()
            );
        }
        finally {
            System.out.println(
                    "\nShopping application ended."
            );
            sc.close();
        }
    }
}