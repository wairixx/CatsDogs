package org.example.entities;


public class Configs {
    //    ProductDao


    public static final String SQL_GET_ALL_PRODUCTS = "SELECT * FROM new_test.products;";
    public static final String SQL_CHANGE_PRODUCT_QUANTITY = "UPDATE new_test.products SET quantity = ? WHERE product_id = ?;";
    public static final String QUANTITY = "SELECT quantity FROM new_test.products WHERE product_id = ?;";
    public static final String PRICE = "SELECT price FROM new_test.products WHERE product_id = ?;";
    public static final String SQL_GET_BOUGHT_PRODUCTS = "SELECT  new_test.products.name, new_test.products.price, new_test.users_products.product_quantity" +
            "            FROM new_test.products" +
            "            INNER JOIN new_test.users_products ON new_test.users_products.product_id=" +
            "            new_test.products.product_id WHERE new_test.users_products.user_id = ?;";


    //    ProductDao
//    PurchaseDao


    public static final String SQL_BUY_PRODUCT = "INSERT INTO new_test.users_products " +
            "(user_id, product_id, product_quantity)" + " VALUES (?,?,?)";
    public static final String SQL_MAKE_DATE = "INSERT INTO new_test.date " +
            "(user_id, month, day, time)" + " VALUES (?,?,?,?)";
    public static final String SQL_BUY_SERVICE = "INSERT INTO new_test.users_services " +
            "(user_id, service_id, date_id)" + " VALUES (?,?,?)";


    //    PurchaseDao
//    StockDao


    protected static final String SQL_GET_ALL_STOCKS = "SELECT * FROM new_test.services;";
    protected static final String SQL_CREATE_DATE = "INSERT INTO new_test.date" +
            "( date_id, month, day, time, user_id)" + " VALUES (?,?,?,?)";
    protected static final String SQL_CHECK_TIME = "SELECT * FROM new_test.date WHERE month = ? AND day = ? AND time = ?;";
    protected static final String STOCK_PRICE = "SELECT price FROM new_test.services WHERE service_id = ?;";
    protected static final String DATE_ID = "SELECT * FROM new_test.date WHERE month = ? AND day = ? AND time = ?;";
    protected static final String SQL_GET_NAME_PRICE_BOUGHT_PRODUCTS = "SELECT  new_test.services.name, new_test.services.price" +
            "FROM new_test.services" +
            "INNER JOIN new_test.users_services ON new_test.users_services.service_id=" +
            "new_test.services.service_id WHERE new_test.users_services.user_id = ?;";

    protected static final String SQL_GET_DATE_BOUGHT_PRODUCTS = "SELECT  new_test.date.month, new_test.date.day, new_test.date.time" +
            "FROM new_test.date" +
            "INNER JOIN new_test.users_services ON new_test.date.date_id =\n" +
            "new_test.users_services.date_id WHERE new_test.users_services.user_id = ?;";


    //    StockDao
//    UserDao


    public static final String SQL_LOGIN = "SELECT * FROM new_test.users WHERE login = ? AND password = ?;";
    public static final String ID = "SELECT user_id FROM new_test.users WHERE login = ? AND password = ?;";
    public static final String SQL_SHOW_INFO = "SELECT * FROM new_test.users WHERE user_id = ?;";
    public static final String MONEY = "SELECT money FROM new_test.users WHERE user_id = ?;";
    public static final String SQL_CHANGE_USER_MONEY = "UPDATE new_test.users SET money = ? WHERE user_id = ?";
    public static final String SQL_SIGN_UP_WITH_ADDITIONAL_INFO = "INSERT INTO new_test.additional_info (user_id, city, country) VALUES (?,?,?)";
    public static final String SQL_SIGN_UP = "INSERT INTO new_test.users (login, password, money) VALUES (?,?,?)";
    public static final String SQL_SHOW_ADDITIONAL_INFO = "SELECT  new_test.users.user_id, new_test.users.login, new_test.users.password, new_test.users.money, new_test.additional_info.city, new_test.additional_info.country" +
            "            FROM new_test.users\n" +
            "            INNER JOIN new_test.additional_info ON new_test.users.user_id =new_test.additional_info.user_id WHERE new_test.users.user_id = ?;";
    public static final String SQL_TO_SWITCH_MONEY = "UPDATE new_test.users SET money = ? WHERE (user_id =  ?);";
    public static final String SQL_CHECK_LOGIN = "SELECT * FROM new_test.users WHERE Login = ?;";


//    UserDao
}
