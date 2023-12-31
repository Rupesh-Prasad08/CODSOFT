import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
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
    private BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void displayOptions() {
        System.out.println("Options:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void processOptions() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayOptions();
            System.out.print("Enter option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    boolean withdrawSuccess = bankAccount.withdraw(withdrawAmount);
                    if (withdrawSuccess) {
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Insufficient balance.");
                    }
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    bankAccount.deposit(depositAmount);
                    System.out.println("Deposit successful.");
                    break;

                case 3:
                    System.out.println("Current balance: " + bankAccount.getBalance());
                    break;

                case 4:
                    System.out.println("Exiting the ATM.");
                    return;

                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        // Creating a bank account with initial balance
        BankAccount userAccount = new BankAccount(1000.0);

        // Creating an ATM instance and connecting it to the bank account
        ATM atm = new ATM(userAccount);

        // Starting the ATM interface
        atm.processOptions();
    }
}
