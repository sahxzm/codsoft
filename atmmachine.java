import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class ATM {
    private final BankAccount account;
    private final Scanner scanner = new Scanner(System.in);

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void start() {
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1 : System.out.println("Balance: " + account.getBalance());
                case 2 : {
                    System.out.print("Deposit amount: ");
                    account.deposit(scanner.nextDouble());
                }
                case 3 : {
                    System.out.print("Withdraw amount: ");
                    boolean success = account.withdraw(scanner.nextDouble());
                    System.out.println(success ? "Withdrawal successful." : "Insufficient balance.");
                }
                case 4 : System.out.println("Thank you for using the ATM!");
                default : System.out.println("Choice doesn't exist. Please try again.");
            }
        } while (choice != 4);
    }

    private void displayMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }
}

public class atmmachine{
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(12345);
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}

