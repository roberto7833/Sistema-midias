package midias.controller;

import midias.midia.Midias;
import midias.sistema.SistemaMidias;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class MidiaSeachAtorController implements ActionListener {
    private SistemaMidias sistema;
    private JFrame janelaPrincipal;

    public MidiaSeachAtorController(SistemaMidias sistema, JFrame janela){
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    public void actionPerformed(ActionEvent e) {
        String ator = JOptionPane.showInputDialog(janelaPrincipal, "qual o nome do ator a pesquisar? ");
        Collection<Midias> atorPesq = sistema.pesquisarPorAtor(ator);
        if(atorPesq.size()>0){
            JOptionPane.showMessageDialog(janelaPrincipal, "Midias encontradas");
            for(Midias m: atorPesq){
                JOptionPane.showMessageDialog(janelaPrincipal, m.toString());
            }
        }
    }
}
