package client;

import client.game.Game;
import client.game.PlayWithComputer;

public class Interface {

    public static void welcome() {
        clearScreen();
        System.out.println("");
        System.out.printf(
                "\n          __          __  _                            _______    \n          \\ \\        / / | |                          |__   __|   \n           \\ \\  /\\  / /__| | ___ ___  _ __ ___   ___     | | ___  \n            \\ \\/  \\/ / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\    | |/ _ \\ \n             \\  /\\  /  __/ | (_| (_) | | | | | |  __/    | | (_) |\n              \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|    |_|\\___/ \n                                                                \n                                                                  ");
        System.out.printf(
                "\n   _ _ _______ _            ___   ___     _____                     _ _ \n  ( | )__   __| |          |__ \\ / _ \\   / ____|                   ( | )\n   V V   | |  | |__   ___     ) | | | | | |  __  __ _ _ __ ___   ___V V \n         | |  | '_ \\ / _ \\   / /| | | | | | |_ |/ _` | '_ ` _ \\ / _ \\   \n         | |  | | | |  __/  / /_| |_| | | |__| | (_| | | | | | |  __/   \n         |_|  |_| |_|\\___| |____|\\___/   \\_____|\\__,_|_| |_| |_|\\___|   \n                                                                        \n                                                                        ");
        System.out.println();
        System.out.println();
        System.out.println("To Start Press Anything:");
        App.input.nextLine();
        Interface.auth();

    }

    public static void auth() {
        clearScreen();
        System.out.printf(
                "\n   _ _ _______ _            ___   ___     _____                     _ _ \n  ( | )__   __| |          |__ \\ / _ \\   / ____|                   ( | )\n   V V   | |  | |__   ___     ) | | | | | |  __  __ _ _ __ ___   ___V V \n         | |  | '_ \\ / _ \\   / /| | | | | | |_ |/ _` | '_ ` _ \\ / _ \\   \n         | |  | | | |  __/  / /_| |_| | | |__| | (_| | | | | | |  __/   \n         |_|  |_| |_|\\___| |____|\\___/   \\_____|\\__,_|_| |_| |_|\\___|   \n                                                                        \n                                                                        \n\n");
        System.out.println("[1]> Register");
        System.out.println("[2]> Login");
        String input;
        while (true) {
            System.out.println("> Enter Option Number:");
            input = App.input.nextLine();
            if (input.equals("1")) {
                register();
                break;
            } else if (input.equals("2")) {
                login();
                break;
            } else {
                System.out.println("Wrong select");
            }
        }
    }

    public static void register(){
        clearScreen();
        System.out.println("\n  _____            _     _            \n |  __ \\          (_)   | |           \n | |__) |___  __ _ _ ___| |_ ___ _ __ \n |  _  // _ \\/ _` | / __| __/ _ \\ '__|\n | | \\ \\  __/ (_| | \\__ \\ ||  __/ |   \n |_|  \\_\\___|\\__, |_|___/\\__\\___|_|   \n              __/ |                   \n             |___/                    ");
        System.out.println();
        System.out.println();
        System.out.println();
        String username;
        String password;
        
        
        // TODO: name validation
        while (true) {
            System.out.printf("> Enter Your username:");
            username = App.input.nextLine();
            break;
        }
        
        // TODO: password validation
        while (true) {
            System.out.printf("> Enter Your password:");
            password = App.input.nextLine();
            break;
        }


        System.out.println("|============================================|");
        System.out.println("|           Registered Successfully          |");
        System.out.println("|============================================|");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}

    }
    
    
    public static void login(){
        clearScreen();
        System.out.println("\n  _                 _       \n | |               (_)      \n | |     ___   __ _ _ _ __  \n | |    / _ \\ / _` | | '_ \\ \n | |___| (_) | (_| | | | | |\n |______\\___/ \\__, |_|_| |_|\n               __/ |        \n              |___/         ");
        System.out.println();
        System.out.println();
        System.out.println();
        String username;
        String password;
        
        while (true) {
            System.out.printf("> Enter Your username:");
            username = App.input.nextLine();
            break;
        }
        
        while (true) {
            System.out.printf("> Enter Your password:");
            password = App.input.nextLine();
            break;
        }


        System.out.println("|============================================|");
        System.out.println("|           Loggedin Successfully            |");
        System.out.println("|============================================|");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}

    }

    public static void home(){
        clearScreen();
        System.out.printf("\n   _ _ _______ _            ___   ___     _____                     _ _ \n  ( | )__   __| |          |__ \\ / _ \\   / ____|                   ( | )\n   V V   | |  | |__   ___     ) | | | | | |  __  __ _ _ __ ___   ___V V \n         | |  | '_ \\ / _ \\   / /| | | | | | |_ |/ _` | '_ ` _ \\ / _ \\   \n         | |  | | | |  __/  / /_| |_| | | |__| | (_| | | | | | |  __/   \n         |_|  |_| |_|\\___| |____|\\___/   \\_____|\\__,_|_| |_| |_|\\___|   \n                                                                        \n                                                                        \n\n");

        System.out.printf("\t%s\n","[1]> Play With Computer");
        System.out.printf("\t%s\n","[2]> Join Game Randomly");
        System.out.printf("\t%s\n","[3]> Play With Friend");
        String input;
        while (true) {
            System.out.printf("> Select Game Mood:");
            input = App.input.nextLine();
            if(input.equals("1")){
                break;
            }else if(input.equals("2")){
                break;
            }else if(input.equals("3")){
                break;
            }else{
                System.out.printf("\n%s\n","Wrong select");
            }
        }
    }

    public void playWithAI(){

    }


    public static void game(Game game){
        System.out.printf(
                "\n   _ _ _______ _            ___   ___     _____                     _ _ \n  ( | )__   __| |          |__ \\ / _ \\   / ____|                   ( | )\n   V V   | |  | |__   ___     ) | | | | | |  __  __ _ _ __ ___   ___V V \n         | |  | '_ \\ / _ \\   / /| | | | | | |_ |/ _` | '_ ` _ \\ / _ \\   \n         | |  | | | |  __/  / /_| |_| | | |__| | (_| | | | | | |  __/   \n         |_|  |_| |_|\\___| |____|\\___/   \\_____|\\__,_|_| |_| |_|\\___|   \n                                                                        \n                                                                        \n\n");


        if(game.getTurn() == 1){
            String input;
            while (true) {
                System.out.println("1 or 2 Steps? :");
                input = App.input.nextLine();
                if (input.equals("1") || input.equals("2")) {
                    game.move(Integer.parseInt(input));
                    break;
                } else {
                    System.out.println("Wrong select");
                }
            }
            System.out.printf("%s have played %s",game.getPlayerTurn().getUsername(),input);
            game.nextTurn();
        }else{
            System.out.printf("%s has played %s",game.getPlayerTurn().getUsername(),game.get_last_player_steps());
            game.nextTurn();
        }
        loadBar(game.get_pointer());

    }

    public static void loadBar(int len){
        System.out.println();
        for (int i = 0; i < ((len*3)+4); i++) {
            System.out.print("-");
        }
        System.out.println();

        System.out.print("| ");

        for (int i = 1; i <= len; i++) {
            if(i < 10){
                System.out.print(" ");
            }
            System.out.print(i);
            System.out.print(" ");
        }


        System.out.println(" |");
        
        for (int i = 0; i < ((len*3)+4); i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
