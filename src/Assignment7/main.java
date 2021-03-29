package Assignment7;
import java.util.*;
import java.io.*;

class User{
    float balance;
    String accountNumber;

    User()
    {
        balance = 0.0f;
        accountNumber = "0000";
        String fileName = accountNumber +".txt";
        try {
            FileWriter fileWriter = new FileWriter(fileName,false);
            fileWriter.write(balance+"\n");
            fileWriter.close();
        }
        catch(Exception e)
        {
            System.out.println("Error occurred while writing the file");
        }
    }

    User(String accountNumber)
    {
        this.accountNumber = accountNumber;
        String fileName = accountNumber +".txt";
        File file = new File(fileName);
        if(!file.exists()) {
            try {
                FileWriter fileWriter = new FileWriter(fileName, false);
                balance = 0.0f;
                fileWriter.write(balance + "\n");
                fileWriter.close();
            } catch (Exception e) {
                System.out.println("Error occurred while writing the file");
            }
        }
        else{
            try {
                Scanner reader = new Scanner(file);
                balance = Float.parseFloat(reader.nextLine());
                reader.close();
            }
            catch (Exception e)
            {
                System.out.println("Error occurred while reading the file");
            }
        }
    }

    User(float balance,String accountNumber)
    {
        this.balance = balance;
        this.accountNumber = accountNumber;
        String fileName = accountNumber +".txt";
        try {
            FileWriter fileWriter = new FileWriter(fileName,false);
            fileWriter.write(balance+"\n");
            fileWriter.close();
        }
        catch(Exception e)
        {
            System.out.println("Error occurred while writing the file");
        }
    }

    public void checkBalance() {
        String fileName = accountNumber +".txt";
        File file = new File(fileName);
        try {
            Scanner reader = new Scanner(file);
            float bal = Float.parseFloat(reader.nextLine());
            System.out.println("Account balance : "+bal);
        }
        catch (Exception e)
        {
            System.out.println("Error occurred while reading the file");
        }
    }

    public void deposit(float amount)
    {
        String fileName = accountNumber +".txt";
        try {
            FileWriter fw = new FileWriter(fileName,false);
            balance += amount;
            fw.write(balance+"\n");
            fw.close();
            System.out.println("The amount "+amount+" is successfully deposited");
            checkBalance();
        }
        catch (Exception e)
        {
            System.out.println("Error occurred while reading the file");
        }

    }

    public void withdraw(float amount)
    {
        String fileName = accountNumber +".txt";
        if(amount > balance)
        {
            System.out.println("Insufficient funds");
        }
        else {
            try {
                FileWriter fw = new FileWriter(fileName, false);
                balance -= amount;
                fw.write(balance + "\n");
                fw.close();
                System.out.println("Withdrawn amount :"+amount);
                checkBalance();
            } catch (Exception e) {
                System.out.println("Error occurred while reading the file");
            }
        }
    }


}

public class main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the account Number : ");
        String accNo = sc.nextLine();
        User user = new User(accNo);
        while (true)
        {
            System.out.println("\n\n------------- MENU -------------");
            System.out.println("1.) Deposit");
            System.out.println("2.) Withdraw");
            System.out.println("3.) Check balance");
            System.out.println("4.) Exit");
            System.out.print("\n\n Enter your choice -> ");
            try {
                int ch = sc.nextInt();
                System.out.println("\n--------------------------------");
                if(ch==1)
                {
                    System.out.print("\nEnter the amount to deposit : ");
                    float amount = sc.nextFloat();
                    user.deposit(amount);
                }
                else if(ch==2)
                {
                    System.out.print("\nEnter the amount to withdraw : ");
                    float amount = sc.nextFloat();
                    user.withdraw(amount);

                }
                else if(ch==3)
                {
                    user.checkBalance();
                }
                else if(ch==4)
                {
                    System.exit(1);
                }
                else {
                    throw new Exception("Invalid choice");
                }
            }
            catch (Exception e)
            {
                System.out.println("\nInvalid choice");
            }
        }

    }
}
