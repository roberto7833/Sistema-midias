package midias.controller;

import midias.midia.Midias;
import midias.sistema.SistemaMidias;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class MidiaSeachDiretorController implements ActionListener {
    private SistemaMidias sistema;
    private JFrame janelaPricipal;

    public MidiaSeachDiretorController(SistemaMidias sistema, JFrame janelaPricipal) {
        this.sistema = sistema;
        this.janelaPricipal = janelaPricipal;
    }

    public void actionPerformed(ActionEvent e) {
        String diretor = JOptionPane.showInputDialog(janelaPricipal, "digite o nome do diretor: ");
        Collection<Midias> diretorPesq = sistema.pesquisarPorDiretor(diretor);
        if(diretorPesq.size()>0){
            JOptionPane.showMessageDialog(janelaPricipal, "midias encontradas");
            for(Midias m: diretorPesq){
                JOptionPane.showMessageDialog(janelaPricipal, m.toString());
            }
        }else{
            JOptionPane.showMessageDialog(janelaPricipal, "nenhuma midia encontrada com o diretor informado");
        }
    }
}
