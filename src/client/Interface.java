package client;

import client.game.Game;
import client.handler.RequestHandler;
import client.handler.Sender;

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
        
    }

    public static int auth() {
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
                return 1;
            } else if (input.equals("2")) {
                return 2;
            } else if (input.equals("3")) {
                message("Bye!");
                System.exit(0);
            } else {
                System.out.println("Wrong select");
            }
        }
    }

    public static void register() {
        clearScreen();
        System.out.println(
                "\n  _____            _     _            \n |  __ \\          (_)   | |           \n | |__) |___  __ _ _ ___| |_ ___ _ __ \n |  _  // _ \\/ _` | / __| __/ _ \\ '__|\n | | \\ \\  __/ (_| | \\__ \\ ||  __/ |   \n |_|  \\_\\___|\\__, |_|___/\\__\\___|_|   \n              __/ |                   \n             |___/                    ");
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

        Sender.send(App.writer, RequestHandler.auth("register", username, password));
    }

    public static void message(String msg) {
        System.out.print("|==");
        for (int i = 0; i < msg.length(); i++) {
            System.out.print("=");
        }
        System.out.println("==|");
        System.out.println("|  " + msg + "  |");
        System.out.print("|==");
        for (int i = 0; i < msg.length(); i++) {
            System.out.print("=");
        }
        System.out.println("==|");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
        }

    }

    public static void login() {
        clearScreen();
        System.out.println(
                "\n  _                 _       \n | |               (_)      \n | |     ___   __ _ _ _ __  \n | |    / _ \\ / _` | | '_ \\ \n | |___| (_) | (_| | | | | |\n |______\\___/ \\__, |_|_| |_|\n               __/ |        \n              |___/         ");
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

        Sender.send(App.writer, RequestHandler.auth("login", username, password));
    }

    public static void home() {
        clearScreen();
        System.out.printf(
                "\n   _ _ _______ _            ___   ___     _____                     _ _ \n  ( | )__   __| |          |__ \\ / _ \\   / ____|                   ( | )\n   V V   | |  | |__   ___     ) | | | | | |  __  __ _ _ __ ___   ___V V \n         | |  | '_ \\ / _ \\   / /| | | | | | |_ |/ _` | '_ ` _ \\ / _ \\   \n         | |  | | | |  __/  / /_| |_| | | |__| | (_| | | | | | |  __/   \n         |_|  |_| |_|\\___| |____|\\___/   \\_____|\\__,_|_| |_| |_|\\___|   \n                                                                        \n                                                                        \n\n");

        System.out.printf("\t%s\n", "[1]> Play With Computer");
        System.out.printf("\t%s\n", "[2]> Join Game Randomly");
        System.out.printf("\t%s\n", "[3]> Exit");

        String input;
        while (true) {
            System.out.printf("> Select Game Mood:");
            input = App.input.nextLine();
            if (input.equals("1")) {
                Sender.send(App.writer, RequestHandler.playWithComputer());
                return;
                // return Integer.parseInt(input);
            } else if (input.equals("2")) {
                Sender.send(App.writer, RequestHandler.joinRandomGame());
                return;
                // return Integer.parseInt(input);
            } else if (input.equals("3")) {
                message("Bye!");
                System.exit(0);
            } else {
                System.out.printf("\n%s\n", "Wrong select");
            }
        }
    }

    public static void wait_player() {
        clearScreen();
        System.out.println("waiting player to join");
    }

    public static void game(Game game) {
        clearScreen();
        System.out.printf(
                "\n   _ _ _______ _            ___   ___     _____                     _ _ \n  ( | )__   __| |          |__ \\ / _ \\   / ____|                   ( | )\n   V V   | |  | |__   ___     ) | | | | | |  __  __ _ _ __ ___   ___V V \n         | |  | '_ \\ / _ \\   / /| | | | | | |_ |/ _` | '_ ` _ \\ / _ \\   \n         | |  | | | |  __/  / /_| |_| | | |__| | (_| | | | | | |  __/   \n         |_|  |_| |_|\\___| |____|\\___/   \\_____|\\__,_|_| |_| |_|\\___|   \n                                                                        \n                                                                        \n\n");

        loadBar(game.get_pointer());

        String input;
        if(App.game.getTurn() == 1){
            while (true) {
                System.out.println("1 or 2 Steps? :");
                input = App.input.nextLine();
                if (input.equals("1") || input.equals("2")) {
                    Sender.send(App.writer, RequestHandler.move(Integer.parseInt(input)));
                    break;
                } else {
                    System.out.println("Wrong select");
                }
            }
        }else{
            System.out.printf("It is %s's turn\n",App.game.getOpponent().getUsername());
        }
    }

    public static void loadBar(int len) {
        System.out.println();
        for (int i = 0; i < ((len * 3) + 4); i++) {
            System.out.print("-");
        }
        System.out.println();

        System.out.print("| ");

        for (int i = 1; i <= len; i++) {
            if (i < 10) {
                System.out.print(" ");
            }
            System.out.print(i);
            System.out.print(" ");
        }

        System.out.println(" |");

        for (int i = 0; i < ((len * 3) + 4); i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void winning() {
        clearScreen();
        System.out.println("_ ___     __          __          __         _ _ _ ");
        System.out.println("  ( | ) \\   / /          \\ \\        / /        | ( | )");
        System.out.println("   V V \\ \\_/ /__  _   _   \\ \\  /\\  / /__  _ __ | |V V ");
        System.out.println("        \\   / _ \\| | | |   \\ \\/  \\/ / _ \\| '_ \\| | ");
        System.out.println("         | | (_) | |_| |    \\  /\\  / (_) | | | |_|");
        System.out.println("         |_|\\___/ \\__,_|     \\/  \\/ \\___/|_| |_(_) ");

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
        }


    }

    public static void losing() {
        clearScreen();
        System.out.println(" _ _  _____                         ____                 _ _ _");
        System.out.println("  ( | )/ ____|                       / __ \\               | ( | )");
        System.out.println("   V V| |  __  __ _ _ __ ___   ___  | |  | |_   _____ _ __| |V V ");
        System.out.println("      | | |_ |/ _` | '_ ` _ \\ / _ \\ | |  | \\ \\ / / _ \\ '__| |    ");
        System.out.println("      | |__| | (_| | | | | | |  __/ | |__| |\\ V /  __/ |  |_|    ");
        System.out.println("       \\_____|\\__,_|_| |_| |_|\\___|  \\____/  \\_/ \\___|_|  (_) ");

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
        }

    }

    public static void Score_Table() {
        clearScreen();
        System.out.println("   _ _  _____                      _______    _     _     _ _ ");
        System.out.println("  ( | )/ ____|                    |__   __|  | |   | |   ( | )");
        System.out.println("   V V| (___   ___ ___  _ __ ___     | | __ _| |__ | | ___V V ");
        System.out.println("       \\___ \\ / __/ _ \\| '__/ _ \\    | |/ _` | '_ \\| |/ _ \\   ");
        System.out.println("       ____) | (_| (_) | | |  __/    | | (_| | |_) | |  __/   ");
        System.out.println("      |_____/ \\___\\___/|_|  \\___|    |_|\\__,_|_.__/|_|\\___|   ");
    }
}
