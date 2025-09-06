package midias.controller;

import midias.midia.Midias;
import midias.sistema.SistemaMidias;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class MidiaTodasMidiasCadastradas implements ActionListener {
    private SistemaMidias sistema;
    private JFrame janelaPrincipal;

    public MidiaTodasMidiasCadastradas(SistemaMidias sistema, JFrame janela){
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }


    public void actionPerformed(ActionEvent e) {
        Collection<Midias> midiasCadastradas = sistema.mostrarTodasAsMidias();
        for(Midias m: midiasCadastradas){
            JOptionPane.showMessageDialog(janelaPrincipal, m.toString());
        }
    }
}
