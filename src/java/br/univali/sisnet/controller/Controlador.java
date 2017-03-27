package br.univali.sisnet.controller;

import br.univali.sisnet.model.Contato;
import br.univali.sisnet.model.Email;
import br.univali.sisnet.model.Telefone;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class Controlador {

    private final HttpServletRequest requisicao;

    public Controlador(HttpServletRequest requisicao) throws Exception {
        this.requisicao = requisicao;

    }
    
    public void processaRequisicao() throws Exception {
        Connection conexao = criaConexao();

        String acao = getAcao();
        
        //===========================================================
        //cadastro de um contato
        if (acao.equals("cadastraContato")) {
            String nome = requisicao.getParameter("nome");
            String enderecoEmail = requisicao.getParameter("email");
            String numeroDoTelefone = requisicao.getParameter("telefone");

            
            //cadastraContato(conexao, novoContato);
        }
        //===========================================================
        //cadastra um novo telefone para um contato
        if (acao.equals("cadastrarTelefone")) {
            int idDoContato = Integer.parseInt(requisicao.getParameter("idDoContato"));
            String numeroDeTelefone = requisicao.getParameter("telefone");
            Telefone telefone = new Telefone(numeroDeTelefone);
            cadastraTelefone(conexao, idDoContato, telefone);
        }

        //cadastra um novo email
        if (acao.equals("cadastrarEmail")) {
            int idDoContato = Integer.parseInt(requisicao.getParameter("idDoContato"));
            String enderecoDeEmail = requisicao.getParameter("email");
            cadastraEmail(conexao, idDoContato, new Email(enderecoDeEmail));
        }
        //===========================================================
        //deleta email
        if (acao.equals("deletarEmail")) {
            int idDoEmail = Integer.parseInt(requisicao.getParameter("idDoEmail"));
            int idDoContato = Integer.parseInt(requisicao.getParameter("idDoContato"));
            deletaEmail(conexao, idDoContato, idDoEmail);
        }
        //===========================================================
        //deleta telefone
        if (acao.equals("deletarTelefone")) {
            int idDoTelefone = Integer.parseInt(requisicao.getParameter("idDoTelefone"));
            int idDoContato = Integer.parseInt(requisicao.getParameter("idDoContato"));
            deletaTelefone(conexao, idDoContato, idDoTelefone);
        }

        //===========================================================
        //sempre coloca a lista de contatos na requisição depois de qualquer ação
        requisicao.setAttribute("contatos", getListaDeContatos(conexao));

        if (conexao != null) {
            conexao.close();
        }

    }

    public List<Telefone> getTelefonesDoContato(Connection conexao, int idDoContato) throws Exception {
        String sql = "select * from telefones_contatos where id_contato = ?";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setInt(1, idDoContato);

        ResultSet resultSet = statement.executeQuery();
        List<Telefone> telefones = new ArrayList<Telefone>();
        while (resultSet.next()) {//para cada telefone
            String numero = resultSet.getString("numero");
            int idDotelefone = resultSet.getInt("id");
            Telefone telefone = new Telefone(idDotelefone, numero);
            telefones.add(telefone);
        }
        statement.close();
        return telefones;
    }

    public List<Email> getEmailsDoContato(Connection conexao, int idDoContato) throws Exception {
        String sql = "select id, email from emails_contatos where id_contato=?";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setInt(1, idDoContato);

        ResultSet resultSet = statement.executeQuery();
        List<Email> emails = new ArrayList<Email>();
        while (resultSet.next()) {//para cada telefone
            String endereco = resultSet.getString("email");
            int id = resultSet.getInt("id");
            emails.add(new Email(id, endereco));
        }
        resultSet.close();
        return emails;
    }

    public List getListaDeContatos(Connection conexao) throws Exception {
        String sql = "select * from contatos where id_usuario=2";//usando um ID de usuário fixo
        Statement statement = conexao.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {//itera em todos os contatos
            
            int idDoContato = resultSet.getInt("id");
            String nomeDoContato = resultSet.getString("nome");
            

            //carrega todos os telefones do contato atual
            

            //carrega todos os emails do contato atual
            
            
        }

        return null;
    }

    public void cadastraContato(Connection conexao, Contato novoContato) throws Exception {
        //insere o contato no banco de dados
        String sql = "insert into contatos (id_usuario, nome) values (?, ?)";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setInt(1, 2);//usando um ID de usuário fixo (2)
        statement.setString(2, novoContato.getNome());
        statement.execute(); //salva 
        statement.close();

        //pega o id do ultimo contato inserido
        sql = "select max(id) as ultimoId from contatos";
        statement = conexao.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();//faz o select para obter o último id
        resultSet.next();//abre o result set
        novoContato.setId(resultSet.getInt("ultimoId"));
        resultSet.close();
        statement.close();

        //insere os emails do contato
        sql = "insert into emails_contatos (id_contato, email) values (?,?)";
        statement = conexao.prepareStatement(sql);
        List<Email> emails = novoContato.getEmails();
        statement.setInt(1, novoContato.getId());
        for (Email mail : emails) {
            statement.setString(2, mail.getEndereco());
            statement.execute();//salva cada email do contato
        }

        //insere os telefones do contato
        sql = "insert into telefones_contatos (id_contato, numero) values (?,?)";
        statement.close();//fecha o statement antes de reutilizá-lo
        statement = conexao.prepareStatement(sql);
        List<Telefone> telefones = novoContato.getTelefones();
        statement.setInt(1, novoContato.getId());
        for (Telefone fone : telefones) {
            statement.setString(2, fone.getNumero());
            statement.execute();//salva cada telefone do contato
        }
    }

    public void cadastraTelefone(Connection conexao, int idDoContato, Telefone novoTelefone) throws Exception {
        String sql = "insert into telefones_contatos (id_contato, numero) values (?,?)";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setInt(1, idDoContato);
        statement.setString(2, novoTelefone.getNumero());
        statement.execute();
    }

    public void cadastraEmail(Connection conexao, int idDoContato, Email novoEmail) throws Exception {
        String sql = "insert into emails_contatos (id_contato, email) values (?,?)";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setInt(1, idDoContato);
        statement.setString(2, novoEmail.getEndereco());
        statement.execute();
    }

    public void deletaEmail(Connection conexao, int idDoContato, int idDoEmail) throws Exception {
        String sql = "delete from emails_contatos where id_contato=? and id=?";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setInt(1, idDoContato);
        statement.setInt(2, idDoEmail);
        statement.execute();
    }

    public void deletaTelefone(Connection conexao, int idDoContato, int idDoTelefone) throws Exception {
        String sql = "delete from telefones_contatos where id_contato=? and id=?";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setInt(1, idDoContato);
        statement.setInt(2, idDoTelefone);
        statement.execute();
    }

    

    private String getAcao() {
        String acao = requisicao.getParameter("acao");
        if (acao == null || acao.length() == 0) {
            return "listaContatos";
        }
        return acao;
    }

    private Connection criaConexao() throws Exception {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        return DriverManager.getConnection("jdbc:mysql://localhost/agenda_contatos", "root", "");
    }

}
