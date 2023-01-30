import java.io.File;
import java.util.*;

public class Main {

    public static List<City> listCity(File data) throws Exception {
        Scanner cityData = new Scanner(data);
        List<City> cityList = new ArrayList<City>();
        while (cityData.hasNextLine()) {
            String city = cityData.nextLine();
            String[] paramCity = city.split(";");
            if (paramCity.length == 6) {
                City modelCity = new City(paramCity[1], paramCity[2], paramCity[3], paramCity[4], paramCity[5]);
                cityList.add(modelCity);
            } else {
                City modelCity = new City(paramCity[1], paramCity[2], paramCity[3], paramCity[4]);
                cityList.add(modelCity);
            }
        }
        return cityList;
    }

    public static class City {
        String name;
        String region;
        String district;
        int population;
        String foundation;

        public City(String name, String region, String district, String population, String foundation) {
            this.name = name;
            this.region = region;
            this.district = district;
            this.population = Integer.parseInt(population);
            this.foundation = foundation;
        }

        public City(String name, String region, String district, String population) {
            this.name = name;
            this.region = region;
            this.district = district;
            this.population = Integer.parseInt(population);

        }

        public String toString() {
            return "City{" +
                    "name='" + name + '\'' +
                    ", region='" + region + '\'' +
                    ", district='" + district + '\'' +
                    ", population=" + population +
                    ", foundation='" + foundation + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        File data = new File("resources/CityData.csv");
        List<City> city = listCity(data);

        //Сортировка по одному параметру.
        TreeSet<City> sortCitySimple = new TreeSet<City>((a, b) -> a.name.compareToIgnoreCase(b.name));
        sortCitySimple.addAll(city);
        System.out.println("Список отсортированный по названию города без учета регистра:");
        for (City ct : sortCitySimple) System.out.println(ct);

        //Сортировка по двум параметрам.
        Comparator<City> districtSort = (a, b) -> a.district.compareTo(b.district);
        Comparator<City> districtAndNameSort = districtSort.thenComparing((a, b) -> a.name.compareTo(b.name));
        TreeSet<City> sortCity = new TreeSet<City>(districtAndNameSort);
        sortCity.addAll(city);
        System.out.println("Список отсортированный по федеральному округу и названию города с учетом регистра:");
        for (City ct : sortCity) System.out.println(ct);
    }
}
