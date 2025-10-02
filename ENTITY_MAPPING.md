# Entity Mapping Documentation

## Entity Relationships

### Auth Module
```
User (app_user)
├── @Id: userId (BIGSERIAL)
├── @ManyToMany: roles (user_role join table)
├── @OneToMany: addresses (via user_id FK)
├── @OneToMany: orders (via user_id FK)
├── @OneToOne: cart (via user_id FK)
└── @OneToOne: wishlist (via user_id FK)

Role (role)
├── @Id: roleId (BIGSERIAL)
└── @ManyToMany: users (user_role join table)
```

### Catalog Module
```
Category (category)
├── @Id: categoryId (BIGSERIAL)
├── @ManyToOne: parent (self-referencing)
├── @OneToMany: children (self-referencing)
└── @OneToMany: products (via category_id FK)

Brand (brand)
├── @Id: brandId (BIGSERIAL)
└── @OneToMany: products (via brand_id FK)

Product (product)
├── @Id: productId (BIGSERIAL)
├── @ManyToOne: category (via category_id FK)
├── @ManyToOne: brand (via brand_id FK)
├── @OneToMany: images (via product_id FK)
├── @OneToMany: cartItems (via product_id FK)
├── @OneToMany: orderItems (via product_id FK)
├── @OneToMany: wishlistItems (via product_id FK)
└── @OneToMany: inventory (via product_id FK)

ProductImage (product_image)
├── @Id: imageId (BIGSERIAL)
└── @ManyToOne: product (via product_id FK)
```

### Commerce Module
```
Location (location)
├── @Id: locationId (BIGSERIAL)
├── @ManyToOne: address (via address_id FK)
├── @OneToMany: inventory (via location_id FK)
└── @OneToMany: orderItems (via location_id FK)

Inventory (inventory)
├── @Id: inventoryId (BIGSERIAL)
├── @ManyToOne: product (via product_id FK)
├── @ManyToOne: location (via location_id FK)
└── @UniqueConstraint: (product_id, location_id)

Cart (cart)
├── @Id: cartId (BIGSERIAL)
├── @ManyToOne: user (via user_id FK)
└── @OneToMany: cartItems (via cart_id FK)

CartItem (cart_item)
├── @Id: cartItemId (BIGSERIAL)
├── @ManyToOne: cart (via cart_id FK)
├── @ManyToOne: product (via product_id FK)
└── @UniqueConstraint: (cart_id, product_id)

Wishlist (wishlist)
├── @Id: wishlistId (BIGSERIAL)
├── @ManyToOne: user (via user_id FK)
└── @OneToMany: wishlistItems (via wishlist_id FK)

WishlistItem (wishlist_item)
├── @Id: wishlistItemId (BIGSERIAL)
├── @ManyToOne: wishlist (via wishlist_id FK)
├── @ManyToOne: product (via product_id FK)
└── @UniqueConstraint: (wishlist_id, product_id)

Order (order)
├── @Id: orderId (BIGSERIAL)
├── @ManyToOne: user (via user_id FK)
├── @ManyToOne: status (via status_id FK)
├── @ManyToOne: billingAddress (via billing_address_id FK)
├── @ManyToOne: shippingAddress (via shipping_address_id FK)
└── @OneToMany: orderItems (via order_id FK)

OrderItem (order_item)
├── @Id: orderItemId (BIGSERIAL)
├── @ManyToOne: order (via order_id FK)
├── @ManyToOne: product (via product_id FK)
└── @ManyToOne: location (via location_id FK)

OrderStatus (order_status)
├── @Id: statusId (SMALLSERIAL - manual assignment)
└── @OneToMany: orders (via status_id FK)
```

### Common Module
```
Address (address)
├── @Id: addressId (BIGSERIAL)
├── @ManyToOne: user (via user_id FK)
└── @OneToMany: locations (via address_id FK)

BaseEntity (abstract)
├── createdAt (TIMESTAMPTZ)
├── updatedAt (TIMESTAMPTZ)
├── @PrePersist: onCreate()
└── @PreUpdate: onUpdate()
```

### CMS Module
```
CmsPage (cms_page)
├── @Id: pageId (BIGSERIAL)
├── @ManyToOne: createdBy (via created_by FK)
├── @ManyToOne: updatedBy (via updated_by FK)
└── @OneToMany: sections (via page_id FK)

CmsSection (cms_section)
├── @Id: sectionId (BIGSERIAL)
└── @ManyToOne: page (via page_id FK)
```

## Key Mapping Features

### Fetch Strategies
- **LAZY**: All @ManyToOne and @OneToMany relationships
- **EAGER**: None (performance optimization)

### Cascade Types
- **ALL**: Parent-child owned relationships (Product→ProductImage, Cart→CartItem)
- **NONE**: Reference relationships (Product→Category, User→Role)

### Validation Annotations
- **@Email**: User.email
- **@NotBlank**: User.email
- **@Positive**: CartItem.quantity

### Unique Constraints
- **Single**: email, sku, slug fields
- **Composite**: (cart_id, product_id), (wishlist_id, product_id), (product_id, location_id)

### Audit Features
- **Timestamps**: All entities extend BaseEntity
- **Soft Delete**: Product.deletedAt
- **User Audit**: CmsPage (created_by, updated_by)

## Database Schema Status
- **Tables Created**: 19 entities implemented
- **Relationships**: 25+ foreign key relationships
- **Constraints**: 8 unique constraints, 3 check constraints
- **Indexes**: Automatic on PKs, FKs, and unique fields