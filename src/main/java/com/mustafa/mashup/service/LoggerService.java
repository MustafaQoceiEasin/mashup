package com.mustafa.mashup.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {

  private static Logger logger;

  public static String writeInfoMsg(String message) {
    logger = LoggerFactory.getLogger("LOGGER");
    logger.info(message);
    return message;
  }

  public static String writeDebugMsg(String message) {
    logger = LoggerFactory.getLogger("LOGGER");
    logger.debug(message);
    return message;
  }

  public static String writeErrorMsg(String message) {
    logger = LoggerFactory.getLogger("LOGGER");
    logger.error(message);
    return message;
  }

  public static String writeWarnMsg(String message) {
    logger = LoggerFactory.getLogger("LOGGER");
    logger.warn(message);
    return message;
  }
}