USE `bankingdemo`;

INSERT INTO `customer` (`user_name`, `password`, `full_name`, `email`, `business_customer`) VALUES
  ('jgarcia','$2a$12$MVK.vPomYewRFXWMiii27el4nVqTss5jgdoxhm4x7fOPnGHoeL1Wu','Julia García','jgarcia@example.com', false), -- password: j1
  ('hlarsen','$2a$12$PsUSyXFyndb2Xvf3KPB8PexbkrTa5K3ML/n7aZMJ8At8avXLOVcQS','Henrik Larsen','hlarsen@exmple.com', false), -- password: h1
  ('rtanaka','$2a$12$NUAeeZTNlxFvbDaKnSKryui39gwfDyK.ctdhjIGkVXvL/mFvgxIdC','田中 りん','rtanaka@example.com', false), -- password: r1
  ('jbutkus','$2a$12$WRK2kaU2Pf/3ht4qRcURi.iEttLxc1dFsgOaxlHgtEoTs5hL5GE3m','Juozas Butkus','jbutkus@example.com', false), -- password: j1
  ('nlee','$2a$12$Rp8hAoBNse3o6Qx0Pb85IuEbXMuzxaKGecxs54Z7kJr.reIRCDKCu','Noémie Lee','nlee@example.com', false), -- password: n1
  ('xy','$2a$12$9hCdnphMy.OjzmaP48uGDOCSf72tGGAU/SwwLXSQsjdo6jufwbCEW','XY SE','xy@example.com', true); -- password: x1

INSERT INTO `authority` (`user_name`, `authority`) VALUES
  ('jgarcia', 'CUSTOMER'),
  ('hlarsen', 'CUSTOMER'),
  ('rtanaka', 'CUSTOMER'),
  ('jbutkus', 'CUSTOMER'),
  ('jbutkus', 'CLERK'),
  ('nlee', 'CUSTOMER'),
  ('nlee', 'ADMIN'),
  ('xy', 'CUSTOMER');

INSERT INTO `account` (`balance`, `customer_id`) VALUES
  (100000, 1),
  (200, 2),
  (20000, 2),
  (3000, 3);

INSERT INTO `phone` (`phonenumber`, `purpose`, `customer_id`) VALUES
  (52112233445501, 'home', 1),
  (52772233445502, 'mobile', 1),
  (47772233445501, 'mobile', 2),
  (81112233445501, 'home', 3),
  (37011223344501, 'home', 4),
  (11122334455601, 'home', 5),
  (352112233440, 'central', 6),
  (352112233442045, 'accounting', 6),
  (352112233442070, 'hr', 6);
