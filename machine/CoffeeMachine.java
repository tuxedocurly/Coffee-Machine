package machine;

import java.util.Locale;

public class CoffeeMachine {
    private MachineState machineState;

    private int water;
    private int milk;
    private int coffeeBeans;
    private int cups;
    private int money;

    private int invalidSelectionCounter = 0;

    public CoffeeMachine(int water, int milk, int coffeeBeans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.cups = cups;
        this.money = money;
        menu();
    }

    /* Application main menu */
    public void menu() {
        machineState = MachineState.MENU;
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }

    /* Takes a String and passes it to the method associated with the current MachineState to process. Should be called
     * wherever primary user input is being continuously collected. */
    public void handleInput(String userInput) {
        switch (machineState) {
            case MENU:
                takeAction(userInput);
                break;
            case BUYING_COFFEE:
                chooseCoffee(userInput);
                break;
            case FILLING_WATER:
                fillWater(userInput);
                break;
            case FILLING_MILK:
                fillMilk(userInput);
                break;
            case FILLING_BEANS:
                fillBeans(userInput);
                break;
            case FILLING_CUPS:
                fillCups(userInput);
                break;
            default:
                System.out.println("Something went wrong. Cannot determine state. Contact support.");
                break;
        }
    }

    /* Handles input provided by the user modifies machine state as needed to process the next handleInput() action. */
    private void takeAction(String userInput) {
        switch (userInput.toLowerCase(Locale.ROOT).trim()) {
            case "buy":
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino," +
                        " back - to main menu:");
                machineState = MachineState.BUYING_COFFEE;
                break;

            case "fill":
                System.out.println("How many ml of water would you like to add to the machine:");
                machineState = MachineState.FILLING_WATER;
                break;
            case "take":
                System.out.println("Dispensing $" + money + "...");
                dispenseMoney();
                break;
            case "remaining":
                getInventory();
                break;
            case "exit":
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input.");
                menu();
                break;
        }
    }

    /* Calls the makeCoffee(CoffeeTypes coffeeType) method based on a user's chosen coffee selection, or returns the
     * user to the main menu. If 3 invalid selections are made, returns a user to the main menu. */
    private void chooseCoffee(String userInput) {
        switch (userInput) {
            case "1":
                makeCoffee(CoffeeTypes.ESPRESSO);
                break;
            case "2":
                makeCoffee(CoffeeTypes.LATTE);
                break;
            case "3":
                makeCoffee(CoffeeTypes.CAPPUCCINO);
                break;
            case "back":
                menu();
                break;
            default:
                // track invalid selections and return user to main menu after 3 invalid selections
                invalidSelectionCounter++;
                if (invalidSelectionCounter > 2) {
                    System.out.println("Too many invalid selections were made. Returning to main menu.");
                    invalidSelectionCounter = 0;
                    menu();
                } else {
                    System.out.println("Invalid selection. Try again...");
                    takeAction("buy");
                }
                break;
        }
    }

    /* Checks if CoffeeMachine has enough resources to make the coffee type and removes the associated amount of
     * resources from the CoffeeMachine. Otherwise, returns a message to the user about the status of the resources. */
    private void makeCoffee(CoffeeTypes coffeeType) {
        boolean isEnoughWater = water >= coffeeType.getWater();
        boolean isEnoughMilk = milk >= coffeeType.getMilk();
        boolean isEnoughCoffeeBeans = coffeeBeans >= coffeeType.getCoffeeBeans();
        boolean isEnoughCups = cups > 0;

        if (isEnoughWater && isEnoughMilk && isEnoughCoffeeBeans && isEnoughCups) {
            System.out.println("OK. Making your " + coffeeType.name().toLowerCase(Locale.ROOT) +
                    "...");
            // remove required resources from machine based on coffeeTypes passed in
            water -= coffeeType.getWater();
            milk -= coffeeType.getWater();
            coffeeBeans -= coffeeType.getCoffeeBeans();
            cups -= 1;
            menu();
        } else {
            if (!isEnoughWater) {
                System.out.println("Not enough water. Current water amount: " + water + "ml");
            }
            if (!isEnoughMilk) {
                System.out.println("Not enough milk. Current milk amount: " + milk + "ml");
            }
            if (!isEnoughCoffeeBeans) {
                System.out.println("Not enough coffee beans. Current coffee bean amount: " + coffeeBeans + "grams");
            }
            if (!isEnoughCups) {
                System.out.println("Not enough cups. Current number of cups: " + cups);
            }
            menu();
        }
    }

    /* Adds a specified amount of water to the CoffeeMachine. Only accepts positive integers. All negatives are
     * processed with Math.abs(), and an error is thrown if the input is not a valid integer. */
    private void fillWater(String userInput) {
        try {
            int integerInput = Math.abs(Integer.parseInt(userInput));
            water += integerInput;
            System.out.println("How many ml of milk would you like to add to the machine:");
            machineState = MachineState.FILLING_MILK;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a positive whole number.");
        }
    }

    /* Adds a specified amount of milk to the CoffeeMachine. Only accepts positive integers. All negatives are
     * processed with Math.abs(), and an error is thrown if the input is not a valid integer. */
    private void fillMilk(String userInput) {
        try {
            int integerInput = Math.abs(Integer.parseInt(userInput));
            milk += integerInput;
            System.out.println("How many grams of beans would you like to add to the machine:");
            machineState = MachineState.FILLING_BEANS;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a positive whole number.");
        }
    }

    /* Adds a specified amount of coffeeBeans to the CoffeeMachine. Only accepts positive integers. All negatives are
     * processed with Math.abs(), and an error is thrown if the input is not a valid integer. */
    private void fillBeans(String userInput) {
        try {
            int integerInput = Math.abs(Integer.parseInt(userInput));
            coffeeBeans += integerInput;
            System.out.println("How many disposable cups would you like to add to the machine:");
            machineState = MachineState.FILLING_CUPS;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a positive whole number.");
        }
    }

    /* Adds a specified amount of cups to the CoffeeMachine. Only accepts positive integers. All negatives are processed
     * with Math.abs(), and an error is thrown if the input is not a valid integer. */
    private void fillCups(String userInput) {
        try {
            int integerInput = Math.abs(Integer.parseInt(userInput));
            cups += integerInput;
            System.out.println("Machine fill operation complete.");
            // prints inventory and sets machine state
            getInventory();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a positive whole number.");
        }
    }

    /* Dispenses money to the user, and adjust the current amount of money in the CoffeeMachine object. */
    private void dispenseMoney() {
        // logic for dispensing mechanism would go here. In this case, money is set to 0 to simulate withdrawal.
        money = 0;
        menu();
    }

    /* Prints a formatted string of the current coffeeMachine product stock to the console and returns the user to the
     * main menu */
    public void getInventory() {
        System.out.println("The coffee machine has:\n" +
                water + " ml of water\n" +
                milk + " ml of milk\n" +
                coffeeBeans + " grams of coffee beans\n" +
                cups + " disposable cup(s)\n" +
                "$" + money + " of money");
        menu();
    }
}