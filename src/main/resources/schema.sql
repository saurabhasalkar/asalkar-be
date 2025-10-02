-- Asalkar Healthy Hub – PostgreSQL Schema (Commerce + CMS)
-- Generated: 2025-10-02T04:06:10Z

-- =======================
-- Auth & Users
-- =======================
CREATE TABLE role (
  role_id BIGSERIAL PRIMARY KEY,
  name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE app_user (
  user_id BIGSERIAL PRIMARY KEY,
  email VARCHAR(320) UNIQUE NOT NULL,
  phone VARCHAR(20),
  password_hash TEXT NOT NULL,
  first_name VARCHAR(100),
  last_name VARCHAR(100),
  is_active BOOLEAN DEFAULT TRUE,
  created_at TIMESTAMPTZ DEFAULT now(),
  updated_at TIMESTAMPTZ DEFAULT now()
);

CREATE TABLE user_role (
  user_id BIGINT REFERENCES app_user(user_id) ON DELETE CASCADE,
  role_id BIGINT REFERENCES role(role_id) ON DELETE CASCADE,
  PRIMARY KEY (user_id, role_id)
);

-- =======================
-- Address Book
-- =======================
CREATE TABLE address (
  address_id BIGSERIAL PRIMARY KEY,
  user_id BIGINT REFERENCES app_user(user_id) ON DELETE SET NULL,
  name VARCHAR(80),
  line1 VARCHAR(200) NOT NULL,
  line2 VARCHAR(200),
  city VARCHAR(120) NOT NULL,
  state VARCHAR(120) NOT NULL,
  postal_code VARCHAR(20) NOT NULL,
  country VARCHAR(80) DEFAULT 'India',
  phone VARCHAR(20),
  is_default BOOLEAN DEFAULT FALSE,
  created_at TIMESTAMPTZ DEFAULT now(),
  updated_at TIMESTAMPTZ DEFAULT now()
);

-- =======================
-- Catalog
-- =======================
CREATE TABLE category (
  category_id BIGSERIAL PRIMARY KEY,
  parent_id BIGINT REFERENCES category(category_id),
  name VARCHAR(120) NOT NULL,
  slug VARCHAR(140) UNIQUE NOT NULL,
  description TEXT,
  is_active BOOLEAN DEFAULT TRUE,
  created_at TIMESTAMPTZ DEFAULT now(),
  updated_at TIMESTAMPTZ DEFAULT now()
);

CREATE TABLE brand (
  brand_id BIGSERIAL PRIMARY KEY,
  name VARCHAR(120) UNIQUE NOT NULL,
  slug VARCHAR(140) UNIQUE NOT NULL,
  description TEXT,
  created_at TIMESTAMPTZ DEFAULT now(),
  updated_at TIMESTAMPTZ DEFAULT now()
);

CREATE TABLE product (
  product_id BIGSERIAL PRIMARY KEY,
  sku VARCHAR(64) UNIQUE NOT NULL,
  category_id BIGINT REFERENCES category(category_id),
  brand_id BIGINT REFERENCES brand(brand_id),
  name VARCHAR(200) NOT NULL,
  slug VARCHAR(200) UNIQUE NOT NULL,
  description_html TEXT,
  unit_measure VARCHAR(16),
  unit_size NUMERIC(10,2),
  price_mrp NUMERIC(10,2) NOT NULL,
  price_sale NUMERIC(10,2),
  tax_rate_percent NUMERIC(5,2) DEFAULT 0,
  is_active BOOLEAN DEFAULT TRUE,
  created_at TIMESTAMPTZ DEFAULT now(),
  updated_at TIMESTAMPTZ DEFAULT now(),
  deleted_at TIMESTAMPTZ
);

CREATE TABLE product_image (
  image_id BIGSERIAL PRIMARY KEY,
  product_id BIGINT REFERENCES product(product_id) ON DELETE CASCADE,
  url TEXT NOT NULL,
  alt_text TEXT,
  sort_order INT DEFAULT 0,
  created_at TIMESTAMPTZ DEFAULT now()
);

-- =======================
-- Locations & Inventory
-- =======================
CREATE TABLE location (
  location_id BIGSERIAL PRIMARY KEY,
  code VARCHAR(40) UNIQUE NOT NULL,
  name VARCHAR(120) NOT NULL,
  address_id BIGINT REFERENCES address(address_id) ON DELETE SET NULL,
  is_active BOOLEAN DEFAULT TRUE,
  created_at TIMESTAMPTZ DEFAULT now(),
  updated_at TIMESTAMPTZ DEFAULT now()
);

CREATE TABLE inventory (
  inventory_id BIGSERIAL PRIMARY KEY,
  product_id BIGINT REFERENCES product(product_id),
  location_id BIGINT REFERENCES location(location_id),
  qty_on_hand INT DEFAULT 0,
  qty_reserved INT DEFAULT 0,
  reorder_level INT DEFAULT 0,
  updated_at TIMESTAMPTZ DEFAULT now(),
  UNIQUE(product_id, location_id)
);

-- =======================
-- Cart & Wishlist
-- =======================
CREATE TABLE cart (
  cart_id BIGSERIAL PRIMARY KEY,
  user_id BIGINT REFERENCES app_user(user_id) ON DELETE SET NULL,
  status VARCHAR(20) DEFAULT 'ACTIVE',
  created_at TIMESTAMPTZ DEFAULT now(),
  updated_at TIMESTAMPTZ DEFAULT now()
);

CREATE TABLE cart_item (
  cart_item_id BIGSERIAL PRIMARY KEY,
  cart_id BIGINT REFERENCES cart(cart_id) ON DELETE CASCADE,
  product_id BIGINT REFERENCES product(product_id),
  unit_price_at_add NUMERIC(10,2) NOT NULL,
  quantity INT NOT NULL CHECK (quantity > 0),
  UNIQUE(cart_id, product_id)
);

CREATE TABLE wishlist (
  wishlist_id BIGSERIAL PRIMARY KEY,
  user_id BIGINT REFERENCES app_user(user_id) ON DELETE CASCADE,
  created_at TIMESTAMPTZ DEFAULT now()
);

CREATE TABLE wishlist_item (
  wishlist_item_id BIGSERIAL PRIMARY KEY,
  wishlist_id BIGINT REFERENCES wishlist(wishlist_id) ON DELETE CASCADE,
  product_id BIGINT REFERENCES product(product_id),
  UNIQUE(wishlist_id, product_id)
);

-- =======================
-- Orders
-- =======================
CREATE TABLE order_status (
  status_id SMALLSERIAL PRIMARY KEY,
  code VARCHAR(30) UNIQUE NOT NULL,
  sort_order INT DEFAULT 0
);

CREATE TABLE "order" (
  order_id BIGSERIAL PRIMARY KEY,
  user_id BIGINT REFERENCES app_user(user_id),
  order_number VARCHAR(50) UNIQUE NOT NULL,
  status_id SMALLINT REFERENCES order_status(status_id),
  placed_at TIMESTAMPTZ DEFAULT now(),
  billing_address_id BIGINT REFERENCES address(address_id),
  shipping_address_id BIGINT REFERENCES address(address_id),
  subtotal_amount NUMERIC(12,2) NOT NULL,
  discount_amount NUMERIC(12,2) DEFAULT 0,
  tax_amount NUMERIC(12,2) DEFAULT 0,
  shipping_amount NUMERIC(12,2) DEFAULT 0,
  total_amount NUMERIC(12,2) NOT NULL,
  currency_code CHAR(3) DEFAULT 'INR',
  notes TEXT,
  created_at TIMESTAMPTZ DEFAULT now(),
  updated_at TIMESTAMPTZ DEFAULT now()
);

CREATE TABLE order_item (
  order_item_id BIGSERIAL PRIMARY KEY,
  order_id BIGINT REFERENCES "order"(order_id) ON DELETE CASCADE,
  product_id BIGINT REFERENCES product(product_id),
  product_name_snapshot VARCHAR(200) NOT NULL,
  sku_snapshot VARCHAR(64) NOT NULL,
  unit_price_snapshot NUMERIC(10,2) NOT NULL,
  quantity INT NOT NULL CHECK (quantity > 0),
  tax_rate_percent_snapshot NUMERIC(5,2) DEFAULT 0,
  location_id BIGINT REFERENCES location(location_id)
);

-- =======================
-- CMS
-- =======================
CREATE TABLE cms_page (
  page_id BIGSERIAL PRIMARY KEY,
  slug VARCHAR(80) UNIQUE NOT NULL,
  title VARCHAR(160) NOT NULL,
  template VARCHAR(40) NOT NULL,
  is_published BOOLEAN DEFAULT FALSE,
  published_at TIMESTAMPTZ,
  created_by BIGINT REFERENCES app_user(user_id),
  updated_by BIGINT REFERENCES app_user(user_id),
  created_at TIMESTAMPTZ DEFAULT now(),
  updated_at TIMESTAMPTZ DEFAULT now()
);

CREATE TABLE cms_section (
  section_id BIGSERIAL PRIMARY KEY,
  page_id BIGINT REFERENCES cms_page(page_id) ON DELETE CASCADE,
  type VARCHAR(40) NOT NULL,
  heading VARCHAR(200),
  body_html TEXT,
  sort_order INT DEFAULT 0,
  is_active BOOLEAN DEFAULT TRUE
);

-- =======================
-- Seed Data
-- =======================
INSERT INTO role (name) VALUES ('ADMIN') ON CONFLICT DO NOTHING;
INSERT INTO role (name) VALUES ('CUSTOMER') ON CONFLICT DO NOTHING;

INSERT INTO order_status (code, sort_order) VALUES
 ('NEW',1),('CONFIRMED',2),('PACKED',3),('SHIPPED',4),('DELIVERED',5),('CANCELLED',6),('RETURNED',7)
ON CONFLICT DO NOTHING;

INSERT INTO location (code, name) VALUES
 ('VITA','Vita Hometown'),
 ('PUNE','Pune City')
ON CONFLICT DO NOTHING;