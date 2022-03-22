package Database;
// code inspired from sql lite tutorial website

import java.sql.*;
import java.util.*;

import User.*;
import Account.*;
import Transaction.*;
import LoanStockCurrency.*;
import GUI.*;

public class Database {
    private Connection conn = null;

    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            String url = "jdbc:mysql://localhost:3306/mysql";
            conn = DriverManager.getConnection(url, "root", "98980713");

            System.out.println("Connection has been established.");
            //dropTables();
            createTable();
            addToMarket("stock1", 10f);
            addToMarket("stock2", 20f);
            addToMarket("fb", 100f);
            addToProducts("Car", 1.0f);
            addToProducts("Housing", 0.5f);
            addToProducts("Education", 0.2f);
            addManager();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(){
        try{
            Statement s = conn.createStatement();
            //create account table
            String sql = "CREATE TABLE IF NOT EXISTS ACCOUNTS (\n"
                    + "	ACC_NUM INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,\n"
                    + "	AMOUNT REAL NOT NULL,\n"
                    + " TYPE TEXT NOT NULL,\n"
                    + "	USERNAME TEXT NOT NULL,\n"
                    + "	USER_ID INTEGER NOT NULL,\n"
                    + "	CURRENCY TEXT NOT NULL);";
            s.execute(sql);

            //create customer table
            sql = "CREATE TABLE IF NOT EXISTS CUSTOMERS (\n"
                    + "	ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,\n"
                    + "	NAME TEXT NOT NULL,\n"
                    + "	PASSWORD TEXT NOT NULL,\n"
                    + "	USERNAME VARCHAR(20) NOT NULL UNIQUE);";
            s.execute(sql);

            //create manager table
            sql = "CREATE TABLE IF NOT EXISTS MANAGERS (\n"
                    + "	ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,\n"
                    + "	NAME TEXT NOT NULL,\n"
                    + " PROFIT REAL NOT NULL,\n"
                    + "	PASSWORD TEXT NOT NULL,\n"
                    + "	USERNAME TEXT NOT NULL);";
            s.execute(sql);

            //create stocks table
            sql = "CREATE TABLE IF NOT EXISTS STOCKS (\n"
                    + "	ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,\n"
                    + "	NAME TEXT NOT NULL,\n"
                    + "	COST REAL NOT NULL,\n"
                    + "	AMOUNT INTEGER NOT NULL,\n"
                    + " SOLD INTEGER NOT NULL,\n"
                    + "	SECURITY_NUM TEXT NOT NULL);";
            s.execute(sql);

            //create market table
            sql = "CREATE TABLE IF NOT EXISTS Market (\n"
                    + "	ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,\n"
                    + "	NAME TEXT NOT NULL,\n"
                    + "	PRICE REAL NOT NULL);";
            s.execute(sql);

            //create products table
            sql = "CREATE TABLE IF NOT EXISTS PRODUCTS (\n"
                    + "	PRODUCT_ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,\n"
                    + " PRODUCT_NAME TEXT NOT NULL,\n"
                    + "	INTEREST REAL NOT NULL);";
            s.execute(sql);

            //create loans table
            sql = "CREATE TABLE IF NOT EXISTS LOANS (\n"
                    + "	LOAN_ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,\n"
                    + "	LOAN_NAME TEXT NOT NULL,\n"
                    + "	INTEREST REAL NOT NULL,\n"
                    + "	CURRENCY TEXT NOT NULL,\n"
                    + " USER_ID TEXT NOT NULL,\n"
                    + " USERNAME TEXT NOT NULL,\n"
                    + " ACC_ID TEXT NOT NULL,\n"
                    + "	AMOUNT REAL NOT NULL,\n"
                    + "	FINISHED INTEGER NOT NULL,\n"
                    + "	CONFIRMED INTEGER NOT NULL);";
            s.execute(sql);

            //transactions table
            sql = "CREATE TABLE IF NOT EXISTS TRANSACTIONS(\n"
                    + " TIME TEXT NOT NULL,\n"
                    + " TYPE TEXT NOT NULL,\n"
                    + " USERID TEXT NOT NULL,\n"
                    + " USERNAME TEXT NOT NULL,\n"
                    + " ACC_ID TEXT NOT NULL,\n"
                    + " ACC_TYPE TEXT NOT NULL,\n"
                    + " AMOUNT REAL NOT NULL,\n"
                    + " CURRENCY TEXT,\n"
                    + " TARGET_ID TEXT NOT NULL,\n"
                    + " TARGET_ACC TEXT NOT NULL);";
            s.execute(sql);

            //Currency table
            sql = "CREATE TABLE IF NOT EXISTS CURRENCY (\n"
                    + "	ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,\n"
                    + "	NAME TEXT NOT NULL,\n"
                    + "	RATE REAL NOT NULL,\n"
                    + "	SYMBOL TEXT NOT NULL);";
            s.execute(sql);
            System.out.println("All tables created.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //drop tables (for testing)
    public void dropTables() {
        Statement s = null;
        try {
            s = conn.createStatement();
            String sql = "DROP TABLE CUSTOMERS";
            s.execute(sql);
            sql = "DROP TABLE MANAGERS";
            s.execute(sql);
            sql = "DROP TABLE ACCOUNTS";
            s.execute(sql);
            sql = "DROP TABLE TRANSACTIONS";
            s.execute(sql);
            sql = "DROP TABLE LOANS";
            s.execute(sql);
            sql = "DROP TABLE STOCKS";
            s.execute(sql);
            sql = "DROP TABLE MARKET";
            s.execute(sql);
            sql = "DROP TABLE PRODUCTS";
            s.execute(sql);
            sql = "DROP TABLE CURRENCY";
            s.execute(sql);
            System.out.println("Drop successful");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //check for authenticated user
    public int isValidUser(String username, String password) {
        if (username.equals("Admin") && password.equals("123")) {
            return 2;
        }
        String sql = "SELECT ID, USERNAME FROM CUSTOMERS WHERE USERNAME = ? AND PASSWORD = ?";
        User authUser = null;
        int id = -1;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            id = rs.getInt(1);
            if (id != -1) {
                return 1;
            }
            else {
                return 0;
            }
        } catch (Exception e) {
            System.out.println("isvaliduser");
            System.out.println(e.getMessage());
        }
        return -1;
    }

    // add, get, delete an account in Accounts
    public Account addAccount(Float amount, String type, String userName, String userId, String currency) {
        String sql = "INSERT INTO ACCOUNTS(ACC_NUM, AMOUNT, TYPE, USERNAME, USER_ID, CURRENCY) VALUES(default,?,?,?,?,?)";
        Account account = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setFloat(1, amount);
            ps.setString(2, type);
            ps.setString(3, userName);
            ps.setString(4, userId);
            ps.setString(5, currency);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        account = getAccount(userId, type);
        return account;
    }

    public Account getAccount(String id, String type) {
        Account acc = null;
        int userid = Integer.parseInt(id);
        try {
            String sql = "SELECT Acc_Num, amount, type, username, user_Id, currency FROM ACCOUNTS WHERE user_Id = ? AND type = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userid);
            ps.setString(2, type);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                switch (type) {
                    case "Checking" -> acc = new Checking(
                            rs.getString(1),
                            rs.getFloat(2),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6));
                    case "Saving" -> acc = new Saving(
                            rs.getString(1),
                            rs.getFloat(2),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6));
                    case "Security" -> acc = new Security(
                            rs.getString(1),
                            rs.getFloat(2),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6));
                }
            }
        } catch (Exception e) {
            System.out.println("getaccount");
            System.out.println(e.getMessage());
            return null;
        }
        return acc;
    }

