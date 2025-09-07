package midias;

import midias.exception.MidiaInexistenteException;
import midias.exception.MidiaJaExisteException;
import midias.midia.Filme;
import midias.midia.Midias;
import midias.sistema.ProgramaSistemaMidias;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

public class MidiaTest {
    private ProgramaSistemaMidias sistema;

    // Este método é executado antes de cada teste
    @BeforeEach
    void setUp() {
        sistema = new ProgramaSistemaMidias();
    }

    // --- Testes de Cadastro ---
    @Test
    void testCadastrarMidiaComSucesso() throws MidiaJaExisteException, MidiaJaExisteException {
        // Testa se uma mídia é cadastrada corretamente
        Midias filme = new Filme("O Poderoso Chefão", "Drama", 1972, "Descrição", new String[]{"Marlon Brando"}, "Francis Ford Coppola", 175);
        sistema.cadastrarMidia(filme);

        // Verifica se a mídia está presente no sistema
        Collection<Midias> midiasEncontradas = sistema.pesquisarPorTitulo("O Poderoso Chefão");
        assertEquals(1, midiasEncontradas.size());
    }

    @Test
    void testCadastrarMidiaExistenteLancaExcecao() {
        // Testa se a exceção é lançada ao tentar cadastrar uma mídia com o mesmo título
        Midias filme = new Filme("O Poderoso Chefão", "Drama", 1972, "Descrição", new String[]{"Marlon Brando"}, "Francis Ford Coppola", 175);
        try {
            sistema.cadastrarMidia(filme);
            assertThrows(MidiaJaExisteException.class, () -> sistema.cadastrarMidia(filme));
        } catch (MidiaJaExisteException e) {
            // Este catch é necessário para que o primeiro cadastro ocorra
            // e a exceção seja testada no segundo
        }
    }

    // --- Testes de Pesquisa ---
    @Test
    void testPesquisarPorTituloEncontraMidia() throws MidiaJaExisteException {
        Midias filme = new Filme("O Poderoso Chefão", "Drama", 1972, "Descrição", new String[]{"Marlon Brando"}, "Francis Ford Coppola", 175);
        sistema.cadastrarMidia(filme);

        Collection<Midias> resultado = sistema.pesquisarPorTitulo("O Poderoso Chefão");
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
    }

    @Test
    void testPesquisarPorTituloNaoEncontraMidia() {
        // Testa se o método retorna uma coleção vazia quando a mídia não existe
        Collection<Midias> resultado = sistema.pesquisarPorTitulo("Titulo Inexistente");
        assertTrue(resultado.isEmpty());
        assertEquals(0, resultado.size());
    }

    @Test
    void testPesquisarPorAtorEncontraMidia() throws MidiaJaExisteException {
        Midias filme = new Filme("O Poderoso Chefão", "Drama", 1972, "Descrição", new String[]{"Al Pacino", "Marlon Brando"}, "Francis Ford Coppola", 175);
        sistema.cadastrarMidia(filme);

        Collection<Midias> resultado = sistema.pesquisarPorAtor("Al Pacino");
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
    }

    // --- Testes de Remoção ---
    @Test
    void testRemoverMidiaComSucesso() throws MidiaJaExisteException, MidiaInexistenteException, MidiaInexistenteException {
        Midias filme = new Filme("O Poderoso Chefão", "Drama", 1972, "Descrição", new String[]{"Marlon Brando"}, "Francis Ford Coppola", 175);
        sistema.cadastrarMidia(filme);

        sistema.removerMidia("O Poderoso Chefão");

        // Verifica se a mídia foi removida
        Collection<Midias> midiasRestantes = sistema.pesquisarPorTitulo("O Poderoso Chefão");
        assertTrue(midiasRestantes.isEmpty());
    }

    @Test
    void testRemoverMidiaInexistenteLancaExcecao() {
        // Testa se a exceção é lançada ao remover uma mídia que não existe
        assertThrows(MidiaInexistenteException.class, () -> sistema.removerMidia("Titulo Inexistente"));
    }

    // --- Testes de Atualização ---
    @Test
    void testAtualizarMidiaComSucesso() throws MidiaJaExisteException, MidiaInexistenteException {
        Midias filmeOriginal = new Filme("O Poderoso Chefão", "Drama", 1972, "Descrição", new String[]{"Marlon Brando"}, "Francis Ford Coppola", 175);
        sistema.cadastrarMidia(filmeOriginal);

        sistema.atualizarMidia("O Poderoso Chefão", "O Poderoso Chefão - Parte II", "Drama", 1974, new String[]{"Al Pacino"}, "Francis Ford Coppola");

        // Verifica se a mídia foi atualizada e se o título mudou
        Collection<Midias> resultado = sistema.pesquisarPorTitulo("O Poderoso Chefão - Parte II");
        assertFalse(resultado.isEmpty());
        Midias midiaAtualizada = resultado.iterator().next();
        assertEquals(1974, midiaAtualizada.getAnoLancamento());
        assertEquals("Al Pacino", midiaAtualizada.getElenco()[0]);
    }

    @Test
    void testAtualizarMidiaInexistenteLancaExcecao() {
        assertThrows(MidiaInexistenteException.class, () -> sistema.atualizarMidia("Titulo Inexistente", "Novo Titulo", "Genero", 2024, new String[]{}, "Diretor"));
    }

    // --- Teste de Pesquisa de Gênero ---
    @Test
    void testPesquisarPorGeneroEncontraMidia() throws MidiaJaExisteException {
        Midias filme = new Filme("Matrix", "Ficção Científica", 1999, "Descrição", new String[]{"Keanu Reeves"}, "Lana Wachowski", 136);
        sistema.cadastrarMidia(filme);

        Collection<Midias> resultado = sistema.pesquisarPorGenero("Ficção Científica");
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
    }
}
