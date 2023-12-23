package com.onlyex.naxtech.api.utils;

import org.apache.logging.log4j.Logger;
public class NTLog {
    public static Logger logger;

    public NTLog() {}

    public static void init(Logger modLogger) {
        logger = modLogger;
    }
}
