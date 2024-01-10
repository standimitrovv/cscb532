use  cscb532;

CREATE TABLE logistic_company (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL
    );
    
    

CREATE TABLE employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    role ENUM('courier', 'office_staff') NOT NULL,
    company_id INT,
    FOREIGN KEY (company_id) REFERENCES logistic_company(id)
    );
   
   
   
   CREATE TABLE client (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    company_id INT,
    FOREIGN KEY (company_id) REFERENCES logistic_company(id)
    );
    
    
    

CREATE TABLE office (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    company_id INT,
    address VARCHAR(255) NOT NULL,
    FOREIGN KEY (company_id) REFERENCES logistic_company(id)
 );
 
 
 
 CREATE TABLE shipment (
    id INT PRIMARY KEY AUTO_INCREMENT,
    sender_id INT,
    receiver_id INT,
    delivery_address VARCHAR(255) NOT NULL,
    weight DECIMAL(5, 2) NOT NULL,
    is_delivered BOOLEAN DEFAULT FALSE,
    office_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES client(id),
    FOREIGN KEY (receiver_id) REFERENCES client(id),
    FOREIGN KEY (office_id) REFERENCES office(id)
    );
    
    
    CREATE TABLE user_auth (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('employee', 'client') NOT NULL,
    employee_id INT,
    client_id INT,
    FOREIGN KEY (employee_id) REFERENCES employee(id),
    FOREIGN KEY (client_id) REFERENCES client(id)
);








/*-------------------------------DATA------------------------------------->*/


INSERT INTO logistic_company (name, address) VALUES
('Logistic Co 1', 'Address 1'),
('Logistic Co 2', 'Address 2');


INSERT INTO employee (name, role, company_id) VALUES
('Courier 1', 'courier', 1),
('Office Staff 1', 'office_staff', 1);


INSERT INTO client (name, address, company_id) VALUES
('Client 1', 'Client Address 1', 1),
('Client 2', 'Client Address 2', 2);


INSERT INTO office (name, company_id, address) VALUES
('Office 1', 1, 'Office Address 1'),
('Office 2', 2, 'Office Address 2');


INSERT INTO shipment (sender_id, receiver_id, delivery_address, weight, office_id) VALUES
(1, 2, 'Receiver Addridididess 2', 3.5, 1),
(2, 1, 'Receiver Address 1', 2.8, 2);









INSERT INTO shipment (sender_id, receiver_id, delivery_address, weight, office_id)
VALUES (3, 4, 'Random Receiver Address 1', 3.2, 1),
       (4, 3, 'Random Receiver Address 2', 2.5, 2);
       
       
       
INSERT INTO shipment (sender_id, receiver_id, delivery_address, weight, office_id)
VALUES (4, 3, 'Random Receiver Address 3', 4.0, 1),
       (3, 4, 'Random Receiver Address 4', 1.8, 2);





/*-------------------------------Test Queries------------------------------------->*/

SELECT * FROM employee;

SELECT * FROM client;

SELECT * FROM shipment;


SELECT * FROM shipment WHERE sender_id = 3 OR receiver_id = 3;


SELECT * FROM shipment WHERE is_delivered = FALSE;


SELECT * FROM shipment WHERE sender_id = 3;

SELECT * FROM shipment WHERE receiver_id = 3;

SELECT * FROM shipment
WHERE is_delivered = TRUE
AND created_at BETWEEN '2022-01-01' AND '2024-12-31';




SELECT * FROM shipment
WHERE office_id IN (SELECT id FROM office WHERE company_id = '2');



SELECT * FROM shipment
WHERE sender_id = '3' OR receiver_id = '3';


/*--------------------------------------------------------------------------------------------*/
ALTER TABLE shipment
ADD COLUMN price DECIMAL(8, 2),
ADD COLUMN delivery_type ENUM('office', 'address');




UPDATE shipment
SET price = CASE
            WHEN delivery_type = 'office' THEN weight * 1.5 + 5.00
            WHEN delivery_type = 'address' THEN weight * 1.5 + 7.50
          END,
   delivery_type = CASE
                    WHEN office_id IS NOT NULL THEN 'office'
                    ELSE 'address'
                  END
WHERE id IN (1, 2, 3);



DELIMITER //

CREATE FUNCTION calculate_shipment_price(weight DECIMAL(5, 2), delivery_type ENUM('office', 'address'))
RETURNS DECIMAL(8, 2)
DETERMINISTIC
BEGIN
    DECLARE base_price DECIMAL(8, 2);
    
    -- Set base price based on delivery type
    IF delivery_type = 'office' THEN
        SET base_price = 5.00; -- Adjust this based on your pricing strategy
    ELSE
        SET base_price = 7.50; -- Adjust this based on your pricing strategy
    END IF;

    -- Calculate total price based on weight
    RETURN base_price + (weight * 1.50); -- Adjust the weight multiplier as needed
