import com.dac.tads.criptografia.MDFive;
import com.dac.tads.dao.CidadeDAO;
import com.dac.tads.dao.EnderecoDAO;
import com.dac.tads.dao.EntregadorDAO;
import com.dac.tads.dao.EstadoDAO;
import com.dac.tads.dao.GerenteDAO;
import com.dac.tads.dao.UsuarioDAO;
import com.dac.tads.model.Cidade;
import com.dac.tads.model.Endereco;
import com.dac.tads.model.Entrega;
import com.dac.tads.model.Entregador;
import com.dac.tads.model.Estado;
import com.dac.tads.model.Gerente;
import com.dac.tads.model.Usuario;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marco
 */
public class TesteTads {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        Estado estado = new Estado();
        estado.setNome("Parana");
        EstadoDAO estadoDAO = new EstadoDAO();
        estadoDAO.insertEstado(estado);
        
        cidades.add()
        
        EstadoDAO dao = new EstadoDAO();
        Estado estado = new Estado();
        estado = dao.selectEstado(1);   
        System.out.println(estado.getNome());
        System.out.println(dao.deleteEstado(estado));
        
        List<Estado> estados;
        
        EstadoDAO dao = new EstadoDAO();
        estados = dao.selectListEstado();
        
        for(Estado e:estados){
            System.out.println(e.getNome());
        }
        */
        
        /*
        Cidade cidade = new Cidade();
        Cidade cidade2 = new Cidade();
        Estado estado = new Estado();
        EstadoDAO estadoDAO = new EstadoDAO();
        CidadeDAO cidadeDAO = new CidadeDAO();
        estado = estadoDAO.selectEstado(2);
        
        List<Cidade> cidades = estado.getCidades();
        
        cidade.setEstado(estado);
        cidade.setNome("Curitiba");
        cidades.add(cidade);
        
        cidade2.setNome("Quatro Barras");
        cidade2.setEstado(estado);
        cidades.add(cidade2);
        
        estado.setCidades(cidades);
        
        estadoDAO.updateEstado(estado);
        =========================================================================
        
        Usuario usuario = new Usuario();
        Gerente gerente = new Gerente();
        Endereco endereco = new Endereco();
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        CidadeDAO cidadeDAO = new CidadeDAO();
        
        endereco.setCidade(cidadeDAO.selectCidade(2));
        endereco.setBairro("Santa felicidade");
        endereco.setComplemento("Casa 1");
        endereco.setNumero(320);
        
        enderecoDAO.insertEndereco(endereco);
        
        
        usuario.setCpf("12345678901");
        usuario.setEmail("marco.vieira.olivette@gmail.com");
        usuario.setNome("Marco Olivette");
        usuario.setSenha("123456");
        usuario.setTelefone("(41) 99999-9999");
        usuario.setTipo('c');
        usuario.setEndereco(endereco);
        
        
        gerente.setUsuario(usuario);
        
        GerenteDAO gerenteDAO = new GerenteDAO();
        
        gerenteDAO.insertGerente(gerente);
        
        =========================================================================
        
        
        
        
        Usuario usuario = new Usuario();
        Entregador entregador = new Entregador();
        
        usuario.setCpf("02123212321");
        usuario.setEmail("patrick.dziura@gmail.com");
        usuario.setNome("Patrick Dziura");
        usuario.setSenha("0123456789");
        usuario.setTelefone("(41) 99999-9999");
        usuario.setTipo('c');
        EnderecoDAO enderecoDao = new EnderecoDAO();
        usuario.setEndereco(enderecoDao.selectEndereco(1));
        
        UsuarioDAO usuarioDao = new UsuarioDAO();
        usuarioDao.insertUsuario(usuario);
        
        EntregadorDAO entregadorDao = new EntregadorDAO();
        entregadorDao.insertEntregador(entregador);
        
        Usuario usuario = new Usuario();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuario = usuarioDAO.selectUsuario(2);
        usuario.setTipo('e');
        usuarioDAO.updateUsuario(usuario);
        
        
        Entregador entregador = new Entregador();
        EntregadorDAO entregadorDAO = new EntregadorDAO();
        entregador.setUsuario(usuario);
        
        entregadorDAO.insertEntregador(entregador);
        
        
        // INSERÇÃO DE ESTADO    
          
            Estado estado = new Estado();
            EstadoDAO estadoDAO = new EstadoDAO();

            estado.setNome("Parana");
            estadoDAO.insertEstado(estado);
        
        
        // INSERÇÃO DE CIDADE    
          
            Cidade cidade = new Cidade();
            CidadeDAO cidadeDAO = new CidadeDAO();
            Estado estado = new Estado();
            EstadoDAO estadoDAO = new EstadoDAO();

            estado = estadoDAO.selectEstado(1);
            cidade.setNome("Curitiba");
            cidade.setEstado(estado);
            cidadeDAO.insertCidade(cidade);
        */
        
        // INSERÇÃO DE USUARIO
        
            Usuario usuario = new Usuario();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            MDFive md5 = new MDFive();
            
            usuario.setEmail("marco@gmail.com");
            usuario.setNome("Marco");
            usuario.setCpf("12345678901");
            usuario.setSenha(md5.encripta("123"));
            usuario.setTipo('e');

            //Cria obj cidade
            CidadeDAO cidadeDAO = new CidadeDAO();
            Cidade cidade = cidadeDAO.selectCidade(1);

            //Cria obj Endereco
            Endereco endereco = new Endereco();
            endereco.setBairro("Santa Felicidade");
            endereco.setCidade(cidade);
            endereco.setComplemento("Casa 1");
            endereco.setNumero(320);
            endereco.setRua("Rua Boa vista da aparecida");
            
            usuario.setEndereco(endereco);
            usuarioDAO.insertUsuario(usuario);
        
    }
    
}
