package midias.GUIComMenu;

import midias.controller.*;
import midias.sistema.ProgramaSistemaMidias;
import midias.sistema.SistemaMidias;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUIComMenu extends JFrame{
    JLabel linha1, linha2;
    ImageIcon midiaPNG = new ImageIcon("./imgs/midias.Png");
    Image imagemRecondicionada = midiaPNG.getImage().getScaledInstance(400,300, Image.SCALE_SMOOTH);
    ImageIcon imagemFinal = new ImageIcon(imagemRecondicionada);
    SistemaMidias midias = new ProgramaSistemaMidias();
    JMenuBar barraDeMenu = new JMenuBar();

    public GUIComMenu(){
        setTitle("Sistema midias");
        setSize(800,600);
        setLocation(150,150);
        setResizable(true);
        setBackground(Color.BLACK);
        linha1 = new JLabel("Sistema filmes", JLabel.CENTER);
        linha1.setForeground(Color.BLACK);
        linha2 = new JLabel(imagemFinal, JLabel.CENTER);
        linha2.setPreferredSize(new Dimension(400,300));

        setLayout(new GridLayout(3,1));
        add(linha1);
        add(linha2);

        JMenu menuCadastrar = new JMenu("Cadastrar");
        JMenuItem menuCadastrarMidia = new JMenuItem("Cadastrar midia");
        menuCadastrar.add(menuCadastrarMidia);
        JMenu menuPesquisar = new JMenu("Pesquisar");
        JMenuItem menuPesquisarTitulo = new JMenuItem("Pesquisar por titulo");
        menuPesquisar.add(menuPesquisarTitulo);
        JMenuItem menuPesquisarGenero = new JMenuItem("Pesquisar por genero");
        menuPesquisar.add(menuPesquisarGenero);
        JMenuItem menuPesquisarAno = new JMenuItem("Pesquisar por ano");
        menuPesquisar.add(menuPesquisarAno);
        JMenuItem menuPesquisarAtor = new JMenuItem("Pesquisar por ator");
        menuPesquisar.add(menuPesquisarAtor);
        JMenuItem menuPesquisarDiretor = new JMenuItem("Pesquisar por diretor");
        menuPesquisar.add(menuPesquisarDiretor);
        JMenu menuAtualizar = new JMenu("Atualizar");
        JMenuItem menuAtualizarMidia = new JMenuItem("Atualizar midia");
        menuAtualizar.add(menuAtualizarMidia);
        JMenu menuMidiasCadastradas = new JMenu("Midias cadastradas");
        JMenuItem menuFilmesCadastrados = new JMenuItem("Filmes cadastrados");
        menuMidiasCadastradas.add(menuFilmesCadastrados);
        JMenuItem menuSeriesCadastradas = new JMenuItem("Series cadastradas");
        menuMidiasCadastradas.add(menuSeriesCadastradas);
        JMenuItem menuTodasMidiasCadastradas = new JMenuItem("Todas midias cadastradas");
        menuMidiasCadastradas.add(menuTodasMidiasCadastradas);
        JMenu menuRemover = new JMenu("Remover");
        JMenuItem menuRemoverMidia = new JMenuItem("Remover midia");
        menuRemover.add(menuRemoverMidia);

        menuCadastrarMidia.addActionListener(new MidiaAddController(midias, this));
        menuPesquisarTitulo.addActionListener(new MidiaSeachTituloController(midias, this));
        menuPesquisarGenero.addActionListener(new MidiaSeachGeneroController(midias, this));
        menuPesquisarAno.addActionListener(new MidiaSeachAtorController(midias, this));
        menuPesquisarAtor.addActionListener(new MidiaSeachAtorController(midias, this));
        menuPesquisarDiretor.addActionListener(new MidiaSeachDiretorController(midias, this));
        menuFilmesCadastrados.addActionListener(new MidiaTodosFilmesCadastrados(midias, this));
        menuSeriesCadastradas.addActionListener(new MidiaTodasSeriesCadastradas(midias, this));
        menuTodasMidiasCadastradas.addActionListener(new MidiaTodasMidiasCadastradas(midias, this));
        menuRemoverMidia.addActionListener(new MidiaRemoverController(midias, this));

        barraDeMenu.add(menuCadastrar);
        barraDeMenu.add(menuPesquisar);
        barraDeMenu.add(menuAtualizar);
        barraDeMenu.add(menuMidiasCadastradas);
        barraDeMenu.add(menuRemover);

        setJMenuBar(barraDeMenu);
    }

    //...
    public static void main(String [] args)throws IOException{
        ProgramaSistemaMidias sistema;
        sistema = new ProgramaSistemaMidias();

        try {
            sistema.recuperarDados();
            JOptionPane.showMessageDialog(null, "Dados carregados com sucesso");
        }catch (IOException e){
            JOptionPane.showMessageDialog(null,"Não foi possivel carregar os dados");
        }
        JFrame janela = new GUIComMenu();
        janela.setVisible(true);
        //salva os dados
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
