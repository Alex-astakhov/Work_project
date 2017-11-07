package dataModels;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Asta on 23.01.2017.
 * Класс содержит методы генерации различных данных
 */
public class DataGenerator {
    private static Random random = new Random();

    public static String generateYearValue(){ // возвращает рендомный год
        int value = Year.now().getValue();
        return String.valueOf(random.nextInt(75) + value - 75);
    }

    public static String generateYearValue(int reduction){  // возвращает рендомный год с понижением на указанное кол-во лет
        int value = Year.now().getValue();
        return String.valueOf(random.nextInt(75 - reduction) + value - 75);
    }

    public static String getCurrentDateAndTimeString(){  // возвращает текущую дату и время
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getCurrentDateString(){  // возвращает текущую дату и время
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static int getCurrentYear(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date date = new Date();
        return Integer.parseInt(dateFormat.format(date));
    }

    public static String generateCityValue(){  // возвращает рендомный код города в Казахстане
        return String.valueOf(random.nextInt(183) + 26402);
    }

    public static String generateCityValue(String excludeValue){  // возвращает рендомный код города в Казахстане
        String value;
        do {
            value = generateCityValue();
        } while(value.equals(excludeValue));
            return value;
    }

    public static String generatePhoneNumber(){  // возвращает рендомный телефонный номер
        String countryCode = "+7";
        String code = "(" + String.valueOf(random.nextInt(900)+ 100) + ")";
        String firstPart = " " + String.valueOf(random.nextInt(900)+ 100);
        String secondPart = "-" + String.valueOf(random.nextInt(90)+ 10);
        String thirdPart = "-" + String.valueOf(random.nextInt(90)+ 10);
        return countryCode + code + firstPart + secondPart + thirdPart;
    }

    public static String generatePassword(){   // возвращает рендомный пароль
        return "qwerty" + (random.nextInt(900) + 100);
    }

    public static String randomItemFromArray(String[] array){  // возвращает рендомный элемент строкового массива
        return array[random.nextInt(array.length)];
    }

    public static int randomItemFromArray(int[] array){
        return array[random.nextInt(array.length)];
    }

    public static <T> T randomItemFromList(List<T> list){  // возвращает рендомный элемент листа
        return list.get(random.nextInt(list.size()));
    }

    public static <T> T randomItemFromList(List<T> list, T exclude){  // возвращает рендомный элемент строкового листа
        T result;
        do {
            result = list.get(random.nextInt(list.size()));
        }while (result.equals(exclude));
        return result;
    }

    public static Date getCurrentDate(int correctionSeconds){  // возвращает текущую дату и время с поправкой в секундах
        Date date = new Date();
        long stamp = date.getTime();
        date.setTime(stamp + correctionSeconds*1000);
        return date;
    }

    public static <T> boolean collectionEquals(Collection<T> c1, Collection<T> c2){
        if ((c1 == null && c2 != null) || (c1 != null && c2 == null)) {
            System.out.println("Object is null");
            return false;
        }
        if (c1 == c2){
            return true;
        }
        if (!c1.getClass().isAssignableFrom(c2.getClass())){
            System.out.println("Types mismatch!");
            return false;
        }
        if (c1.size() != c2.size()){
            System.out.println("Collections sizes is not equals: " + c1.size() + " and " + c2.size());
            return false;
        }
        if (!c1.containsAll(c2) && !c2.containsAll(c1)) {
            System.out.println("Collections items is not equals:\n" + c1.toString() + "\n" + c2.toString());
            return false;
        }
        return true;
    }

    public static <T> boolean collectionStrictEquals(Collection<T> c1, Collection<T> c2){
        if ((c1 == null && c2 != null) || (c1 != null && c2 == null)) {
            System.out.println("Object is null");
            return false;
        }
        if (c1 == c2){
            return true;
        }
        if (!c1.getClass().isAssignableFrom(c2.getClass())){
            System.out.println("Types mismatch!");
            return false;
        }
        if (c1.size() != c2.size()){
            System.out.println("Collections sizes is not equals: " + c1.size() + " and " + c2.size());
            return false;
        }
        Iterator<T> iterator1 = c1.iterator();
        Iterator<T> iterator2 = c2.iterator();
        while (iterator1.hasNext()) {
            T e1 = iterator1.next();
            T e2 = iterator2.next();
            if (!e1.equals(e2)){
                System.out.println("Element mismatch: \n" + e1 + "\nand\n" + e2);
                return false;
            }
        }
        return true;
    }

    public static int getDaysCountBetweenTwoDates(Date beginDate, Date endDate){
        long diff = endDate.getTime() - beginDate.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static Date parseDateFromString(String date, String mask){
        DateFormat df = new SimpleDateFormat(mask);
        try {
            return df.parse(date);
        } catch (ParseException e) {
            System.out.println("Can't parse to data string:\n" + date);
        }
        return null;
    }
}
