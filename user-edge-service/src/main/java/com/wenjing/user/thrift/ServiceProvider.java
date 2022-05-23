package com.wenjing.user.thrift;

import com.wenjing.thrift.user.UserService;
import org.apache.thrift.TConfiguration;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.layered.TFramedTransport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceProvider {

    @Value("${thrift.user.ip}")
    private String serverIp;
    @Value("${thrift.user.port}")
    private int serverPort;

    public UserService.Client getUserService() {
        TConfiguration config = new TConfiguration();
        TTransport transport = null;
        try {
            TSocket socket = new TSocket(config, serverIp, serverPort);
            transport = new TFramedTransport(socket);
            transport.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        TProtocol protocol = new TBinaryProtocol(transport);
        UserService.Client clinet = new UserService.Client(protocol);
        return clinet;
    }
}
