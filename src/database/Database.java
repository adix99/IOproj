package database;
import java.sql.*;

public class Database {
    public static Statement connectToDatabase(String database_name,String username, String password){
        Connection con=null;
        Statement st=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database_name,username,password);
            st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            System.out.println("Succesfully connected to "+ database_name+" database!");
        }catch(Exception e){
            System.out.println("Couldn't connect to "+database_name+" database!");
            System.out.println(e);}
        return st;
    }
    public static void addUser(Statement st, String username, String password, String email, String appCode){
        try {
            st.executeUpdate("insert into Users values('"+username+"','"+password+"','"+email+"','"+appCode+"');");
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("Couldn't execute the query");
            System.out.println(e);
        }
    }
    public static void addUserData(Statement st, String username, String firstName, String lastName, String PESEL, String phoneNumber, String town, String postCode, String street, String streetNumber){
        try {
            st.executeUpdate("insert into UsersData values('"+username+"','"+firstName+"','"+lastName+"','"+PESEL+"','"+phoneNumber+"','"+town+"','"+postCode+"','"+street+"','"+streetNumber+"');");
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Couldn't execute the query");
            System.out.println(e);
        }
    }

    public static void addOrdinaryAccountNumber(Statement st, String username, String ordinary_account_number){
        try {
            st.executeUpdate("insert into OrdinaryAccounts values('"+username+"','"+ordinary_account_number+"',0);");
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Couldn't execute the query");
            System.out.println(e);
        }
    }
    public static void addSavingsAccountNumber(Statement st, String username, String savings_account_number){
        try {
            st.executeUpdate("insert into SavingsAccounts values('"+username+"','"+savings_account_number+"',0,1);");
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Couldn't execute the query");
            System.out.println(e);
        }
    }

    public static void addCard(Statement st, String username, String card_number, String pin_code){
        try{
            st.executeUpdate("insert into Cards values ('"+username+"','"+card_number+"','"+pin_code+"');");
        }catch(SQLException e) {
            System.out.println("Couldn't execute the query");
            System.out.println(e);
        }
    }

    public static String getEmail(Statement st, String username){
        try {
            ResultSet rs = st.executeQuery("select email from Users where username='"+username+"';");
            rs.next();
            return rs.getString(1);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("Couldn't execute the query");
            System.out.println(e);
        }
        return null;
    }

    public static String getOrdinaryAccountNumber(Statement st, String username){
        try{
            ResultSet rs = st.executeQuery("select `Account number` from OrdinaryAccounts where username='"+username+"';");
            rs.next();
            return rs.getString(1);
        }
        catch(SQLException e){
            System.out.println("Couldn't execute the query");
            System.out.println(e);
        }
        return null;
    }

    public static String getSavingsAccountNumber(Statement st, String username){
        try{
            ResultSet rs = st.executeQuery("select `Account number` from SavingsAccounts where username='"+username+"';");
            rs.next();
            return rs.getString(1);
        }
        catch(SQLException e){
            System.out.println("Couldn't execute the query");
            System.out.println(e);
        }
        return null;
    }

    public static float getOrdinaryAccountBalance(Statement st, String username){
        try{
            ResultSet rs = st.executeQuery("select Balance from OrdinaryAccounts where username='"+username+"';");
            rs.next();
            return rs.getFloat(1);
        }catch(SQLException e) {
            System.out.println("Couldn't execute the query");
            System.out.println(e);
        }
        return -1;
    }

    public static float getSavingsAccountBalance(Statement st, String username){
        try{
            ResultSet rs = st.executeQuery("select Balance from SavingsAccounts where username='"+username+"';");
            rs.next();
            return rs.getFloat(1);
        }catch(SQLException e) {
            System.out.println("Couldn't execute the query");
            System.out.println(e);
        }
        return -1;
    }

    public static float getSavingsAccountRate(Statement st, String username){
        try{
            ResultSet rs = st.executeQuery("select Rate from SavingsAccounts where username='"+username+"';");
            rs.next();
            return rs.getFloat(1);
        }catch(SQLException e) {
            System.out.println("Couldn't execute the query");
            System.out.println(e);
        }
        return -1;
    }

    public static String getUsernameByCard(Statement st, String nr){
        try {
            ResultSet rs = st.executeQuery("select username from Cards where nr='" + nr + "';");
            rs.next();
            return rs.getString(1);
        }catch(SQLException e) {
            System.out.println("Couldn't execute the query");
            System.out.println(e);
        }
        return null;
    }

    public static void setUsername(Statement st,String username, String new_username){
        try {
            st.executeUpdate("update Users set username='"+new_username+"'where username='"+username+"';");
            username=new_username;
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("Couldn't execute the query");
            System.out.println(e);
        }
    }

    public static void setPassword(Statement st, String username, String new_password){
        try {
            st.executeUpdate("update Users set password='"+new_password+"'where password='"+username+"';");
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("Couldn't execute the query");
            System.out.println(e);
        }
    }

    public static void setEmail(Statement st,String username, String new_email) {
        try {
            st.executeQuery("update Users set username='" + new_email + "'where username='" + username + "';");
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("Couldn't execute the query");
            System.out.println(e);
        }
    }

    public static void setOrdinaryAccountBalance(Statement st, String username, float new_balance){
        try{
            st.executeUpdate("update OrdinaryAccounts set Balance='"+new_balance+"' where username='"+username+"';");
        }catch(SQLException e) {
            System.out.println("Couldn't execute the query");
            System.out.println(e);
        }
    }
    public static void setSavingsAccountBalance(Statement st, String username, float new_balance){
        try{
            st.executeUpdate("update SavingsAccounts set Balance='"+new_balance+"' where username='"+username+"';");
        }catch(SQLException e) {
            System.out.println("Couldn't execute the query");
            System.out.println(e);
        }
    }
    public static boolean isUsernameTaken(Statement st, String username){
        try{
            ResultSet rs = st.executeQuery("select count(*) from Users where username='"+username+"';");
            rs.next();
            return(rs.getInt(1) == 1);
        }
        catch(SQLException e){
            System.out.println("Couldn't execute the query");
            System.out.println(e);
        }
        return false;
    }
    public static boolean verifyUser(Statement st, String username, String password){
        try{
            ResultSet rs = st.executeQuery("select count(*) from Users where username='"+username+"'and password='"+password+"';");
            rs.next();
            int res=rs.getInt(1);
            return(res == 1);
        }
        catch(SQLException e){
            System.out.println("Couldn't execute the query");
            System.out.println(e);
        }
        return false;
    }

    public static boolean hasCard(Statement st, String username){
        try {
            ResultSet rs = st.executeQuery("select count(*) from Cards where username='" + username + "';");
            rs.next();
            return rs.getInt(1)==1;
        }catch(SQLException e) {
            System.out.println("Couldn't execute the query");
            System.out.println(e);
        }
        return false;
    }
    public static boolean verifyCard(Statement st, String nr, String pin){
        try {
            ResultSet rs = st.executeQuery("select count(*) from Cards where nr='" + nr + "' and pin='"+pin+"';");
            rs.next();
            return rs.getInt(1)==1;
        }catch(SQLException e) {
            System.out.println("Couldn't execute the query");
            System.out.println(e);
        }
        return false;
    }
    public static void deleteUser(Statement st,String username){
        try{
            st.executeUpdate("delete from Users where username='"+username+"';");
        }catch(SQLException e) {
            System.out.println("Couldn't execute the query");
            System.out.println(e);
        }
    }

    public static void addOutgoingHistoryOrdinary(Statement st, String database, String operationDate, String transferType,
                                                  String senderAccountNumber, String receiverAccountNumber, String phoneNumber,
                                                  double transferAmount, String transferCurrency, double totalTransferCost,
                                                  String transferTitle, String startDate, String endDate, int transferCycle,
                                                  String transferCycleUnits, String receiverFirstName, String receiverLastName,
                                                  String receiverTown, String receiverPostCode, String receiverStreet, String receiverStreetNumber){
        switch(database){
            case "OutgoingHistoryOrdinary":
                try {
                    st.executeUpdate("insert into OutgoingHistoryOrdinary values('"+operationDate+"','"+transferType+"','"+senderAccountNumber+"','"
                            +receiverAccountNumber+"','"+phoneNumber+"','"+transferAmount+"','"+transferCurrency+"','"+totalTransferCost+"','"
                            +transferTitle+"','"+startDate+"','"+endDate+"','"+transferCycle+"','"+transferCycleUnits+"','"+receiverFirstName+"','"
                            +receiverLastName+"','"+receiverTown+"','"+receiverPostCode+"','"+receiverStreet+"','"+receiverStreetNumber+"');");
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "OutgoingHistorySavings":
                try {
                    st.executeUpdate("insert into OutgoingHistorySavings values('"+operationDate+"','"+transferType+"','"+senderAccountNumber+"','"
                            +receiverAccountNumber+"','"+phoneNumber+"','"+transferAmount+"','"+transferCurrency+"','"+totalTransferCost+"','"
                            +transferTitle+"','"+startDate+"','"+endDate+"','"+transferCycle+"','"+transferCycleUnits+"','"+receiverFirstName+"','"
                            +receiverLastName+"','"+receiverTown+"','"+receiverPostCode+"','"+receiverStreet+"','"+receiverStreetNumber+"');");
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "IncomingHistoryOrdinary":
                try {
                    st.executeUpdate("insert into IncomingHistoryOrdinary values('"+operationDate+"','"+transferType+"','"+senderAccountNumber+"','"
                            +receiverAccountNumber+"','"+phoneNumber+"','"+transferAmount+"','"+transferCurrency+"','"+totalTransferCost+"','"
                            +transferTitle+"','"+startDate+"','"+endDate+"','"+transferCycle+"','"+transferCycleUnits+"','"+receiverFirstName+"','"
                            +receiverLastName+"','"+receiverTown+"','"+receiverPostCode+"','"+receiverStreet+"','"+receiverStreetNumber+"');");
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "IncomingHistorySavings":
                try {
                    st.executeUpdate("insert into IncomingHistorySavings values('"+operationDate+"','"+transferType+"','"+senderAccountNumber+"','"
                            +receiverAccountNumber+"','"+phoneNumber+"','"+transferAmount+"','"+transferCurrency+"','"+totalTransferCost+"','"
                            +transferTitle+"','"+startDate+"','"+endDate+"','"+transferCycle+"','"+transferCycleUnits+"','"+receiverFirstName+"','"
                            +receiverLastName+"','"+receiverTown+"','"+receiverPostCode+"','"+receiverStreet+"','"+receiverStreetNumber+"');");
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            default:
                System.out.println("Incorrect database!");
        }
    }
}