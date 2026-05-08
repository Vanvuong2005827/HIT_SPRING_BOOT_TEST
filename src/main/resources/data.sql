INSERT IGNORE INTO room_type (id, name, max_occupancy, base_price, created_at, updated_at)
VALUES
    ('11111111-1111-1111-1111-111111111111', 'Standard Room', 2, 500000.00, UTC_TIMESTAMP(), UTC_TIMESTAMP()),
    ('22222222-2222-2222-2222-222222222222', 'Deluxe Room', 2, 800000.00, UTC_TIMESTAMP(), UTC_TIMESTAMP()),
    ('33333333-3333-3333-3333-333333333333', 'Family Room', 4, 1200000.00, UTC_TIMESTAMP(), UTC_TIMESTAMP());

INSERT IGNORE INTO room (id, room_name, status, room_type_id, created_at, updated_at)
VALUES
    ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa1', '201', 'AVAILABLE', '11111111-1111-1111-1111-111111111111', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
    ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2', '202', 'AVAILABLE', '11111111-1111-1111-1111-111111111111', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
    ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa3', '301', 'AVAILABLE', '22222222-2222-2222-2222-222222222222', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
    ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa4', '401', 'AVAILABLE', '33333333-3333-3333-3333-333333333333', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
    ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa5', '402', 'INACTIVE', '33333333-3333-3333-3333-333333333333', UTC_TIMESTAMP(), UTC_TIMESTAMP());

-- INSERT INTO booking (id, customer_name, customer_cccd, room_id, check_in_datetime, check_out_datetime, number_of_guests, status, total_price, note, created_at, updated_at)
-- VALUES
--     (
--         UUID(),
--         'Nguyen Van A',
--         '012345678912',
--         'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa1',
--         '2024-05-10 14:00:00',
--         '2024-05-12 12:00:00',
--         2,
--         'PENDING',
--         1000000.00,
--         'Khách đặt phòng Standard',
--         UTC_TIMESTAMP(),
--         UTC_TIMESTAMP()
--     ),
--     (
--         UUID(),
--         'Tran Thi B',
--         '098765432109',
--         'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa3',
--         '2024-05-15 14:00:00',
--         '2024-05-16 12:00:00',
--         1,
--         'PENDING',
--         800000.00,
--         'Khách muốn check-in sớm',
--         UTC_TIMESTAMP(),
--         UTC_TIMESTAMP()
--     );