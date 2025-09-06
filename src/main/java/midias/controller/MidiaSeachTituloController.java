package midias.controller;

import midias.midia.Midias;
import midias.sistema.SistemaMidias;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class MidiaSeachTituloController implements ActionListener {
    private SistemaMidias sistema;
    private JFrame janelaPrincipal;

    public MidiaSeachTituloController(SistemaMidias sistema, JFrame janela){
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    public void actionPerformed(ActionEvent e) {
        String titulo = JOptionPane.showInputDialog(janelaPrincipal, "qual o nome da midia a pesquisar? ");
        Collection<Midias> tituloPesq = sistema.pesquisarPorTitulo(titulo);
        if(tituloPesq.size()>0){
            JOptionPane.showMessageDialog(janelaPrincipal, "midias encontradas");
            for(Midias m: tituloPesq){
                JOptionPane.showMessageDialog(janelaPrincipal, m.toString());
            }
        }else{
            JOptionPane.showMessageDialog(janelaPrincipal, "nenhuma midia encontrada");
        }
    }
}
