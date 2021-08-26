package ru.geebrains.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeekbrainsWindow extends JFrame {
    private int randomNumber;
    private JTextField textField;
    private int count = 3;

    public GeekbrainsWindow() {
        this.randomNumber = (int) (Math.random() * 10) + 1; // [1, 10]

        setTitle("Игра: Угадай число");
        setBounds(600, 300, 600, 160);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setLayout(new FlowLayout());
        setResizable(false);

        textField = new JTextField();
        add(textField, BorderLayout.NORTH);
        textField.setText("Программа загадала число от 1 до 10");
        textField.setEditable(false);
        textField.setHorizontalAlignment(SwingConstants.CENTER);

        JButton jButton = new JButton();
        add(jButton, BorderLayout.SOUTH);
        jButton.setText("Перезагрузить игру");
        jButton.setHorizontalAlignment(SwingConstants.CENTER);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomNumber=(int) (Math.random() * 10) + 1;
                count=3;
                textField.setText("Игра перезапущена. Загадано новое число");
            }
        });

        Font font = new Font("Arial", Font.PLAIN, 20);
        textField.setFont(font);

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 10));
        buttonsPanel.setBackground(Color.BLUE);
        add(buttonsPanel, BorderLayout.CENTER);

        for (int i = 1; i <= 10; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.setFont(font);
            buttonsPanel.add(button);
            int buttonIndex = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tryToAnswer(buttonIndex);
                }
            });
        }


        setVisible(true);
    }

    public void tryToAnswer(int answer) {
        if (count==0){
            textField.setText("Игра окончена, вы проиграли.");
            return;

        }
        if(answer < randomNumber) {
            textField.setText("Не угадали! Загаданное число больше!Осталось попыток "+count);
            count--;
            return;
        }
        if(answer > randomNumber) {
            textField.setText("Не угадали! Загаданное число меньше! Осталось попыток "+count);
            count--;
        }
        else {
            textField.setText("Вы угадали!!! Ответ: " + randomNumber);
            count=3;
        }
    }
}
