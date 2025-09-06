package midias.controller;

import midias.exception.MidiaInexistenteException;
import midias.midia.Midias;
import midias.sistema.SistemaMidias;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collection;

public class MidiaRemoverController implements ActionListener {
    private SistemaMidias sistema;
    private JFrame janelaPrincipal;

    public MidiaRemoverController(SistemaMidias sistema, JFrame janela){
        this.sistema = sistema;
        janelaPrincipal = janela;
    }

    public void actionPerformed(ActionEvent e) {
        String titulo = JOptionPane.showInputDialog(janelaPrincipal, "qual o nome do titulo que deseja remover? ");
        try {
            sistema.removerMidia(titulo);
            sistema.salvarDados();
            JOptionPane.showMessageDialog(janelaPrincipal, "nidia removida com sucesso");
        }catch (MidiaInexistenteException | IOException ex){
            JOptionPane.showMessageDialog(janelaPrincipal, "midia não encontrada");
        }
    }
}
