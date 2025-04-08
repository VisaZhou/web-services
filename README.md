# 服务端：
wsdl文件：http://localhost:8080/ws/hello.wsdl

# 客户端：
## 1. Maven 依赖
```xml
<project>
    <!-- Spring Web Services Core -->
    <dependency>
        <groupId>org.springframework.ws</groupId>
        <artifactId>spring-ws-core</artifactId>
        <version>4.0.0</version> <!-- 使用合适的版本 -->
    </dependency>

    <!-- JAXB API -->
    <dependency>
        <groupId>jakarta.xml.bind</groupId>
        <artifactId>jakarta.xml.bind-api</artifactId>
        <version>3.0.1</version>
    </dependency>

    <!-- JAXB Runtime -->
    <dependency>
        <groupId>org.glassfish.jaxb</groupId>
        <artifactId>jaxb-runtime</artifactId>
        <version>2.3.5</version>
    </dependency>

    <!-- Jackson for SOAP serialization/deserialization -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.13.3</version>
    </dependency>

    <!-- Apache HttpClient -->
    <dependency>
        <groupId>org.apache.httpcomponents</groupId>
        <artifactId>httpclient</artifactId>
        <version>4.5.13</version>
    </dependency>
</project>
```
## 2. 目录结构
HelloRequest和HelloResponse直接用服务端的复制到客户端。

```markdown
wsclient/
│
├── client/
│   └── SoapClient.java                    # SOAP 客户端代码
│
└── pojo/
    ├── req/
    │   └── HelloRequest.java              # 请求对象类
    │
    ├── res/
    │   └── HelloResponse.java             # 响应对象类
    │
    └── ObjectFactory.java                 # JAXB ObjectFactory 类
```
## 3. SOAP 客户端代码
```java
package cn.vortexinfo.emergency.wsclient.pojo;

import cn.vortexinfo.emergency.wsclient.pojo.req.HelloRequest;
import cn.vortexinfo.emergency.wsclient.pojo.res.HelloResponse;
import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {}

    public HelloRequest createHelloRequest() {
        return new HelloRequest();
    }

    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }
}
```
```java
package cn.vortexinfo.emergency.wsclient.client;

import cn.vortexinfo.emergency.wsclient.pojo.req.HelloRequest;
import cn.vortexinfo.emergency.wsclient.pojo.res.HelloResponse;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class SoapClient {

    private static final String SERVICE_URI = "http://localhost:8080/ws";  // 服务端的URL

    public static void main(String[] args) {
        // 创建Jaxb2Marshaller
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("cn.vortexinfo.emergency.wsclient.pojo"); // 设置JAXB上下文路径

        // 创建WebServiceTemplate
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate(marshaller);

        // 创建请求对象
        HelloRequest request = new HelloRequest();
        request.setName("John Doe");

        // 设置SOAP请求的Action
        String soapAction = ""; // 设置SOAP Action根据服务端配置

        // 调用Web服务
        HelloResponse response = (HelloResponse) webServiceTemplate.marshalSendAndReceive(
                SERVICE_URI, request, new SoapActionCallback(soapAction));

        // 输出响应结果
        if (response != null) {
            System.out.println("Response: " + response.getMessage());
        } else {
            System.out.println("No response received.");
        }
    }
}
```