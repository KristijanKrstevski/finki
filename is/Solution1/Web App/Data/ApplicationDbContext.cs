using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using Domain.Idenity_Models;
using Domain.Domain_Models;
namespace Web_App.Data
{
    public class ApplicationDbContext : IdentityDbContext <CourseAplicationUser>
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
        {
        }
         public virtual DbSet<Book> Books {get;set;}
        //site db setovi
        

        protected override void OnModelCreating(ModelBuilder builder)
        {
            base.OnModelCreating(builder);
            //tuka vrskite many to many one to one
        }
    }
}
