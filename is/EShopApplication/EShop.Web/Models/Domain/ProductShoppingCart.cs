﻿namespace EShop.Web.Models.Domain
{
    public class ProductShoppingCart
    {
        public Guid ProductId { get; set; }
        public Product Product { get; set; }
        public Guid ShoppingCartId { get; set;}
        public ShoppingCart ShoppingCart { get; set; }
        public int Quantity {  get; set; }
    }
}
 