package class08.demo;

public class Address {
    String streetAddress;
    String city;
    String state;
    int postalcode;

    public Address(String streetAddress, String city, String state, int postalcode) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.postalcode = postalcode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalcode=" + postalcode +
                '}';
    }
}
