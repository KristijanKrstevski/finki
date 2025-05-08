using EConcert.Domain.DomainModels;
using EConcert.Repository.Interface;
using EConecrt.Service.Interface;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EConecrt.Service.Implementation
{
    public class TicketService : ITicketService
    {
        private readonly IRepository<Ticket> _ticketRepository;

        public TicketService(IRepository<Ticket> ticketRepository)
        {
            _ticketRepository = ticketRepository;
        }

        public List<Ticket> GetAllTickets()
        {
            return _ticketRepository.GetAll(t => t, include: t => t.Include(x => x.ConcertTickets)).ToList();
        }

        public Ticket GetTicketDetails(Guid id)
        {
            return _ticketRepository.Get(t => t, predicate: t => t.Id == id, include: t => t.Include(x => x.ConcertTickets));
        }

        public Ticket CreateTicket(Ticket ticket)
        {
            return _ticketRepository.Insert(ticket);
        }

        public Ticket UpdateTicket(Ticket ticket)
        {
            return _ticketRepository.Update(ticket);
        }

        public void DeleteTicket(Guid id)
        {
            var ticket = GetTicketDetails(id);
            if (ticket != null)
            {
                _ticketRepository.Delete(ticket);
            }
        }
    }
}
