package com.deskmate.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public final class DbConnectionFactory {
	
	private static final Properties props = new Properties();

	static {
		try (InputStream in = DbConnectionFactory.class.getClassLoader().getResourceAsStream("db.properties")) {
			if (in == null)
				throw new IllegalStateException("db.properties not found in resources/");
			props.load(in);
		} catch (Exception e) {
			throw new ExceptionInInitializerError("Failed to load db.properties: " + e.getMessage());
		}
	}

	private DbConnectionFactory() {
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.username"),
					props.getProperty("db.password"));
		} catch (Exception e) {
			throw new RuntimeException("DB connection failed: " + e.getMessage(), e);
		}
	}
}
