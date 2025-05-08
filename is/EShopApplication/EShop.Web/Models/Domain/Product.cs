using System.ComponentModel.DataAnnotations;

namespace EShop.Web.Models.Domain
{
    public class Product
    {
        public Guid Id { get; set; }
        [Required]
        public string ProductName { get; set; }
        [Required]
        public string ProductDescription { get; set; }
        [Required]
        public string ProductImage { get; set; }
        [Required] 
        public int Rating { get; set; }
        [Required]
        public int ProductPrice { get; set; }

        public virtual ICollection<ProductShoppingCart> ProductShoppingCarts { get; set; }

    }
}
