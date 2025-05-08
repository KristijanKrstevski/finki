using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EConcert.Domain.DomainModels
{
    public class Concert:BaseEntity
    {
        public Guid Id { get; set; }
        [Required]
        public string? ConcertName { get; set; }
        [Required]
        public string? ConcertPlace { get; set; }
        [Required]
        public int? ConcertPrice { get; set; }
        [Required]
        public string? ConcertDate { get; set; }

        public virtual ICollection<Ticket>? Ticket { get; set; }
    }
}
