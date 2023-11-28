package machine;

import java.util.Scanner;

public class CoffeeMachine {

    // class variables that define the initial state of the coffee machine
    int waterStock;
    int milkStock;
    int coffeeStock;
    int cupStock;
    int moneyInMachine;


    // class constructor
    public CoffeeMachine(int waterStock, int milkStock, int coffeeStock, int cupStock,
                         int moneyInMachine) {
        this.waterStock = waterStock;
        this.milkStock = milkStock;
        this.coffeeStock = coffeeStock;
        this.cupStock = cupStock;
        this.moneyInMachine = moneyInMachine;

    }


    /* Calculates the maximum cups of coffee the machine can produce.*/
    private static void makeCoffee(CoffeeMachine coffeeMachine, String coffeeType) {

        // check if the type of coffee can be made. If it can, make the coffee
        if (coffeeMachine.cupStock > 0) {
            switch (coffeeType) {
                case "1":
                    if (coffeeMachine.waterStock >= 250 && coffeeMachine.coffeeStock >= 16) {
                        // make coffee
                        coffeeMachine.cupStock--;
                        coffeeMachine.waterStock -= 250;
                        coffeeMachine.coffeeStock -= 16;
                        coffeeMachine.moneyInMachine += 4;
                    }
                    break;
                case "2":
                    if (coffeeMachine.waterStock >= 350 && coffeeMachine.milkStock >= 75 && coffeeMachine.coffeeStock >= 20) {
                        // make coffee
                        coffeeMachine.cupStock--;
                        coffeeMachine.waterStock -= 350;
                        coffeeMachine.milkStock -= 75;
                        coffeeMachine.coffeeStock -= 20;
                        coffeeMachine.moneyInMachine += 7;
                    }
                    break;
                case "3":
                    if (coffeeMachine.waterStock >= 200 && coffeeMachine.milkStock >= 100 && coffeeMachine.coffeeStock >= 12) {
                        // make coffee
                        coffeeMachine.cupStock--;
                        coffeeMachine.waterStock -= 200;
                        coffeeMachine.milkStock -= 100;
                        coffeeMachine.coffeeStock -= 12;
                        coffeeMachine.moneyInMachine += 6;
                    }
                    break;
                default:
                    System.out.println("Invalid selection");
                    break;
            }
        }
    }


    static void printMachineState(CoffeeMachine coffeeMachine) {
        System.out.println("The coffee machine has:\n" +
                            coffeeMachine.waterStock + " ml of water\n" +
                            coffeeMachine.milkStock + " ml of milk\n" +
                            coffeeMachine.coffeeStock + " g of coffee beans\n" +
                            coffeeMachine.cupStock + " disposable cups\n" +
                            "$" + coffeeMachine.moneyInMachine + " of money");
    }

    static void buy(CoffeeMachine coffeeMachine) {
        System.out.println("What do you want to buy?");
        Scanner in = new Scanner(System.in);
        String coffeeType = in.next();

        // this can be saved as a boolean if it's possible/not possible to make coffee
        // for future extensibility
        makeCoffee(coffeeMachine, coffeeType);
    }

    static void fill(CoffeeMachine coffeeMachine) {
        Scanner in = new Scanner(System.in);
        System.out.println("Write how many ml of water you want to add:");
        coffeeMachine.waterStock += in.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        coffeeMachine.milkStock += in.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        coffeeMachine.coffeeStock += in.nextInt();
        System.out.println("Write how many disposable cups you want to add:");
        coffeeMachine.cupStock += in.nextInt();
    }

    static void take(CoffeeMachine coffeeMachine) {
        System.out.println("I gave you $" + coffeeMachine.moneyInMachine);
        coffeeMachine.moneyInMachine = 0;
    }

}
