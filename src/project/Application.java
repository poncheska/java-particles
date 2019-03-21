package project;

import project.Menu;

public class Application {
    public static void main(String[] args){
        Menu menu = new Menu();
        new Thread(menu).start();

    }
}
