package at.martinahrer.tdd.sample.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {
    private String line1;
    private String line2;
    private String city;
    private String zip;
    private String country; // add

    @SuppressWarnings("java:S125")
    public String printAddress() {
        //return String.format("%s %s %s %s %s", getCountry().trim(), getZip().trim(), getCity().trim(), getLine1().trim(), getLine2().trim());
        return String.format("%s %s %s %s", getZip().trim(), getCity().trim(), getLine1().trim(), getLine2().trim());
    }
}
