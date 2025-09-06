package midias.sistema;

import midias.exception.MidiaInexistenteException;
import midias.exception.MidiaJaExisteException;
import midias.midia.Midias;

import java.io.IOException;
import java.util.Collection;

public interface SistemaMidias {
    void cadastrarMidia(Midias midia)throws MidiaJaExisteException;
    Collection<Midias> pesquisarPorTitulo(String titulo);
    Collection<Midias> pesquisarPorGenero(String genero);
    Collection<Midias> pesquisarPorAno(int ano);
    Collection<Midias> pesquisarPorAtor(String ator);
    Collection<Midias> pesquisarPorDiretor(String diretor);
    Collection<Midias> mostrarTodosOsFilmes();
    Collection<Midias> mostrarTodasAsSeries();
    Collection<Midias> mostrarTodasAsMidias();
    void atualizarMidia(String titulo, String novoTitulo, String genero, int anoLancamento, String [] elenco, String diretor)throws MidiaInexistenteException;
    void removerMidia(String titulo)throws MidiaInexistenteException;
    void salvarDados()throws IOException;
    void recuperarDados()throws IOException;
}
