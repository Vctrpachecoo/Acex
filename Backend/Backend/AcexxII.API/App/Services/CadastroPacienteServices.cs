using AcexxII.API.Context;
using AcexxII.API.Models;
using AcexxII.API.Repositories;

namespace AcexxII.API.Services
{
    public class CadastroPacienteServices : ICadastroPacienteInterface
    {

        // Injeção de dependência do banco de dados 

        private readonly ApplicationDbContext _context;

        public CadastroPacienteServices(ApplicationDbContext context)
        {

            _context = context;
            
        }

        public async Task RegisterPaciente(UserPaciente paciente)
        {

            _context.Pacientes.Add(paciente);
            await _context.SaveChangesAsync();  
        }
    }
}
