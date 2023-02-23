package stepDefs;

import io.cucumber.java.en.Given;
import org.junit.Assert;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class Question1StepDef {
    private static int count = 0;

    @Given("{string} {string} {string} {string} {string} should match the DB record")
    public void should_match_the_db_record(String customer_id, String fullname, String city, String country, String sum) {
        String query = "SELECT customer.customer_id, CONCAT (first_name, ' ', last_name) AS fullname,city,country,SUM(amount)\n" +
                "FROM customer\n" +
                "JOIN payment ON payment.customer_id = customer.customer_id\n" +
                "JOIN address ON address.address_id = customer.address_id\n" +
                "JOIN city ON city.city_id = address.city_id\n" +
                "JOIN country ON country.country_id = city.country_id\n" +
                "GROUP BY customer.customer_id, first_name,last_name,city,country\n" +
                "HAVING \n" +
                "COUNT(*) = (SELECT COUNT(*) FROM payment\n" +
                "GROUP BY customer_id\n" +
                "ORDER BY COUNT(*) DESC\n" +
                "LIMIT 1)";

        List<Map<String,Object>> resultMap = DBUtils.getQueryResultMap(query);
        Map<String,Object> map = resultMap.get(count++);
        Assert.assertEquals(map.get("customer_id") + "",customer_id);
        Assert.assertEquals(map.get("fullname"),fullname);
        Assert.assertEquals(map.get("city"),city);
        Assert.assertEquals(map.get("country"),country);
        Assert.assertEquals(map.get("sum") + "",sum);
    }

}
