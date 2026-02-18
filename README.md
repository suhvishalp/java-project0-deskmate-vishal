# DeskMate – Slot Booking & Payment System (D.B.P.S.)

## Setup
1. Run schema.sql in MySQL.
2. Copy `src/main/resources/db.properties.template` to `db.properties` and fill credentials.
3. Run:
   - `mvn test`
   - `mvn -q -DskipTests package`
   - Run App main

## Design
- Booking + Payment happens in a single transaction.
- If payment is SUCCESS → booking becomes PAID.
- If payment is FAILED → booking remains CREATED.
- Double booking prevented by DB UNIQUE(desk_id, slot_start).