    public void deleteAccount(String accountId) {
        String sql = "DELETE FROM ACCOUNTS WHERE ACC_NUM = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(accountId));
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Update balance to account
    public boolean updateBalance(String accountId, Float amount) {
        String sql = "UPDATE ACCOUNTS SET AMOUNT = ? WHERE ACC_NUM = ?";
        int acc_num = Integer.parseInt(accountId);
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setFloat(1, amount);
            ps.setInt(2, acc_num);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("update balance");
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean addBalance(String accountId, Float amount) {
        String sql = "UPDATE ACCOUNTS SET AMOUNT = AMOUNT + ? WHERE ACC_NUM = ?";
        int acc_num = Integer.parseInt(accountId);
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setFloat(1, amount);
            ps.setInt(2, acc_num);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("add balance");
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


    //add User
    public int addUser(String name, String password, String userName) {
        if (getUser(userName, 1) != null) {
            System.out.println("Username already exists.");
            return 0;
        }
        String sql = "INSERT INTO CUSTOMERS(Id, Name, Password, Username) VALUES (default,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, userName);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    //get a User
    public User getUser(String username, int a) {
        String sql = "SELECT ID, NAME, PASSWORD, USERNAME FROM CUSTOMERS WHERE USERNAME = ?";
        User newUser = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String password = rs.getString(3);
                String userName = rs.getString(4);

                if (a == 1) {
                    Customer newCus = new Customer(id, name, userName, password);
                    if (getAccount(id, "Checking") != null) {
                        newCus.setChecking((Checking) getAccount(id, "Checking"));
                    }
                    if (getAccount(id, "Saving") != null) {
                        newCus.setSaving((Saving) getAccount(id, "Saving"));
                    }
                    if (getAccount(id, "Security") != null) {
                        newCus.setSecurity((Security) getAccount(id, "Security"));
                        newCus.getSecurityAcc().setStocks(getAllStocks(newCus.getSecurityAcc().getAccNum()));
                    }
                    if (customerGetConfirmedLoans(id) != null) {
                        newCus.setLoans(customerGetConfirmedLoans(id));
                    }
                    ps.close();
                    rs.close();
                    return newCus;
                }
                else {
                    newUser = new Manager(id, name, userName, password, 0.0f);
                    ps.close();
                    rs.close();
                    return newUser;
                }
            }
        } catch (SQLException e) {
            System.out.println("getuser");
            e.printStackTrace();
            return null;
        }

        return newUser;
    }

    public ArrayList<String> getAllCustomers() {
        String sql = "SELECT ID, NAME, USERNAME FROM CUSTOMERS";
        ArrayList<String> customers = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String userName = rs.getString(3);
                String customer = "ID: "+ id + " Name: " + name + " Username: " + userName;
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("get all customers");
            e.printStackTrace();
            return null;
        }
        return customers;
    }

    //get User Accounts
    public List<Account> getUserAccounts(int id) {
        List<Account> accounts = new ArrayList<>();
        try {
            String sql = "SELECT ID FROM ACCOUNTS WHERE USER_ID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Account a = getAccount(rs.getString(5), rs.getString(3));
                accounts.add(a);
            }
        } catch (Exception e) {
            System.out.println("getuseracc");
            System.out.println(e.getMessage());
            return null;
        }
        return accounts;
    }

