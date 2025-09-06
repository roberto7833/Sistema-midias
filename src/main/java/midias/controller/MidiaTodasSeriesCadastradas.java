package midias.controller;

import midias.midia.Midias;
import midias.sistema.SistemaMidias;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class MidiaTodasSeriesCadastradas implements ActionListener {
    private SistemaMidias sistema;
    private JFrame janelaPrincipal;

    public MidiaTodasSeriesCadastradas(SistemaMidias sistema, JFrame janela){
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    public void actionPerformed(ActionEvent e) {
        Collection<Midias> seriesCadastradas = sistema.mostrarTodasAsSeries();
        for(Midias m: seriesCadastradas){
            JOptionPane.showMessageDialog(janelaPrincipal, m.toString());
        }
    }
}
