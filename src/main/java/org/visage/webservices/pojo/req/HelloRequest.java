package org.visage.webservices.pojo.req;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Setter;

@Setter
@XmlRootElement(name = "HelloRequest", namespace = "http://example.com/soap")
public class HelloRequest {

    private String name;

    @XmlElement(name = "name", namespace = "http://example.com/soap")
    public String getName() {
        return name;
    }

}
