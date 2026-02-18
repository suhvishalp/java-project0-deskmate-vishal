CREATE DATABASE IF NOT EXISTS deskmate_dbps;
USE deskmate_dbps;

-- Desks (resource / capacity proxy)
CREATE TABLE desks (
  desk_id    BIGINT PRIMARY KEY AUTO_INCREMENT,
  desk_code  VARCHAR(30) NOT NULL UNIQUE,
  name       VARCHAR(120) NOT NULL,
  active     BOOLEAN NOT NULL DEFAULT TRUE,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Bookings (core workflow)
CREATE TABLE bookings (
  booking_id     BIGINT PRIMARY KEY AUTO_INCREMENT,
  desk_id        BIGINT NOT NULL,
  customer_phone VARCHAR(20) NOT NULL,
  slot_start     DATETIME NOT NULL,
  slot_end       DATETIME NOT NULL,
  total_amount   DECIMAL(12,2) NOT NULL,
  status         VARCHAR(20) NOT NULL, -- CREATED, PAID, CANCELLED
  created_at     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

  CONSTRAINT fk_booking_desk FOREIGN KEY (desk_id) REFERENCES desks(desk_id) ON DELETE RESTRICT,

  -- prevent double booking of the same desk for the same slot start (v1)
  CONSTRAINT uq_desk_slot UNIQUE (desk_id, slot_start),

  CONSTRAINT chk_slot_order CHECK (slot_end > slot_start),
  CONSTRAINT chk_total_amount CHECK (total_amount >= 0)
);

-- Payments (one per booking in v1)
CREATE TABLE payments (
  payment_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  booking_id BIGINT NOT NULL UNIQUE,
  mode       VARCHAR(20) NOT NULL, -- CASH, CARD, UPI
  amount     DECIMAL(12,2) NOT NULL,
  status     VARCHAR(20) NOT NULL, -- SUCCESS, FAILED
  paid_at    DATETIME NULL,

  CONSTRAINT fk_pay_booking FOREIGN KEY (booking_id) REFERENCES bookings(booking_id) ON DELETE CASCADE,
  CONSTRAINT chk_pay_amount CHECK (amount >= 0)
);

-- Seed desks
INSERT INTO desks (desk_code, name, active) VALUES
('D-101', 'Window Desk', TRUE),
('D-102', 'Corner Desk', TRUE),
('D-201', 'Meeting Pod', TRUE);

