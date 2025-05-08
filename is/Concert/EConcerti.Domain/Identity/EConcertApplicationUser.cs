using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using EConcert.Domain.DomainModels;
using Microsoft.AspNetCore.Identity;

namespace EConcerti.Domain.Identity
{
    public class EConcertApplicationUser:IdentityUser 
    {
        [Required]
        [Display(Name ="FirstName")]
        public string? FirstName { get; set; }
        [Required]
        [Display(Name = "LastName")]
        public string? LastName { get; set; }
        [Required]
        [Display(Name = "Address")]
        public string? Address { get; set; }

        public virtual ICollection<Ticket>? UserTickets { get; set; }
     
    }
}
