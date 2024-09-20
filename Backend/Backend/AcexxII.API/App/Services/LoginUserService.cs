using AcexxII.API.App.Interfaces;
using AcexxII.API.Context;
using AcexxII.API.Repository.Models;
using Microsoft.EntityFrameworkCore;

namespace AcexxII.API.App.Services
{
    public class LoginUserService : ILoginUserInterface
    {

        private readonly ApplicationDbContext _context;

        public LoginUserService(ApplicationDbContext context)
        {
            _context = context;
        }

        public async Task<string> LoginAsync(UsersLoginModel loginModel)
        {

            // Verifica se é um paciente
            var paciente = await _context.Pacientes
                .FirstOrDefaultAsync(p => p.Email == loginModel.Email && p.Senha == loginModel.Senha);

            if (paciente != null)
            {
                return "Login bem-sucedido como paciente!";
            }

            // Verifica se é um psicólogo
            var psicologo = await _context.Pscicologos
                .FirstOrDefaultAsync(p => p.Email == loginModel.Email && p.Senha == loginModel.Senha);

            if (psicologo != null)
            {
                return "Login bem-sucedido como psicólogo!";
            }


            throw new NotImplementedException("Email ou senha incorretos.");
        }
    }
}
