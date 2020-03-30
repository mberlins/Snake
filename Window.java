import javax.swing.*;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa Window dziedzicząca z klasy JFrame
 */
public class Window extends JFrame
{
    /**
     * Obiekt klasy kontroler
     */
    private Controller controller;
    /**
     * Obiekt klasy map
     */
    private Map map;

    /**
     * Przycisk rozpoczynający rozgrywkę
     */
    private static JButton start;

    /**
     * Przycisk aktywujący przyciski poziomów trudności
     */
    private static JButton difficulty;

    /**
     * Przycisk ustawiający niski poziom trudności
     */
    private static JButton easy;

    /**
     * Przycisk ustawiający średni poziom trudności
     */
    private static JButton medium;

    /**
     * Przycisk ustawiający wysoki poziom trudności
     */
    private static JButton hard;

    /**
     * Przycisk włączający pauzę
     */
    private static JButton pause;

    /**
     * Przycisk włączający pauzę
     */
    private static JButton playAgain;

    /**
     * Etykieta oznaczająca wynik gracza
     */
    private static JLabel score;

    /**
     * Etykieta wskazująca aktualny rezultat
     */
    private static JLabel result;


    /**
     * Konstruktor wyświetlający nazwę, uruchamiający okienko z odpowiednimi parametrami oraz przyciskami
     * @param controller inicjalizuje pole controller
     */
    public Window(Controller controller)
    {
        super("Deluxe Snake Evolution FX2200");

        this.controller = controller;

        setSize(720, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setLocation(new Point(600, 180));

        start = new JButton("Start");
        start.setFocusable(false);
        start.setBounds(10, 640, 100, 30);
        start.addActionListener(new StartListener());
        start.setEnabled(false);

        difficulty = new JButton("Difficulty");
        difficulty.setFocusable(false);
        difficulty.setBounds(110, 640, 100, 30);
        difficulty.addActionListener(new DifficultyListener());

        easy = new JButton("Easy");
        easy.setFocusable(false);
        easy.setBounds(210, 640, 100, 30);
        easy.setEnabled(false);
        easy.addActionListener(new EasyListener());

        medium = new JButton("Medium");
        medium.setFocusable(false);
        medium.setBounds(310, 640, 100, 30);
        medium.setEnabled(false);
        medium.addActionListener(new MediumListener());

        hard = new JButton("Hard");
        hard.setFocusable(false);
        hard.setBounds(410, 640, 100, 30);
        hard.setEnabled(false);
        hard.addActionListener(new HardListener());

        pause = new JButton("Pause");
        pause.setFocusable(false);
        pause.setBounds(510, 640, 100, 30);
        pause.setEnabled(false);
        pause.addActionListener(new PauseListener());

        playAgain = new JButton("Continue");
        playAgain.setFocusable(false);
        playAgain.setBounds(610, 640, 90, 30);
        playAgain.setEnabled(false);
        playAgain.addActionListener(new PlayAgainListener());

        result = new JLabel("Score:");
        result.setBounds(650, 30, 45, 30);

        score = new JLabel("0");
        score.setBounds(660, 50, 45, 30);

        this.map = new Map(this.controller);
        map.setBounds(0, 0, map.getWIDTH(), map.getHEIGHT());

        add(map);
        add(start);
        add(difficulty);
        add(easy);
        add(medium);
        add(hard);
        add(pause);
        add(playAgain);

        add(result);
        add(score);

        setVisible(true);
    }

    /**
     * @return obiekt klasy Map
     */
    public Map getMap()
    {
        return map;
    }

    /**
     * @param score ustawia do wyświetlenia aktualny wynik gracza
     */
    public static void setScoreValue(int score) { Window.score.setText(String.valueOf(score)); }

    /**
     * @param tmp aktywuje lub dezaktywuje przycisk start
     */
    public static void showStart(boolean tmp) { start.setEnabled(tmp); }

    /**
     * @param tmp Aktywuje lub dezaktywuje przycisk difficulty
     */
    public static void showDifficulty(boolean tmp) { difficulty.setEnabled(tmp); }

    /**
     * Klasa wewnętrzna obsługująca kliknięcie przycisku start
     */
    private class StartListener implements  ActionListener
    {
        /**
         * Po kliknięciu przycisku start ukrywa przyciski start,difficulty, easy, medium, hard, pokazuje przycisk pause,
         * prosi controller o reset wyniku i uruchomienie licznika programu
         * @param e obiekt klasy ActionEvent
         */
        public void actionPerformed(ActionEvent e)
        {

            start.setEnabled(false);
            difficulty.setEnabled(false);
            easy.setEnabled(false);
            medium.setEnabled(false);
            hard.setEnabled(false);
            pause.setEnabled(true);
            controller.resetScore();
            setScoreValue(controller.readScore());
            controller.startCounter();
        }
    }

    /**
     * Klasa wewnętrzna obsługująca kliknięcie przycisku difficulty
     */
    private class DifficultyListener implements  ActionListener
    {
        /**
         * Po kliknięciu przycisku pokazuje przyciski wyboru poziomu trudności
         * @param e obiekt klasy ActionEvent
         */
        public void actionPerformed(ActionEvent e)
        {
            easy.setEnabled(true);
            medium.setEnabled(true);
            hard.setEnabled(true);
        }
    }

    /**
     * Klasa wewnętrzna obsługująca kliknięcie easy
     */
    private class EasyListener implements ActionListener
    {
        /**
         * Po kliknięciu pokazuje przycisk start oraz prosi kontoler o zmianę poziomu trudności
         * @param e obiekt klasy ActionEvent
         */
        public void actionPerformed(ActionEvent e)
        {
            start.setEnabled(true);
            controller.changeDifficulty(220);
        }
    }

    /**
     * Klasa wewnętrzna obsługująca kliknięcie medium
     */
    private class MediumListener implements ActionListener
    {
        /**
         * Po kliknięciu pokazuje przycisk start oraz prosi kontoler o zmianę poziomu trudności
         * @param e obiekt klasy ActionEvent
         */
        public void actionPerformed(ActionEvent e)
        {
            start.setEnabled(true);
            controller.changeDifficulty(150);
        }
    }

    /**
     * Klasa wewnętrzna obsługująca kliknięcie hard
     */
    private class HardListener implements ActionListener
    {
        /**
         * Po kliknięciu pokazuje przycisk start oraz prosi kontoler o zmianę poziomu trudności
         * @param e obiekt klasy ActionEvent
         */
        public void actionPerformed(ActionEvent e)
        {
            start.setEnabled(true);
            controller.changeDifficulty(75);
        }
    }

    /**
     * Klasa wewnętrzna obsługująca kliknięcie pause
     */
    private class PauseListener implements ActionListener
    {
        /**
         * Po kliknięciu prosi kontroler o zatrzymanie licznika gry
         * @param e obiekt klasy ActionEvent
         */
        public void actionPerformed(ActionEvent e)
        {
            playAgain.setEnabled(true);
            controller.stopCounter();
        }
    }

    /**
     * Klasa wewnętrzna obsługująca kliknięcie continue
     */
    private class PlayAgainListener implements ActionListener
    {
        /**
         * Po kliknięciu prosi kontroler o uruchomienie licznika gry, ukrycie przycisku continue oraz pokazanie przycisku pause
         * @param e obiekt klasy ActionEvent
         */
        public void actionPerformed(ActionEvent e)
        {
            pause.setEnabled(true);
            playAgain.setEnabled(false);
            controller.startCounter();
        }
    }
}