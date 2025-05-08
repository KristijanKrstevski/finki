using EConcert.Domain.DomainModels;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EConecrt.Service.Interface
{
    public interface IConcertService
    {
        List<Concert> GetAllConcerts();
        Concert GetConcertDetails(Guid id);
        Ticket CreateTicket(Ticket ticket);
        Ticket UpdateTicket(Ticket ticket);

        void DeleteConcert(Guid id);
    }
}
