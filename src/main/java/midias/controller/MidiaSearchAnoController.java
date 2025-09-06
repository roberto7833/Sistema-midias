package midias.controller;

import midias.midia.Midias;
import midias.sistema.SistemaMidias;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class MidiaSearchAnoController implements ActionListener {
    private SistemaMidias sistema;
    private JFrame janelaPrincipal;

    public MidiaSearchAnoController(SistemaMidias sistema, JFrame janela){
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    public void actionPerformed(ActionEvent e) {
        int ano = Integer.parseInt(JOptionPane.showInputDialog(janelaPrincipal, "digite o ano: "));
        Collection<Midias> anoPesq = sistema.pesquisarPorAno(ano);
        if(anoPesq.size()>0){
            JOptionPane.showMessageDialog(janelaPrincipal, "midias encontradas");
            for(Midias m: anoPesq){
                JOptionPane.showMessageDialog(janelaPrincipal, m.toString());
            }
        }else{
            JOptionPane.showMessageDialog(janelaPrincipal, "nenhuma midia encontrada");
        }
    }
}
