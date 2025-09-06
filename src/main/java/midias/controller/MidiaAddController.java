package midias.controller;

import midias.exception.MidiaJaExisteException;
import midias.midia.*;
import midias.sistema.SistemaMidias;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MidiaAddController implements ActionListener {
    private SistemaMidias sistema;
    private JFrame janelaPrincipal;

    public MidiaAddController(SistemaMidias sistema, JFrame janelaPrincipal) {
        this.sistema = sistema;
        this.janelaPrincipal = janelaPrincipal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int tipoMidia = obterOpcaoNumerica("1.Filme \n2.Serie \nEscolha uma opção");
        if(tipoMidia !=1 && tipoMidia !=2){
            JOptionPane.showMessageDialog(janelaPrincipal, "opção invalida");
            return;
        }
        Midias midia = MidiaFormulario.criarFormularioBase();
        if(midia !=null){
            String titulo = midia.getTitulo();
            String genero = midia.getGenero();
            int ano = midia.getAnoLancamento();
            String descricao = midia.getDescricao();
            String [] elenco = midia.getElenco();
            String diretor = midia.getDiretor();

            try {
                if(tipoMidia==1){
                    //cadastra filme
                    int duracao = obterOpcaoNumerica("digite a duração (em minutos)");
                    midia = new Filme(titulo, genero, ano, descricao, elenco, diretor, duracao);
                }else if(tipoMidia==2){
                    List<Episodio> eps = new ArrayList<>();
                    int quantEps = Integer.parseInt(JOptionPane.showInputDialog(janelaPrincipal, "quantos episodios deseja cadastrar? "));
                    for(int k=0; k<quantEps; k++){
                        String nomeEp = JOptionPane.showInputDialog(janelaPrincipal, "nome do episodio "+(k+1));
                        int duracaoEp = obterOpcaoNumerica( "duracao do episodio "+(k+1)+" em minutos");
                        Episodio episodio = new Episodio(nomeEp, duracaoEp);
                        eps.add(episodio);
                    }
                    midia = new Serie(titulo, genero, ano, descricao, elenco, diretor, eps);
                }else{
                    JOptionPane.showMessageDialog(janelaPrincipal, "Opção invalida");
                    return;
                }
                sistema.cadastrarMidia(midia);
                sistema.salvarDados();
                JOptionPane.showMessageDialog(janelaPrincipal, "midia cadastrada com sucesso");
            }catch (MidiaJaExisteException ex){
                JOptionPane.showMessageDialog(janelaPrincipal, "já existe uma midia cadastrada com esse nome ");
                ex.printStackTrace();
            }catch (Exception ex){
                JOptionPane.showMessageDialog(janelaPrincipal, "Erro ao cadastrar midia "+ex.getMessage());
                ex.printStackTrace();
            }
        }


    }
    public int obterOpcaoNumerica(String msg){
        int opcao = -1;
        try {
            opcao = Integer.parseInt(JOptionPane.showInputDialog(janelaPrincipal, msg));
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(janelaPrincipal, e);
        }
        return opcao;
    }
}
