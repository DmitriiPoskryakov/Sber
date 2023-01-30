import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public static void maxPopulation(List<City> city) {
        City[] cityArray = city.toArray(new City[0]);
        int populationMax = 0;
        int index = 0;
        for (int i = 0; i < cityArray.length; i++) {
            if (populationMax < cityArray[i].population) {
                populationMax = cityArray[i].population;
                index = i;
            }
        }
        System.out.println("[" + index + "]" + " = " + populationMax);
    }

    public static void main(String[] args) throws Exception {
        File data = new File("resources/CityData.csv");
        List<City> city = listCity(data);
        maxPopulation(city);
    }
}
