CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE currency (
    id INT AUTO_INCREMENT PRIMARY KEY,
    symbol ENUM('USD', 'EUR', 'GBP', 'JPY', 'CNY', 'AUD', 'CAD', 'CHF', 'NZD', 'SGD', 'THB', 'IDR', 'MYR', 'PHP', 'VND', 'KRW', 'TWD') NOT NULL DEFAULT 'TWD',
    name VARCHAR(10) NOT NULL,
    cash_buy DECIMAL(10,2) NOT NULL COMMENT '現金買進',
    cash_sell DECIMAL(10,2) NOT NULL COMMENT '現金賣出',
    spot_buy DECIMAL(10,2) NOT NULL COMMENT '即期買入',
    spot_sell DECIMAL(10,2) NOT NULL COMMENT '即期匯率',
    last_update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE fee_plan(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL COMMENT '手續費方案名稱',
    type ENUM('STOCK', 'FUTURE') NOT NULL DEFAULT 'STOCK' COMMENT '類型是期貨還是股票',
    currency ENUM('USD', 'EUR', 'GBP', 'JPY', 'CNY', 'AUD', 'CAD', 'CHF', 'NZD', 'SGD', 'THB', 'IDR', 'MYR', 'PHP', 'VND', 'KRW', 'TWD') NOT NULL DEFAULT 'TWD',
    fee_rate DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '手續費率',
    fixed_fee DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '手續費',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    symbol VARCHAR(20) NOT NULL UNIQUE,
    currency ENUM('USD', 'EUR', 'GBP', 'JPY', 'CNY', 'AUD', 'CAD', 'CHF', 'NZD', 'SGD', 'THB', 'IDR', 'MYR', 'PHP', 'VND', 'KRW', 'TWD') NOT NULL DEFAULT 'TWD',
    step_point DECIMAL(10,2) NOT NULL DEFAULT 1 COMMENT '最小跳動點數',
    step_price DECIMAL(10,2) DEFAULT 10 COMMENT '一跳金額',
    type ENUM('STOCK', 'FUTURE') NOT NULL DEFAULT 'STOCK' COMMENT '類型是期貨還是股票'
);

CREATE TABLE positions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    product_id INT NOT NULL,
    fee_plan_id INT NOT NULL,
    status ENUM('OPEN', 'CLOSE') NOT NULL DEFAULT 'OPEN',
    direction ENUM('LONG', 'SHORT') NOT NULL DEFAULT 'LONG',
    quantity INT NOT NULL DEFAULT 0 COMMENT '總部位數量',
    average_cost DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '平均成本',
    balance_cost DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '平衡成本',
    total_tax DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '總稅金',
    open_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    close_at TIMESTAMP NULL DEFAULT NULL,
    comment VARCHAR(255) NULL DEFAULT NULL,
    INDEX idx_user(user_id),
    INDEX idx_user_product (user_id, product_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (fee_plan_id) REFERENCES fee_plan(id)
);

CREATE TABLE transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    position_id INT NOT NULL,
    trade_type ENUM('BUY', 'SELL') NOT NULL DEFAULT 'BUY',
    price DECIMAL(10,2) NOT NULL,
    quantity INT NOT NULL,
    comment VARCHAR(255) NULL DEFAULT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (position_id) REFERENCES positions(id)
);

