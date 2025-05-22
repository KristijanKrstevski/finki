CREATE MATERIALIZED VIEW accommodations_by_host AS
SELECT h.id AS host_id,
       COUNT(a.id) AS num_accommodations
FROM host h
         LEFT JOIN accommodation a ON a.hostid = h.id
GROUP BY h.id;


CREATE MATERIALIZED VIEW hosts_by_country AS
SELECT c.id AS country_id,
       COUNT(h.id) AS num_hosts
FROM country c
         LEFT JOIN host h ON h.countryid = c.id
GROUP BY c.id;





