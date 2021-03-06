package Controller;

import View.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainController extends Application {



    private MainView mainView;
    private String character;
    private static EnemyController enemyController = new EnemyController();

    private static PlayerController playerController = new PlayerController(1);

    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Start Method for showing the View
     * @param primaryStage Stage for showing the View (similar to JFrame in Java.swing)
     * @throws Exception shows error when something is wrong inside the View
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        mainView = new MainView();
        mainView.start(primaryStage);
    }
    
    /**
     * Fight Method
     */
    public static void fight() {
        if (playerController.getStatsInt()[0] > 0 && enemyController.enemyStatsInt()[0] > 0) {
            if (playerController.getStatsInt()[3] >= enemyController.enemyStatsInt()[3]) {
                if ((int) (Math.random() * 6) + playerController.getStatsInt()[5] >= 5) {
                    enemyController.setEnemyHealth(enemyController.enemyStatsInt()[0] + enemyController.enemyStatsInt()[1] - playerController.getStatsInt()[2] * 5);
                    System.out.println("Enemy health: " + enemyController.enemyStatsInt()[0]);
                    if (enemyController.enemyStatsInt()[0] > 0) {
                        MainView.setHealth((double) (((playerController.getStatsInt()[0] + playerController.getStatsInt()[1] - enemyController.enemyStatsInt()[2]) / playerController.getStatsInt()[0]) / 100));
                        playerController.setCharacterHealth(playerController.getStatsInt()[0] + playerController.getStatsInt()[1] - enemyController.enemyStatsInt()[2]);
                        System.out.println("Player health: " + playerController.getStatsInt()[0]);
                        if (playerController.getStatsInt()[0] <= 0) {
                            lose();
                        }
                    } else {
                        playerController.increasePlayerStats();
                        enemyController.generateNewEnemy(playerController.getCurrentLevel());
                        System.out.println("next enemy created");
                    }
                } else {
                    enemyController.setEnemyHealth(enemyController.enemyStatsInt()[0] + enemyController.enemyStatsInt()[1] - playerController.getStatsInt()[2]);
                    System.out.println(enemyController.enemyStatsInt()[0]);
                    if (enemyController.enemyStatsInt()[0] > 0) {
                        MainView.setHealth((double) (((playerController.getStatsInt()[0] + playerController.getStatsInt()[1] - enemyController.enemyStatsInt()[2]) / playerController.getStatsInt()[1]) / 100));
                        playerController.setCharacterHealth(playerController.getStatsInt()[0] + playerController.getStatsInt()[1] - enemyController.enemyStatsInt()[2]);
                        System.out.println("Player health: " + playerController.getStatsInt()[0]);
                        if (playerController.getStatsInt()[0] <= 0) {
                            lose();
                        }
                    } else {
                        playerController.increasePlayerStats();
                        enemyController.generateNewEnemy(playerController.getCurrentLevel());
                        System.out.println("next enemy created");
                    }
                }
            } else {
                MainView.setHealth((double) (((playerController.getStatsInt()[0] + playerController.getStatsInt()[1] - enemyController.enemyStatsInt()[2]) / playerController.getStatsInt()[0]) / 100));
                playerController.setCharacterHealth(playerController.getStatsInt()[0] + playerController.getStatsInt()[1] - enemyController.enemyStatsInt()[2]);
                System.out.println("Player health: " + playerController.getStatsInt()[0]);
                if (playerController.getStatsInt()[0] > 0) {
                    if ((Math.random() * 6) + playerController.getStatsInt()[5] >= 5) {
                        enemyController.setEnemyHealth(enemyController.enemyStatsInt()[0] + enemyController.enemyStatsInt()[1] - playerController.getStatsInt()[2] * 5);
                        System.out.println("Enemy health: " + enemyController.enemyStatsInt()[0]);
                        if (enemyController.enemyStatsInt()[0] <= 0) {
                            playerController.increasePlayerStats();
                            enemyController.generateNewEnemy(playerController.getCurrentLevel());
                            System.out.println("next enemy created");
                        }
                    } else {
                        enemyController.setEnemyHealth(enemyController.enemyStatsInt()[0] + enemyController.enemyStatsInt()[1] - playerController.getStatsInt()[2]);
                        System.out.println("Enemy health: " + enemyController.enemyStatsInt()[0]);
                        if (enemyController.enemyStatsInt()[0] <= 0) {
                            playerController.increasePlayerStats();
                            enemyController.generateNewEnemy(playerController.getCurrentLevel());
                            System.out.println("next enemy created");
                        }
                    }
                } else {
                    lose();
                }
            }
        }
    }
    
    /**
     * Method that runs after the flee button is pressed
     */
    public static void flee() {
        if (playerController.getStatsInt()[0] > 0 && enemyController.enemyStatsInt()[0] > 0) {
            int fleeNumber = (int) (Math.random() * 6) + playerController.getStatsInt()[5];
            if (fleeNumber >= 5) {
                if(playerController.getStatsInt()[0]<= playerController.getStatsInt()[6]) {
                    playerController.healThroughFlee();
                }
                enemyController.generateNewEnemy(playerController.getCurrentLevel());
                System.out.println(playerController.getStatsInt()[0]);
            } else {
                playerController.setCharacterHealth(playerController.getStatsInt()[0] + playerController.getStatsInt()[1] - enemyController.enemyStatsInt()[2]);
                System.out.println(playerController.getStatsInt()[0]);
            }
        }
    }
    
    /**
     * Method that runs when the player losses (when playerHealth <= 0)
     */
    public static void lose(){
        MainView.showLoosePane();
    }
    
    
    
    /**
     * Getter and Setter Methods
     */
    public static void setCharacter(String character) {
        if (character.equals("orc")){
            playerController.createPlayerMeMeBIIIGBOI();
        }
        if (character.equals("mage")){
            playerController.createPlayerMage();
        }
        if (character.equals("assassin")){
            playerController.createPlayerAssasin();
        }
        if (character.equals("knight")){
            playerController.createPlayerKnight();
        }
        enemyController.generateNewEnemy(playerController.getCurrentLevel());
        System.out.println("First Enemy created");
    }
    
    public static String getCharacter(){
        return playerController.getCharacter();
    }

}

