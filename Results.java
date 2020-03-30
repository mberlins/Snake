import javax.swing.*;
import java.util.Scanner;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa Results dziedzicząca z klasy JFrame
 */
public class Results extends JFrame
{
    /**
     * Obiekt klasy kontroler
     */
    private Controller controller;

    /**
     * Przycisk zapisujący wyniki
     */
    private JButton save;

    /**
     * Przycisk usuwający okienko
     */
    private JButton cancel;

    /**
     * Etykieta podająca komunikat
     */
    private JLabel message;

    /**
     * Konstruktor wyświetlający nazwę, uruchamiający okienko z odpowiednimi parametrami oraz przyciskami
     * @param controller inicjalizuje pole controller
     */
    public Results(Controller controller)
    {
        super("Results");

        this.controller = controller;

        setSize(256, 256);
        setLayout(null);
        setResizable(false);
        setLocation(new Point(800, 380));

        save = new JButton("Save");
        save.setFocusable(false);
        save.setBounds(120, 160, 100, 30);
        //save.addActionListener(new SaveListener());
        save.setEnabled(false);

        cancel = new JButton("Cancel");
        cancel.setFocusable(false);
        cancel.setBounds(10, 160, 100, 30);
        cancel.addActionListener(new CancelListener());
        cancel.setEnabled(true);

        message = new JLabel("Saving results is available in full version!");
        message.setBounds(7, 40, 230, 30);

        add(save);
        add(cancel);
        add(message);

        setVisible(true);
    }

    /**
     * Klasa wewnętrzna obsługująca kliknięcie przycisku cancel
     */
    private class CancelListener implements ActionListener
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
}