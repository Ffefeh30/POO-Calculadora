package Qualquer1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class CalculadoraFelipe {

    private JFrame frmFelipeBarrozo;
    private JTextField JTextfieldR;

    // Estado da calculadora
    double v1 = 0, v2 = 0;
    String Op = "";
    boolean novoNumero = true;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CalculadoraFelipe window = new CalculadoraFelipe();
                    window.frmFelipeBarrozo.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CalculadoraFelipe() {
        initialize();
    }

    // ===================== MÉTODOS OBRIGATÓRIOS =====================

    public double adicao(double a, double b) {
        return a + b;
    }

    public double subtracao(double a, double b) {
        return a - b;
    }

    public double multiplicacao(double a, double b) {
        return a * b;
    }

    public double divisao(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Divisao por zero");
        }
        return a / b;
    }

    public double potencia(double base, double expoente) {
        return Math.pow(base, expoente);
    }

    // ================================================================

    private String formatar(double valor) {
        if (valor == Math.floor(valor) && !Double.isInfinite(valor)) {
            return String.valueOf((long) valor);
        }
        return String.valueOf(valor);
    }

    private void digitar(String d) {
        if (novoNumero) {
            JTextfieldR.setText(d);
            novoNumero = false;
        } else {
            JTextfieldR.setText(JTextfieldR.getText() + d);
        }
    }

    private void escolherOperacao(String operacao) {
        if (!JTextfieldR.getText().isEmpty()) {
            v1 = Double.parseDouble(JTextfieldR.getText());
        }
        Op = operacao;
        novoNumero = true;
    }

    private void calcular() {
        if (Op.isEmpty() || JTextfieldR.getText().isEmpty()) {
            return;
        }
        v2 = Double.parseDouble(JTextfieldR.getText());
        try {
            double resultado = 0;
            if (Op.equals("soma")) {
                resultado = adicao(v1, v2);
            } else if (Op.equals("Menos")) {
                resultado = subtracao(v1, v2);
            } else if (Op.equals("Vezes")) {
                resultado = multiplicacao(v1, v2);
            } else if (Op.equals("Divisao")) {
                resultado = divisao(v1, v2);
            } else if (Op.equals("Potencia")) {
                resultado = potencia(v1, v2);
            }
            JTextfieldR.setText(formatar(resultado));
        } catch (ArithmeticException ex) {
            JTextfieldR.setText("Erro");
        }
        Op = "";
        novoNumero = true;
    }

    private JButton criarBotao(String texto, int x, int y, int w, Color cor, ActionListener acao) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 11));
        botao.setBackground(cor);
        botao.setBounds(x, y, w, 44);
        botao.addActionListener(acao);
        frmFelipeBarrozo.getContentPane().add(botao);
        return botao;
    }

    private void criarDigito(final String d, int x, int y) {
        criarBotao(d, x, y, 41, new Color(192, 192, 192), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                digitar(d);
            }
        });
    }

    private void initialize() {
        frmFelipeBarrozo = new JFrame();
        // TITLE FORM: nome completo e RA  (troque o RA pelo seu)
        frmFelipeBarrozo.setTitle("Felipe Barrozo dos Santos - RA 0000000");
        frmFelipeBarrozo.getContentPane().setBackground(new Color(108, 102, 207));
        frmFelipeBarrozo.getContentPane().setLayout(null);

        JTextfieldR = new JTextField();
        JTextfieldR.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
        JTextfieldR.setBackground(new Color(192, 192, 192));
        JTextfieldR.setBounds(10, 52, 274, 111);
        frmFelipeBarrozo.getContentPane().add(JTextfieldR);
        JTextfieldR.setColumns(10);

        // Dígitos
        criarDigito("0", 110, 381);
        criarDigito("1", 64, 326);
        criarDigito("2", 110, 326);
        criarDigito("3", 161, 326);
        criarDigito("4", 64, 275);
        criarDigito("5", 110, 275);
        criarDigito("6", 161, 275);
        criarDigito("7", 64, 220);
        criarDigito("8", 110, 220);
        criarDigito("9", 160, 220);

        // Limpar
        criarBotao("c", 10, 179, 41, Color.WHITE, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextfieldR.setText("");
                v1 = 0;
                v2 = 0;
                Op = "";
                novoNumero = true;
            }
        });

        // Potência
        criarBotao("^", 230, 179, 41, Color.LIGHT_GRAY, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                escolherOperacao("Potencia");
            }
        });

        // Adição
        criarBotao("+", 228, 234, 43, Color.LIGHT_GRAY, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                escolherOperacao("soma");
            }
        });

        // Subtração
        criarBotao("-", 230, 289, 41, Color.LIGHT_GRAY, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                escolherOperacao("Menos");
            }
        });

        // Multiplicação
        criarBotao("x", 230, 344, 41, Color.LIGHT_GRAY, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                escolherOperacao("Vezes");
            }
        });

        // Divisão
        criarBotao("/", 230, 399, 41, Color.LIGHT_GRAY, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                escolherOperacao("Divisao");
            }
        });

        // Igual
        criarBotao("=", 10, 234, 41, Color.WHITE, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcular();
            }
        });

        frmFelipeBarrozo.setBounds(100, 100, 310, 505);
        frmFelipeBarrozo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
