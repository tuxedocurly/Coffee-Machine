# Coffee-Machine

A simple implementation of a Coffee Machine control mechanism, written in Java. The ultimate purpose of this project is to demonstrate the control flow that could be taken with a "real" Coffee Machine, and to provide an example project that could be expanded on by using actual hardware and control libraries.

# How to use
Primarily, you should expect to consume the CoffeeMachine class (which depends on all included files except for Main.java). This is where the machine control logic is housed. After instantiating an instance of the CoffeeMachine class, repeatedly passing input to the class via the handleInput(String string) method will allow you to successfully execute the machine control process.

# Example

Note: See Main.Java for the full example.
```
// create an instance of CoffeeMachine and specify the initial amount of each resource
CoffeeMachine coffeeMachine = new CoffeeMachine(400, 540, 120, 9, 550);

Scanner scanner = new Scanner(System.in)

    while (scanner.hasNext()) {
    // call the handleInput method on an input String. In this case, I'm using an instance of Scanner.
        coffeeMachine.handleInput(scanner.next());
    }

```
