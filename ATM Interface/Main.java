import java.util.Scanner;

class BankAccount {
    long accountNumber;
    int pin;
    double balance;

    public BankAccount(long accountNumber, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class UserAccount extends BankAccount {
    public UserAccount() {
        super(0, 0, 0); 
    }

    public void setAccountDetails(long accountNumber, int pin, double initialBalance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = initialBalance;
    }
}

class ATM {
    private UserAccount userAccount;

    public ATM() {
        userAccount = new UserAccount();
    }

    public UserAccount authenticate(int accountNumber, int pin) {
        if (userAccount.getAccountNumber() == accountNumber && userAccount.getPin() == pin) {
            return userAccount;
        }
        return null;
    }

    public void displayMenu() {
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        long accountNumber;
        int pin;
        double initialBalance;

        System.out.println("Enter your account number:");
        accountNumber = scanner.nextLong();
        System.out.println("Enter your PIN:");
        pin = scanner.nextInt();
        System.out.println("Enter your initial balance:");
        initialBalance = scanner.nextDouble();

        userAccount.setAccountDetails(accountNumber, pin, initialBalance);

        System.out.println("Account created successfully.");

        int choice;
        do {
            displayMenu();
            System.out.println("Enter your choice:");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Balance: Rs. " + userAccount.getBalance());
                    break;
                case 2:
                    System.out.println("Enter deposit amount:");
                    double depositAmount = scanner.nextDouble();
                    userAccount.deposit(depositAmount);
                    System.out.println("Deposit successful. \nNew balance: Rs. " + userAccount.getBalance());
                    break;
                case 3:
                    System.out.println("Enter withdrawal amount:");
                    double withdrawalAmount = scanner.nextDouble();
                    if (userAccount.withdraw(withdrawalAmount)) {
                        System.out.println("Withdrawal successful. \nNew balance: Rs. " + userAccount.getBalance());
                    } else {
                        System.out.println("Insufficient funds.");
                    }
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 4);

        scanner.close();
    }
}

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
