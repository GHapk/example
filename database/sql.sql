CREATE TABLE IF NOT EXISTS `cabrio` (
  `hood_type` enum('HARDTOP','CLOTH') NOT NULL,
  `hood_open_time` decimal(10,2) NOT NULL,
  `open_speed` int(11) NOT NULL,
  `car_id` int(11) NOT NULL,
  PRIMARY KEY (`car_id`),
  KEY `fk_car` (`car_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `cabrio`
--

INSERT INTO `cabrio` (`hood_type`, `hood_open_time`, `open_speed`, `car_id`) VALUES
('HARDTOP', '30.00', 30, 3);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `car`
--

CREATE TABLE IF NOT EXISTS `car` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hp` int(11) NOT NULL,
  `fuel` varchar(25) NOT NULL,
  `fabrication_year` int(11) NOT NULL,
  `fabrication_month` int(11) NOT NULL,
  `mileage` int(11) NOT NULL,
  `manufacturer` varchar(255) NOT NULL,
  `type` enum('CABRIO','PICKUP','NORMAL') NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `seller_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_seller` (`seller_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Daten für Tabelle `car`
--

INSERT INTO `car` (`id`, `hp`, `fuel`, `fabrication_year`, `fabrication_month`, `mileage`, `manufacturer`, `type`, `price`, `seller_id`) VALUES
(1, 123, 'diesel', 2012, 10, 10000, 'Audi', 'NORMAL', '21000.00', 1),
(3, 123, 'benzin', 2012, 10, 10000, 'Audi A3', 'CABRIO', '25000.00', 1),
(4, 250, 'LPG', 2010, 1, 100000, 'Landrover', 'PICKUP', '8000.00', 1),
(5, 268, 'benzin', 2011, 2, 50000, 'bmw', 'NORMAL', '12000.00', 1),
(6, 550, 'Super', 2012, 7, 0, 'Porsche', 'NORMAL', '150000.00', 1),
(7, 450, 'diesel', 2011, 10, 2000, 'Dodge', 'PICKUP', '25000.00', 1),
(8, 123, 'lpg', 2012, 7, 50000, 'fiat', 'NORMAL', '1200.00', 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `carorder`
--

CREATE TABLE IF NOT EXISTS `carorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `car_id` int(11) NOT NULL,
  `orderdate` date NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_car` (`car_id`),
  KEY `fk_customer` (`customer_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

--
-- Daten für Tabelle `carorder`
--

INSERT INTO `carorder` (`id`, `car_id`, `orderdate`, `customer_id`) VALUES
(6, 3, '2012-07-01', 5),
(7, 4, '2012-07-01', 5),
(8, 5, '2012-07-01', 5),
(9, 7, '2012-07-02', 5),
(10, 6, '2012-07-02', 5),
(11, 1, '2012-07-02', 5);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `payment`
--

CREATE TABLE IF NOT EXISTS `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `invoice_amount` decimal(10,2) NOT NULL,
  `invoice_date` datetime NOT NULL,
  `invoice_number` varchar(25) NOT NULL,
  `type` enum('CREDITCARD','CASH') NOT NULL,
  `creditcard_number` varchar(45) DEFAULT NULL,
  `delivery_date` datetime DEFAULT NULL,
  `pickupdate` datetime DEFAULT NULL,
  `order_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order` (`order_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Daten für Tabelle `payment`
--

INSERT INTO `payment` (`id`, `invoice_amount`, `invoice_date`, `invoice_number`, `type`, `creditcard_number`, `delivery_date`, `pickupdate`, `order_id`) VALUES
(1, '25000.00', '2012-07-01 00:00:00', '0.42956379101793807', 'CASH', NULL, NULL, '2012-12-12 00:00:00', 6),
(2, '8000.00', '2012-07-01 00:00:00', '0.332052151839615', 'CREDITCARD', '123141423123', '2012-11-12 00:00:00', NULL, 7),
(3, '12000.00', '2012-07-01 00:00:00', '0.03654323386006031', 'CREDITCARD', '123141423123', '2012-12-12 00:00:00', NULL, 8),
(4, '25000.00', '2012-07-02 00:00:00', '0.006913359071469505', 'CASH', NULL, NULL, '2012-07-10 00:00:00', 9),
(5, '150000.00', '2012-07-02 00:00:00', '0.6707006080609211', 'CASH', NULL, NULL, '2012-06-30 00:00:00', 10),
(6, '21000.00', '2012-07-02 00:00:00', '0.8922136255982861', 'CREDITCARD', '123456789', '2012-12-12 00:00:00', NULL, 11);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `pickup`
--

CREATE TABLE IF NOT EXISTS `pickup` (
  `cargo_area` decimal(10,2) NOT NULL,
  `cargo_closeable` enum('Y','N') NOT NULL,
  `car_id` int(11) NOT NULL,
  PRIMARY KEY (`car_id`),
  KEY `fk_car_pickup` (`car_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `pickup`
--

INSERT INTO `pickup` (`cargo_area`, `cargo_closeable`, `car_id`) VALUES
('5.00', 'Y', 4),
('5.00', 'Y', 7);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `familyname` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(255) NOT NULL,
  `tel` varchar(45) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `type` enum('seller','customer') NOT NULL,
  `admin` enum('Y','N') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`id`, `name`, `familyname`, `password`, `email`, `tel`, `company_name`, `type`, `admin`) VALUES
(1, 'admin', 'admin', '123', 'admin@admin.de', '12345', 'admin', 'seller', 'Y'),
(2, 'seller', 'seller', '123', 'seller@seller.de', '12345', 'seller', 'seller', 'N'),
(5, 'customer', 'customer', '123', 'customer@customer.de', '12345', 'Customer', 'customer', 'N');

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `car`
--
ALTER TABLE `car`
  ADD CONSTRAINT `fk_seller` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `carorder`
--
ALTER TABLE `carorder`
  ADD CONSTRAINT `fk_car` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`),
  ADD CONSTRAINT `fk_customer` FOREIGN KEY (`customer_id`) REFERENCES `user` (`id`) ON UPDATE CASCADE;

--
-- Constraints der Tabelle `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `fk_order` FOREIGN KEY (`order_id`) REFERENCES `carorder` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `pickup`
--
ALTER TABLE `pickup`
  ADD CONSTRAINT `fk_car_pickup` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

