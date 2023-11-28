package machine;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        CoffeeMachine coffeeMachine = new CoffeeMachine(400, 540, 120, 9, 550);
        Scanner scanner = new Scanner(System.in);
        String action;

        System.out.println("Write an action (buy, fill, take, remaining, exit):");
        //action = scanner.next().toLowerCase();
        while (scanner.hasNext()) {
            action = scanner.next().toLowerCase();
            if (action.equals("exit")) {
                break;
            }
            //System.out.println("Write an action (buy, fill, take, remaining, exit):"
            switch (action) {
                case "buy":
                    CoffeeMachine.buy(coffeeMachine);
                    break;
                case "fill":
                    CoffeeMachine.fill(coffeeMachine);
                    break;
                case "take":
                    CoffeeMachine.take(coffeeMachine);
                    break;
                case "remaining":
                    CoffeeMachine.printMachineState(coffeeMachine);
                    break;
            }
        }
    }
}
