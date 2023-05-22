package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 */
public class Insert {

    /**
     * Connect to the test.db database
     *
     * @return the Connection object
     */
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:/Users/hankypanky/vscode/TraderBot/stockdatabase.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Insert a new row into the stocks table
     *
     */
    public void insert(float highPrice, float lowPrice,String shortName, float delayedPrice, int requestNumber, String symbol) {
        String sql = "INSERT INTO stocks(highPrice,lowPrice, shorName, delayedPrice, reqeustNumber, symbol) VALUES(?,?,?,?,?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setFloat(1, highPrice);
            pstmt.setFloat(2, lowPrice);
            pstmt.setString(3, shortName);
            pstmt.setFloat(4, delayedPrice);
            pstmt.setInt(5, requestNumber);
            pstmt.setString(6, symbol);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Insert app = new Insert();
        // insert three new rows
        app.insert(3.30f, 2.30f, "apple inc", 3.00f, 5, "aapl");
    }

}