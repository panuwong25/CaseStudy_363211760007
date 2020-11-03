public class ExchangeCurrencyApp {

    public static void main(String[] args) {

        ExhangeAPI ex = new ExhangeAPI();

        // test connection with THB
        if (ex.getConnection("THB")) {
            System.out.println(ex.getResult());
        }






    }
}
