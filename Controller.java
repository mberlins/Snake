import javax.swing.*;
import java.util.ArrayList;

/**
 * Klasa Controller przekazuje informacje między widokiem i modelem oraz zarządza nimi
 */
class Controller
{
    /** 
     * Obiekt klasy Map 
     */
    private Map map;

    /** 
     * Obiekt klasy Snake
     */
    private Snake snake;

    /**
     * Obiekt klasy Ending
     */
    private Ending ending;

    /**
     * Obiekt klasy Results
     */
    private Results results;
    /** 
     * Obiekt klasy Food 
     */
    private Food food;

    /** 
     * Zmienna logiczna wskazująca czy gra aktywna 
     */
    static boolean GAME_ACTIVE = true;

    /** 
     * Licznik programu 
     */
    static Timer counter;

    /** 
     * poziom trudności gry
     */
    private int diffulty;

    /**
     * Konstruktor tworzący obiekt klasy Window, inicjalizujący pola snake, food i map oraz 
     * @param diff przekazuje żądany poziom trudności
     */
    public Controller(int diff)
    {
        Window window = new Window(this);
        this.snake = new Snake(this);
        this.food = new Food (this);
        this.map = window.getMap();
        //int diff = window.getDiff_lvl();
        initGame(diff);
    }

    /**
     * @return czy gra aktywna czy nie
     */
    public boolean isGameActive() { return GAME_ACTIVE; }

    /**
     * @param status ustawia czy gra aktywna czy nie
     */
    public void setGameActive(boolean status) { Controller.GAME_ACTIVE = status; }

    /**
     * @return czy wąż żywy
     */
    public boolean checkIsAlive() { return snake.isAlive(); }

    /**
     * @param tmp ustawia czy wąż żywy
     */
    public void setIsAlive(boolean tmp) {  snake.setAlive(tmp); }

    /**
     * @return czy wąż idzie do góry
     */
    public boolean checkIsUp() { return snake.isUp(); }

    /**
     * @return czy wąż idzie w dół
     */
    public boolean checkIsDown() { return snake.isDown(); }

    /**
     * @return czy wąż idzie w prawo 
     */
    public boolean checkIsRight() { return snake.isRight(); }

    /**
     * @return czy wąż idzie w lewo
     */
    public boolean checkIsLeft() { return snake.isLeft(); }

    /**
     * @return wynik
     */
    public int readScore() { return food.getScore(); }

    /**
     * @return zwiększony o 1 wynik
     */
    public  int incrementSize() { return snake.incrementSize();}

    /**
     * @return współrzędną x pożywienia 
     */
    public int readFoodX() { return food.getFoodX(); }

    /**
     * @return współrzędną y pożywienia 
     */
    public int readFoodY() { return food.getFoodY(); }

    /**
     * @return współrzędne x modułów ciała węża
     */
    public ArrayList<Integer> getSnakeX()
    {
        return snake.getSnakeX();
    }

    /**
     * @return  współrzędne y modułów ciała węża 
     */
    public ArrayList<Integer> getSnakeY()
    {
        return snake.getSnakeY();
    }

    /**
     * @return wartość licznika zdobytego pożywienia
     */
    public int getSuperFoodCounter()
    {
        return food.getSuperFoodCounter();
    }

    /**
     * @return poziom trudności
     */
    public int getDiffulty()
    {
        return diffulty;
    }

    /**
     * @return wielkość jednostki pola gry
     */
    public int readPIXEL_SIZE() { return map.getPIXEL_SIZE(); }

    /**
     * @return szerokość mapy
     */
    public int readWIDTH() { return map.getWIDTH(); }

    /**
     * @return wysokość mapy
     */
    public int readHEIGHT() { return map.getHEIGHT(); }

    /**
     * Uruchamia licznik programu
     */
    public void startCounter() { counter.start(); }

    /**
     * Zatrzymuje licznik programu
     */
    public void stopCounter() { counter.stop(); }

    /**
     * Resetuje wynik
     */
    public void resetScore() { food.setScore(0); }

    /**
     * Prosi obiekt Window o zmianę wyświetlanego wyniku
     */
    public void setVisibleValue() { Window.setScoreValue(food.getScore()); }

    /**
     * Uruchamia grę z domyślnym poziomem trudności, prosi obiekt Snake o stworzenie początkowego węża oraz
     * zlokalizowanie pożywienia tworzy obiekt klasy Timer przypisany do obiektu klasy Map
     * @param diff przekazuje poziom trudności
     */
    public void initGame(int diff)
    {
        snake.createSnake();
        food.dropFood();
        food.setScore(0);
        snake.setAlive(true);
        counter = new Timer(diff, map);
    }

    /**
     * Zmienia domyślny poziom trudności poprzez stworzenie nowego licznika, któremu przekazuje argument,
     * a także modyfikuje pole difficulty
     * @param diff przekazuje poziom trudności
     */
    public void changeDifficulty(int diff)
    {
        if (diff<100)
            this.diffulty = 3;
        else if (diff >=100 && diff < 200)
            this.diffulty = 2;
        else if (diff >=200 )
            this.diffulty = 1;

        counter = new Timer(diff, map);
    }

    /**
     * Pokazuje okienko ending
     */
    public void showEnding()
    {
        ending = new Ending(this);
    }

    /**
     * Pokazuje okienko results
     */
    public void showResults()
    {
        results = new Results(this);
    }

    /**
     * Prosi obiekt snake o zmianę kierunku ruchu w logice na lewo
     */
    public void goLeft()
    {
        snake.setLeft(true);
        snake.setUp(false);
        snake.setDown(false);
    }

    /**
     * Prosi obiekt snake o zmianę kierunku ruchu w logice na prawo
     */
    public void goRight()
    {
        snake.setRight(true);
        snake.setUp(false);
        snake.setDown(false);
    }

    /**
     * Prosi obiekt snake o zmianę kierunku ruchu w logice na w górę
     */
    public void goUp()
    {
        snake.setUp(true);
        snake.setLeft(false);
        snake.setRight(false);
    }

    /**
     * Prosi obiekt snake o zmianę kierunku ruchu w logice na w dół
     */
    public void goDown()
    {
        snake.setDown(true);
        snake.setLeft(false);
        snake.setRight(false);
    }


    /**
     * Jeśli gra jest aktywna funkcja prosi obiekt klasy Snake o sprawdzenie czy wąż nie dokonał kolizji  oraz o
     * przesunięcie węża. Prosi obiekt klasy Map o wygenerowanie "nowej klatki".
     * Jeśli gra nieaktywna zatrzymuje licznik programu oraz prosi obiket snake o przywrócenie węża do domyślnych wartości,
     * pokazuje przyciski Difficulty oraz Start
     */
    public void gameManager()
    {
        if (GAME_ACTIVE)
        {
            food.checkFood();
            snake.checkCollision();
            snake.moveSnake();
            map.repaint();
        }
        else
        {
            counter.stop();
            snake.killSnake();
            snake.setSize(3);
            snake.createSnake();

            GAME_ACTIVE = true;
            snake.setUp(true);
            snake.setDown(false);
            snake.setLeft(false);
            snake.setRight(false);

            Window.showDifficulty(true);
            Window.showStart(true);
            map.repaint();
        }
    }

    /**
     * Funkcja main tworzy obiekt typu kontroler
     * @param args
     */
    public static void main(String[] args)
    {
        new Controller(140);
    }



}