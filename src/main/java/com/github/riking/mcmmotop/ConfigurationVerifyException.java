package com.github.riking.mcmmotop;

import java.util.List;

public class ConfigurationVerifyException extends Exception {
    private static final long serialVersionUID = -5406718924995621791L;
    public List<String> problems;

    public ConfigurationVerifyException(List<String> grievances) {
        super("Configuration failed validation");
        problems = grievances;
    }
}
