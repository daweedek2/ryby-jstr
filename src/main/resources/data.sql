-- hunter - men
INSERT INTO hunter(id, name)
SELECT 1001, 'Vojta'
    WHERE NOT EXISTS(
    SELECT * FROM hunter WHERE id = 1001);
INSERT INTO hunter(id, name)
SELECT 1002, 'David'
WHERE NOT EXISTS(
    SELECT * FROM hunter WHERE id = 1002);
INSERT INTO hunter(id, name)
SELECT 1003, 'Tomáš K.'
WHERE NOT EXISTS(
    SELECT * FROM hunter WHERE id = 1003);
INSERT INTO hunter(id, name)
SELECT 1004, 'Tomáš P.'
WHERE NOT EXISTS(
    SELECT * FROM hunter WHERE id = 1004);
INSERT INTO hunter(id, name)
SELECT 1005, 'Tomášek'
    WHERE NOT EXISTS(
    SELECT * FROM hunter WHERE id = 1005);
INSERT INTO hunter(id, name)
SELECT 1006, 'Jirka'
    WHERE NOT EXISTS(
    SELECT * FROM hunter WHERE id = 1006);

-- hunter - women
INSERT INTO hunter(id, name)
SELECT 1011, 'Ivana'
WHERE NOT EXISTS(
    SELECT * FROM hunter WHERE id = 1011);
INSERT INTO hunter(id, name)
SELECT 1012, 'Johanka'
WHERE NOT EXISTS(
    SELECT * FROM hunter WHERE id = 1012);
INSERT INTO hunter(id, name)
SELECT 1013, 'Barča'
WHERE NOT EXISTS(
    SELECT * FROM hunter WHERE id = 1013);
INSERT INTO hunter(id, name)
SELECT 1014, 'Štěpánka'
WHERE NOT EXISTS(
    SELECT * FROM hunter WHERE id = 1014);


-- fish types with bonus
INSERT INTO fish_type(id, type, bonus)
SELECT 101, 'kapr', 20
WHERE NOT EXISTS(SELECT * FROM fish_type WHERE id = 101);

INSERT INTO fish_type(id, type, bonus)
SELECT 102, 'jeseter', 35
WHERE NOT EXISTS(SELECT * FROM fish_type WHERE id = 102);

INSERT INTO fish_type(id, type, bonus)
SELECT 103, 'cejn', 10
WHERE NOT EXISTS(SELECT * FROM fish_type WHERE id = 103);

INSERT INTO fish_type(id, type, bonus)
SELECT 104, 'amur', 25
WHERE NOT EXISTS(SELECT * FROM fish_type WHERE id = 104);

INSERT INTO fish_type(id, type, bonus)
SELECT 105, 'štika', 30
WHERE NOT EXISTS(SELECT * FROM fish_type WHERE id = 105);

INSERT INTO fish_type(id, type, bonus)
SELECT 106, 'pstruh', 25
WHERE NOT EXISTS(SELECT * FROM fish_type WHERE id = 106);

INSERT INTO fish_type(id, type, bonus)
SELECT 107, 'candát', 30
WHERE NOT EXISTS(SELECT * FROM fish_type WHERE id = 107);

INSERT INTO fish_type(id, type, bonus)
SELECT 108, 'karas', 15
WHERE NOT EXISTS(SELECT * FROM fish_type WHERE id = 108);

INSERT INTO fish_type(id, type, bonus)
SELECT 109, 'lín', 25
WHERE NOT EXISTS(SELECT * FROM fish_type WHERE id = 109);

INSERT INTO fish_type(id, type, bonus)
SELECT 110, 'okoun', 15
WHERE NOT EXISTS(SELECT * FROM fish_type WHERE id = 110);

INSERT INTO fish_type(id, type, bonus)
SELECT 111, 'plotice', 10
WHERE NOT EXISTS(SELECT * FROM fish_type WHERE id = 111);

INSERT INTO fish_type(id, type, bonus)
SELECT 112, 'siven', 25
WHERE NOT EXISTS(SELECT * FROM fish_type WHERE id = 112);

INSERT INTO fish_type(id, type, bonus)
SELECT 113, 'sumeček', 15
WHERE NOT EXISTS(SELECT * FROM fish_type WHERE id = 113);

INSERT INTO fish_type(id, type, bonus)
SELECT 114, 'úhoř', 35
WHERE NOT EXISTS(SELECT * FROM fish_type WHERE id = 114);

INSERT INTO fish_type(id, type, bonus)
SELECT 115, 'bolen', 25
WHERE NOT EXISTS(SELECT * FROM fish_type WHERE id = 115);

INSERT INTO fish_type(id, type, bonus)
SELECT 116, 'koi-kapr', 30
WHERE NOT EXISTS(SELECT * FROM fish_type WHERE id = 116);

INSERT INTO fish_type(id, type, bonus)
SELECT 117, 'mník', 30
WHERE NOT EXISTS(SELECT * FROM fish_type WHERE id = 117);

INSERT INTO fish_type(id, type, bonus)
SELECT 118, 'okounek', 15
WHERE NOT EXISTS(SELECT * FROM fish_type WHERE id = 118);

INSERT INTO fish_type(id, type, bonus)
SELECT 119, 'tolstolobik', 20
WHERE NOT EXISTS(SELECT * FROM fish_type WHERE id = 119);

INSERT INTO fish_type(id, type, bonus)
SELECT 120, 'sumec', 35
WHERE NOT EXISTS(SELECT * FROM fish_type WHERE id = 120);

INSERT INTO fish_type(id, type, bonus)
SELECT 121, 'vyza', 40
WHERE NOT EXISTS(SELECT * FROM fish_type WHERE id = 121);

-- history
INSERT INTO history(id, year, termin, total_fish_count, total_fish_size, fish_type_names, hunter_names)
SELECT 1001, 2024, '10.8. - 17.8.', 128, 0, 'kapr cejn jeseter amur', 'Vojta Tomáš P. Tomáš K. Štěpán David Jirka'
WHERE NOT EXISTS(
    SELECT * FROM history WHERE id = 1001);
INSERT INTO history(id, year, termin, total_fish_count, total_fish_size, fish_type_names, hunter_names)
SELECT 1002, 2025, '2.8. - 9.8.', 209, 12490, 'kapr cejn jeseter štika', 'Vojta Tomáš P. Tomáš K. Štěpán Štěpánka David Tomášek Jirka'
WHERE NOT EXISTS(
    SELECT * FROM history WHERE id = 1002);
INSERT INTO history(id, year, termin, total_fish_count, total_fish_size, fish_type_names, hunter_names)
SELECT 1003, 2026, '25.7. - 1.8.', 255, 17200, 'kapr cejn jeseter amur štika sumeček lín vyza', 'Tomáš P. Tomáš K. Vojta Štěpán Tomášek Romča Jeník Laďa Štěpánka Barča'
WHERE NOT EXISTS(
    SELECT * FROM history WHERE id = 1003);