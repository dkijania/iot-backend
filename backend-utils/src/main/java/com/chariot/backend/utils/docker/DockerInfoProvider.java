package com.chariot.backend.utils.docker;

import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

@Service
public class DockerInfoProvider {

    public static final String DOCKER_HOST = "DOCKER_HOST";
    public static final String HTTP_HOST_FORMAT = "http://%s:%s";

    public String getAddress(int port) throws URISyntaxException {
        String dockerHost = getDockerHostSystemEnv();
        return String.format(HTTP_HOST_FORMAT, dockerHost, Integer.toString(port));
    }

    private String getDockerHostSystemEnv() {
        return System.getenv(DOCKER_HOST);
    }

}
