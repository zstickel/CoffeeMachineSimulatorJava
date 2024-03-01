package machine;

import java.util.Scanner;

public class CoffeeMachine {

    public static void main(String[] args) {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        NewCoffeeMachine newCoffeeMachine = new NewCoffeeMachine();
        while (!exit) {
            String input = scanner.nextLine();
            exit = newCoffeeMachine.operate(input);
        }
    }

}

class NewCoffeeMachine {
    static int water = 400;
    static int milk = 540;
    static int beans = 120;
    static int cups = 9;
    static int money = 550;
    MachineState machineState = MachineState.CHOOSINGACTION;
    FillItem fillItem = FillItem.WATER;
    enum MachineState {
        CHOOSINGACTION, CHOOSINGTYPE, FILLING
    }

    enum FillItem {
        WATER, MILK, BEANS, CUPS
    }
    NewCoffeeMachine(){
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }
    boolean operate(String input){
        boolean exit = false;
        switch (machineState) {
            case CHOOSINGACTION:
                exit = completeAction(input);
                break;
            case CHOOSINGTYPE:
                buyCoffee(input);
                break;
            case FILLING:
                addSupply(input);
                break;
        }
        return exit;
    }


    void addSupply(String input){
        switch(fillItem){
            case WATER:
                water += Integer.parseInt(input);
                System.out.println("Write how many ml of milk you want to add:");
                fillItem = FillItem.MILK;
                break;
            case MILK:
                milk += Integer.parseInt(input);
                System.out.println("Write how many grams of coffee beans you want to add:");
                fillItem = FillItem.BEANS;
                break;
            case BEANS:
                beans += Integer.parseInt(input);
                System.out.println("Write how many disposable cups you want to add:");
                fillItem = FillItem.CUPS;
                break;
            case CUPS:
                cups += Integer.parseInt(input);
                writeActionMessage();
                fillItem = FillItem.WATER;
                machineState = MachineState.CHOOSINGACTION;
                break;
        }
    }

    boolean completeAction(String input){
        boolean exit = false;
        switch (input){
            case "remaining":
                printCoffeeState();
                writeActionMessage();
                machineState = MachineState.CHOOSINGACTION;
                break;
            case "buy":
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                machineState = MachineState.CHOOSINGTYPE;
                break;
            case "take":
                takeMoney();
                writeActionMessage();
                machineState = MachineState.CHOOSINGACTION;
                break;
            case "fill":
                System.out.println("Write how many ml of water you want to add:");
                machineState = MachineState.FILLING;
                break;

            case "exit":
                exit = true;
                break;
        }
        return exit;
    }
    void buyCoffee(String type) {
        if (type.equals("back")) {
            writeActionMessage();
            machineState = MachineState.CHOOSINGACTION;
            return;
        }
        switch (type) {
            case "1":
                if (!isEnoughResources("espresso")) {
                    System.out.println("Sorry, not enough water!");
                    writeActionMessage();
                    machineState = MachineState.CHOOSINGACTION;
                    break;
                }
                water -= 250;
                beans -= 16;
                cups -= 1;
                money += 4;
                System.out.println("I have enough resources, making you a coffee!");
                writeActionMessage();
                machineState = MachineState.CHOOSINGACTION;
                break;
            case "2":
                if (!isEnoughResources("latte")) {
                    System.out.println("Sorry, not enough water!");
                    writeActionMessage();
                    machineState = MachineState.CHOOSINGACTION;
                    break;
                }
                water -= 350;
                milk -= 75;
                beans -= 20;
                cups -= 1;
                money += 7;
                System.out.println("I have enough resources, making you a coffee!");
                writeActionMessage();
                machineState = MachineState.CHOOSINGACTION;
                break;
            case "3":
                if (!isEnoughResources("cappuccino")) {
                    System.out.println("Sorry, not enough water!");
                    writeActionMessage();
                    machineState = MachineState.CHOOSINGACTION;
                    break;
                }
                water -= 200;
                milk -= 100;
                beans -= 12;
                cups -= 1;
                money += 6;
                System.out.println("I have enough resources, making you a coffee!");
                writeActionMessage();
                machineState = MachineState.CHOOSINGACTION;
                break;
            default:
                System.out.println("Invalid drink");
                writeActionMessage();
                machineState = MachineState.CHOOSINGACTION;
        }

    }

    void writeActionMessage(){
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }

    void takeMoney() {
        System.out.println("I gave you $" + money);
        money -= money;

    }

    void printCoffeeState() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(beans + " g of coffee beans");
        System.out.println(cups + " disposable cups");
        System.out.println("$" + money + " of money");
    }

    boolean isEnoughResources(String drink) {
        if (drink.equals("espresso")) {
            if (water - 250 < 0 || beans - 16 < 0 || cups - 1 < 0) {
                return false;
            }
        }
        if (drink.equals("latte")) {
            if (water - 350 < 0 || milk - 75 < 0 || beans - 20 < 0 || cups - 1 < 0) {
                return false;
            }
        }
        if (drink.equals("cappuccino")) {
            if (water - 200 < 0 || milk - 100 < 0 || beans - 12 < 0 || cups - 1 < 0) {
                return false;
            }
        }
        return true;
    }
}

