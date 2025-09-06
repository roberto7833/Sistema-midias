package midias.sistema;

import midias.exception.MidiaInexistenteException;
import midias.exception.MidiaJaExisteException;
import midias.midia.Filme;
import midias.midia.GravadorDeDados;
import midias.midia.Midias;
import midias.midia.Serie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ProgramaSistemaMidias implements SistemaMidias {
    private HashMap<String, Midias> midias;
    private GravadorDeDados gravador;

    public ProgramaSistemaMidias(){
        this.midias = new HashMap<>();
        this.gravador = new GravadorDeDados();

        try {
            this.midias = gravador.recuperarDados();//recupera os dados no sistema
        }catch (IOException e){
            this.midias = new HashMap<>();//caso não tenha, inicia um map vazio
        }
    }
    public void cadastrarMidia(Midias midia)throws MidiaJaExisteException{
        if(this.midias.containsKey(midia.getTitulo())){
            throw new MidiaJaExisteException("Já existe uma midia cadastrada com esse nome "+midia.getTitulo());
        }
        this.midias.put(midia.getTitulo(), midia);
    }
    public Collection<Midias> pesquisarPorTitulo(String titulo){
        Collection<Midias> tituloPesq = new ArrayList<>();
        for(Midias m: this.midias.values()){
            if(m.getTitulo().equalsIgnoreCase(titulo)){
                tituloPesq.add(m);
            }
        }
        return tituloPesq;
    }
    public Collection<Midias> pesquisarPorGenero(String genero){
        Collection<Midias> generoPesq = new ArrayList<>();
        for(Midias m: this.midias.values()){
            if(m.getGenero().equalsIgnoreCase(genero)){
                generoPesq.add(m);
            }
        }
        return generoPesq;
    }
    public Collection<Midias> pesquisarPorAno(int ano){
        Collection<Midias> anoPesq = new ArrayList<>();
        for(Midias m: this.midias.values()){
            if(m.getAnoLancamento()==ano){
                anoPesq.add(m);
            }
        }
        return anoPesq;
    }
    public Collection<Midias> pesquisarPorAtor(String ator){
        Collection<Midias> atorPesq = new ArrayList<>();
        for(Midias m: this.midias.values()){
            String [] elenco = m.getElenco();
            for(String nome: elenco){
                if(nome.equalsIgnoreCase(ator)){
                    atorPesq.add(m);
                }
            }
        }
        return atorPesq;
    }
    public Collection<Midias> pesquisarPorDiretor(String diretor){
        Collection<Midias> diretorPesq = new ArrayList<>();
        for(Midias m: this.midias.values()){
            if(m.getDiretor().equalsIgnoreCase(diretor)){
                diretorPesq.add(m);
            }
        }
        return diretorPesq;
    }
    public Collection<Midias> mostrarTodosOsFilmes(){
        Collection<Midias> filmesCadastrados = new ArrayList<>();
        for(Midias m: this.midias.values()){
            if(m instanceof Filme){
                filmesCadastrados.add(m);
            }
        }
        return filmesCadastrados;
    }
    public Collection<Midias> mostrarTodasAsSeries(){
        Collection<Midias> seriesCadastradas = new ArrayList();
        for(Midias m: this.midias.values()){
            if(m instanceof Serie){
                seriesCadastradas.add(m);
            }
        }
        return seriesCadastradas;
    }
    public Collection<Midias> mostrarTodasAsMidias(){
        return this.midias.values();
    }
    public void atualizarMidia(String titulo, String novoTitulo, String genero, int anoLancamento, String [] elenco, String diretor)throws MidiaInexistenteException{
        if(!midias.containsKey(titulo)){
            throw new MidiaInexistenteException("Nunhuma midia encontrada com o titulo "+titulo);
        }
        Midias midia = midias.get(titulo);
        midia.setTitulo(novoTitulo);
        midia.setGenero(genero);
        midia.setAnoLancamento(anoLancamento);
        midia.setElenco(elenco);
        midia.setDiretor(diretor);

        if(!titulo.equalsIgnoreCase(novoTitulo)){
            midias.remove(titulo);
            midias.put(novoTitulo, midia);
        }
    }
    public void removerMidia(String titulo)throws MidiaInexistenteException{
        if(!midias.containsKey(titulo)){
            throw new MidiaInexistenteException("Nenhuma midia encontrada com o titulo "+titulo);
        }
        midias.remove(titulo);
    }
    public void salvarDados()throws IOException{
        this.gravador.salvarDados(midias);
    }
    public void recuperarDados()throws IOException{
        this.midias = gravador.recuperarDados();
    }
}
