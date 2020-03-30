/**
 * Klasa przechowuje informacje o pożywieniu
 */
class Food
{
    /**
     * Obiekt klasy Controller
     */
    private Controller controller;

    /**
     *  Współrzędna x pożywienia
     */
    private int foodX;

    /**
     *  Współrzędna y pożywienia
     */
    private int foodY;

    /**
     * Licznik zjedzonego pożywienia (zawyża o 1 aby specjalne jedzenie nie było pierwsze)
     */
    private static  int superFoodCounter = 1;

    /**
     * Aktualny wynik gracza
     */
    private static int score = 0;

    /**
     * Konstruktor klasy Food
     * @param controller , inicjalizuje pole controller
     */
    public Food(Controller controller)
    {
        this.controller = controller;
    }

    /**
     * @param score ustawia wartość pola score
     */
    public static void setScore(int score) { Food.score = score; }

    /**
     * @return  wartość pola score
     */
    public static int getScore() { return score; }

    /**
     * @return współrzędną x pożywienia
     */
    public int getFoodX() { return foodX; }

    /**
     * @return współrzędną y pożywienia
     */
    public int getFoodY() { return foodY; }

    /**
     * @return wartość licznika zjedzonego pożywienia
     */
    public int getSuperFoodCounter()
    {
        return superFoodCounter;
    }

    /**
     * Generuje losową pozycję dla nowego pożywienia oraz sprawdza czy ta pozycja jest dozwolona
     */
    public void dropFood()
    {
        while (true)
        {
            int x = (int) (Math.random()* (controller.readWIDTH() / controller.readPIXEL_SIZE() - 2) + 1);
            int y = (int) (Math.random()* (controller.readHEIGHT() / controller.readPIXEL_SIZE() - 2) + 1);
            foodX = x * controller.readPIXEL_SIZE();
            foodY = y * controller.readPIXEL_SIZE();

            if (!controller.getSnakeX().contains(foodX) && !controller.getSnakeY().contains(foodY))
                break;
        }
    }

    /**
     * Sprawdza czy głowa węża nie natrafiła na pozycję pożywienia. W takim prypadku zwiększa rozmiar o 1, zwiększa odpowiednio
     * licznik punktów w zależności od poziomu trudności oraz rodzaju jedzenia oraz wywołuje funkcję tworzącą nowe pożywienie
     */
    public void checkFood()
    {
        if (controller.getSnakeX().get(0) == foodX && controller.getSnakeY().get(0) == foodY)
        {
            if (superFoodCounter % 5 == 0)
            {
                controller.incrementSize();
                double tmp = Math.random()*50;
                score += ((int) tmp)*controller.getDiffulty();
                controller.setVisibleValue();
                controller.getSnakeX().add(controller.getSnakeX().get(1));
                controller.getSnakeY().add(controller.getSnakeY().get(1));
                superFoodCounter++;
                dropFood();
            }
            else
            {
                controller.incrementSize();
                score += 10*controller.getDiffulty();
                controller.setVisibleValue();
                controller.getSnakeX().add(controller.getSnakeX().get(1));
                controller.getSnakeY().add(controller.getSnakeY().get(1));
                superFoodCounter++;
                dropFood();
            }
        }
    }

}