END //

DELIMITER ;



INSERT INTO shipment (sender_id, receiver_id, delivery_address, weight, office_id, delivery_type, price)
VALUES (4, 4, 'Receiver Addridididess 2', 3.5, 1, 'office', calculate_shipment_price(3.5, 'office'));

UPDATE shipment
SET price = calculate_shipment_price(weight, delivery_type);



SET SQL_SAFE_UPDATES = 0;

UPDATE shipment
SET price = calculate_shipment_price(weight, delivery_type);

SET SQL_SAFE_UPDATES = 1;


SELECT * FROM shipment;

SELECT SUM(price) AS total_cost_of_shipments
FROM shipment;

SELECT id, price, created_at
FROM shipment
WHERE created_at BETWEEN '2000-01-01' AND '2025-01-01';



ALTER TABLE shipment
ADD COLUMN employee_id INT,
ADD CONSTRAINT fk_employee_shipment
    FOREIGN KEY (employee_id)
    REFERENCES employee(id);
    
    
    ALTER TABLE shipment
ADD COLUMN client_id INT,
ADD CONSTRAINT fk_client_shipment
    FOREIGN KEY (client_id)
    REFERENCES client(id);





INSERT INTO logistic_company (name, address) VALUES
('Logistic Co 8', 'Address 8'),
('Logistic Co 9', 'Address 9');


INSERT INTO employee (name, role, company_id) VALUES
('Courier 8', 'courier', 1),
('Office Staff 9', 'office_staff', 2);

INSERT INTO client (name, address, company_id) VALUES
('Client 8', 'Client Address 8', 8),
('Client 9', 'Client Address 9', 9);


INSERT INTO office (name, company_id, address) VALUES
('Office 8', 8, 'Office Address 8'),
('Office 9', 9, 'Office Address 9');


INSERT INTO shipment (sender_id, receiver_id, delivery_address, weight, office_id, client_id, employee_id)
VALUES
(8, 8, 'Receiver Address 2', 3.5, 1, 1, NULL),
(9, 9, 'Receiver Address 1', 2.8, 2, NULL, 1);


INSERT INTO user_auth (username, password, role, employee_id, client_id) VALUES
('courier1', 'courier1pass', 'employee', 1, NULL),
('client1', 'client1pass', 'client', NULL, 1);






-- Assuming :clientId is the ID of the logged-in client
SELECT s.*
FROM shipment s
WHERE 1 = 1;


-- Assuming :employeeId is the ID of the logged-in employee
SELECT s.*
FROM shipment s
JOIN office o ON s.office_id = o.id
JOIN employee e ON o.company_id = e.company_id
WHERE e.id = 1;


INSERT INTO shipment (sender_id, receiver_id, delivery_address, weight, office_id, client_id, employee_id)
VALUES
(1, 2, 'Receiver Address 2', 3.5, 1, 1, NULL),
(2, 1, 'Receiver Address 1', 2.8, 2, NULL, 1);


INSERT INTO client (id, name, address, company_id) VALUES
(1, 'Client 1', 'Client Address 1', 1),
(2, 'Client 2', 'Client Address 2', 2);

-- Then insert shipments
INSERT INTO shipment (sender_id, receiver_id, delivery_address, weight, office_id, client_id, employee_id)
VALUES
(1, 2, 'Receiver Address 2', 3.5, 1, 1, NULL),
(2, 1, 'Receiver Address 1', 2.8, 2, NULL, 1);



SELECT s.*
FROM shipment s
JOIN office o ON s.office_id = o.id
JOIN employee e ON o.company_id = e.company_id
WHERE e.id = 1;

SELECT s.*
FROM shipment s
WHERE client_id = 1;






ALTER TABLE user_auth
ADD CONSTRAINT fk_user_auth_client
    FOREIGN KEY (client_id)
    REFERENCES client(id);




ALTER TABLE shipment
ADD COLUMN is_sent BOOLEAN DEFAULT FALSE;


INSERT INTO shipment (sender_id, receiver_id, delivery_address, weight, office_id, is_sent)
VALUES (1, 2, 'Test Address', 3.0, 1, TRUE);


INSERT INTO shipment (sender_id, receiver_id, delivery_address, weight, office_id)
VALUES (2, 1, 'Another Address', 2.5, 2);


SELECT id, sender_id, receiver_id, delivery_address, weight, office_id
FROM shipment
WHERE is_sent = TRUE;












