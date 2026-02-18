package com.deskmate;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.deskmate.config.AppConfig;
import com.deskmate.constants.Role;
import com.deskmate.exception.DatabaseOperationException;
import com.deskmate.exception.DoubleBookingException;
import com.deskmate.exception.EntityNotFoundException;
import com.deskmate.exception.ValidationException;
import com.deskmate.utils.InputUtil;

public class App {
    private static final Logger log = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        AppConfig cfg = new AppConfig();
        log.info("DeskMate D.B.P.S. started");

        String user = InputUtil.readString("Username: ");
        System.out.println("Role: 1) ADMIN  2) AGENT");
        int r = InputUtil.readInt("Choose: ");
        Role role = (r == 1) ? Role.ADMIN : Role.AGENT;

        while (true) {
            try {
                System.out.println("\n=== DeskMate D.B.P.S. (" + role + ") ===");
                if (role == Role.ADMIN) System.out.println("1. Desks (Admin)");
                System.out.println("2. Bookings");
                System.out.println("3. Reports");
                System.out.println("0. Exit");

                int c = InputUtil.readInt("Choose: ");
                switch (c) {
//                    case 1 -> {
//                        if (role != Role.ADMIN) System.out.println("Access denied.");
//                        else cfg.deskController().menu();
//                    }
//                    case 2 -> cfg.bookingController().menu();
//                    case 3 -> cfg.reportController().menu();
                    case 0 -> {
                        log.info("DeskMate stopped by user={}", user);
                        System.out.println("Bye!");
                        return;
                    }
                    default -> System.out.println("Invalid option.");
                }
            } catch (ValidationException | EntityNotFoundException | DoubleBookingException e) {
                log.warn("User error: {}", e.getMessage());
                System.out.println("ERROR: " + e.getMessage());
            } catch (DatabaseOperationException e) {
                log.error("DB error", e);
                System.out.println("ERROR: Database operation failed. Please retry.");
            } catch (Exception e) {
                log.error("Unexpected error", e);
                System.out.println("ERROR: Unexpected failure.");
            }
        }
    }
}

