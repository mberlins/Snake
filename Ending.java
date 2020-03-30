import javax.swing.*;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa Ending dziedzicząca z klasy JFrame
 */
public class Ending extends JFrame
{
    /**
     * Obiekt klasy kontroler
     */
    private Controller controller;

    /**
     * Przycisk usuwający okienko
     */
    private JButton playAgain;

    /**
     * Przycisk uruchamiający okno zapisu wyniku
     */
    private JButton saveScore;

    /**
     * Etykieta wyświetlająca komunikat
     */
    private JLabel message;

    /**
     * Etykieta wyświetlająca ilość zdobytych punktów
     */
    private JLabel score;

    /**
     * Konstruktor wyświetlający nazwę, uruchamiający okienko z odpowiednimi parametrami oraz przyciskami
     * @param controller inicjalizuje pole controller
     */
    public Ending(Controller controller)
    {
        super("Results");

        this.controller = controller;

        setSize(256, 256);
        setLayout(null);
        setResizable(false);
        setLocation(new Point(800, 380));

        playAgain = new JButton("Play again");
        playAgain.setFocusable(false);
        playAgain.setBounds(120, 160, 100, 30);
        playAgain.addActionListener(new PlayAgainListener());
        playAgain.setEnabled(true);

        saveScore = new JButton("Save score");
        saveScore.setFocusable(false);
        saveScore.setBounds(10, 160, 100, 30);
        saveScore.addActionListener(new SaveScoreListener());
        saveScore.setEnabled(true);

        message = new JLabel("Your result is  ");
        message.setBounds(10, 40, 150, 30);

        score = new JLabel(String.valueOf(showScoreValue()));
        score.setBounds(190, 40, 60,30);

        add(playAgain);
        add(saveScore);
        add(message);
        add(score);

        setVisible(true);
    }

    /**
     * @return ilość zdobytych punktów
     */
    public int showScoreValue()
    {
        int score = controller.readScore();
        return score;
    }

    /**
     * Klasa wewnętrzna obsługująca kliknięcie przycisku playAgain
     */
    private class PlayAgainListener implements ActionListener
    {
        /**
         * Po kliknięciu usuwa okienko
         * @param e obiekt klasy ActionEvent
         */
        public void actionPerformed(ActionEvent e)
        {
            dispose();
        }
    }

    /**
     * Klasa wewnętrzna obsługująca kliknięcie przycisku SaveScore
     */
    private class SaveScoreListener implements ActionListener
    {
        /**
         * Po kliknięciu usuwa okienko
         * @param e obiekt klasy ActionEvent
         */
        public void actionPerformed(ActionEvent e)
        {
            controller.showResults();
            dispose();
        }
    }
}