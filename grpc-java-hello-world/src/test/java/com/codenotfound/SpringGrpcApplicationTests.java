package com.codenotfound;

import static org.assertj.core.api.Assertions.assertThat;

import com.codenotfound.grpc.helloworld.NumText;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.codenotfound.grpc.HelloWorldClient;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringGrpcApplicationTests {

    @Autowired
    private HelloWorldClient helloWorldClient;

    @Test
    public void testSayHello() {
        final String responseDoe = helloWorldClient.sayHello(null, "Doe", null);
        assertThat(responseDoe).isEqualTo("Hello Doe!");

        final String responseJohn = helloWorldClient.sayHello("John", null, null);
        assertThat(responseJohn).isEqualTo("Hello John!");

        final NumText numText = NumText.newBuilder().setNum(27).setText("Jahre").build();
        String responseNumText = helloWorldClient.sayHello(null, null, numText);
        assertThat(responseNumText).isEqualTo("Hello 27 Jahre!");
    }
}
