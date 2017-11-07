package dataModels.applicant.cvParts;

import dataModels.DataGenerator;
import dataModels.apiModels.v1.errorModels.errorBlocks.SocialNetworksDataParams;
import org.testng.annotations.DataProvider;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Asta on 28.12.2016.
 * Класс содержит данные о социальных сетях, а также методы генерации и сравнения этих данных
 */
public class SocialNetData extends DataGenerator {
    private Map<String, String> socNets = new HashMap<>();

    public SocialNetData(Map<String, String> socNets) {
        this.socNets = socNets;
    }

    public SocialNetData() {
        this.socNets.put("1", "https://vk.com/user" + generateCityValue());
        this.socNets.put("2", "https://ok.ru/user" + generateCityValue());
        this.socNets.put("3", "https://www.facebook.com/user" + generateCityValue());
        this.socNets.put("4", "https://twitter.com/user" + generateCityValue());
        this.socNets.put("5", "https://www.linkedin.com/user" + generateCityValue());
        this.socNets.put("6", "https://plus.google.com/user" + generateCityValue());
    }

    public Map<String, String> getSocNets() {
        return socNets;
    }

    public String getSocNetUrlByKey(String key){
        return this.socNets.get(key);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!SocialNetData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final SocialNetData other = (SocialNetData) obj;
        if ((this.socNets == null) ? (other.socNets != null) : !this.socNets.equals(other.socNets)) {
            System.out.println("Expected social networks:");
            for (Map.Entry<String, String> pair: this.socNets.entrySet()){
                System.out.println("Selected value: " + pair.getKey() + ", URL: " + pair.getValue());
            }
            System.out.println("Actual social networks:");
            for (Map.Entry<String, String> pair: other.socNets.entrySet()){
                System.out.println("Selected value: " + pair.getKey() + ", URL: " + pair.getValue());
            }
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SocialNetData{" +
                "socNets=" + socNets.toString() +
                '}';
    }

    @DataProvider
    public static Object[][] negative(){
        return new Object[][]{
                {"1", ""},
                {"1", "      "},
                {"1", "qwerty"},
                {"1", "http://вк.ком"},
                {"1", "https://ok.ru/user"},
                {"1", "https://www.facebook.com/user"},
                {"1", "https://twitter.com/user"},
                {"1", "https://www.linkedin.com/user"},
                {"1", "https://plus.google.com/user"},
                {"2", "https://vk.com/user"},
                {"2", "https://www.facebook.com/user"},
                {"2", "https://twitter.com/user"},
                {"2", "https://www.linkedin.com/user"},
                {"2", "https://plus.google.com/user"},
                {"3", "https://vk.com/user"},
                {"3", "https://ok.ru/user"},
                {"3", "https://twitter.com/user"},
                {"3", "https://www.linkedin.com/user"},
                {"3", "https://plus.google.com/user"},
                {"4", "https://vk.com/user"},
                {"4", "https://ok.ru/user"},
                {"4", "https://www.facebook.com/user"},
                {"4", "https://www.linkedin.com/user"},
                {"4", "https://plus.google.com/user"},
                {"5", "https://vk.com/user"},
                {"5", "https://ok.ru/user"},
                {"5", "https://www.facebook.com/user"},
                {"5", "https://twitter.com/user"},
                {"5", "https://plus.google.com/user"},
                {"6", "https://vk.com/user"},
                {"6", "https://ok.ru/user"},
                {"6", "https://www.facebook.com/user"},
                {"6", "https://twitter.com/user"},
                {"6", "https://www.linkedin.com/user"}
        };
    }

    @DataProvider
    public static Object[][] negativeForApi(){
        return new Object[][]{
                {new SocialNetData(new HashMap<>(Collections.singletonMap("0", ""))),
                        new SocialNetworksDataParams(false, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("", ""))),
                        new SocialNetworksDataParams(false, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("7", ""))),
                        new SocialNetworksDataParams(false, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("1", ""))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("1", "      "))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("1", "qwerty"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("1", "http://вк.ком"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("1", "https://ok.ru/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("1", "https://www.facebook.com/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("1", "https://twitter.com/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("1", "https://www.linkedin.com/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("1", "https://plus.google.com/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("2", "https://vk.com/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("2", "https://www.facebook.com/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("2", "https://twitter.com/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("2", "https://www.linkedin.com/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("2", "https://plus.google.com/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("3", "https://vk.com/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("3", "https://ok.ru/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("3", "https://twitter.com/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("3", "https://www.linkedin.com/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("3", "https://plus.google.com/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("4", "https://vk.com/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("4", "https://ok.ru/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("4", "https://www.facebook.com/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("4", "https://www.linkedin.com/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("4", "https://plus.google.com/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("5", "https://vk.com/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("5", "https://ok.ru/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("5", "https://www.facebook.com/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("5", "https://twitter.com/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("5", "https://plus.google.com/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("6", "https://vk.com/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("6", "https://ok.ru/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("6", "https://www.facebook.com/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("6", "https://twitter.com/user"))),
                        new SocialNetworksDataParams(true, false)},

                {new SocialNetData(new HashMap<>(Collections.singletonMap("6", "https://www.linkedin.com/user"))),
                        new SocialNetworksDataParams(true, false)}
        };
    }
}
