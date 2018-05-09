package hu.szakadth.interfacetest;

/**
 * Created by bogrea on 2018.03.22..
 */
public class TestImpl implements TestInterface {
    private String city;
    private Integer year;

    public TestImpl(String city, Integer year) {
        this.city = city;
        this.year = year;
    }

    public String getCity() {
        return city;
    }

    public Integer getYear() {
        return year;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TestImpl{");
        sb.append("city='").append(city).append('\'');
        sb.append(", year=").append(year);
        sb.append('}');
        return sb.toString();
    }
}
