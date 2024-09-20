using AcexxII.API.Models;
using AcexxII.API.Repository.Models;
using Microsoft.EntityFrameworkCore;

// Contexto de conexão do banco de dados 

namespace AcexxII.API.Context
{
    public class ApplicationDbContext : DbContext
    {

        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options) : base (options)
        {

        }

        public DbSet<UserPaciente> Pacientes { get; set; }

        public DbSet<UserPsicologo> Pscicologos { get; set; }

    }
}
