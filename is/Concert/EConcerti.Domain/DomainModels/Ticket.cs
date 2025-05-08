using EConcerti.Domain.Identity;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EConcert.Domain.DomainModels
{
    public class Ticket:BaseEntity
    {
        public Guid Id { get; set; }
        [Required]
        public string? NumberOfPeople { get; set; }
        public Concert? ConcertTickets { get; set; }

        public EConcertApplicationUser? Owner { get; set; }
    }
}
