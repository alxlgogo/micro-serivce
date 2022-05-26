package com.wenjing.course.service;

import com.wenjing.thrift.user.UserService;
import org.apache.thrift.TConfiguration;
import org.apache.thrift.TServiceClient;
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


    private enum ServiceType {
        USER,
        MESSAGE
    }

    public UserService.Client getUserService() {
        return getService(serverIp, serverPort, ServiceType.USER);
    }


    public <T> T getService(String ip, int port, ServiceType serviceType) {
        TConfiguration config = new TConfiguration();
        TTransport transport = null;
        try {
            TSocket socket = new TSocket(config, ip, port);
            transport = new TFramedTransport(socket);
            transport.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        TProtocol protocol = new TBinaryProtocol(transport);

        TServiceClient result = null;
        switch (serviceType) {
            case USER:
                result = new UserService.Client(protocol);
                break;
        }
        return (T) result;
    }
}