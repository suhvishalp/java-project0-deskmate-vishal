package com.deskmate.utils;

import com.deskmate.exception.ValidationException;

public final class ValidationUtil {
	
	private ValidationUtil() {
	}

	public static String normalizePhone(String phone) {
		if (phone == null)
			throw new ValidationException("Phone is required");
		String p = phone.trim().replaceAll("\\s+", "");
		if (!p.matches("\\d{10}"))
			throw new ValidationException("Phone must be 10 digits");
		return p;
	}

	public static void requireNonBlank(String v, String field) {
		if (v == null || v.trim().isEmpty())
			throw new ValidationException(field + " is required");
	}
}
