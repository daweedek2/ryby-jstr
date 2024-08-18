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


-- fish types
INSERT INTO fish_type(id, type)
SELECT 101, 'kapr'
    WHERE NOT EXISTS(
    SELECT * FROM fish_type WHERE id = 101);
INSERT INTO fish_type(id, type)
SELECT 102, 'jeseter'
    WHERE NOT EXISTS(
    SELECT * FROM fish_type WHERE id = 102);
INSERT INTO fish_type(id, type)
SELECT 103, 'cejn'
    WHERE NOT EXISTS(
    SELECT * FROM fish_type WHERE id = 103);
INSERT INTO fish_type(id, type)
SELECT 104, 'amur'
    WHERE NOT EXISTS(
    SELECT * FROM fish_type WHERE id = 104);
INSERT INTO fish_type(id, type)
SELECT 105, 'štika'
    WHERE NOT EXISTS(
    SELECT * FROM fish_type WHERE id = 105);
INSERT INTO fish_type(id, type)
SELECT 106, 'pstruh'
    WHERE NOT EXISTS(
    SELECT * FROM fish_type WHERE id = 106);
INSERT INTO fish_type(id, type)
SELECT 107, 'candát'
    WHERE NOT EXISTS(
    SELECT * FROM fish_type WHERE id = 107);
INSERT INTO fish_type(id, type)
SELECT 108, 'karas'
    WHERE NOT EXISTS(
    SELECT * FROM fish_type WHERE id = 108);
INSERT INTO fish_type(id, type)
SELECT 109, 'lín'
    WHERE NOT EXISTS(
    SELECT * FROM fish_type WHERE id = 109);
INSERT INTO fish_type(id, type)
SELECT 110, 'okoun'
    WHERE NOT EXISTS(
    SELECT * FROM fish_type WHERE id = 110);
INSERT INTO fish_type(id, type)
SELECT 111, 'plotice'
    WHERE NOT EXISTS(
    SELECT * FROM fish_type WHERE id = 111);
INSERT INTO fish_type(id, type)
SELECT 112, 'siven'
    WHERE NOT EXISTS(
    SELECT * FROM fish_type WHERE id = 112);
INSERT INTO fish_type(id, type)
SELECT 113, 'sumeček'
    WHERE NOT EXISTS(
    SELECT * FROM fish_type WHERE id = 113);
INSERT INTO fish_type(id, type)
SELECT 114, 'úhoř'
    WHERE NOT EXISTS(
    SELECT * FROM fish_type WHERE id = 114);
INSERT INTO fish_type(id, type)
SELECT 115, 'bolen'
    WHERE NOT EXISTS(
    SELECT * FROM fish_type WHERE id = 115);
INSERT INTO fish_type(id, type)
SELECT 116, 'koi-kapr'
    WHERE NOT EXISTS(
    SELECT * FROM fish_type WHERE id = 116);
INSERT INTO fish_type(id, type)
SELECT 117, 'mník'
    WHERE NOT EXISTS(
    SELECT * FROM fish_type WHERE id = 117);
INSERT INTO fish_type(id, type)
SELECT 118, 'okounek'
    WHERE NOT EXISTS(
    SELECT * FROM fish_type WHERE id = 118);
INSERT INTO fish_type(id, type)
SELECT 119, 'tolstolobik'
    WHERE NOT EXISTS(
    SELECT * FROM fish_type WHERE id = 119);
INSERT INTO fish_type(id, type)
SELECT 120, 'sumec'
    WHERE NOT EXISTS(
    SELECT * FROM fish_type WHERE id = 120);
INSERT INTO fish_type(id, type)
SELECT 121, 'vyza'
    WHERE NOT EXISTS(
    SELECT * FROM fish_type WHERE id = 121);
