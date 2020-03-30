import java.util.ArrayList;

/**
 * Klasa Snake przechowuje dane o wężu
 */
class Snake
{
    /**
     * Obiekt klasy Controller
     */
    private Controller controller;
    /**
     * Informuje czy wąż żywy
     */
    private boolean alive = false;
    /**
     * Zmienna logiczna informuje czy wąż idzie w górę
     */
    private boolean up = true;
    /**
     * Zmienna logiczna informuje czy wąż idzie w dół
     */
    private boolean down = false;
    /**
     * Zmienna logiczna informuje czy wąż idzie w lewo
     */
    private boolean left = false;
    /**
     * Zmienna logiczna informuje czy wąż idzie w prawo
     */
    private boolean right = false;
    /**
     * Obecny rozmiar węża
     */
    private static int size = 3;
    /**
     * Lista współrzędnych x modułów ciała węża
     */
    private ArrayList<Integer> snakeX;
    /**
     * Lista współrzędnych y modułów ciała węża
     */
    private ArrayList<Integer> snakeY;

    /**
     * Konstruktor obiektu klasy Snake przyjmujący w parametrze obiekt typu controller, inicjalizujący pola controller, snakeX oraz snakeY
     * @param controller
     */
    public Snake(Controller controller)
    {
        this.controller = controller;
        this.snakeX = new ArrayList<>();
        this.snakeY = new ArrayList<>();
    }
    /**
     * @return wartość pola alive
     */
    public boolean isAlive() {
        return alive;
    }
    /**
     * Ustawia pole alive na wartość przyjętego argumentu
     * @param alive
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    /**
     * @return wartość logiczną pola up
     */
    public boolean isUp()
    {
        return up;
    }
    /**
     * @param up nadaje wartość logiczną polu up
     */
    public void setUp(boolean up)
    {
        this.up = up;
    }
    /**
     * @return wartość logiczną pola down
     */
    public boolean isDown()
    {
        return down;
    }
    /**
     * @param down nadaje wartość logiczną polu down
     */
    public void setDown(boolean down)
    {
        this.down = down;
    }
    /**
     * @return wartość logiczną pola left
     */
    public boolean isLeft()
    {
        return left;
    }
    /**
     * @param left nadaje wartość logiczną polu left
     */
    public void setLeft(boolean left)
    {
        this.left = left;
    }
    /**
     * @return wartość logiczną pola right
     */
    public boolean isRight()
    {
        return right;
    }
    /**
     * @param right nadaje wartość logiczną polu right
     */
    public void setRight(boolean right)
    {
        this.right = right;
    }
    /**
     * @param size ustawia wartość pola size
     */
    public static void setSize(int size) { Snake.size = size; }
    /**
     * @return wartość pola size
     */
    public static int getSize() { return size; }

    /**
     * @return zwiększony o 1 rozmiar węża
     */
    public int incrementSize(){ return  ++size;}

    /**
     * @return listę współrzędnych x modułów ciała węża
     */
    public ArrayList<Integer> getSnakeX() { return snakeX; }

    /**
     * @return listę współrzędnych y modułów ciała węża
     */
    public ArrayList<Integer> getSnakeY() { return snakeY; }

    /**
     * Usuwa aktualną pozycję węża
     */
    public void killSnake()
    {
        snakeX.clear();
        snakeY.clear();
    }

    /**
     * Ustawia węża na pozycji początkowej
     */
    public void createSnake()
    {
        for (int i=0; i<size; i++)
        {
            setAlive(true);
            snakeX.add(320);
            snakeY.add(320 + i*controller.readPIXEL_SIZE());
        }
    }

    /**
     * Zmienia zawartość list przechowujących pozycję węża w zależności od wybranego kierunku
     */
    public void moveSnake()
    {
        int tmp;
        for (int i = size-1; i > 0; i--)
        {
            snakeX.set(i, snakeX.get(i-1));
            snakeY.set(i, snakeY.get(i-1));
        }
        if (left)
        {
        tmp = snakeX.get(0);
        snakeX.set(0, tmp-controller.readPIXEL_SIZE());
        }
        else if (right)
        {
            tmp = snakeX.get(0);
            snakeX.set(0, tmp+controller.readPIXEL_SIZE());
        }
        else if (up)
        {
            tmp = snakeY.get(0);
            snakeY.set(0, tmp-controller.readPIXEL_SIZE());
        }
        else if (down)
        {
            tmp = snakeY.get(0);
            snakeY.set(0, tmp+controller.readPIXEL_SIZE());
        }
    }

    /**
     * Sprawdza czy wąż nie zginął
     */
    public void checkCollision()
    {
        for (int i=1; i<size; i++)
        {
            if(snakeX.get(i).equals(snakeX.get(0)) && snakeY.get(i).equals(snakeY.get(0)))
            {
                setAlive(false);
                controller.setGameActive(false);
                break;
            }
        }

        if (snakeX.get(0) > controller.readWIDTH()-2*controller.readPIXEL_SIZE())
        {
            setAlive(false);
            controller.setGameActive(false);
            controller.showEnding();
        }
        else if (snakeX.get(0) < controller.readPIXEL_SIZE())
        {
            setAlive(false);
            controller.setGameActive(false);
            controller.showEnding();
        }
        else if (snakeY.get(0) > controller.readHEIGHT()-2*controller.readPIXEL_SIZE())
        {
            setAlive(false);
            controller.setGameActive(false);
            controller.showEnding();
        }
        else if (snakeY.get(0) < controller.readPIXEL_SIZE())
        {
            setAlive(false);
            controller.setGameActive(false);
            controller.showEnding();
        }
    }
}