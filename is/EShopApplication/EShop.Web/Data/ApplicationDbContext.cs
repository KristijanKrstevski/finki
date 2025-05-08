using EShop.Web.Models.Domain;
using EShop.Web.Models.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;

namespace EShop.Web.Data
{
    public class ApplicationDbContext : IdentityDbContext<EShopApplicationUser>
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
        {

        }

        public virtual DbSet<Product> Products { get; set; }
        public virtual DbSet<ShoppingCart> ShoppingCarts { get; set; }
        public virtual DbSet<ProductShoppingCart> ProductShoppingCarts {  get; set; }

        protected override void OnModelCreating(ModelBuilder builder)
        {
            base.OnModelCreating(builder);
            //builder.Entity<Product>().Property(z=>z.ProductName).IsRequired();
            builder.Entity<Product>().Property(p=>p.Id).ValueGeneratedOnAdd();
            builder.Entity<ShoppingCart>().Property(s => s.Id).ValueGeneratedOnAdd();

            builder.Entity<ProductShoppingCart>().HasKey(ps => new { ps.ProductId, ps.ShoppingCartId });

            builder.Entity<ProductShoppingCart>()
                .HasOne(p => p.Product)
                .WithMany(s => s.ProductShoppingCarts)
                .HasForeignKey(p => p.ShoppingCartId);

            builder.Entity<ProductShoppingCart>()
                .HasOne(p => p.ShoppingCart)
                .WithMany(s => s.ProductShoppingCarts)
                .HasForeignKey(p => p.ProductId);

            builder.Entity<ShoppingCart>()
                .HasOne<EShopApplicationUser>(e => e.Owner)
                .WithOne(e => e.UserShoppingCart)
                .HasForeignKey<ShoppingCart>(e => e.OwnerId);
        }
    }
}
