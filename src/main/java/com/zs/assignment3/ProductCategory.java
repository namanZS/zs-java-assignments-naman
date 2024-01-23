package com.zs.assignment3;

enum ProductCategory {
PersonalCare(false),
Grocery(true),
Electronics(true),
Other(true);

private final boolean isReturnable;

ProductCategory(boolean isReturnable) {
    this.isReturnable = isReturnable;
}

public boolean isReturnable() {
    return isReturnable;
}
}