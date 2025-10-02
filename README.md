# Asalkar Healthy Hub - Backend

A comprehensive e-commerce platform for health products built with Spring Boot and PostgreSQL.

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/asalkar/healthyhub/
│   │       ├── AsalkarHealthyHubApplication.java
│   │       ├── config/
│   │       │   └── DatabaseConfig.java
│   │       ├── entity/
│   │       │   ├── auth/
│   │       │   │   ├── Role.java
│   │       │   │   └── User.java
│   │       │   ├── catalog/
│   │       │   │   ├── Brand.java
│   │       │   │   ├── Category.java
│   │       │   │   ├── Product.java
│   │       │   │   └── ProductImage.java
│   │       │   ├── commerce/
│   │       │   │   ├── Cart.java
│   │       │   │   ├── CartItem.java
│   │       │   │   ├── Location.java
│   │       │   │   ├── Order.java
│   │       │   │   ├── OrderItem.java
│   │       │   │   └── OrderStatus.java
│   │       │   ├── cms/
│   │       │   │   ├── CmsPage.java
│   │       │   │   └── CmsSection.java
│   │       │   └── common/
│   │       │       ├── Address.java
│   │       │       └── BaseEntity.java
│   │       ├── repository/
│   │       │   ├── ProductRepository.java
│   │       │   └── UserRepository.java
│   │       ├── service/
│   │       └── controller/
│   └── resources/
│       └── application.yml
└── test/
```

## Features

- **Authentication & Authorization**: User management with role-based access
- **Product Catalog**: Categories, brands, products with images
- **E-commerce**: Shopping cart, orders, payments, shipments
- **Inventory Management**: Multi-location inventory tracking
- **CMS**: Content management for pages and sections
- **Audit Trail**: Complete audit logging for admin actions

## Database Schema

The application uses PostgreSQL with the following main modules:
- Auth & Users (role, app_user, user_role)
- Address Book (address)
- Catalog (category, brand, product, product_image)
- Locations & Inventory (location, inventory)
- Pricing (product_price, product_price_history)
- Cart & Wishlist (cart, cart_item, wishlist, wishlist_item)
- Orders (order, order_item, order_status)
- Payments & Shipments (payment, shipment)
- Reviews & Coupons (review, coupon)
- CMS (cms_page, cms_section, media_asset)

## Getting Started

1. **Prerequisites**
   - Java 17+
   - Maven 3.6+
   - PostgreSQL 12+

2. **Database Setup**
   ```sql
   CREATE DATABASE asalkar_healthy_hub;
   ```

3. **Environment Variables**
   ```
   DB_USERNAME=your_db_username
   DB_PASSWORD=your_db_password
   SPRING_PROFILES_ACTIVE=dev
   ```

4. **Run Application**
   ```bash
   mvn spring-boot:run
   ```

## Configuration

- **Database**: PostgreSQL connection configured in `application.yml`
- **JPA**: Hibernate with validation mode (requires existing schema)
- **Security**: Spring Security enabled
- **Logging**: Debug level for application packages

## Next Steps

1. Create remaining entity classes for all tables
2. Implement service layer with business logic
3. Add REST controllers for API endpoints
4. Configure Spring Security for authentication
5. Add validation and error handling
6. Implement unit and integration tests