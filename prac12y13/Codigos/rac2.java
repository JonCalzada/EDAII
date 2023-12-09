class SavingsAccount { public volatile int balance = 0; }
class ATM extends Sugar implements Runnable {
private int numAccounts = 0;
private SavingsAccount[] savingsAccount = null;
public ATM(int numAccounts, SavingsAccount[] savingsAccount) {
this.numAccounts = numAccounts;
this.savingsAccount = savingsAccount;
}
public void run() {
int fromAccount, toAccount, amount;
while (true) {
if (Thread.interrupted()) {
System.out.println("age()=" + age()
+ ", ATM was interrupted");
return;
}
fromAccount = (int) random(numAccounts);
toAccount = (int) random(numAccounts);
amount = 1 +
(int) random(savingsAccount[fromAccount].balance);
savingsAccount[fromAccount].balance -= amount;
savingsAccount[toAccount].balance += amount;
}
}
}
class Auditor extends Sugar implements Runnable {
private int numAccounts = 0;
private SavingsAccount[] savingsAccount = null;
public Auditor(int numAccounts, SavingsAccount[] savingsAccount) {
this.numAccounts = numAccounts;

this.savingsAccount = savingsAccount;
}
public void run() {
int total;
while (true) {
try { Thread.sleep(1000); }
catch (InterruptedException e) {
System.out.println("age()=" + age()
+ ", Auditor interrupted from sleep");
return;
}
total = 0;
for (int i = 0; i < numAccounts; i++)
total += savingsAccount[i].balance;
System.out.println("age()=" + age()
+ ", total is $" + total);
if (Thread.interrupted()) {
System.out.println("age()=" + age()
+ ", Auditor was interrupted");
return;
}
}
}
}
class Bank extends Sugar {
public static void main(String[] args) {
int numAccounts = 100;
int initialValue = 1000; // dollars
SavingsAccount[] savingsAccount = null;
try {
numAccounts = Integer.parseInt(args[0]);
initialValue = Integer.parseInt(args[1]);
} catch (Exception e) { /* use defaults */ }
savingsAccount = new SavingsAccount[numAccounts];
for (int i = 0; i < numAccounts; i++) {
savingsAccount[i] = new SavingsAccount();
savingsAccount[i].balance = initialValue;
}
System.out.println("Bank open with " + numAccounts
+ " accounts, each starting with $" + initialValue);
//new PseudoTimeSlicing(); // for Solaris, not Windows 95/NT
Thread atm = new Thread(
new ATM(numAccounts, savingsAccount));
Thread auditor = new Thread(
new Auditor(numAccounts, savingsAccount));
atm.start(); auditor.start();
try {
Thread.sleep(10000);
atm.interrupt(); atm.join();
Thread.sleep(3000);
auditor.interrupt(); auditor.join();
} catch (InterruptedException e) { /* ignored */ }
System.exit(0);
}
}