    public Manager getManager() {
        String sql = "SELECT ID, NAME, PROFIT, PASSWORD, USERNAME FROM MANAGERS WHERE ID = 1";
        Manager manager = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                Float profit = rs.getFloat(3);
                String password = rs.getString(4);
                String userName = rs.getString(5);

                manager = new Manager(id, name, userName, password, profit);
                ps.close();
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println("get manager");
            e.printStackTrace();
            return null;
        }
        return manager;
    }

    public void updateManager(Float profit) {
        try{
            String sql = "UPDATE MANAGERS SET PROFIT = PROFIT + ? WHERE ID = 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setFloat(1, profit);
            ps.executeUpdate();
            ps.close();
        }
        catch (Exception e) {
            System.out.println("update profit");
            System.out.println(e.getMessage());
        }
    }

    //add Bank manager as a user
    public void addManager() {
        if (existsManager()) {
            //System.out.println("Already have a manager.");
            return;
        }
        String sql = "INSERT INTO MANAGERS(ID,NAME,PROFIT,PASSWORD,USERNAME) VALUES (1,\"Admin\",0.0,\"123\",\"Admin\");";
        Statement s = null;
        try {
            s = conn.createStatement();
            s.execute(sql);
            s.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Boolean existsManager() {
        String sql = "SELECT COUNT(*) FROM MANAGERS;";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            if (rs.getInt(1) == 0){
                return false;
            }
        }
        catch (Exception e) {
            System.out.println("exists manager");
            e.printStackTrace();
        }
        return true;
    }

    //add a loan to Loans
    public Loan addLoan(String loan_name, Float interest, String currency, String user_Id, String username,
                        String acc_id, Float amount, int finished, int confirmed) {
        String sql = "INSERT INTO LOANS(loan_id, loan_name, interest, currency, user_id, username, acc_id, amount, finished, confirmed) " +
                "VALUES (default,?,?,?,?,?,?,?,?,?)";
        Loan loan = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, loan_name);
            ps.setFloat(2, interest);
            ps.setString(3, currency);
            ps.setString(4, user_Id);
            ps.setString(5, username);
            ps.setString(6, acc_id);
            ps.setFloat(7, amount);
            ps.setInt(8, finished);
            ps.setInt(9, confirmed);
            ps.execute();
            loan = getLoan(loan_name, acc_id);
        } catch (SQLException e) {
            System.out.println("add loan");
            System.out.println(e.getMessage());
        }
        return loan;
    }

    //get loan by user_id
    public ArrayList<Loan> customerGetConfirmedLoans(String user_id) {
        ArrayList<Loan> res = new ArrayList<>();
        try {
            String sql = "SELECT LOAN_ID, LOAN_NAME, INTEREST, CURRENCY, AMOUNT, ACC_ID FROM LOANS WHERE USER_ID = ? AND FINISHED = 0 AND CONFIRMED = 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(user_id));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Loan loan = new Loan(rs.getString(1), rs.getString(2),
                        rs.getFloat(3), rs.getString(4), rs.getFloat(5),
                        0,1, rs.getString(6));
                res.add(loan);
            }
        } catch (Exception e) {
            System.out.println("cus get confirmed loans");
            System.out.println(e.getMessage());
            return null;
        }
        return res;
    }

    public ArrayList<Loan> managerGetUnconfirmedLoans() {
        ArrayList<Loan> res = new ArrayList<>();
        try {
            String sql = "SELECT LOAN_ID, LOAN_NAME, INTEREST, CURRENCY, AMOUNT, ACC_ID FROM LOANS WHERE CONFIRMED = 0";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Loan loan = new Loan(rs.getString(1), rs.getString(2),
                        rs.getFloat(3), rs.getString(4), rs.getFloat(5),
                        0,0, rs.getString(6));
                res.add(loan);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return res;
    }

    public ArrayList<Loan> managerGetConfirmedLoans() {
        ArrayList<Loan> res = new ArrayList<>();
        try {
            String sql = "SELECT LOAN_ID, LOAN_NAME, INTEREST, CURRENCY, AMOUNT, ACC_ID FROM LOANS WHERE CONFIRMED = 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Loan loan = new Loan(rs.getString(1), rs.getString(2),
                        rs.getFloat(3), rs.getString(4), rs.getFloat(5),
                        0,0, rs.getString(6));
                res.add(loan);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return res;
    }

    public Loan getLoan(String loan_name, String acc_id) {
        Loan loan = null;
        try{
            String sql = "SELECT LOAN_ID, LOAN_NAME, INTEREST, CURRENCY, AMOUNT, ACC_ID FROM LOANS " +
                    "WHERE LOAN_NAME = ? AND ACC_ID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, loan_name);
            ps.setString(2, acc_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String loan_id = rs.getString(1);
                loan = new Loan(loan_id, rs.getString(2), rs.getFloat(3), rs.getString(4),
                        rs.getFloat(5), 0, 0, rs.getString(6));
            }
        }
        catch (Exception e) {
            System.out.println("get loan");
            System.out.println(e.getMessage());
        }
        return loan;
    }

    public void updateLoan(String loan_id, Float amount, int finished, int confirmed) {
        try{
            String sql = "UPDATE LOANS SET AMOUNT = ?, FINISHED = ?, CONFIRMED = ? WHERE LOAN_ID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setFloat(1, amount);
            ps.setInt(2, finished);
            ps.setInt(3, confirmed);
            ps.setString(4, loan_id);
            ps.executeUpdate();
            ps.close();
        }
        catch (Exception e) {
            System.out.println("update loan");
            System.out.println(e.getMessage());
        }
    }

    public void updateMarket(String stock_name, Float price) {
        try{
            String sql = "UPDATE MARKET SET PRICE = ? WHERE NAME = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setFloat(1, price);
            ps.setString(2, stock_name);
            ps.executeUpdate();
            ps.close();
        }
        catch (Exception e) {
            System.out.println("update market");
            System.out.println(e.getMessage());
        }
    }


    //add stock into stocks table
    public Stock addStock(String name, Float cost, int amount, int sold, String security_num) {
        String sql = "INSERT INTO STOCKS(id, name, cost, amount, sold, security_num) VALUES(default,?,?,?,?,?)";
        Stock s = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setFloat(2, cost);
            ps.setInt(3, amount);
            ps.setInt(4, sold);
            ps.setString(5, security_num);
            ps.execute();
            s = getStock(name, security_num);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return s;
    }

    public ArrayList<Stock> getAllStocks(String security_num) {
        ArrayList<Stock> res = new ArrayList<>();
        try{
            String sql = "SELECT ID, NAME, COST, AMOUNT FROM STOCKS WHERE SECURITY_NUM = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, security_num);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String stock_id = rs.getString(1);
                Stock st = new Stock(stock_id, rs.getString(2), getMarketPrice(stock_id),
                        rs.getFloat(3), rs.getInt(4), 0);
                res.add(st);
            }
        }
        catch (Exception e) {
            System.out.println("get stocks");
            System.out.println(e.getMessage());
        }
        return res;
    }

    public Stock getStock(String name, String security_num) {
        Stock stock = null;
        try{
            String sql = "SELECT ID, NAME, COST, AMOUNT FROM STOCKS WHERE NAME = ? AND SECURITY_NUM = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, security_num);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String stock_id = rs.getString(1);
                stock = new Stock(stock_id, rs.getString(2), getMarketPrice(stock_id),
                        rs.getFloat(3), rs.getInt(4), 0);
            }
        }
        catch (Exception e) {
            System.out.println("get stock");
            System.out.println(e.getMessage());
        }
        return stock;
    }


    // get all loan products from Products
    public ArrayList<LoanProduct> getProductsInfo() {
        ArrayList<LoanProduct> res = new ArrayList<>();
        try {
            String sql = "SELECT PRODUCT_NAME, INTEREST FROM PRODUCTS";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LoanProduct loanProduct = new LoanProduct(rs.getString(1), rs.getFloat(2));
                res.add(loanProduct);
            }
        } catch (Exception e) {
            System.out.println("get products info");
            System.out.println(e.getMessage());
            return null;
        }
        return res;
    }

    // get all stock products from Market
    public ArrayList<StockProduct> getMarketInfo() {
        ArrayList<StockProduct> res = new ArrayList<>();
        try {
            String sql = "SELECT NAME, PRICE FROM MARKET";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StockProduct sp = new StockProduct(rs.getString(1), rs.getFloat(2));
                res.add(sp);
            }
        } catch (Exception e) {
            System.out.println("get market info");
            System.out.println(e.getMessage());
            return null;
        }
        return res;
    }

    public void updateUserStock(String stock_id, Float cost, int amount, int sold) {
        try{
            String sql = "UPDATE STOCKS SET COST = ?, AMOUNT = ?, SOLD = ? WHERE ID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setFloat(1, cost);
            ps.setInt(2, amount);
            ps.setInt(3, sold);
            ps.setString(4, stock_id);
            ps.executeUpdate();
            ps.close();
        }
        catch (Exception e) {
            System.out.println("update stock");
            System.out.println(e.getMessage());
        }

    }

    public Float getMarketPrice(String stock_id) {
        try{
            String sql = "SELECT PRICE FROM MARKET WHERE ID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, stock_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getFloat(1);
            }
        }
        catch (Exception e) {
            System.out.println("getmarketprice");
            System.out.println(e.getMessage());
        }
        return 0f;
    }

    public StockProduct getStockProduct(String name) {
        StockProduct sp = null;
        try{
            String sql = "SELECT NAME, PRICE FROM MARKET WHERE NAME = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                sp = new StockProduct(rs.getString(1), rs.getFloat(2));
            }
        }
        catch (Exception e) {
            System.out.println("get stock product");
            System.out.println(e.getMessage());
        }
        return sp;
    }

    public LoanProduct getLoanProduct(String loan_name) {
        LoanProduct lp = null;
        try{
            String sql = "SELECT PRODUCT_NAME, INTEREST FROM PRODUCTS WHERE PRODUCT_NAME = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, loan_name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lp = new LoanProduct(rs.getString(1), rs.getFloat(2));
            }
        }
        catch (Exception e) {
            System.out.println("get stock product");
            System.out.println(e.getMessage());
        }
        return lp;
    }

    public int addToMarket(String name, Float price) {
        if (getStockProduct(name) != null) {
            //System.out.println("Product name already exists.");
            return 0;
        }
        String sql = "INSERT INTO MARKET(ID, NAME, PRICE) VALUES(default,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setFloat(2, price);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public int addToProducts(String name, Float interest) {
        if (getLoanProduct(name) != null) {
            //System.out.println("Loan already exists.");
            return 0;
        }
        String sql = "INSERT INTO PRODUCTS(PRODUCT_ID, PRODUCT_NAME, INTEREST) VALUES(default,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setFloat(2, interest);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    //add transaction
    public void addTransaction(String time, String type, String userid, String userName, String accNum, String accType,
                                      Float amount, String currency, String targetUserId, String targetAccNum) {
        String sql = "INSERT INTO TRANSACTIONS(TIME,TYPE,USERID,USERNAME,ACC_ID,ACC_TYPE,AMOUNT,CURRENCY,TARGET_ID,TARGET_ACC) VALUES (?,?,?,?,?,?,?,?,?,?)";
        Transaction t = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, time);
            ps.setString(2, type);
            ps.setString(3, userid);
            ps.setString(4, userName);
            ps.setString(5, accNum);
            ps.setString(6, accType);
            ps.setFloat(7, amount);
            ps.setString(8, currency);
            ps.setString(9, targetUserId);
            ps.setString(10, targetAccNum);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Transaction> getAllTransaction() {
        ArrayList<Transaction> res = new ArrayList<>();
        String sql = "SELECT TIME,TYPE,USERID,USERNAME,ACC_ID,ACC_TYPE,AMOUNT,CURRENCY,TARGET_ID,TARGET_ACC " +
                "FROM TRANSACTIONS";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Transaction trans = new Transaction(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getFloat(7),
                        rs.getString(8), rs.getString(9), rs.getString(10));
                res.add(trans);
            }
        } catch (SQLException e) {
            System.out.println("get transaction");
            e.printStackTrace();
        }

        return res;
    }

    public ArrayList<Transaction> getTransaction(String user_id) {
        ArrayList<Transaction> res = new ArrayList<>();
        String sql = "SELECT TIME,TYPE,USERID,USERNAME,ACC_ID,ACC_TYPE,AMOUNT,CURRENCY,TARGET_ID,TARGET_ACC " +
                "FROM TRANSACTIONS WHERE USERID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Transaction trans = new Transaction(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getFloat(7),
                        rs.getString(8), rs.getString(9), rs.getString(10));
                res.add(trans);
            }

        } catch (SQLException e) {
            System.out.println("get transaction");
            e.printStackTrace();
        }

        return res;
    }

    public ArrayList<Transaction> getTransactionByDate(String date) {
        ArrayList<Transaction> res = new ArrayList<>();
        String sql = "SELECT TIME,TYPE,USERID,USERNAME,ACC_ID,ACC_TYPE,AMOUNT,CURRENCY,TARGET_ID,TARGET_ACC " +
                "FROM TRANSACTIONS WHERE TIME LIKE ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            String date_reg = date.substring(0, 10) + "%";
            ps.setString(1, date_reg);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Transaction trans = new Transaction(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getFloat(7),
                        rs.getString(8), rs.getString(9), rs.getString(10));
                res.add(trans);
            }

        } catch (SQLException e) {
            System.out.println("get transaction by date");
            e.printStackTrace();
        }

        return res;
    }
}
