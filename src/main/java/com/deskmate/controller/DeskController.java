package com.deskmate.controller;

import java.util.List;

import com.deskmate.model.Desk;
import com.deskmate.services.DeskService;
import com.deskmate.utils.InputUtil;

public class DeskController {
	
    private final DeskService deskService;

    public DeskController(DeskService deskService) {
        this.deskService = deskService;
    }

    public void menu() {
        while (true) {
            System.out.println("\n--- Desks (Admin) ---");
            System.out.println("1. Add desk");
            System.out.println("2. Deactivate desk");
            System.out.println("3. List active desks");
            System.out.println("0. Back");

            int c = InputUtil.readInt("Choose: ");
            switch (c) {
                case 1 -> addDesk();
                case 2 -> deactivateDesk();
                case 3 -> listActive();
                case 0 -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void addDesk() {
        String code = InputUtil.readString("deskCode: ");
        String name = InputUtil.readString("name: ");
        long id = deskService.addDesk(code, name);
        System.out.println("Created deskId=" + id);
    }

    private void deactivateDesk() {
        String code = InputUtil.readString("deskCode to deactivate: ");
        deskService.deactivateDesk(code);
        System.out.println("Deactivated: " + code);
    }

    private void listActive() {
        List<Desk> desks = deskService.listActive();
        desks.forEach(d -> System.out.println(d.getDeskCode() + " | " + d.getName()));
    }
}

