using AcexxII.API.App.Interfaces;
using AcexxII.API.Context;
using AcexxII.API.Models;
using AcexxII.API.Repository.Models;

namespace AcexxII.API.App.Services
{
    public class CadastroPsicologoService : ICadastroPsicologoInterface
    {


        // Injeção de dependência do banco de dados 

        private readonly ApplicationDbContext _context;

        public CadastroPsicologoService(ApplicationDbContext context)
        {

            _context = context;

        }

        public async Task RegisterPscicologo(UserPsicologo pscicologo)
        {

            _context.Pscicologos.Add(pscicologo);
            await _context.SaveChangesAsync();
        }


    }

}