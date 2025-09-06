package midias.controller;

import midias.midia.Midias;
import midias.sistema.SistemaMidias;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class MidiaSeachGeneroController implements ActionListener {
    private SistemaMidias sistema;
    private JFrame janelaPrincipal;

    public MidiaSeachGeneroController(SistemaMidias sistema, JFrame janela){
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    public void actionPerformed(ActionEvent e) {
        String genero = JOptionPane.showInputDialog(janelaPrincipal, "qual lgenero deseja pesquisar? ");
        Collection<Midias> generoPesq = sistema.pesquisarPorGenero(genero);
        if(generoPesq.size()>0){
            JOptionPane.showMessageDialog(janelaPrincipal, "midias encontradas");
            for(Midias m: generoPesq){
                JOptionPane.showMessageDialog(janelaPrincipal, m.toString());
            }
        }else{
            JOptionPane.showMessageDialog(janelaPrincipal, "não foi encontrado nenhuma midia com o genero pesquisado");
        }
    }
}
