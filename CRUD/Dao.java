package CRUD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockDao {
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/test.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insert(Stock stock){
        String sql = "INSERT INTO stocks(name, price, quantity) VALUES(?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, stock.getName());
            pstmt.setFloat(2, stock.getPrice());
            pstmt.setInt(3, stock.getQuantity());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Stock> selectAll(){
        String sql = "SELECT id, name, price, quantity FROM stocks";
        List<Stock> stocks = new ArrayList<>();

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            while (rs.next()) {
                Stock stock = new Stock();
                stock.setId(rs.getInt("id"));
                stock.setName(rs.getString("name"));
                stock.setPrice(rs.getFloat("price"));
                stock.setQuantity(rs.getInt("quantity"));
                stocks.add(stock);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return stocks;
    }

    public void update(Stock stock){
        String sql = "UPDATE stocks SET name = ? , price = ? , quantity = ? WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, stock.getName());
            pstmt.setFloat(2, stock.getPrice());
            pstmt.setInt(3, stock.getQuantity());
            pstmt.setInt(4, stock.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id){
        String sql = "DELETE FROM stocks WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

