using EConcert.Domain.DomainModels;
using EConcert.Repository.Interface;
using EConecrt.Service.Interface;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EConecrt.Service.Implementation
{
    public class ConcertService : IConcertService
    {
        private readonly IRepository<Concert> _concertRepository;

        public ConcertService(IRepository<Concert> concertRepository)
        {
            _concertRepository = concertRepository;
        }

        public List<Concert> GetAllConcerts()
        {
            return _concertRepository.GetAll(c => c).ToList();
        }

        public Concert GetConcertDetails(Guid id)
        {
            return _concertRepository.Get(c => c, predicate: c => c.Id == id);
        }

        public Concert CreateConcert(Concert concert)
        {
            return _concertRepository.Insert(concert);
        }

        public Concert UpdateConcert(Concert concert)
        {
            return _concertRepository.Update(concert);
        }

        public void DeleteConcert(Guid id)
        {
            var concert = GetConcertDetails(id);
            if (concert != null)
            {
                _concertRepository.Delete(concert);
            }
        }
    }
}
