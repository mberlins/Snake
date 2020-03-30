import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * Klasa Map przechowuje dane o mapie gry
 */
class Map extends JPanel implements ActionListener
{
    /**
     * Obiekt klasy controller
     */
    private Controller controller;

    /**
     * Ikonka głowy węża
     */
    private Image head;

    /**
     * Ikonka modułu ciała węża
     */
    private Image body;

    /**
     * Ikonka ściany pola gry
     */
    private Image wall;

    /**
     *  Ikonka pożywienia
     */
    private Image food;

    /**
     * Ikonka specjalnego pożywienia
     */
    private Image superFood;

    /**
     * Szerokość pola gry
     */
    private final int WIDTH = 640;

    /**
     * Wysokość pola gry
     */
    private final int HEIGHT = 640;

    /**
     * Rozmiar jednostki lokalizacji na polu gry
     */
    private final int PIXEL_SIZE = 40;

    /**
     *  Konstruktor ustawiający kolor pola gry oraz wczytujący grafikę dla wszytkich ikon
     * @param controller inicjalizuje pole kontroler
     */
    public Map(Controller controller)
    {
        this.controller = controller;

        addKeyListener(new Adapter());
        setBackground(Color.white);

        ImageIcon wall = new ImageIcon("src/resources/wall.png");
        this.wall = wall.getImage();

        ImageIcon head = new ImageIcon("src/resources/head.png");
        this.head = head.getImage();

        ImageIcon body = new ImageIcon("src/resources/dot.png");
        this.body = body.getImage();

        ImageIcon food = new ImageIcon("src/resources/apple.png");
        this.food = food.getImage();

        ImageIcon superFood = new ImageIcon("src/resources/bug.png");
        this.superFood = superFood.getImage();

        setFocusable(true);
    }


    /**
     * @return szerokość pola gry
     */
    public int getHEIGHT() { return HEIGHT; }

    /**
     * @return wysokość pola gry
     */
    public int getWIDTH() { return WIDTH; }

    /**
     * @return rozmiar jednostki lokalizacji na polu gry
     */
    public int getPIXEL_SIZE() { return PIXEL_SIZE; }

    /**
     * Wywołuje funkcję gameManager obiektu klasy kontroler
     * @param e obiekt klasy ActionEvent
     */
    public void actionPerformed(ActionEvent e) { controller.gameManager(); }

    /**
     * Klasa wewnętrzna reagująca na sygnał od użytkownika
     */
    private class Adapter extends KeyAdapter
    {
        /**
         * Kliknięcie przycisku wywołuje odpowiednią metodę klasy contoller, która przekazuje żądanie o zmianie kierunku do klasy Snake
         * @param e obiekt klasy KeyEvent
         */
        public void keyPressed(KeyEvent e)
        {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_UP && !controller.checkIsDown())
                controller.goUp();

            if (key == KeyEvent.VK_DOWN && !controller.checkIsUp())
                controller.goDown();

            if (key == KeyEvent.VK_LEFT && !controller.checkIsRight())
                controller.goLeft();

            if (key == KeyEvent.VK_RIGHT && !controller.checkIsLeft())
                controller.goRight();
        }
    }

    /**
     * Rysuje na planszy przeszkody, węża oraz odpowiednie pożywienie w zależności od licznika pożywienia
     * @param g obiekt klasy Graphics
     */
    public void paint(Graphics g)
    {
        super.paint(g);

        for (int i = 0; i<=WIDTH- PIXEL_SIZE; i+= PIXEL_SIZE)
        {
            g.drawImage(wall, i, 0, this);
            g.drawImage(wall, i, HEIGHT- PIXEL_SIZE, this);
        }
        for (int i = PIXEL_SIZE; i<HEIGHT- PIXEL_SIZE; i+= PIXEL_SIZE)
        {
            g.drawImage(wall, 0, i, this);
            g.drawImage(wall, WIDTH- PIXEL_SIZE, i, this);
        }

        if (controller.isGameActive())
        {
            if (controller.getSuperFoodCounter()%5 != 0)
                g.drawImage(food, controller.readFoodX(), controller.readFoodY(), this);
            else
                g.drawImage(superFood, controller.readFoodX(), controller.readFoodY(), this);

            for (int i=0; i<Snake.getSize(); i++)
            {
                if (i == 0)
                    g.drawImage(head, controller.getSnakeX().get(i), controller.getSnakeY().get(i), this);
                else
                    g.drawImage(body, controller.getSnakeX().get(i), controller.getSnakeY().get(i), this);
            }
        }
    }


}