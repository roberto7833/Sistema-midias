package midias.midia;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class MidiaFormulario implements Serializable {

    public static Midias criarFormularioBase(){
        JTextField tituloField = new JTextField(20);
        JTextField generoField = new JTextField(20);
        JTextField anoField = new JTextField(4);
        JTextField descricaoField = new JTextField(60);
        JTextField elencoField = new JTextField(60);
        JTextField diretorField = new JTextField(40);

        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.setPreferredSize(new Dimension(300, 250));
        panel.add(new JLabel("Titulo"));
        panel.add(tituloField);
        panel.add(new JLabel("Genero"));
        panel.add(generoField);
        panel.add(new JLabel("Ano de lançamento"));
        panel.add(anoField);
        panel.add(new JLabel("Descrição"));
        panel.add(descricaoField);
        panel.add(new JLabel("Elenco"));
        panel.add(elencoField);
        panel.add(new JLabel("Diretor"));
        panel.add(diretorField);

        while (true) {
            int result = JOptionPane.showConfirmDialog(null, panel, "Confirme os dados",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    String titulo = tituloField.getText();
                    String genero = generoField.getText();
                    String anoText = anoField.getText();
                    String descricao = descricaoField.getText();
                    String [] elenco = elencoField.getText().split(",");
                    String diretor = diretorField.getText();

                    if (titulo.trim().isEmpty() || genero.trim().isEmpty() || anoText.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Os campos Título, Gênero e Ano de Lançamento são obrigatórios.");
                        continue; // Reexibe o formulário
                    }

                    int ano = Integer.parseInt(anoText);
                    return new Midias(titulo, genero, ano, descricao, elenco, diretor);

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "O ano de lançamento deve ser um número válido.");
                    // O loop continua, reexibindo o formulário
                }
            } else {
                return null; // O usuário clicou em "Cancelar" ou fechou a janela
            }
        }
    }
}