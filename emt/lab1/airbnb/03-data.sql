-- Countries
INSERT INTO country (id, name, continent) VALUES
                                              (1, 'France', 'Europe'),
                                              (2, 'Japan', 'Asia'),
                                              (3, 'Brazil', 'South America');

-- Hosts
INSERT INTO host (id, name, surname, country_id) VALUES
                                                     (1, 'Jean', 'Dupont', 1),
                                                     (2, 'Yuki', 'Tanaka', 2),
                                                     (3, 'Carlos', 'Silva', 3);

-- Accommodations
INSERT INTO accommodation (id, name, category, host_id, num_rooms) VALUES
                                                                       (1, 'Parisian Loft', 'Apartment', 1, 3),
                                                                       (2, 'Tokyo Capsule', 'Capsule Hotel', 2, 20),
                                                                       (3, 'Rio Beach House', 'Villa', 3, 5);
