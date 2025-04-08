package org.visage.webservices.pojo.res;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Setter;

@Setter
@XmlRootElement(name = "HelloResponse", namespace = "http://example.com/soap")
public class HelloResponse {

    private String message;

    @XmlElement(name = "message", namespace = "http://example.com/soap")
    public String getMessage() {
        return message;
    }

}
