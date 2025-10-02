# API Endpoints Documentation

## Auth & Account

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - User login
- `POST /api/auth/logout` - User logout

### Profile Management
- `GET /api/me` - Get current user profile
- `PUT /api/me` - Update profile (name, phone)

### Address Management
- `GET /api/me/addresses` - Get user addresses
- `POST /api/me/addresses` - Create new address
- `PUT /api/me/addresses/{addressId}` - Update address
- `DELETE /api/me/addresses/{addressId}` - Delete address

## Catalog (Public)

### Categories & Brands
- `GET /api/categories` - Get categories tree/list
- `GET /api/brands` - Get all brands

### Products
- `GET /api/products` - Search products (filters: q, category, brand, isActive, page, size)
- `GET /api/products/{id}` - Get product by ID
- `GET /api/products/sku/{sku}` - Get product by SKU
- `GET /api/products/{id}/images` - Get product images

### Locations & Inventory
- `GET /api/locations` - Get all locations (Vita, Pune, etc.)
- `GET /api/inventory` - Get inventory (params: productId, sku, locationCode)

## Wishlist (Customer)

- `GET /api/wishlist` - Get user wishlist
- `POST /api/wishlist/items` - Add to wishlist (body: { productId })
- `DELETE /api/wishlist/items/{itemId}` - Remove from wishlist

## Cart & Checkout (Customer)

### Cart Management
- `GET /api/cart` - Get user cart
- `POST /api/cart/items` - Add to cart (body: { productId, quantity })
- `PUT /api/cart/items/{itemId}` - Update cart item (body: { quantity })
- `DELETE /api/cart/items/{itemId}` - Remove from cart

### Orders
- `POST /api/orders` - Create order from cart (body: { shippingAddressId, billingAddressId? })
- `GET /api/orders` - Get user orders
- `GET /api/orders/{orderId}` - Get order by ID
- `GET /api/orders/by-number/{orderNumber}` - Get order by number

## Admin - Users/Roles

- `GET /api/admin/users` - Search users (filters: q, role, page, size)
- `GET /api/admin/users/{userId}` - Get user by ID
- `PUT /api/admin/users/{userId}` - Update user (activate/deactivate, assign roles)

## Admin - Catalog

### Categories
- `POST /api/admin/categories` - Create category
- `PUT /api/admin/categories/{id}` - Update category
- `DELETE /api/admin/categories/{id}` - Delete category (soft delete if products exist)

### Brands
- `POST /api/admin/brands` - Create brand
- `PUT /api/admin/brands/{id}` - Update brand
- `DELETE /api/admin/brands/{id}` - Delete brand

### Products
- `POST /api/admin/products` - Create product
- `PUT /api/admin/products/{id}` - Update product
- `DELETE /api/admin/products/{id}` - Soft delete product (set deleted_at)
- `POST /api/admin/products/{id}/images` - Add product image
- `DELETE /api/admin/products/{id}/images/{imageId}` - Delete product image

## Admin - Locations & Inventory

### Locations
- `GET /api/admin/locations` - Get all locations
- `POST /api/admin/locations` - Create location
- `PUT /api/admin/locations/{locationId}` - Update location

### Inventory
- `GET /api/admin/inventory` - Get inventory (filters: productId/sku, locationId/code)
- `POST /api/admin/inventory/adjust` - Adjust inventory (body: { productId|sku, locationId|code, delta, note })

## Admin - Orders

- `GET /api/admin/orders` - Search orders (filters: status, q, dateFrom, dateTo, page, size)
- `GET /api/admin/orders/{orderId}` - Get order by ID
- `PUT /api/admin/orders/{orderId}/status` - Update order status (body: { statusCode })
- `GET /api/admin/orders/{orderId}/items` - Get order items

## Implementation Status

### ✅ Created Controllers:
- `AuthController` - Auth & profile endpoints
- `CatalogController` - Public catalog endpoints
- `WishlistController` - Wishlist management
- `CartController` - Cart & order endpoints
- `AdminController` - All admin endpoints

### ✅ Created Services:
- `AuthService` - Authentication logic
- `UserService` - User & profile management
- `CatalogService` - Catalog operations
- `CartService` - Cart management
- `WishlistService` - Wishlist operations
- `OrderService` - Order processing
- `InventoryService` - Inventory & location management

### ✅ Created DTOs:
- `LoginRequest` - Login credentials
- `RegisterRequest` - Registration data
- `CartItemRequest` - Add to cart request
- `CreateOrderRequest` - Order creation request

### ✅ Created Repositories:
- `UserRepository` - User data access
- `ProductRepository` - Product data access
- `CategoryRepository` - Category data access
- `BrandRepository` - Brand data access
- `CartRepository` - Cart data access
- `OrderRepository` - Order data access

### 🔄 Next Steps:
1. Implement service methods with business logic
2. Add Spring Security configuration
3. Add JWT token handling
4. Add validation and error handling
5. Add pagination and sorting
6. Add unit and integration tests

All endpoints are now structured and ready for implementation!