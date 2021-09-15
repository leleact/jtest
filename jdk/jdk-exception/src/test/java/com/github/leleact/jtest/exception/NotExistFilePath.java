package com.github.leleact.jtest.exception;

/**
 * not exist file path.
 *
 * @author leleact
 * @since 2021-09-15
 */
public class NotExistFilePath {
    public static String PATH = NotExistFilePath.class.getClassLoader().getResource("not_exist_file.json").getPath();
}
