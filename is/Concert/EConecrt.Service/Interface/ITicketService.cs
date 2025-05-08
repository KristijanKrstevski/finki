using EConcert.Domain.DomainModels;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EConecrt.Service.Interface
{
    public interface ITicketService
    {
        List<Ticket> GetAllTickets();
        Ticket GetTicketDetails(Guid id);
  
        void DeleteTicket(Guid id);
        Ticket CreateTicket(Ticket ticket);
        Ticket UpdateTicket(Ticket ticket);

    }
}
