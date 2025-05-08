using EShop.Web.Models.Identity;

namespace EShop.Web.Models.Domain
{
    public class ShoppingCart
    {
        public Guid Id { get; set; }
        public string OwnerId  { get; set; }
        public virtual EShopApplicationUser Owner { get; set; }
        public virtual ICollection<ProductShoppingCart> ProductShoppingCarts { get; set; }
        
    }
}
