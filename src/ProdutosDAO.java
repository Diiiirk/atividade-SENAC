/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    Statement stmt;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
       
    public void cadastrarProduto (ProdutosDTO produto){
        
        
        conn  = new conectaDAO().connectDB();
        String query = "INSERT INTO produtos (nome,valor,status) VALUES " // COMANDO DE INSERT
                + "('" + produto.getNome() +"','"+ produto.getValor()+"','"+ produto.getStatus()+"')";
      
        try {
            prep = conn.prepareStatement(query);
            prep.executeUpdate(); // EXECUÇÃO DO COMANDO
            conn.close();
            JOptionPane.showMessageDialog(null, "Adicionado com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir: " + ex.getMessage());
        }
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
        
       try {
                      
            try {
                conn  = new conectaDAO().connectDB();
                String Query = "SELECT * from produtos WHERE status like 'Vendido'" ;
                stmt = conn.createStatement();
                resultset = stmt.executeQuery(Query);
               
                
            } catch (SQLException ex) {
                Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
                        
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                int id = resultset.getInt("id");
                String nome = resultset.getString("nome");
                int valor = resultset.getInt("valor");
                String status = resultset.getString("status");
                
                produto.setId(id);
                produto.setNome(nome);
                produto.setValor(valor);
                produto.setStatus(status);
                
                listagem.add(produto);
                
            }
                    
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return listagem;
            
    }
    
    public ArrayList<ProdutosDTO> listarProdutosAVenda(){
        
        try {
                      
            try {
                conn  = new conectaDAO().connectDB();
                String Query = "SELECT * from produtos WHERE status like 'A venda'" ;
                stmt = conn.createStatement();
                resultset = stmt.executeQuery(Query);
               
                
            } catch (SQLException ex) {
                Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
                        
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                int id = resultset.getInt("id");
                String nome = resultset.getString("nome");
                int valor = resultset.getInt("valor");
                String status = resultset.getString("status");
                
                produto.setId(id);
                produto.setNome(nome);
                produto.setValor(valor);
                produto.setStatus(status);
                
                listagem.add(produto);
                
            }
                    
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return listagem;
            
    }
    
    public void venderProduto(Integer a){
        
        
        try {
            conn  = new conectaDAO().connectDB();
            String query = "UPDATE produtos SET status = 'Vendido' WHERE id = " + a;
            prep = conn.prepareStatement(query);
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto a venda");
            
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar: " + ex.getMessage());
        }
    } 
    
    
        
}

