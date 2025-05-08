using EConcert.Domain.DomainModels;
using EConcerti.Domain.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;

namespace EConcert.Repository
{
    public class ApplicationDbContext : IdentityDbContext<EConcertApplicationUser>
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
        {
        }
        public DbSet<Concert>Concerts  { get; set; }
        public DbSet<Ticket> Ticket { get; set; }
        protected override void OnModelCreating(ModelBuilder builder)
        {
            base.OnModelCreating(builder);

            builder.Entity<Concert>()
                .Property(z => z.Id).ValueGeneratedOnAdd();
            builder.Entity<Ticket>()
                .Property(z => z.Id).ValueGeneratedOnAdd();

            //ovie dole mozhe i ne treba
            //builder.Entity<Concert>()
            //    .HasKey(z => z.Id);

            //builder.Entity<Ticket>()
            //    .HasKey(z => z.Id);

            builder.Entity<Ticket>()
                 .HasOne(t => t.ConcertTickets)
                 .WithMany(c => c.Ticket)
                 .HasForeignKey("ConcertId") 
                 .OnDelete(DeleteBehavior.Cascade); 

            builder.Entity<Ticket>()
                .HasOne(t => t.Owner)
                .WithMany(u => u.UserTickets)
                .HasForeignKey("OwnerId") 
                .OnDelete(DeleteBehavior.Cascade);
        }
    }
}
