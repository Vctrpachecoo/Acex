using AcexxII.API.Models;

namespace AcexxII.API.Repositories
{
    public interface ICadastroPacienteInterface
    {

        Task RegisterPaciente(UserPaciente paciente);
        
    }
}
